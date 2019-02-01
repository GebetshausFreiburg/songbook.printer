package org.openskies.songbook.printer;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * The Class Utils.
 */
public class Utils {

	/**
	 * Checks if is ne.
	 *
	 * @param string the string
	 * @return true, if is ne
	 */
	public static boolean isNE(String string) {
		return !isNNE(string);
	}

	/**
	 * Checks if is nne.
	 *
	 * @param string the string
	 * @return true, if is nne
	 */
	public static boolean isNNE(String string) {
		if (string != null) {
			if (!string.trim().equals("")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Detect Charset in a string.
	 *
	 * @param value the value
	 * @return the string
	 * @see https://www.turro.org/publications/?item=114&page=0
	 */
	public static String detectCharset(String value) {
		if (isNE(value))
			return null;

		String detectedCharset = charset(value, new String[] { "ISO-8859-1", "UTF-8" });
		return detectedCharset;
	}

	/**
	 * Convert.
	 *
	 * @param value        the value
	 * @param fromEncoding the from encoding
	 * @param toEncoding   the to encoding
	 * @return the string
	 */
	public static String convert(String value, String fromEncoding, String toEncoding) {
		try {
			return new String(value.getBytes(fromEncoding), toEncoding);
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	/**
	 * Space builder.
	 *
	 * @param count the count
	 * @param spacer the spacer
	 * @return the string
	 */
	public static String spaceBuilder(int count, String spacer) {

		if (count <= 0) {
			return "";
		}

		String s = "";
		for (int i = 0; i < count; i++) {
			s += spacer;
		}
		return s;
	}

	/**
	 * Calculate width.
	 *
	 * @param text the text
	 * @param font the font
	 * @return the int
	 */
	public static int calculateWidth(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		int textwidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textwidth;
	}

	/**
	 * Beautify.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String beautify(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}

	/**
	 * Charset.
	 *
	 * @param value    the value
	 * @param charsets the charsets
	 * @return the string
	 */
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
