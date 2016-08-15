package org.bonitasoft.camel.bonita.exception;

public class BonitaException extends Exception {
	
	 private static final long serialVersionUID = 1L;

	    public BonitaException(String message) {
	        super(message);
	    }

	    public BonitaException(Throwable throwable) {
	        super(throwable);
	    }

}
