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

/**
 * Servlet implementation class ServletUsuarioLogin
 */
@WebServlet("/ServletUsuarioLogin")
public class ServletUsuarioLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IEjbUsuario iEjbUsuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("usuario/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		iEjbUsuario = new EjbUsuario();
		
		iEjbUsuario.getByCorreoElectronico(request.getParameter("txtCorreoElectronico"));
		Map<String,String> returnMap =iEjbUsuario.login(request.getParameter("passContrasenia"));
		
		
		request.setAttribute("correcto", returnMap.get("correcto"));
		request.setAttribute("mensajeGeneral", returnMap.get("mensajeGeneral"));
		
		if(returnMap.get("correcto").equals("Si")){
			HttpSession httpSession = request.getSession();
			
			httpSession.setAttribute("idUsuario", iEjbUsuario.getUsuario().getIdUsuario());
			httpSession.setAttribute("correoElectronico", request.getParameter("txtCorreoElectronico"));
			response.sendRedirect("/appwebagenda/ServletUsuarioEditar");
		}else{
			request.getRequestDispatcher("usuario/login.jsp").forward(request, response);
		}
		
	}

}
