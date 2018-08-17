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
 * Song represents a loaded file and holds the language, id and content of a
 * song.
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
	 * @param path the path
	 */
	public Song(Path path) throws SongParserException {

		// TODO Needs exception-handling for wrong IDs. Stichwort: regexr.com
		// TODO Needs exception-handling for wrong Book.
		// TODO Sprachprüfung über enumeration lösen. Ziel: Text in Enum pflegen
		// TODO Ausgabe testen mit CCS + HTML, Stichwort jsfiddle.com

		File file = path.toFile();
		source = file.toString();
		

		String[] n = file.getName().split("_");
		book = file.getParentFile().getName();
		id = n[0];
		language = n[1];
		// System.out.println(this); //
		// X
		
		if (language.equals("en") || language.equals("de")) {
			elements = LexicalSongParser.parse(source);
		} else{
			throw new SongParserException("Songlanguage invalid");
//			System.out.println(elements);
//			System.out.println("Fehlerhafte Sprachangabe");
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
			sb.append(songElement.getContent());
		}
		return sb.toString();
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
	 * @param source the new source
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
	 * @param id the new id
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
	 * @param book the new book
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
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

}
