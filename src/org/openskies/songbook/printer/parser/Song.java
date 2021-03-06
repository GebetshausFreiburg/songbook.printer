/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer.parser;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openskies.songbook.printer.SongbookPrinter;
import org.openskies.songbook.printer.Songs;
import org.openskies.songbook.printer.elements.ChordElement;
import org.openskies.songbook.printer.elements.ChordproElement;
import org.openskies.songbook.printer.elements.ChordproSubtype;
import org.openskies.songbook.printer.elements.IRenderer;
import org.openskies.songbook.printer.elements.OnsongElement;
import org.openskies.songbook.printer.elements.OnsongSubtype;
import org.openskies.songbook.printer.elements.SongElement;
import org.openskies.songbook.printer.elements.SongElementType;
import org.openskies.songbook.printer.elements.WordElement;
import org.openskies.songbook.printer.util.SongKey;

/**
 * 
 * Song represents a loaded file and holds the language, id and content of a
 * song.
 * 
 * @author Matthias Wegner
 * @since 27. Juli 2018
 *
 */
public class Song implements IRenderer {

	final static Logger LOGGER = LogManager.getLogger(Song.class);
	
	/** The source. */
	private String source;

	/** The id. */
	private String id;

	/** The language. */
	private String language;

	/** The filename. */
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
	 * @throws SongParserException
	 *             the song parser exception
	 */
	public Song(Path path) throws SongParserException {
		// get file from path
		File file = path.toFile();

		// get file-source as string
		source = file.toString();

		// get filename from file
		filename = file.getName();

		// split filename by underscore
		String[] n = filename.split("_");

		// subdirectory in data-directory should represent a songbook
		book = file.getParentFile().getName();

		// song-id of song should be first prefix in file
		id = n[0];

		// song-language should be second element in song-title
		language = n[1];
		
	
		// Check if book exists
		if (book.trim().equalsIgnoreCase("data")) {
			throw new SongParserException("No directory/songbook set which contains song '" + file.getName() + "'");
		}
		
		
		
		// Check if id is correct. Only upper-case letter with three-digits are allowed
		boolean isCorrectId = id.matches("([A-Z]{1})+((?!000)([0-9]{3}))");
		if (!isCorrectId) {
			throw new SongParserException("Invalid id in filename '" + file.getName() + "'",
					new Throwable("Invalid id"));
		}
		

		// Check whether filename is valid. No �,�,�,�
		boolean isValidFilename = filename.toString().matches("\\p{ASCII}+");
		if (!isValidFilename) {
			throw new SongParserException("Contains not-ASCII character: '" + file.getName() + "'",
					new Throwable("Invalid filename"));
		}

		// Check if enabled language exists (at the moment german, english). i8n
		// possible
		if (SongLanguage.isLanguage(language)) {
			elements = LexicalSongParser.parse(source);
			for (SongElement se : elements) {
				se.setSong(this);
			}
		} else {
			throw new SongParserException("No valid language in filename '" + file.getName() + "'",
					new Throwable("Invalid language"));
		}
		
		if (this.getKey() == null) {
			LOGGER.error("Key is missing '" + this.getTitle() + "'");		
		}

		// Validate song-content. Throws exception if something is wrong
		validateSongContent(file);
	}

	/**
	 * Gets the element after the actual element.
	 *
	 * @param element
	 *            the element after the actual
	 * @return the element after
	 */
	public SongElement getElementAfter(SongElement element) {
		// find index of actual element
		int index = elements.indexOf(element);

		// if index of element is last element return null, because no element after
		// this exists
		if (index + 1 >= elements.size()) {
			return null;
		}

		// return element with incremented index
		return elements.get(index + 1);
	}

	/**
	 * Gets the element before.
	 *
	 * @param element
	 *            the element
	 * @return the element before
	 */
	public SongElement getElementBefore(SongElement element) {
		// find index of actual element
		int index = elements.indexOf(element);

		// if index of actual element is first element return null, because no element
		// before exists
		if (index <= 0) {
			return null;
		}

		// return element with decremented index
		return elements.get(index - 1);
	}

	/*
	 * Gets the encoding of the first word-element. Expected UTF-8.
	 *
	 * @return the encoding
	 */
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

	/**
	 * Gets the calculated key of the song.
	 *
	 * @return the calculated key
	 */
	public String getCalculatedKey() {

		SongKey[] keys = SongKey.values();
		for (SongKey songKey : keys) {
			if (songKey.containsChords(this.getBaseChords())) {
				return songKey.name().replace("x", "#");
			}
		}

		return null;
	}

	/**
	 * Validate song content.
	 *
	 * @param file
	 *            the file of the song
	 * @throws SongParserException
	 *             the song parser exception
	 */

