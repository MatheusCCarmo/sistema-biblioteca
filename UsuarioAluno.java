package br.ufba.trabalho.biblioteca;

import java.util.ArrayList;
//import java.util.Date;

public abstract class UsuarioAluno extends Usuario {
	private int limiteDeEmprestimos;

	public UsuarioAluno(int codigo, String nome) {
		super(codigo, nome);
		setReservas(new ArrayList<Reserva>());
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

		if (this.getLimiteDeEmprestimos() <= this.getEmprestimos().size()) {
			System.out
					.println("O usuário não pode fazer empréstimo pois atingiu o limite de empréstimos. Seu limite é: "
							+ this.getLimiteDeEmprestimos());
			return false;
		}

		for (Emprestimo emprestimo : this.getEmprestimos()) {
			if (emprestimo.getLivro().getCodigo() == codigoLivro) {
				System.out
						.println("O usuário não pode fazer este empréstimo pois já possui um empréstimo deste livro.");
				return false;
			}
		}

		int qntReservasLivro = dados.getLivroPorCodigo(codigoLivro).getReservas().size();
		int qntExemplaresLivro = dados.getLivroPorCodigo(codigoLivro).getExemplares().size();

		if (qntReservasLivro >= qntExemplaresLivro) {
			if (!super.estaReservado(codigoLivro)) {
				System.out.println(
						"O usuário não pode fazer empréstimo deste livro pois todos exemplares estão reservados.");
				return false;
			}
		}

		return true;
	}

	public int getLimiteDeEmprestimos() {
		return limiteDeEmprestimos;
	}

	public void setLimiteDeEmprestimos(int limiteDeEmprestimos) {
		this.limiteDeEmprestimos = limiteDeEmprestimos;
	}

}
