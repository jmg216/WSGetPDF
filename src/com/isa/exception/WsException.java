package com.isa.exception;


public class WsException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	
    public WsException(String message){
        super(message);
    }
 
    public WsException(String message, Throwable cause) {
        super(message, cause);
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
    
    

}
