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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class Songs which holds all loaded songs.
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
	 * Load all songs from data-directory.
	 */
	public void load() {
		songs = new ArrayList<Song>();
		try {
			Files.walk(Paths.get("data")).filter(Files::isRegularFile).forEach(file -> {
				Song song = null;
				try {
					song = new Song(file);
					songs.add(song);
				} catch (SongParserException e) {
					if (e.getCause() != null) {
						System.out.println("EXCEPTION: " + e.getCause().getMessage() + ": " + e.getMessage());
					} else {
						System.out.println("EXCEPTION: " + e.getMessage());
					}
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
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
