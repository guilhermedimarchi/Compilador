package AST;

import Lexer.Gramatica;

public class TrueExpr  extends Expr{
	

	private boolean valor;
	
	public TrueExpr(String valor)
	{
		if(valor == Gramatica.TRUE)
			this.valor = true;
		else
			valor = null;
	}
	@Override
	public String toString() {
		return "TrueExpr [valor=" + valor + "]";
	}

}
