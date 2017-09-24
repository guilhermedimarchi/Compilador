package AST;

public class VariableExpr extends Expr {
	private String name;
	private Integer valor;
	
	public VariableExpr(String name)
	{
		this.name = name;
	}
	public VariableExpr(String name, Integer valor)
	{
		this.name = name;
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "VariableExpr [name=" + name + ", valor=" + valor + "]";
	}
	
}
