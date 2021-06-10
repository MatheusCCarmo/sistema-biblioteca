package br.ufba.trabalho.biblioteca;

//import java.util.Date;

public class Professor extends UsuarioSuperior {
	int duracaoEmprestimoEmDias = 7;

	public Professor(int codigo, String nome) {
		super(codigo, nome);

	}

	@Override
	void fazerEmprestimo(int codigoLivro) {
		if (!possoFazerEmprestimo(codigoLivro)) {
			System.out.println("Seu empréstimo falhou.");
			return;
		}

		super.fazerEmprestimo(codigoLivro);
	}

	@Override
	boolean possoFazerEmprestimo(int codigoLivro) {
		BaseDeDados dados = BaseDeDados.obterInstancia();

		if (!dados.existeExemplarDoLivro(codigoLivro)) {
			System.out.println("Não há exemplares deste livro na biblioteca.");
			return false;
		}

		if (!dados.existeExemplarDisponivel(codigoLivro)) {
			System.out.println("Não há exemplares deste livro disponíveis no momento.");
			return false;
		}

		if (dados.checarUsuarioDevedor(this.getCodigo())) {
			System.out.println("O usuário não pode fazer empréstimo pois está devendo livro em atraso.");
			return false;
		}

		return true;
	}

}
