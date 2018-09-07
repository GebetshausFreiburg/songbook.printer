package org.openskies.songbook.printer;

/**
 * The Class WordElement.
 */
public class WordElement extends SongElement {

	/**
	 * Gets the encoding.
	 *
	 * @return the encoding
	 */
	public String getEncoding() {
		return Utils.detectCharset(this.getContent());
	}
	
	/**
	 * Instantiates a new word element.
	 *
	 * @param line the line
	 * @param column the column
	 * @param content the content
	 */
	public WordElement(int line, int column, String content) {
		super(SongElementType.WORD, line, column, content);
	}
	
	/**
	 * Instantiates a new word element.
	 *
	 * @param type the type
	 * @param line the line
	 * @param column the column
	 * @param content the content
	 */
	public WordElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}

}
