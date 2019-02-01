package org.openskies.songbook.printer;

import java.awt.Font;

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
	 * Checks if chord is valid. Check against regular expression
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
		// look for element after actual chord
		SongElement afterElement = this.getSong().getElementAfter(this);
		
		boolean whitespaceflag = false;
		boolean chordflag = false;
		
		// if element after actual chord exists, then ...
		if (afterElement!=null) {
			
			// ... check if it is whitespace ...
			if (afterElement.getType()==SongElementType.WHITESPACE) {
				whitespaceflag = true;
			}
			
			// ... check if it is chord ...
			if (afterElement.getType()==SongElementType.CHORD) {
				chordflag = true;
			}
			
			// ... and disable element after chord if whitespace or chord. Use chord-block-css instead.
			if (whitespaceflag||chordflag) {
				afterElement.setEnabled(false);
				return "<span class=\"chord-block\" chord=\""+this.getContent()+" \">&nbsp;&nbsp;</span>";
			}
			
			// estimate word length (with sans-serif-font)
			if (afterElement.getType()==SongElementType.WORD) {
				Font arial = new Font("Arial", Font.PLAIN, 12);
				
				// estimate length of element after chord
				int afterWordWidth = Utils.calculateWidth(afterElement.getContent(), arial);
				
				// estimate length of actual chord
				int chordWidth = Utils.calculateWidth(this.getContent(), arial);
				
				// if chord is longer than element after, then ...
				if ((chordWidth)>=afterWordWidth) {
					// ... calculate needed spaces
					double diff = chordWidth - afterWordWidth;
					
					// calculate length of a whitespace
					double whitespaceWidth = Utils.calculateWidth(" ", arial);
					
					// estimate amount of needed spaces 
					int val = (int)Math.ceil(diff / whitespaceWidth);
					
					// create string-representation of html-nbsp-element
					String space = Utils.spaceBuilder(val, "&nbsp;");
					
					// disable element after chord
					afterElement.setEnabled(false);
					
					// return spaced chord
					return "<span class=\"chord-space\" chord=\""+this.getContent()+"\">"+afterElement.getContent()+space+"</span>";
				} else { // else add simple chord
					
					// disable element after chord
					afterElement.setEnabled(false);
					
					// return normal chord
					return "<span class=\"chord\" chord=\""+this.getContent()+"\">"+afterElement.getContent()+"</span>";			
				}
				
			}
		}
		
		return "";
	}

}
