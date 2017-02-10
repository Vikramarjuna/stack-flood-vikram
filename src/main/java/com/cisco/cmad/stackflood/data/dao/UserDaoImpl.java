package com.cisco.cmad.stackflood.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cmad.stackflood.data.api.UserDao;
import com.cisco.cmad.stackflood.model.UserDetails;

public class UserDaoImpl extends BaseJPADao implements UserDao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
		super();
	}

	public UserDetails read(String userName) {
		LOGGER.debug("UserDaoImpl ::: Received request for read(String userName).");
		EntityManager em = factory.createEntityManager();
		UserDetails userDetails = em.find(UserDetails.class, userName);
		em.close();
		LOGGER.debug("UserDaoImpl ::: Completed request for read(String userName).");
		return userDetails;
	}
	
	public UserDetails authenticate(String userName, String password) throws NoResultException, NonUniqueResultException{
		LOGGER.debug("UserDaoImpl ::: Received request for authenticate(String userName, String password).");
		EntityManager em = factory.createEntityManager();
		UserDetails userDetails = (UserDetails) em.createQuery("from UserDetails u where u.userName = :uname AND u.password = :pwd").setParameter("uname", userName).setParameter("pwd", password).getSingleResult();
		em.close();
		LOGGER.debug("UserDaoImpl ::: Completed request for authenticate(String userName, String password).");
		return userDetails;
	}

	public UserDetails create(UserDetails userDetails) throws PersistenceException{
		LOGGER.debug("UserDaoImpl ::: Received request for authenticate(String userName, String password).");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(userDetails);
		em.getTransaction().commit();
		em.close();
		LOGGER.debug("UserDaoImpl ::: Completed request for authenticate(String userName, String password).");
		return userDetails;
	}

}
