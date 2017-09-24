package Sintatico;

import java.util.Hashtable;
import java.util.Vector;

import AST.*;
import Lexer.Gramatica;
import Lexer.Lexer;

public class Compilador {
	private Lexer lexer;
	private Hashtable<String, Integer> variaveisDeclaradas = new Hashtable();

	public Compilador(Lexer lexer) {
		this.lexer = lexer;
	}

	public void error() {
		System.out.println("ERRO");
		Runtime.getRuntime().exit(1);
	}

	// Program ::= [ "var" VarDecList ] CompositeStatement
	public Program program() {
		Vector v = null;
		lexer.nextToken();
		if (lexer.getToken() == Gramatica.VAR) {
			lexer.nextToken();
			v = varDecList();
		}
		return new Program(v, compositeStatement());
	}

	// VarDecList ::= VarDecList2 { VarDecList2 }
	private Vector varDecList() {
		Vector v = new Vector();
		v.addElement(varDecList2());
		while (lexer.getToken() == Gramatica.ID) {
			v.addElement(varDecList2());
		}
		return v;
	}

	// VarDecList2 ::= Ident { ’,’ Ident } ’:’ Type ’;’
	private Expr varDecList2() {
		Expr e = ident();
		while (lexer.getToken() == Gramatica.VIRGULA) {
			lexer.nextToken();
			ident();
		}
		if (lexer.getToken() == Gramatica.DOISPONTOS) {
			lexer.nextToken();
			type();
		} else
			error();

		if (lexer.getToken() == Gramatica.PONTOEVIRGULA) {
			lexer.nextToken();
		} else
			error();
		
		return e;

	}

	// Type ::= "integer" | "boolean" | "char"
	private void type() {
		if (lexer.getToken() == Gramatica.INTEGER || lexer.getToken() == Gramatica.BOOLEAN
				|| lexer.getToken() == Gramatica.CHAR)
			lexer.nextToken();
		else
			error();
	}

	// Ident ::= Letter { Letter }
	private Expr ident() {
		Expr e = null;
		if (lexer.getToken() == Gramatica.ID)
		{
			//analise semantica
			if(!variaveisDeclaradas.containsKey(lexer.getValorString()))
				variaveisDeclaradas.put(lexer.getValorString(), 0);
			else
			{
				System.out.println("Nome de variável já utilizado\n");
				error();
			}
			
			e = new VariableExpr(lexer.getValorString());
			lexer.nextToken();
		}
		else
			error();
		
		return e;
	}

	// CompositeStatement ::= "begin" StatementList "end"
	private Statement compositeStatement() {
		Statement e = null;
		if (lexer.getToken() != Gramatica.BEGIN)
			error();

		lexer.nextToken();

		e = statementList();

		if (lexer.getToken() != Gramatica.END)
			error();

		lexer.nextToken();

		return e;
	}

	// StatementList ::= | Statement ";" StatementList
	private Statement statementList() {
		Statement s = null;
		if (lexer.getToken() != Gramatica.FIM) {
			s = statement();
			if (lexer.getToken() == Gramatica.PONTOEVIRGULA) {
				lexer.nextToken();
				return new CompositeStatement(s, statementList());
			}
		}
		return s;
	}

	// Statement ::= AssignmentStatement | IfStatement | ReadStatement |
	// WriteStatement
	private Statement statement() {
		Statement s = null;
		switch (lexer.getToken()) {
			case Gramatica.ID:
				s = assignmentStatement();
				break;
			case Gramatica.IF:
				s = ifStatement();
				break;
			case Gramatica.READ:
				s = readStatement();
				break;
			case Gramatica.WRITE:
				s = writeStatement();
				break;
			default:
				error();
		}
		return s;
	}

	// WriteStatement ::= "write" "(" OrExpr ")"
	private Statement writeStatement() {
		Expr e = null;
		
		lexer.nextToken();
		if (lexer.getToken() == Gramatica.PARENTESESE) {
			lexer.nextToken();

			e = orExpr();

			if (lexer.getToken() == Gramatica.PARENTESESD)
				lexer.nextToken();
			else
				error();
		} else
			error();
		return new WriteStatement(e);
	}

	// ReadStatement ::= "read" "(" Variable ")"
	private Statement readStatement() {
		Expr e = null;
		lexer.nextToken();
		if (lexer.getToken() == Gramatica.PARENTESESE) {
			lexer.nextToken();

			e = variable();

			if (lexer.getToken() == Gramatica.PARENTESESD)
				lexer.nextToken();
			else
				error();
		} else
			error();
		
		return new ReadStatement(e);
	}

	// IfStatement ::= "if" OrExpr "then" StatementList [ "else" StatementList ] "endif"
	private Statement ifStatement() {
		Statement s1 =null, s2 = null;
		
		lexer.nextToken();
		Expr e = orExpr();
		if (lexer.getToken() == Gramatica.THEN) {
			lexer.nextToken();
			s1 = statementList();
		} else
			error();
		if (lexer.getToken() == Gramatica.ELSE) {
			lexer.nextToken();
			s2 = statementList();
		}
		if (lexer.getToken() == Gramatica.ENDIF)
			lexer.nextToken();
		else
			error();
		
		return new IfStatement(e, s1, s2);
	}

	private Statement assignmentStatement() {
		Expr e = variable();
		if (lexer.getToken() == Gramatica.ATRIBUICAO) {
			String op = lexer.getToken();
			lexer.nextToken();
			return new AssignmentStatement(e,op,orExpr());
		}
		else 
			error();
		return null;
	}

