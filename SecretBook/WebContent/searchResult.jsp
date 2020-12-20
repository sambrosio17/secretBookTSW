<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.LinkedList" %>
    <%@page import="it.SecretBook.model.*" %>
<!DOCTYPE html>
<% LinkedList<Product> list=(LinkedList<Product>) request.getAttribute("result");
	if(list==null)
	{
		response.sendRedirect("./404-notfound.jsp");
		return;
	}

%>


<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="style.css" rel="stylesheet" type="text/css">
<title>Hai cercato: <%=request.getAttribute("queryString") %></title>
</head>
<body>

<div>
<jsp:include page="header.jsp"></jsp:include>
</div>
<jsp:include page="alert.jsp"></jsp:include>

<div class="pageBody">

</div>
		<%if(list!=null){
			%>
		<h1 class="pageTitle">Risultati della ricerca "<%=request.getAttribute("queryString") %>": <%=list.size() %></h1>
		<%} %>
		<div class="resultContainer">
		<%if(!list.isEmpty()){
			%>
		<ul class="searchList">
		<%
		for(int i=0; i<list.size(); i++){
			
			Product p=(Product) list.get(i);
		%>
			<li ><a href="./product?id=<%=p.getIsbn() %>" style="color:rgba(35,49,62,1.00); text-transform: uppercase; text-decoration: none;"><%=p.getNome() %></a> - #<%=p.getCategoria() %></li>
			<%} %>
		</ul>
		<%} %>
		</div>

<div>
<jsp:include page="footer.jsp"></jsp:include>
</div>

</body>
</html>