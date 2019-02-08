/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openskies.songbook.printer.elements.ChordElement;
import org.openskies.songbook.printer.parser.RenderMode;
import org.openskies.songbook.printer.parser.Song;
import org.openskies.songbook.printer.parser.SongParserException;
import org.openskies.songbook.printer.util.Comparators;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.lowagie.text.DocumentException;

/**
 * The Class Songs which holds all loaded songs.
 */
public class Songs {

	/** The Constant LOGGER. */
	final static Logger LOGGER = LogManager.getLogger(Songs.class);

	/** The list of all songs. */
	private List<Song> songs = null;

	/**
	 * Gets the single instance of Songs.
	 *
	 * @return single instance of Songs
	 */
	public static Songs getInstance() {
		if (instance == null) {
			instance = new Songs();
		}
		return instance;
	}

	/** The instance. */
	private static Songs instance;

	/**
	 * Gets the songs.
	 *
	 * @return the songs
	 */
	public List<Song> getSongs() {
		return getSongs(Comparators.TITLE);
	}

	/**
	 * Gets the songs.
	 *
	 * @param comparator the comparator
	 * @return the songs
	 */
	public List<Song> getSongs(Comparator<Song> comparator) {
		java.util.Collections.sort(songs, comparator);
		return songs;
	}

	/**
	 * Gets the song.
	 *
	 * @param id the id
	 * @return the song
	 */
	public List<Song> getSong(String id) {
		List<Song> foundSongs = new ArrayList<Song>();
		for (Song song : songs) {
			if (song.getId().equals(id)) {
				foundSongs.add(song);
			}
		}
		return foundSongs;
	}

	/**
	 * Gets the invalid chords.
	 *
	 * @return the invalid chords
	 */
	public Map<String, Song> getInvalidChords() {
		Map<String, Song> invalidChords = new HashMap<String, Song>();
		for (Song song : songs) {
			for (ChordElement ce : song.getInvalidChords()) {
				if (!invalidChords.containsKey(ce.getContent())) {
					invalidChords.put(ce.getContent(), song);
				}
			}
		}

		return invalidChords;
	}

	/**
	 * Write html.
	 *
	 * @param filename   the filename
	 * @param cómparator the cómparator
	 */
	public void writeHtml(String filename, Comparator<Song> cómparator) {

		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link rel=\"stylesheet\" href=\"web/styles.css\"/>");
		sb.append("<meta charset=\"utf-8\"/>");
		sb.append("</head>");
		sb.append("<body>");

		for (Song ws : getSongs(cómparator)) {

			sb.append(ws.render(RenderMode.WEB_NO_HEADER));
			sb.append("<p style=\"page-break-after: always;\">&nbsp;</p>\n"
					+ "<p style=\"page-break-before: always;\">&nbsp;</p>");

		}

		sb.append("</body>");
		sb.append("</html>");

		Path p = Paths.get("." + File.separatorChar + filename);

