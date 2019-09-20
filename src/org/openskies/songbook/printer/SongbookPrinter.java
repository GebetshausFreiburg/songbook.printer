/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openskies.songbook.printer.util.Comparators;
import org.openskies.songbook.printer.webserver.Webserver;

/**
 * Printer for Song-Files in ChordPro and OnSong-Format.
 *
 * @author Matthias Wegner
 * @since 27. Juli 2018
 */
public class SongbookPrinter {

	final static public int UNSCALED_SONG_LENGTH = 28;
	
	/** The Constant LOGGER. */
	final static Logger LOGGER = LogManager.getLogger(SongbookPrinter.class);

	/**
	 * The main method of the songbook-creator.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		// Load songs from data-directory
		Songs.getInstance().load();

		// Display how much songs are loaded
		LOGGER.info(Songs.getInstance().count() + " songs loaded.");

//		Songs.getInstance().writePdf("Songs_byTitle.pdf", Comparators.TITLE);
//		Songs.getInstance().writePdf("Songs_byId.pdf", Comparators.ID);

		// Write songs
		LOGGER.info("Write Songs (Single Html-Files)");
		Songs.getInstance().writeSongs();

		// Write songs
		LOGGER.info("Write Songs (Merged Html-File with Chords)");
		//Songs.getInstance().writeHtml("Songs_with_Chords.html", Comparators.LENGTH_INVERTED);
		Songs.getInstance().writeHtml("Songs_with_Chords.html", Comparators.ID);

		// Write songs
		LOGGER.info("Write Songs (Merged Html-File without Chords)");
		Songs.getInstance().writeHtmlPlain("Songs_without_Chords.html", Comparators.ID);
		
		// Write index
		LOGGER.info("Write Index-Html-File");
		Songs.getInstance().writeHtmlPlainIndex("Songs_index.html");
		Songs.getInstance().writeLinkIndex();
//		Songs.getInstance().createPrintableIndex();
		
		// Start server
//		LOGGER.info("Start Webserver through Launcher");
//		Webserver server = new Webserver();
//		server.openInBrowser();
//		server.run();

	}

}
