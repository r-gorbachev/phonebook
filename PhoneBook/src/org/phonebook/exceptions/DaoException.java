package org.phonebook.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
