package AST;

public class VariableExpr extends Expr {

	public VariableExpr(String name)
	{
		this.setName(name);
	}
	public VariableExpr(String name, Integer valor)
	{
		this.setName(name);
		this.setValor(valor);
	}

	@Override
	public String toString() {
		return "VariableExpr [name=" + this.getName() + ", valor=" + this.getValor() + ", type=" + this.getType() + "]";
	}
	
}
