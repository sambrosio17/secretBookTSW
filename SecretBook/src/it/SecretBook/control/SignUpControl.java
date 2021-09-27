package it.SecretBook.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.SecretBook.DAO.UserModel;
import it.SecretBook.model.User;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class SignUpControl extends HttpServlet {
	static String SAVE_DIR ="/uploadTemp";
	private static final long serialVersionUID = 1L;
     
	UserModel model=new UserModel();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User u=new User();
		
		String action=request.getParameter("action");
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		byte[] foto=null;
		
		
		System.out.println("ciao sono stata invocata");
		u.setNome(request.getParameter("nome"));
		u.setCognome(request.getParameter("cognome"));
		u.setUsername(request.getParameter("username"));
		u.setEmail(request.getParameter("email"));
		u.setRuolo("user");
		u.setSaldo(0);
		u.setDataDiNascita(null);
		u.setPassword(request.getParameter("password"));
		
		try {
			model.doSave(u);
			
			/*File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && !fileName.equals("")) {
					
				
					part.write(savePath + File.separator + fileName);
					model.updatePhoto(request.getParameter("username"), savePath + File.separator + fileName);
					System.out.println("ho ricevuto la foto");
				}
					
			}*/
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd= getServletContext().getRequestDispatcher("/registration.jsp");
		rd.forward(request, response);
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
