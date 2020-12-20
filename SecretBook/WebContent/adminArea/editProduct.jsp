<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.LinkedList"%>
    <%@page import="it.SecretBook.model.Product" %>
    <%@page import="java.util.Iterator"%>
    
    <% Product p= (Product) request.getAttribute("product");
    if(p==null)
    {
    	response.sendRedirect("../404-notfound.jsp");
		return;
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pannello Admin - SecretBook</title>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
</head>
<jsp:include page="menuAdmin.jsp"></jsp:include>
<jsp:include page="../alert.jsp"></jsp:include>

<div class="pageBody">
<h1 class="pageTitle">MODIFICA <%=p.getNome() %></h1>
<div class="insertProduct">
			<form action="admin" class="productForm" enctype="multipart/form-data" method="post">
			  <div class="sectionForm">
			  <input type="hidden" name="action" value="doEdit" hidden="true">
				<label for="isbn">ISBN</label>
					<input type="text" name="isbn" readonly value="<%=p.getIsbn()%>">
				  <label for="titolo">TITOLO</label>
					<input type="text" name="titolo"  placeholder="<%=p.getNome()%>">
				  <label for="descrizione">DESCRIZIONE</label>
					<input type="text" name="descrizione"  placeholder="<%=p.getDescrizione() %>" class="descript">
				   <label for="prezzo">PREZZO</label>
					<input type="text" name="prezzo"  placeholder="<%=p.getPrezzo() %>" class="prezzo">
				  <label for="prezzo">QUANTITà</label>
					<input type="text" name="qnt"  placeholder="<%=p.getQt() %>" class="prezzo">
				</div>
				<div class="sectionForm">
				<label for="isbn">FOTO</label>
					<input type="file" class="file" name="foto"  placeholder="inserisci foto">
					<label for="categoria">CATEGORIA</label>
					<input type="text" name="categoria" placeholder="<%=p.getCategoria()%>">
					<label for="special">special</label>
					<input type="text" name="special" placeholder="inserisci special" value="">
					<label for="prezzo">IVA</label>
					<input type="text" name="iva" placeholder="<%=p.getIva() %>" class="prezzo">
				</div>
		<button type="submit">INSERISCI</button>	
		</form>
	  </div>
	  
</body>
</html>