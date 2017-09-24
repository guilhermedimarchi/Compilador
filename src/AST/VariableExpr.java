package AST;

public class VariableExpr extends Expr {
	private String name;
	
	public VariableExpr(String name)
	{
		this.name = name;
	}
}
