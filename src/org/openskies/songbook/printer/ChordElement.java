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
	 * Checks if is valid.
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
		
		SongElement afterElement = this.getSong().getElementAfter(this);
		
		boolean whitespaceflag = false;
		boolean chordflag = false;
		if (afterElement!=null) {
			
			if (afterElement.getType()==SongElementType.WHITESPACE) {
				whitespaceflag = true;
			}
			
			if (afterElement.getType()==SongElementType.CHORD) {
				chordflag = true;
			}
			
			if (whitespaceflag||chordflag) {
				afterElement.setEnabled(false);
				return "<span class=\"chord-block\" chord=\""+this.getContent()+" \">&nbsp;&nbsp;</span>";
			}
			
			if (afterElement.getType()==SongElementType.WORD) {
				int lengthWord = afterElement.getContent().length();
				int lengthChord = this.getContent().length();
				
				Font arial = new Font("Arial", Font.PLAIN, 12);
				
				int afterWordWidth = Utils.calculateWidth(afterElement.getContent(), arial);
				int chordWidth = Utils.calculateWidth(this.getContent(), arial);
				
				if ((chordWidth)>=afterWordWidth) {
					double diff = chordWidth - afterWordWidth;
					double whitespaceWidth = Utils.calculateWidth(" ", arial);
						
					int val = (int)Math.ceil(diff / whitespaceWidth);
					String space = Utils.spaceBuilder(val, "&nbsp;");
					
					afterElement.setEnabled(false);
					return "<span class=\"chord-space\" chord=\""+this.getContent()+"\">"+afterElement.getContent()+space+"</span>";
				} else {
					afterElement.setEnabled(false);
					return "<span class=\"chord\" chord=\""+this.getContent()+"\">"+afterElement.getContent()+"</span>";			
				}
				
			}
		}
		
		return "";
	}

}
