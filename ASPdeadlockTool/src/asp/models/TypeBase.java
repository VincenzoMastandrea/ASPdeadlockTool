package asp.models;

/**
 * @author Vincenzo Mastandrea
 * Represents the types used in a program. 
 */

public abstract class TypeBase{
	
	String id;
	String type;
	
	public TypeBase(String id, String type)
	{
		this.id = id;
		this.type = type;
	}
	
	public TypeBase(String type)
	{
		this.id = id;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
