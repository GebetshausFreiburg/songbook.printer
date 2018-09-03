package org.openskies.songbook.printer;

public enum ChordproSubtype {

	TITLE, COPYRIGHT, ARTIST, KEY, SOC, EOC, CCLI, SUBTITLE, COMMENT, CAPO;
	
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