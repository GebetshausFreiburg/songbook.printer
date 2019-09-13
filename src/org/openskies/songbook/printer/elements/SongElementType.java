package org.openskies.songbook.printer.elements;

/**
 * The Enum SongElementType.
 */
public enum SongElementType {

	/** The chordpro. */
	CHORDPRO,
	/** The word. */
	WORD,
	/** The whitespace. */
	WHITESPACE,
	
	WHITEONLY,
	/** The linebreak. */
	
	LINEBREAK,
	
	FAKEBREAK,
	
	TEXTONLY,
	/** The chord. */
	CHORD,
	/** The onsong. */
	ONSONG;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name().toUpperCase();
	}

}
