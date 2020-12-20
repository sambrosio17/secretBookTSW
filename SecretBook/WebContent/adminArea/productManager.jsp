<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList"%>
    <%@page import="it.SecretBook.model.Product" %>
    <%@page import="java.util.Iterator"%>
   <% LinkedList<Product> products=(LinkedList) request.getAttribute("products");
   Boolean admin= (Boolean)request.getSession().getAttribute("isAdmin");
   Boolean logged=(Boolean) request.getSession().getAttribute("isLogged");
   if(admin==null || !admin || logged==null || !logged)
   {
	   response.sendRedirect("../404-notfound.jsp");
		return;
   }
   else
   {
   if(products==null)
   {
	   response.sendRedirect("../admin?action=manage");
	   return;
   }
   }
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestione Prodotti - SecretBook</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="menuAdmin.jsp"></jsp:include>

<jsp:include page="../alert.jsp"></jsp:include>

<div class="pageBody">

<h1 class="pageTitle">GESTIONE PRODOTTI</h1>

<table class="productTable">
				<th>isbn</th>
				<th>foto</th>
				<th>titolo</th>
				<th>descrizione</th>
				<th>categoria</th>
				<th>prezzo</th>
				<th>iva</th>
				<th>quantità</th>
				<th>special</th>
				<th>action</th>
				
<%if(products!=null)
	{
		Iterator it=products.iterator();
		while(it.hasNext())
		{
			Product p=(Product) it.next();%>
			<tr>
				<td><%=p.getIsbn()%></td>
				<td><img src="./productPicture?id=<%=p.getIsbn() %>"></td>
				<td><%=p.getNome()%></td>
				<td><%=p.getDescrizione() %></td>
				<td><%=p.getCategoria() %></td>
				<td><%=p.getPrezzo() %>&euro;</td>
				<td><%=p.getIva() %>&euro;</td>
				<td><%=p.getQt() %></td>
				<td><%=p.getSpecial() %></td>
				<td><a href="./admin?action=delete&id=<%=p.getIsbn() %>" class="fas fa-minus-circle"></a>
					<a href="./product?id=<%=p.getIsbn() %>" class="fas fa-external-link-alt"></a>
					<a href="./admin?action=edit&id=<%=p.getIsbn() %>" class="fas fa-pen"></a>
				</td>
			</tr>
<% }%>
			
			
			</table>
<%} %>


</body>
</html>