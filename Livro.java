package br.ufba.trabalho.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Livro implements Subject {
	private int codigo;
	private String titulo;
	private String editora;
	private List<String> autores;
	private String edicao;
	private int anoDaPublicacao;
	private List<Exemplar> exemplares;
	private List<Reserva> reservas;
	private List<Observer> observers;

	public Livro(int codigo, String titulo, String editora, List<String> autores, String edicao, int anoDaPublicacao) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.editora = editora;
		this.autores = autores;
		this.edicao = edicao;
		this.anoDaPublicacao = anoDaPublicacao;
		this.exemplares = new ArrayList<Exemplar>();
		this.reservas = new ArrayList<Reserva>();
		this.observers = new ArrayList<Observer>();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public List<String> getAutores() {
		return autores;
	}

	public void setAutores(List<String> autores) {
		this.autores = autores;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getAnoDaPublicacao() {
		return anoDaPublicacao;
	}

	public void setAnoDaPublicacao(int anoDaPublicacao) {
		this.anoDaPublicacao = anoDaPublicacao;
	}

	public List<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void adicionarReserva(Reserva reserva) {
		reservas.add(reserva);
		if (reservas.size() > 2) {
			notifyObservers();
		}
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		int itemIndex = observers.indexOf(observer);
		if (itemIndex >= 0) {
			observers.remove(itemIndex);
		}

	}

	public void notifyObservers() {
		this.observers.forEach((e) -> e.update(this));
	}

}
