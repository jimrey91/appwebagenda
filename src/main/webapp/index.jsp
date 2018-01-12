<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
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
    <header>
    	<nav class="menu">
    		<ul>
    			<li><a href="">Inicio</a></li>
    			<li><a href="">Registrar datos en el sistema</a></li>
    			<li><a href="">Ver los datos registrados</a></li>
    		</ul>
    	</nav>
    </header>
       <section>
       		<label class="label">Este es el label</label>
       		<input type="text" class="text" id="txt1">
       		<br>
       		<label class="label">Este es el label</label>
       		<input type="password" class="password">
       		<br>
       		<label class="label">Este es el label</label>
       		<textarea class="textArea"></textarea>
       		<br>
       		<label class="label">Estes es el label</label>
       		<select class="table">
       			<option>Item 1</option>
       		</select>
       		<br>
       		<table class="table">
       		<thead>
       			<tr>
	       			<th>Cabecera 1</th>
	       			<th>Cabecera 2</th>
	       			<th>Cabecera 3</th>
       			</tr>
       		</thead>
       		<tr>
       			<td>Item 1</td>
       			<td>Item 2</td>
       			<td>Item 3</td>
       		</tr>
       		<tr>
       			<td>Item 1</td>
       			<td>Item 2</td>
       			<td>Item 3</td>
       		</tr>
       		</table>
       		<br>
       		<input type="button" value="Este es un boton" class="button" onclick="prueba();">
       		<div id="divDialogo">Dialogo</div>
       </section>
    </body>
    <script type="text/javascript">
    	function prueba(){
    		alert($('#txt1').val());
    		$('#divDialogo').dialog();
    	}
    </script>
</html>
