package com.playerauction.team.exception;

public class TeamAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TeamAlreadyExistException(String msg)
	{
		super(msg);
	}

}
