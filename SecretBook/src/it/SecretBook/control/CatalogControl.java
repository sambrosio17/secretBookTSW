package it.SecretBook.control;


import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.ProductModel;
import it.SecretBook.model.Model;
import it.SecretBook.model.Product;


//SERVLET CHE PRENDE TUTTI I PRODOTTI DAL DATABASE E LI INVIA ALLA JSP PER LA VISUALIZZAZIONE DEL CATALOGO
/**
 * Servlet implementation class ProductControl
 */
@WebServlet("/index")
public class CatalogControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ProductModelDS usa il DataSource
	// ProductModelDM usa il DriverManager	
	
	Model model=new ProductModel();
	
	
	public CatalogControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		/*try {
			if (action != null) {
				if (action.equalsIgnoreCase("read")) {
					long is = Long.parseLong(request.getParameter("isbn"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(is));
				} else if (action.equalsIgnoreCase("delete")) {
					int is = Integer.parseInt(request.getParameter("isbn"));
					model.doDelete(is);
				} else if (action.equalsIgnoreCase("insert")) {
					int isbn= Integer.parseInt(request.getParameter("isbn"));
					String name = request.getParameter("nome");
					String description = request.getParameter("descrizione");
					int price = Integer.parseInt(request.getParameter("prezzo"));
					double iva= Double.parseDouble(request.getParameter("iva"));
					int quantity = Integer.parseInt(request.getParameter("qt"));
					String categ = request.getParameter("categoria");

					Product bean = new Product();
					bean.setIsbn(isbn);
					bean.setNome(name);
					bean.setDescrizione(description);
					bean.setPrezzo(price);
					bean.setIva(iva);
					bean.setQt(quantity);
					bean.setCategoria(categ);
					model.doSave(bean);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}*/

		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll("home"));
			LinkedList<Product> libri=(LinkedList<Product>) model.doRetrieveAll("home");
			System.out.println(libri.getFirst().getNome()+" ciao "+libri.getFirst().getIva());
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
