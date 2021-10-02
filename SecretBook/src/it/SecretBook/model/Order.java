package it.SecretBook.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
	
	
	private ArrayList<CartItem> list; 
	private int orderId;
	private String status;
	private LocalDate data;
	private Double prezzoTotale;
	private String user;
	
	
	public Order(Cart cart)
	{
		list=cart.getItems();
		orderId=-1;
		status="appena creato";
		data=LocalDate.now();
		prezzoTotale=0.0;
		user="";
	}
	
	public Order()
	{
		list=new ArrayList<CartItem>();
		orderId=-1;
		status="";
		data=LocalDate.MIN;
		prezzoTotale=0.0;
		user="";
		
	}
	
	public Double getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(Double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public ArrayList<CartItem> getList() {
		return list;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public String getStatus() {
		return status;
	}
	
	
	public void setList(ArrayList<CartItem> list) {
		this.list = list;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
//Untangled partizione: 18//Untangled partizione: 6