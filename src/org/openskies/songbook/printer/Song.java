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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Song represents a loaded file and holds the language, id and content of a song.
 * 
 * @author Matthias Wegner
 * @since 27. Juli 2018
 *
 */
public class Song implements IRenderer {

	/** The source. */
	private String source;

	/** The id. */
	private String id;

	/** The language. */
	private String language;
	
	private String filename;

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
		filename = file.getName();
		
		String[] n = filename.split("_");
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
			throw new SongParserException("Invalid id in filename '" + file.getName() + "'", new Throwable("Invalid id"));
		}

		// Check if language exists
		if (SongLanguage.isLanguage(language)) {
			elements = LexicalSongParser.parse(source);
			for (SongElement se : elements) {
				se.setSong(this);
			}
		} else {
			throw new SongParserException("No valid language in filename '" + file.getName() + "'", new Throwable("Invalid language"));
		}

		validateSongContent(file);
	}

	public String getEncoding() {
		for (SongElement element : getElements()) {
			if (element.getType() == SongElementType.WORD) {
				String encoding = ((WordElement) element).getEncoding();
				if (encoding.equals(StandardCharsets.UTF_8.name())) {
					return StandardCharsets.UTF_8.name();
				}
			}
		}
		return StandardCharsets.ISO_8859_1.name();
	}

	private void validateSongContent(File file) throws SongParserException {
		ChordproSubtype[] values = { ChordproSubtype.TITLE, ChordproSubtype.COPYRIGHT };// ChordproSubtype.values();
		for (ChordproSubtype chordproSubtype : values) {
			ChordproElement element = this.getChordproElement(chordproSubtype);

			if (element == null) {
				throw new SongParserException("No valid " + chordproSubtype.name().toLowerCase() + " in file '" + file.getName() + "'",
						new Throwable("Non-existing " + chordproSubtype.name().toLowerCase()));
			}
		}

		for (SongElement element : getElements()) {
			if (element.getType() == SongElementType.CHORDPRO) {
				ChordproElement ose = (ChordproElement) element;
				if (ose.getSubtype() == null) {
					throw new SongParserException("Chordpro-Syntax '" + ose.getContent() + "' is unkown in file '" + file.getName() + "'",
							new Throwable("Unknown Chordpro-Syntax"));
				}
			}
		}

		for (SongElement element : getElements()) {
			if (element.getType() == SongElementType.ONSONG) {
				OnsongElement ose = (OnsongElement) element;
				if (ose.getSubtype() == OnsongSubtype.BRIDGE) {
					if (ose.getContent() != null) {
						if (!ose.getContent().equals("")) {
							throw new SongParserException("Bridge at line '" + ose.getLine() + "' must be in single line in file '" + file.getName() + "'",
									new Throwable("Bridge not in single line"));
						}
					}
				}
				if (ose.getSubtype() == null) {
					throw new SongParserException("Onsong-Syntax '" + ose.getContent() + "' is unkown in file '" + file.getName() + "'",
							new Throwable("Unknown Onsong-Syntax"));
				}
			}
		}
	}

	public List<ChordElement> getInvalidChords() {
		List<String> chords = new ArrayList<String>();
		List<ChordElement> chordElements = new ArrayList<ChordElement>();
		for (SongElement songElement : elements) {
			if (songElement.getType() == SongElementType.CHORD) {
				ChordElement ce = (ChordElement) songElement;
				String chord = ce.getContent();
				if (!ce.isValid()) {
					if (!chords.contains(chord)) {
						chords.add(chord);
						chordElements.add(ce);
					}
				}
			}
		}
		return chordElements;
	}

	public List<String> getBaseChords() {

		List<String> chords = new ArrayList<String>();

		for (SongElement songElement : elements) {
			if (songElement.getType() == SongElementType.CHORD) {
				String chord = songElement.getContent();
				ChordElement ce = (ChordElement) songElement;
				if (ce.isValid()) {

					if (chord.contains("/")) {
						int idx = chord.indexOf("/");
						chord = chord.substring(0, idx);
					}

					if (chord.contains("sus")) {
						int idx = chord.indexOf("sus");
						chord = chord.substring(0, idx);
					}

					// if (chord.contains("m")) {
					// int idx = chord.indexOf("m");
					// chord = chord.substring(0, idx);
					// }

					if (chord.contains("add")) {
						int idx = chord.indexOf("add");
						chord = chord.substring(0, idx);
					}

					chord = chord.replace("2", "").replace("4", "").replace("6", "").replace("7", "");

					chord = chord.replace("H", "B");

					if (!chords.contains(chord)) {
						chords.add(chord);
					}
				}
			}
		}
		return chords;
	}
	
	public String getFilename() {
		return filename;
	}

	public List<String> getChords() {

		List<String> chords = new ArrayList<String>();

		for (SongElement songElement : elements) {
			if (songElement.getType() == SongElementType.CHORD) {
				if (!chords.contains(songElement.getContent())) {
					ChordElement ce = (ChordElement) songElement;
					if (ce.isValid()) {
						chords.add(songElement.getContent());
					}
				}
			}
		}
		return chords;
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

	public String getArtist() {
		ChordproElement chordproElement = getChordproElement(ChordproSubtype.ARTIST);
		if (chordproElement != null) {
			return chordproElement.getContent();
		}

		OnsongElement onsongElement = getOnsongElement(OnsongSubtype.ARTIST);
		if (onsongElement != null) {
			return onsongElement.getContent();
		}

		return null;
	}

	public String getCapo() {
		ChordproElement chordproElement = getChordproElement(ChordproSubtype.CAPO);
		if (chordproElement != null) {
			return chordproElement.getContent();
		}

		OnsongElement onsongElement = getOnsongElement(OnsongSubtype.CAPO);
		if (onsongElement != null) {
			return onsongElement.getContent();
		}

		return null;
	}

	public String getTitle() {
		ChordproElement chordproElement = getChordproElement(ChordproSubtype.TITLE);
		if (chordproElement != null) {
			return chordproElement.getContent();
		}

		OnsongElement onsongElement = getOnsongElement(OnsongSubtype.TITLE);
		if (onsongElement != null) {
			return onsongElement.getContent();
		}

		return null;
	}

	private OnsongElement getOnsongElement(OnsongSubtype subtype) {
		for (SongElement songElement : elements) {
			if (songElement.getType() == SongElementType.ONSONG) {
				OnsongElement elem = (OnsongElement) songElement;
				if (elem.getSubtype() == subtype) {
					return elem;
				}
			}
		}
		return null;
	}

	private List<SongElement> getContentElements() {
		List<SongElement> elems = new ArrayList<SongElement>();
		for (SongElement songElement : elements) {
			boolean ignore = false;
			if (songElement.getType() == SongElementType.CHORDPRO) {
				ChordproElement elem = (ChordproElement) songElement;
				if (elem.getSubtype() == ChordproSubtype.TITLE) {
					ignore = true;
				}
				if (elem.getSubtype() == ChordproSubtype.ARTIST) {
					ignore = true;
				}
				if (elem.getSubtype() == ChordproSubtype.CCLI) {
					ignore = true;
				}
				if (elem.getSubtype() == ChordproSubtype.COPYRIGHT) {
					ignore = true;
				}
			}
			if (songElement.getType() == SongElementType.ONSONG) {
				OnsongElement elem = (OnsongElement) songElement;
				if (elem.getSubtype() == OnsongSubtype.TITLE) {
					ignore = true;
				}
				if (elem.getSubtype() == OnsongSubtype.ARTIST) {
					ignore = true;
				}
				if (elem.getSubtype() == OnsongSubtype.CCLI) {
					ignore = true;
				}
				if (elem.getSubtype() == OnsongSubtype.COPYRIGHT) {
					ignore = true;
				}
			}
			if (!ignore) {
				elems.add(songElement);
			}
		}

		return elems;
	}

	private ChordproElement getChordproElement(ChordproSubtype subtype) {
		for (SongElement songElement : elements) {
			if (songElement.getType() == SongElementType.CHORDPRO) {
				ChordproElement elem = (ChordproElement) songElement;
				if (elem.getSubtype() == subtype) {
					return elem;
				}
			}
		}
		return null;
	}

	public String getKey() {
		ChordproElement element = getChordproElement(ChordproSubtype.KEY);
		if (element != null) {
			return element.getContent();
		}
		return null;
	}

	public String getCCLI() {
		ChordproElement chordproElement = getChordproElement(ChordproSubtype.CCLI);
		if (chordproElement != null) {
			return chordproElement.getContent();
		}

		OnsongElement onsongElement = getOnsongElement(OnsongSubtype.CCLI);
		if (onsongElement != null) {
			return onsongElement.getContent();
		}
		return null;
	}

	public String getCopyright() {
		ChordproElement chordproElement = getChordproElement(ChordproSubtype.COPYRIGHT);
		if (chordproElement != null) {
			return chordproElement.getContent();
		}

		OnsongElement onsongElement = getOnsongElement(OnsongSubtype.COPYRIGHT);
		if (onsongElement != null) {
			return onsongElement.getContent();
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

	@Override
	public String render() {

		StringBuilder sb = new StringBuilder();

		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link rel=\"stylesheet\" href=\"styles.css\">");
		sb.append("<meta charset=\"utf-8\">");
		sb.append("</head>");
		sb.append("<body>");
		
		sb.append("<div id=\"title\">" + this.getTitle() + "</div>\n");
		sb.append("<div id=\"artist\">" + this.getArtist() + "</div>\n");

		for (SongElement songElement : getContentElements()) {
			sb.append(songElement.render());
		}

		if (this.getCCLI() != null) {
			sb.append("<div id=\"ccli\">" + this.getCCLI() + "</div>\n");
		}
		sb.append("<div id=\"copyright\">" + this.getCopyright() + "</div>");

		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}

}