package deadlock.analyser.factory;

import java.util.Set;

import com.gzoumix.semisolver.term.Term;
import com.gzoumix.semisolver.term.TermVariable;
import com.gzoumix.semisolver.term.Variable;

public class ObjKindVar extends TermVariable implements ObjKind{

	public ObjKindVar(Variable v) { super(v); }
	
}
