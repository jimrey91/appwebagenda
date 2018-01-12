package com.reyes.javaee.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reyes.javaee.ejb.EjbUsuario;
import com.reyes.javaee.ejbinterfaces.IEjbUsuario;
import com.reyes.javaee.jb.JbUsuario;


/**
 * Servlet implementation class ServletUsuarioEditar
 */
@WebServlet("/ServletUsuarioEditar")
public class ServletUsuarioEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IEjbUsuario iEjbUsuario; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioEditar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		iEjbUsuario = new EjbUsuario();
		
		HttpSession httpSession = request.getSession();
		
		iEjbUsuario.getByIdUsuario((int)httpSession.getAttribute("idUsuario"));
		
		JbUsuario jbUsuario = new JbUsuario();
		jbUsuario.setIdUsuario(iEjbUsuario.getUsuario().getIdUsuario());
		jbUsuario.setNombre(iEjbUsuario.getUsuario().getNombre());
		jbUsuario.setApellido(iEjbUsuario.getUsuario().getApellido());
		jbUsuario.setFechaNacimiento(iEjbUsuario.getUsuario().getFechaNacimiento());
		jbUsuario.setCorreoElectronico(iEjbUsuario.getUsuario().getCorreoElectronico());
		jbUsuario.setContrasenia(iEjbUsuario.getUsuario().getContrasenia());
		jbUsuario.setFechaRegistro(iEjbUsuario.getUsuario().getFechaRegistro());
		jbUsuario.setFechaActualizacion(iEjbUsuario.getUsuario().getFechaActualizacion());
		
		request.setAttribute("usuario", jbUsuario);
		
		if(httpSession.getAttribute("correcto")!=null){
			request.setAttribute("correcto", httpSession.getAttribute("correcto"));
			request.setAttribute("mensajeGeneral", httpSession.getAttribute("mensajeGeneral"));
			
			httpSession.removeAttribute("correcto");
			httpSession.removeAttribute("mensajeGeneral");
		}
		request.getRequestDispatcher("usuario/editar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		iEjbUsuario = new EjbUsuario();
		
		HttpSession httpSession = request.getSession();
				
		iEjbUsuario.getByIdUsuario((int)httpSession.getAttribute("idUsuario"));
		
		iEjbUsuario.setCorreoElectronicoAnterior(iEjbUsuario.getUsuario().getCorreoElectronico());
		
		iEjbUsuario.getUsuario().setNombre(request.getParameter("txtNombre"));
		iEjbUsuario.getUsuario().setApellido(request.getParameter("txtApellido"));
		iEjbUsuario.getUsuario().setFechaNacimiento(request.getParameter("dateFechaNacimiento"));
		iEjbUsuario.getUsuario().setCorreoElectronico(request.getParameter("txtCorreoElectronico"));
		
		iEjbUsuario.setContraseniaAnterior("");
		
		if(request.getParameter("radioCambiarContrasenia").equals("Si")){
			iEjbUsuario.setContraseniaAnterior(request.getParameter("passContraseniaAnterior"));
			iEjbUsuario.setContraseniaNueva(request.getParameter("passContraseniaNueva"));
			iEjbUsuario.setContraseniaRepita(request.getParameter("passContraseniaNuevaRepita"));
		}
		
		Map<String, String> returnMap = iEjbUsuario.update();
		
		httpSession.setAttribute("correcto", returnMap.get("correcto"));
		httpSession.setAttribute("mensajeGeneral", returnMap.get("mensajeGeneral"));
		
		response.sendRedirect("/appwebagenda/ServletUsuarioEditar");
	}

}
