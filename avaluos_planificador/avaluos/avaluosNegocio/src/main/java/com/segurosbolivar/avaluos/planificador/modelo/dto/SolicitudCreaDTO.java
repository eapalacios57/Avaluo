package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;
import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;


/**
 * DAO que contiene la información de la entidad BeneficiarioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class SolicitudCreaDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ActividadFinancieraSolicitudDTO> listaAFSolicitud;
	
	private List<BeneficiarioCrearDTO> listBeneficiarios;
	
	private PlanificadorPK planificadorId;
	
	private String nombreAsesorComercial;
	
	private String telefonoAsesorComercial;
	
	private String comentariosAnexos;
	
	private String fechaSolicitudStr;
	
	private String municipioDepartamento;
	
	private List<Documento> documento;

	private String municipio;
	
	private String departamento;
	
    public SolicitudCreaDTO(){
		
    }

	public List<ActividadFinancieraSolicitudDTO> getListaAFSolicitud() {
		return listaAFSolicitud;
	}

	public void setListaAFSolicitud(List<ActividadFinancieraSolicitudDTO> listaAFSolicitud) {
		this.listaAFSolicitud = listaAFSolicitud;
	}

	public List<BeneficiarioCrearDTO> getListBeneficiarios() {
		return listBeneficiarios;
	}

	public void setListBeneficiarios(List<BeneficiarioCrearDTO> listBeneficiarios) {
		this.listBeneficiarios = listBeneficiarios;
	}

	public PlanificadorPK getPlanificadorId() {
		return planificadorId;
	}

	public void setPlanificadorId(PlanificadorPK planificadorId) {
		this.planificadorId = planificadorId;
	}

	public String getNombreAsesorComercial() {
		return nombreAsesorComercial;
	}

	public void setNombreAsesorComercial(String nombreAsesorComercial) {
		this.nombreAsesorComercial = nombreAsesorComercial;
	}

	public String getTelefonoAsesorComercial() {
		return telefonoAsesorComercial;
	}

	public void setTelefonoAsesorComercial(String telefonoAsesorComercial) {
		this.telefonoAsesorComercial = telefonoAsesorComercial;
	}

	public String getComentariosAnexos() {
		return comentariosAnexos;
	}

	public void setComentariosAnexos(String comentariosAnexos) {
		this.comentariosAnexos = comentariosAnexos;
	}

	public String getFechaSolicitudStr() {
		return fechaSolicitudStr;
	}

	public void setFechaSolicitudStr(String fechaSolicitudStr) {
		this.fechaSolicitudStr = fechaSolicitudStr;
	}

	public String getMunicipioDepartamento() {
		return municipioDepartamento;
	}

	public void setMunicipioDepartamento(String municipioDepartamento) {
		this.municipioDepartamento = municipioDepartamento;
	}

	public List<Documento> getDocumento() {
		return documento;
	}

	public void setDocumento(List<Documento> documento) {
		this.documento = documento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


} 

