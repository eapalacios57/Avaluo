package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;

import java.util.List;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/**
 * DAO que contiene la información de la entidad SolicitudDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class SolicitudDetalleDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Solicitud solicitud;
	
	private String DocBeneficiario;
	private String DocPlanificador;

	private SolicitudBeneficiario solBeneficiario;
	
	private List<ActividadFinancieraSolicitud> actFinSolicitud;

	private List<UnidadProductiva> unidadProductiva;
	
	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public SolicitudDetalleDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="solicitud")
	public Solicitud getSolicitud() {
		return solicitud;
	}

	@XmlElement(name="solicitud")
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	@XmlElement(name="DocBeneficiario")
	public String getDocBeneficiario() {
		return DocBeneficiario;
	}


	@XmlElement(name="DocBeneficiario")
	public void setDocBeneficiario(String docBeneficiario) {
		DocBeneficiario = docBeneficiario;
	}


	@XmlElement(name="DocPlanificador")
	public String getDocPlanificador() {
		return DocPlanificador;
	}


	@XmlElement(name="DocPlanificador")
	public void setDocPlanificador(String docPlanificador) {
		DocPlanificador = docPlanificador;
	}

	@XmlElement(name="solBeneficiario")
	public SolicitudBeneficiario getSolBeneficiario() {
		return solBeneficiario;
	}

	@XmlElement(name="solBeneficiario")
	public void setSolBeneficiario(SolicitudBeneficiario solBeneficiario) {
		this.solBeneficiario = solBeneficiario;
	}

	@XmlElement(name="actFinSolicitud")
	public List<ActividadFinancieraSolicitud> getActFinSolicitud() {
		return actFinSolicitud;
	}

	@XmlElement(name="actFinSolicitud")
	public void setActFinSolicitud(List<ActividadFinancieraSolicitud> actFinSolicitud) {
		this.actFinSolicitud = actFinSolicitud;
	}

	@XmlElement(name="unidadProductiva")
	public List<UnidadProductiva> getUnidadProductiva() {
		return unidadProductiva;
	}

	@XmlElement(name="unidadProductiva")
	public void setUnidadProductiva(List<UnidadProductiva> unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}
	
	
	@Override
	public String toString() {
		return "SolicitudDetalleDTO [solicitud=" + solicitud + ", DocBeneficiario=" + DocBeneficiario
				+ ", DocPlanificador=" + DocPlanificador + "]";
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end
	

} 

