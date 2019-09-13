package org.openskies.songbook.printer.elements;

import org.openskies.songbook.printer.parser.RenderMode;

public class TextOnlyLine extends SongElement {

	public TextOnlyLine(int line, int column, String content) {
		super(SongElementType.TEXTONLY, line, column, content);
	}

	
	public TextOnlyLine(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}
	
	
}
