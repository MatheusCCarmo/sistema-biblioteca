package br.ufba.trabalho.biblioteca;

public class Exemplar {
	private int codigoExemplar;
	private Livro livro;
	private String status;
	private Usuario usuario;

	public Exemplar(Livro livro, int codigoExemplar, String status) {
		super();
		this.livro = livro;
		this.codigoExemplar = codigoExemplar;
		this.status = status;
	}

	public int getCodigoExemplar() {
		return codigoExemplar;
	}

	public void setCodigoExemplar(int codigoExemplar) {
		this.codigoExemplar = codigoExemplar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}
