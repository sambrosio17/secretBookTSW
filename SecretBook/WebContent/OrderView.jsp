<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="java.util.LinkedList"%>
     <%@page import="java.util.ArrayList"%>
    <%@page import="it.SecretBook.model.Product" %>
    <%@page import="it.SecretBook.model.Cart" %>
    <%@page import="it.SecretBook.model.CartItem" %>
    <%@page import="it.SecretBook.model.Order" %>
    <%@page import="java.util.Iterator"%>
    <%@page import="it.SecretBook.model.*" %>
    
    <%User u=(User) session.getAttribute("user");
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
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
<link href="style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<title>@<%=u.getUsername() %> | I miei ordini</title>
</head>
<body>
<div>
<jsp:include page="header.jsp"></jsp:include>
</div>
<jsp:include page="alert.jsp"></jsp:include>
<div class="pageBody">
<div class="orderSection">
		 <div class="orderSectionHeader">
			 <h3>Ordini di</h3> <h3>@<%=u.getUsername() %></h3>
			 
		</div>
		<%ArrayList<Order> ordini= (ArrayList<Order>) request.getAttribute("orders");
				if(ordini==null || ordini.isEmpty()){
				%>
				<h1>NESSUN ORDINE EFFETTUATO</h1>
				<%}
				else {
				%>
			<table>
			 	<th>id</th>
				<th>data</th>
				<th>stato</th>
				<th>Prezzo Totale </th>
				<th>azione</th>
				<%for(int i=0; i<ordini.size(); i++)
				{
					Order o=ordini.get(i);
					%>
				<tr>
					<td><%=o.getOrderId() %></td>
					<td><%=o.getData() %></td>
					<td><%=o.getStatus() %></td>
					<td><%=o.getPrezzoTotale() %>&euro;</td>
					<td><a href="./order?id=<%=o.getOrderId()%>">visualizza fattura</a></td>
				</tr>
				<%}
				}%>
				
			 </table> 
		 </div>
</div>
<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>