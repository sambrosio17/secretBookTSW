<div class="header">
		<div class="upperHeader"> <!-- inizio riga header-->
		<div class="menuMobile">
			<i class="fas fa-bars icon" id="hamburger" onclick="openMenu();"></i><!--copiare-->
		</div>
			<div class="logoArea"><!-- logo area-->
				<a href="./index.jsp"><img src="./logos.png" class="logo"></a>
			</div><!--fine logo area--> 
			<div class="searchArea"> 
				<form action="search" method="get">
			<input type="search" name="q" class="searchForm" placeholder="Search..." >
		</form>
			</div><!-- search area -->
			<div class="iconArea"> 
				<a href="#user"><em class="fas fa-user icon" onClick="showLoginForm(<%=session.getAttribute("isLogged")%>);"></em></a>
				<%Boolean check=true;
				if(session.getAttribute("isLogged")==null)
				{
					check=false;
				}
				%>
				<a href="./wishlist" onclick="preventAction(<%=check%>,event);"><em class="fas fa-heart icon"></em></a>
				<a href="./Cart"><em class="fas fa-shopping-cart icon"></em></a>
			</div><!-- icon area -->
		</div><!-- fine riga header -->
		<div class="nav">
			<a href="./catalog?cat=0" class="menuLink">Adrenalina</a>
			<a href="./catalog?cat=1" class="menuLink">Brividi lungo la schiena</a>
			<a href="./catalog?cat=2" class="menuLink">Dal profondo del cuore</a>
			<a href="./catalog?cat=3" class="menuLink">In cerca di avventura</a>
			<a href="./catalog?cat=4" class="menuLink">Reality++</a>
				
			</div><!-- fine nav-->
			<div class="miniLog" id="miniLog">
		<div id="notLogged">
		<h3>Login</h3>
		<form action="./Login" method="post" id="miniformLog">
		<input type="text" name="username" placeholder="username" id="logUser">
		<input type="password" name="password" placeholder="password" id="logPass">
		<!--<a href="#lostPassword">Password dimenticata?</a>-->
		<input type="submit" id="miniLogSub" value="Accedi" class="signMeUp" onclick="checkLogin(event);">
		</form>
		<span>Sei un nuovo cliente?</span><a href="./registration.jsp">Registrati</a>
		</div>
			<div id="logged">
			<a href="./mySpace">La mia Area Personale</a>
			<a href="./Logout">Esci</a>
			</div>
		</div>
	</div>
	<div class="mobileMenuContainer" id="mobilemenu">
	<div class="mmcLogoArea"><em class="fas fa-times icon" id="closemenu" onclick="closeMenu();"></em>
	  <h1>SECRETBOOK.IT</h1>
		</div>
		<hr> <!--separatore-->
		<div class="menuItems">
		<a href="./index.jsp"><h5>home</h5></a>
		<a href="./catalog?cat=3"><h5>in cerca di avventura</h5></a>
		<a href="./catalog?cat=4"><h5>reality++</h5></a>
		<a href="./catalog?cat=1"><h5>brividi lungo la schiena</h5></a>
		<a href="./catalog?cat=2"><h5>dal profrondo del cuore</h5></a>
		<a href="./catalog?cat=0"><h5>adrenalina</h5></a>
		</div>
		<hr>
		<div class="menuIcon">
		<%Boolean log=(Boolean) session.getAttribute("isLogged");
		if( log==null ||log!=true){
		%>
			<a href="./registration.jsp"><em class="fas fa-user icon"></em></a>
			<%}
				else{%>
				<a href="./mySpace"><em class="fas fa-user icon"></em></a>
				<a href="./Logout"><em class="fas fa-sign-out-alt icon"></em></a>
				<%} %>
				<%
				if(session.getAttribute("isLogged")==null)
				{
					check=false;
				}
				%>
				<a href="./wishlist" onclick="preventAction(<%=check%>,event);"><em class="fas fa-heart icon"></em></a>
				<a href="./Cart"><em class="fas fa-shopping-cart icon"></em></a>
		</div>
	</div>
