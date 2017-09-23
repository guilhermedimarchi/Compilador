
public class Compilador {

	public static void main(String[] args) {

		
		String entrada = "teste 123 if";
		System.out.println("Código de entrada: "+entrada);
		Lexer lexer = new Lexer(entrada);
		
		
		while(lexer.token!=Gramatica.FIM)
		{
			lexer.nextToken();
			System.out.println(lexer.token);
		}
		
	}

}
