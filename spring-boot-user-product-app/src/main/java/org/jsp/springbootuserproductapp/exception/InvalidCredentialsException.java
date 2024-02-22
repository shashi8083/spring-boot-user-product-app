package org.jsp.springbootuserproductapp.exception;

public class InvalidCredentialsException extends RuntimeException{
	public InvalidCredentialsException(String message) {
		super(message);
	}
}
