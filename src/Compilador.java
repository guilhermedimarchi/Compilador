
public class Compilador {

	public static void main(String[] args) {

		
		String entrada = "teste 123 if * / 2131 id if while teseee +++++";
		System.out.println("Código de entrada: "+entrada);
		Lexer lexer = new Lexer(entrada);
		
		
		while(lexer.getToken()!=Gramatica.FIM)
		{
			lexer.nextToken();
			System.out.println(lexer.getToken());
		}
		
	}

}
