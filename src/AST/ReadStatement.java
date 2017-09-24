package AST;

public class ReadStatement extends Statement {
	Expr e;
	
	public ReadStatement(Expr e)
	{
		this.e = e;
	}

}
