package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.AddressModel;
import it.SecretBook.DAO.OrderModel;
import it.SecretBook.DAO.UserModel;
import it.SecretBook.model.Address;
import it.SecretBook.model.Order;
import it.SecretBook.model.User;

/**
 * Servlet implementation class AdminOrderControl
 */
@WebServlet("/adminOrder")
public class AdminOrderControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	OrderModel oModel=new OrderModel();
	UserModel uModel=new UserModel();
	AddressModel aModel=new AddressModel();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		
		User u=(User) request.getSession().getAttribute("user");
		Boolean isAdmin=(Boolean) request.getSession().getAttribute("isAdmin");
		
		if(u==null || !isAdmin)
		{
			response.sendRedirect("404-notfound.jsp");
			return;
		}
		
		if(type!=null && type.equalsIgnoreCase("all"))
		{
			try {
				ArrayList<Order> list= (ArrayList<Order>) oModel.doRetrieveAll();
				request.setAttribute("lista",list);
				System.out.println(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/adminArea/orders.jsp");
			rd.forward(request, response);
			return;
		}
		if( type!=null && type.equalsIgnoreCase("user"))
		{
			String id=request.getParameter("id");
			try {
				request.setAttribute("lista", oModel.doRetrieveAll(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/adminArea/orders.jsp");
			rd.forward(request, response);
			return;
		}
		if(type!=null && type.equalsIgnoreCase("date"))
		{
			LocalDate start=LocalDate.parse(request.getParameter("start"));
			LocalDate end=LocalDate.parse(request.getParameter("end"));
			
			try {
				request.setAttribute("lista", oModel.doRetrieveBetweenDates(start, end));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/adminArea/orders.jsp");
			rd.forward(request, response);
			return;
		}
		if(type!=null && type.equalsIgnoreCase("fattura"))
		{
			String number=request.getParameter("n");
			
			Order o=null;
			User user=null;
			Address a=null;
			try {
				o = oModel.doRetrieveByKey(Integer.parseInt(number));
				user=uModel.doRetrieveByUsername(o.getUser());
				a=aModel.doRetrievebyUsername(user.getUsername());
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("user", user);
			request.setAttribute("ordine", o);
			request.setAttribute("address", a);
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/fatturaAdmin.jsp");
			rd.forward(request, response);
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
