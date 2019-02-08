package org.openskies.songbook.printer.elements;

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
	PRECHORUS("Prechorus", "Pre-Chorus"),
	
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

	private final String[] aliases;
	
	public boolean isEqual(String checkAlias) {
		if (aliases!=null) {
			if (aliases.length>0) {
				for (String alias : aliases) {
					
					//System.out.println(checkAlias +" =?= "+alias);
					if (checkAlias.toLowerCase().startsWith((alias+":").toLowerCase())) {
				//		System.out.println(checkAlias +" =TRUE= "+alias);
						return true;
					}
				}
			}
		}
		
		if (checkAlias.toLowerCase().startsWith((this.name()+":").toLowerCase())) {
			//System.out.println(checkAlias +" =TRUE= "+this.name());
			return true;
		}
		return false;
	}
	
	private OnsongSubtype(String... aliases) {
		this.aliases = aliases;
	}
	
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
