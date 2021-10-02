package it.SecretBook.Util;

import java.util.ArrayList;
import java.util.List;

import it.SecretBook.model.Product;

public class Utility {
	
	//controlla se un libro è  già presente in una lista
	public static boolean checkPresence(Long id, ArrayList<Product> list) {
		
		
		for(int i=0; i<list.size(); i++)
		{
			if(id==list.get(i).getIsbn())
			{
				return true;
			}
		}
		
		
		
		return false;
		
	}

}
//Untangled partizione: 2//Untangled partizione: 10