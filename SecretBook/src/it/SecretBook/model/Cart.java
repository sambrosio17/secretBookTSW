package it.SecretBook.model;

import java.util.ArrayList;

public class Cart {
	
	private ArrayList<CartItem> list;
	
	public Cart()
	{
		list=new ArrayList<CartItem>();
	}
	
	public synchronized void addItem(CartItem item)
	{
			for(int i=0; i<list.size(); i++)
			{
				if(list.get(i).getISBN()==item.getISBN())
				{
					System.out.println("Cart says: item già presente");
					list.get(i).updateQuantity(item.getQuantity());
					System.out.println("cart says: incremento la quantità");
					return;
				}
			}
			System.out.println("Cart says: item non presente, aggiugo");
			list.add(item);
			
	}
	
	public synchronized void deleteItem(CartItem item)
	{
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i).getISBN()==item.getISBN())
			{
				list.remove(i);
				return;
			}
		}
	}
	
	public synchronized void setQuantity(CartItem item, int quantity)
	{
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i).getISBN()==item.getISBN())
			{
				list.get(i).setQuantity(quantity);
			}
		}
	}
	
	public synchronized CartItem getItem(long ISBN)
	{
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i).getISBN()==ISBN)
			{
				return list.get(i);
			}
		}
		return null;
	}
	
	public synchronized boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	public synchronized int size()
	{
		return list.size();
	}
	public synchronized ArrayList<CartItem> getItems()
	{
		return list;
	}
	
	public synchronized int getTotalQuantiy()
	{
		int tot=0;
		for(int i=0; i<list.size(); i++)
		{
			tot+=list.get(i).getQuantity();
		}
		return tot;
	}
	
	public synchronized double getTotalCost()
	{
		double tot=0;
		for(int i=0; i<list.size(); i++)
		{
			tot+=list.get(i).getTotalCost();
		}
		return tot;
	}

	public synchronized void deleteAll()
	{
		for(int i=0; i<list.size(); i++)
		{
			list.remove(i);
		}
	}
	
	public boolean contains(CartItem item)
	{
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i).getISBN()==item.getISBN())
			{
				return true;
			}
		}
		
		return false;
	}
}
//Untangled partizione: 15