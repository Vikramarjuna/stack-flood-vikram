package com.cisco.cmad.stackflood.data.api;

import java.util.List;

import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.model.mongod.Reply;

public interface PostDao {

	public PostDetails read(String postId);
	
	public List<PostDetails> read();
	
	public List<PostDetails> search(String searchString);
	
	public PostDetails create(PostDetails postDetails);
	
	public PostDetails addReply(String postId, Reply reply);
	
	public boolean updateViews(String postId);
	
}
