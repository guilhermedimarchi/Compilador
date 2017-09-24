package AST;

public class CompositeExpr extends Expr{
	private Expr e, d;
	private String op;
	
	public CompositeExpr(Expr e, String op, Expr d)
	{
		this.e = e;
		this.op = op;
		this.d = d;
	}

	@Override
	public String toString() {
		return "CompositeExpr [Expr esquerda=" + e +  ", op=" + op + ", Expr direita=" + d + "]";
	}

}
