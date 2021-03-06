/*-***
 *
 * This file defines a stand-alone lexical analyzer for a song-file which is
 * written in chordpro or onsong format.
 *
 * Created with <TT>java -jar jflex-1.6.1.jar SongParserLogic.jflex</TT>
 *
 */

package org.openskies.songbook.printer.parser;

import java.util.ArrayList;
import java.util.List;

import org.openskies.songbook.printer.elements.ChordElement;
import org.openskies.songbook.printer.elements.ChordproElement;
import org.openskies.songbook.printer.elements.LinebreakElement;
import org.openskies.songbook.printer.elements.OnsongElement;
import org.openskies.songbook.printer.elements.SongElement;
import org.openskies.songbook.printer.elements.SongElementType;
import org.openskies.songbook.printer.elements.WordElement;
import java.util.*;
%%

/*-*
 * LEXICAL FUNCTIONS:
 */

%class LexicalSongParser
%standalone
%line
%column

%{
    /** List of parsed song elements*/
    private static List<SongElement> songElements = new ArrayList<SongElement>();

    /**
     * Returns the parsed song elements
     */
	public static List<SongElement> parse(String file) {
		songElements = new ArrayList<SongElement>();
		LexicalSongParser scanner = null;
		try {
			java.io.FileInputStream stream = new java.io.FileInputStream(file);
			java.io.Reader reader = new java.io.InputStreamReader(stream, "UTF-8");
			scanner = new LexicalSongParser(reader);
			while (!scanner.zzAtEOF)
				scanner.yylex();
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found : \"" + file + "\"");
		} catch (java.io.IOException e) {
			System.out.println("IO error scanning file \"" + file + "\"");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Unexpected exception:");
			e.printStackTrace();
		}
		return songElements;
	}

%}

/*-*
 * PATTERN DEFINITIONS:
 */

WORD=[a-zA-Z\x21-\x40\xA1-\xFF]+
CHORD=\[[^\]]*\]
CHORDPRO=\{[^\}]*\}
ONSONG=([\n\r][^\{](\w+)(-*)(\w*)[ ]?[123]?\:)[ ]?[A-H#bm]*
WHITE_SPACE_CHAR=[\ \t\r]
NEWLINE_CHAR=[\n\r]
LINEBREAK_FAKE=[\n\r][\ \t]+[\n\r]
TEXTONLY=[\n\r][^\{\}\[\]\n\r]+[\n\r]
%%

/**
 * LEXICAL RULES:
 */

<YYINITIAL> {WORD} { songElements.add(new WordElement(yyline, yycolumn, yytext())); }
{CHORD} { songElements.add(new ChordElement(yyline, yycolumn, yytext().replace("[", "").replace("]", ""))); }
{CHORDPRO} { songElements.add(new ChordproElement(yyline, yycolumn, yytext().replace("{", "").replace("}", ""))); }
{WHITE_SPACE_CHAR} { songElements.add(new SongElement(SongElementType.WHITESPACE, yyline, yycolumn, " ")); }
{NEWLINE_CHAR} { songElements.add(new LinebreakElement(yyline, yycolumn, "\n")); }
{ONSONG} { songElements.add(new OnsongElement(yyline, yycolumn, yytext().trim())); }
{LINEBREAK_FAKE} { songElements.add(new FakebreakElement(yyline, yycolumn, " ")); }
{TEXTONLY} { songElements.add(new TextOnlyLine(yyline, yycolumn, yytext())); }
.  { /*Do nothing*/ }
