package br.ufba.trabalho.biblioteca;

public class AlunoPosGraduacao extends UsuarioAluno {
	private int duracaoEmprestimoEmDias = 4;
	private int limiteDeEmprestimos = 4;

	public AlunoPosGraduacao(int codigo, String nome) {
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
