package AST;

import java.util.Vector;

public class Program {
	Vector v;
	Statement s;
	
	public Program(Vector v, Statement s)
	{
		this.v = v;
		this.s = s;
	}

	@Override
	public String toString() {
		return "Program [Variaveis=" + v + ", statements=" + s + "]";
	}
	
	
}
