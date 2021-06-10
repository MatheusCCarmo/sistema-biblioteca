package br.ufba.trabalho.biblioteca;

public class AlunoGraduacao extends UsuarioAluno {
	private int duracaoEmprestimoEmDias = 3;
	private int limiteDeEmprestimos = 3;

	public AlunoGraduacao(int codigo, String nome) {
		super(codigo, nome);
	}

	@Override
	public int getLimiteDeEmprestimos() {
		return this.limiteDeEmprestimos;
	}

	@Override
	public int getDuracaoEmprestimoEmDias() {
		return this.duracaoEmprestimoEmDias;
	}
}
