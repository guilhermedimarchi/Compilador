package Lexer;

import AST.Expr;

public class Gramatica {

	public final static String VOID = "void", FIM = "fim", VAR = "var", ID = "id", LETTER = "letter", NUMBER = "number",
			IF = "if", ENDIF = "endif", THEN = "then", ELSE = "else", WHILE = "while", MAIS = "mais", MENOS = "menos",
			PARENTESESD = "parentesesd", PARENTESESE = "parentesese", MULTIPLICACAO = "multiplicacao",
			MODULO = "modulo", DIVISAO = "divisao", MAIOR = "maior", MENOR = "menor", MENORIGUAL = "menorigual",
			MAIORIGUAL = "maiorigual", ATRIBUICAO = "atribuicao", DIFERENTE = "diferente", IGUAL = "igual",
			VIRGULA = "virgula", PONTOEVIRGULA = "pontoevirgula", PONTO = "ponto", BEGIN = "begin", END = "end",
			READ = "read", WRITE = "write", INTEGER = "integer", BOOLEAN = "boolean", CHAR = "char", OR = "or",
			AND = "and", TRUE = "true", DIGIT = "digit", NOT = "not", FALSE = "false", DOISPONTOS = "doispontos";

	public static boolean checkTypes(Expr e, String op, Expr d) {
		boolean retorno = true;
		if (op == Gramatica.ATRIBUICAO) {
			if (e.getType() != d.getType())
				retorno = false;
		}

		if (op == Gramatica.IGUAL || op == Gramatica.MAIOR || op == Gramatica.MENOR || op == Gramatica.MAIORIGUAL
				|| op == Gramatica.MENORIGUAL || op == Gramatica.DIFERENTE || op == Gramatica.MULTIPLICACAO
				|| op == Gramatica.DIVISAO || op == Gramatica.MODULO || op == Gramatica.MAIS || op == Gramatica.MENOS) {
			if (e.getType() != d.getType())
				retorno = false;
		}

		if (op == Gramatica.OR) {
			if (d.getType() != Gramatica.BOOLEAN || e.getType() != Gramatica.BOOLEAN)
				retorno = false;
		}

		if (op == Gramatica.AND) {
			if (d.getType() != Gramatica.BOOLEAN || e.getType() != Gramatica.BOOLEAN)
				retorno = false;
		}

		if (!retorno) {
			System.out.println("Tipo de variável errado");
			error();
		}

		return retorno;
	}

	public static void error() {
		System.out.println("ERRO");
		Runtime.getRuntime().exit(1);
	}

}
