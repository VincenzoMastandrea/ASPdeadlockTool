package asp.models;

import java.util.LinkedList;

public class NewAcrive extends NewObject{

	private final static boolean ACTIVE = true;
	
	public NewAcrive(String className, LinkedList<Expression> parameters) {
		super(ACTIVE, className, parameters);
		// TODO Auto-generated constructor stub
	}

}
