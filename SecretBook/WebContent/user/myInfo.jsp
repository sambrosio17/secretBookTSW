<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.SecretBook.model.*" %>
    <% User u= (User) session.getAttribute("user");
    	Address a= (Address) session.getAttribute("address");
    	CreditCard c=(CreditCard) request.getAttribute("creditCard");
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
<title>@<%=u.getUsername() %> - i miei dati</title>
</head>
<body>

<div>
<jsp:include page="../header.jsp"></jsp:include>
</div>

<jsp:include page="../alert.jsp"></jsp:include>

<div class="pageBody">

<div class="myDataSection">
		 	<div class="userInfo">
			 	<h3>I tuoi dati</h3>
				<div class="innerInfoBlock">
					<div class="squaredPic" style="background:url(./userPicture?username=<%=u.getUsername() %>)"></div>
				<div class="innerUserInfo">
				<h5>Nome</h5>
					<span><%=u.getNome() %></span>
				<h5>Cognome:</h5>
					<span><%=u.getCognome() %></span>
				<h5>Username:</h5>
					<span>@<%=u.getUsername() %></span>
				<h5>Email:</h5>
					<span><%=u.getEmail() %></span>
					</div>
					<a href="#editInfo" onclick="showEditor('utente')"> <i class="far fa-edit"></i> Modifica</a>
				</div>
			</div>
			 <div class="addressInfo">
				<h3>Il tuo indirizzo</h3>
				 <div class="innerInfoBlock">
				<div class="innerUserInfo">
				<%if(a==null)
				{%>
			
					<h2 style="color:red;">Nessun Indirizzo inserito</h2>
					<a href="#editInfo" onclick="showEditor('addI')"> <i class="far fa-edit"></i> Aggiungi</a>
					<%}else
					{%>
				<h5>Via</h5>
					<span><%= a.getVia() %></span>
				<h5>Civico</h5>
					<span><%=a.getNum() %></span>
				<h5>CAP</h5>
					<span><%=a.getCap() %></span>
				<h5>Città</h5>
					<span><%=a.getCitta() %></span>
				<h5>Stato</h5>
					<span><%=a.getNazione() %></span><br>
					<a href="#editInfo" onclick="showEditor('indirizzo')"> <i class="far fa-edit"></i> Modifica</a>
					 <%} %>
					</div>
					
				</div>
			 </div>
			 <div class="userPaymentInfo">
			 	<h3>I tuoi dati di pagamento</h3>
				 <div class="innerInfoBlock">
					 <div class="innerUserInfo">
					 <%if(c==null){ %>
					  <h2 style="color:red;">Nessun Metodo di Pagamento inserito</h2>
					  <a href="#editInfo" onclick="showEditor('addP')"> <i class="far fa-edit"></i> Aggiungi</a>
					  <%}else{ %>
				<h5>Intestario</h5>
					<span><%=c.getIntestatario() %></span>
				<h5>Numero</h5>
					<span><%=c.getCod() %></span>
				<h5>Data di scadenza</h5>
					<span><%=c.getScad().getMonth() %>/<%=c.getScad().getYear() %></span><br>
					<a href="#editInfo" onclick="showEditor('pagamento')"> <i class="far fa-edit"></i> Modifica</a>
					<%} %>
					</div>
					
				</div>
				
			 </div>
		 </div>
		 <div class="editorContainer" id="editContainer">
		 <h3 id="editorTitle">Modifica dati</h3>
			<div id="modificaDatiUtente">
			<span style="display:block; color:red; padding-left:0.2em;">Lasciare immutati i campi che non si vuole modificare!</span>
			<form method="post" action="./editUser" enctype="multipart/form-data">
			<span>Aggiorna Immagine: </span> <input type="file" name="photo">	
			<span>Nome: </span><input type="text" name="nome" value="<%=u.getNome()%>">
			<span>Cognome: </span><input type="text" name="cognome" value="<%=u.getCognome()%>" >
			<span>Email: </span><input type="text" name="email" value="<%=u.getEmail()%>">
			<span>Data di nascita: </span> <input type="date" name="datanasc" value="<%=u.getDataDiNascita()%>">
			<span>Nome utente: </span><input type="text" name="nomeUtente" value="<%=u.getUsername()%>">
			<input type="submit" value="Modifica">
			</form> 
			</div>
			 <div id="modificaDatiIndirizzo">
			<form method="post" action="./editAddress">
			<input name="tipo" value="modificaIndirizzo" type="hidden">
			<span>Via: </span> <input type="text" name="via" >	
			<span>Civico: </span><input type="text" name="civico" >
			<span>CAP: </span><input type="text" name="cap">
			<span>Città: </span><input type="text" name="citta" >
			<span>Stato: </span><input type="text" name="stato">
			<input type="submit" value="Modifica">
			</form> 
			</div>
			 <div id="modificaDatiPagamento">
			 <span style="display:block; color:red; padding-left:0.2em;">Completare il form in tutti i suoi campi per sostituire il metodo di pagamento.</span>
			<form method="post" action="./editCC">
			<span>Intestatario: </span> <input type="text" name="intestatario" placeholder="John Doe" required>	
			<span>Numero: </span><input type="tel" inputmode="numeric" name="numero" pattern="[0-9\s]{13,19}" autocomplete="cc-number" maxlength="19" placeholder="xxxx xxxx xxxx xxxx" required>
			<span>Data di scadenza </span><input type="month" name="data" required>
			<span>CVC </span><input type="number" name="cvc" maxlength="3" required>
			<input type="submit" value="Modifica">
			</form> 
			</div>
			<div id="aggiungiDatiIndirizzo">
			<form method="post" action="./addAddress">
			<span>Via: </span> <input type="text" name="via" placeholder="tal dei tali" required>	
			<span>Civico: </span><input type="text" name="civico" required>
			<span>CAP: </span><input type="text" name="cap" required>
			<span>Città: </span><input type="text" name="citta" required>
			<span>Stato: </span><input type="text" name="stato" required>
			<input type="submit" value="Aggiungi">
			</form> 
			</div>
			 <div id="aggiungiDatiPagamento">
			<form method="post" action="./addCC">
			<span>Intestatario: </span> <input type="text" name="intestatario" placeholder="John Doe" required>	
			<span>Numero: </span><input type="number" inputmode="numeric"  name="numero" pattern="[0-9\s]{13,19}" autocomplete="cc-number" maxlength="19" placeholder="xxxx xxxx xxxx xxxx" required>
			<span>Data di scadenza </span><input type="month" name="data" required>
			<span>CVC </span><input type="number" name="cvc" maxlength="3" required>
			<input type="submit" value="Aggiungi">
			</form> 
			</div>
		 </div>


</div>

 <div>
<jsp:include page="../footer.jsp"></jsp:include>
</div>

</body>
</html>