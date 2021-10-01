package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.SecretBook.DAO.UserModel;

/**
 * Servlet implementation class ValidatorServlet
 */
@WebServlet("/validator")
public class ValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserModel uModel=new UserModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String user=request.getParameter("user");
		String mail=request.getParameter("mail");
		
		Map<String, Boolean> result=new HashMap<String, Boolean>();
		
		Gson g=new Gson();
		
		try {
			result.put("username", uModel.isValidUsername(user));
			result.put("email", uModel.isValidEmail(mail));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(g.toJson(result));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(g.toJson(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
