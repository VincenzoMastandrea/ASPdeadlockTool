package asp.analyzer;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import asp.models.Program;
import asp.parser.ASPLexer;
import asp.parser.ASPParser;


public class Analyzer {
	
	public static void main(String[] args) throws Exception {
		InputStream is = System.in;
		is = new FileInputStream("src/input.txt");
		ANTLRInputStream input = new ANTLRInputStream(is);
		ASPLexer lexer = new ASPLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ASPParser parser = new ASPParser(tokens);
		//parser.setBuildParseTree(false);
		FileOutputStream f = new FileOutputStream("src/log.txt"); 
	    System.setOut(new PrintStream(f));
	    Program program = parser.program().prog;
	    program.print();
	}
}
