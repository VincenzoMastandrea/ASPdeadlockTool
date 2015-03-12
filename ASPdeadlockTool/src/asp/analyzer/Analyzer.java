package asp.analyzer;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import asp.models.Program;
import asp.parser.ASPgrammarLexer;
import asp.parser.ASPgrammarParser;
import asp.parser.ASPgrammarParser.ProgramContext;


public class Analyzer {
	
	public static void main(String[] args) throws Exception {
		InputStream is = System.in;
		is = new FileInputStream("src/input.txt");
		ANTLRInputStream input = new ANTLRInputStream(is);
		ASPgrammarLexer lexer = new ASPgrammarLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ASPgrammarParser parser = new ASPgrammarParser(tokens);
		//parser.setBuildParseTree(false);
		FileOutputStream f = new FileOutputStream("src/log.txt"); 
	    System.setOut(new PrintStream(f));
	    Program program = parser.program().prog;
		
		
		
	}

}