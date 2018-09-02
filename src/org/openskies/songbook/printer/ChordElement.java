package org.openskies.songbook.printer;

public class ChordElement extends SongElement {

	public ChordElement(int line, int column, String content) {
		super(SongElementType.CHORD, line, column, content);
	}

	public ChordElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}

	public boolean isValid() {
		// Found at https://stackoverflow.com/questions/29144822/regex-for-standard-guitar-lyric-chord-bracketing. Added german used H and m. 
		boolean match = this.getContent().matches(
				"\\b([CDEFGABH](?:b|bb)*(?:#|##|sus|m|maj|min|aug|add)*[\\d\\/]*(?:[CDEFGABH](?:b|bb)*(?:#|##|sus|m|maj|min|aug|add)*[\\d\\/]*)*)(?=\\s|$)(?! \\w)");
		return match;
	}

	@Override
	public String toString() {
		return this.getContent();
	}

}
