package it.SecretBook.control;

import it.SecretBook.control.*;
import it.SecretBook.model.*;
import it.SecretBook.DAO.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderControl
 */


//SERVLET GESTIONE ORDINE (DA RIVEDERE CON CONTROLLI)
@WebServlet("/order")
public class OrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	OrderModel oModel= new OrderModel();
	ProductModel pModel=new ProductModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderControl() {
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
		User u;
		synchronized (session) {
			userCart=(Cart) session.getAttribute("cart"); //prendo il carrello salvato nella sessione
			u=(User)session.getAttribute("user");
			System.out.println(u);
			if(u==null)
			{
				request.setAttribute("error", true);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/404-notfound.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if(userCart==null) //se la sessione non ha il carrello
			{
				System.out.println("creo nuovo carrello, nuova sessione");
				userCart=new Cart(); //viene costruito un nuovo carello
				session.setAttribute("cart", userCart);	 //viene messo nella sessione
			}
			String action = request.getParameter("action");
			try {
			
			if(action!=null)
			{
				if(action.equalsIgnoreCase("doOrder"))
				{
					oModel.doSave(userCart, u.getUsername());
					userCart.deleteAll();
					session.removeAttribute("cart");
					session.setAttribute("cart", userCart);
				}
		
			}
				
			}catch (Exception e) {
				System.out.println("Error: "+e.getMessage());
			}
			String orderId= request.getParameter("id");
			if(orderId!=null)
			{
				try {
					request.setAttribute("ordine", oModel.doRetrieveByKey(Integer.parseInt(orderId), u.getUsername()));
					RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/fattura.jsp");
					dispatcher.forward(request, response);
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				
				request.removeAttribute("orders");
				ArrayList<Order> orders= (ArrayList<Order>) oModel.doRetrieveAll(u.getUsername());
				request.setAttribute("orders", orders);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/OrderView.jsp");
			dispatcher.forward(request, response);
			return;
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
//Untangled partizione: 9//Untangled partizione: 13