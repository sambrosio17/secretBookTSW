<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="it.SecretBook.model.*" %>
<!DOCTYPE html>
<% User u= (User) session.getAttribute("user");
	if(u==null){
		response.sendRedirect("./registration.jsp");
		return;
		}%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
<link href="style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<title> @<%=u.getUsername() %> | La mia Area Personale </title>
</head>
<body>
<div>
<jsp:include page="../header.jsp"></jsp:include>
</div>

<jsp:include page="../alert.jsp"></jsp:include>
 <div class="pageBody">
 <div class="userArea">
<%if(u!=null){
	%>

		 <div class="upperSection">
			<div class="userImg" style="background:url(./userPicture?username=<%=u.getUsername() %>)">
			</div>
			 <h1><%=u.getNome() %> <%=u.getCognome() %></h1>
			 <h3>@<%=u.getUsername() %></h3>
		</div>
		<div class="actionSection">
		<a href="./mySpace?q=info">I miei dati</a>
<hr>
		<a href="./order">I miei ordini</a>
		<%if((Boolean) session.getAttribute("isAdmin")) {%>
		<hr>
		<a href="./admin">Pannello Amministratore</a>
		<%} %>
		</div>
		<%}%>
	</div> 

 </div>
 
 
 <div>
<jsp:include page="../footer.jsp"></jsp:include>
</div>
 
</body>
</html>