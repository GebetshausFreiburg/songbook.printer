package org.openskies.songbook.printer.parser;

/**
 * The Enum SongLanguage.
 */
public enum SongLanguage {

	/** The german. */
	GERMAN("de"),
	/** The english. */
	ENGLISH("en");

	/** The text. */
	private final String language;

	/**
	 * Instantiates a new song language.
	 *
	 * @param language
	 *            the language
	 */
	SongLanguage(final String lang) {
		this.language = lang;
	}
	
	/**
	 * Checks if is language.
	 *
	 * @param language
	 *            the language
	 * @return true, if is language
	 */
	public static boolean isLanguage(String language) {
		SongLanguage[] languages = SongLanguage.values();
		for (SongLanguage songLanguage : languages) {
			if (songLanguage.isEquals(language)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if is equals.
	 *
	 * @param language
	 *            the language
	 * @return true, if is equals
	 */
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
