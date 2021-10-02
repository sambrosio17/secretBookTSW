package it.SecretBook.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import it.SecretBook.model.*;

public class OrderModel {
	
	private static final String ORDINE = "ordine";
	private static final String COMPOSIZIONE ="composizione";
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


	public synchronized void doSave(Cart cart, String userId) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement insertStat=null;
		Statement stat=null;
		PreparedStatement checkQt=null;
		
		//creo un ordine nel database associato all'utente
		//aggiungo all'ordine i prodotti 
		
		String sql="INSERT INTO "+ORDINE+" (stato, utente,data) VALUES (?,?,?)";
		String getId="SELECT * FROM "+ORDINE+" order by id desc limit 1";
		String insertItems="INSERT INTO "+COMPOSIZIONE+" (qt, prezzo, iva, prodotto, ordine, prezzoTotale) VALUES (?,?,?,?,?,?);";
		String updateCost="UPDATE "+ORDINE+" SET prezzoTotale = ? WHERE id=?;";
		String updateQuantity="UPDATE prodotto SET "+" qt=qt-? WHERE isbn=?;";
		
		try {
			
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "just created");
			preparedStatement.setString(2, userId);
			preparedStatement.setDate(3,Date.valueOf(LocalDate.now()));
			preparedStatement.executeUpdate();
			
			checkQt=connection.prepareStatement(updateQuantity);
			//connection.commit();
			
			stat=connection.createStatement();
			
			ResultSet rs= stat.executeQuery(getId);
			int id=-1;
			if(!rs.next())
			{
				System.out.println("Order Model says: no order found, id is "+id);
				return;
			}
			id=rs.getInt("id");
			
			insertStat=connection.prepareStatement(insertItems);
			for(int i=0; i<cart.getItems().size(); i++)
			{
				insertStat.setInt(1, cart.getItems().get(i).getQuantity());
				insertStat.setDouble(2, cart.getItems().get(i).getPrice());
				insertStat.setDouble(3, cart.getItems().get(i).getIva());
				insertStat.setLong(4, cart.getItems().get(i).getISBN());
				insertStat.setInt(5, id);
				insertStat.setDouble(6, cart.getItems().get(i).getTotalCost());
				insertStat.executeUpdate();
				checkQt.setInt(1,cart.getItems().get(i).getQuantity());
				checkQt.setLong(2,cart.getItems().get(i).getISBN());
				checkQt.executeUpdate();
				//connection.commit();
				
			}
			
			preparedStatement.close();
			
			preparedStatement=connection.prepareStatement(updateCost);
			preparedStatement.setDouble(1, cart.getTotalCost());
			preparedStatement.setInt(2, id);
			
			preparedStatement.executeUpdate();
			
			
			
			
		} finally {
			try {
				if (preparedStatement != null && stat!=null && insertStat!=null)
				{
					preparedStatement.close();
					stat.close();
					insertStat.close();
				}
			} finally {
				if(connection!=null)
					connection.close();
			}
		}

	}


	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ORDINE + " WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	public synchronized Order doRetrieveByKey(int code, String user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Order order=new Order();
		CartItem item=new CartItem();
		Product prod=new Product();
		ProductModel mod=new ProductModel();
		ArrayList<CartItem> list= new ArrayList<CartItem>();
		

		String selectSQL = "SELECT ordine, utente, stato, prodotto, data, qt, prezzo, ordine.prezzoTotale, iva FROM ordine JOIN composizione ON composizione.ordine=ordine.id WHERE ordine=? AND utente=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.setString(2, user);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			order.setOrderId(code);
			
			while(rs.next())
			{
				prod=new Product();
				item=new CartItem();
				Long isbn=rs.getLong("prodotto");
				prod= mod.doRetrieveByKey(isbn);
				prod.setIva(rs.getDouble("iva"));
				prod.setPrezzo(rs.getDouble("prezzo"));
				item.setProd(prod);
				item.setQuantity(rs.getInt("qt"));
				list.add(item);	
				order.setUser(user);
				order.setStatus(rs.getString("stato"));
				order.setData(rs.getDate("data").toLocalDate());
				order.setPrezzoTotale(rs.getDouble("prezzoTotale"));
			}
			order.setList(list);

			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return order;
	}
	
	public synchronized Order doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Order order=new Order();
		CartItem item=new CartItem();
		Product prod=new Product();
		ProductModel mod=new ProductModel();
		ArrayList<CartItem> list= new ArrayList<CartItem>();
		

		String selectSQL = "SELECT ordine, utente, stato, prodotto, data, qt, prezzo, ordine.prezzoTotale, iva FROM ordine JOIN composizione ON composizione.ordine=ordine.id WHERE ordine=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			order.setOrderId(code);
			
			while(rs.next())
			{
				prod=new Product();
				item=new CartItem();
				Long isbn=rs.getLong("prodotto");
				prod= mod.doRetrieveByKey(isbn);
				prod.setIva(rs.getDouble("iva"));
				prod.setPrezzo(rs.getDouble("prezzo"));
				item.setProd(prod);
				item.setQuantity(rs.getInt("qt"));
				list.add(item);	
				order.setUser(rs.getString("utente"));
				order.setStatus(rs.getString("stato"));
				order.setData(rs.getDate("data").toLocalDate());
				order.setPrezzoTotale(rs.getDouble("prezzoTotale"));
			}
			order.setList(list);

			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return order;
	}

	public synchronized Collection<Order> doRetrieveAll(String user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql="SELECT id FROM "+ORDINE+ " WHERE utente=?;";
		
		ArrayList<Order> orders=new ArrayList<Order>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				int id=rs.getInt("id");
				Order o= doRetrieveByKey(id, user);
				orders.add(o);
			}
			
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return orders;
	}
	
public synchronized Collection<Order> doRetrieveAll() throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql="SELECT id, utente FROM "+ORDINE+";";
		
		ArrayList<Order> orders=new ArrayList<Order>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				System.out.println("ordine trovato");
				int id=rs.getInt("id");
				Order o= doRetrieveByKey(id, rs.getString("utente"));
				orders.add(o);
			}
			
			

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return orders;
	}

public synchronized Collection<Order> doRetrieveBetweenDates(LocalDate start, LocalDate end) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	String sql="SELECT * FROM "+ORDINE+ " WHERE ordine.data BETWEEN ? AND ?";
	
	ArrayList<Order> orders=new ArrayList<Order>();
	
	try {
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1, Date.valueOf(start));
		preparedStatement.setDate(2, Date.valueOf(end));
		
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next())
		{
			int id=rs.getInt("id");
			Order o= doRetrieveByKey(id, rs.getString("utente"));
			orders.add(o);
		}
		
		

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if(connection!=null)
				connection.close();
		}
	}
	return orders;
}

}
