package br.ufba.trabalho.biblioteca;

public interface Subject {
	public void registerObserver(Observer observer);

	public void removeObserver(Observer observer);

	public void notifyObservers();
}
