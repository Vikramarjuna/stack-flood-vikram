package com.cisco.cmad.stackflood.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cisco.cmad.stackflood.model.ErrorResponse;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<BaseException>{

	public Response toResponse(BaseException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatusCode(getStatusCode(e));
		errorResponse.setStatusMessage(e.getMessage());
		return Response.status(getStatusCode(e)).entity(errorResponse).build();
	}

	private int getStatusCode(BaseException e){
		int statusCode=500;
		if(e instanceof PostNotFoundException){
			statusCode = 404;
		}else if(e instanceof UnAuthorizedException){
			statusCode = 401;
		}else if(e instanceof UserExistsException){
			statusCode = 409;
		}else if(e instanceof UserNotFoundException){
			statusCode = 404;
		}else {
			statusCode = 500;
		}
		return statusCode;
	}
}