	private void validateSongContent(File file) throws SongParserException {
		

		// check title in chordpro-format exists (mandatory)
		ChordproSubtype[] values = { ChordproSubtype.TITLE };
		for (ChordproSubtype chordproSubtype : values) {
			ChordproElement element = this.getChordproElement(chordproSubtype);

			if (element == null) {
				throw new SongParserException(
						"No valid " + chordproSubtype.name().toLowerCase() + " in file '" + file.getName() + "'",
						new Throwable("Non-existing " + chordproSubtype.name().toLowerCase()));
			}
		}

		// check if all elements which indicate to be chord-pro are known from
		// application
		for (SongElement element : elements) {
			if (element.getType() == SongElementType.CHORDPRO) {
				ChordproElement ose = (ChordproElement) element;
				if (ose.getSubtype() == null) {
					throw new SongParserException(
							"Chordpro-Syntax '" + ose.getContent() + "' is unkown in file '" + file.getName() + "'",
							new Throwable("Unknown Chordpro-Syntax"));
				}
			}
		}

		// check if bridge in onsong-format is single line
		for (SongElement element : getElements()) {
			if (element.getType() == SongElementType.ONSONG) {
				OnsongElement ose = (OnsongElement) element;
				// check if all elements which indicate to be onsong are known from application
				if (ose.getSubtype() == null) {
					throw new SongParserException(
							"Onsong-Syntax '" + ose.getContent() + "' is unkown in file '" + file.getName() + "'",
							new Throwable("Unknown Onsong-Syntax"));

				}
			}
		}

		for (SongElement element : elements) {
			if (element.getType() == SongElementType.FAKEBREAK) {
				throw new SongParserException("Whitespace problem in " + element.getSong().getFilename() + " line: "
						+ (element.getLine() + 2), new Throwable("Fake linebreak"));
			}
		}		
		
		
		
	}

	/**
	 * Gets the invalid chords.
	 *
	 * @return the invalid chords
	 */
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

	/**
	 * Gets the base chords.
	 *
	 * @return the base chords
	 */
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

