/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer;

/**
 * 
 * Printer for Song-Files in ChordPro and OnSong-Format.
 * 
 * @author Matthias Wegner
 * @since 27. Juli 2018
 *
 */
public class SongbookPrinter {

	public static void main(String[] args) {

		Songs.getInstance().load();
		System.out.println(Songs.getInstance().count() + " songs loaded.");

	}

}
