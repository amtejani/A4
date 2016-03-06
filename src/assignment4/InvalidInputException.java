package assignment4;

/*
Sonal Jain and Ali Tejani
sj23277 and amt3639
Assignment 4 - Word Ladder
*/

public class InvalidInputException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
