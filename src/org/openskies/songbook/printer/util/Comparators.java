package org.openskies.songbook.printer.util;

import java.util.Comparator;

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

}
