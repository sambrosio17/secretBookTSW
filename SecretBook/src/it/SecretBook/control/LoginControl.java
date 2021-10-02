package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.AddressModel;
import it.SecretBook.DAO.UserModel;
import it.SecretBook.model.Address;
import it.SecretBook.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserModel model=new UserModel();
	AddressModel aModel=new AddressModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User u=null;
		Address a=null;
		
		request.getSession().setAttribute("isAdmin", false);
		request.getSession().setAttribute("isLogged", false);
		
		
		try {
			if(model.doValidate(username, password))
			{
				u=model.doRetrieveByUsername(username);
				a=aModel.doRetrievebyUsername(username);
				if(u.getRuolo().equalsIgnoreCase("admin"))
				{
					request.getSession().removeAttribute("isAdmin");
					request.getSession().setAttribute("isAdmin", true);
					System.out.println("sono");
				}
				request.getSession().removeAttribute("isLogged");
				request.getSession().setAttribute("isLogged", true);
				request.getSession().setAttribute("user",u);
				request.getSession().setAttribute("address", a);
				
				System.out.println("autenticazione fatta");
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/user/myArea.jsp");
				dispatcher.forward(request, response);
			}
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
//Untangled partizione: 8