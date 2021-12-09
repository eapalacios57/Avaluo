package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRODUCTO_RELACIONADO_SOLICITUD database table.
 * 
 */
@Embeddable
public class ProductoRelacionadoSolicitudPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PRODUCTO_RELACIONADO_CODIGO", insertable=false, updatable=false)
	private String productoRelacionadoCodigo;

	@Column(name="CODIGO_SOLICITUD", insertable=false, updatable=false)
	private String codigoSolicitud;

	public ProductoRelacionadoSolicitudPK() {
	}
	public String getProductoRelacionadoCodigo() {
		return this.productoRelacionadoCodigo;
	}
	public void setProductoRelacionadoCodigo(String productoRelacionadoCodigo) {
		this.productoRelacionadoCodigo = productoRelacionadoCodigo;
	}
	public String getCodigoSolicitud() {
		return this.codigoSolicitud;
	}
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductoRelacionadoSolicitudPK)) {
			return false;
		}
		ProductoRelacionadoSolicitudPK castOther = (ProductoRelacionadoSolicitudPK)other;
		return 
			this.productoRelacionadoCodigo.equals(castOther.productoRelacionadoCodigo)
			&& this.codigoSolicitud.equals(castOther.codigoSolicitud);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productoRelacionadoCodigo.hashCode();
		hash = hash * prime + this.codigoSolicitud.hashCode();
		
		return hash;
	}
}