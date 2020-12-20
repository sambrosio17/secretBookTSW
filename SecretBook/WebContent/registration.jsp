<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accedi o Registrati - SecretBook.it</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

<div class="regFormArea">
			<div class="leftSideReg">

			<img src="https://images.pexels.com/photos/1926988/pexels-photo-1926988.jpeg" alt="immagine libri" >
				
			</div>
			<div class="rightSideReg">
				<h3 class="labelButton" id="inLabel" onClick="myfunc(this)">SIGN IN</h3>
				<h3 class="labelButton" id="upLabel" onClick="myfunc(this)">SIGN UP</h3>
				<div class="formArea">
				<h3 id="formTitle">REGISTRAZIONE</h3>
					<form action="./SignUp" method="post" class="signupForm" id="signup" >
						<label for="nome">Nome:
						<input type="text" name="nome" placeholder="John" required id="regName"></label>
						<p style="color:red; display:none;" id="noName">.</p>
						<label for="cognome">Cognome:
						<input type="text" name="cognome" placeholder="Doe" required id="regSurname"></label>
						<p style="color:red; display:none;" id="noSurname">.</p>
						<label for="username" id="un">Username:
						<input type="text" name="username" placeholder="jdoe" required id="regUser"></label>
						<p style="color:red; display:none;"id="noUsername">.</p>
						<label for="email"  id="em">Email:
						<input type="text" name="email" placeholder="john@doe.it" required id="regMail"></label>
						<p style="color:red; display:none; "id="noEmail">.</p>
						<div class="inputPassword">
						<label for="password" id="pw">password:
						<input type="password" name="password" placeholder="yourpassword" id="pwField" required></label>
						<p style="color:red; display:none;" id="noPass">.</p>
						<span class="fas fa-eye-slash eyePos" onClick="toggle(this,0)" id="eyeIcon"></span>
						</div>
						
						<label class="checkboxLabel">
						<input type="checkbox" value="true" required>
						<span class="customCheckbox">oo</span>
						<span class="label">Autorizzo al trattamento dei miei dati personali.</span>
						</label>
						
						<input type="submit" value="Registrami!" class="signMeUp" onclick="event.preventDefault(); validate(this)">
						
</form>
					<form action="./Login" method="post" class="signupForm" id="signin">
						<label for="username">Username:</label>
						<input type="text" name="username" placeholder="" required id="usernameField1">
						<div class="inputPassword">
						<label for="password">password:</label>
						<input type="password" name="password"  id="pwField1" required>
						<span class="fas fa-eye-slash eyePos" onClick='toggle(this,1)' id="eyeIcon1"></span>
						</div>
							
						
						<input type="submit" value="Accedi" class="signMeUp" onclick="checkForm(event);">
						
</form>
				</div>
			</div>
		
		</div>


</div>


<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>

</body>
</html>