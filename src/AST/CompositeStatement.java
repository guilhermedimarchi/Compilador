package AST;

public class CompositeStatement extends Statement{
	private Statement s;
	private Statement sl;
	
	public CompositeStatement(Statement s, Statement sl)
	{
		this.s = s;
		this.sl = sl;
	}
	
}
