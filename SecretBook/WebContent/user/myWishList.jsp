<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.SecretBook.model.*"%>
    <%@page import="java.util.ArrayList"%>
<%
	Boolean isLogged= (Boolean) request.getSession().getAttribute("isLogged");
	WishList w= (WishList) request.getSession().getAttribute("wishlist");
	ArrayList<Product> list=null;
	User u=null;
	if(isLogged==null || isLogged==false || w==null)
	{
		response.getWriter().println("<p>Accedi per proseguire</p>");
		response.sendRedirect("./404-notfound.jsp");
		return;
	}
	else if(isLogged==false)
	{
		response.getWriter().println("<p>Accedi per proseguire</p>");
	}
	if(w==null)
	{
		response.sendRedirect("./wishlist");
	}
	else{
		list=w.getProdotti();
		u=w.getUtente();
	}
	if(list==null)
	{
		response.sendRedirect("./404-notfound.jsp");
		response.getWriter().println("<p>Accedi per proseguire</p>");
	}
	if(u==null)
	{
		response.sendRedirect("./404-notfound.jsp");
		return;
	
	}
%>    
    
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
<title>WishList di <%=u.getUsername() %> </title>
</head>
<body>
<div>
<jsp:include page="../header.jsp"></jsp:include>
</div>

<jsp:include page="../alert.jsp"></jsp:include>

<div class="pageBody">
<div class="favList">
	<h1 class="pageTitle">I miei preferiti</h1>
	<div class="listContainer">
	<%if(w!=null && !w.getProdotti().isEmpty())
		{
			ArrayList<Product> listP=w.getProdotti();
			for(int i=0; i<list.size(); i++)
			{
				Product p=listP.get(i);

		%>
	<div class="cartItem">
			<div class="itemPhoto">
			<img src="./productPicture?id=<%=p.getIsbn()%>">
			</div>
			<div class="itemTitle">
				<a href="./product?id=<%=p.getIsbn()%>"><h3><%=p.getNome() %></h3></a>
			</div>
			<div class="itemInfo">
			<h1><%=p.getCost() %> &euro;</h1>
			<a href="./wishlist?action=remove&id=<%=p.getIsbn()%>"><h6>rimuovi prodotto</h6></a>
			</div>
			</div>
			<%} 
			}else {
			%>
			<h2 class="noItemWarning">NESSUN ELEMENTO NELLA LISTA</h2>
			<%} %>
	</div>
	
	</div>

</div>



<div>
<jsp:include page="../footer.jsp"></jsp:include>
</div>



</body>
</html>