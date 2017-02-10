package com.cisco.cmad.stackflood.service;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cmad.stackflood.api.User;
import com.cisco.cmad.stackflood.data.dao.BaseJPADao;
import com.cisco.cmad.stackflood.data.dao.UserDaoImpl;
import com.cisco.cmad.stackflood.exception.StackFloodException;
import com.cisco.cmad.stackflood.exception.UnAuthorizedException;
import com.cisco.cmad.stackflood.exception.UserExistsException;
import com.cisco.cmad.stackflood.exception.UserNotFoundException;
import com.cisco.cmad.stackflood.model.Credentials;
import com.cisco.cmad.stackflood.model.UserDetails;

public class UserImpl implements User{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserImpl.class);
	
	private static BaseJPADao baseJPADao;

	public UserImpl() {
		baseJPADao = new UserDaoImpl();
	}

	public UserDetails authenticate(Credentials credentials) {
		LOGGER.debug("UserImpl ::: Received request for authenticate(Credentials credentials).");
		UserDetails userDetails = null;
		try {
			userDetails = ((UserDaoImpl) baseJPADao).authenticate(credentials.getUserName(), credentials.getPassword());
		} catch (Exception e) {
			LOGGER.error("UserImpl :: authenticate(Credentials credentials) - Exception : " + e.getMessage(),e);
			if(e instanceof NoResultException){
				throw new UnAuthorizedException("Unauthrized:Please check username and password");
			}else{
				throw new StackFloodException(e.getMessage());
			}
		}
		userDetails.setPassword("");
		LOGGER.debug("UserImpl ::: Completed request for authenticate(Credentials credentials).");
		return userDetails;
	}

	public UserDetails add(UserDetails userDetails) {
		LOGGER.debug("UserImpl ::: Received request for add(UserDetails userDetails).");
		UserDetails userDetailsResult = null;
		try {
			userDetailsResult = ((UserDaoImpl) baseJPADao).create(userDetails);
		} catch (Exception e) {
			LOGGER.error("UserImpl :: add(UserDetails userDetails) - Exception : " + e.getMessage(),e);
			if(e instanceof PersistenceException){
				throw new UserExistsException("User already exists, please check your username");
			}else{
				throw new StackFloodException(e.getMessage());
			}
		}
		LOGGER.debug("UserImpl ::: Completed request for add(UserDetails userDetails).");
		return userDetailsResult;
	}

	public UserDetails fetch(String userName) {
		LOGGER.debug("UserImpl ::: Received request for fetch(String userName).");
		UserDetails userDetailsResult = null;
		try {
			userDetailsResult = ((UserDaoImpl) baseJPADao).read(userName);
			if (userDetailsResult == null){
				LOGGER.error("UserImpl :: fetch(String userName) - UserNotFoundException : User does not exists");
				throw new UserNotFoundException("User does not exists");
			}
		} catch (Exception e) {
			LOGGER.error("UserImpl :: fetch(String userName) - Exception : " + e.getMessage(),e);
			throw new StackFloodException(e.getMessage());
		}
		LOGGER.debug("UserImpl ::: Completed request for fetch(String userName).");
		return userDetailsResult;
	}

}
