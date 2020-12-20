package it.SecretBook.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.SecretBook.model.CreditCard;
import it.SecretBook.model.User;

public class CreditCardModel {

	private static final String TABLE_NAME = "cartacredito";
	static DataSource ds;
	
	static {				//?
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/secretbook");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	public void doSave(CreditCard c, User u) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TABLE_NAME+" (cod, intestatario, cvc, scad, saldo, utente) values (?,?,?,?,?,?);";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, c.getCod());
			preparedStatement.setString(2, c.getIntestatario());
			preparedStatement.setInt(3, c.getCvc());
			preparedStatement.setDate(4, Date.valueOf(c.getScad()));
			preparedStatement.setDouble(5, c.getSaldo());
			preparedStatement.setString(6, u.getUsername());
			preparedStatement.executeUpdate();
			
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
	
	public boolean doDelete(long id) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="DELETE FROM "+TABLE_NAME+" WHERE id=?";
		
		int res=0;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, id);
			
			res=preparedStatement.executeUpdate();
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
		return (res!=0);
	}
	
	public void doUpdate(CreditCard c, User u, Long oldNumber) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="UPDATE "+TABLE_NAME+" SET cod=?, intestatario=?, cvc=?, scad=?, utente=? WHERE cod=?";
		
	
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, c.getCod());
			preparedStatement.setString(2, c.getIntestatario());
			preparedStatement.setInt(3, c.getCvc());
			preparedStatement.setDate(4,Date.valueOf(c.getScad()));
			preparedStatement.setString(5, u.getUsername());
			preparedStatement.setLong(6, oldNumber);
			
			preparedStatement.executeUpdate();
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		
		return;
	}
	
	public CreditCard doRetriveByUser(User u) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="SELECT * FROM "+TABLE_NAME+" where utente=?";
		
		CreditCard c=null;
	
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, u.getUsername());
			
			ResultSet rs=preparedStatement.executeQuery();
			
			if(rs.next())
			{
				c=new CreditCard();
				c.setCod(rs.getLong("cod"));
				c.setCvc(rs.getInt("cvc"));
				c.setIntestatario(rs.getString("intestatario"));
				c.setScad(rs.getDate("scad").toLocalDate());
				c.setSaldo(rs.getDouble("saldo"));
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
