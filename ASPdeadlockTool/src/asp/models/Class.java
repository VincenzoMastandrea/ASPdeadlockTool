package asp.models;

import java.util.HashMap;
import java.util.LinkedList;

public class Class extends ComputationUnit {
	
	LinkedList<TypeBase> fields;
	LinkedList<TypeBase> parameters; 
	HashMap<String, Method> methods;
	
	public Method getMethodFromID (String methodName)
	{
		return methods.get(methodName);
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
	
	public Class(LinkedList<TypeBase> fields, LinkedList<TypeBase> parameters, HashMap<String, Method> methods)
	{
		this.fields = fields;
		this.parameters = parameters;
		this.methods = methods;
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
