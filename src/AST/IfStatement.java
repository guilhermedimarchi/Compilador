package AST;

public class IfStatement extends Statement{
	Expr e;
	Statement s1, s2;
	
	public IfStatement(Expr e, Statement s1, Statement s2)
	{
		this.e = e;
		this.s1 = s1;
		this.s2 = s2;
	}

}
