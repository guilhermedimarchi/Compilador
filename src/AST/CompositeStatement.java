package AST;

public class CompositeStatement extends Statement{

	private StatementList sl;
	
	public CompositeStatement(StatementList sl)
	{
		this.sl = sl;
	}

	@Override
	public String toString() {
		return "CompositeStatement [statementList=" + sl + "]";
	}
	
}
