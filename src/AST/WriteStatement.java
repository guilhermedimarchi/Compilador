package AST;

public class WriteStatement extends Statement {
	Expr e;
	
	public WriteStatement(Expr e)
	{
		this.e = e;
	}
}
