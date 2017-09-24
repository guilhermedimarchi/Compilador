package Sintatico;
import Lexer.Gramatica;
import Lexer.Lexer;


public class Compilador {
	Lexer lexer;
	
	public Compilador(Lexer lexer)
	{
		this.lexer = lexer;
	}

	public void error()
	{
		System.out.println("ERRO");
	}
	
	//Program ::= [ "var" VarDecList ] CompositeStatement
	public void program()
	{	
		lexer.nextToken();
		if(lexer.getToken()==Gramatica.VAR)
		{
			lexer.nextToken();
			varDecList();
		}
		compositeStatement();
	}
	
	//VarDecList ::= VarDecList2 { VarDecList2 } 
	private void varDecList() {
		varDecList2();
		while(lexer.getToken()==Gramatica.ID)
		{
			varDecList2();
		}
	}
	
	//VarDecList2 ::= Ident { ’,’ Ident } ’:’ Type ’;’ 
	private void varDecList2() {
		ident();
		while(lexer.getToken()==Gramatica.VIRGULA)
		{
			lexer.nextToken();
			ident();
		}
		if(lexer.getToken()==Gramatica.DOISPONTOS)
		{
			lexer.nextToken();
			type();
		}
		else
			error();
		
		if(lexer.getToken()==Gramatica.PONTOEVIRGULA)
		{
			lexer.nextToken();
		}
		else
			error();
		
		
	}
	
	//Type ::= "integer" | "boolean" | "char"
	private void type() {
		if(lexer.getToken()==Gramatica.INTEGER || lexer.getToken()==Gramatica.BOOLEAN || lexer.getToken()==Gramatica.CHAR)
			lexer.nextToken();
		else
			error();
	}

	//Ident ::= Letter { Letter } 
	private void ident() {
		if(lexer.getToken()==Gramatica.ID)
			lexer.nextToken();
		else
			error();
	}

	//CompositeStatement ::= "begin" StatementList "end"
	private void compositeStatement()
	{
		if(lexer.getToken()!=Gramatica.BEGIN)
			error();
		
		lexer.nextToken();
		
		statementList();
		
		if(lexer.getToken()!=Gramatica.END)
			error();
		
		lexer.nextToken();
		
	}
	
	//StatementList ::= | Statement ";" StatementList
	private void statementList() {
		if(lexer.getToken()!=Gramatica.FIM)
		{
			statement();
			if(lexer.getToken()==Gramatica.PONTOEVIRGULA)
			{
				lexer.nextToken();
				statementList();
			}
		}
	}

	//Statement ::= AssignmentStatement | IfStatement | ReadStatement | WriteStatement
	private void statement() {
		switch(lexer.getToken())
		{
			case Gramatica.ID:
				assignmentStatement();
				break;
			case Gramatica.IF:
				ifStatement();
				break;
			case Gramatica.READ:
				readStatement();
				break;
			case Gramatica.WRITE:
				writeStatement();
				break;
			default:
				error();
		}
	}

	//WriteStatement ::= "write" "(" OrExpr ")" 
	private void writeStatement() {
		lexer.nextToken();
		if(lexer.getToken()==Gramatica.PARENTESESE)
		{
			lexer.nextToken();
			
			orExpr();
			
			if(lexer.getToken()==Gramatica.PARENTESESD)
				lexer.nextToken();
			else
				error();
		}
		else
			error();
	}

	//ReadStatement ::= "read" "(" Variable ")"
	private void readStatement() {
		lexer.nextToken();
		if(lexer.getToken()==Gramatica.PARENTESESE)
		{
			lexer.nextToken();
			
			variable();
			
			if(lexer.getToken()==Gramatica.PARENTESESD)
				lexer.nextToken();
			else
				error();
		}
		else
			error();
	}

	//IfStatement ::= "if" OrExpr "then" StatementList [ "else" StatementList ] "endif" 
	private void ifStatement() {
		lexer.nextToken();
		orExpr();
		if(lexer.getToken()==Gramatica.THEN)
		{
			lexer.nextToken();
			statementList();
		}
		else
			error();
		if(lexer.getToken()==Gramatica.ELSE)
		{
			lexer.nextToken();
			statementList();
		}
		if(lexer.getToken()==Gramatica.ENDIF)
			lexer.nextToken();
		else
			error();
	}

	private void assignmentStatement() {
		variable();
		if(lexer.getToken()==Gramatica.ATRIBUICAO)
		{
			lexer.nextToken();
			orExpr();
		}
	}
	
	//OrExpr ::= AndExpr [ "or" AndExpr ]
	private void orExpr() {
		andExpr();
		if(lexer.getToken()==Gramatica.OR)
		{
			lexer.nextToken();
			andExpr();
		}
	}

	//AndExpr ::= RelExpr [ "and" RelExpr ]
	private void andExpr() {
		relExpr();
		if(lexer.getToken()==Gramatica.AND)
		{
			lexer.nextToken();
			relExpr();
		}
	}

