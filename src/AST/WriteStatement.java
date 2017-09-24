package AST;

public class WriteStatement extends Statement {
	@Override
	public String toString() {
		return "WriteStatement [e=" + e + "]";
	}

	Expr e;
	
	public WriteStatement(Expr e)
	{
		this.e = e;
	}
}
