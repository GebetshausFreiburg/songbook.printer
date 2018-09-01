/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * 
 * Song represents a loaded file and holds the language, id and content of a song.
 * 
 * @author Matthias Wegner
 * @since 27. Juli 2018
 *
 */
public class Song {

	/** The source. */
	private String source;

	/** The id. */
	private String id;

	/** The language. */
	private String language;

	/** The book. */
	private String book;

	/** The elements. */
	private List<SongElement> elements;

	/**
	 * Instantiates a new song.
	 *
	 * @param path
	 *            the path
	 */
	public Song(Path path) throws SongParserException {

		// TODO Ausgabe testen mit CCS + HTML, Stichwort jsfiddle.com

		File file = path.toFile();
		source = file.toString();

		String[] n = file.getName().split("_");
		book = file.getParentFile().getName();
		id = n[0];
		language = n[1];

		// Check if book exists
		if (book.trim().equalsIgnoreCase("data")) {
			throw new SongParserException("No directory/songbook set which contains song '" + file.getName() + "'");
		}

		// Check if id is correct
		boolean isCorrectId = id.matches("[A-Z]{1}[0-9]{3}");
		if (!isCorrectId) {
			throw new SongParserException("ID is invalid in File '" + file.getName() + "'");
		}

		// Check if language exists
		if (SongLanguage.isLanguage(language)) {
			elements = LexicalSongParser.parse(source);
		} else {
			throw new SongParserException("Songlanguage invalid in File '" + file.getName() + "'");
		}
		
		String title = this.getTitle();
		if (Utils.isNE(title)) {
			throw new SongParserException("Song contains no valid title in File '" + file.getName() + "'");	
		}
	}

	public String getMeta() {
		return "book=" + book + ", id=" + id + ", language=" + language + ", source=" + source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (SongElement songElement : elements) {
			// System.out.println(songElement.getType()+": "+songElement.getContent());
			sb.append(songElement.getContent());
		}
		return sb.toString();
	}

	public String getTitle() {
		for (SongElement songElement : elements) {
			if (songElement.getType()==SongElementType.CHORDPRO) {
				ChordproElement elem = (ChordproElement)songElement;
				if (elem.getSubtype()==ChordproSubtype.TITLE) {
					return elem.getContent();
				}
//				
//				if (songElement.getContent().startsWith("title:")) {
//					return songElement.getContent().replaceAll("title:", "").trim();
//				}
			}
		}
		return null;
	}

	/**
	 * Gets the source-path of the song
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the source-path of the song
	 *
	 * @param source
	 *            the new source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the book.
	 *
	 * @return the book
	 */
	public String getBook() {
		return book;
	}

	/**
	 * Sets the book.
	 *
	 * @param book
	 *            the new book
	 */
	public void setBook(String book) {
		this.book = book;
	}

	/**
	 * Gets the elements.
	 *
	 * @return the elements
	 */
	public List<SongElement> getElements() {
		return elements;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language
	 *            the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

}
