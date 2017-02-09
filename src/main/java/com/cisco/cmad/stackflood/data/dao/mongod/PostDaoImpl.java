package com.cisco.cmad.stackflood.data.dao.mongod;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.cisco.cmad.stackflood.data.api.PostDao;
import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.model.mongod.Reply;
import com.cisco.cmad.stackflood.util.MongoConnection;

public class PostDaoImpl extends BasicDAO<PostDetails, Integer> implements PostDao{
	
	static MongoConnection conn = MongoConnection.getInstance();
	
	public PostDaoImpl(){
		this(conn.getDatastore());
	}
	
	protected PostDaoImpl(Datastore ds) {
		super(ds);
	}
	
	public PostDetails read(String postId) {
		PostDetails result = createQuery().field("_id").equal(new ObjectId(postId)).get();
		return result;
	}

	public List<PostDetails> read() {
		List<PostDetails> postDetailsList = find().asList();
		return postDetailsList;
	}

	public List<PostDetails> search(String searchString) {
		Query<PostDetails> q = createQuery();
		q.or(q.criteria("title").contains(searchString), q.criteria("article").contains(searchString), q.criteria("tags").contains(searchString));
		System.out.println(q.toString());
		List<PostDetails> postDetailsList = q.asList();
		return postDetailsList;
	}

	public PostDetails create(PostDetails postDetails){
		Key<PostDetails> obj = save(postDetails);
		if(null != obj && null != obj.getId())
			return postDetails;
		return null;
	}
	
	public PostDetails addReply(String postId, Reply reply){
		PostDetails postDetails = createQuery().field("_id").equal(new ObjectId(postId)).get();
		List<Reply> prepRepliesList = new ArrayList<Reply>();
		prepRepliesList.add(reply);
		if(null != postDetails.getReplies() && !postDetails.getReplies().isEmpty() && postDetails.getReplies().size() > 0){
			prepRepliesList.addAll(postDetails.getReplies());
		}
		
		Query<PostDetails> query = createQuery().field("_id").equal(new ObjectId(postId));
		UpdateOperations<PostDetails> ops = createUpdateOperations().set("replies", prepRepliesList).set("replyCount", postDetails.getReplyCount()+1);

		UpdateResults updateResults = update(query, ops);
		if(updateResults.getUpdatedCount() > 0){
			postDetails = createQuery().field("_id").equal(new ObjectId(postId)).get();
		}else{
			postDetails = null;
		}
		return postDetails;
	}
	
	public boolean updateViews(String postId){
		boolean success = false;
		PostDetails postDetails = createQuery().field("_id").equal(new ObjectId(postId)).get();
		Query<PostDetails> query = createQuery().field("_id").equal(new ObjectId(postId));
		UpdateOperations<PostDetails> ops = createUpdateOperations().set("viewCount", postDetails.getViewCount()+1);

		UpdateResults updateResults = update(query, ops);
		if(updateResults.getInsertedCount() > 0){
			success = true;
		}
		
		return success;
	}

}
