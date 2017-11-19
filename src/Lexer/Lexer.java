package Lexer;

import java.util.Hashtable;

public class Lexer {

	private char[] entrada;
	private String token;
	private int pos;
	private int valorNumerico;
	private String valorString;

	// somente get para não deixar alterar o valor de token ou de outras variaveis
	public String getToken() {
		return token;
	}

	public int getValorNumerico() {
		return valorNumerico;
	}

	public String getValorString() {
		return valorString;
	}

	static private Hashtable<String, String> palavrasReservadas;

	public Lexer(String entrada) {
		this.entrada = entrada.toLowerCase().toCharArray();
		this.pos = 0;
		this.token = "";
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
		this.valorString = "";

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
				if (!palavrasReservadas.containsKey(palavra)) {
					token = Gramatica.ID;
					valorString = palavra;
				} else {
					token = palavrasReservadas.get(palavra);
					valorString = palavra;
				}
			}

			else if (ch == '’') {
				String palavra = "" + ch;
				pos++;
				palavra += entrada[pos];
				pos++;
				palavra += entrada[pos];
				pos++;
				token = Gramatica.CHAR;
				valorString = palavra;
			}

			else if (ch == '=') {
				String palavra = "" + ch;
				pos++;
				if (entrada[pos] == '=') {
					palavra += entrada[pos];
					pos++;
					token = Gramatica.IGUAL;
				} else {
					token = Gramatica.ATRIBUICAO;
				}
				valorString = palavra;
			} else if (ch == '<') {
				String palavra = "" + ch;
				pos++;
				if (entrada[pos] == '=') {
					palavra += entrada[pos];
					pos++;
					token = Gramatica.MENORIGUAL;
				} else if (entrada[pos] == '>') {
					palavra += entrada[pos];
					token = Gramatica.DIFERENTE;
				} else
					token = Gramatica.MENOR;
				valorString = palavra;
			} else if (ch == '>') {
				String palavra = "" + ch;
				pos++;
				if (entrada[pos] == '=') {
					palavra += entrada[pos];
					pos++;
					token = Gramatica.MAIORIGUAL;
				} else
					token = Gramatica.MAIOR;
				valorString = palavra;
			} else {
				valorString += "" + ch;
				pos++;
				switch (ch) {
				case '+':
					token = Gramatica.MAIS;
					break;
				case '%':
					token = Gramatica.MODULO;
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
