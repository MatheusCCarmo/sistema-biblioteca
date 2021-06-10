package br.ufba.trabalho.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class InterfaceUsuario {

	private HashMap<String, Comando> comandos;

	private String obterComandoConsole() throws IOException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		System.out.println();
		System.out.println("Digite o comando: ");
		return teclado.readLine();
	}

	private void executarComando(String stringComando) {
		String[] stringComandoSeparado = stringComando.split(" ");

		String siglaDoComando = stringComandoSeparado[0];
		Comando comando = comandos.get(siglaDoComando);

		if (!validarComando(comando, stringComandoSeparado)) {
			System.out.println("Comando inválido.");
			return;
		}

		int codigo1 = Integer.parseInt(stringComandoSeparado[1]);
		int codigo2;

		// comandos com 2 entradas
		if (stringComandoSeparado.length == 2) {
			comando.executar(codigo1);
		}
		// comandos com 3 entradas
		if (stringComandoSeparado.length == 3) {
			codigo2 = Integer.parseInt(stringComandoSeparado[2]);
			comando.executar(codigo1, codigo2);
		}
	}

	private boolean validarComando(Comando comando, String[] stringComandoSeparado) {
		if (comando == null) {
			System.out.println("Este comando não consta na lista de comandos");
			return false;
		}

		// menos de 2 entradas
		if (stringComandoSeparado.length < 2) {
			System.out.println("Entrada insuficinte.");
			return false;
		}
		
		// mais de 3 entradas
		if (stringComandoSeparado.length > 3) {
			System.out.println("Não é aceito tantos parâmetros");
			return false;
		}

		return true;
	}

	public void fazerLoopEntrada() throws IOException {
		comandos = InicializadorComandos.inicializarComandos();

		String stringComando = obterComandoConsole();
		while (!stringComando.equals("sai")) {
			executarComando(stringComando);
			stringComando = obterComandoConsole();
		}
	}
}
