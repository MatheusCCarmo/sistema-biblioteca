package br.ufba.trabalho.biblioteca;

import java.util.Calendar;
import java.util.Date;

public class Emprestimo {
	private Livro livro;
	private Date data;
	private Date dataLimite;
	private Date dataDevolucao;
	private Usuario usuario;
	private String status;
	private Calendar c = Calendar.getInstance();

	public Emprestimo(Usuario usuario, Livro livro, Date data, String status) {
		this.usuario = usuario;
		this.livro = livro;
		this.data = data;
		this.status = status;
		c.setTime(data);
		c.add(Calendar.DATE, usuario.getDuracaoEmprestimoEmDias());
		this.dataLimite = c.getTime();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
