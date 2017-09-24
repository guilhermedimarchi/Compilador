package Lexer;

import java.util.Hashtable;

public class Lexer {

	private char[] entrada;
	private String token;
	private int pos;
	private int valorNumerico;

	// somente get para não deixar alterar o valor de token ou de outras variaveis
	public String getToken() {
		return token;
	}

	public int getValorNumerico() {
		return valorNumerico;
	}

	static private Hashtable<String, String> palavrasReservadas;

	public Lexer(String entrada) {
		this.entrada = entrada.toLowerCase().toCharArray();
		pos = 0;
		token = "";
		initpalavrasReservadas();
	}

	/**
	 * Lista de palavras reservadas pela gramatica
	 */
	private void initpalavrasReservadas() {

		palavrasReservadas = new Hashtable<>();
		palavrasReservadas.put("var", Gramatica.VAR);
		palavrasReservadas.put("if", Gramatica.IF);
		palavrasReservadas.put("else", Gramatica.ELSE);
		palavrasReservadas.put("while", Gramatica.WHILE);
		palavrasReservadas.put("begin", Gramatica.BEGIN);
		palavrasReservadas.put("end", Gramatica.END);
		palavrasReservadas.put("read", Gramatica.READ);
		palavrasReservadas.put("write", Gramatica.WRITE);
		palavrasReservadas.put("integer", Gramatica.INTEGER);
		palavrasReservadas.put("boolean", Gramatica.BOOLEAN);
		palavrasReservadas.put("char", Gramatica.CHAR);
		palavrasReservadas.put("or", Gramatica.OR);
		palavrasReservadas.put("and", Gramatica.AND);
		palavrasReservadas.put("true", Gramatica.TRUE);
		palavrasReservadas.put("false", Gramatica.FALSE);
		palavrasReservadas.put("then", Gramatica.THEN);
		palavrasReservadas.put("not", Gramatica.NOT);
		palavrasReservadas.put("endif", Gramatica.ENDIF);

	}

	public void nextToken() {
		char ch = '\0';

		if (pos < entrada.length)
			ch = entrada[pos];

		// Verifica se é o EOF
		if (ch == '\0') {
			token = Gramatica.FIM;
		} else {
			// Elimina espaços brancos
			while (ch == ' ') {
				pos++;
				ch = entrada[pos];
			}

			if (Character.isDigit(ch)) // VERIFICA SE É NUMERO
			{
				String numero = "";
				while (Character.isDigit(entrada[pos])) {
					numero += entrada[pos];
					pos++;
				}
				valorNumerico = Integer.parseInt(numero);
				token = Gramatica.NUMBER;
			} else if (Character.isLetter(ch)) // VERIFICA SE É ID ou alguma palavra reservada
			{
				String palavra = "";
				while (pos < entrada.length && Character.isLetter(entrada[pos])) {
					palavra += entrada[pos];
					pos++;
				}
				if (!palavrasReservadas.containsKey(palavra))
					token = Gramatica.ID;
				else
					token = palavrasReservadas.get(palavra);
			} else if (ch == '=') {
				pos++;
				if (entrada[pos] == '=') {
					pos++;
					token = Gramatica.IGUAL;
				} else {
					token = Gramatica.ATRIBUICAO;
				}
			} else if (ch == '<') {
				pos++;
				if (entrada[pos] == '=') {
					pos++;
					token = Gramatica.MENORIGUAL;
				} else if (entrada[pos] == '>') {
					token = Gramatica.DIFERENTE;
				}
				else
					token = Gramatica.MENOR;
			} else if (ch == '>') {
				pos++;
				if (entrada[pos] == '=') {
					pos++;
					token = Gramatica.MAIORIGUAL;
				}
				else
					token = Gramatica.MAIOR;
			} else {
				pos++;
				switch (ch) {
				case '+':
					token = Gramatica.MAIS;
					break;
				case '-':
					token = Gramatica.MENOS;
					break;
				case '*':
					token = Gramatica.MULTIPLICACAO;
					break;
				case '/':
					token = Gramatica.DIVISAO;
					break;
				case '(':
					token = Gramatica.PARENTESESE;
					break;
				case ')':
					token = Gramatica.PARENTESESD;
					break;
				case ',':
					token = Gramatica.VIRGULA;
					break;
				case ';':
					token = Gramatica.PONTOEVIRGULA;
					break;
				case ':':
					token = Gramatica.DOISPONTOS;
					break;
				case '.':
					token = Gramatica.PONTO;
					break;
				default:
					token = Gramatica.FIM;
				}
			}
		}
	}
}
