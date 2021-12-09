package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;
import java.util.Date;

@XmlRootElement
public class SoporteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idSoporte;

	private Date fecha;

	private Date fechaCreacion;

	private String hora;

	private String latitud;

	private String longitud;

	private String path;
	
	private String codImagen;

	private String tipoSoporte;

	private String usuarioCreacion;

	private Cultivo cultivo;
	
	private int idCultivo;

	private UnidadProductiva unidadProductiva;
	
	private byte[] contenidoImagen;

	public SoporteDTO() {
	}

	@XmlElement(name="idSoporte")
	public long getIdSoporte() {
		return this.idSoporte;
	}

	@XmlElement(name="idSoporte")
	public void setIdSoporte(long idSoporte) {
		this.idSoporte = idSoporte;
	}

	@XmlElement(name="fecha")
	public Date getFecha() {
		return this.fecha;
	}

	@XmlElement(name="fecha")
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@XmlElement(name="fechaCreacion")
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	@XmlElement(name="fechaCreacion")
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@XmlElement(name="hora")
	public String getHora() {
		return this.hora;
	}

	@XmlElement(name="hora")
	public void setHora(String hora) {
		this.hora = hora;
	}

	@XmlElement(name="latitud")
	public String getLatitud() {
		return this.latitud;
	}

	@XmlElement(name="latitud")
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	@XmlElement(name="longitud")
	public String getLongitud() {
		return this.longitud;
	}

	@XmlElement(name="longitud")
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	@XmlElement(name="path")
	public String getPath() {
		return this.path;
	}

	@XmlElement(name="path")
	public void setPath(String path) {
		this.path = path;
	}

	@XmlElement(name="tipoSoporte")
	public String getTipoSoporte() {
		return this.tipoSoporte;
	}

	@XmlElement(name="tipoSoporte")
	public void setTipoSoporte(String tipoSoporte) {
		this.tipoSoporte = tipoSoporte;
	}

	@XmlElement(name="usuarioCreacion")
	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	@XmlElement(name="usuarioCreacion")
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	@XmlElement(name="cultivo")
	public Cultivo getCultivo() {
		return this.cultivo;
	}

	@XmlElement(name="cultivo")
	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	@XmlElement(name="unidadProductiva")
	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	@XmlElement(name="unidadProductiva")
	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

	@XmlElement(name="codImagen")
	public String getCodImagen() {
		return codImagen;
	}

	@XmlElement(name="codImagen")
	public void setCodImagen(String codImagen) {
		this.codImagen = codImagen;
	}

	@XmlElement(name="contenidoImagen")
	public byte[] getContenidoImagen() {
		return contenidoImagen;
	}

	@XmlElement(name="contenidoImagen")
	public void setContenidoImagen(byte[] contenidoImagen) {
		this.contenidoImagen = contenidoImagen;
	}
	@XmlElement(name="idCultivo")
	public int getIdCultivo() {
		return idCultivo;
	}
	@XmlElement(name="idCultivo")
	public void setIdCultivo(int idCultivo) {
		this.idCultivo = idCultivo;
	}
	
	
	
}