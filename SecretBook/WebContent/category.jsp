<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>
    <%@page import="it.SecretBook.model.*" %>
    
   	<%
   	LinkedList<Product> products= (LinkedList<Product>) request.getAttribute("catalog");
   	Category c=(Category) request.getAttribute("category");
   	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
<title><%=c.getTitolo() %> - SecretBook.it</title>
</head>
<body>

<div>
<jsp:include page="header.jsp"></jsp:include>
</div>

<jsp:include page="alert.jsp"></jsp:include>

<div class="categoryPage">
		 <div class="categoryBillboard"  style="background: url('<%=c.getPercorso()%>')">
			 <h1 id="categoryTitle"><%=c.getTitolo() %></h1>
			 <blockquote id="quote">.</blockquote>
		</div>
		
		<%if(products!=null && !products.isEmpty())
	{ %>
	<div class="productRow">
	<% 
		for(int i=0; i<products.size(); i++)
		{
			Product p=(Product) products.get(i);
		%>
			
		

		<% 
			if(i%4!=0)
			{ %>
			
			<div class="singleProduct">
			<div class="info-card" style="background:url(./productPicture?id=<%=p.getIsbn() %>); background-position: center;
	background-size: cover;">
						<h2>
							<%=p.getNome() %>	
						</h2>
						<p>
						<%=p.getDescrizione() %>	
						</p>
						<a href="Cart?action=addItem&itemId=<%=p.getIsbn() %>&qt=1" class="fas fa-shopping-cart icon"  title="aggiungi al carrello"></a>
						<a href="./wishlist?action=add&id=<%=p.getIsbn() %>" class="fas fa-heart icon"  title="aggiungi alla tua lista" onclick="preventAction(<%=session.getAttribute("isLogged") %>, event)"></a>
						<a href="./product?id=<%=p.getIsbn() %>" class="fas fa-arrow-right icon"  title="visualizza prodotto"></a>
					</div>
		</div>
			<% 
				
			}else
			{ %>
				</div>
				<div class="productRow">
				<div class="singleProduct">
			<div class="info-card" style="background:url(./productPicture?id=<%=p.getIsbn() %>); background-position: center;
	background-size: cover;">
						<h2>
							<%=p.getNome() %>	
						</h2>
						<p>
						<%=p.getDescrizione() %>	
						</p>
						<a href="Cart?action=addItem&itemId=<%=p.getIsbn() %>&qt=1" class="fas fa-shopping-cart icon"  title="aggiungi al carrello"></a>
						<a href="./wishlist?action=add&id=<%=p.getIsbn() %>" class="fas fa-heart icon"  title="aggiungi alla tua lista" onclick="preventAction(<%=session.getAttribute("isLogged") %>, event)"></a>
						<a href="./product?id=<%=p.getIsbn() %>" class="fas fa-arrow-right icon"  title="visualizza prodotto"></a>
					</div>
		</div>
			<%
			}
		}%>
		</div>
		<%
	}
	%>
</div>

<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
$(document).ready(function(){
	console.log("documento pronto");
	var str=$("#categoryTitle").text();
	var catNumber;
	if(str==="ADRENALINA")
		catNumber=0;
	if(str==="BRIVIDI LUNGO LA SCHIENA")
		catNumber=1;
	if(str==="DAL PROFONDO DEL CUORE")
		catNumber=2;
	if(str==="IN CERCA DI AVVENTURA")
		catNumber=3;
	if(str==="REALITY++")
		catNumber=4;
	
	
	$("#categoryTitle").show(2000);
	$(".categoryBillboard").css("filter","grayscale(0)");
	$.ajax({
		url:'./getQuote',
		method:'POST',
		data:{
			cat:catNumber,
		},
		success:function(response){
		$("#quote").html(response);
		$("#quote").show(2000);
	},
	error:function()
	{
		alert("errore chiamata async");
	}
	});		
				  
})

</script>

</body>
</html>