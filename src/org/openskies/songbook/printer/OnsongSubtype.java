package org.openskies.songbook.printer;

/**
 * The Enum OnsongSubtype.
 */
public enum OnsongSubtype {

	/** The key. */
	KEY,
	/** The bridge. */
	BRIDGE,
	/** The tag. */
	TAG,
	/** The artist. */
	ARTIST,
	/** The title. */
	TITLE,
	/** The author. */
	INTERLUDE,

	/** The prechorus. */
	PRECHORUS,

	/** The ending. */
	ENDING,

	/** The intro. */
	INTRO,

	/** The outro. */
	OUTRO,

	/** The chorus. */
	CHORUS,

	/** The author. */
	AUTHOR,
	/** The capo. */
	CAPO,
	/** The copyright. */
	COPYRIGHT,
	/** The ccli. */
	CCLI;

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
