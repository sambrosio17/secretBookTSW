package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.ProductModel;



//SERVLET PER LA GESTIONE DELLE IMMAGINI DEL PRODOTTO

/**
 * Servlet implementation class ProductPictureServlet
 */
@WebServlet("/productPicture")
public class ProductPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	ProductModel model=new ProductModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id=Long.parseLong(request.getParameter("id"));
		if (id != -1)
		{
			byte[] bt=null;
			try {
				bt = model.load(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			ServletOutputStream out = response.getOutputStream();
			if(bt != null)
			{
				out.write(bt);
				response.setContentType("image/jpeg");			
			}
			out.close();
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
//Untangled partizione: 10