package com.cisco.cmad.stackflood.api;

import java.util.List;

import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.model.mongod.Reply;

public interface Post {

	public PostDetails fetch(String postId);
	
	public PostDetails add(PostDetails postDetails);
	
	public List<PostDetails> fetch();
	
	public List<PostDetails> search(String searchString);
	
	public PostDetails addReply(String postId, Reply reply);
	
	public boolean updteViews(String postId);
	
}