	// OrExpr ::= AndExpr [ "or" AndExpr ]
	private Expr orExpr() {
		Expr e = andExpr();
		if (lexer.getToken() == Gramatica.OR) {
			String op = lexer.getToken();
			lexer.nextToken();
			return new CompositeExpr(e,op,andExpr());
		}
		return e;
	}

	// AndExpr ::= RelExpr [ "and" RelExpr ]
	private Expr andExpr() {
		Expr e = relExpr();
		if (lexer.getToken() == Gramatica.AND) {
			String op = lexer.getToken();
			lexer.nextToken();
			return new CompositeExpr(e,op,relExpr());
		}
		return e;
	}

	// RelExpr ::= AddExpr [ RelOp AddExpr ]
	private Expr relExpr() {
		Expr e = addExpr();
		if (lexer.getToken() == Gramatica.MENOR || lexer.getToken() == Gramatica.MAIOR
				|| lexer.getToken() == Gramatica.MENORIGUAL || lexer.getToken() == Gramatica.MAIORIGUAL
				|| lexer.getToken() == Gramatica.IGUAL || lexer.getToken() == Gramatica.DIFERENTE) {
			String op = relOp();
			return new CompositeExpr(e,op,addExpr());
		}
		return e;
	}

	private Expr addExpr() {
		Expr e = multExpr();
		while (lexer.getToken() == Gramatica.MAIS || lexer.getToken() == Gramatica.MENOS) {
			String op = addOp();
			return new CompositeExpr(e,op,multExpr());
		}
		return e;
	}

	//MultExpr ::= SimpleExpr { MultOp SimpleExpr }
	private Expr multExpr() {
		Expr e = simpleExpr();
		while (lexer.getToken() == Gramatica.MULTIPLICACAO || lexer.getToken() == Gramatica.DIVISAO
				|| lexer.getToken() == Gramatica.MODULO) {
			String op = multOp();
			return new CompositeExpr(e,op,simpleExpr());
		}
		return e;
	}

	// SimpleExpr ::= Number | Variable | "true" | "false" | Character | ’(’ Expr
	// ’)’ | "not" SimpleExpr | AddOp SimpleExpr
	private Expr simpleExpr() {
		Expr e = null;
		switch (lexer.getToken()) {
			case Gramatica.NUMBER:
				e = number();
				break;
			case Gramatica.ID:
				e = variable();
				break;
			case Gramatica.TRUE:
				e = trueExpr();
				break;
			case Gramatica.FALSE:
				e = falseExpr();
				break;
			case Gramatica.PARENTESESE:
				lexer.nextToken();
				e = expr();
				if (lexer.getToken() == Gramatica.PARENTESESD)
					lexer.nextToken();
				else
					error();
				break;
			case Gramatica.NOT:
				lexer.nextToken();
				e = simpleExpr();
				break;
			case Gramatica.MAIS:
				addOp();
				e = simpleExpr();
				break;
			case Gramatica.MENOS:
				addOp();
				e = simpleExpr();
				break;
			default:
				error();
				break;
		}
		return e;
	}

	private Expr expr() {
		Expr e = null;
		
		return e;
	}

	private Expr trueExpr() {
		Expr e = null;
		if (lexer.getToken() == Gramatica.TRUE)
		{
			e = new TrueExpr(lexer.getToken());
			lexer.nextToken();
		}
		else
			error();
		return e;
	}

	private Expr falseExpr() {
		Expr e = null;
		if (lexer.getToken() == Gramatica.FALSE)
		{
			e = new FalseExpr(lexer.getToken());
			lexer.nextToken();
		}
		else
			error();
		return e;
	}

	private Expr number() {
		Expr e = null;
		if (lexer.getToken() == Gramatica.NUMBER)
		{
			e = new NumberExpr(lexer.getValorNumerico());
			lexer.nextToken();
		}
		else
			error();
		return e;
	}

	private String multOp() {
		String op = "";
		if (lexer.getToken() == Gramatica.MULTIPLICACAO || lexer.getToken() == Gramatica.DIVISAO
				|| lexer.getToken() == Gramatica.MODULO)
		{
			op = lexer.getValorString();
			lexer.nextToken();
		}
		else
			error();
		return op;
	}

	private String addOp() {
		String op = "";
		if (lexer.getToken() == Gramatica.MAIS || lexer.getToken() == Gramatica.MENOS) 
		{
			op = lexer.getValorString();
			lexer.nextToken();
		} 
		else
			error();
		return op;
	}

	private String relOp() {
		String op = "";
		if (lexer.getToken() == Gramatica.MENOR || lexer.getToken() == Gramatica.MAIOR
				|| lexer.getToken() == Gramatica.MENORIGUAL || lexer.getToken() == Gramatica.MAIORIGUAL
				|| lexer.getToken() == Gramatica.IGUAL || lexer.getToken() == Gramatica.DIFERENTE) {
			op = lexer.getValorString();
			lexer.nextToken();
		} else
			error();
		return op;
	}

	private Expr variable() {
		Expr e = null;
		if (lexer.getToken() == Gramatica.ID)
		{
			//Analise sintatica. Verifica se a variavel que está sendo utilizada foi realmente criada
			if(variaveisDeclaradas.containsKey(lexer.getValorString()))
				e = new VariableExpr(lexer.getValorString());
			else
			{
				System.out.println("Variavel não declarada");
				error();
			}
			lexer.nextToken();
		}
		else
			error();
		return e;
	}

}
