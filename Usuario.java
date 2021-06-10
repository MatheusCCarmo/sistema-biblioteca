package br.ufba.trabalho.biblioteca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Usuario {
	private int codigo;
	private String nome;
	private List<Emprestimo> historicoEmprestimos;
	private List<Emprestimo> emprestimos;
	private List<Reserva> historicoReservas;
	private List<Reserva> reservas;
	private int duracaoEmprestimoEmDias;
	private boolean isDevedor = false;
	private int limiteReservas = 3;

	public Usuario(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.emprestimos = new ArrayList<Emprestimo>();
		this.historicoEmprestimos = new ArrayList<Emprestimo>();
		this.reservas = new ArrayList<Reserva>();
		this.historicoReservas = new ArrayList<Reserva>();
	}

	void fazerReserva(int codigoLivro) {
		Livro livro = BaseDeDados.obterInstancia().getLivroPorCodigo(codigoLivro);

		if (!possoFazerReserva(codigoLivro)) {
			System.out.println("Sua reserva falhou.");
			return;
		}
		Reserva reserva = new Reserva(livro, this, new Date());

		this.getReservas().add(reserva);
		livro.adicionarReserva(reserva);

		System.out.println("Reserva feita com Sucesso! Usuário: " + this.getNome() + " | Livro: " + livro.getTitulo());
	}

	boolean possoFazerReserva(int codigoLivro) {
		Livro livro = BaseDeDados.obterInstancia().getLivroPorCodigo(codigoLivro);

		if (reservas.size() >= limiteReservas) {
			System.out.println("O usuário não pode ter mais do que " + this.limiteReservas + " reservas feitas."
					+ " Usuário: " + this.getNome() + " | Livro: " + livro.getTitulo());
			return false;
		}

		for (Reserva reserva : reservas) {
			if (reserva.getLivro().getCodigo() == codigoLivro) {
				System.out.println("O usuário não pode ter mais de uma reserva para o mesmo livro. Usuário: "
						+ this.getNome() + " | Livro: " + livro.getTitulo());
				return false;
			}
		}

		return true;
	}

	void fazerEmprestimo(int codigoLivro) {
		BaseDeDados dados = BaseDeDados.obterInstancia();
		Livro livro = dados.getLivroPorCodigo(codigoLivro);
		Emprestimo emprestimo = new Emprestimo(this, livro, new Date(), "Em Curso");
		int indexReserva = -1;

		this.getEmprestimos().add(emprestimo);

		for (Exemplar exemplar : dados.getExemplares()) {
			if (exemplar.getLivro().getCodigo() == codigoLivro && exemplar.getStatus().equals("Disponível")) {
				exemplar.setStatus("Emprestado");
				exemplar.setUsuario(this);;
				break;
			}
		}

		if (!estaReservado(codigoLivro)) {
			System.out.println(
					"Livro: " + dados.getLivroPorCodigo(codigoLivro).getTitulo() + ". Emprestado com sucesso!");
			return;
		}

		System.out.println("O usuário possui reserva para este livro.");

		for (int i = 0; i < this.getReservas().size(); i++) {
			if (this.getReservas().get(i).getLivro().getCodigo() == codigoLivro) {
				indexReserva = i;
			}
		}

		Reserva reserva = this.getReservas().remove(indexReserva);
		this.getHistoricoReservas().add(reserva);

		livro.getReservas().remove(reserva);

		System.out.println("Livro: " + dados.getLivroPorCodigo(codigoLivro).getTitulo() + ". Emprestado com sucesso!");
	}

	boolean possoFazerEmprestimo(int codigoLivro) {
		return true;
	}

	boolean estaReservado(int codigoLivro) {
		for (Reserva reserva : this.getReservas()) {
			if (reserva.getLivro().getCodigo() == codigoLivro) {
				return true;
			}
		}
		return false;
	}

	void fazerDevolucao(int codigoLivro) {
		BaseDeDados dados = BaseDeDados.obterInstancia();
		int indexEmprestimo = -1;

		if (!possoFazerDevolucao(codigoLivro)) {
			System.out.println("Sua devolução falhou.");
			return;
		}

		for (Exemplar exemplar : dados.getExemplares()) {
			if (exemplar.getLivro().getCodigo() == codigoLivro && exemplar.getStatus().equals("Emprestado")) {
				exemplar.setStatus("Disponível");
				break;
			}
		}

		for (int i = 0; i < this.getEmprestimos().size(); i++) {
			if (this.getEmprestimos().get(i).getLivro().getCodigo() == codigoLivro) {
				indexEmprestimo = i;
			}
		}

		Emprestimo emprestimo = this.getEmprestimos().remove(indexEmprestimo);
		emprestimo.setStatus("Finalizado");
		this.getHistoricoEmprestimos().add(emprestimo);

		System.out.println("Livro: " + dados.getLivroPorCodigo(codigoLivro).getTitulo() + ". Devolvido com sucesso!");
	}

	boolean possoFazerDevolucao(int codigoLivro) {
		for (Emprestimo emprestimo : this.getEmprestimos()) {
			if (emprestimo.getLivro().getCodigo() == codigoLivro)
				return true;
		}
		System.out.println("Não há empréstimo em aberto deste livro para este usuário.");
		return false;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public boolean isDevedor() {
		return isDevedor;
	}

	public void setDevedor(boolean isDevedor) {
		this.isDevedor = isDevedor;
	}

	public int getDuracaoEmprestimoEmDias() {
		return duracaoEmprestimoEmDias;
	}

	public void setDuracaoEmprestimoEmDias(int duracaoEmprestimoEmDias) {
		this.duracaoEmprestimoEmDias = duracaoEmprestimoEmDias;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public int getLimiteReservas() {
		return limiteReservas;
	}

	public void setLimiteReservas(int limiteReservas) {
		this.limiteReservas = limiteReservas;
	}

	public List<Reserva> getHistoricoReservas() {
		return historicoReservas;
	}

	public void setHistoricoReservas(List<Reserva> historicoReservas) {
		this.historicoReservas = historicoReservas;
	}

	public List<Emprestimo> getHistoricoEmprestimos() {
		return historicoEmprestimos;
	}

	public void setHistoricoEmprestimos(List<Emprestimo> historicoEmprestimos) {
		this.historicoEmprestimos = historicoEmprestimos;
	}

}
