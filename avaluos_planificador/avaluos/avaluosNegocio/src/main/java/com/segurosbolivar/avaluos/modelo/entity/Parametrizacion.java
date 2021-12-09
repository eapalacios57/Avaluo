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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PGB_PARAMETRIZACION")
@NamedQueries({ @NamedQuery(name = "Parametrizacion.findAll", query = "SELECT p FROM Parametrizacion p"),
	@NamedQuery(name = "Parametrizacion.getParametro", query = "SELECT p FROM Parametrizacion p WHERE p.Tipoparametro = :tipoParametro AND p.Nombreparametro = :nombreParametro"),
	@NamedQuery(name = "Parametrizacion.getTiposParametro", query = "SELECT p FROM Parametrizacion p WHERE p.Tipoparametro = :tipoParametro") })
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class Parametrizacion implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    public static final String ENTIDAD_PARAMETRIZACION_FK_ID_PARAMETRO_RELACION = "ParametroPadre";
    // Definicion de atributos de la entidad (Exceptuando relaciones)
    public static final String ENTIDAD_PARAMETRIZACION_PK = "Idparametrizacion";
    public static final String ENTIDAD_PARAMETRIZACION_TIPO_PARAMETRO = "Tipoparametro";
    public static final String ENTIDAD_PARAMETRIZACION_NOMBRE_PARAMETRO = "Nombreparametro";
    public static final String ENTIDAD_PARAMETRIZACION_VALOR_PARAMETRO = "Valorparametro";
    public static final String ENTIDAD_PARAMETRIZACION_DESCRIPCION_PARAMETRO = "Descripcionparametro";
    public static final String ENTIDAD_PARAMETRIZACION_FECHA_CREACION = "Fechacreacion";
    public static final String ENTIDAD_PARAMETRIZACION_USUARIO_CREACION = "Usuariocreacion";
    public static final String ENTIDAD_PARAMETRIZACION_FECHA_MODIFICACION = "Fechamodificacion";
    public static final String ENTIDAD_PARAMETRIZACION_USUARIO_MODIFICACION = "Usuariomodificacion";

    private static final String[] ATRIBUTOS_ENTIDAD_PGB_PARAMETRIZACION = { ENTIDAD_PARAMETRIZACION_PK, ENTIDAD_PARAMETRIZACION_TIPO_PARAMETRO,
	    ENTIDAD_PARAMETRIZACION_NOMBRE_PARAMETRO, ENTIDAD_PARAMETRIZACION_VALOR_PARAMETRO, ENTIDAD_PARAMETRIZACION_DESCRIPCION_PARAMETRO,
	    ENTIDAD_PARAMETRIZACION_FECHA_CREACION, ENTIDAD_PARAMETRIZACION_USUARIO_CREACION, ENTIDAD_PARAMETRIZACION_FECHA_MODIFICACION,
	    ENTIDAD_PARAMETRIZACION_USUARIO_MODIFICACION };

    @Id
    @SequenceGenerator(name = "PARAMETRIZACION_IDPARAMETRIZACION_GENERATOR", sequenceName = "SEC_PGB_PARAMETRIZACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETRIZACION_IDPARAMETRIZACION_GENERATOR")
    @Column(name = "ID_PARAMETRIZACION", nullable = false, precision = 22)
    private Long Idparametrizacion;

    @Column(name = "TIPO_PARAMETRO", nullable = false)
    private String Tipoparametro;

    @Column(name = "NOMBRE_PARAMETRO", nullable = false)
    private String Nombreparametro;

    @Column(name = "VALOR_PARAMETRO", nullable = false)
    private String Valorparametro;

    @Column(name = "DESCRIPCION_PARAMETRO", nullable = false)
    private String Descripcionparametro;

    @Column(name = "ID_PARAMETRO_RELACION", nullable = true, precision = 22)
    private Long Idparametrorelacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION", nullable = false)
    private Date Fechacreacion;

    @Column(name = "USUARIO_CREACION", nullable = false)
    private String Usuariocreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_MODIFICACION", nullable = true)
    private Date Fechamodificacion;

    @Column(name = "USUARIO_MODIFICACION", nullable = true)
    private String Usuariomodificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PARAMETRO_RELACION", referencedColumnName = "ID_PARAMETRIZACION", insertable = false, updatable = false)
    private Parametrizacion parametroPadre;

    /* Constraint */
    public Parametrizacion() {
	super();
    }

    /**
     * Verifica si la entidad contiene el atributo que se pasa como par�metro
     *
     * @param atributo
     *            Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
	boolean contiene = false;
	for (final String atr : ATRIBUTOS_ENTIDAD_PGB_PARAMETRIZACION) {
	    if (atr.equals(atributo)) {
		contiene = true;
	    }
	}
	return contiene;
    }

    public static String[] getAtributosEntidad() {
	return ATRIBUTOS_ENTIDAD_PGB_PARAMETRIZACION;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 37 * hash + Objects.hashCode(this.Idparametrizacion);
	hash = 37 * hash + Objects.hashCode(this.Tipoparametro);
	hash = 37 * hash + Objects.hashCode(this.Nombreparametro);
	hash = 37 * hash + Objects.hashCode(this.Valorparametro);
	hash = 37 * hash + Objects.hashCode(this.Descripcionparametro);
	hash = 37 * hash + Objects.hashCode(this.Idparametrorelacion);
	hash = 37 * hash + Objects.hashCode(this.Fechacreacion);
	hash = 37 * hash + Objects.hashCode(this.Usuariocreacion);
	hash = 37 * hash + Objects.hashCode(this.Fechamodificacion);
	hash = 37 * hash + Objects.hashCode(this.Usuariomodificacion);
	return hash;
    }

    /**
     * Valida la igualdad de la instancia de la entidad Parametrizacion que se pasa
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
	final Parametrizacion other = (Parametrizacion) obj;
	if (!Objects.equals(this.Idparametrizacion, other.Idparametrizacion)) {
	    return false;
	}
	if (!Objects.equals(this.Tipoparametro, other.Tipoparametro)) {
	    return false;
	}
	if (!Objects.equals(this.Nombreparametro, other.Nombreparametro)) {
	    return false;
	}
	if (!Objects.equals(this.Valorparametro, other.Valorparametro)) {
	    return false;
	}
	if (!Objects.equals(this.Descripcionparametro, other.Descripcionparametro)) {
	    return false;
	}
	if (!Objects.equals(this.Idparametrorelacion, other.Idparametrorelacion)) {
	    return false;
	}
	if (!Objects.equals(this.Fechacreacion, other.Fechacreacion)) {
	    return false;
	}
	if (!Objects.equals(this.Usuariocreacion, other.Usuariocreacion)) {
	    return false;
	}
	if (!Objects.equals(this.Fechamodificacion, other.Fechamodificacion)) {
	    return false;
	}
	if (!Objects.equals(this.Usuariomodificacion, other.Usuariomodificacion)) {
	    return false;
	}
	return true;
    }
    
    @Override
	public Parametrizacion clone() throws CloneNotSupportedException {
		return (Parametrizacion) super.clone();
	}

    /* Getters and Setters */
    public Long getIdparametrizacion() {
	return Idparametrizacion;
    }

    public void setIdparametrizacion(Long idparametrizacion) {
	Idparametrizacion = idparametrizacion;
    }

    public String getTipoparametro() {
	return Tipoparametro;
    }

    public void setTipoparametro(String tipoparametro) {
	Tipoparametro = tipoparametro;
    }

    public String getNombreparametro() {
	return Nombreparametro;
    }

    public void setNombreparametro(String nombreparametro) {
	Nombreparametro = nombreparametro;
    }

    public String getValorparametro() {
	return Valorparametro;
    }

    public void setValorparametro(String valorparametro) {
	Valorparametro = valorparametro;
    }

    public String getDescripcionparametro() {
	return Descripcionparametro;
    }

    public void setDescripcionparametro(String descripcionparametro) {
	Descripcionparametro = descripcionparametro;
    }

    public Date getFechacreacion() {
	return Fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
	Fechacreacion = fechacreacion;
    }

    public String getUsuariocreacion() {
	return Usuariocreacion;
    }

    public void setUsuariocreacion(String usuariocreacion) {
	Usuariocreacion = usuariocreacion;
    }

    public Date getFechamodificacion() {
	return Fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
	Fechamodificacion = fechamodificacion;
    }

    public String getUsuariomodificacion() {
	return Usuariomodificacion;
    }

    public void setUsuariomodificacion(String usuariomodificacion) {
	Usuariomodificacion = usuariomodificacion;
    }

    public Long getIdparametrorelacion() {
	return Idparametrorelacion;
    }

    public void setIdparametrorelacion(Long idparametrorelacion) {
	Idparametrorelacion = idparametrorelacion;
    }

    public Parametrizacion getParametroPadre() {
	return parametroPadre;
    }

    public void setParametroPadre(Parametrizacion parametroPadre) {
	this.parametroPadre = parametroPadre;
    }

}
