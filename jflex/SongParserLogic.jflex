import java.util.*;
%%
%class LexicalSongParser
%standalone
%line
%column

WORD=[a-zA-Z]+
NUMBER=[0-9]+
CHORD=\[[^\]]*\]
CHORDPRO=\{[^\}]*\}
WHITE_SPACE_CHAR=[\ \t\r]
NEWLINE_CHAR=[\n]

%%
<YYINITIAL> {WORD} { System.out.printf("*** found word-match [%s] at line %d, column %d\n", yytext(), yyline, yycolumn); }
{CHORD} { System.out.printf("*** found chord-match [%s] at line %d, column %d\n", yytext(), yyline, yycolumn); }
{CHORDPRO} { System.out.printf("*** found chordpro-syntax [%s] at line %d, column %d\n", yytext(), yyline, yycolumn); }
{WHITE_SPACE_CHAR} { System.out.println("Whitespace"); }
{NEWLINE_CHAR} { System.out.println("Linebreak"); }
.  { /*Do nothing*/ }
