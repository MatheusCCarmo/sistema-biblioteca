package br.ufba.trabalho.biblioteca;

public class RealizarDevolucao implements Comando {

	@Override
	public void executar(int codigoUsuario, int codigoLivro) {
		BibliotecaFachada.obterInstancia().realizarDevolucao(codigoUsuario, codigoLivro);
	}

	@Override
	public void executar(int codigo1) {
		// TODO Auto-generated method stub

	}

}
