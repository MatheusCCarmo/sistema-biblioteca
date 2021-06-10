package br.ufba.trabalho.biblioteca;

public class RealizarConsultaUsuario implements Comando {

	@Override
	public void executar(int codigoUsuario) {
		BibliotecaFachada.obterInstancia().realizarConsultaUsuario(codigoUsuario);
	}

	@Override
	public void executar(int codigo1, int codigo2) {
		// TODO Auto-generated method stub
		
	}

}
