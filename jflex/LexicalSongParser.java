/* The following code was generated by JFlex 1.6.1 */

/*-***
 *
 * This file defines a stand-alone lexical analyzer for a song-file which is
 * written in chordpro or onsong format.
 *
 * Created with <TT>java -jar jflex-1.6.1.jar SongParserLogic.jflex</TT>
 *
 */
package org.openskies.songbook.printer;

import java.util.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>SongParserLogic.jflex</tt>
 */
class LexicalSongParser {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\6\1\7\1\10\1\10\1\11\22\0\1\6\17\1\12\13"+
    "\1\14\6\1\32\13\1\2\1\0\1\3\1\0\1\12\1\0\32\13"+
    "\1\4\1\0\1\5\7\0\1\10\33\0\11\1\1\13\12\1\1\13"+
    "\4\1\1\13\5\1\27\13\1\1\37\13\1\1\10\13\u01c2\12\4\0"+
    "\14\12\16\0\5\12\7\0\1\12\1\0\1\12\21\0\165\12\1\0"+
    "\2\12\2\0\4\12\1\0\1\12\6\0\1\12\1\0\3\12\1\0"+
    "\1\12\1\0\24\12\1\0\123\12\1\0\213\12\1\0\255\12\1\0"+
    "\46\12\2\0\1\12\7\0\47\12\11\0\55\12\1\0\1\12\1\0"+
    "\2\12\1\0\2\12\1\0\1\12\10\0\33\12\5\0\3\12\35\0"+
    "\13\12\5\0\112\12\4\0\146\12\1\0\10\12\2\0\12\12\1\0"+
    "\23\12\2\0\1\12\20\0\73\12\2\0\145\12\16\0\66\12\4\0"+
    "\1\12\5\0\56\12\22\0\34\12\104\0\23\12\61\0\200\12\2\0"+
    "\12\12\1\0\23\12\1\0\10\12\2\0\2\12\2\0\26\12\1\0"+
    "\7\12\1\0\1\12\3\0\4\12\2\0\11\12\2\0\2\12\2\0"+
    "\4\12\10\0\1\12\4\0\2\12\1\0\5\12\2\0\14\12\17\0"+
    "\3\12\1\0\6\12\4\0\2\12\2\0\26\12\1\0\7\12\1\0"+
    "\2\12\1\0\2\12\1\0\2\12\2\0\1\12\1\0\5\12\4\0"+
    "\2\12\2\0\3\12\3\0\1\12\7\0\4\12\1\0\1\12\7\0"+
    "\20\12\13\0\3\12\1\0\11\12\1\0\3\12\1\0\26\12\1\0"+
    "\7\12\1\0\2\12\1\0\5\12\2\0\12\12\1\0\3\12\1\0"+
    "\3\12\2\0\1\12\17\0\4\12\2\0\12\12\21\0\3\12\1\0"+
    "\10\12\2\0\2\12\2\0\26\12\1\0\7\12\1\0\2\12\1\0"+
    "\5\12\2\0\11\12\2\0\2\12\2\0\3\12\10\0\2\12\4\0"+
    "\2\12\1\0\5\12\2\0\12\12\1\0\1\12\20\0\2\12\1\0"+
    "\6\12\3\0\3\12\1\0\4\12\3\0\2\12\1\0\1\12\1\0"+
    "\2\12\3\0\2\12\3\0\3\12\3\0\14\12\4\0\5\12\3\0"+
    "\3\12\1\0\4\12\2\0\1\12\6\0\1\12\16\0\12\12\20\0"+
    "\4\12\1\0\10\12\1\0\3\12\1\0\27\12\1\0\20\12\3\0"+
    "\10\12\1\0\3\12\1\0\4\12\7\0\2\12\1\0\2\12\6\0"+
    "\4\12\2\0\12\12\21\0\3\12\1\0\10\12\1\0\3\12\1\0"+
    "\27\12\1\0\12\12\1\0\5\12\2\0\11\12\1\0\3\12\1\0"+
    "\4\12\7\0\2\12\7\0\1\12\1\0\4\12\2\0\12\12\1\0"+
    "\2\12\16\0\3\12\1\0\10\12\1\0\3\12\1\0\51\12\2\0"+
    "\10\12\1\0\3\12\1\0\5\12\10\0\1\12\10\0\4\12\2\0"+
    "\12\12\12\0\6\12\2\0\2\12\1\0\22\12\3\0\30\12\1\0"+
    "\11\12\1\0\1\12\2\0\7\12\3\0\1\12\4\0\6\12\1\0"+
    "\1\12\1\0\10\12\6\0\12\12\2\0\2\12\15\0\72\12\5\0"+
    "\17\12\1\0\12\12\47\0\2\12\1\0\1\12\2\0\2\12\1\0"+
    "\1\12\2\0\1\12\6\0\4\12\1\0\7\12\1\0\3\12\1\0"+
    "\1\12\1\0\1\12\2\0\2\12\1\0\15\12\1\0\3\12\2\0"+
    "\5\12\1\0\1\12\1\0\6\12\2\0\12\12\2\0\4\12\40\0"+
    "\1\12\27\0\2\12\6\0\12\12\13\0\1\12\1\0\1\12\1\0"+
    "\1\12\4\0\12\12\1\0\44\12\4\0\24\12\1\0\22\12\1\0"+
    "\44\12\11\0\1\12\71\0\112\12\6\0\116\12\2\0\46\12\1\0"+
    "\1\12\5\0\1\12\2\0\53\12\1\0\u014d\12\1\0\4\12\2\0"+
    "\7\12\1\0\1\12\1\0\4\12\2\0\51\12\1\0\4\12\2\0"+
    "\41\12\1\0\4\12\2\0\7\12\1\0\1\12\1\0\4\12\2\0"+
    "\17\12\1\0\71\12\1\0\4\12\2\0\103\12\2\0\3\12\40\0"+
    "\20\12\20\0\125\12\14\0\u026c\12\2\0\21\12\1\0\32\12\5\0"+
    "\113\12\3\0\13\12\7\0\15\12\1\0\7\12\13\0\25\12\13\0"+
    "\24\12\14\0\15\12\1\0\3\12\1\0\2\12\14\0\124\12\3\0"+
    "\1\12\4\0\2\12\2\0\12\12\41\0\3\12\2\0\12\12\6\0"+
    "\130\12\10\0\53\12\5\0\106\12\12\0\37\12\1\0\14\12\4\0"+
    "\14\12\12\0\50\12\2\0\5\12\13\0\54\12\4\0\32\12\6\0"+
    "\12\12\46\0\34\12\4\0\77\12\1\0\35\12\2\0\13\12\6\0"+
    "\12\12\15\0\1\12\10\0\17\12\101\0\114\12\4\0\12\12\21\0"+
    "\11\12\14\0\164\12\14\0\70\12\10\0\12\12\3\0\61\12\122\0"+
    "\3\12\1\0\43\12\1\0\2\12\6\0\366\12\6\0\u011a\12\2\0"+
    "\6\12\2\0\46\12\2\0\6\12\2\0\10\12\1\0\1\12\1\0"+
    "\1\12\1\0\1\12\1\0\37\12\2\0\65\12\1\0\7\12\1\0"+
    "\1\12\3\0\3\12\1\0\7\12\3\0\4\12\2\0\6\12\4\0"+
    "\15\12\5\0\3\12\1\0\7\12\53\0\1\10\1\10\25\0\2\12"+
    "\23\0\1\12\34\0\1\12\15\0\1\12\20\0\15\12\63\0\41\12"+
    "\21\0\1\12\4\0\1\12\2\0\12\12\1\0\1\12\3\0\5\12"+
    "\6\0\1\12\1\0\1\12\1\0\1\12\1\0\4\12\1\0\13\12"+
    "\2\0\4\12\5\0\5\12\4\0\1\12\21\0\51\12\u032d\0\64\12"+
    "\u0716\0\57\12\1\0\57\12\1\0\205\12\6\0\11\12\14\0\46\12"+
    "\1\0\1\12\5\0\1\12\2\0\70\12\7\0\1\12\17\0\30\12"+
    "\11\0\7\12\1\0\7\12\1\0\7\12\1\0\7\12\1\0\7\12"+
    "\1\0\7\12\1\0\7\12\1\0\7\12\1\0\40\12\57\0\1\12"+
    "\u01d5\0\3\12\31\0\17\12\1\0\5\12\2\0\5\12\4\0\126\12"+
    "\2\0\2\12\2\0\3\12\1\0\132\12\1\0\4\12\5\0\51\12"+
    "\3\0\136\12\21\0\33\12\65\0\20\12\u0200\0\u19b6\12\112\0\u51cd\12"+
    "\63\0\u048d\12\103\0\56\12\2\0\u010d\12\3\0\34\12\24\0\63\12"+
    "\1\0\12\12\1\0\37\12\1\0\123\12\45\0\11\12\2\0\147\12"+
    "\2\0\4\12\1\0\36\12\2\0\2\12\105\0\61\12\30\0\64\12"+
    "\14\0\105\12\13\0\12\12\6\0\30\12\3\0\1\12\4\0\56\12"+
    "\2\0\44\12\14\0\35\12\3\0\101\12\16\0\13\12\6\0\37\12"+
    "\1\0\67\12\11\0\16\12\2\0\12\12\6\0\27\12\3\0\111\12"+
    "\30\0\3\12\2\0\20\12\2\0\5\12\12\0\6\12\2\0\6\12"+
    "\2\0\6\12\11\0\7\12\1\0\7\12\1\0\53\12\1\0\4\12"+
    "\4\0\2\12\132\0\53\12\1\0\2\12\2\0\12\12\6\0\u2ba4\12"+
    "\14\0\27\12\4\0\61\12\u2104\0\u016e\12\2\0\152\12\46\0\7\12"+
    "\14\0\5\12\5\0\14\12\1\0\15\12\1\0\5\12\1\0\1\12"+
    "\1\0\2\12\1\0\2\12\1\0\154\12\41\0\u016b\12\22\0\100\12"+
    "\2\0\66\12\50\0\14\12\4\0\20\12\20\0\16\12\5\0\2\12"+
    "\30\0\3\12\40\0\5\12\1\0\207\12\23\0\12\12\7\0\32\12"+
    "\4\0\1\12\1\0\32\12\13\0\131\12\3\0\6\12\2\0\6\12"+
    "\2\0\6\12\2\0\3\12\43\0\14\12\1\0\32\12\1\0\23\12"+
    "\1\0\2\12\1\0\17\12\2\0\16\12\42\0\173\12\105\0\65\12"+
    "\210\0\1\12\202\0\35\12\3\0\61\12\17\0\1\12\37\0\40\12"+
    "\20\0\33\12\5\0\53\12\5\0\36\12\2\0\44\12\4\0\10\12"+
    "\1\0\5\12\52\0\236\12\2\0\12\12\126\0\50\12\10\0\64\12"+
    "\234\0\u0137\12\11\0\26\12\12\0\10\12\230\0\6\12\2\0\1\12"+
    "\1\0\54\12\1\0\2\12\3\0\1\12\2\0\27\12\12\0\27\12"+
    "\11\0\37\12\141\0\26\12\12\0\32\12\106\0\70\12\6\0\2\12"+
    "\100\0\4\12\1\0\2\12\5\0\10\12\1\0\3\12\1\0\33\12"+
    "\4\0\3\12\4\0\1\12\40\0\35\12\3\0\35\12\43\0\10\12"+
    "\1\0\36\12\31\0\66\12\12\0\26\12\12\0\23\12\15\0\22\12"+
    "\156\0\111\12\u03b7\0\107\12\37\0\12\12\17\0\74\12\25\0\31\12"+
    "\7\0\12\12\6\0\65\12\1\0\12\12\20\0\44\12\2\0\1\12"+
    "\11\0\105\12\13\0\13\12\45\0\22\12\1\0\45\12\170\0\73\12"+
    "\5\0\12\12\7\0\3\12\1\0\10\12\2\0\2\12\2\0\26\12"+
    "\1\0\7\12\1\0\2\12\1\0\5\12\2\0\11\12\2\0\2\12"+
    "\2\0\3\12\11\0\1\12\5\0\7\12\2\0\7\12\3\0\5\12"+
    "\u010b\0\106\12\1\0\1\12\10\0\12\12\246\0\66\12\2\0\11\12"+
    "\77\0\101\12\3\0\1\12\13\0\12\12\46\0\70\12\10\0\12\12"+
    "\u01d6\0\112\12\25\0\1\12\u01c0\0\71\12\u0507\0\u0399\12\147\0\157\12"+
    "\u0b91\0\u042f\12\u33d1\0\u0239\12\7\0\37\12\1\0\12\12\146\0\36\12"+
    "\2\0\5\12\13\0\67\12\11\0\4\12\14\0\12\12\11\0\25\12"+
    "\5\0\23\12\u0370\0\105\12\13\0\57\12\20\0\21\12\u4060\0\2\12"+
    "\u0bfe\0\153\12\5\0\15\12\3\0\11\12\7\0\12\12\3\0\2\12"+
    "\u14c6\0\5\12\3\0\6\12\10\0\10\12\2\0\7\12\36\0\4\12"+
    "\224\0\3\12\u01bb\0\125\12\1\0\107\12\1\0\2\12\2\0\1\12"+
    "\2\0\2\12\2\0\4\12\1\0\14\12\1\0\1\12\1\0\7\12"+
    "\1\0\101\12\1\0\4\12\2\0\10\12\1\0\7\12\1\0\34\12"+
    "\1\0\4\12\1\0\5\12\1\0\1\12\3\0\7\12\1\0\u0154\12"+
    "\2\0\31\12\1\0\31\12\1\0\37\12\1\0\31\12\1\0\37\12"+
    "\1\0\31\12\1\0\37\12\1\0\31\12\1\0\37\12\1\0\31\12"+
    "\1\0\10\12\2\0\62\12\u1000\0\305\12\13\0\7\12\u0529\0\4\12"+
    "\1\0\33\12\1\0\2\12\1\0\1\12\2\0\1\12\1\0\12\12"+
    "\1\0\4\12\1\0\1\12\1\0\1\12\6\0\1\12\4\0\1\12"+
    "\1\0\1\12\1\0\1\12\1\0\3\12\1\0\2\12\1\0\1\12"+
    "\2\0\1\12\1\0\1\12\1\0\1\12\1\0\1\12\1\0\1\12"+
    "\1\0\2\12\1\0\1\12\2\0\4\12\1\0\7\12\1\0\4\12"+
    "\1\0\4\12\1\0\1\12\1\0\12\12\1\0\21\12\5\0\3\12"+
    "\1\0\5\12\1\0\21\12\u0274\0\32\12\6\0\32\12\6\0\32\12"+
    "\u0e76\0\ua6d7\12\51\0\u1035\12\13\0\336\12\u3fe2\0\u021e\12\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u06ed\0"+
    "\360\12\uffff\0\uffff\0\ufe12\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\1\1\3\1\4\1\5\1\3"+
    "\1\0\1\6\1\0\1\7\4\0\1\10";

