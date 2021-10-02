package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.SecretBook.DAO.*;

/**
 * Servlet implementation class CheckUserControl
 */
@WebServlet("/checkLogin")
public class CheckUserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserModel m= new UserModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		
		
		System.out.println("sono stata invocata");
		Boolean result=null;
		
		try {
			result=m.doValidate(username, password);
			response.setContentType("application/json");
			
			Gson g= new Gson();
			response.getWriter().write(g.toJson(result));
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
//Untangled partizione: 6