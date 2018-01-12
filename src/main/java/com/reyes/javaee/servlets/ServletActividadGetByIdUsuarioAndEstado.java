package com.reyes.javaee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reyes.javaee.ejb.EjbActividad;
import com.reyes.javaee.ejbinterfaces.IEjbActividad;
import com.reyes.javaee.entidades.Actividad;
import com.reyes.javaee.jb.JbActividad;

/**
 * Servlet implementation class ServletActividadGetByIdAndEstado
 */
@WebServlet("/ServletActividadGetByIdUsuarioAndEstado")
public class ServletActividadGetByIdUsuarioAndEstado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IEjbActividad iEjbActividad;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActividadGetByIdUsuarioAndEstado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		iEjbActividad = new EjbActividad();
		
		iEjbActividad.getByIdUsuarioAndEstado((int)request.getSession().getAttribute("idUsuario"), Boolean.parseBoolean(request.getParameter("estado")));
		
		List<JbActividad> listaJbActividad = new ArrayList<>();
		
		for(Actividad item : iEjbActividad.getListaActividad()){
			
			JbActividad jbActividad = new JbActividad();
			jbActividad.setIdActividad(item.getIdActividad());
			jbActividad.setNombre(item.getNombre());
			jbActividad.setDescripcion(item.getDescripcion());
			jbActividad.setLugar(item.getLugar());
			jbActividad.setEstado(item.getEstado());
			jbActividad.setFechaHoraInicio(item.getFechaHoraInicio());
			jbActividad.setFechaHoraFin(item.getFechaHoraFin());
			jbActividad.setFechaRegistro(item.getFechaRegistro());
			
			listaJbActividad.add(jbActividad);
		}
		request.setAttribute("listaActividad", listaJbActividad);
		
		request.getRequestDispatcher("actividad/ver.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
