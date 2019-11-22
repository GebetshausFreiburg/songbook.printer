package org.openskies.songbook.printer.elements;

import org.openskies.songbook.printer.parser.RenderMode;
import org.openskies.songbook.printer.parser.SongParserException;

/**
 * The Class LinebreakElement.
 */
public class LinebreakElement extends SongElement {

	/**
	 * Instantiates a new linebreak element.
	 *
	 * @param line
	 *            the line
	 * @param column
	 *            the column
	 * @param content
	 *            the content
	 */
	public LinebreakElement(int line, int column, String content) {
		super(SongElementType.LINEBREAK, line, column, content);
	}

	/**
	 * Instantiates a new linebreak element.
	 *
	 * @param type
	 *            the type
	 * @param line
	 *            the line
	 * @param column
	 *            the column
	 * @param content
	 *            the content
	 */
	public LinebreakElement(SongElementType type, int line, int column, String content) {
		super(type, line, column, content);
	}

	/**
	 * Count linebreaks before.
	 *
	 * @param element
	 *            the element
	 * @return the int
	 */
	private int countLinebreaksBefore(SongElement element) {
		int counter = 0;
		while (hasLinebreakBefore(element)) {
			element = getLinebreakBefore(element);
			counter++;
		}
		return counter;
	}

	/**
	 * Gets the linebreak before actual element
	 *
	 * @param element
	 *            the element
	 * @return the linebreak before
	 */
	private SongElement getLinebreakBefore(SongElement element) {
		SongElement beforeElement = element.getSong().getElementBefore(element);
		while (beforeElement.getType() == SongElementType.WHITESPACE) {
			beforeElement = element.getSong().getElementBefore(beforeElement);
		}
		if (beforeElement.getType() == SongElementType.LINEBREAK) {
			return beforeElement;
		}
		return null;
	}

	/**
	 * Checks for linebreak before.
	 *
	 * @param element
	 *            the element
	 * @return true, if successful
	 */
	private boolean hasLinebreakBefore(SongElement element) {
		if (element == null) {
			return false;
		}
		SongElement beforeElement = element.getSong().getElementBefore(element);
		while (beforeElement.getType() == SongElementType.WHITESPACE) {
			beforeElement = element.getSong().getElementBefore(beforeElement);
		}
		if (beforeElement.getType() == SongElementType.LINEBREAK) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openskies.songbook.printer.SongElement#render()
	 */
	@Override
	public String render(RenderMode mode) {
		SongElement beforeElement = this.getSong().getElementBefore(this);
		SongElement afterElement = this.getSong().getElementAfter(this);

		// if element before whitespace is chordpro or onsong, then not break line
		if (beforeElement.getType() == SongElementType.WHITESPACE) {
			SongElement beforeElement2 = this.getSong().getElementBefore(beforeElement);
			if (beforeElement2.getType() == SongElementType.CHORDPRO
					|| beforeElement2.getType() == SongElementType.ONSONG) {
				return "";
			}
		}

		// if (mode==RenderMode.PLAIN_WITH_TITLE) {
		// if (countLi{nebreaksBefore(this)>=1) {
		// return "";
		// }
		// }

		// if linesbreaks before this line, then skip linebreak
		if (countLinebreaksBefore(this) > 1) {
			return "";
		}

		// if element before this linebreak is chordpro or onsong, then not break line
		if (beforeElement.getType() == SongElementType.CHORDPRO || beforeElement.getType() == SongElementType.ONSONG) {
			return "";
		}
		
		if (mode != RenderMode.PLAIN) {
			if (beforeElement.getType() == SongElementType.TEXTONLY) {
				return "<br class=\"smallbreak\">";
				
			}
		
		} else {
			return "\n";
		}

	


		// return html-linebreak
		return "</br>";
		
	}
	
	
}
