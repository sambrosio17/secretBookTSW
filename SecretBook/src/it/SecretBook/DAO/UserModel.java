package it.SecretBook.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.SecretBook.model.User;

public class UserModel {
	
	
	private static final String TABLE_NAME = "utente";
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
	
	
	
	public void doSave(User user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql="INSERT INTO "+TABLE_NAME+" (username, nome, cognome, email, datanasc, psw, saldo, ruolo) values (?,?,?,?,?,?,?,?);";
		
		try {
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getNome());
			preparedStatement.setString(3, user.getCognome());
			preparedStatement.setString(4, user.getEmail());
			if(user.getDataDiNascita()!=null)
			{
				preparedStatement.setDate(5,Date.valueOf(user.getDataDiNascita()));
			}
			else
			{
				preparedStatement.setDate(5, null);
			}
			preparedStatement.setString(6,user.getPassword());
			preparedStatement.setInt(7, user.getSaldo());
			preparedStatement.setString(8, user.getRuolo());
			
			preparedStatement.executeUpdate();
			
			uploadPhoto(user.getUsername(), user.getFoto());
				
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
	}
	
	
	public boolean doDelete(String username) throws SQLException
	{
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int result=0;
			
			String sql="DELETE FROM "+ TABLE_NAME+ " where username=?";
			
			try {
				connection=ds.getConnection();
				preparedStatement=connection.prepareStatement(sql);
				
				result=preparedStatement.executeUpdate(sql);	
			}
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if(connection!=null)
						connection.close();
				}
			}
			return (result!=0);
			
	}
	
	public void doUpdate(String username, User user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String sql="UPDATE "+TABLE_NAME+" SET username=?, nome=?, cognome=?, email =?, datanasc=?, psw=?, saldo=?, ruolo=? where username=?;";
		
		try {
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getNome());
			preparedStatement.setString(3, user.getCognome());
			preparedStatement.setString(4, user.getEmail());
			if(user.getDataDiNascita()!=null)
			{
				preparedStatement.setDate(5,Date.valueOf(user.getDataDiNascita()));
			}
			else
			{
				preparedStatement.setDate(5, null);
			}
			preparedStatement.setString(6,user.getPassword());
			preparedStatement.setInt(7, user.getSaldo());
			preparedStatement.setString(8, user.getRuolo());
			preparedStatement.setString(9, username);
			
			preparedStatement.executeUpdate();
			
			uploadPhoto(user.getUsername(), user.getFoto());
			
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
	}


	public User doRetrieveByUsername(String username) throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="SELECT * FROM "+TABLE_NAME+" WHERE username=?;";
		
		User u=new User();
		
		ResultSet rs=null;
		
		try {
			
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				u.setUsername(rs.getString(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setEmail(rs.getString(4));
				if(rs.getDate(5)==null)
				{
					u.setDataDiNascita(LocalDate.MIN);
				}
				else {
				u.setDataDiNascita((rs.getDate(5).toLocalDate()));
				}
				u.setPassword(rs.getString(6));
				u.setSaldo(rs.getInt(7));
				u.setRuolo(rs.getString(8));
				u.setFoto(rs.getBytes(9));
			}
			
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return u;
	}
	
	
	public Collection<User> doRetrieveAll() throws SQLException
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="SELECT * FROM "+TABLE_NAME+";";
		
		Collection<User> users=new LinkedList();
		
		
		
		ResultSet rs=null;
		
		try {
			connection=ds.getConnection();
			
			preparedStatement=connection.prepareStatement(sql);
			
			rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				User u=new User();
				u.setUsername(rs.getString(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setDataDiNascita(rs.getDate(5).toLocalDate());
				u.setPassword(rs.getString(6));
				u.setSaldo(rs.getInt(7));
				u.setRuolo(rs.getString(8));
				u.setFoto(this.load(rs.getString("username")));
				users.add(u);
				
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
		
		return users;
		
	}
	
	public boolean isValidUsername(String username) throws SQLException //true se username non presente altrimenti false
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		
		String sql="SELECT username FROM "+TABLE_NAME+" WHERE username=?;";
		
		try {
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			if(!rs.next())
			{
				return true;
			}
			
			
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return false;
		
		
	}
	public boolean isValidEmail(String email) throws SQLException    //true se email non è presente, false se è gia presente
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		
		String sql="SELECT email FROM "+TABLE_NAME+" WHERE email=?;";
		
		try {
			connection=ds.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, email);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			if(!rs.next())
			{
				return true;
			}
			
			
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection!=null)
					connection.close();
			}
		}
		return false;
		
		
	}
	
	
	public boolean doValidate(String username, String password) throws SQLException  //se corrispondono true, false se utente non esiste e se la password non è giusta
	{
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		if(this.isValidUsername(username))
		{
			return false;
		}
		
		String sql="SELECT psw FROM "+TABLE_NAME+" WHERE username=?";
		
			
			try {
				connection=ds.getConnection();
				preparedStatement=connection.prepareStatement(sql);
				
				preparedStatement.setString(1, username);
				
				ResultSet rs=preparedStatement.executeQuery();
				
				if(rs.next())
				{
					if(rs.getString("psw").equals(password))
						return true;
				}
				
				
			}
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if(connection!=null)
						connection.close();
				}
			}
			
			return false;
	}
	
	
	public synchronized byte[]load(String username) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		ResultSet rs=null;
		
		String sql="SELECT photo FROM "+TABLE_NAME+" where username= ?";
		
		byte[] foto=null;
		
		try {
			connection=ds.getConnection();
			
			stat=connection.prepareStatement(sql);
			stat.setString(1, username);
			
			rs=stat.executeQuery();
			
			if(rs.next())
			{
				foto=rs.getBytes("photo");
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
	
	
	public synchronized void updatePhoto(String username, String photo) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		
		String sql="UPDATE "+TABLE_NAME+" SET photo = ? where username = ?";
		
		try {
			connection=ds.getConnection();
			
			stat=connection.prepareStatement(sql);
			stat.setString(2, username);
			
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
	
	
	public synchronized void uploadPhoto(String username, byte[] foto) throws SQLException
	{
		Connection connection=null;
		PreparedStatement stat=null;
		
		String sql="UPDATE "+TABLE_NAME+" SET photo = ? where username = ?";
		
		try {
			connection=ds.getConnection();
			
			stat=connection.prepareStatement(sql);
			stat.setString(2, username);
			
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
//Untangled partizione: 0