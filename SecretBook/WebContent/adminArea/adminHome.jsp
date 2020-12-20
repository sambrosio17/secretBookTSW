<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
<title>Pannello Admin - SecretBook</title>
</head>
<body>

<jsp:include page="menuAdmin.jsp"></jsp:include>

<div class="adminHome">
		
	<div class="adminForm">
	<h3>Cerca ordine per utente</h3>
	<form action="adminOrder">
	<input type="text" name="type" value="user" hidden>
	<label for="id">Nome utente</label>
	<input type="text" name="id" placeholder="nome utente">
	<input type="submit" value="Cerca">
	</form>
	</div>
		
	<div class="adminForm">
	<h3>Cerca ordine per intervallo date</h3>
	<form action="adminOrder">
	<input type="text" name="type" value="date" hidden>
	<label for="start">Data inzio</label>
	<input type="date" name="start">
		<br><br>
	<label for="end">Data fine</label>
	<input type="date" name="end">
		<br>
		<br>
	<input type="submit" value="Cerca">
	</form>
	</div>
	
	</div>
	

</body>
</html>