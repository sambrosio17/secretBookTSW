package it.SecretBook.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.ProductModel;
import it.SecretBook.model.Product;

/**
 * Servlet implementation class LuckyBook
 */
@WebServlet("/feelingLucky")
public class LuckyBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductModel model=new ProductModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LuckyBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product prod=new Product();
		
		try {
			
			prod=model.doRetrieveRandom();
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		request.setAttribute("prod", prod);
		
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/product-page.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
//Untangled partizione: 9