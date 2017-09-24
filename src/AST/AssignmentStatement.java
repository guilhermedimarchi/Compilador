package AST;

public class AssignmentStatement extends Statement {
	Expr e, d;
	String op;
	
	public AssignmentStatement(Expr e, String op, Expr d)
	{
		this.d = d;
		this.op = op;
		this.e = e;
	}

	@Override
	public String toString() {
		return "AssignmentStatement [Expr esquerda=" + e +  ", op=" + op + ", Expr direita=" + d + "]";
	}
}
