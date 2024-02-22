package org.jsp.springbootuserproductapp.exception;

public class IdNotFoundException extends RuntimeException{

	@Override
	public String getMessage() {
		return "Invalid Id";
	}
}
