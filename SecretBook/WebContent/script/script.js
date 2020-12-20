// JavaScript Document

function myfunc(x) //gestisce la view di signup/registrazione
{
	var myLabel= document.getElementById(x.id);
	var title=document.getElementById("formTitle");
	if(myLabel.id=="inLabel") //se sto nel sign-in
		{
			myLabel.style="color:rgba(238,133,114,1.00); border-bottom: 3px solid rgba(238,133,114,1.00);";
			document.getElementById("upLabel").style="color:white; border-bottom: 3px solid white";
			title.innerHTML="ACCEDI";
			title.style="display:block";
			document.getElementById("signin").style="display:block;";
			document.getElementById("signup").style="display:none;";
		}
	else //se sto nel sign up
		{
			myLabel.style="color:rgba(238,133,114,1.00); border-bottom: 3px solid rgba(238,133,114,1.00);";
			document.getElementById("inLabel").style="color:white; border-bottom: 3px solid white";
			title.innerHTML="REGISTRATI";
			title.style="display:block";
			document.getElementById("signup").style="display:block;";
			document.getElementById("signin").style="display:none;";
		
		}
	
}


function toggle(x,str) //gestisce la visibilità della password
{
	var toggle;
	var input;
	if(str===0)
		{
		input=document.getElementById("pwField");
		toggle=document.getElementById("eyeIcon");
		}
	else
		{
		input=document.getElementById("pwField1");
		toggle=document.getElementById("eyeIcon1");
		}
	
	if(input.type=== "password")
		{
			input.type="text";
			toggle.classList.remove("fa-eye-slash");
			toggle.classList.add("fa-eye");
		}
	else
		{
			input.type="password";
			toggle.classList.remove("fa-eye");
			toggle.classList.add("fa-eye-slash");
			
		}
	
}

function showLoginForm(isLogged) //gestisce il minilogin in header
		{
			var log= document.getElementById("notLogged");
			var container=document.getElementById("miniLog");
			var logged= document.getElementById("logged");
			console.log(isLogged);
			if(!isLogged)
				{
			if(!container.classList.contains("active"))
				{
					log.style.display="block";
					container.classList.add("active");
				}
			else{
				log.style.display="none";
				container.classList.remove("active");
			}
				}else{
					if(!container.classList.contains("active"))
						{
							logged.style.display="block";
							container.classList.add("active");
							
						}else{
							logged.style.display="none";
							container.classList.remove("active");
							}
				
			}
			
		}

function checkUsername(text)
{
	
	var name=/^[a-zA-Z0-9]+([_-]?[a-zA-Z0-9])*$/;
	if(text.match(name))
		return true;
	return false;
}

function checkEmail(text)
{
	var email=/^\w+([\.-]?\w+)*@\w+([1.-]?\w+)*(\.\w{2,3})+$/;
	if(text.match(email))
		return true;
	return false
	
}


