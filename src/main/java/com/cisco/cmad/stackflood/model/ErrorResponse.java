package com.cisco.cmad.stackflood.model;

public class ErrorResponse {
	
	protected Integer statusCode;
	protected String statusMessage;
	
	public ErrorResponse() {
		super();
	}

	public ErrorResponse(Integer statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

}
