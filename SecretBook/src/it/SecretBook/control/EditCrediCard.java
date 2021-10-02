package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.SecretBook.DAO.CreditCardModel;
import it.SecretBook.model.CreditCard;
import it.SecretBook.model.User;

/**
 * Servlet implementation class EditCrediCard
 */
@WebServlet("/editCC")
public class EditCrediCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	CreditCardModel cModel=new CreditCardModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCrediCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User u= (User) request.getSession().getAttribute("user");
		
		CreditCard c=new CreditCard();
		try {
			c = cModel.doRetriveByUser(u);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Long oldNumber=c.getCod();
		
		String intestatario= request.getParameter("intestatario");
		Long numero= Long.parseLong(request.getParameter("numero"));
		String dataS=request.getParameter("data");
		int anno=Integer.parseInt(dataS.substring(0, 4));
		int mese=Integer.parseInt(dataS.substring(5, dataS.length()));
		System.out.println(dataS+" "+mese+" "+ anno);
		LocalDate data=LocalDate.of(anno, mese, 01);
		int cvc=Integer.parseInt(request.getParameter("cvc"));
		
		c.setIntestatario(intestatario);
		c.setCod(numero);
		c.setScad(data);
		c.setCvc(cvc);

		
		try {
			cModel.doUpdate(c, u,oldNumber);
			request.setAttribute("creditCar", cModel.doRetriveByUser(u));
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
//Untangled partizione: 7//Untangled partizione: 12