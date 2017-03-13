package com.cisco.cmad.stackflood.test;

import java.util.List;

import javax.ws.rs.core.Response;

import com.cisco.cmad.stackflood.api.Post;
import com.cisco.cmad.stackflood.controller.PostsController;
import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.service.PostImpl;
import com.cisco.cmad.stackflood.util.PropertiesUtil;

public class MyTest {
	public static void main(String[] args){
		
		try {
			
			PropertiesUtil.initLog4j();
		} catch (Exception e) {
			
		}

		try {
			
			PropertiesUtil.loadProperties("stack-flood");
		} catch (Exception e) {
			
		}
		
		
		
		PostsController controller=new PostsController();
		
	Response response=	controller.fetch();
Object object=	response.getEntity();
int length=	response.getLength();
	///List<PostDetails> posts=	service.fetch();
		
		PostDetails postData=new PostDetails();
		postData.setArticle("first article data");
		postData.setTitle("first article");
		postData.setCreatedBy("vikram");
		postData.setTags(new String[]{"tst"});
		//service.add(postData);
	}

}
