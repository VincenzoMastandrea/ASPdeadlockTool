package deadlock.analyser.factory;

import java.util.List;

import com.gzoumix.semisolver.term.Term;

public class ObjKindAct extends GenericStructuredTerm implements ObjKind {

	public static String name = "ObjKindAct";
	
	public ObjKindAct() {
		super(name, null);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		String kind = "a.";
		return kind;
	}

}