package com.zking.exception;

import java.sql.SQLException;

public class ServiceException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException(){}
	
	public ServiceException(String message){
		super(message);
	}
	
}
