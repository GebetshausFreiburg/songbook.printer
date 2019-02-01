package org.openskies.songbook.printer;

/**
 * The Enum ChordproSubtype.
 */
public enum ChordproSubtype {

	/** The title. */
	TITLE,
	/** The copyright. */
	COPYRIGHT,
	/** The artist. */
	ARTIST,
	/** The key. */
	KEY,
	/** The soc. */
	SOC,
	START_OF_CHORUS,
	/** The eoc. */
	EOC,
	END_OF_CHORUS,
	/** The ccli. */
	CCLI,
	/** The subtitle. */
	SUBTITLE,
	/** The comment. */
	COMMENT,
	/** The capo. */
	CAPO;

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