		try {
			Files.createDirectories(p.getParent());
			if (Files.exists(p)) {
				Files.delete(p);
			}
			Files.createFile(p);
			BufferedWriter writer = Files.newBufferedWriter(p, StandardCharsets.UTF_8);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e);
		}

	}

	/*public void writePdf2(String filename, Comparator<Song> cómparator) {
		ITextRenderer renderer = new ITextRenderer();
		try {
			String s = new String();
			for (Song ws : getSongs(cómparator)) {

				try {
					// LOGGER.debug(ws.getTitle());
					renderer.setDocumentFromString(ws.render());
				s += ws.render();
				} catch (Exception e) {
					LOGGER.error("Error in generated html-file '" + ws.getId() + ") " + ws.getTitle()
							+ "'.  Change source-song.", e);
				}
			}

			renderer = new ITextRenderer();
			renderer.setDocumentFromString(s);
			renderer.layout();
			OutputStream outputStream = new FileOutputStream(filename);
			renderer.createPDF(outputStream);
			outputStream.close();

		} catch (FileNotFoundException e) {
			LOGGER.error(e);
//			LOGGER.error("Error in generated html-file '" + ws.getId()+") "+ws.getTitle() + "'. Change source-song.");
		} catch (DocumentException e) {
			LOGGER.error(e);
//			LOGGER.error("Error in generated html-file '" + ws.getId()+") "+ws.getTitle() + "'.  Change source-song.");
		} catch (IOException e) {
			LOGGER.error(e);
//			LOGGER.error("Error in generated html-file '" + ws.getId()+") "+ws.getTitle() + "'.  Change source-song.");
		}

	}*/

	/**
	 * Write pdf.
	 *
	 * @param filename   the filename
	 * @param cómparator the cómparator
	 */
	public void writePdf(String filename, Comparator<Song> cómparator) {

		try {
			OutputStream file = new FileOutputStream(new File(filename));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();

			RenderMode mode = RenderMode.PLAIN;

			for (Song ws : getSongs(cómparator)) {
				// create plain html from song
				String s = new String();
				if (RenderMode.PLAIN == mode) {
					s = "<html><head></head><body>";
					s += "<h1>" + ws.getTitle() + "</h1>";
					s += ws.getContent();
					s += "</body></html>";
				}
				if (RenderMode.WEB_WITH_HEADER == mode) {
					s += ws.render();
				}

//				LOGGER.debug(s);

				// parse html to pdf
				XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

				InputStream is = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));

				if (RenderMode.WEB_WITH_HEADER == mode) {

//						CSSResolver cssResolver = new StyleAttrCSSResolver();
//						CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(("web" + File.separatorChar + "styles.css").getBytes()));
//						cssResolver.addCss(cssFile);
//
//						XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(
//								XMLWorkerFontProvider.DONTLOOKFORFONTS);
//						fontProvider.register("ressources/fonts/open-sans/OpenSans-Regular.ttf");
//
//						CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
//						HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
//						htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
//
//						PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
//						HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
//						CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
//
//						XMLWorker w = new XMLWorker(css, true);
//						    XMLParser p = new XMLParser(w);
//						    ByteArrayInputStream stream = new ByteArrayInputStream(s.getBytes("UTF-8"));
//						       
//						    p.parse(stream, Charset.forName("UTF-8"));

					FileInputStream css = new FileInputStream(new File("web" + File.separatorChar + "styles.css"));
					worker.parseXHtml(writer, document, is, css, Charset.forName("UTF-8"));
				}
				if (RenderMode.PLAIN == mode) {
					worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"));
				}

				// pagebreak in pdf
				document.newPage();
			}

			document.close();
			file.close();

		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	/**
	 * Write songs.
	 */
	public void writeSongs() {
		List<Song> songs = getSongs();
		for (Song ws : songs) {
			Path p = ws.getFilenameHtml();
			try {
				Files.createDirectories(p.getParent());
				if (Files.exists(p)) {
					Files.delete(p);
				}
				Files.createFile(p);
				BufferedWriter writer = Files.newBufferedWriter(p, StandardCharsets.UTF_8);
				writer.write(ws.render());
				writer.close();
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * Load all songs from data-directory.
	 */
	public void load() {
		// Initialize emtpy list of songs
		songs = new ArrayList<Song>();
		try {
			// iterate through all files in data-directory (including subdirectories)
			Files.walk(Paths.get("data")).filter(Files::isRegularFile).forEach(file -> {
				Song song = null;
				try {
					// Instantiate new song
					song = new Song(file);

					// Add song to list of songs
					songs.add(song);
				} catch (SongParserException e) {
					// skip adding song to list if exception occurs
					if (e.getCause() != null) {
						// show cause if cause is not null
						LOGGER.error(e.getCause().getMessage() + ": " + e.getMessage());
					} else {
						// show exception message
						LOGGER.error(e.getMessage());
					}
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Write index.
	 */
	public void writeIndex() {
		StringBuilder sb = new StringBuilder();

		Path idx = Paths.get("web" + File.separatorChar + "index.html");

		// create html-header
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link rel=\"stylesheet\" href=\"styles.css\">");
		sb.append("<meta charset=\"utf-8\">");
		sb.append("</head>");
		sb.append("<body>\n");

		sb.append("<ul>\n");
		List<Song> songs = Songs.getInstance().getSongs();
		for (Song ws : songs) {
			// Path p = ws.getFilenameHtml();

			String s = ws.getFilename();
			File f = new File(s);

			sb.append("<li><a href=\"" + f.getName().replace(".txt", ".html") + "\">" + ws.getTitle() + "</a></li>\n");
		}
		sb.append("</ul>\n");

		// create html-footer
		sb.append("</body>");
		sb.append("</html>");

		try {
			Files.createDirectories(idx.getParent());
			if (Files.exists(idx)) {
				Files.delete(idx);
			}
			Files.createFile(idx);
			BufferedWriter writer = Files.newBufferedWriter(idx, StandardCharsets.UTF_8);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Count number of songs.
	 *
	 * @return the int
	 */
	public int count() {
		return songs.size();
	}

}
