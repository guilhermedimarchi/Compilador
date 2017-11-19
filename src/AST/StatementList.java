package AST;

import java.util.Enumeration;
import java.util.Vector;

public class StatementList extends Statement {
	
	public StatementList(Vector v) {
		this.v = v; 
	}
	
	public void genC( StringBuilder sb ) {
		Enumeration e = v.elements(); 
		while ( e.hasMoreElements() ) 
			((Statement ) e.nextElement()).genC(sb);

	}
	
	private Vector v;

	@Override
	public String toString() {
		return "StatementList [v=" + v + "]";
	}


}
