package AST;

import Lexer.Gramatica;

public class TrueExpr  extends Expr{
	

	private boolean valor;
	
	public TrueExpr(String valor)
	{
		this.setType(Gramatica.BOOLEAN);
		if(valor == Gramatica.TRUE)
			this.setValor(true); 
		else
			valor = null;
	}
	@Override
	public String toString() {
		return "TrueExpr [valor=" + this.getValor() + "]";
	}
	
	@Override
	public void genC(StringBuilder sb) {
		sb.append(this.getValor());		
	}

}
