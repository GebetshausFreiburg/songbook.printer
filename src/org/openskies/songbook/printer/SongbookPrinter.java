/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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

		List<Song> songs = Songs.getInstance().getSongs();
		for (Song song : songs) {
			System.out.println(song.getTitle() + ": " + song.getBaseChords());
		}

		if (true) {
			System.out.println("Found invalid chords:");
			Map<String, Song> ichords = Songs.getInstance().getInvalidChords();
			Vector<String> keys = new Vector<String>(ichords.keySet());
			for (String key : keys) {
				System.out.println("   " + key + ": " + ichords.get(key).getTitle());
			}
		}

		/*
		 * List<Song> songs = Songs.getInstance().getSong("A032"); for (Song song : songs) { System.out.println(song.getSource()); }
		 */

	}

}
