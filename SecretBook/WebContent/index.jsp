<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList"%>
    <%@page import="it.SecretBook.model.Product" %>
    <%@page import="java.util.Iterator"%>
    <%@page import="java.util.Random" %>
   <% LinkedList<Product> products=(LinkedList) request.getAttribute("products");
   if(products==null)
   {
	   response.sendRedirect("./index");
	   return;
   }
   Product prod=(Product) request.getAttribute("product");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home - SecretBoook.it</title>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
</head>
<body>
<div>
<jsp:include page="header.jsp"></jsp:include>
</div>

<jsp:include page="alert.jsp"></jsp:include>


 <div class="categoryPage">
		 <div class="categoryBillboard" style="background: url('./images/category/home.jpg')">
			 <h1 id="categoryTitle" style="font-size:50px;">SECRETBOOK.IT</h1>
			 <blockquote id="quote" style="display:block;">Lascia che sia il libro a scegliere te.</blockquote>
			 <a href="./feelingLucky" class="feelingLucky">MI SENTO FORTUNATO!</a>
		</div>
		 </div>
<div class="pageBody">
<%if(products!=null && !products.isEmpty())
	{ %>
	<div class="productRow">
	<% 
		for(int j=0; j<4; j++)
		{
			Random r=new Random();
			int i= r.nextInt(products.size());
			Product p=(Product) products.get(i);
		%>
			
		

		<% 
			if(j%4!=0)
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
<div class="categoryPage">
		 <div class="categoryBillboard" style="background: url('./images/category/home1.jpg')">
			 <h1 id="categoryTitle" style="font-size:50px;">LA COPERTINA NON CONTA...</h1>
			 <blockquote id="quote1" style="display:block;">.</blockquote>
		</div>
</div>
<div class="pageBody">
<%if(products!=null && !products.isEmpty())
	{ %>
	<div class="productRow">
	<% 
		for(int j=0; j<8; j++)
		{
			Random r=new Random();
			int i= r.nextInt(products.size());
			Product p=(Product) products.get(i);
		%>
			
		

		<% 
			if(j%4!=0)
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
			cat:5,
		},
		success:function(response){
		$("#quote1").html(response);
		$("#quote1").show(2000);
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