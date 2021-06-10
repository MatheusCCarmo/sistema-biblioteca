package br.ufba.trabalho.biblioteca;

import java.util.HashMap;

public class InicializadorComandos {

	public static HashMap<String, Comando> inicializarComandos() {
		HashMap<String, Comando> comandos = new HashMap<String, Comando>();

		comandos.put("res", new RealizarReserva());
		comandos.put("emp", new RealizarEmprestimo());
		comandos.put("dev", new RealizarDevolucao());
		comandos.put("obs", new RealizarObservacao());
		comandos.put("liv", new RealizarConsultaLivro());
		comandos.put("usu", new RealizarConsultaUsuario());
		comandos.put("ntf", new RealizarConsultaSuperior());
		
		return comandos;
	}
}
