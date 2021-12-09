package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "DA_REGLA_ESTDIR")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
@NamedQuery(name = "DaReglaEstDir.findAll", query = "SELECT p FROM DaReglaEstDir p")
public class DaReglaEstDir implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String ENTIDAD_DA_REGLAS_DIRECCIONES_PK = "descripcion";
    private static final String ENTIDAD_DA_REGLAS_DIRECCIONES_CONVERSION = "conversion";
    private static final String ENTIDAD_DA_REGLAS_DIRECCIONES_TIPOCONVERSION = "tipoConversion";

    private static final String[] ATRIBUTOS_ENTIDAD_DA_REGLAS_DIRECCIONES = { ENTIDAD_DA_REGLAS_DIRECCIONES_PK,
	    ENTIDAD_DA_REGLAS_DIRECCIONES_CONVERSION, ENTIDAD_DA_REGLAS_DIRECCIONES_TIPOCONVERSION };
    @Id
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CONVERSION")
    private String conversion;
    @Column(name = "TIPO_CONV")
    private String tipoConversion;

    public String getDescripcion() {
	return descripcion;
    }

    public static boolean contieneAtributo(String atributo) {

	boolean contiene = false;
	for (final String atr : ATRIBUTOS_ENTIDAD_DA_REGLAS_DIRECCIONES) {
	    if (atr.equals(atributo)) {
		contiene = true;
	    }
	}

	return contiene;
    }

    public static String[] getAtributosEntidadPgbCargueTmp() {
	return ATRIBUTOS_ENTIDAD_DA_REGLAS_DIRECCIONES;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
	int hash = 3;

	hash = 37 * hash + Objects.hashCode(this.descripcion);
	hash = 37 * hash + Objects.hashCode(this.conversion);
	hash = 37 * hash + Objects.hashCode(this.tipoConversion);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final DaReglaEstDir other = (DaReglaEstDir) obj;

	if (!Objects.equals(this.descripcion, other.descripcion)) {
	    return false;
	}
	if (!Objects.equals(this.conversion, other.conversion)) {
	    return false;
	}
	return Objects.equals(this.tipoConversion, other.tipoConversion);
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getConversion() {
	return conversion;
    }

    public void setConversion(String conversion) {
	this.conversion = conversion;
    }

    public String getTipoConversion() {
	return tipoConversion;
    }

    public void setTipoConversion(String tipoConversion) {
	this.tipoConversion = tipoConversion;
    }
}
