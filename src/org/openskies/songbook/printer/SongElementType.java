package org.openskies.songbook.printer;

public enum SongElementType {

	CHORDPRO, WORD, WHITESPACE, LINEBREAK, CHORD, ONSONG;
	
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
