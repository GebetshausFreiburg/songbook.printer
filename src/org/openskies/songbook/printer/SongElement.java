/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer;

import javax.rmi.CORBA.Util;

/**
 * 
 * Songelement which is part of a song.
 * 
 * @author Matthias Wegner
 * @since 27. Juli 2018
 *
 */
public class SongElement implements IRenderer {
	
	/** The type of a song. Could be WHITESPACE, LINEBREAK, CHORD or CHORDPRO */
	private SongElementType type;
	
	/** The line-position of the element in the song */
	private int line;
	
	/** The column-position of the element in the song */
	private int column;
	
	/** The content of the element */
	private String content;

	private Song song;
	
	public Song getSong() {
		return song;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}

	/**
	 * Instantiates a new song element.
	 *
	 * @param type the type
	 * @param line the line
	 * @param column the column
	 * @param content the content
	 */
	public SongElement(SongElementType type, int line, int column, String content) {
		this.setType(type);
		this.line = line;
		this.column = column;
		this.setContent(content);
		
	}

	/**
	 * Gets the type of the element
	 *
	 * @return the type
	 */
	public SongElementType getType() {
		return type;
	}

	/**
	 * Sets the type of a element
	 *
	 * @param type the new type
	 */
	public void setType(SongElementType type) {
		this.type = type;
	}

	/**
	 * Gets the line-position of an element
	 *
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Gets the column-position of an element
	 *
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Gets the content of the element
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content of the element
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(this.getClass().getSimpleName()+" [type=" + type + ", line=" + line + ", column=" + column);

		if (content != null) {
			if (!content.trim().equals("")) {
				sb.append(", content=" + content);
			}
		}

		sb.append("]");

		return sb.toString();
	}

	@Override
	public String render() {
		
		if (this.type==SongElementType.CHORDPRO) {
			ChordproElement e = (ChordproElement)this;
			if (e.getSubtype()==ChordproSubtype.SOC) {
				return "<div class=\"chorus\"><div class=\"chorus-text\">";
			}
			if (e.getSubtype()==ChordproSubtype.EOC) {
				return "</div></div>";
			}
		}
		
		return this.getContent();
	}

}
