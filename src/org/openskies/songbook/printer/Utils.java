package org.openskies.songbook.printer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Utils {

	public static boolean isNE(String string) {
		return !isNNE(string);
	}

	public static boolean isNNE(String string) {
		if (string != null) {
			if (!string.trim().equals("")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Detect Charset in a string
	 * 
	 * @see https://www.turro.org/publications/?item=114&page=0
	 * @param value
	 * @return
	 */
	public static String detectCharset(String value) {
		if (isNE(value))
			return null;
		
		String detectedCharset = charset(value, new String[] { "ISO-8859-1", "UTF-8" });
		return detectedCharset;
	}

	public static String convert(String value, String fromEncoding, String toEncoding) {
		try {
			return new String(value.getBytes(fromEncoding), toEncoding);
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	private static String charset(String value, String charsets[]) {
		String probe = StandardCharsets.UTF_8.name();
		
		for (String c : charsets) {
			Charset charset = Charset.forName(c);
			if (charset != null) {
				if (value.equals(convert(convert(value, charset.name(), probe), probe, charset.name()))) {
					return c;
				}
			}
		}
		return StandardCharsets.UTF_8.name();
	}

}
