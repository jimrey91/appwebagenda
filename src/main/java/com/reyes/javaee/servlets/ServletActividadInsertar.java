package com.reyes.javaee.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reyes.javaee.ejb.EjbActividad;
import com.reyes.javaee.ejbinterfaces.IEjbActividad;

/**
 * Servlet implementation class ServletActividadInsertar
 */
@WebServlet("/ServletActividadInsertar")
public class ServletActividadInsertar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IEjbActividad iEjbActividad;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActividadInsertar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("actividad/insertar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			HttpSession httpSession = request.getSession();
			iEjbActividad = new EjbActividad();
			
			iEjbActividad.getActividad().getUsuario().setIdUsuario(Integer.parseInt(httpSession.getAttribute("idUsuario").toString()));
			iEjbActividad.getActividad().setNombre(request.getParameter("txtNombre"));
			iEjbActividad.getActividad().setDescripcion(request.getParameter("txtDescripcion"));
			iEjbActividad.getActividad().setLugar(request.getParameter("txtLugar"));
			iEjbActividad.getActividad().setFechaHoraInicio(request.getParameter("dateFechaInicio"));
			iEjbActividad.getActividad().setFechaHoraFin(request.getParameter("dateFechaFin"));
			
			Map<String, String> returnMap = iEjbActividad.insert();
			
			request.setAttribute("correcto", returnMap.get("correcto"));
			request.setAttribute("mensajeGeneral", returnMap.get("mensajeGeneral"));
			
			request.getRequestDispatcher("actividad/insertar.jsp").forward(request, response);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
