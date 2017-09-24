package AST;

public class NumberExpr extends Expr{
	@Override
	public String toString() {
		return "NumberExpr [valor=" + valor + "]";
	}

	private Integer valor;
	
	public NumberExpr(Integer valor)
	{
		this.valor = valor;
	}

}
