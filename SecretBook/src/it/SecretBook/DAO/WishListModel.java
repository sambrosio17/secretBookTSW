package it.SecretBook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.SecretBook.model.Cart;
import it.SecretBook.model.Product;
import it.SecretBook.model.WishList;




public class WishListModel{
	
	UserModel uModel=new UserModel();
	
	private static final String LISTA = "listadesideri";
	private static final String COMPOSIZIONELISTA = "composizionelista";
	static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/secretbook");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}


	public synchronized void doSave(WishList lista) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		//creo una lista nel database
		
		String sql="INSERT INTO "+LISTA+" (utente) VALUES (?)";
		
		try {
			
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, lista.getUtente().getUsername());
			preparedStatement.executeUpdate();
			
			
		} finally {
			try {
				if (preparedStatement != null )
				{
					preparedStatement.close();
				}
			} finally {
				if(connection!=null)
					connection.close();
			}
		}

	}




public synchronized void doAddProduct(WishList lista, long isbn) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	
	String sql="INSERT INTO "+COMPOSIZIONELISTA+" ( prodotto,lista) VALUES (?,?)";
	
	try {
		
		connection=ds.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setLong(1, isbn);
		preparedStatement.setInt(2,lista.getId());
		preparedStatement.executeUpdate();
		
		
	} finally {
		try {
			if (preparedStatement != null )
			{
				preparedStatement.close();
			}
		} finally {
			if(connection!=null)
				connection.close();
		}
	}

}
public synchronized void doDeleteProduct(WishList lista, long isbn) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	
	String sql="DELETE FROM "+COMPOSIZIONELISTA+" WHERE prodotto=? and lista=?;";
	
	try {
		
		connection=ds.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setLong(1, isbn);
		preparedStatement.setInt(2,lista.getId());
		preparedStatement.executeUpdate();
		
		
	} finally {
		try {
			if (preparedStatement != null )
			{
				preparedStatement.close();
			}
		} finally {
			if(connection!=null)
				connection.close();
		}
	}

}

public synchronized ArrayList<Product> doRetriveProducts(int idList) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	ProductModel pm=new ProductModel();

	
	String sql="SELECT prodotto FROM "+LISTA+" JOIN "+COMPOSIZIONELISTA+" ON composizionelista.lista=listadesideri.id WHERE listadesideri.id=? ";
	 
	ArrayList<Product> prodotti= new ArrayList<Product>(); 
	
	try {
		
		connection=ds.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, idList);
		
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next())
		{
			Long id=rs.getLong("prodotto");
			prodotti.add(pm.doRetrieveByKey(id));
			
		
		}
		
		
	} finally {
		try {
			if (preparedStatement != null )
			{
				preparedStatement.close();
			}
		} finally {
			if(connection!=null)
				connection.close();
		}
	}

	return prodotti;
}

public synchronized boolean doCheckList(String username) throws SQLException{ 
	//QUESTA FUNZIONE CONTROLLA SE L'UTENTE HA GIA' UNA LISTA 
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String sql="SELECT utente FROM listadesideri WHERE utente=?";
try {
		
		connection=ds.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		
		ResultSet rs=preparedStatement.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		
		
	} finally {
		try {
			if (preparedStatement != null )
			{
				preparedStatement.close();
			}
		} finally {
			if(connection!=null)
				connection.close();
		}
	}
return false;
}




public synchronized WishList doRetrieveByKey(String username) throws SQLException{ 
	//QUESTA FUNZIONE CONTROLLA SE L'UTENTE HA GIA' UNA LISTA 
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	WishList w=null;
	String sql="SELECT id FROM "+LISTA+" WHERE utente=?";
try {
		
		connection=ds.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setString(1, username);
		
		ResultSet rs=preparedStatement.executeQuery();
		
		w=new WishList();
		
		while(rs.next())
		{
			w.setId(rs.getInt("id"));
			w.setUtente(uModel.doRetrieveByUsername(username));
			w.setProdotti(this.doRetriveProducts(rs.getInt("id")));
	
		}
		
		
	} finally {
		try {
			if (preparedStatement != null )
			{
				preparedStatement.close();
			}
		} finally {
			if(connection!=null)
				connection.close();
		}
	}
return w;
}

}

//Untangled partizione: 1//Untangled partizione: 9