package com.example.business;

import java.util.ArrayList;

public class Notas {
	private ArrayList<Nota> notas;
	public Notas() {
		notas = new ArrayList<Nota>();
	}
	
	public void addNota(Nota n) {
		notas.add(n);
	}
	
	public ArrayList<Nota> getNotas() {
		return notas;
	}
	
	public void removeNota(Nota n) {
		notas.remove(n);
	}
}
