package com.segurosbolivar.avaluos.modelo.ws.dto.fca;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.segurosbolivar.avaluos.modelo.jaxb.LongDateStringToDateAdapter;

@XmlRootElement(name = "Usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 6879696077855096439L;
	@XmlElement(name = "Codigo")
	private String codigo;
	@XmlElement(name = "Documento")
	private String documento;
	@XmlElement(name = "Aplicacion")
	private int aplicacion;
	@XmlElement(name = "CodigoPerfil")
	private String codigoPerfil;
	@XmlElement(name = "NombrePerfil")
	private String nombrePerfil;
	@XmlElement(name = "Nombres")
	private String nombres;
	@XmlElement(name = "Nivel")
	private int nivel;
	@XmlElement(name = "Estado")
	private Character estado;
//	@XmlElement(name = "FechaUltimoIngreso", type = String.class)
	@XmlElement(name = "FechaUltimoIngreso")
	//	@XmlJavaTypeAdapter(LongDateStringToDateAdapter.class)
//	@XmlSchemaType(name="date")
	private String fechaUltimoIngreso;
	@XmlElement(name = "Email")
	private String email;
	@XmlElement(name = "CodigoLocalidad")
	private String codigoLocalidad;
	@XmlElement(name = "DescripcionLocalidad")
	private String descripcionLocalidad;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public int getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(int aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Character getEstado() {
		return estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

	public String getFechaUltimoIngreso() {
		return fechaUltimoIngreso;
	}

	public void setFechaUltimoIngreso(String fechaUltimoIngreso) {
		this.fechaUltimoIngreso = fechaUltimoIngreso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodigoLocalidad() {
		return codigoLocalidad;
	}

	public void setCodigoLocalidad(String codigoLocalidad) {
		this.codigoLocalidad = codigoLocalidad;
	}

	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}

	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}

}
