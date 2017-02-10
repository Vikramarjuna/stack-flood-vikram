package com.cisco.cmad.stackflood.exception;

@SuppressWarnings("serial")
public class UnAuthorizedException extends BaseException{

	public UnAuthorizedException() {
		super();
	}

	public UnAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnAuthorizedException(String message) {
		super(message);
	}

	public UnAuthorizedException(Throwable cause) {
		super(cause);
	}

}
