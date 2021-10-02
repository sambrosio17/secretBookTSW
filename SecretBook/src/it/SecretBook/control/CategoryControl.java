package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.CategoryModel;
import it.SecretBook.DAO.ProductModel;
import it.SecretBook.model.Product;

/**
 * Servlet implementation class CategoryControl
 */
@WebServlet("/catalog")
public class CategoryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductModel m=new ProductModel();
	CategoryModel cModel=new CategoryModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int category= Integer.parseInt(request.getParameter("cat"));
		
		String cat="";
		
		LinkedList<Product> c= null;
		
		switch(category)
		{
		case 0: cat="ADRENALINA"; break;
		case 1: cat="BRIVIDI LUNGO LA SCHIENA"; break;
		case 2: cat="DAL PROFONDO DEL CUORE"; break;
		case 3: cat="IN CERCA DI AVVENTURA"; break;
		case 4:	cat="REALITY++"; break;
		}
		
		try {
			c=(LinkedList<Product>) m.doRetrieveByCategory(cat);
			request.setAttribute("catalog", c);
			request.setAttribute("category", cModel.doRetrieveByKey(cat));
			
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/category.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
//Untangled partizione: 5//Untangled partizione: 11