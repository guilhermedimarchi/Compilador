package AST;

import Lexer.Gramatica;

public class ReadStatement extends Statement {
	Expr e;
	
	public ReadStatement(Expr e)
	{
		this.e = e;
	}

	@Override
	public String toString() {
		return "ReadStatement [e=" + e + "]";
	}
	
	@Override
	public void genC( StringBuilder sb)
	{
		
		if(((VariableExpr)e).getType()==Gramatica.CHAR)
			sb.append("scanf(\"%s\", &" );
		else
			sb.append("scanf(\"%i\", &" );
		
		sb.append(( (VariableExpr) e).getName() + ");\n " );
	
	}

}
