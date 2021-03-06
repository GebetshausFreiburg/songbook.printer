package org.openskies.songbook.printer.elements;

import org.openskies.songbook.printer.parser.RenderMode;

/**
 * The Class ChordproElement.
 */
public class ChordproElement extends SongElement {

	/** The subtype. */
	public ChordproSubtype subtype;

	/**
	 * Instantiates a new chordpro element.
	 *
	 * @param line    the line
	 * @param column  the column
	 * @param content the content
	 */
	public ChordproElement(int line, int column, String content) {
		super(SongElementType.CHORDPRO, line, column, content);
		init();
	}

	/**
	 * Instantiates a new chordpro element.
	 *
	 * @param type    the type
	 * @param line    the line
	 * @param column  the column
	 * @param content the content
	 */
	public ChordproElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		parseSubtype(this.getContent());
	}

	/**
	 * Gets the subtype.
	 *
	 * @return the subtype
	 */
	public ChordproSubtype getSubtype() {
		return subtype;
	}

	/**
	 * Parses the subtype.
	 *
	 * @param subtype the subtype
	 */
	private void parseSubtype(String c) {
		ChordproSubtype[] values = ChordproSubtype.values();
		for (ChordproSubtype chordproSubtype : values) {
			String type = chordproSubtype.name().toLowerCase() + ":";
			int length = type.length();
			if (c.startsWith(type)) {
				subtype = chordproSubtype;
				this.setContent(this.getContent().substring(length, this.getContent().length()).trim());
			}

			if (c.equalsIgnoreCase(ChordproSubtype.SOC.name())
					|| c.equalsIgnoreCase(ChordproSubtype.START_OF_CHORUS.name())) {
				subtype = ChordproSubtype.SOC;
			}

			if (c.equalsIgnoreCase(ChordproSubtype.EOC.name())
					|| c.equalsIgnoreCase(ChordproSubtype.END_OF_CHORUS.name())) {
				subtype = ChordproSubtype.EOC;
			}

		}
	}

	@Override
	public String render(RenderMode mode) {
	
		if (mode == RenderMode.PLAIN) {
			return "";
		}

		if (getSubtype() == ChordproSubtype.SOC) {
			return "<div class=\"chorus\"><div class=\"chorus-text\">";
		}
		if (getSubtype() == ChordproSubtype.EOC) {
			return "</div></div>";
		}

		/*
		 * if (getSubtype() == ChordproSubtype.CAPO) { return "<i>Kapo: " +
		 * this.getContent() + "</i>"; }
		 */

		return this.getContent();
	}

}
