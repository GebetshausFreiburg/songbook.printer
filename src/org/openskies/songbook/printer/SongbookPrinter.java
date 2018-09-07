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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Printer for Song-Files in ChordPro and OnSong-Format.
 *
 * @author Matthias Wegner
 * @since 27. Juli 2018
 */
public class SongbookPrinter {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		Songs.getInstance().load();

		System.out.println(Songs.getInstance().count() + " songs loaded.");

		List<Song> songs = Songs.getInstance().getSongs();
		for (Song song : songs) {
			System.out.println("[" + song.getId() + "] " + song.getTitle() + ": " + song.getBaseChords() +" >>> "+song.getCalculatedKey());
		}

		if (true) {
			System.out.println("Found invalid chords:");
			Map<String, Song> ichords = Songs.getInstance().getInvalidChords();
			Vector<String> keys = new Vector<String>(ichords.keySet());
			for (String key : keys) {
				System.out.println("   " + key + ": " + ichords.get(key).getTitle());
			}
		}
		
		// TODO Erklärung Writer

		for (Song ws : songs) {
			Path p = Paths.get("web/" + ws.getFilename().replace(".txt", ".html"));
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
				e.printStackTrace();
			}
		}
	}

}
