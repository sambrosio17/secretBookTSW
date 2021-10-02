package it.SecretBook.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.SecretBook.model.Model;
import it.SecretBook.model.Product;

public class ProductModel implements Model {

	private static final String TABLE_NAME = "prodotto";
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

	public synchronized void doSave(Product product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModel.TABLE_NAME
				+ " (isbn, nome, descrizione, prezzo, iva, qt, categoria,special) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setLong(1, product.getIsbn());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getDescrizione());
			preparedStatement.setDouble(4, product.getPrezzo());
			preparedStatement.setDouble(5, product.getIva());
			preparedStatement.setInt(6, product.getQt());
			preparedStatement.setString(7, product.getCategoria());
			preparedStatement.setString(8, product.getSpecial());

			preparedStatement.executeUpdate();
			
			this.uploadPhoto(product.getIsbn(), product.getFoto());
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
	}

	public synchronized Product doRetrieveByKey(long code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Product bean = null;

		String selectSQL = "SELECT * FROM " + ProductModel.TABLE_NAME + " WHERE ISBN = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setLong(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean=new Product();
				bean.setIsbn(rs.getLong("isbn"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setQt(rs.getInt("qt"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setFoto(this.load(rs.getLong("isbn")));
				bean.setSpecial(rs.getString("special"));
				
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
		return bean;
	}
	
	public synchronized Product doRetrieveRandom() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Product bean = null;

		String selectSQL = "SELECT * FROM " + ProductModel.TABLE_NAME + " ORDER BY RAND() LIMIT 1";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				bean=new Product();
				bean.setIsbn(rs.getLong("isbn"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setQt(rs.getInt("qt"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setFoto(this.load(rs.getLong("isbn")));
				bean.setSpecial(rs.getString("special"));
				
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
		return bean;
	}

	public synchronized boolean doDelete(long code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModel.TABLE_NAME + " WHERE ISBN = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setLong(1, code);

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

	public synchronized Collection<Product> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Product> products = new LinkedList<Product>();
		String selectSQL;

		if(order!=null && order.equalsIgnoreCase("home"))
		{
			selectSQL="SELECT * FROM " + ProductModel.TABLE_NAME+" WHERE qt>0";
			System.out.println("sto nella fottuta home");
		}
		else
			selectSQL="SELECT * FROM " + ProductModel.TABLE_NAME;

		/*if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}*/

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Product bean = new Product();

				bean.setIsbn(rs.getLong("isbn"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setQt(rs.getInt("qt"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setFoto(this.load(rs.getLong("isbn")));
				bean.setSpecial(rs.getString("special"));
				products.add(bean);
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
		return products;
	}
	
	public synchronized Collection<Product> doRetrieveFromSearch(String search) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Product> products = new LinkedList<Product>();
		String selectSQL="SELECT * FROM " + ProductModel.TABLE_NAME +" WHERE nome LIKE?";

		String sr="%"+search+"%";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, sr);
			
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Product bean = new Product();

				bean.setIsbn(rs.getLong("isbn"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setQt(rs.getInt("qt"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setFoto(this.load(rs.getLong("isbn")));
				bean.setSpecial(rs.getString("special"));
				products.add(bean);
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
		return products;
	}
	
	
	public Collection<Product> doRetrieveByCategory(String category) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		Collection<Product> products= new LinkedList<Product>();
		
		String sql="SELECT * FROM "+ TABLE_NAME +" WHERE categoria= ? AND qt>0";
		
		try {
			connection= ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, category);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			while (rs.next()) {
				Product bean = new Product();

				bean.setIsbn(rs.getLong("isbn"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setIva(rs.getDouble("iva"));
				bean.setQt(rs.getInt("qt"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setFoto(this.load(rs.getLong("isbn")));
				bean.setSpecial(rs.getString("special"));
				products.add(bean);
			}
			
			
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return products;
		
	}

	public synchronized byte[]load(long id) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		ResultSet rs=null;
		
		String sql="SELECT foto FROM "+TABLE_NAME+" where isbn= ?";
		
		byte[] foto=null;
		
		try {
			connection=ds.getConnection();
			
			stat=connection.prepareStatement(sql);
			stat.setLong(1, id);
			
			rs=stat.executeQuery();
			
			if(rs.next())
			{
				foto=rs.getBytes("foto");
			}
			
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
		
		return foto;
	}
	
	
	public synchronized void updatePhoto(long id, String photo) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		
		String sql="UPDATE "+TABLE_NAME+" SET foto = ? where isbn = ?";
		
		try {
			connection=ds.getConnection();
			
			stat=connection.prepareStatement(sql);
			stat.setLong(2, id);
			
			File file=new File(photo);
			
			try {
				FileInputStream fis=new FileInputStream(file);
				stat.setBinaryStream(1,fis,fis.available());
				
				stat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("Error Updating Photo: "+e.getMessage());
			}
			

		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
		
		
	}
	
	public synchronized void doUpdate(Product prod, long id) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		
		String sql="UPDATE "+TABLE_NAME+" SET isbn=?, nome=?, descrizione=?, prezzo=?, iva=?, qt=?, categoria=?, special=? WHERE isbn=?;";

		try {
			connection=ds.getConnection();
			
			stat=connection.prepareStatement(sql);
			
			stat.setLong(1, prod.getIsbn());
			stat.setString(2, prod.getNome());
			stat.setString(3, prod.getDescrizione());
			stat.setDouble(4, prod.getPrezzo());
			stat.setDouble(5, prod.getIva());
			stat.setInt(6, prod.getQt());
			stat.setString(7, prod.getCategoria());
			stat.setString(8, prod.getSpecial());
			stat.setLong(9, id);

			stat.executeUpdate();
			
			this.uploadPhoto(id, prod.getFoto());
			
			
			
			stat.executeUpdate();
			

		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
	}
	
	
	public synchronized void uploadPhoto(long id, byte[] foto) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		
		String sql="UPDATE "+TABLE_NAME+" SET foto = ? where isbn = ?";
		
		try {
			connection=ds.getConnection();
			
			stat=connection.prepareStatement(sql);
			stat.setLong(2, id);
			
			stat.setBytes(1, foto);
			
			stat.executeUpdate();
			

		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
		
		
	}

}
//Untangled partizione: 0//Untangled partizione: 9