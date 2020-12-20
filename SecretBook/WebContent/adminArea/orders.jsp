<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*" %>
    <%@page import="it.SecretBook.model.*" %>
    
    <%  User u=(User) session.getAttribute("user");
    	Boolean isAdmin=(Boolean) session.getAttribute("isAdmin");
    	if(u==null || isAdmin==null || !isAdmin)
    	{
    		response.sendRedirect("./404-notfound.jsp");
    		return;
    	}
    	ArrayList<Order> list=(ArrayList<Order>) request.getAttribute("lista");
    	/**if(list==null)
    	{
    		System.out.println("lista == null");
    		response.sendRedirect("./adminOrder");
    		return;
    	}**/
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="style.css" rel="stylesheet" type="text/css">
<title>ORDINI - SECRETBOOK</title>
</head>
<body>
<jsp:include page="menuAdmin.jsp"></jsp:include>
<div class="pageBody">
		<div class="orderSection">
		 <div class="orderSectionHeader">
		 <%if(list==null || list.isEmpty()){ %>
			 <h3>NESSUN ORDINE TROVATO</h3>
			 <%}else{ %>
			 	<h3>Ordini trovati <%=list.size() %></h3>
		</div>
			<table>
			 	<th>id</th>
				<th>utente</th>
				<th>data</th>
				<th>stato</th>
				<th>azione</th>
				<%for(int i=0; i<list.size(); i++)
					{
						Order o=list.get(i);%>
				<tr>
					<td><%=o.getOrderId() %></td>
					<td><%=o.getUser() %></td>
					<td><%=o.getData() %></td>
					<td><%=o.getStatus() %></td>
					<td><a href="./adminOrder?type=fattura&n=<%=o.getOrderId()%>">visualizza fattura</a></td>
				</tr>
				<%} %>
			 </table> 
			 <%} %>
		 </div>
	</div>

</body>
</html>