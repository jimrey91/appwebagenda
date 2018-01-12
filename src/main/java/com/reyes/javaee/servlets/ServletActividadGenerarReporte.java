package com.reyes.javaee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reyes.javaee.ejb.EjbActividad;
import com.reyes.javaee.ejbinterfaces.IEjbActividad;
import com.reyes.javaee.entidades.Actividad;
import com.reyes.javaee.jb.JbActividad;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Servlet implementation class ServletActividadGenerarReporte
 */
@WebServlet("/ServletActividadGenerarReporte")
public class ServletActividadGenerarReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IEjbActividad iEjbActividad;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActividadGenerarReporte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		iEjbActividad = new EjbActividad();
		
		iEjbActividad.getByIdUsuario((int)request.getSession().getAttribute("idUsuario"));
		
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
		
		try {
			byte[] bytes = JasperRunManager.runReportToPdf(this.getServletContext().getRealPath("/WEB-INF/classes/com/reyes/javaee/report/ReporteActividades.jasper"), null, new JRBeanCollectionDataSource(listaJbActividad));
			
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();
			ServletInputStream inputStream = request.getInputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
