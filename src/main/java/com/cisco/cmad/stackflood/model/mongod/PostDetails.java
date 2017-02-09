package com.cisco.cmad.stackflood.model.mongod;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity
public class PostDetails extends BaseMongoDO{
	
	@Id
	@Property("postid")
	protected ObjectId postId;
	protected String title;
	protected String article;
	protected String createdBy;
	protected Date createdDate;
	protected String[] tags;
	protected int replyCount;
	protected int viewCount;
	protected List<Reply> replies;
	
	public PostDetails() {
		super();
	}

	public PostDetails(ObjectId postId, String title, String article, String createdBy, Date createdDate, String[] tags,
			int replyCount, int viewCount, List<Reply> replies) {
		super();
		this.postId = postId;
		this.title = title;
		this.article = article;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.tags = tags;
		this.replyCount = replyCount;
		this.viewCount = viewCount;
		this.replies = replies;
	}

	public String getPostId() {
		return postId.toHexString();
	}

	public void setPostId(ObjectId postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
}
