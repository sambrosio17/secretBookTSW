<%@page import="java.util.ArrayList"%>
<%@page import="it.SecretBook.model.*" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<%Order o= (Order) request.getAttribute("ordine");
User u=(User) request.getAttribute("user");
Address a=(Address) request.getAttribute("address");
if(o==null || u==null || a==null)
{
	response.sendRedirect("./404-notfound.jsp");
	return;

}%>
<title>Fattura n. <%=o.getOrderId() %></title>
<script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
<link href="newFatturaStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="outerDiv">
		<div class="fatturaSpace">
			<div class="line">S E C R E T B O O K . I T</div>
			<div class="innerFattura">
				<div class="fatturaIntestazione">
					<h1>Ordine n.<%=o.getOrderId() %></h1>	
					<h5> in data: <%=o.getData() %></h5>
				</div>
				<div class="dataDiv">
					<h1>SecretBook.it</h1>
					<h5>info@secretbook.it</h5>
					<h5>Via Lavianaio II Tratto, 65</h5>
					<h5>Ottaviano(NA)</h5>
					<h5>+39 081 5289964</h5>
					
				</div>
				<div class="dataDiv">
					<h1><%=u.getNome() %> <%=u.getCognome() %></h1>
					<h5><%=u.getEmail() %></h5>
					<h5><%=a.getVia() %>, <%=a.getNum() %></h5>
					<h5><%=a.getCitta() %>, <%=a.getCap() %></h5>
				</div>
				<div class="fatturaInfo">
					<table class="itemTable">
					<tr>
					<th>Articolo</th>	
					<th>Prezzo (no iva)</th>
					<th>IVA</th>
					<th>Prezzo Univoco</th>
					<th>Quantità</th>
					<th>Prezzo Totale</th>
					</tr>
						<tbody>
						<%ArrayList<CartItem> list=o.getList(); 
						if(list!=null && !list.isEmpty())
						{
							
							for(int i=0; i<list.size(); i++)
							{
								CartItem c=list.get(i);
								
						
						
						%>
						<tr><!--un riga di esempio-->
						<td><%=c.getProd().getNome() %></td>
						<td>&euro; <%=c.getPrice() %></td>
						<td>&euro; <%=c.getIva() %></td>
						<td>&euro; <%=c.getUnitCost() %></td>
						<td><%=c.getQuantity() %></td>
						<td>&euro; <%=c.getTotalCost() %></td>
						</tr>
						<%}
							}
							%>
						</tbody>
					</table>
					<h1>Totale</h1>
					<h3>&euro; <%=o.getPrezzoTotale() %></h3>
				</div>
				<button onClick="window.print()"><i class="fas fa-print"></i> Stampa Fattura</button>
			</div>
		</div>
	</div>
	
</body>
</html>
