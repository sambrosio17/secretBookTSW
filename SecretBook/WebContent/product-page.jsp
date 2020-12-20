<%@page import="it.SecretBook.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%Product p= (Product) request.getAttribute("prod");
    if(p==null)
    {
    	response.sendRedirect("./404-notfound.jsp");
    	return;
    }%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
<link href="style.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<title><%=p.getNome() %> - SecretBook</title>
</head>
<body>
<div>
<jsp:include page="header.jsp"></jsp:include>
</div>
<jsp:include page="alert.jsp"></jsp:include>
<div class="pageBody">
<div class="productPageRow">
		<div class="productPhoto">
			<img src="./productPicture?id=<%=p.getIsbn()%>">
		</div>
		<div class="productInfo">
		<h5 class="category">#<%=p.getCategoria() %></h5>
		<h1 class="tilte"><%=p.getNome() %></h1>
		<h5 class="price"><%=p.getCost() %> &euro;</h5>
		<p class="quotes"><%=p.getDescrizione() %></p>
		<form class="prodForm" action="Cart" method="post">
		<input type="hidden" name="itemId" value="<%=p.getIsbn()%>" >
		<input type="hidden" name="action" value="addItem" >
		<label for="quantity">Quantità:</label>
		<input type="number" name="qt" min="1" max="10" class="qtInput" value="1" required id="#qtReq"><br>
			<button type="submit" class="sendButton" onclick="checkQt(<%=p.getQt()%>,event)" id="buttonQt"><em class="fas fa-shopping-cart icon"></em> aggiungi al carrello</button>
		</form>
		</div>
	</div>


</div>
<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>

</body>
</html>