package br.ufba.trabalho.biblioteca;

public class RealizarConsultaSuperior implements Comando {

	@Override
	public void executar(int codigoUsuario) {
		BibliotecaFachada.obterInstancia().realizarConsultaSuperior(codigoUsuario);
	}

	@Override
	public void executar(int codigo1, int codigo2) {
		// TODO Auto-generated method stub

	}

}
