<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    Boolean admin= (Boolean)request.getSession().getAttribute("isAdmin");
    Boolean logged=(Boolean) request.getSession().getAttribute("isLogged");
    if(admin==null || !admin || logged==null || !logged)
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
<title>Pannello Admin - SecretBook</title>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="./style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="menuAdmin.jsp"></jsp:include>
<jsp:include page="../alert.jsp"></jsp:include>

<div class="pageBody">
<h1 class="pageTitle">INSERISCI NUOVO PRODOTTO</h1>
<div class="insertProduct">
			<form action="../admin" class="productForm" enctype="multipart/form-data" method="post">
			  <div class="sectionForm">
			  <input type="hidden" name="action" value="insert" hidden="true">
				<label for="isbn">ISBN</label>
					<input type="text" name="isbn" required placeholder="inserisci isbn">
				  <label for="titolo">TITOLO</label>
					<input type="text" name="titolo" required placeholder="inserisci titolo">
				  <label for="descrizione">DESCRIZIONE</label>
					<input type="text" name="descrizione" required placeholder="inserisci descrizione" class="descript">
				   <label for="prezzo">PREZZO</label>
					<input type="text" name="prezzo" required placeholder="inserisci prezzo" class="prezzo">
				  <label for="prezzo">QUANTITà</label>
					<input type="text" name="qnt" required placeholder="inserisci quantità" class="prezzo">
				</div>
				<div class="sectionForm">
				<label for="isbn">FOTO</label>
					<input type="file" class="file" name="foto" required placeholder="inserisci foto">
					<label for="categoria">CATEGORIA</label>
					<input type="text" name="categoria" required placeholder="inserisci categoria">
					<label for="special">special</label>
					<input type="text" name="special" placeholder="inserisci special" value="">
					<label for="prezzo">IVA</label>
					<input type="text" name="iva" required placeholder="inserisci iva" class="prezzo">
				</div>
		<button type="submit">INSERISCI</button>	
		</form>
	  </div>


</body>
</html>