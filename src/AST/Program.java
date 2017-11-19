package AST;

import java.util.ArrayList;
import java.util.Vector;

import Lexer.Gramatica;

public class Program {
	Vector v;
	CompositeStatement s;
	
	public Program(Vector v, CompositeStatement s)
	{
		this.v = v;
		this.s = s;
	}

	@Override
	public String toString() {
		return "Program [Variaveis=" + v + ", CompositeStatement=" + s + "]";
	}
	
	public void genC( StringBuilder sb)
	{
		sb.append("#include <stdio.h> \n");
		sb.append("#include <stdlib.h> \n");
		sb.append("void main () { \n");
		
		for(Object array : v)
		{
			for(Object var : ((ArrayList) array))
			{
				if(((VariableExpr)var).getType()==Gramatica.INTEGER)
					sb.append("int " + ((VariableExpr)var).getName() + ";\n");
				if(((VariableExpr)var).getType()==Gramatica.CHAR)
					sb.append("char " + ((VariableExpr)var).getName() + "[256] ;\n");
				if(((VariableExpr)var).getType()==Gramatica.BOOLEAN)
					sb.append("bool " + ((VariableExpr)var).getName() + ";\n");
			}
		}
		
		s.genC(sb);
		
		sb.append("}");
		
	}
}
