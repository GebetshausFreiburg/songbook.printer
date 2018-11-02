package org.openskies.songbook.printer;

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
		if (afterElement!=null) {
			if (afterElement.getType()==SongElementType.WHITESPACE) {
				whitespaceflag = true;
			}
			/*if (afterElement.getType()==SongElementType.WORD) {
				int lengthWord = afterElement.getContent().length();
				int lengthChord = this.getContent().length();
				if (lengthChord>=lengthWord) {
					int indexChord = this.getSong().getElements().indexOf(this);
					
					int diff = (lengthChord-lengthWord)+1;
					
					//System.out.println(this.getSong().getTitle() + ": "+diff + "("+this.getContent()+")");
					
					for (int i = 0; i < diff; i++) {
						SongElement spacer = new SongElement(SongElementType.WHITESPACE,this.getLine(),this.getColumn()," ");
						spacer.setSong(this.getSong());
						this.getSong().getElements().add(indexChord+1, spacer);	
					}
					
				}
			}*/
		}
		
		if (whitespaceflag) {
			return "<span class=\"chord\"><span class=\"innerwithspace\">"+this.getContent()+"</span></span>";
		} else {
			return "<span class=\"chord\"><span class=\"inner\">"+this.getContent()+"</span></span>";		
		}
		
	}

}
