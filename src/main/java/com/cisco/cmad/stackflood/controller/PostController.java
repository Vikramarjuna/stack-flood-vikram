package com.cisco.cmad.stackflood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.cmad.stackflood.api.Post;
import com.cisco.cmad.stackflood.model.mongod.PostDetails;
import com.cisco.cmad.stackflood.model.mongod.Reply;
import com.cisco.cmad.stackflood.service.PostImpl;

@Path("/post")
public class PostController {

	@Context
    private HttpServletResponse servletResponse;
	
	@Context
	private HttpServletRequest servletRequest;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response add(PostDetails postDetails){
		Post post = new PostImpl();
		PostDetails postDetailsResponse = post.add(postDetails);
		return Response.ok().entity(postDetailsResponse).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{postid}")
	public Response fetch(@PathParam("postid") String postId){
		Post post = new PostImpl();
		PostDetails postDetails = post.fetch(postId);
		return Response.ok().entity(postDetails).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/reply/{postid}")
	public Response addReply(@PathParam("postid") String postId, Reply reply){
		Post post = new PostImpl();
		PostDetails postDetailsResponse = post.addReply(postId, reply);
		return Response.ok().entity(postDetailsResponse).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/view/{postid}")
	public Response updateViews(@PathParam("postid") String postId){
		Post post = new PostImpl();
		post.updteViews(postId);
		return Response.ok().build();
	}
	
}
