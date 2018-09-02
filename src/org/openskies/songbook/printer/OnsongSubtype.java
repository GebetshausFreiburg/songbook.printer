package org.openskies.songbook.printer;

public enum OnsongSubtype {

	KEY, BRIDGE, TAG, ARTIST, TITLE, AUTHOR, CAPO;
	
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
