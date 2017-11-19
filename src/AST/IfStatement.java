package AST;

public class IfStatement extends Statement{


	Expr e;
	Statement s1, s2;
	
	public IfStatement(Expr e, Statement s1, Statement s2)
	{
		this.e = e;
		this.s1 = s1;
		this.s2 = s2;
	}

	
		@Override
	public String toString() {
		return "IfStatement [Comparacao=" + e + ", Then=" + s1 + ", Else=" + s2 + "]";
	}


		@Override
		public void genC(StringBuilder sb) {
			sb.append("if ( ");
			e.genC(sb);
			sb.append(" ) {\n");
			s1.genC(sb);
			sb.append(" } \n");
			if(s2!=null)
			{
				sb.append("else { ");
				s2.genC(sb);
				sb.append(" } \n");
			}
			
		}
}
