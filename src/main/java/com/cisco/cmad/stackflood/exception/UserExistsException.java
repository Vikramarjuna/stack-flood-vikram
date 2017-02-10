package com.cisco.cmad.stackflood.exception;

@SuppressWarnings("serial")
public class UserExistsException extends BaseException{

	public UserExistsException() {
		super();
	}

	public UserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistsException(String message) {
		super(message);
	}

	public UserExistsException(Throwable cause) {
		super(cause);
	}
	
}
