package AST;

import Lexer.Gramatica;

public class FalseExpr extends Expr{
	
	public FalseExpr(String valor)
	{
		this.setType(Gramatica.BOOLEAN);
		if(valor == Gramatica.FALSE)
			this.setValor(false); 
		else
			valor = null;
	}

	@Override
	public String toString() {
		return "FalseExpr [valor=" + this.getValor() + "]";
	}
	
	@Override
	public void genC(StringBuilder sb) {
		sb.append(this.getValor());		
	}
}
