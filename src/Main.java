import Lexer.Lexer;
import Sintatico.Compilador;

public class Main {

		public static void main(String[] args) {

		String entrada = "begin" + 
				"  Write(abc)" + 
				" end";
		System.out.println("Código de entrada: "+entrada);
		
		Lexer lexer = new Lexer(entrada);
		Compilador compilador = new Compilador(lexer);
		compilador.program();
		
		
	}
}
