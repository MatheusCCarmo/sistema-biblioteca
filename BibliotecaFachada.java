package br.ufba.trabalho.biblioteca;

public class BibliotecaFachada {
	private static BibliotecaFachada instancia;

	private BibliotecaFachada() {
	}

	public static BibliotecaFachada obterInstancia() {
		if (instancia == null) {
			instancia = new BibliotecaFachada();
		}
		return instancia;
	}

	public void realizarReserva(int codigoUsuario, int codigoLivro) {
		System.out.println("Iniciando procedimento de reserva...");

		Usuario usuario = BaseDeDados.obterInstancia().getUsuarioPorCodigo(codigoUsuario);

		if (usuario != null) {
			usuario.fazerReserva(codigoLivro);
			return;
		}

	}

	public void realizarEmprestimo(int codigoUsuario, int codigoLivro) {
		System.out.println("Iniciando procedimento de emprestimo");

		Usuario usuario = BaseDeDados.obterInstancia().getUsuarioPorCodigo(codigoUsuario);

		if (usuario != null) {
			usuario.fazerEmprestimo(codigoLivro);
			return;
		}

	}

	public void realizarDevolucao(int codigoUsuario, int codigoLivro) {
		System.out.println("Iniciando procedimento de devolucao...");

		Usuario usuario = BaseDeDados.obterInstancia().getUsuarioPorCodigo(codigoUsuario);

		if (usuario != null) {
			usuario.fazerDevolucao(codigoLivro);
			return;
		}

	}

	public void realizarObservacao(int codigoUsuario, int codigoLivro) {
		System.out.println("Iniciando procedimento de observação...");

		Usuario usuario = BaseDeDados.obterInstancia().getUsuarioPorCodigo(codigoUsuario);
		Livro livro = BaseDeDados.obterInstancia().getLivroPorCodigo(codigoLivro);

		if (usuario != null && livro != null) {
			livro.registerObserver((Observer) usuario);
			System.out.println("Observador registrado com sucesso!");
			return;
		}
		
		System.out.println("Não foi possível registrar o observador.");
	}

	public void realizarConsultaLivro(int codigoLivro) {
		System.out.println("Iniciando consulta de dados do livro...");

		BaseDeDados dados = BaseDeDados.obterInstancia();
		Livro livro = dados.getLivroPorCodigo(codigoLivro);
		Usuario usuario;

		System.out.println(
				"\n-Título: " + livro.getTitulo() + "\n-Quantidade de Reservas: " + livro.getReservas().size());
		if (livro.getReservas().size() > 0) {
			System.out.println("-Reservas:");
			for (Reserva reserva : livro.getReservas()) {
				System.out.println("--Usuário: " + reserva.getUsuario().getNome());
			}
		}

		System.out.println("\n-Exemplares: ");
		for (Exemplar exemplar : livro.getExemplares()) {
			System.out.println(
					"--Código do exemplar: " + exemplar.getCodigoExemplar() + "\n--Status: " + exemplar.getStatus());
			if (exemplar.getStatus().equalsIgnoreCase("Emprestado")) {
				usuario = exemplar.getUsuario();
				System.out.println("--Usuário em posse: " + usuario.getNome());

				// percorre os emprestimos do usuario para achar qual corresponde livro do
				// exemplar
				for (Emprestimo emprestimo : usuario.getEmprestimos()) {
					if (emprestimo.getLivro().getCodigo() == exemplar.getLivro().getCodigo()) {
						System.out.println("--Data do emprestimo: " + emprestimo.getData() + "\n--Data de devolução: "
								+ emprestimo.getDataLimite());
					}
				}
			}
		}

	}

	public void realizarConsultaUsuario(int codigoUsuario) {
		System.out.println("Iniciando consulta de dados do usuário...");

		BaseDeDados dados = BaseDeDados.obterInstancia();
		Usuario usuario = dados.getUsuarioPorCodigo(codigoUsuario);

		System.out.println("\nUsuário: " + usuario.getNome());

		System.out.println("\n-Empréstimos correntes:");
		for (Emprestimo emprestimo : usuario.getEmprestimos()) {
			System.out.println("\n--Título: " + emprestimo.getLivro().getTitulo() + "\n--Data do empréstimo: "
					+ emprestimo.getData() + "\n--Status: " + emprestimo.getStatus() + "\n--Data limite: "
					+ emprestimo.getDataLimite());
		}
		System.out.println("\n-Empréstimos passados:");
		for (Emprestimo emprestimo : usuario.getHistoricoEmprestimos()) {
			System.out.println("\n--Título: " + emprestimo.getLivro().getTitulo() + "\n--Data do empréstimo: "
					+ emprestimo.getData() + "\n--Status: " + emprestimo.getStatus() + "\n--Data da devolução: "
					+ emprestimo.getDataDevolucao());
		}
		System.out.println("\n-Reservas correntes:");
		for (Reserva reserva : usuario.getReservas()) {
			System.out.println(
					"\n--Título: " + reserva.getLivro().getTitulo() + "\n--Data do empréstimo: " + reserva.getData());
		}
		System.out.println("\n-Reservas passadas:");
		for (Reserva reserva : usuario.getHistoricoReservas()) {
			System.out.println(
					"\n--Título: " + reserva.getLivro().getTitulo() + "\n--Data do empréstimo: " + reserva.getData());
		}

	}

	public void realizarConsultaSuperior(int codigoUsuario) {
		System.out.println("Iniciando consulta de notificações do professor...");
		BaseDeDados dados = BaseDeDados.obterInstancia();
		UsuarioSuperior usuario = (UsuarioSuperior) dados.getUsuarioPorCodigo(codigoUsuario);

		System.out.println("\nQuantidade de vezes notificado: " + usuario.getVezesNotificado());
	}

}
