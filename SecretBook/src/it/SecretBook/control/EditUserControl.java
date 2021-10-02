package it.SecretBook.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.SecretBook.DAO.*;
import it.SecretBook.model.*;

/**
 * Servlet implementation class EditInfoControl
 */
@WebServlet("/editUser")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class EditUserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String SAVE_DIR ="/uploadTemp";
	
	UserModel uModel= new UserModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("sto nella nuova servlet");
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		
			System.out.println("sto modificando l'utente");
			User u=(User) request.getSession().getAttribute("user");
			String preUser=u.getUsername();
			if(request.getParameter("nome")!=null && request.getParameter("nome")!="")
				u.setNome(request.getParameter("nome"));
			if(request.getParameter("cognome")!=null && request.getParameter("cognome")!="")
				u.setCognome(request.getParameter("cognome"));
			if(request.getParameter("email")!=null && request.getParameter("email")!="")
				u.setEmail(request.getParameter("email"));
			if(request.getParameter("datanasc")!=null && request.getParameter("datanasc")!="")
				{
					LocalDate date=LocalDate.parse(request.getParameter("datanasc"));
						u.setDataDiNascita(date);
				}
			if(request.getParameter("nomeUtente")!=null)
				u.setUsername(request.getParameter("nomeUtente"));

			
			try {
				uModel.doUpdate(preUser, u);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}
			
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && !fileName.equals("")) {
					
				
					part.write(savePath + File.separator + fileName);
					try {
						uModel.updatePhoto(u.getUsername(), savePath + File.separator + fileName);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("ho ricevuto la foto");
				}
					
			}
		
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/mySpace?q=info");
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
//Untangled partizione: 7//Untangled partizione: 12