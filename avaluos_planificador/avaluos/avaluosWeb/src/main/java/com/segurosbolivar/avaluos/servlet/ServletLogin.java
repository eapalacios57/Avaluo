package com.segurosbolivar.avaluos.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asesoftware.util.lang.UtilTexto;

@WebServlet("/servletlogin")
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = -2297546460083850189L;
	

	public ServletLogin() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String login = null;
			if (request.getParameter("login") != null)
				login = request.getParameter("login");
			else if (request.getAttribute("login") != null)
				login = (String) request.getAttribute("login");
			else {// No se recibe el login
				pintaError(request, response, "USUARIO NULO");
				return;
			}
			if (!UtilTexto.estaVacio(login)) {
				request.getSession().setAttribute("login", login);
				response.sendRedirect("/avaluos/loginMap.faces");
				return;
			}
			pintaError(request, response, "USUARIO NO VALIDO: ");
		} catch (Exception e) {
			e.printStackTrace();
			pintaError(request, response, e.toString());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String login = null;
			if (request.getParameter("login") != null)
				login = request.getParameter("login");
			else if (request.getAttribute("login") != null)
				login = (String) request.getAttribute("login");
			else {// No se recibe el login
				pintaError(request, response, "USUARIO NULO");
				return;
			}
			if (!UtilTexto.estaVacio(login)) {
				request.getSession().setAttribute("login", login);
				response.sendRedirect("/avaluos/loginMap.faces");
				return;
			}
			pintaError(request, response, "USUARIO NO VALIDO: ");
		} catch (Exception e) {
			e.printStackTrace();
			pintaError(request, response, e.toString());
		}
	}

	// Manejo de la p�gina de Error
	public void pintaError(HttpServletRequest request, HttpServletResponse response, String error)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuilder paginaError = new StringBuilder();
//		try {
			paginaError.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n" + 
					"<title>Acceso Denegado</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<header>\r\n" + 
					"		<div style=\"max-width: 100%; height: 45px; background-color: green; padding: 10px; margin-bottom: 40px\">\r\n" + 
					"			<img style=\"margin-left: 10%\" src=\"./resources/images/logo.png\" alt=\"logo se seguros bolivar\">\r\n" + 
					"		</div>\r\n" + 
					"	</header>\r\n" + 
					"<article>\r\n" + 
					"	<div align=\"center\"  style=\"margin:0 auto; width: 500px; background-color: #D5D8DC;padding: 30px\">\r\n" + 
					"		<img alt=\"icono de error de seguros bolivar\" src=\"./resources/images/warning.png\">\r\n" + 
					"		<span><h3>El nombre de usuario no puede ser nulo</h3></span>\r\n" + 
					"		<div style=\"padding: 10px; background-color: black;color: white;width: 200px\">\r\n" + 
					"			<span>Acceso Denegado</span>\r\n" + 
					"		</div>\r\n" + 
					"	</div>\r\n" + 
					"</article>\r\n" + 
					"</body>\r\n" + 
					"</html>");
//			response.sendRedirect("/avaluos/error.html");
//			response.sendRedirect(AvaluosCons.RUTA_CIERRE_SESION);
//			response.sendRedirect("/avaluos/");
			// Generamos un hidden con caracteres aleatorios que ocultan el error:
//			String cadenaAleatoria = "";
//			int longitud = 5000;
//			long milis = new java.util.GregorianCalendar().getTimeInMillis();
//			Random r = new Random(milis);
//			int i = 0;
//			while (i < longitud) {
//				char c = (char) r.nextInt(255);
//				if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
//					cadenaAleatoria += c;
//					i++;
//				}
//			}
//			out.write("<input type='hidden' value='" + cadenaAleatoria + error + cadenaAleatoria + "' />");
			out.write(paginaError.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.sendRedirect("/avaluos/error.html");// Si ocurre un error se pinta la pantalla porque no se pudo
//			response.sendRedirect("/avaluos/");												// capturar
//		} finally {
			out.close();
//		}

	}
}