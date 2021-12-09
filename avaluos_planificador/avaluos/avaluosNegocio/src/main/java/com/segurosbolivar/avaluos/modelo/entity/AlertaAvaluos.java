package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name = "PGB_ALERTA_AVALUOS")
@NamedQuery(name = "AlertaAvaluos.findAll", query = "SELECT p FROM AlertaAvaluos p WHERE p.idAvaluo=:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class AlertaAvaluos implements Serializable {

    private static final long serialVersionUID = 1L;

    // Definicion de atributos de la entidad (Exceptuando relaciones)
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_PK = "idAlerta";
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_ID_AVALUO = "idAvaluo";
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_COD_TIPO_ALERTA = "codTipoAlerta";
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_EST_ACTIVA = "estActiva";
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_FECHA_CREACION = "fechaCreacion";
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_USUARIO_CREACION = "usuarioCreacion";
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_FECHA_TRANSACCION = "fechaTransaccion";
    public static final String ENTIDAD_PGB_ALERTA_AVALUOS_USUARIO_TRANSACCION = "usuarioTransaccion";
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_ALERTA_AVALUOS = { ENTIDAD_PGB_ALERTA_AVALUOS_EST_ACTIVA, ENTIDAD_PGB_ALERTA_AVALUOS_COD_TIPO_ALERTA,
	    ENTIDAD_PGB_ALERTA_AVALUOS_FECHA_TRANSACCION, ENTIDAD_PGB_ALERTA_AVALUOS_USUARIO_CREACION, ENTIDAD_PGB_ALERTA_AVALUOS_PK, ENTIDAD_PGB_ALERTA_AVALUOS_ID_AVALUO,
	    ENTIDAD_PGB_ALERTA_AVALUOS_USUARIO_TRANSACCION, ENTIDAD_PGB_ALERTA_AVALUOS_FECHA_CREACION };

    @Id
    @SequenceGenerator(name = "PGB_ALERTA_AVALUOS_ID_ALERTA_GENERATOR", sequenceName = "SEQ_PGB_ALERTA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_ALERTA_AVALUOS_ID_ALERTA_GENERATOR")
    @Column(name = "ID_ALERTA")
    private Long idAlerta;

    @Column(name = "ID_AVALUO")
    private Long idAvaluo;

    @Column(name = "COD_TIPO_ALERTA")
    private Long codTipoAlerta;

    @Column(name = "EST_ACTIVA")
    private String estActiva;

    @Column(name = "FECHA_CREACION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_TRANSACCION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaTransaccion;

    @Column(name = "USUARIO_TRANSACCION")
    private String usuarioTransaccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private Avaluo avaluo;

    // protected region atributos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region atributos adicionales end

    public AlertaAvaluos() {
	// protected region procedimientos adicionales de inicialización on
	// begin
	// Escriba en esta sección sus modificaciones

	// protected region procedimientos adicionales de inicialización end
    }

    public AlertaAvaluos clonar() {
	AlertaAvaluos copia = new AlertaAvaluos();
	copia.setCodTipoAlerta(codTipoAlerta);
	copia.setEstActiva(estActiva);
	copia.setUsuarioCreacion(usuarioCreacion);
	copia.setFechaCreacion(fechaCreacion);
	copia.setUsuarioTransaccion(usuarioTransaccion);
	copia.setFechaTransaccion(fechaTransaccion);
	return copia;
    }

    public Long getIdAlerta() {
	return this.idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
	this.idAlerta = idAlerta;
    }

    public Long getIdAvaluo() {
	return this.idAvaluo;
    }

    public void setIdAvaluo(Long idAvaluo) {
	this.idAvaluo = idAvaluo;
    }

    public Long getCodTipoAlerta() {
	return this.codTipoAlerta;
    }

    public void setCodTipoAlerta(Long codTipoAlerta) {
	this.codTipoAlerta = codTipoAlerta;
    }

    public String getEstActiva() {
	return this.estActiva;
    }

    public void setEstActiva(String estActiva) {
	this.estActiva = estActiva;
    }

    public Date getFechaCreacion() {
	return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
	return this.usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
	this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaTransaccion() {
	return this.fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
	this.fechaTransaccion = fechaTransaccion;
    }

    public String getUsuarioTransaccion() {
	return this.usuarioTransaccion;
    }

    public void setUsuarioTransaccion(String usuarioTransaccion) {
	this.usuarioTransaccion = usuarioTransaccion;
    }

    public Avaluo getAvaluo() {
	return avaluo;
    }

    public void setAvaluo(Avaluo avaluo) {
	this.avaluo = avaluo;
    }

    /**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo
     *            Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {

	boolean contiene = false;
	for (final String atr : ATRIBUTOS_ENTIDAD_PGB_ALERTA_AVALUOS) {
	    if (atr.equals(atributo)) {
		contiene = true;
	    }
	}

	return contiene;
    }

    public static String[] getAtributosEntidadPgbAlertaAvaluos() {
	return ATRIBUTOS_ENTIDAD_PGB_ALERTA_AVALUOS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
	int hash = 3;

	hash = 37 * hash + Objects.hashCode(this.idAlerta);
	hash = 37 * hash + Objects.hashCode(this.idAvaluo);
	hash = 37 * hash + Objects.hashCode(this.codTipoAlerta);
	hash = 37 * hash + Objects.hashCode(this.estActiva);
	hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
	hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
	hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
	hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);

	return hash;
    }

    /**
     * Valida la igualdad de la instancia de la entidad PgbAlertaAvaluos que se pasa
     * como parámetro comprobando que comparten los mismos valores en cada uno de
     * sus atributos. Solo se tienen en cuenta los atributos simples, es decir, se
     * omiten aquellos que definen una relación con otra tabla.
     *
     * @param obj
     *            Instancia de la categoría a comprobar iguales.
     * @return Verdadero si esta instancia y la que se pasan como parámetros son
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final AlertaAvaluos other = (AlertaAvaluos) obj;

	if (!Objects.equals(this.idAlerta, other.idAlerta)) {
	    return false;
	}

	if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
	    return false;
	}

	if (!Objects.equals(this.codTipoAlerta, other.codTipoAlerta)) {
	    return false;
	}

	if (!Objects.equals(this.estActiva, other.estActiva)) {
	    return false;
	}

	if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
	    return false;
	}

	if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
	    return false;
	}

	if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
	    return false;
	}

	return Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion);

    }

    @Override
    public String toString() {
	return " ALERTA [idAlerta=" + idAlerta + "idAvaluo=" + idAvaluo + ", codTipoAlerta=" + codTipoAlerta + "]";
    }

    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region metodos adicionales end

}
