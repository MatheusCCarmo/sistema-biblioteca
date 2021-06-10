package br.ufba.trabalho.biblioteca;

public abstract class UsuarioSuperior extends Usuario implements Observer {
	private int vezesNotificado = 0;

	public UsuarioSuperior(int codigo, String nome) {
		super(codigo, nome);
	}

	public Object update(Livro livro) {
		setVezesNotificado(getVezesNotificado() + 1);
		return null;
	}

	public int getVezesNotificado() {
		return vezesNotificado;
	}

	public void setVezesNotificado(int vezesNotificado) {
		this.vezesNotificado = vezesNotificado;
	}
}
