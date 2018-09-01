package org.openskies.songbook.printer;

public class ChordproElement extends SongElement {

	public ChordproSubtype subtype;

	public ChordproElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
		parseSubtype(content);
	}

	public ChordproSubtype getSubtype() {
		return subtype;
	}

	private void parseSubtype(String c) {
		ChordproSubtype[] values = ChordproSubtype.values();
		for (ChordproSubtype chordproSubtype : values) {
			String type = chordproSubtype.name().toLowerCase() + ":";
			int length = type.length();
			if (c.startsWith(type)) {
				subtype = chordproSubtype;
				this.setContent(this.getContent().substring(length, this.getContent().length()).trim());
			}
		}
	}

}
