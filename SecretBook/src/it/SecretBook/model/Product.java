//Product è la classe prodotto prodotto
package it.SecretBook.model;

public class Product {
	
	private long  isbn;
	private String nome;
	private String descrizione;
	private double prezzo;
	private double iva;
	private int qt;
	private String categoria;
	private byte[] foto;
	private String special;
	
	public Product() {
		isbn= 0;
		nome="";
		descrizione="";
		prezzo= 0.0;
		iva= 0.0;
		qt= 0;
		categoria="";
		foto=null;
		this.special="";
	}

	public Product(long isbn, String nome, String descrizione, double prezzo, double iva, int qt, String categoria, byte[] foto, String special) {
		this.isbn = isbn;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.iva = iva;
		this.qt = qt;
		this.categoria = categoria;
		this.foto=foto;
		this.special=special;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}
	
	
	public double getCost()
	{
		return this.prezzo+this.iva;
	}

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
		this.qt = qt;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String toString()
	{
		return isbn+" "+nome;
	}
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public String getSpecial() {
		return special;
	}
	
	public void setSpecial(String special) {
		this.special = special;
	}
}
