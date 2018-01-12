<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<jsp:useBean id="listaActividad" class="java.util.ArrayList<com.reyes.javaee.jb.JbActividad>" scope="request"></jsp:useBean>
	<%@include file="/parciales/header.jsp"%>
	<section>
	<div>
		<a href="/appwebagenda/ServletActividadGetByIdUsuario">Todas las actividades</a>
		-
		<a href="/appwebagenda/ServletActividadGetByIdUsuarioAndEstado?estado=true">Actividades finalizadas</a>
		-
		<a href="/appwebagenda/ServletActividadGetByIdUsuarioAndEstado?estado=false">Actividades pendientes</a>
		-
		<a href="/appwebagenda/ServletActividadGenerarReporte" target="_blank">Generar Reporte</a>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripcion</th>
				<th>Lugar</th>
				<th>Estado</th>
				<th>Fecha Inicio</th>
				<th>Fecha Fin</th>
				<th>Fecha Registro</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaActividad}" var="item">
				<tr>
					<td>${item.getNombre()}</td>
					<td>${item.getDescripcion()}</td>
					<td>${item.getLugar()}</td>
					<td>${item.isEstado() ? "Finalizado" : "Pendiente"}</td>
					<td>${item.getFechaHoraInicio()}</td>
					<td>${item.getFechaHoraFin()}</td>
					<td>${item.getFechaRegistro()}</td>
					<td>
						<c:if test="${item.isEstado()==true}">
	  						<input type="button" value="Restaurar actividad" onclick="restaurarActividad(${item.getIdActividad()})">
						</c:if>
						
						<c:if test="${item.isEstado()==false}">
							<input type="button" value="Terminar actividad" onclick="terminarActividad(${item.getIdActividad()})">
						</c:if>
						
				</td>
				</tr>
			
			</c:forEach>
		</tbody>
	</table>
	</section>
</body>
<script type="text/javascript">
	function terminarActividad(idActividad){
		
		window.location.href="/appwebagenda/ServletActividadCambiarEstado?idActividad=" +idActividad + "&estado=true";
	}
	
	function restaurarActividad(idActividad){
		window.location.href="/appwebagenda/ServletActividadCambiarEstado?idActividad=" +idActividad + "&estado=false";
	}

</script>



</html>