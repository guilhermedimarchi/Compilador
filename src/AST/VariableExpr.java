package AST;

public class VariableExpr extends Expr {
	private String name;
	private Object valor;
	private String type;
	
	public VariableExpr(String name)
	{
		this.name = name;
	}
	public VariableExpr(String name, Integer valor)
	{
		this.name = name;
		this.valor = valor;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "VariableExpr [name=" + name + ", valor=" + valor + ", type=" + type + "]";
	}
	
}
