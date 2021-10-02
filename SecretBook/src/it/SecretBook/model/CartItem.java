package it.SecretBook.model;

public class CartItem {
	
	private Product prod;
	private int quantity;
	
	public CartItem()
	{
		
	}
	public CartItem(Product prod)
	{
		this.prod=prod;
		quantity=1;
	}
	
	public Product getProd() {
		return prod;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void updateQuantity(int qnt)
	{
		this.quantity+=qnt;
	}
	public void setProd(Product prod) {
		this.prod = prod;
	}
	
	 public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 
	 public void decreaseQuantity()
	 {
		 quantity--;
	 }
	 
	 public double getIva()
	 {
		 return prod.getIva();
	 }
	 public void increaseQuantity() {
		 
		 quantity++;
	 }
	 
	 public void resetQuantity()
	 {
		 quantity=0;
	 }
	 
	 public long getISBN()
	 {
		 return prod.getIsbn();
	 }
	 
	 public double getUnitCost()
	 {
		 return prod.getIva()+prod.getPrezzo();
	 }
	 
	 public double getPrice()
	 {
		 return prod.getPrezzo();
	 }
	 public double getTotalCost()
	 {
		 return this.getUnitCost()*this.getQuantity();
	 }
	 
	 public String toString()
	 {
		 return prod.toString()+" "+quantity+" "+getTotalCost();
	 }
}
//Untangled partizione: 16