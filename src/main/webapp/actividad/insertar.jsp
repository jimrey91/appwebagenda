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
    	<form id="frmInsertarActividad" action="/appwebagenda/ServletActividadInsertar" method="post">
    		<h2>Registrar actividad</h2>
    		<hr>
    		<label for="txtNombre" class="label">Nombre</label>
    		<input type="text" class="text" id="txtNombre" name="txtNombre" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtNombre") : "" %> ">
    		<br>
    		<label for="txtDescripcion" class="label">Descripcion</label>
    		<textarea id="txtDescripcion" name="txtDescripcion" class="textArea"></textarea>
    		<br>
		    <label for="txtLugar" class="label">Lugar</label>
    		<input type="text" class="text" id="txtLugar" name="txtLugar" value="<%= (request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("txtLugar") : "" %> ">
    		<br>
    		<label for="dateFechaInicio" class="label">Fecha de Inicio</label>
    		<input type="datetime-local" class="text" id="dateFechaInicio" name="dateFechaInicio" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("dateFechaInicio") : "" %> ">
    		<br>
   		    <label for="dateFechaFin" class="label">Fecha de Finalizacion</label>
    		<input type="datetime-local" class="text" id="dateFechaFin" name="dateFechaFin" value="<%=(request.getAttribute("correcto")!=null && request.getAttribute("correcto").equals("No")) ? request.getParameter("dateFechaFin") : "" %> ">
    		<br>
    		<input type="submit" value="Registrar actividad" class="button">
    	</form>
    
    </section>
</body>
</html>