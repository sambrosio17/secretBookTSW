package it.SecretBook.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.SecretBook.DAO.ProductModel;
import it.SecretBook.DAO.WishListModel;
import it.SecretBook.model.Product;
import it.SecretBook.model.User;
import it.SecretBook.model.WishList;

/**
 * Servlet implementation class WishListControl
 */
@WebServlet("/wishlist")
public class WishListControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	WishListModel wModel= new WishListModel();
	ProductModel pModel=new ProductModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishListControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		
		System.out.println("******LOG WISHLIST || START******");
		HttpSession session= request.getSession();
		
		session.setMaxInactiveInterval(0);
		
		Enumeration<String> en=session.getAttributeNames();
		while(en.hasMoreElements())
		{
			System.out.println(en.nextElement());
		}
		WishList userList=null;
		
		User u;
		
		if(session.getAttribute("isLogged")==null || (Boolean)session.getAttribute("isLogged")!=true)
		{
			System.out.println("utente non presente nella sessione");
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/user/myWishList.jsp");
			dispatcher.forward(request, response);
		}
		u=(User) session.getAttribute("user");
		
		
		if(u==null)
		{
	
			System.out.println("scem fai l'access");
			return;
		}
			
			if(session.getAttribute("wishlist")!=null)  //controllo se la lista è gia presente nella sessione
			{
				
				System.out.println("lista già nella sessione");
				userList= (WishList) session.getAttribute("wishlist"); //se è presente la assegno a userList
				
				
				System.out.println("lista già presente nella sessione");
				System.out.println(userList.getId()+ " " +userList.getUtente().getUsername()+ " // "+u.getUsername()+userList.getProdotti().toString());
			}
			else { // se non è presente
				try {
					if(wModel.doCheckList(u.getUsername())) //se l'utente ha già una lista la assegno a userList e la salvo nella sessione
					{
						System.out.println("lista già nel db");
						userList=wModel.doRetrieveByKey(u.getUsername());
						session.removeAttribute("wishlist");
						session.setAttribute("wishlist", userList);
						System.out.println("lista già presente nel database");
						System.out.println(userList.getId()+ " " +userList.getUtente().getUsername()+ " // "+u.getUsername()+userList.getProdotti().toString());
					}
					else //se l'utente non ha una lista ne creo una, la assegno a userList e la salvo nella sessione
					{
						System.out.println("creo lista");
						userList= new WishList();
						userList.setUtente(u);
						wModel.doSave(userList); //la doSave non specifica l'id perchè è autoincrement, quindi non lo settiamo
						userList=wModel.doRetrieveByKey(u.getUsername());
						session.setAttribute("wishlist", wModel.doRetrieveByKey(u.getUsername())); //nella sessione mettiamo la lista appena creata prendendola dal database
						System.out.println("lista non presente nel database");
						System.out.println(userList.getId()+ " " +userList.getUtente().getUsername()+ " // "+u.getUsername()+userList.getProdotti().toString());
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			session.removeAttribute("user");
			session.setAttribute("user", u);
			
			
			String a=request.getParameter("action");
			
			if(a!=null && a.equalsIgnoreCase("add"))
			{
				
				System.out.println("sto facendo la add");
				Long book=Long.parseLong(request.getParameter("id"));
				Boolean in=it.SecretBook.Util.Utility.checkPresence(book, userList.getProdotti());
				try {
					if(in==false) {
					wModel.doAddProduct(userList, book);
					session.removeAttribute("wishlist");
					session.setAttribute("wishlist", wModel.doRetrieveByKey(u.getUsername()));
					System.out.println("add conclusa");
					}
					
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/user/myWishList.jsp");
				rd.forward(request, response);
				return;
			}
			if(a!=null && a.equalsIgnoreCase("remove"))
			{
				Long book=Long.parseLong(request.getParameter("id"));
				try {
					wModel.doDeleteProduct(userList, book);
					session.removeAttribute("wishlist");
					session.setAttribute("wishlist", wModel.doRetrieveByKey(u.getUsername()));
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/user/myWishList.jsp");
					rd.forward(request, response);
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			System.out.println("******LOG WISHLIST || END******");
			
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/user/myWishList.jsp");
			dispatcher.forward(request, response);
}
		



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
//Untangled partizione: 14