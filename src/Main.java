import AST.Program;
import Lexer.Lexer;
import Sintatico.Compilador;

public class Main {

	public static void main(String[] args) {

		String entrada = "var abc : integer; begin  READ(abc); Write(abc); if a > b then a = 1 endif  end";

		String entradaErro = "var abc : integer; begin123123;;  READ(abc); Write(abc)  end";

		System.out.println("Código de entrada: " + entrada);

		Lexer lexer = new Lexer(entrada);
		Compilador compilador = new Compilador(lexer);
		
		Program p = compilador.program();

		System.out.println("Sucesso");

	}
}
