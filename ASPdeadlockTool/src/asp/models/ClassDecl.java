package asp.models;

import java.util.HashMap;
import java.util.LinkedList;

public class ClassDecl extends ComputationUnit {
	
	String className;
	HashMap<String,Declaration> fields;
	HashMap<Integer,Declaration> parameters; 
	HashMap<String, LinkedList<Method>> methods;
	
	
	public ClassDecl(String className, 
				 HashMap<Integer,Declaration> parameters, 
				 HashMap<String,Declaration> fields, 
			     HashMap<String, LinkedList<Method>> methods)
	{
		this.className = className;
		this.parameters = parameters;
		this.fields = fields;
		this.methods = methods;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public LinkedList<Method> getMethodFromID (String methodName)
	{
		if (methods.containsKey(methodName))
			return methods.get(methodName);
		else
			return null;
	}
	
	public HashMap<String, LinkedList<Method>> getMethods()
	{
		return methods;
	}
	
	public HashMap<String,Declaration> getFields()
	{
		return fields;
	}
	
	public HashMap<Integer,Declaration> getParameters()
	{
		return parameters;
	}
		
	public void setFields (HashMap<String,Declaration> fields )
	{
		this.fields = fields;
	}
	
	public void setParameters (HashMap<Integer,Declaration> parameters )
	{
		this.parameters = parameters;
	}
	
	public void setMethods (HashMap<String, LinkedList<Method>> methods )
	{
		this.methods = methods;
	}
	
}
