package com.cisco.cmad.stackflood.service;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import com.cisco.cmad.stackflood.api.Post;
import com.cisco.cmad.stackflood.data.api.PostDao;
import com.cisco.cmad.stackflood.data.dao.mongod.PostDaoImpl;
import com.cisco.cmad.stackflood.exception.PostException;
import com.cisco.cmad.stackflood.exception.UserException;
import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.model.mongod.Reply;

public class PostImpl implements Post{
	
	private static PostDao postDao;

	public PostImpl() {
		postDao = new PostDaoImpl();
	}

	public PostDetails fetch(String postId) {
		PostDetails postDetails = null;
		try {
			postDetails = postDao.read(postId);
			if (postDetails == null)
				throw new PostException(new Integer(404), "Post not found");
		} catch (Exception e) {
				throw new PostException(new Integer(500), e.getMessage());
			
		}
		return postDetails;
	}

	public PostDetails add(PostDetails postDetails) {
		PostDetails postDetailsResponse = null;
		try {
			postDetails.setReplyCount(0);
			postDetails.setViewCount(0);
			postDetails.setCreatedDate(new Date());
			postDetailsResponse = postDao.create(postDetails);
			if (postDetailsResponse == null)
				throw new PostException(new Integer(400), "Bad request");
		} catch (Exception e) {
			if(e instanceof PersistenceException){
				throw new PostException(new Integer(600), "Post already exists.");
			}else{
				throw new PostException(new Integer(500), e.getMessage());
			}
		}
		return postDetailsResponse;
	}

	public List<PostDetails> fetch() {
		List<PostDetails> postDetailsList = null;
		try {
			postDetailsList = postDao.read();
			if (postDetailsList == null)
				throw new PostException(new Integer(404), "Posts not found");
		} catch (Exception e) {
				throw new UserException(new Integer(500), e.getMessage());
			
		}
		return postDetailsList;
	}
	
	public List<PostDetails> search(String searchString) {
		List<PostDetails> postDetailsList = null;
		try {
			postDetailsList = postDao.search(searchString);
			if (postDetailsList == null)
				throw new PostException(new Integer(404), "Posts not found");
		} catch (Exception e) {
				throw new UserException(new Integer(500), e.getMessage());
			
		}
		return postDetailsList;
	}

	public PostDetails addReply(String postId, Reply reply) {
		PostDetails postDetailsResult = null;
		try {
			reply.setCreatedDate(new Date());
			postDetailsResult = postDao.addReply(postId, reply);
			if (postDetailsResult == null)
				throw new PostException(new Integer(404), "Posts not found");
		} catch (Exception e) {
				throw new UserException(new Integer(500), e.getMessage());
			
		}
		return postDetailsResult;
	}

	public boolean updteViews(String postId) {
		boolean success = false;
		try {
			success = postDao.updateViews(postId);
		} catch (Exception e) {
				throw new UserException(new Integer(500), e.getMessage());
			
		}
		return success;
	}

}
