package org.openskies.songbook.printer.elements;

/**
 * The Enum OnsongSubtype.
 */
public enum OnsongSubtype {

	/** The key. */
	KEY,
	/** The bridge. */
	BRIDGE,
	
	BRIDGE_1("Bridge 1"),
	BRIDGE_2("Bridge 2"),
	
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
	
	PRECHORUS_1("Prechorus 1", "Pre-Chorus 1"),
	
	PRECHORUS_2("Prechorus 2", "Pre-Chorus 2"),
	
	PRECHORUS_3("Prechorus 3", "Pre-Chorus 3"),
	
	POSTCHORUS("Postchorus", "Post-Chorus"),
	
	REPRISE,
	
	/** The ending. */
	ENDING,

	/** The intro. */
	INTRO,

	/** The outro. */
	OUTRO,

	/** The chorus. */
	CHORUS,
	
	CHORUS_2("Chorus 2"),

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
