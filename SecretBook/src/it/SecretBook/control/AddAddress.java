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
import it.SecretBook.model.Address;
import it.SecretBook.model.User;

/**
 * Servlet implementation class AddAddress
 */
@WebServlet("/addAddress")
public class AddAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AddressModel aModel= new AddressModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Address a=new Address();
		User u=(User)request.getSession().getAttribute("user");
		
		String via=request.getParameter("via");
		String civico=request.getParameter("civico");
		String cap=request.getParameter("cap");
		String citta=request.getParameter("citta");
		String stato=request.getParameter("stato");
		
		a.setUtente(u.getUsername());
		
		if(via!=null && !via.isEmpty())
			a.setVia(via);
		if(civico!=null && !civico.isEmpty())
			a.setNum(Integer.parseInt(civico));
		if(cap!=null && !cap.isEmpty())
			a.setCap(Integer.parseInt(cap));
		if(citta!=null && !citta.isEmpty())
			a.setCitta(citta);
		if(stato!=null && !stato.isEmpty())
			a.setNazione(stato);
		
		try {
			aModel.doSave(a);
			request.getSession().removeAttribute("address");
			request.getSession().setAttribute("address", aModel.doRetrievebyUsername(u.getUsername()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/mySpace?q=info");
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
//Untangled partizione: 2//Untangled partizione: 10