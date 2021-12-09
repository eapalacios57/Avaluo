package com.segurosbolivar.avaluos.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import static org.eclipse.persistence.annotations.CacheType.NONE;


/**
 * The persistent class for the V_RESUMEN_CARGUE database view.
 * 
 */
@Entity
@Table(name="V_RESUMEN_CARGUE")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class VResumenCargue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(precision=22)
	private BigDecimal aplicados;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

    @Id
    @Column(name="NUMERO_REF_CARGUE", precision=22)
	private BigDecimal numeroRefCargue;

	@Column(precision=22)
	private BigDecimal rechazados;

	@Column(name="SIN_ESTADO", precision=22)
	private BigDecimal sinEstado;

	@Column(name="TIPO_CARGUE", length=10)
	private String tipoCargue;

	@Column(precision=22)
	private BigDecimal total;

	@Column(name="NOMBRE_ARCHIVO")
	private String nombreArchivo;
	
	@Column(name="CONSECUTIVO_BATCH", precision=5)
	private BigDecimal consecutivoBatch;
		
	@Column(name="USUARIO_TRANSACCION", nullable=false, length=15)
	private String usuarioTransaccion;
	
	@Column(name="EMPRESA_AVALUOS", nullable=false, length=15)
	private String empresaAvaluos;
	
    public VResumenCargue() {
    }

	public BigDecimal getAplicados() {
		return this.aplicados;
	}

	public void setAplicados(BigDecimal aplicados) {
		this.aplicados = aplicados;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public BigDecimal getNumeroRefCargue() {
		return this.numeroRefCargue;
	}

	public void setNumeroRefCargue(BigDecimal numeroRefCargue) {
		this.numeroRefCargue = numeroRefCargue;
	}

	public BigDecimal getRechazados() {
		return this.rechazados;
	}

	public void setRechazados(BigDecimal rechazados) {
		this.rechazados = rechazados;
	}

	public BigDecimal getSinEstado() {
		return this.sinEstado;
	}

	public void setSinEstado(BigDecimal sinEstado) {
		this.sinEstado = sinEstado;
	}

	public String getTipoCargue() {
		return this.tipoCargue;
	}

	public void setTipoCargue(String tipoCargue) {
		this.tipoCargue = tipoCargue;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public BigDecimal getConsecutivoBatch() {
		return consecutivoBatch;
	}

	public void setConsecutivoBatch(BigDecimal consecutivoBatch) {
		this.consecutivoBatch = consecutivoBatch;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public String getEmpresaAvaluos() {
		return this.empresaAvaluos;
	}

	public void setEmpresaAvaluos(String empresaAvaluos) {
		this.empresaAvaluos = empresaAvaluos;
	}
	
}