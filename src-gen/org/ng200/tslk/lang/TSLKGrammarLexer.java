/*******************************************************************************
 * Copyright (c) 2013 Nick Guletskii.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nick Guletskii - initial API and implementation
 ******************************************************************************/
package org.ng200.tslk.lang;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class TSLKGrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int NUMBER = 1, FUNC = 2, FOR = 3, WHILE = 4, IF = 5,
			ELSE = 6, THEN = 7, DO = 8, END = 9, OR = 10, AND = 11, LOCAL = 12,
			TABLE = 13, RETURN = 14, BREAK = 15, CONTINUE = 16, TRUE = 17,
			FALSE = 18, PLUSEQ = 19, MINUSEQ = 20, MULEQ = 21, DIVEQ = 22,
			REMEQ = 23, POWEQ = 24, LESSEQ = 25, LESS = 26, MOREEQ = 27,
			MORE = 28, EQ = 29, NOTEQ = 30, PLUS = 31, MINUS = 32, MUL = 33,
			DIV = 34, REM = 35, NOT = 36, LEN = 37, POW = 38, DOT = 39,
			COMMA = 40, SEMI = 41, COLON = 42, LPAREN = 43, RPAREN = 44,
			LBRACKET = 45, RBRACKET = 46, LBRACE = 47, RBRACE = 48,
			STRING = 49, SLCOMMENT = 50, MLCOMMENT = 51, WS = 52,
			GENERAL_ID = 53;
	public static String[] modeNames = { "DEFAULT_MODE" };

	public static final String[] tokenNames = { "<INVALID>", "NUMBER",
			"'function'", "'for'", "'while'", "'if'", "'else'", "'then'",
			"'do'", "'end'", "'or'", "'and'", "'local'", "'table'", "'return'",
			"'break'", "'continue'", "'true'", "'false'", "'+='", "'-='",
			"'*='", "'/='", "'%='", "'^='", "'<='", "'<'", "'>='", "'>'",
			"'='", "'!='", "'+'", "'-'", "'*'", "'/'", "'%'", "'not'", "'#'",
			"'^'", "'.'", "','", "';'", "':'", "'('", "')'", "'['", "']'",
			"'{'", "'}'", "STRING", "SLCOMMENT", "MLCOMMENT", "WS",
			"GENERAL_ID" };
	public static final String[] ruleNames = { "NUMBER", "FUNC", "FOR",
			"WHILE", "IF", "ELSE", "THEN", "DO", "END", "OR", "AND", "LOCAL",
			"TABLE", "RETURN", "BREAK", "CONTINUE", "TRUE", "FALSE", "PLUSEQ",
			"MINUSEQ", "MULEQ", "DIVEQ", "REMEQ", "POWEQ", "LESSEQ", "LESS",
			"MOREEQ", "MORE", "EQ", "NOTEQ", "PLUS", "MINUS", "MUL", "DIV",
			"REM", "NOT", "LEN", "POW", "DOT", "COMMA", "SEMI", "COLON",
			"LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE",
			"STRING", "ESC", "DIGIT", "HEXDIGIT", "XID_Start", "XID_Continue",
			"SLCOMMENT", "MLCOMMENT", "WS", "GENERAL_ID" };

	public static final String _serializedATN = "\2\4\67\u0175\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"
			+ "\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"
			+ "\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"
			+ "\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"
			+ "\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"
			+ "(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62"
			+ "\t\62\4\63\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4"
			+ ":\t:\4;\t;\3\2\6\2y\n\2\r\2\16\2z\3\2\3\2\6\2\177\n\2\r\2\16\2\u0080\3"
			+ "\2\6\2\u0084\n\2\r\2\16\2\u0085\5\2\u0088\n\2\3\3\3\3\3\3\3\3\3\3\3\3"
			+ "\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3"
			+ "\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3"
			+ "\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"
			+ "\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"
			+ "\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"
			+ "\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3"
			+ "\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3"
			+ "\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3"
			+ "!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3"
			+ "+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\62\7\62\u012f"
			+ "\n\62\f\62\16\62\u0132\13\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3"
			+ "\63\3\63\5\63\u013e\n\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\5\67"
			+ "\u0148\n\67\38\38\38\38\78\u014e\n8\f8\168\u0151\138\38\58\u0154\n8\3"
			+ "8\38\38\38\39\39\39\39\79\u015e\n9\f9\169\u0161\139\39\39\39\39\39\3:"
			+ "\6:\u0169\n:\r:\16:\u016a\3:\3:\3;\3;\7;\u0171\n;\f;\16;\u0174\13;\4\u014f"
			+ "\u015f<\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25"
			+ "\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)"
			+ "\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= "
			+ "\1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1"
			+ "_\61\1a\62\1c\63\1e\2\1g\2\1i\2\1k\2\1m\2\1o\64\2q\65\3s\66\4u\67\1\3"
			+ "\2\7\4$$^^\4ppvv\5\62;CHch\5C\\aac|\5\13\f\17\17\"\"\u017c\2\3\3\2\2\2"
			+ "\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"
			+ "\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"
			+ "\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"
			+ "\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"
			+ "\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"
			+ "\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"
			+ "\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"
			+ "\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2"
			+ "\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\3\u0087\3\2\2\2\5\u0089"
			+ "\3\2\2\2\7\u0092\3\2\2\2\t\u0096\3\2\2\2\13\u009c\3\2\2\2\r\u009f\3\2"
			+ "\2\2\17\u00a4\3\2\2\2\21\u00a9\3\2\2\2\23\u00ac\3\2\2\2\25\u00b0\3\2\2"
			+ "\2\27\u00b3\3\2\2\2\31\u00b7\3\2\2\2\33\u00bd\3\2\2\2\35\u00c3\3\2\2\2"
			+ "\37\u00ca\3\2\2\2!\u00d0\3\2\2\2#\u00d9\3\2\2\2%\u00de\3\2\2\2\'\u00e4"
			+ "\3\2\2\2)\u00e7\3\2\2\2+\u00ea\3\2\2\2-\u00ed\3\2\2\2/\u00f0\3\2\2\2\61"
			+ "\u00f3\3\2\2\2\63\u00f6\3\2\2\2\65\u00f9\3\2\2\2\67\u00fb\3\2\2\29\u00fe"
			+ "\3\2\2\2;\u0100\3\2\2\2=\u0102\3\2\2\2?\u0105\3\2\2\2A\u0107\3\2\2\2C"
			+ "\u0109\3\2\2\2E\u010b\3\2\2\2G\u010d\3\2\2\2I\u010f\3\2\2\2K\u0113\3\2"
			+ "\2\2M\u0115\3\2\2\2O\u0117\3\2\2\2Q\u0119\3\2\2\2S\u011b\3\2\2\2U\u011d"
			+ "\3\2\2\2W\u011f\3\2\2\2Y\u0121\3\2\2\2[\u0123\3\2\2\2]\u0125\3\2\2\2_"
			+ "\u0127\3\2\2\2a\u0129\3\2\2\2c\u012b\3\2\2\2e\u0135\3\2\2\2g\u013f\3\2"
			+ "\2\2i\u0141\3\2\2\2k\u0143\3\2\2\2m\u0147\3\2\2\2o\u0149\3\2\2\2q\u0159"
			+ "\3\2\2\2s\u0168\3\2\2\2u\u016e\3\2\2\2wy\5g\64\2xw\3\2\2\2yz\3\2\2\2z"
			+ "x\3\2\2\2z{\3\2\2\2{|\3\2\2\2|~\7\60\2\2}\177\5g\64\2~}\3\2\2\2\177\u0080"
			+ "\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0088\3\2\2\2\u0082"
			+ "\u0084\5g\64\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2"
			+ "\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087x\3\2\2\2\u0087\u0083"
			+ "\3\2\2\2\u0088\4\3\2\2\2\u0089\u008a\7h\2\2\u008a\u008b\7w\2\2\u008b\u008c"
			+ "\7p\2\2\u008c\u008d\7e\2\2\u008d\u008e\7v\2\2\u008e\u008f\7k\2\2\u008f"
			+ "\u0090\7q\2\2\u0090\u0091\7p\2\2\u0091\6\3\2\2\2\u0092\u0093\7h\2\2\u0093"
			+ "\u0094\7q\2\2\u0094\u0095\7t\2\2\u0095\b\3\2\2\2\u0096\u0097\7y\2\2\u0097"
			+ "\u0098\7j\2\2\u0098\u0099\7k\2\2\u0099\u009a\7n\2\2\u009a\u009b\7g\2\2"
			+ "\u009b\n\3\2\2\2\u009c\u009d\7k\2\2\u009d\u009e\7h\2\2\u009e\f\3\2\2\2"
			+ "\u009f\u00a0\7g\2\2\u00a0\u00a1\7n\2\2\u00a1\u00a2\7u\2\2\u00a2\u00a3"
			+ "\7g\2\2\u00a3\16\3\2\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7j\2\2\u00a6\u00a7"
			+ "\7g\2\2\u00a7\u00a8\7p\2\2\u00a8\20\3\2\2\2\u00a9\u00aa\7f\2\2\u00aa\u00ab"
			+ "\7q\2\2\u00ab\22\3\2\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af"
			+ "\7f\2\2\u00af\24\3\2\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2\7t\2\2\u00b2\26"
			+ "\3\2\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7f\2\2\u00b6"
			+ "\30\3\2\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7q\2\2\u00b9\u00ba\7e\2\2\u00ba"
			+ "\u00bb\7c\2\2\u00bb\u00bc\7n\2\2\u00bc\32\3\2\2\2\u00bd\u00be\7v\2\2\u00be"
			+ "\u00bf\7c\2\2\u00bf\u00c0\7d\2\2\u00c0\u00c1\7n\2\2\u00c1\u00c2\7g\2\2"
			+ "\u00c2\34\3\2\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7"
			+ "v\2\2\u00c6\u00c7\7w\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7p\2\2\u00c9\36"
			+ "\3\2\2\2\u00ca\u00cb\7d\2\2\u00cb\u00cc\7t\2\2\u00cc\u00cd\7g\2\2\u00cd"
			+ "\u00ce\7c\2\2\u00ce\u00cf\7m\2\2\u00cf \3\2\2\2\u00d0\u00d1\7e\2\2\u00d1"
			+ "\u00d2\7q\2\2\u00d2\u00d3\7p\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7k\2\2"
			+ "\u00d5\u00d6\7p\2\2\u00d6\u00d7\7w\2\2\u00d7\u00d8\7g\2\2\u00d8\"\3\2"
			+ "\2\2\u00d9\u00da\7v\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7w\2\2\u00dc\u00dd"
			+ "\7g\2\2\u00dd$\3\2\2\2\u00de\u00df\7h\2\2\u00df\u00e0\7c\2\2\u00e0\u00e1"
			+ "\7n\2\2\u00e1\u00e2\7u\2\2\u00e2\u00e3\7g\2\2\u00e3&\3\2\2\2\u00e4\u00e5"
			+ "\7-\2\2\u00e5\u00e6\7?\2\2\u00e6(\3\2\2\2\u00e7\u00e8\7/\2\2\u00e8\u00e9"
			+ "\7?\2\2\u00e9*\3\2\2\2\u00ea\u00eb\7,\2\2\u00eb\u00ec\7?\2\2\u00ec,\3"
			+ "\2\2\2\u00ed\u00ee\7\61\2\2\u00ee\u00ef\7?\2\2\u00ef.\3\2\2\2\u00f0\u00f1"
			+ "\7\'\2\2\u00f1\u00f2\7?\2\2\u00f2\60\3\2\2\2\u00f3\u00f4\7`\2\2\u00f4"
			+ "\u00f5\7?\2\2\u00f5\62\3\2\2\2\u00f6\u00f7\7>\2\2\u00f7\u00f8\7?\2\2\u00f8"
			+ "\64\3\2\2\2\u00f9\u00fa\7>\2\2\u00fa\66\3\2\2\2\u00fb\u00fc\7@\2\2\u00fc"
			+ "\u00fd\7?\2\2\u00fd8\3\2\2\2\u00fe\u00ff\7@\2\2\u00ff:\3\2\2\2\u0100\u0101"
			+ "\7?\2\2\u0101<\3\2\2\2\u0102\u0103\7#\2\2\u0103\u0104\7?\2\2\u0104>\3"
			+ "\2\2\2\u0105\u0106\7-\2\2\u0106@\3\2\2\2\u0107\u0108\7/\2\2\u0108B\3\2"
			+ "\2\2\u0109\u010a\7,\2\2\u010aD\3\2\2\2\u010b\u010c\7\61\2\2\u010cF\3\2"
			+ "\2\2\u010d\u010e\7\'\2\2\u010eH\3\2\2\2\u010f\u0110\7p\2\2\u0110\u0111"
			+ "\7q\2\2\u0111\u0112\7v\2\2\u0112J\3\2\2\2\u0113\u0114\7%\2\2\u0114L\3"
			+ "\2\2\2\u0115\u0116\7`\2\2\u0116N\3\2\2\2\u0117\u0118\7\60\2\2\u0118P\3"
			+ "\2\2\2\u0119\u011a\7.\2\2\u011aR\3\2\2\2\u011b\u011c\7=\2\2\u011cT\3\2"
			+ "\2\2\u011d\u011e\7<\2\2\u011eV\3\2\2\2\u011f\u0120\7*\2\2\u0120X\3\2\2"
			+ "\2\u0121\u0122\7+\2\2\u0122Z\3\2\2\2\u0123\u0124\7]\2\2\u0124\\\3\2\2"
			+ "\2\u0125\u0126\7_\2\2\u0126^\3\2\2\2\u0127\u0128\7}\2\2\u0128`\3\2\2\2"
			+ "\u0129\u012a\7\177\2\2\u012ab\3\2\2\2\u012b\u0130\7$\2\2\u012c\u012f\5"
			+ "e\63\2\u012d\u012f\n\2\2\2\u012e\u012c\3\2\2\2\u012e\u012d\3\2\2\2\u012f"
			+ "\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0133\3\2"
			+ "\2\2\u0132\u0130\3\2\2\2\u0133\u0134\7$\2\2\u0134d\3\2\2\2\u0135\u013d"
			+ "\7^\2\2\u0136\u013e\t\3\2\2\u0137\u0138\7w\2\2\u0138\u0139\5i\65\2\u0139"
			+ "\u013a\5i\65\2\u013a\u013b\5i\65\2\u013b\u013c\5i\65\2\u013c\u013e\3\2"
			+ "\2\2\u013d\u0136\3\2\2\2\u013d\u0137\3\2\2\2\u013ef\3\2\2\2\u013f\u0140"
			+ "\4\62;\2\u0140h\3\2\2\2\u0141\u0142\t\4\2\2\u0142j\3\2\2\2\u0143\u0144"
			+ "\t\5\2\2\u0144l\3\2\2\2\u0145\u0148\5k\66\2\u0146\u0148\4\63;\2\u0147"
			+ "\u0145\3\2\2\2\u0147\u0146\3\2\2\2\u0148n\3\2\2\2\u0149\u014a\7\61\2\2"
			+ "\u014a\u014b\7\61\2\2\u014b\u014f\3\2\2\2\u014c\u014e\13\2\2\2\u014d\u014c"
			+ "\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150"
			+ "\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0152\u0154\7\17\2\2\u0153\u0152\3"
			+ "\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0156\7\f\2\2\u0156"
			+ "\u0157\3\2\2\2\u0157\u0158\b8\2\2\u0158p\3\2\2\2\u0159\u015a\7\61\2\2"
			+ "\u015a\u015b\7,\2\2\u015b\u015f\3\2\2\2\u015c\u015e\13\2\2\2\u015d\u015c"
			+ "\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u0160\3\2\2\2\u015f\u015d\3\2\2\2\u0160"
			+ "\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162\u0163\7,\2\2\u0163\u0164\7\61"
			+ "\2\2\u0164\u0165\3\2\2\2\u0165\u0166\b9\3\2\u0166r\3\2\2\2\u0167\u0169"
			+ "\t\6\2\2\u0168\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u0168\3\2\2\2\u016a"
			+ "\u016b\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d\b:\4\2\u016dt\3\2\2\2\u016e"
			+ "\u0172\5k\66\2\u016f\u0171\5m\67\2\u0170\u016f\3\2\2\2\u0171\u0174\3\2"
			+ "\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173v\3\2\2\2\u0174\u0172"
			+ "\3\2\2\2\20\2z\u0080\u0085\u0087\u012e\u0130\u013d\u0147\u014f\u0153\u015f"
			+ "\u016a\u0172";

	public static final ATN _ATN = ATNSimulator.deserialize(_serializedATN
			.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}

	public TSLKGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA,
				_sharedContextCache);
	}

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 54:
			SLCOMMENT_action((RuleContext) _localctx, actionIndex);
			break;

		case 55:
			MLCOMMENT_action((RuleContext) _localctx, actionIndex);
			break;

		case 56:
			WS_action((RuleContext) _localctx, actionIndex);
			break;
		}
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	@Override
	public String getGrammarFileName() {
		return "TSLKGrammar.g4";
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String[] getTokenNames() {
		return tokenNames;
	}

	private void MLCOMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			skip();
			break;
		}
	}

	private void SLCOMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			skip();
			break;
		}
	}

	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			skip();
			break;
		}
	}
}
