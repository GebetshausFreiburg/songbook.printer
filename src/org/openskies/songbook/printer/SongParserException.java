package org.openskies.songbook.printer;

/**
 * The Class SongParserException.
 */
public class SongParserException extends Exception {
	
	/**
	 * Instantiates a new song parser exception.
	 */
	public SongParserException() {
		super();

	}

	/**
	 * Instantiates a new song parser exception.
	 *
	 * @param message the message
	 */
	public SongParserException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new song parser exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public SongParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
