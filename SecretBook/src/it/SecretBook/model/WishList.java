package it.SecretBook.model;

import java.util.ArrayList;

public class WishList {

	private int id;
	private User utente;
	private ArrayList<Product> prodotti;
	
	
	public WishList(int id, User utente, ArrayList<Product> prodotti) {
		this.id = id;
		this.utente = utente;
		this.prodotti= prodotti;
	}


	public WishList() {
		
		id=-1;
		utente=null;
		utente=null;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public User getUtente() {
		return utente;
	}


	public void setUtente(User utente) {
		this.utente = utente;
	}


	public ArrayList<Product> getProdotti() {
		return prodotti;
	}


	public void setProdotti(ArrayList<Product> prodotti) {
		this.prodotti = prodotti;
	}
	
	
}
