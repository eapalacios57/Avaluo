package com.segurosbolivar.avaluos.filtro;

import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.NonexistentConversationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.planificador.util.JwtTokenUtil;

/**
 * Filtro el ingreso desde una URL para verificar si existe una sesion activa y
 * si el usuario tiene permisos para ingresar a una opcion especifica del
 * sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 11-sep-2017 09:55:19 a.m.
 */
public class SesionFiltro implements Filter {
	

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
	

		final String PROPERTIES = "/config.properties";
		final String PLANIFICADOR_URL_SEGURIDAD = "url.planificador.seguridad";
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession sesion = request.getSession(false);
		String url = request.getRequestURI();
		String autorizacion = request.getHeader("Authorization");
		
		
		
		/*
		* Adicionado para manejo autenticaci�n lrm
		*/
		if(url.contains("api")) {             
            try {
            	Properties prop = new Properties();
				prop.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES));
				url = prop.getProperty(PLANIFICADOR_URL_SEGURIDAD);
				JwtTokenUtil.validarToken(autorizacion,url);
			} catch (Exception e) {
				response.sendRedirect(request.getContextPath() + AvaluosCons.RUTA_NO_AUTORIZADO);			
			}
            chain.doFilter(request, response);
            return;
        }
		/*if (url == null) {
			response.sendRedirect(request.getContextPath() + AvaluosCons.RUTA_NO_AUTORIZADO);
			chain.doFilter(request, response);
			return;
		}*/
		// Excepciones
//		if (url.endsWith(AvaluosCons.RUTA_PAG_IMPRESION) || url.endsWith(AvaluosCons.RUTA_LOGIN_HTML) || url.endsWith(AvaluosCons.RUTA_NO_AUTORIZADO)) {
		if (url.endsWith(AvaluosCons.RUTA_PAG_IMPRESION) || url.endsWith(AvaluosCons.RUTA_NO_AUTORIZADO)) {	
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
		try {
//			if(sesion != null) {
//				getValuesFromSession(sesion);
//			}
			
			if (sesion == null || sesion.getAttribute(AvaluosCons.CREDENCIAL) == null) {
				response.sendRedirect(request.getContextPath());
//				response.sendRedirect(AvaluosCons.RUTA_CIERRE_SESION);
				chain.doFilter(request, response);
				return;
			} else {
				request.setCharacterEncoding("UTF-8");
				chain.doFilter(request, response);
			}

		} catch (ServletException e) {
			if (e.getCause() instanceof NonexistentConversationException) {
				HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request);
				String context = request.getContextPath();
				String viewId = request.getRequestURI();
				String path = viewId.replaceFirst(context, "");
				request.getRequestDispatcher(path).forward(wrapper, res);
			} else {
				throw e;
			}
		}

		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

//	private void getValuesFromSession(HttpSession session) {
//		Enumeration<?> e = session.getAttributeNames();
//
//		while (e.hasMoreElements()) {
//			String p = (String) e.nextElement();
//			Object o = session.getAttribute(p);
//			if (o != null) {
//				serializableCheck(o, p);
//			}
//		}
//	}

//	private void serializableCheck(Object o, String objName) {
//		try {
//			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			ObjectOutputStream oos = new ObjectOutputStream(out);
//			oos.writeObject(o);
//			oos.close();
//			if (out.toByteArray().length > 0) {
//				log.info(o.getClass().getCanonicalName() +" IS SERIALIZABLE");
//			} else {
//				log.info("NOT SERIALIZABLE");
//			}
//		} catch (IOException io) {
//			System.out.println(o+" IS NOT SERIALIZABLE");
//		}
//	}
}