package it.SecretBook.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.SecretBook.DAO.UserModel;
import it.SecretBook.model.User;

/**
 * Servlet implementation class ValidateControl
 */
@WebServlet("/validate")
public class ValidateControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserModel model=new UserModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		String input="";
		
		System.out.println("sono stata invocata");
		Boolean result;
		
	try {
		if(action.equalsIgnoreCase("user"))
		{
			input=request.getParameter("username");
			if(model.isValidUsername(input))
			{
				result=true;
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(new Gson().toJson(result));
			}
			else {
				result=false;
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(new Gson().toJson(result));
			}
			
		}
		if(action.equalsIgnoreCase("address"))
		{
			input=request.getParameter("email");
			if(model.isValidEmail(input))
			{
				result=true;
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(new Gson().toJson(result));
			}
			else {
				result=false;
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(new Gson().toJson(result));
			}
		}
		
	} catch (Exception e) {
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
//Untangled partizione: 13