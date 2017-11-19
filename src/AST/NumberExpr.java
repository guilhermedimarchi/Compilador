package AST;

import Lexer.Gramatica;

public class NumberExpr extends Expr{
	@Override
	public String toString() {
		return "NumberExpr [valor=" + this.getValor() + "]";
	}

	public NumberExpr(Integer valor)
	{
		this.setType(Gramatica.INTEGER);
		this.setValor(valor);
	}

}
