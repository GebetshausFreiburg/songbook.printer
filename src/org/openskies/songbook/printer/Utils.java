package org.openskies.songbook.printer;

public class Utils {

	
	public static boolean isNE(String string) {
		return !isNNE(string);
	}
	
	public static boolean isNNE(String string) {
		if (string!=null) {
			if (!string.trim().equals("")) {
				return true;
			}
		}
		return false;
	}
	
}
