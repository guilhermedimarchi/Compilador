package AST;

import Lexer.Gramatica;

public class CompositeExpr extends Expr{
	private Expr e, d;
	private String op;
	
	public CompositeExpr(Expr e, String op, Expr d)
	{
		if(Gramatica.checkTypes(e, op, d))
		{
			if(op == Gramatica.IGUAL || op == Gramatica.MAIOR || op == Gramatica.MENOR || op == Gramatica.MAIORIGUAL || op == Gramatica.MENORIGUAL || op == Gramatica.DIFERENTE)
			{
				this.setType(Gramatica.BOOLEAN);
			}
			else
			{
				this.setType(e.getType());
			}
			
		}
		this.e = e;
		this.op = op;
		this.d = d;
	}

	@Override
	public String toString() {
		return "CompositeExpr [Expr esquerda=" + e +  ", op=" + op + ", Expr direita=" + d + "]";
	}

	@Override
	public void genC(StringBuilder sb) {
		e.genC(sb);
		
		switch(op) {
			case Gramatica.MULTIPLICACAO:
				sb.append(" * ");
				break;
			case Gramatica.MAIS:
				sb.append(" + ");
				break;
			case Gramatica.MENOS:
				sb.append(" - ");
				break;
			case Gramatica.DIVISAO:
				sb.append(" / ");
				break;
			case Gramatica.MAIOR:
				sb.append(" > ");
				break;
			case Gramatica.MENOR:
				sb.append(" < ");
				break;
			case Gramatica.MENORIGUAL:
				sb.append(" <= ");
				break;
			case Gramatica.MAIORIGUAL:
				sb.append(" >= ");
				break;
			case Gramatica.AND:
				sb.append(" && ");
				break;
			case Gramatica.OR:
				sb.append(" || ");
				break;
			case Gramatica.IGUAL:
				sb.append(" == ");
				break;
			default:
				sb.append(" != ");
				break;
				
		}
		
		d.genC(sb);
		
		
		
	}

}
