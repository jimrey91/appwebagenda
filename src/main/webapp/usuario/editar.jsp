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
	<jsp:useBean id="usuario" class="com.reyes.javaee.jb.JbUsuario" scope="request"></jsp:useBean>
	<%@include file="/parciales/header.jsp" %>
    <section>
    	<form id="frmEditarUsuario" action="/appwebagenda/ServletUsuarioEditar" method="post">
    		<h2>Mis datos personales</h2>
    		<br>
    		<label for="txtNombre" class="label">Nombre</label>
    		<input type="text" class="text" id="txtNombre" name="txtNombre" value="<%= usuario.getNombre() %> ">
    		<br>
		    <label for="txtApellido" class="label">Apellido</label>
    		<input type="text" class="text" id="txtApellido" name="txtApellido" value="<%= usuario.getApellido()%> ">
    		<br>
    		<label for="dateFechaNacimiento" class="label">Fecha de Nacimiento</label>
    		<input type="date" class="text" id="dateFechaNacimiento" name="dateFechaNacimiento" value="<%= usuario.getFechaNacimiento()%> ">
    		<br>
   		    <label for="txtCorreoElectronico" class="label">Correo Electronico</label>
    		<input type="text" class="text" id="txtCorreoElectronico" name="txtCorreoElectronico" value="<%= usuario.getCorreoElectronico()%> ">
    		<br>
    		<label for="radioCambiarContrasenia" class="label">Cambiar contraseña</label>
    		<label for="radioCambiarContraseniaSi">Si</label>
    		<input type="radio" id="radioCambiarContraseniaSi" name="radioCambiarContrasenia" value="Si" onchange="onChangeRadioCambiarContrasenia()">
    		<label for="radioCambiarContraseniaNo">No</label>
    		<input type="radio" id="radioCambiarContraseniaNo" name="radioCambiarContrasenia" value="No" checked="checked" onchange="onChangeRadioCambiarContrasenia()">
    		<div id="divCambiarContrasenia" style="display: none">
	    		<label for="passContraseniaAnterior" class="label">Contraseña anterior</label>
	    		<input type="password" class="password" id="passContraseniaAnterior" name="passContraseniaAnterior">
	    		<br>
	    		<label for="passContraseniaNueva" class="label">Contraseña nueva</label>
	    		<input type="password" class="password" id="passContraseniaNueva" name="passContraseniaNueva">
	    		<br>
		    	<label for="passContraseniaNuevaRepita" class="label">Repita Contraseña nueva</label>
	    		<input type="password" class="password" id="passContraseniaNuevaRepita" name="passContraseniaNuevaRepita">
    		<br>
    			
    		</div>
    		<br>
    		<input type="submit" value="Guardar datos" class="button" onclick="enviarFrmEditar();">
    	</form>
    
    </section>
    <script>
    	function onChangeRadioCambiarContrasenia(){
    		if($('input[name=radioCambiarContrasenia]:checked').val()=='Si'){
    			$('#divCambiarContrasenia').show();
    		}else{
    			$('#divCambiarContrasenia').hide();
    			$('#passContraseniaAnterior').val('');
    			$('#passContraseniaNueva').val('');
    			$('#passContraseniaNuevaRepita').val('');
    		}
    		
    		function enviarFrmEditar(){
    			if($('#passContraseniaNueva').val()===$('#passContraseniaNuevaRepita').val()){
    				return $('#frmEditarUsuario').submit();
    			}
    			alert('Las contraseñas no coiciden');
    		}
    	}
    </script>
</body>

</html>