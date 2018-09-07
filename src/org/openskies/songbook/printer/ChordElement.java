package org.openskies.songbook.printer;

/**
 * The Class ChordElement.
 */
public class ChordElement extends SongElement {

	/**
	 * Instantiates a new chord element.
	 *
	 * @param line the line
	 * @param column the column
	 * @param content the content
	 */
	public ChordElement(int line, int column, String content) {
		super(SongElementType.CHORD, line, column, content);
	}

	/**
	 * Instantiates a new chord element.
	 *
	 * @param type the type
	 * @param line the line
	 * @param column the column
	 * @param content the content
	 */
	public ChordElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		// Found at https://stackoverflow.com/questions/29144822/regex-for-standard-guitar-lyric-chord-bracketing. Added german used H and m. 
		boolean match = this.getContent().matches(
				"\\b([CDEFGABH](?:b|bb)*(?:#|##|sus|m|maj|min|aug|add)*[\\d\\/]*(?:[CDEFGABH](?:b|bb)*(?:#|##|sus|m|maj|min|aug|add)*[\\d\\/]*)*)(?=\\s|$)(?! \\w)");
		return match;
	}

	/* (non-Javadoc)
	 * @see org.openskies.songbook.printer.SongElement#toString()
	 */
	@Override
	public String toString() {
		return this.getContent();
	}
	
	/* (non-Javadoc)
	 * @see org.openskies.songbook.printer.SongElement#render()
	 */
	@Override
	public String render() {
		return "<span class=\"chord\"><span class=\"inner\">"+this.getContent()+"</span></span>";
	}

}
