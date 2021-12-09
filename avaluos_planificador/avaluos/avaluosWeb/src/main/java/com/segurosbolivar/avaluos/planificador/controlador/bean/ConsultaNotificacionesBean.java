/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segurosbolivar.avaluos.planificador.controlador.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.planificador.util.Constantes;

/**
 *
 * @author spinilla
 */
@ManagedBean(name = "consultaNotificacionesBean")
@ViewScoped
public class ConsultaNotificacionesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String PROPERTIES = "/config.properties";
	private static final String PROPERTI_KEY = "url.planificador";
	private static final String  URL_CONSULTAR = "#/solicitud/list";

	String perfil = "0";
	String url = "";

	@PostConstruct
	public void init() {

		UsuarioDto dto = (UsuarioDto) UtilJsf.obtenerParametroSesion(AvaluosCons.CREDENCIAL);
		
		Properties prop = new Properties();

		try {
			
			prop.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES));
			url = prop.getProperty(PROPERTI_KEY);
//			dto.getUsuario().getCodigoPerfil() -- Falta validar código de perfil planificador
			
			if(dto.getUsuario().getCodigoPerfil().toUpperCase().trim().equals(Constantes.PAGACTUALIZ01.toUpperCase()) )
				perfil="0";

			if(dto.getUsuario().getCodigoPerfil().toUpperCase().trim().equals(Constantes.PAGCAPTURA01.toUpperCase()) )
				perfil="1";

			if(dto.getUsuario().getCodigoPerfil().toUpperCase().trim().equals(Constantes.PAGGESTION01.toUpperCase()) )
				perfil="2";

			
			url = url + URL_CONSULTAR + "?cn="+dto.getUsuario().getDocumento()+"&perf="+perfil;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/* get and set */

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
