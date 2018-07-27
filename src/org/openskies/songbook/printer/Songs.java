/*
 * Songbook.Printer
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 27.07.2018
 * 
 */
package org.openskies.songbook.printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Songs which holds all loaded songs
 */
public class Songs {

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
		return songs;
	}

	/**
	 * Load all songs from data-directory
	 */
	public void load() {
		songs = new ArrayList<Song>();
		try {
			Files.walk(Paths.get("data")).filter(Files::isRegularFile).forEach(file -> {
				Song song = new Song(file);
				songs.add(song);
				// System.out.println(song);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Count number of songs
	 *
	 * @return the int
	 */
	public int count() {
		return songs.size();
	}

}
