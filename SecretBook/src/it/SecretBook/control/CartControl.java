package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.SecretBook.DAO.*;
import it.SecretBook.model.*;



//SERVLET PER LA GESTIONE DEL CARRELLO CON SESSESIONE
/**
 * Servlet implementation class CartControl
 */
@WebServlet("/Cart")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Model model=new ProductModel();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(); //prendo la richiesta dalla sessione
		Cart userCart; 
		synchronized (session) {
			userCart=(Cart) session.getAttribute("cart"); //prendo il carrello salvato nella sessione
			if(userCart==null) //se la sessione non ha il carrello
			{
				System.out.println("creo nuovo carrello, nuova sessione");
				userCart=new Cart(); //viene costruito un nuovo carello
				session.setAttribute("cart", userCart);	 //viene messo nella sessione
			}
		}
			String action=request.getParameter("action");
			long itemId=0;
			int qnt=1;
			try {
			if(action!=null)
			{
				if(action.equalsIgnoreCase("addItem"))
				{
					System.out.println("action: "+action);
					itemId=Long.parseLong(request.getParameter("itemId"));
					CartItem prod= new CartItem(model.doRetrieveByKey(itemId));
					qnt=Integer.parseInt(request.getParameter("qt"));
					prod.setQuantity(qnt);
					userCart.addItem(prod);
					System.out.println("ciao aggiundo elemento al carrello"+prod.getProd().getNome());
				}
				if(action.equalsIgnoreCase("deleteItem")) //cancella completamente il prodotto dal carrello
				{
					itemId=Long.parseLong(request.getParameter("itemId"));
					CartItem prod=new CartItem(model.doRetrieveByKey(itemId));
					userCart.deleteItem(prod);
					System.out.println("ciao cancello elemento al carrello"+prod.getProd().getNome());
				}
				if(action.equalsIgnoreCase("setQnt"))
				{
					itemId=Long.parseLong(request.getParameter("itemId"));
					qnt=Integer.parseInt(request.getParameter("qt"));
					CartItem prod=new CartItem(model.doRetrieveByKey(itemId));
					userCart.setQuantity(prod, qnt);
					System.out.println("aggiorno quantità");
				}
			}
			} catch (Exception e) {
				System.out.println("Error: "+e.getMessage());
				e.printStackTrace();
			}
			
			session.setAttribute("cart", userCart);
			request.setAttribute("cart", userCart);
			
			request.removeAttribute("cartList");
			request.removeAttribute("cartTotal");
			request.removeAttribute("cartQnt");
			
			try {
				
				request.setAttribute("cartList", userCart.getItems());
				request.setAttribute("cartTotal",userCart.getTotalCost());
				request.setAttribute("cartQnt", userCart.getTotalQuantiy());
				
			} catch (Exception e) {
				System.out.println("Error: "+e.getMessage());
			}
			
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/CartView.jsp");
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
//Untangled partizione: 4