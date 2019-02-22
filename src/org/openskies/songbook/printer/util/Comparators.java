package org.openskies.songbook.printer.util;

import java.util.Comparator;

import org.openskies.songbook.printer.elements.SongElementType;
import org.openskies.songbook.printer.parser.Song;

public class Comparators {

	public final static Comparator<Song> ID = new Comparator<Song>() {
		@Override
		public int compare(Song o1, Song o2) {
			return (o1.getId()+"_"+o2.getLanguage()).compareTo(o2.getId()+"_"+o2.getLanguage());
		}
	};
	
	public final static Comparator<Song> TITLE = new Comparator<Song>() {
		@Override
		public int compare(Song o1, Song o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	};
	
	public final static Comparator<Song> LENGTH = new Comparator<Song>() {
		@Override
		public int compare(Song o1, Song o2) {
			Integer i1 = o1.count(SongElementType.LINEBREAK);
			Integer i2 = o2.count(SongElementType.LINEBREAK);
			return i1.compareTo(i2);
		}
	};
	
	public final static Comparator<Song> LENGTH_INVERTED = new Comparator<Song>() {
		@Override
		public int compare(Song o1, Song o2) {
			Integer i1 = o1.count(SongElementType.LINEBREAK);
			Integer i2 = o2.count(SongElementType.LINEBREAK);
			return i2.compareTo(i1);
		}
	};

}
