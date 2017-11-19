package AST;

import Lexer.Gramatica;

public class CharExpr extends Expr{
	@Override
	public String toString() {
		return "CharExpr [valor=" + this.getValor() + "]";
	}

	public CharExpr(String valor)
	{
		this.setType(Gramatica.CHAR);
		this.setValor(valor); 
	}

	@Override
	public void genC(StringBuilder sb) {
		sb.append(this.getValor());		
	}

}
