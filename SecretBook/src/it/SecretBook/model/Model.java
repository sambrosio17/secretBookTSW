package it.SecretBook.model;

import java.sql.SQLException;
import java.util.Collection;

public interface Model {
	public void doSave(Product product) throws SQLException;

	public boolean doDelete(long code) throws SQLException;

	public Product doRetrieveByKey(long code) throws SQLException;
	
	public Collection<Product> doRetrieveAll(String order) throws SQLException;
}
//Untangled partizione: 17