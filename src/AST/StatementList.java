package AST;

import java.util.Vector;

public class StatementList extends Statement {
	
	public StatementList(Vector v) {
		this.v = v; 
	}
	
	public void genC( StringBuilder sb ) {
		
	}
	
	private Vector v;

	@Override
	public String toString() {
		return "StatementList [v=" + v + "]";
	}


}
