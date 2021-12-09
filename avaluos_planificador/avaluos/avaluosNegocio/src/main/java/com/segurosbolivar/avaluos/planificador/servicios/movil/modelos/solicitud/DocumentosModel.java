package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud;

public class DocumentosModel {

	private Integer idDocumento;
	private String nombreDocumento;
	private String pathFileNet;
	
	public DocumentosModel() {
		
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getPathFileNet() {
		return pathFileNet;
	}

	public void setPathFileNet(String pathFileNet) {
		this.pathFileNet = pathFileNet;
	}
	
	
	

	
	
}
