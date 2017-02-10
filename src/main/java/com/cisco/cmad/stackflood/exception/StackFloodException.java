package com.cisco.cmad.stackflood.exception;

@SuppressWarnings("serial")
public class StackFloodException extends BaseException{

	public StackFloodException() {
		super();
	}

	public StackFloodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StackFloodException(String message, Throwable cause) {
		super(message, cause);
	}

	public StackFloodException(String message) {
		super(message);
	}

	public StackFloodException(Throwable cause) {
		super(cause);
	}
	
}
