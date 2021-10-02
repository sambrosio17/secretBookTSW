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



//SERVLET CHE PRENDE IL PRODOTTO DAL DATABASE E LO INVIA ALLA JSP PER LA VISUALIZZAZIONE DEL SINGOLO PRODOTTO
/**
 * Servlet implementation class ProductControl
 */
@WebServlet("/product")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	ProductModel model=new ProductModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		long id=Long.parseLong(request.getParameter("id"));
		Product prod=null;
		
		try {
			
			prod=model.doRetrieveByKey(id);
			
			
			
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
//Untangled partizione: 10//Untangled partizione: 14