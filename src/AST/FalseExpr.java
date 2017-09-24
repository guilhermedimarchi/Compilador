package AST;

import Lexer.Gramatica;

public class FalseExpr extends Expr{
	private boolean valor;
	
	public FalseExpr(String valor)
	{
		if(valor == Gramatica.FALSE)
			this.valor = false;
		else
			valor = null;
	}

	@Override
	public String toString() {
		return "FalseExpr [valor=" + valor + "]";
	}
}
