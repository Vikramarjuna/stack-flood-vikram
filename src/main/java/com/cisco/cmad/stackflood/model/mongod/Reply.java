package com.cisco.cmad.stackflood.model.mongod;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity
public class Reply extends BaseMongoDO{
	
	@Id
	@Property("replyid")
	protected ObjectId replyId;
	protected String article;
	protected String createdBy;
	protected Date createdDate;
	
	public Reply() {
		super();
	}

	public Reply(ObjectId replyId, String article, String createdBy, Date createdDate) {
		super();
		this.replyId = replyId;
		this.article = article;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
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
	
}