  private static int [] zzUnpackAction() {
    int [] result = new int[18];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\15\0\32\0\47\0\64\0\15\0\101\0\101"+
    "\0\116\0\47\0\15\0\64\0\15\0\133\0\150\0\165"+
    "\0\202\0\202";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[18];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\2\1\5\1\2\1\6\1\7"+
    "\1\10\1\11\1\2\2\3\16\0\1\3\11\0\2\3"+
    "\3\12\1\13\11\12\5\14\1\15\7\14\4\16\1\0"+
    "\14\16\1\0\2\16\1\17\5\16\12\0\2\20\1\0"+
    "\4\16\1\0\5\16\2\20\1\16\12\0\2\20\1\21"+
    "\7\22\3\0\3\22";

  private static int [] zzUnpackTrans() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\1\11\3\1\1\0\1\11\1\0"+
    "\1\11\4\0\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[18];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
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



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  LexicalSongParser(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2588) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return YYEOF;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { /*Do nothing*/
            }
          case 9: break;
          case 2: 
            { songElements.add(new WordElement(yyline, yycolumn, yytext()));
            }
          case 10: break;
          case 3: 
            { songElements.add(new SongElement(SongElementType.WHITESPACE, yyline, yycolumn, " "));
            }
          case 11: break;
          case 4: 
            { songElements.add(new SongElement(SongElementType.LINEBREAK, yyline, yycolumn, "\n"));
            }
          case 12: break;
          case 5: 
            { System.out.print(yytext());
            }
          case 13: break;
          case 6: 
            { songElements.add(new ChordElement(yyline, yycolumn, yytext().replace("[", "").replace("]", "")));
            }
          case 14: break;
          case 7: 
            { songElements.add(new ChordproElement(yyline, yycolumn, yytext().replace("{", "").replace("}", "")));
            }
          case 15: break;
          case 8: 
            { songElements.add(new SongElement(SongElementType.ONSONG, yyline, yycolumn, yytext()));
            }
          case 16: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java LexicalSongParser [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid? 
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        LexicalSongParser scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new LexicalSongParser(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}