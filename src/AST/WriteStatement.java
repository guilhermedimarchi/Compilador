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

	@Override
	public void genC(StringBuilder sb) {
		sb.append("printf(\"%d\", ");
		e.genC(sb);
		sb.append(");\n");		
	}
}
