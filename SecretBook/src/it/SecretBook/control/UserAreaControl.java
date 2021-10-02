package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.CreditCardModel;
import it.SecretBook.DAO.UserModel;
import it.SecretBook.model.CreditCard;
import it.SecretBook.model.User;

/**
 * Servlet implementation class UserAreaControl
 */

@WebServlet("/mySpace")
public class UserAreaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CreditCardModel cModel=new CreditCardModel();
	UserModel m=new UserModel();    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAreaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		User u= (User) request.getSession().getAttribute("user");
		
		if(u==null)
		{
			response.sendRedirect("/SecretBook/404-notfound.jsp");
			return;
		}
		
		CreditCard c=null;
		
		try {
			c=cModel.doRetriveByUser(u);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String q=request.getParameter("q");
		
		if(q!=null && q.equalsIgnoreCase("info"))
		{
			request.setAttribute("creditCard", c);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/user/myInfo.jsp");
			rd.forward(request, response);
			return;
		}
		if(u==null)
		{
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/registration.jsp");
			rd.forward(request, response);
			return;
		}
		String s=u.getUsername();
		
		try {
			u=m.doRetrieveByUsername(s);
			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/user/myArea.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
//Untangled partizione: 12//Untangled partizione: 0