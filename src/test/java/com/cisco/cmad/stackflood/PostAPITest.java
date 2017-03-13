package com.cisco.cmad.stackflood;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.cisco.cmad.stackflood.api.Post;
import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.service.PostImpl;

public class PostAPITest {
	Post service;

	@Before
	public void setup() {
		//service=new PostImpl();
	}

	@Test
	public void testPartitions() {
//		PostDetails postData=new PostDetails();
//		postData.setArticle("first article data");
//		postData.setTitle("first article");
//		postData.setCreatedBy("vikram");
//		postData.setTags(new String[]{"tst"});
//		service.add(postData);
		assertTrue(true);
	}


}
