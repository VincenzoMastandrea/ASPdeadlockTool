package asp.analyzer;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import deadlock.analyser.Tester;
import asp.parser.ASPLexer;
import asp.parser.ASPParser;
import asp.translator.Translator;



public class Analyzer {
	
	public static final String tradPath = "src/trad.abs";
	public static final String logPath = "src/log.txt";
	
	public static void main(String[] args) throws Exception {
		PrintStream stdout = System.out;
		InputStream is = System.in;
		is = new FileInputStream(args[0]);
		
		ANTLRInputStream input = new ANTLRInputStream(is);
		ASPLexer lexer = new ASPLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ASPParser parser = new ASPParser(tokens);
		//parser.setBuildParseTree(false);
		FileOutputStream trad = new FileOutputStream(tradPath); 
	    System.setOut(new PrintStream(trad));
	    
	    //PARSING
		ParseTree tree = parser.program();
		   
		//TRAD
		System.setOut(new PrintStream(trad));
		Translator.translate(parser,tree);
		
		//FileOutputStream log = new FileOutputStream(logPath); 
	    //System.setOut(new PrintStream(log));
		System.setOut(stdout);
		Tester.main(tradPath,"-v");
	}
}