	//RelExpr ::= AddExpr [ RelOp AddExpr ]
	private void relExpr() {
		addExpr();
		if(lexer.getToken()==Gramatica.MENOR ||
			lexer.getToken()==Gramatica.MAIOR ||
			lexer.getToken()==Gramatica.MENORIGUAL ||
			lexer.getToken()==Gramatica.MAIORIGUAL ||
			lexer.getToken()==Gramatica.IGUAL ||
			lexer.getToken()==Gramatica.DIFERENTE)
		{
			relOp();
			addExpr();
			
		}
	}

	private void addExpr() {
		multExpr();
		while(lexer.getToken()==Gramatica.MAIS || lexer.getToken()==Gramatica.MENOS)
		{
			addOp();
			multExpr();
		}
	}

	private void multExpr() {
		simpleExpr();
		while(lexer.getToken()==Gramatica.MULTIPLICACAO || 
				lexer.getToken()==Gramatica.DIVISAO ||
				lexer.getToken()==Gramatica.MODULO)
		{
			multOp();
			simpleExpr();
		}
	}

	//SimpleExpr ::= Number | Variable | "true" | "false" | Character | ’(’ Expr ’)’ | "not" SimpleExpr | AddOp SimpleExpr 
	private void simpleExpr() {
		switch(lexer.getToken()) 
		{
			case Gramatica.NUMBER:
				number();
				break;
			case Gramatica.ID:
				variable();
				break;
			case Gramatica.TRUE:
				trueExpr();
				break;
			case Gramatica.FALSE:
				trueExpr();
				break;
			case Gramatica.PARENTESESE:
				lexer.nextToken();
				expr();
				if(lexer.getToken()==Gramatica.PARENTESESD)
					lexer.nextToken();
				else
					error();
				break;
			case Gramatica.NOT:
				lexer.nextToken();
				simpleExpr();
				break;
			case Gramatica.MAIS:
				addOp();
				simpleExpr();
				break;
			case Gramatica.MENOS:
				addOp();
				simpleExpr();
				break;
			default:
				error();
				break;
				
		}
	}

	private void expr() {
		// TODO Auto-generated method stub
		
	}

	private void trueExpr() {
		if(lexer.getToken()==Gramatica.TRUE)
			lexer.nextToken();
		else
			error();
	}
	private void falseExpr() {
		if(lexer.getToken()==Gramatica.FALSE)
			lexer.nextToken();
		else
			error();
	}
	private void number() {
		if(lexer.getToken()==Gramatica.NUMBER)
			lexer.nextToken();
		else
			error();
	}

	private void multOp() {
		if(lexer.getToken()==Gramatica.MULTIPLICACAO || 
				lexer.getToken()==Gramatica.DIVISAO ||
				lexer.getToken()==Gramatica.MODULO)
			lexer.nextToken();
		else
			error();
		
	}

	private void addOp() {
		if(lexer.getToken()==Gramatica.MAIS || lexer.getToken()==Gramatica.MENOS)
		{
			lexer.nextToken();
		}
		else
			error();
	}

	private void relOp() {
		if(lexer.getToken()==Gramatica.MENOR ||
			lexer.getToken()==Gramatica.MAIOR ||
			lexer.getToken()==Gramatica.MENORIGUAL ||
			lexer.getToken()==Gramatica.MAIORIGUAL ||
			lexer.getToken()==Gramatica.IGUAL ||
			lexer.getToken()==Gramatica.DIFERENTE)
		{
			lexer.nextToken();
		}
		else
			error();
	}

	private void variable()
	{
		if(lexer.getToken()==Gramatica.ID)
			lexer.nextToken();
		else
			error();
	}
	
	
	
	
	
	
	
	
	
	
	


//	private Vector varDecList()
//	{
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	private Expr SimpleExpr()
//	{
//		Expr expr;
//		if(lexer.getToken()==Gramatica.NUMBER)
//		{
//			expr = new NumberExpr(lexer.getValorNumerico());
//			lexer.nextToken();
//		}
//		else if(lexer.getToken()==Gramatica.ID)
//		{
//			expr = new VariableExpr(lexer.getToken());
//			lexer.nextToken();
//		}
//		else if(lexer.getToken()==Gramatica.TRUE)
//		{
//			expr = trueExpr();
//		}
//		else if(lexer.getToken()==Gramatica.FALSE)
//		{
//			expr = falseExpr();
//		}
//		else if(lexer.getToken()==Gramatica.PARENTESESE)
//		{
//			lexer.nextToken();
//			expr = expr();
//			if(lexer.getToken()!=Gramatica.PARENTESESD)
//				error();
//			lexer.nextToken();
//		}
//		else if(lexer.getToken()==Gramatica.NOT)
//		{
//			lexer.nextToken();
//			expr = SimpleExpr();
//		}
//		else if(lexer.getToken()==Gramatica.MAIS || lexer.getToken()==Gramatica.MENOS)
//		{
//			AddOp();
//			expr = SimpleExpr();
//		}
//		else
//			error();
//		
//		return expr;
//		
//	}
//	
//	private Expr numberExpr() {
//		
//		Expr e = new NumberExpr();
//		
//	}
//
//
//	public void AddOp()
//	{
//		if(lexer.getToken()==Gramatica.MAIS || lexer.getToken()==Gramatica.MENOS)
//			lexer.nextToken();
//		else
//			error();
//			
//	}
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