					if (chord.contains("maj")) {
						int idx = chord.indexOf("maj");
						chord = chord.substring(0, idx);
					}

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

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.render(RenderMode.PLAIN));
		return sb.toString();
	}

	/**
	 * Gets the chords.
	 *
	 * @return the chords
	 */
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

	/**
	 * Gets the meta.
	 *
	 * @return the meta
	 */
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
	 * Gets the artist.
	 *
	 * @return the artist
	 */
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

	/**
	 * Gets the capo.
	 *
	 * @return the capo
	 */
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

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
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

	/**
	 * Gets the onsong element.
	 *
	 * @param subtype
	 *            the subtype
	 * @return the onsong element
	 */
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

	/**
	 * Gets the content elements.
	 *
	 * @return the content elements
	 */
	private List<SongElement> getContentElements() {
		List<SongElement> elems = new ArrayList<SongElement>();
		for (SongElement songElement : elements) {
			boolean ignore = false;
			if (songElement.getType() == SongElementType.CHORDPRO) {
				ChordproElement elem = (ChordproElement) songElement;
				if (elem.getSubtype() == ChordproSubtype.TITLE) {
					ignore = true;
				}
				if (elem.getSubtype() == ChordproSubtype.CAPO) {
					ignore = true;
				}
				if (elem.getSubtype() == ChordproSubtype.KEY) {
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
				if (elem.getSubtype() == OnsongSubtype.CAPO) {
					ignore = true;
				}
				if (elem.getSubtype() == OnsongSubtype.KEY) {
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

	/**
	 * Gets the chordpro element.
	 *
	 * @param subtype
	 *            the subtype
	 * @return the chordpro element
	 */
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

	/**
	 * Gets the filename html.
	 *
	 * @return the filename html
	 */
	public Path getFilenameHtml() {
		return Paths.get("web/" + this.getFilename().replace(".txt", ".html"));
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		ChordproElement celement = getChordproElement(ChordproSubtype.KEY);
		if (celement != null) {
			
			return celement.getContent();
		}
		OnsongElement oelement = getOnsongElement(OnsongSubtype.KEY);
		if (oelement != null) {
			
			return oelement.getContent();
		}
		return null;

	}

	/**
	 * Gets the ccli.
	 *
	 * @return the ccli
	 */
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

	/**
	 * Gets the copyright.
	 *
	 * @return the copyright
	 */
	public String getCopyright() {
		ChordproElement chordproElement = getChordproElement(ChordproSubtype.COPYRIGHT);
		if (chordproElement != null) {
			String copyright = chordproElement.getContent().trim();
			if (copyright.startsWith("(c)")){
				copyright = " " + copyright.substring(3, copyright.length()).trim();
			}
			
			return copyright;
		}

		OnsongElement onsongElement = getOnsongElement(OnsongSubtype.COPYRIGHT);
		if (onsongElement != null) {
			String copyright = onsongElement.getContent().trim();
			if (copyright.startsWith("(c)")){
				copyright = " " + copyright.substring(3, copyright.length()).trim();
			}
			
			return copyright;
		}
		
		return null;
	}
	
	

	/**
	 * Gets the source-path of the song.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the source-path of the song.
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

	/**
	 * Render.
	 *
	 * @return the string
	 */
	public String render() {
		return render(RenderMode.WEB_WITH_HEADER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openskies.songbook.printer.IRenderer#render()
	 */
	@Override
	public String render(RenderMode mode) {
		StringBuilder sb = new StringBuilder();

		if (!mode.name().toLowerCase().startsWith("plain")) {
			if (mode != RenderMode.WEB_NO_HEADER) {
				// create html-header
				sb.append("<!DOCTYPE html>");
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<link rel=\"stylesheet\" href=\"styles.css\"/>");
				sb.append("<meta charset=\"utf-8\"/>");
				sb.append("</head>");
				sb.append("<body>");
			}
			sb.append("<div class=\"keyTitleArtistSong\">");
			
			// create key of song
			if (this.getKey() != null) {
				if (!this.getKey().equals("")) {
					sb.append("<div id=\"key\">" + this.getKey() + "</div>\n");
				}
			}else {
				sb.append("<div id=\"key\">"+ "?" + "</div>\n");
			}
			
			// create title of song
			sb.append("<div id=\"title\">" + this.getTitle() + "</div>\n");
			
			// create artist of song
			if (this.getArtist() != null) {
				if (!this.getArtist().equals("")) {
					sb.append("<div id=\"artist\">" + this.getArtist() + "</div>\n");
				}
			}

			// count linebreak-elements
			int length = count(SongElementType.LINEBREAK);

			float scale = (float) 1.0;

			// Scaling 100% if song has length smaller than 28 lines.
			// If length is greater, then downscale song by linear equation.
			if (length > SongbookPrinter.UNSCALED_SONG_LENGTH) {
				// linear equation. Maximum length is 48 linebreaks and need 60% scale.
				scale = (float) -0.4f * (length - SongbookPrinter.UNSCALED_SONG_LENGTH) / 20.0f + 0.96f;
			}

			// created css-id for calculated scale
			String s = ("scale" + scale + "").replace(".", "");
			
			
			// add css-style for scaled print
			sb.append("<style>" + "@media print {" + "  #" + s + " {" + " -ms-transform: scale(" + scale + ");"
					+ " -webkit-transform: scale(" + scale + ");" + " transform: scale(" + scale + ");"
					+ " transform-origin: top left;" + "	}" + "}" + "</style>");

			// open div of type "song". Is needed to make inline-content inside a boxed
			// element to avoid linebreak in scaled songs
			sb.append("<div class=\"song\" id=\"" + s + "\">");
			

		}

		if (RenderMode.PLAIN_WITH_TITLE == mode) {
			sb.append("<div class=\"song\" id=\"scale100\">");

			// create title of song
			sb.append("<div id=\"title\">" + this.getTitle() + "</div>\n");

			// create artist of song
			if (this.getArtist() != null) {
				if (!this.getArtist().equals("")) {
					sb.append("<div id=\"artist\">" + this.getArtist() + "</div>\n");
				}
			}
		}

		for (SongElement songElement : getContentElements()) {
			if (songElement.getType() == SongElementType.LINEBREAK
					|| songElement.getType() == SongElementType.WHITESPACE) {
				songElement.setEnabled(false);
			} else {
				break;
			}
		}

		// shrink whitespaces and linebreak after title to one single line (not more)
		boolean acceptWhitespaces = false;
		for (SongElement songElement : getContentElements()) {
			if (acceptWhitespaces || !(songElement.getType() == SongElementType.WHITESPACE
					|| songElement.getType() == SongElementType.LINEBREAK)) {
				sb.append(songElement.render(mode));
				acceptWhitespaces = true;
			}
		}

		if (mode != RenderMode.PLAIN) {
			// add ccli-number if available
			if (this.getCCLI() != null) {
				sb.append("<div id=\"ccli\">" + this.getCCLI() + "</div>\n");
			}
			
			
			sb.append("<div id=\"copyright\">" + this.getCopyright() + "</div>");

			// close div of id "song"
			sb.append("</div>");
			
			//close div of class "keyTitleArtistSong"
			sb.append("</div>");
			

			if (mode != RenderMode.WEB_NO_HEADER) {
				// create html-footer
				sb.append("</body>");
				sb.append("</html>");
			}
		}

		// cut whitespaces or linebreak from beginning of song (used for plain
		// rendering)
		String s = sb.toString();
		while (s.startsWith(" ") || s.startsWith("<br/>")) {
			s = s.trim();
			if (s.startsWith("<br/>")) {
				s = s.substring(5);
			}
		}

		return s;
	}

	/**
	 * Count songelement of given type
	 *
	 * @param type
	 *            the type of the songelement
	 * @return the amount of counted elements
	 */
	public int count(SongElementType type) {
		int counter = 0;
		for (SongElement songElement : elements) {
			if (songElement.getType() == type) {
				counter++;
			}
		}

		return counter;
	}

}
