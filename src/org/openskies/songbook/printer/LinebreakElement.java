package org.openskies.songbook.printer;

public class LinebreakElement extends SongElement {

	public LinebreakElement(int line, int column, String content) {
		super(SongElementType.LINEBREAK, line, column, content);
	}

	public LinebreakElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}

	private int countLinebreaksBefore(SongElement element) {
		int counter = 0;
		while (hasLinebreakBefore(element)) {
			element = getLinebreakBefore(element);
			counter++;
		}
		return counter;
	}

	private SongElement getLinebreakBefore(SongElement element) {
		SongElement beforeElement = element.getSong().getElementBefore(element);
		if (beforeElement.getType() == SongElementType.WHITESPACE) {
			SongElement beforeElement2 = element.getSong().getElementBefore(beforeElement);
			if (beforeElement2.getType() == SongElementType.LINEBREAK) {
				return beforeElement2;
			}
		}
		return null;
	}

	private boolean hasLinebreakBefore(SongElement element) {
		SongElement beforeElement = element.getSong().getElementBefore(element);
		if (beforeElement.getType() == SongElementType.WHITESPACE) {
			SongElement beforeElement2 = element.getSong().getElementBefore(beforeElement);
			if (beforeElement2.getType() == SongElementType.LINEBREAK) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String render() {
		SongElement beforeElement = this.getSong().getElementBefore(this);

		if (beforeElement.getType() == SongElementType.WHITESPACE) {
			SongElement beforeElement2 = this.getSong().getElementBefore(beforeElement);
			if (beforeElement2.getType() == SongElementType.CHORDPRO||beforeElement2.getType() == SongElementType.ONSONG) {
				return "";
			}
		}

		if (countLinebreaksBefore(this)>1) {
			return "";
		}

		if (beforeElement.getType() == SongElementType.CHORDPRO||beforeElement.getType() == SongElementType.ONSONG) {
			return "";
		}

		return "<br/>";
	}

}
