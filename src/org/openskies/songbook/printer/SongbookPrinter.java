package org.openskies.songbook.printer;

import java.util.List;

public class SongbookPrinter {

	public static void main(String[] args) {

		List<SongElement> elements = LexicalSongParser.parse("A191_en_Arms_Wide_Open.txt");
		for (SongElement songElement : elements) {
			System.out.println(songElement);
			//
		}
	}

}
