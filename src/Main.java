import Lexer.Lexer;
import Sintatico.Compilador;

public class Main {

		public static void main(String[] args) {

		String entrada = "var a : 2;";
		System.out.println("Código de entrada: "+entrada);
		
		Lexer lexer = new Lexer(entrada);
		Compilador compilador = new Compilador(lexer);
		compilador.program();
		
		
	}
}
