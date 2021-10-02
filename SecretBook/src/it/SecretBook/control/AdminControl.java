package it.SecretBook.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.SecretBook.DAO.ProductModel;
import it.SecretBook.model.Product;

/**
 * Servlet implementation class AdminControl
 */
@WebServlet("/admin")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR ="/uploadTemp";
	
	ProductModel model=new ProductModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 	Boolean admin= (Boolean)request.getSession().getAttribute("isAdmin");
		   Boolean logged=(Boolean) request.getSession().getAttribute("isLogged");
		   if(admin==null || !admin || logged==null || !logged)
		   {
			   RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/404-notfound.jsp");
				dispatcher.forward(request, response);
				return;
		   }
			String action=request.getParameter("action");
			String appPath = request.getServletContext().getRealPath("");
			String savePath = appPath + File.separator + SAVE_DIR;
			byte[] foto=null;
			
			if(action==null || action.equalsIgnoreCase("show"))
			{
				 RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/adminArea/adminHome.jsp");
					dispatcher.forward(request, response);
					return;
				
			}
			
			try {
				if(action!=null)
				{
					if(action.equalsIgnoreCase("doInsert"))
					{
						RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/adminArea/insertProduct.jsp");
						dispatcher.forward(request, response);
					}
					if(action.equalsIgnoreCase("insert"))
					{
						Product bean=new Product();
						bean.setIsbn(Long.parseLong(request.getParameter("isbn")));
						bean.setNome(request.getParameter("titolo"));
						bean.setDescrizione(request.getParameter("descrizione"));
						bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
						bean.setIva(Double.parseDouble(request.getParameter("iva")));
						bean.setQt(Integer.parseInt(request.getParameter("qnt")));
						bean.setCategoria(request.getParameter("categoria"));
						bean.setSpecial(request.getParameter("special"));
						model.doSave(bean);
						
						File fileSaveDir = new File(savePath);
						if (!fileSaveDir.exists()) {
							fileSaveDir.mkdir();
						}
						
						for (Part part : request.getParts()) {
							String fileName = extractFileName(part);
							if (fileName != null && !fileName.equals("")) {
								
							
								part.write(savePath + File.separator + fileName);
								model.updatePhoto(Long.parseLong(request.getParameter("isbn")), savePath + File.separator + fileName);
								System.out.println("ho ricevuto la foto");
							}
								
						}
						RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/product?id="+request.getParameter("isbn"));
						dispatcher.forward(request, response);
					
					}
					if(action.equalsIgnoreCase("manage"))
					{
						request.setAttribute("products", model.doRetrieveAll(""));
						RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/adminArea/productManager.jsp");
						dispatcher.forward(request, response);
					}
					if(action.equalsIgnoreCase("delete"))
					{
						model.doDelete(Long.parseLong(request.getParameter("id")));
						RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/admin?action=manage");
						dispatcher.forward(request, response);
					}
					if(action.equalsIgnoreCase("edit"))
					{
						System.out.println("edit me");
						long id=Long.parseLong(request.getParameter("id"));
						request.setAttribute("product", model.doRetrieveByKey(id));
						RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/adminArea/editProduct.jsp");
						dispatcher.forward(request, response);
					}
					if(action.equalsIgnoreCase("doEdit"))
					{
						long id=Long.parseLong(request.getParameter("isbn"));
						Product bean=model.doRetrieveByKey(id);
						bean.setIsbn(Long.parseLong(request.getParameter("isbn")));
						if(request.getParameter("titolo")!=null && !request.getParameter("titolo").isEmpty())
							bean.setNome(request.getParameter("titolo"));
						if(request.getParameter("descrizione")!=null && !request.getParameter("descrizione").isEmpty())
							bean.setDescrizione(request.getParameter("descrizione"));
						if(request.getParameter("prezzo")!=null && !request.getParameter("prezzo").isEmpty())
							bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
						if(request.getParameter("iva")!=null && !request.getParameter("iva").isEmpty())
							bean.setIva(Double.parseDouble(request.getParameter("iva")));
						if(request.getParameter("qnt")!=null && !request.getParameter("qnt").isEmpty())
							bean.setQt(Integer.parseInt(request.getParameter("qnt")));
						if(request.getParameter("categoria")!=null && !request.getParameter("categoria").isEmpty())
							bean.setCategoria(request.getParameter("categoria"));
						if(request.getParameter("special")!=null && !request.getParameter("special").isEmpty())
							bean.setCategoria(request.getParameter("special"));
						model.doUpdate(bean, id);
						

						File fileSaveDir = new File(savePath);
						if (!fileSaveDir.exists()) {
							fileSaveDir.mkdir();
						}
						
						for (Part part : request.getParts()) {
							String fileName = extractFileName(part);
							if (fileName != null && !fileName.equals("")) {
								
							
								part.write(savePath + File.separator + fileName);
								model.updatePhoto(id, savePath + File.separator + fileName);
								System.out.println("ho ricevuto la foto");
							}
								
						}
			
						
						RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/product?id="+request.getParameter("isbn"));
						dispatcher.forward(request, response);
						
					}
				}
				
			} catch (Exception e) {
				System.out.println("Error: "+e.getMessage());
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
	
	
	 private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length()-1);
	            }
	        }
	        return "";
	    }		

}
//Untangled partizione: 3//Untangled partizione: 10