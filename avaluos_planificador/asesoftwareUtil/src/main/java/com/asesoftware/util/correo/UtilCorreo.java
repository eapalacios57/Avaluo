package com.asesoftware.util.correo;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.ManejadorExcepcion;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.asesoftware.util.mail.dto.CorreoDto;
import com.asesoftware.util.validador.UtilValidador;

/**
 * Esta utilidad permite la realizacion de operacion para el manejo de correos
 * electronicos
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public class UtilCorreo {

	public static final String SMTP_USUARIO = "mail.smtp.user";
	public static final String SMTP_PORT = "mail.smtp.port";
	public static final String SMTP_SENDER = "mail.smtp.mail.sender";
	public static final String SMTP_HOST = "mail.smtp.host";
	public static final String SMTP_PASS = "mail.smtp.password";
	private static final String SMTP_CONTENT = "text/html; charset=utf-8";
	private static final String SEPARADOR_INICIO = "[";
	private static final String SEPARADOR_FIN = "]";
	private static Properties config = new Properties();

	private static ManejadorExcepcion mgrExc = ManejadorExcepcion.getDef();

	private UtilCorreo() {

	}
	
	public static void enviarCorreo(long id, String asunto, String mensaje, String... destinatarios) throws NegocioException {
		CorreoDto correo = new CorreoDto(asunto, mensaje, Arrays.asList(destinatarios), null, id);
		UtilCorreo.enviarCorreo(config, correo); 
	}
	
	public static void configuraCorreo(String usuarioSmtp, String senderSmtp, String portSmtp, String hostSmtp, String passSmtp) {
		config.setProperty(SMTP_USUARIO, usuarioSmtp);
		config.setProperty(SMTP_SENDER, senderSmtp);
		config.setProperty(SMTP_PORT, portSmtp);
		config.setProperty(SMTP_HOST, hostSmtp);
		config.setProperty(SMTP_PASS, passSmtp);
	}

	/***
	 * 
	 * @param props
	 * @param correo
	 * @return
	 * @throws NegocioException
	 */
	public static boolean enviarCorreo(Properties props, CorreoDto correo) throws NegocioException {
		try {
			// Se añade el origen del mensaje
			if (!UtilValidador.validarCorreo(props.getProperty(SMTP_SENDER), true, 100)) {
				throw mgrExc.lanzarExcepcion(250, TipoErrorNegocio.ALERTA);
			}
			if (!UtilValidador.validar(props.getProperty(SMTP_USUARIO), true, 100)) {
				throw mgrExc.lanzarExcepcion(251, TipoErrorNegocio.ALERTA);
			}
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol","smtp");
			// Se instancian las variables
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty(SMTP_USUARIO)));
			// Agregar destinatarios al mensaje
			for (String destiny : correo.getDestinatarios()) {
				if (UtilValidador.validarCorreo(destiny, true, 100))
					message.addRecipients(Message.RecipientType.TO, destiny);
				else
					throw mgrExc.lanzarExcepcion(252, TipoErrorNegocio.ALERTA);
			}
			if (!UtilTexto.estaVacio(correo.getCopia()) && UtilValidador.validarCorreo(correo.getCopia(), false, 100))
				message.addRecipients(Message.RecipientType.CC, correo.getCopia());
			if (!UtilTexto.estaVacio(correo.getCopiaOculta())
					&& UtilValidador.validarCorreo(correo.getCopiaOculta(), false, 100))
				message.addRecipients(Message.RecipientType.BCC, correo.getCopiaOculta());
			if (UtilTexto.estaVacio(correo.getMensaje()))
				throw mgrExc.lanzarExcepcion(253, TipoErrorNegocio.ALERTA);
			// Añadir copia y copia oculta
			MimeMultipart multiParte = new MimeMultipart();
			BodyPart texto = new MimeBodyPart();
			// Se añade el contenido con el archivo adjunto
			texto.setContent(correo.getMensaje(), SMTP_CONTENT);
			BodyPart adjunto = new MimeBodyPart();
			if (!UtilTexto.estaVacio(correo.getArchivoAdjunto())) {
				File file = new File(correo.getArchivoAdjunto());
				if (!file.exists() || file.length() <= 0)
					throw mgrExc.lanzarExcepcion(254, TipoErrorNegocio.ALERTA);
				if (file.length() > 5000000) {
					throw mgrExc.lanzarExcepcion(255, TipoErrorNegocio.ALERTA);
				}
				adjunto.setDataHandler(new DataHandler(new FileDataSource(correo.getArchivoAdjunto())));
				adjunto.setFileName(correo.getNombreArchivoAdjunto());
				multiParte.addBodyPart(adjunto);
			}
			multiParte.addBodyPart(texto);
			// Se conecta y se envia el mensaje
			String asuntoConIdDoc = "";
			// poner dichos caracteres parametrizables BD
			StringBuilder cadena = new StringBuilder();
			cadena.append(SEPARADOR_INICIO);
			cadena.append(correo.getId());
			cadena.append(SEPARADOR_FIN);
			cadena.append(" ");
			cadena.append(correo.getAsunto());
			asuntoConIdDoc = cadena.toString();
			message.setSubject(asuntoConIdDoc);
			message.setContent(multiParte, SMTP_CONTENT);
			Transport transport = session.getTransport("smtp");
			transport.connect(props.getProperty(SMTP_HOST), Integer.parseInt(props.getProperty(SMTP_PORT)),
					props.getProperty(SMTP_USUARIO), props.getProperty(SMTP_PASS));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (MessagingException e) {
			throw mgrExc.lanzarExcepcion(256, TipoErrorNegocio.ALERTA, e.getMessage(), null);
		} catch (Exception e) {
			throw e;
		}
	}

}