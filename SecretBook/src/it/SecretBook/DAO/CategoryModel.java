package it.SecretBook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.SecretBook.model.Category;

public class CategoryModel {
	
	
	private static final String TABLE_NAME = "categoria";
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

	
	
	public Category doRetrieveByKey(String title) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="SELECT * FROM " +TABLE_NAME+" WHERE titolo=?";		
		
		Category c=null;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, title);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				c=new Category();
				c.setTitolo(rs.getString("titolo"));
				c.setPercorso(rs.getString("percorso"));
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
		
		return c;
		
	}
}
