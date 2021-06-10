package br.ufba.trabalho.biblioteca;

public class RealizarObservacao implements Comando {

	@Override
	public void executar(int codigoUsuario, int codigoLivro) {
		BibliotecaFachada.obterInstancia().realizarObservacao(codigoUsuario, codigoLivro);
	}

	@Override
	public void executar(int codigo1) {
		// TODO Auto-generated method stub

	}

}
