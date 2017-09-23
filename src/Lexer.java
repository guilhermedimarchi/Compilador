import java.util.Hashtable;

public class Lexer {
	
	private char [] entrada;
	private String token;
	private int pos;
	
	//somente get para não deixar alterar o valor de token ou de outras variaveis
	public String getToken() {
		return token;
	}
	
	static private Hashtable<String, String> palavrasReservadas;

	public Lexer(String entrada)
	{
		entrada.toLowerCase();
		this.entrada = entrada.toCharArray();
		pos = 0;
		token = "";
		initpalavrasReservadas();
	}

	/**
	 * Lista de palavras reservadas pela gramatica
	 */
	private void initpalavrasReservadas() {
		
		palavrasReservadas = new Hashtable<>();
		palavrasReservadas.put("var", 	Gramatica.VAR);
		palavrasReservadas.put("if", 	Gramatica.IF);
		palavrasReservadas.put("else", 	Gramatica.ELSE);
		palavrasReservadas.put("while", Gramatica.WHILE);
		palavrasReservadas.put("begin", Gramatica.BEGIN);
		palavrasReservadas.put("end", 	Gramatica.END);
		palavrasReservadas.put("read", 	Gramatica.READ);
		palavrasReservadas.put("write", Gramatica.WRITE);
		palavrasReservadas.put("integer", Gramatica.INTEGER);
		palavrasReservadas.put("boolean", Gramatica.BOOLEAN);
		palavrasReservadas.put("char", 	Gramatica.CHAR);
		palavrasReservadas.put("or", 	Gramatica.OR);
		palavrasReservadas.put("and", 	Gramatica.AND);
		palavrasReservadas.put("true", 	Gramatica.TRUE);
		palavrasReservadas.put("false", Gramatica.FALSE);
		palavrasReservadas.put("then", Gramatica.THEN);
		palavrasReservadas.put("endif", Gramatica.ENDIF);
	}
	
	public void nextToken() {
		char ch = '\0';
		
		if(pos<entrada.length)
			ch = entrada[pos];
		
		//Verifica se é o EOF
		if(ch=='\0')
		{
			token = Gramatica.FIM;
		}
		else
		{
			//Elimina espaços brancos
			while(ch==' ')
			{
				pos++;
				ch = entrada[pos];
			}
			
			if(Character.isDigit(ch)) //VERIFICA SE É NUMERO
			{
				while(Character.isDigit(entrada[pos]))
				{
					pos++;
				}
				token = Gramatica.NUMBER;
			}
			else if (Character.isLetter(ch)) //VERIFICA SE É ID ou alguma palavra reservada
			{
				String palavra = "";
				while(pos<entrada.length && Character.isLetter(entrada[pos]) )
				{
					palavra += entrada[pos];
					pos++;
				}
				if(!palavrasReservadas.containsKey(palavra))
					token = Gramatica.ID;
				else
					token = palavrasReservadas.get(palavra);	
			}
			else
			{
				pos++;
				switch(ch) {
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
					case '>':
						token = Gramatica.MAIOR;
						break;
					case '<':
						token = Gramatica.MENOR;
						break;
					case '=':
						token = Gramatica.IGUAL;
						break;
					case '(':
						token = Gramatica.PARENTESESD;
						break;
					case ')':
						token = Gramatica.PARENTESESE;
						break;
					case ',':
						token = Gramatica.VIRGULA;
						break;
					case ';':
						token = Gramatica.PONTOEVIRGULA;
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
