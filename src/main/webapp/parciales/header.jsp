<header>
    <nav class="menu">
    		<ul>
    			<li><a href="/appwebagenda/ServletActividadInsertar">Insertar actividades</a></li>
    			<li><a href="/appwebagenda/ServletActividadGetByIdUsuario">Ver actividades</a></li>   			
    			<li><a href="/appwebagenda/ServletUsuarioInsertar">Insertar usuario</a></li>
    			<li><a href="/appwebagenda/ServletUsuarioEditar">Editar usuario</a></li>
    		</ul>
    </nav>
    <div id="divLogin">
    	<b>Usuario</b>
    	<%
    		if(request.getSession().getAttribute("correoElectronico")!= null){
  		%>
    			<%= request.getSession().getAttribute("correoElectronico")%> |
    			<a href="/appwebagenda/ServletUsuarioLogout">Cerrar Sesión</a>
   			<%
    		}else{
    		%>
    		<%="Anonimo" %>
    	 <% 
    		}
    	 %>
    </div>
    <%
		if(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("Si")){
		%>
			<div class="divAlertaCorrecto"><%= request.getAttribute("mensajeGeneral") %></div>
		<%		
			}
			
			if(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")){
		%>
			<div class="divAlertaError"><%= request.getAttribute("mensajeGeneral") %></div>
		<%
			} 
    %>	
 
</header>