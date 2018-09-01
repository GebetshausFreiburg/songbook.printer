package org.openskies.songbook.printer;

import java.util.Iterator;

public enum SongLanguage {

	GERMAN("de"), ENGLISH("en");

	/** The text. */
	private final String language;

	SongLanguage(final String language) {
		this.language = language;
	}
	
	public static boolean isLanguage(String language) {
		SongLanguage[] languages = SongLanguage.values();
		for (SongLanguage songLanguage : languages) {
			if (songLanguage.isEquals(language)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isEquals(String language) {
		if (this.language.equalsIgnoreCase(language)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getLanguage() {
		return language;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.language;
	}

}
