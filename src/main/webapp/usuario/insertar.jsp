<!doctype html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Agenda Web</title>
		<link rel="stylesheet" href="/appwebagenda/resources/css/cssLayout.css" type="text/css">
        <link rel="stylesheet" href="/appwebagenda/resources/css/cssComponente.css" type="text/css">
        <link rel="stylesheet" href="/appwebagenda/resources/css/jquery-ui.min.css" type="text/css">
        
        <script type="text/javascript" src="/appwebagenda/resources/js/jquery-2.1.3.min.js"></script>
        <script type="text/javascript" src="/appwebagenda/resources/js/jquery-ui.min.js"></script>
	</head>
<body>
	<%@include file="/parciales/header.jsp" %>
    <section>
    	<form id="frmInsertarUsuario" action="/appwebagenda/ServletUsuarioInsertar" method="post">
    		<h2>Registrarse en el sistema</h2>
    		<br>
    		<label for="txtNombre" class="label">Nombre</label>
    		<input type="text" class="text" id="txtNombre" name="txtNombre" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtNombre") : "" %> ">
    		<br>
		    <label for="txtApellido" class="label">Apellido</label>
    		<input type="text" class="text" id="txtApellido" name="txtApellido" value="<%= (request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtApellido") : "" %> ">
    		<br>
    		<label for="dateFechaNacimiento" class="label">Fecha de Nacimiento</label>
    		<input type="date" class="text" id="dateFechaNacimiento" name="dateFechaNacimiento" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("dateFechaNacimiento") : "" %> ">
    		<br>
   		    <label for="txtCorreoElectronico" class="label">Correo Electronico</label>
    		<input type="text" class="text" id="txtCorreoElectronico" name="txtCorreoElectronico" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtCorreoElectronico") : "" %> ">
    		<br>
    		<label for="passContrasenia" class="label">Contraseña</label>
    		<input type="password" class="password" id="passContrasenia" name="passContrasenia">
    		<br>
	    	<label for="passContraseniaRepita" class="label">Repita Contraseña</label>
    		<input type="password" class="password" id="passContraseniaRepita" name="passContraseniaRepita">
    		<br>
    		<input type="submit" value="Registrarme" class="button">
    	</form>
    
    </section>
</body>
</html>