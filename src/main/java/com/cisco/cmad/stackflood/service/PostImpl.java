package com.cisco.cmad.stackflood.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.cmad.stackflood.api.Post;
import com.cisco.cmad.stackflood.data.api.PostDao;
import com.cisco.cmad.stackflood.data.dao.mongod.PostDaoImpl;
import com.cisco.cmad.stackflood.exception.PostNotFoundException;
import com.cisco.cmad.stackflood.exception.StackFloodException;
import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.model.mongod.Reply;

public class PostImpl implements Post{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostImpl.class);
	
	private static PostDao postDao;

	public PostImpl() {
		postDao = new PostDaoImpl();
	}

	public PostDetails fetch(String postId) {
		LOGGER.debug("PostImpl ::: Received request for fetch(String postId).");
		PostDetails postDetails = null;
		try {
			postDetails = postDao.read(postId);
			if (postDetails == null){
				LOGGER.error("PostImpl :: fetch(String postId) - PostNotFoundException : Post not found");
				throw new PostNotFoundException("Post not found");
			}
		} catch (Exception e) {
			if(e instanceof IllegalArgumentException){
				LOGGER.error("PostImpl :: fetch(String postId) - PostNotFoundException : Post not found");
				throw new PostNotFoundException("Post not found");
			}
			LOGGER.error("PostImpl :: fetch(String postId) - Exception : " + e.getMessage(),e);
			throw new StackFloodException(e.getMessage());
			
		}
		LOGGER.debug("PostImpl ::: Completed request for fetch(String postId).");
		return postDetails;
	}

	public PostDetails add(PostDetails postDetails) {
		LOGGER.debug("PostImpl ::: Received request for add(PostDetails postDetails).");
		PostDetails postDetailsResponse = null;
		try {
			postDetails.setReplyCount(0);
			postDetails.setViewCount(0);
			postDetails.setCreatedDate(new Date());
			postDetailsResponse = postDao.create(postDetails);
		} catch (Exception e) {
			LOGGER.error("PostImpl :: add(PostDetails postDetails) - Exception : " + e.getMessage(),e);
			throw new StackFloodException(e.getMessage());
		}
		LOGGER.debug("PostImpl ::: Completed request for add(PostDetails postDetails).");
		return postDetailsResponse;
	}

	public List<PostDetails> fetch() {
		LOGGER.debug("PostImpl ::: Received request for fetch().");
		List<PostDetails> postDetailsList = null;
		try {
			postDetailsList = postDao.read();
			if (postDetailsList == null){
				LOGGER.error("PostImpl :: fetch() - PostNotFoundException : No posts available");
				throw new PostNotFoundException("No posts available");
			}
		} catch (Exception e) {
			LOGGER.error("PostImpl :: fetch() - Exception : " + e.getMessage(),e);
			throw new StackFloodException(e.getMessage());
		}
		LOGGER.debug("PostImpl ::: Completed request for fetch().");
		return postDetailsList;
	}
	
	public List<PostDetails> search(String searchString) {
		LOGGER.debug("PostImpl ::: Received request for search(String searchString).");
		List<PostDetails> postDetailsList = null;
		try {
			postDetailsList = postDao.search(searchString);
			if (postDetailsList == null){
				LOGGER.error("PostImpl :: search(String searchString) - PostNotFoundException : No posts available");
				throw new PostNotFoundException("No posts available");
			}
		} catch (Exception e) {
			LOGGER.error("PostImpl :: search(String searchString) - Exception : " + e.getMessage(),e);
			throw new StackFloodException(e.getMessage());
		}
		LOGGER.debug("PostImpl ::: Completed request for search(String searchString).");
		return postDetailsList;
	}

	public PostDetails addReply(String postId, Reply reply) {
		LOGGER.debug("PostImpl ::: Received request for addReply(String postId, Reply reply).");
		PostDetails postDetailsResult = null;
		try {
			reply.setCreatedDate(new Date());
			postDetailsResult = postDao.addReply(postId, reply);
			if (postDetailsResult == null){
				LOGGER.error("PostImpl :: addReply(String postId, Reply reply) - PostNotFoundException : Post not found");
				throw new PostNotFoundException("Post not found");
			}
		} catch (Exception e) {
			if(e instanceof IllegalArgumentException){
				LOGGER.error("PostImpl :: fetch(String postId) - PostNotFoundException : Post not found");
				throw new PostNotFoundException("Post not found");
			}
			LOGGER.error("PostImpl :: addReply(String postId, Reply reply) - Exception : " + e.getMessage(),e);
			throw new StackFloodException(e.getMessage());
		}
		LOGGER.debug("PostImpl ::: Completed request for addReply(String postId, Reply reply).");
		return postDetailsResult;
	}

	public boolean updteViews(String postId) {
		LOGGER.debug("PostImpl ::: Received request for updteViews(String postId).");
		boolean success = false;
		try {
			success = postDao.updateViews(postId);
		} catch (Exception e) {
			if(e instanceof IllegalArgumentException){
				LOGGER.error("PostImpl :: fetch(String postId) - PostNotFoundException : Post not found");
				throw new PostNotFoundException("Post not found");
			}
			LOGGER.error("PostImpl :: updteViews(String postId) - Exception : " + e.getMessage(),e);
			throw new StackFloodException(e.getMessage());
		}
		LOGGER.debug("PostImpl ::: Completed request for updteViews(String postId).");
		return success;
	}

}
