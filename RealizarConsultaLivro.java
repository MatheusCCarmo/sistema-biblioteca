package br.ufba.trabalho.biblioteca;

public class RealizarConsultaLivro implements Comando {

	@Override
	public void executar(int codigoLivro) {
		BibliotecaFachada.obterInstancia().realizarConsultaLivro(codigoLivro);
	}

	@Override
	public void executar(int codigo1, int codigo2) {
		// TODO Auto-generated method stub

	}

}
