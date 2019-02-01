package org.openskies.songbook.printer;

/**
 * The Class OnsongElement.
 */
public class OnsongElement extends SongElement {

	/** The subtype. */
	public OnsongSubtype subtype;

	/**
	 * Instantiates a new onsong element.
	 *
	 * @param line    the line
	 * @param column  the column
	 * @param content the content
	 */
	public OnsongElement(int line, int column, String content) {
		super(SongElementType.ONSONG, line, column, content);
		parseSubtype(content);
	}

	/**
	 * Instantiates a new onsong element.
	 *
	 * @param type    the type
	 * @param line    the line
	 * @param column  the column
	 * @param content the content
	 */
	public OnsongElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
		parseSubtype(content);
	}

	/**
	 * Gets the subtype.
	 *
	 * @return the subtype
	 */
	public OnsongSubtype getSubtype() {
		return subtype;
	}

	/**
	 * Parses the subtype.
	 *
	 * @param c the c
	 */
	private void parseSubtype(String c) {
		OnsongSubtype[] values = OnsongSubtype.values();
		for (OnsongSubtype osSubtype : values) {
			String type = osSubtype.name().toLowerCase() + ":";
			int length = type.length();
			if (c.toLowerCase().startsWith(type)) {
				subtype = osSubtype;
				this.setContent(this.getContent().substring(length, this.getContent().length()).trim());

				if (subtype == OnsongSubtype.KEY) {
					if (this.getContent().contains("[") || this.getContent().contains("]")) {
						this.setContent(this.getContent().replace("[", "").replace("]", ""));
					}
				}
			}
		}

	}

	@Override
	public String render() {

		if (this.getSubtype() == OnsongSubtype.KEY) {
			return "";
		} else {
			return "<b>" + this.getSubtype() +": "+this.getContent()+ "</b><br/>";
		}

	}

}
