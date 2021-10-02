package it.SecretBook.control;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.SecretBook.Util.Quote;

/**
 * Servlet implementation class QuotesServlet
 */
@WebServlet("/getQuote")

public class QuotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuotesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int category= Integer.parseInt(request.getParameter("cat"));
		
		Quote q=new Quote();
		
		String cat="";
		
		switch(category)
		{
		case 0: cat="ADRENALINA"; break;
		case 1: cat="BRIVIDI LUNGO LA SCHIENA"; break;
		case 2: cat="DAL PROFONDO DEL CUORE"; break;
		case 3: cat="IN CERCA DI AVVENTURA"; break;
		case 4:	cat="REALITY++"; break;
		case 5: cat="home"; break;
		}
		
		String quote=q.getQuote(cat);
		
		
		
		Gson g=new Gson();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(g.toJson(quote));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
//Untangled partizione: 11//Untangled partizione: 14