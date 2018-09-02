package org.openskies.songbook.printer;

public class WordElement extends SongElement {

	public String getEncoding() {
		return Utils.detectCharset(this.getContent());
	}
	
	public WordElement(int line, int column, String content) {
		super(SongElementType.WORD, line, column, content);
	}
	
	public WordElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}

}
