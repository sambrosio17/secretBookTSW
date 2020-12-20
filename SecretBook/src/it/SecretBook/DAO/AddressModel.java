package it.SecretBook.DAO;

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

import it.SecretBook.model.Address;

public class AddressModel {
	
	private static final String TABLE_NAME = "indirizzo";
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

	
	public void doSave(Address a) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TABLE_NAME+" (nazione, citta, via, cap, num, utente) values (?,?,?,?,?,?);";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			String naz= (String) a.getNazione().substring(0, 2);
			
			preparedStatement.setString(1, naz);
			preparedStatement.setString(2, a.getCitta());
			preparedStatement.setString(3, a.getVia());
			preparedStatement.setInt(4, a.getCap());
			preparedStatement.setInt(5, a.getNum());
			preparedStatement.setString(6, a.getUtente());
			
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
	
	
	public boolean doDelete(int id) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="DELETE FROM "+TABLE_NAME+" WHERE id=?";
		
		int res=0;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
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
	
	
	public Address doRetrieveById(int id) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		Address a=null;
		
		ResultSet rs=null;
		
		String sql="SELECT * FROM "+TABLE_NAME+" WHERE id=?;";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			rs=preparedStatement.executeQuery();
			
			if(rs.next())
			{
				a=new Address();
				a.setId(rs.getInt("id"));
				a.setNazione(rs.getString("nazione"));
				a.setCitta(rs.getString("citta"));
				a.setVia(rs.getString("via"));
				a.setCap(rs.getInt("cap"));
				a.setNum(rs.getInt("num"));
				a.setUtente(rs.getString("utente"));
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
		
		return a;
	}
	
	
	public Collection<Address> doRetrievebyUser(String utente) throws SQLException
	{
		

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		Address a=null;
		
		Collection<Address> list=new LinkedList<Address>();
		
		ResultSet rs=null;
		
		String sql="SELECT * FROM "+TABLE_NAME+" WHERE utene=?;";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, utente);
			
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				a=new Address();
				a.setId(rs.getInt("id"));
				a.setNazione(rs.getString("nazione"));
				a.setCitta(rs.getString("citta"));
				a.setVia(rs.getString("via"));
				a.setCap(rs.getInt("cap"));
				a.setNum(rs.getInt("num"));
				a.setUtente(rs.getString("utente"));
				list.add(a);
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
		
		return list;
		
	}
	
	public Address doRetrievebyUsername(String utente) throws SQLException
	{
		

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		Address a=null;
		

		
		ResultSet rs=null;
		
		String sql="SELECT * FROM "+TABLE_NAME+" WHERE utente=?;";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, utente);
			
			rs=preparedStatement.executeQuery();
			
			if(rs.next())
			{
				a=new Address();
				a.setId(rs.getInt("id"));
				a.setNazione(rs.getString("nazione"));
				a.setCitta(rs.getString("citta"));
				a.setVia(rs.getString("via"));
				a.setCap(rs.getInt("cap"));
				a.setNum(rs.getInt("num"));
				a.setUtente(rs.getString("utente"));
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
		
		return a;
		
	}
	
	
	public void doUpdate(Address a) throws SQLException
	{

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		
		String sql="UPDATE "+ TABLE_NAME+ " SET nazione=?, citta=?, via=?, cap=?, num=?, utente=? where id=?";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			String naz= (String) a.getNazione().substring(0, 2);
			
			preparedStatement.setString(1, naz);
			preparedStatement.setString(2, a.getCitta());
			preparedStatement.setString(3, a.getVia());
			preparedStatement.setInt(4, a.getCap());
			preparedStatement.setInt(5, a.getNum());
			preparedStatement.setString(6, a.getUtente());
			preparedStatement.setInt(7, a.getId());
			
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
}
