import AST.Program;
import Lexer.Lexer;
import Sintatico.Compilador;

public class Main {

	public static void main(String[] args) {

		String entrada = "var abc,abd,abe : integer; begin  READ(abc); Write(abd); if abc > abe then abe = 1 else abc = abd + 2 endif  end";

		String entradaErro = "var abc : integer; begin123123;;  READ(abc); Write(abc)  end";

		System.out.println("Código de entrada: " + entrada);

		Lexer lexer = new Lexer(entrada);
		Compilador compilador = new Compilador(lexer);
		
		Program p = compilador.program();
		
		System.out.println(p);

		System.out.println("Sucesso");

	}
}