function checkPassword(text)
{
	var password=/^(?:(?=.*?[A-Z])(?:(?=.*?[0-9])(?=.*?[-!@#$%^&*()_[\]{},.<>+=])|(?=.*?[a-z])(?:(?=.*?[0-9])|(?=.*?[-!@#$%^&*()_[\]{},.<>+=])))|(?=.*?[a-z])(?=.*?[0-9])(?=.*?[-!@#$%^&*()_[\]{},.<>+=]))[A-Za-z0-9!@#$%^&*()_[\]{},.<>+=-]{7,50}$/;
	if(text.match(password))
		return true;
	return false;

}








/*function validate(obj)
{


	jQuery.ajaxSetup({async:false});
	
    var form=document.getElementById("signup");
    var validate=true;
     notinDB=true;
     function returnData(x)
  	{
  		notinDB=notinDB && x;
  		console.log("sono stato chiamato : "+x);
  	}
     function validateUsername(username)
 	{
 		$.get("./validate?action=user&username="+username.value,
 				function(data)
 				{
 					returnData(data);
 					
 				}
 			);
 		
 	}
     function validateEmail(email)
     {
    	 $.get("./validate?action=address&email="+email.value,
    				function(data)
    				{
    					returnData(data);
    					
    				}
    			);
     }
  

    
    //controllo username
     

	var username=document.getElementsByName("username")[1];
	username.id="username";
	console.log(username.value);
	
	var usernameError=document.createElement("p");
	usernameError.id=("uError");
	usernameError.style="color:red; text-transform: none;"
	usernameError.innerHTML="Username non valida. La username pu&ograve; contenere solo cifre, numeri, trattino basso (_) e trattino alto (-). Non pu&ograve; iniziare o terminare con simboli. Riprovare."
	
	var emailError=document.createElement("p");
	emailError.id=("eError");
	emailError.style="color:red; text-transform: none;"
	emailError.innerHTML="Formato email non valido. Formato accettato: example1234@example.it"
		
		
	var pwError=document.createElement("p");
	pwError.id=("pwError");
	pwError.style="color:red; text-transform: none;"
	pwError.innerHTML="La password deve essere di minimo 7 caratteri, deve contenere un carattere MAIUSCOLO, uno minuscolo, un numero e un carattere non alfanumerico (?!@-). Riprovare."
	
	
	if(!checkUsername(username))
    {
      $("#username").css("border","1px solid red");
      validate=false;
      if(!document.getElementById(uError).childNodes.contains(usernameError))
    	  document.getElementById("un").appendChild(usernameError);
      
    }
    else
    {
        $("#username").css("border","");
        validateUsername(username);
        document.getElementById("uError").style="display:none";
    }
	
	var email=document.getElementsByName("email")[0];
	email.id="email";
	console.log(email.value);
	if(!checkEmail(email))
	{
	      $("#email").css("border","1px solid red");
	      validate=false;
	      document.getElementById("em").appendChild(emailError);
	 }
	 else
	 {
	        $("#email").css("border","");
	        document.getElementById("eError").style="display:none";
	        validateEmail(email);
	 }
	
	var password=document.getElementsByName("password")[1];
	password.id="password";
	console.log(password.value);
	if(!checkPassword(password))
		{
			$("password").css("border","1px solid red");
			validate=false;
			document.getElementById("pw").appendChild(pwError);
		}
	else
		{
		$("password").css("border","");
		document.getElementById("pwError").style="display:none";
		}
	
	console.log(notinDB);
	
	if(validate && notinDB) obj.submit();
	
}*/

function validate(object)
{
	var nome=$("#regName").val();
	var cognome=$("#regSurnmae").val();
	var username=$("#regUser").val();
	var email=$("#regMail").val();
	var pw=$("#pwField").val();
	
	$.ajax({
		url:'./validator',
		method:"POST",
		data: {
			user:username,
			mail:email,
		},
		success:function(response,object)
		{
			var result=true;
			
			console.log(response.username)
			if(checkEmail($("#regMail").val())==false)
			{
				$("#noEmail").html("Inserire un email nel formato: eg@domain.nat");
				$("#noEmail").show();
				result=result && false;
			}
			else{
				$("#noEmail").hide();
				if(!response.email)
				{
					$("#noEmail").html("Email gi&agrave; presente");
					$("#noEmail").show();
					result=result && false;
				}
				else
					$("#noEmail").hide(); result=result && true;
			}
			
			if(checkUsername($("#regUser").val())==false)
			{
				$("#noUsername").html("Username non valida. La username pu&ograve; contenere solo cifre, numeri, trattino basso (_) e trattino alto (-). Non pu&ograve; iniziare o terminare con simboli. Riprovare.");
				$("#noUsername").show();
				result=result && false;
			}
			else{
				$("#noUsername").hide();
				if(!response.username)
				{
					$("#noUsername").html("Username gi&agrave; presente");
					$("#noUsername").show();
					result=result && false;
				}
				else
					$("#noUsername").hide(); result=result && true;
			}
			
			if(checkPassword($("#pwField").val())==false)
			{
				$("#noPass").html("La password deve essere di minimo 7 caratteri, deve contenere un carattere MAIUSCOLO, uno minuscolo e un numero. Riprovare.");
				$("#noPass").show();
				result=result && false;
			}
			else
				$("#noPass").hide(); result=result && true;
				
				
				
				console.log(result);
				if(result)
					{
					$("#signup").submit();
					}
				
		},
		error:function(){
			
		}
		
	});

}

function checkLogin(event)
{
	event.preventDefault();
	var username= document.getElementById("logUser").value;
	var password= document.getElementById("logPass").value;
	$.ajax({
		url:'./checkLogin',
		method:"POST",
		data: {
			username:username,
			password:password
		},
		success:function(response,event){
			
			var result=JSON.parse(response);
			if(result===true)
				{
					document.getElementById("miniformLog").submit();
				}
			else{
				createAlert("Errore accesso","Credenziali non valide. Riprovare")
			}
		},
		error:function()
		{
			alert("errore chiamata async");
		}
	});
}
function preventAction(check, event)
{
	console.log("funzione check");
	if(!check)
		{
		event.preventDefault();
		createAlert("Errore accesso","Accedi per visualizzare la lista.")
		}
	else
		{
		event.submit();
		}
	
}

function openMenu()
{
	$("#mobilemenu").show();
	$(".footerContainer").hide();
	
}
function closeMenu()
{	
	$("#mobilemenu").hide();
	$(".footerContainer").show();
}

function checkForm(event)
{
	event.preventDefault();
	var username= document.getElementById("usernameField1").value;
	var password= document.getElementById("pwField1").value;
	$.ajax({
		url:'./checkLogin',
		method:"POST",
		data: {
			username:username,
			password:password
		},
		success:function(response,event){
			
			var result=JSON.parse(response);
			if(result===true)
				{
				document.getElementById("signin").submit();
				}
			else{
				createAlert("Errore accesso","Credenziali non valide. Riprovare")
			}
		},
		error:function()
		{
			alert("errore chiamata async");
		}
	});
	
	}


function showEditor(type)
{
	console.log("mi hai chiamato");
	var form;
	var container=document.getElementById("editContainer");
	document.getElementById("editContainer").scrollIntoView();
	var title=document.getElementById("editorTitle");
	if(type==="utente")
		{
			document.getElementById("modificaDatiIndirizzo").style="display:none;";
			document.getElementById("modificaDatiPagamento").style="display:none;";
			document.getElementById("aggiungiDatiIndirizzo").style="display:none;";
			document.getElementById("aggiungiDatiPagamento").style="display:none;";
			form=document.getElementById("modificaDatiUtente");
			title.innerHTML="Modifica Dati Utente";
		}
	if(type==="indirizzo")
		{
			document.getElementById("modificaDatiUtente").style="display:none;";
			document.getElementById("modificaDatiPagamento").style="display:none;";
			document.getElementById("aggiungiDatiIndirizzo").style="display:none;";
			document.getElementById("aggiungiDatiPagamento").style="display:none;";
			form=document.getElementById("modificaDatiIndirizzo");
			title.innerHTML="Modifica Dati Indirizzo";
		}
	if(type==="pagamento")
		{
			document.getElementById("modificaDatiUtente").style="display:none;";
			document.getElementById("modificaDatiIndirizzo").style="display:none;";
			document.getElementById("aggiungiDatiIndirizzo").style="display:none;";
			document.getElementById("aggiungiDatiPagamento").style="display:none;";
			form=document.getElementById("modificaDatiPagamento");
			title.innerHTML="Modifica Dati Pagamento";
		}
	if(type==="addP")
		{	
			document.getElementById("modificaDatiIndirizzo").style="display:none;";
			document.getElementById("aggiungiDatiIndirizzo").style="display:none;";
			document.getElementById("modificaDatiPagamento").style="display:none;";
			document.getElementById("modificaDatiUtente").style="display:none;";
			form=document.getElementById("aggiungiDatiPagamento");
			title.innerHTML="Aggiungi Dati Pagamento";
		}
	if(type==="addI")
		{
			document.getElementById("modificaDatiIndirizzo").style="display:none;";
			document.getElementById("aggiungiDatiPagamento").style="display:none;";
			document.getElementById("modificaDatiPagamento").style="display:none;";
			document.getElementById("modificaDatiUtente").style="display:none;";
			form=document.getElementById("aggiungiDatiIndirizzo");
			title.innerHTML="Aggiungi Dati Indirizzo";
		}
	container.style="display:block;"
	form.style="display:block;"
	
}

function checkQt(qt,ev)
{
	var req=document.getElementById("#qtReq").value;
	if(req>qt)
		{
		ev.preventDefault();
		createAlert("Errore Quantita'!","La quantita' inserita non e' disponibile per il libro selezionato. Riprovare.")
		}
	else {
		console.log("ok va bene");
		$("#buttonQt").submit();
	}

}


function checkOrder(isLogged,event)
{
	if(isLogged)
		{
			event.submit();
		}
	else{
		event.preventDefault();
		createAlert("Errore accesso","Accedere per completare l'ordine. Riprovare")
	}

}


function createAlert(title,descrizione)
{
	$("#alertTitle").text(title);
	$("#alertDescription").text(descrizione);
	
	$("#alert").show();
		
}

function closeAlert(){
	
	$("#alert").hide();
}

