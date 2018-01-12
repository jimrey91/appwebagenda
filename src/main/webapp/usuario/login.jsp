<!doctype html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Agenda Web</title>
        <link rel="stylesheet" href="/appwebagenda/resources/css/cssComponente.css" type="text/css">
        <link rel="stylesheet" href="/appwebagenda/resources/css/jquery-ui.min.css" type="text/css">
        
        <script type="text/javascript" src="/appwebagenda/resources/js/jquery-2.1.3.min.js"></script>
        <script type="text/javascript" src="/appwebagenda/resources/js/jquery-ui.min.js"></script>
	</head>
<body>
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
    <section>
    	<form id="frmEditarUsuario" action="/appwebagenda/ServletUsuarioLogin" method="post">
    		<h2>Iniciar Sesion</h2>
    		<hr>
    		<label for="txtCorreoElectronico" class="label">Correo Electronico</label>
    		<input type="text" class="text" id="txtCorreoElectronico" name="txtCorreoElectronico">
    		<br>
    		<label for="passContrasenia" class="label">Contraseña</label>
    		<input type="password" class="password" id="passContrasenia" name="passContrasenia">
   			<br>
    		<input type="submit" value="Ingresar" class="button">
    	</form>   
    </section>

</body>

</html>