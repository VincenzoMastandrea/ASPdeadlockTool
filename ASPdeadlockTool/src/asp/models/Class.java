package asp.models;

import java.util.HashMap;
import java.util.LinkedList;

public class Class extends ComputationUnit {
	
	String className;
	LinkedList<TypeBase> fields;
	LinkedList<TypeBase> parameters; 
	HashMap<String, Method> methods;
	
	
	public Class(String className, 
			     LinkedList<TypeBase> fields, 
			     LinkedList<TypeBase> parameters, 
			     HashMap<String, Method> methods)
	{
		this.className = className;
		this.fields = fields;
		this.parameters = parameters;
		this.methods = methods;
	}

	
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public Method getMethodFromID (String methodName)
	{
		if (methods.containsKey(methodName))
			return methods.get(methodName);
		else
			return null;
	}
	
	public HashMap<String, Method> getMethods()
	{
		return methods;
	}
	
	public LinkedList<TypeBase> getFields()
	{
		return fields;
	}
	
	public LinkedList<TypeBase> getParameters()
	{
		return parameters;
	}
		
	public void setFields (LinkedList<TypeBase> fields )
	{
		this.fields = fields;
	}
	
	public void setParameters (LinkedList<TypeBase> parameters )
	{
		this.parameters = parameters;
	}
	
	public void setMethods (HashMap<String, Method> methods )
	{
		this.methods = methods;
	}
	
}
