package com.zking.exception;

import org.apache.shiro.authc.AuthenticationException;

public class AuthFailException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public AuthFailException(){}
	
	public AuthFailException(String message) {
		super(message);
	}
}
