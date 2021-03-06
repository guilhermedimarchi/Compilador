import AST.Program;
import Lexer.Lexer;
import Sintatico.Compilador;

public class Main {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		String entrada = "var i, j : integer; achou : boolean; ch : char; begin i = 1; j = i*3 - 4%i + 3*2*i/2; if i + 1 > j - 3 and i <= j + 5 or 4 < i then write(i); endif; ch = �a�; achou = false; if ch >= �b� and achou then read(ch); endif; end";

		String entradaErro = "var abc : integer; begin aaa))( READ(abc); Write(abc);  end";

		String entradsa = "var i, j : integer; begin j = i + 1; end";

		System.out.println("C�digo de entrada: " + entrada);

		Lexer lexer = new Lexer(entrada);
		Compilador compilador = new Compilador(lexer);

		Program p = compilador.program();
		p.genC(sb);

		System.out.println("AST: " + p);

		System.out.println(sb.toString());

		System.out.println("Sucesso");

	}
}
