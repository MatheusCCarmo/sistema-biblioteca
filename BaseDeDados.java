package br.ufba.trabalho.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseDeDados {
	private static BaseDeDados instancia;
	private List<Usuario> usuarios;
	private List<Livro> livros;
	private List<Exemplar> exemplares;

	private BaseDeDados() {
	}

	public static BaseDeDados obterInstancia() {
		if (instancia == null) {
			instancia = new BaseDeDados();
			instancia.carregarDadosIniciais();
		}
		return instancia;
	}

	public void carregarDadosIniciais() {
		usuarios = new ArrayList<Usuario>();
		livros = new ArrayList<Livro>();
		exemplares = new ArrayList<Exemplar>();

		usuarios.add(new AlunoGraduacao(123, "João da Silva"));
		usuarios.add(new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues"));
		usuarios.add(new AlunoGraduacao(789, "Pedro Paulo"));
		usuarios.add(new Professor(100, "Carlos Lucena"));

		Livro livro100 = new Livro(100, "Engenharia de Software", "AddisonWesley", Arrays.asList("Ian Sommervile"), "6ª", 2000);
		Livro livro101 = new Livro(101, "UML - Guia do Usuário", "Campus",
				Arrays.asList("Grady Booch", "James Rumbaugh", "Ivar Jacobson"), "7ª", 2000);
		Livro livro200 = new Livro(200, "Code Complete", "Microsoft", Arrays.asList("Steve McConnell"), "2ª", 2014);
		Livro livro201 = new Livro(201, "Agile Software Development Principles, Patterns and Practices", "Prentice Hall",
				Arrays.asList("Robert Martin"), "1ª", 2002);
		Livro livro300 = new Livro(300, "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional",
				Arrays.asList("Martin Fowler"), "1ª", 1999);
		Livro livro301 = new Livro(301, "Software Metrics: A Rigorous and Practical Approach", "CRC Press",
				Arrays.asList("Norman Fenton", "James Bieman"), "3ª", 2014);
		Livro livro400 = new Livro(400, "Design Patterns: Elements of Reusable Object-Oriented Software",
				"Addison-Wesley Professional",
				Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"), "1ª", 1994);
		Livro livro401 = new Livro(401, "UML Distilled: A Brief Guide to the Standard Object Modeling Language",
				"Addison-Wesley Professional", Arrays.asList("Martin Fowler"), "3ª", 2003);
		
		livros.add(livro100);
		livros.add(livro101);
		livros.add(livro200);
		livros.add(livro201);
		livros.add(livro300);
		livros.add(livro301);
		livros.add(livro400);
		livros.add(livro401);

		exemplares.add(new Exemplar(livro100, 1, "Disponível"));
		exemplares.add(new Exemplar(livro100, 2, "Disponível"));
		exemplares.add(new Exemplar(livro101, 3, "Disponível"));
		exemplares.add(new Exemplar(livro200, 4, "Disponível"));
		exemplares.add(new Exemplar(livro201, 5, "Disponível"));
		exemplares.add(new Exemplar(livro300, 6, "Disponível"));
		exemplares.add(new Exemplar(livro300, 7, "Disponível"));
		exemplares.add(new Exemplar(livro400, 8, "Disponível"));
		exemplares.add(new Exemplar(livro400, 9, "Disponível"));

		for (Livro livro : livros) {
			for (Exemplar exemplar : exemplares) {
				if (livro.getCodigo() == exemplar.getLivro().getCodigo()) {
					livro.getExemplares().add(exemplar);
				}
			}
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public Usuario getUsuarioPorCodigo(int codigoUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getCodigo() == codigoUsuario) {
				return usuario;
			}
		}
		System.out.println("Usuario não encontrado.");
		return null;
	}

	public Livro getLivroPorCodigo(int codigoLivro) {
		for (Livro livro : livros) {
			if (livro.getCodigo() == codigoLivro) {
				return livro;
			}
		}
		System.out.println("Livro não encontrado.");
		return null;
	}

	public boolean existeExemplarDoLivro(int codigoLivro) {
		Livro livro = getLivroPorCodigo(codigoLivro);
		boolean existeExemplar = !livro.getExemplares().isEmpty();
		return existeExemplar;
	}

	public boolean existeExemplarDisponivel(int codigoLivro) {
		Livro livro = getLivroPorCodigo(codigoLivro);
		if (livro.getExemplares().isEmpty()) {
			System.out.println("Não há exemplares deste livro na biblioteca.");
			return false;
		}
		for (Exemplar exemplar : livro.getExemplares()) {
			if (exemplar.getStatus().equals("Disponível")) {
				System.out.println("Existe pelo menos um exemplar disponível.");
				return true;
			}
		}
		return false;
	}

	public boolean checarUsuarioDevedor(int codigoUsuario) {
		Usuario usuario = getUsuarioPorCodigo(codigoUsuario);
		return usuario.isDevedor();
	}

}
