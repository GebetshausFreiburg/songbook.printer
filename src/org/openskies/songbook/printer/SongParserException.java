package org.openskies.songbook.printer;

public class SongParserException extends Exception {
	public SongParserException() {
		super();

	}

	public SongParserException(String message) {
		super(message);
	}

	public SongParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
