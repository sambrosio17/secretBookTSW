<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.LinkedList"%>
     <%@page import="java.util.ArrayList"%>
    <%@page import="it.SecretBook.model.Product" %>
    <%@page import="it.SecretBook.model.Cart" %>
    <%@page import="it.SecretBook.model.CartItem" %>
    <%@page import="java.util.Iterator"%>
    
   <%
   Cart cart= (Cart) request.getAttribute("cart");
   if(cart==null)
   {
	   response.sendRedirect("./Cart");
   }
   
   %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Carrello - SecretBook</title>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
</head>
<body>
<div>
<jsp:include page="header.jsp"></jsp:include>
</div>
<jsp:include page="alert.jsp"></jsp:include>
<div class="pageBody">
<h1 class="pageTitle">Carrello</h1>
<%if(cart!=null && !cart.isEmpty())
{
	%>
	<div class="products">
	<%
	for(int i=0; i<cart.size(); i++)
	{
		CartItem c= cart.getItems().get(i);
	%>
			<div class="cartItem">
			<div class="itemPhoto">
			<img src="./productPicture?id=<%=c.getISBN()%>">
			</div>
			<div class="itemTitle">
				<a href="./product?id=<%=c.getISBN()%>"><h3><%=c.getProd().getNome() %></h3></a>
			</div>
			<div class="itemInfo">
			<h1><%=c.getTotalCost() %> &euro;</h1>
			<h6> quantità: <%=c.getQuantity() %></h6>
			<form action="Cart" method="post">
				<input name="action" value="setQnt" type="hidden">
				<input name="itemId" value="<%=c.getISBN() %>" type="hidden">
				<label for="qt">Aggiorna quantità:</label><br>
				<input name="qt" value="1" type="number" min="1" max="10"><br>
				<button type="submit">aggiorna</button>
			</form>
			<a href="Cart?action=deleteItem&itemId=<%=c.getISBN()%>"><h6>rimuovi prodotto</h6></a>
			</div>
			</div>
	
		<%
	}
		%>
		</div>
		<div class="cartInfo">
		<div class="innerCartInfo">
		<h3>Riepilogo Ordine</h3>
		<hr>
		<h5 class="rowTitle">Articoli:</h5><h5 class="rowItem"><%=cart.getTotalQuantiy() %></h5><br>
		<h3 class="rowTitle">Totale:</h3><h3 class="rowItem"><%=cart.getTotalCost() %> &euro;</h3>
		<hr>
		<a href="./order?action=doOrder" onclick="checkOrder(<%=session.getAttribute("isLogged")%>,event)">procedi all'ordine</a>
		</div>
		</div>
	<%
	}
	else
	{
		%>
		<h2 class="noItemWarning" >NESSUN ELEMENTO NEL CARRELLO</h2>
		<a href="./" class="backHome">INIZIA LO SHOPPING</a>
		<% 
	}

	%>
</div>



<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>


</body>
</html>