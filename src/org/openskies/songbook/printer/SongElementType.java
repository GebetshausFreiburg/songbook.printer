package org.openskies.songbook.printer;

public enum SongElementType {

	CHORDPRO, WORD, WHITESPACE, LINEBREAK, CHORD;
	
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
