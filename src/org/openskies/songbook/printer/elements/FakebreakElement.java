package org.openskies.songbook.printer.elements;

public class FakebreakElement extends SongElement {
	
	public FakebreakElement(int line, int column, String content) {
		super(SongElementType.FAKEBREAK, line, column, content);
	}

	
	public FakebreakElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}


}
