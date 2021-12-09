package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PGB_EMPRESAS_AVALUOS database table.
 * 
 */
@Entity
@Table(name = "PGB_EMPRESAS_AVALUOS")
@NamedNativeQueries({
		@NamedNativeQuery(name = "getLogoEmpresa", query = "select e.logo from pgb_empresas_avaluos e where e.id_empresa_avaluo = ?"),
		@NamedNativeQuery(name = "getFirmaEmpresa", query = "select e.firma from pgb_empresas_avaluos e where e.id_empresa_avaluo = ?") })
@NamedQueries({ @NamedQuery(name = "getEmpresas", query = "select e from EmpresasAvaluos e"),
		@NamedQuery(name = "EmpresasAvaluos.cantidadValores", query = "SELECT COUNT( e.idEmpresaAvaluo) FROM EmpresasAvaluos e "),
		@NamedQuery(name = "EmpresasAvaluos.findAll", query = "SELECT e FROM EmpresasAvaluos e ORDER BY e.descEmpresa") })
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class EmpresasAvaluos implements Serializable, Cloneable, IPersona {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_PK = "idEmpresaAvaluo";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_TIPO_DOCUMENTO = "tipoDocumento";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_DESC_EMPRESA = "descEmpresa";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_ESTADO = "estado";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_LOGO = "logo";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_SUCURSAL_DAVIVIENDA = "sucursalDavivienda";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_FIRMA = "firma";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_REGISTRO_SIC = "registroSic";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_REGISTRO_PRIVADO = "registroPrivado";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_CARGA_ARCHIVO_PLANO = "cargaArchivoPlano";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_EMPRESAS_AVALUOS_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_EMPRESAS_AVALUOS = {
			ENTIDAD_PGB_EMPRESAS_AVALUOS_FECHA_CREACION, ENTIDAD_PGB_EMPRESAS_AVALUOS_FIRMA,
			ENTIDAD_PGB_EMPRESAS_AVALUOS_USUARIO_CREACION, ENTIDAD_PGB_EMPRESAS_AVALUOS_FECHA_TRANSACCION,
			ENTIDAD_PGB_EMPRESAS_AVALUOS_PK, ENTIDAD_PGB_EMPRESAS_AVALUOS_CARGA_ARCHIVO_PLANO,
			ENTIDAD_PGB_EMPRESAS_AVALUOS_NUMERO_DOCUMENTO, ENTIDAD_PGB_EMPRESAS_AVALUOS_DESC_EMPRESA,
			ENTIDAD_PGB_EMPRESAS_AVALUOS_LOGO, ENTIDAD_PGB_EMPRESAS_AVALUOS_REGISTRO_PRIVADO,
			ENTIDAD_PGB_EMPRESAS_AVALUOS_USUARIO_TRANSACCION, ENTIDAD_PGB_EMPRESAS_AVALUOS_TIPO_DOCUMENTO,
			ENTIDAD_PGB_EMPRESAS_AVALUOS_SUCURSAL_DAVIVIENDA, ENTIDAD_PGB_EMPRESAS_AVALUOS_REGISTRO_SIC,
			ENTIDAD_PGB_EMPRESAS_AVALUOS_ESTADO };

	@Id
	@SequenceGenerator(name = "PGB_EMPRESAS_AVALUOS_IDEMPRESAAVALUO_GENERATOR", sequenceName = "SEQ_PGB_EMPRESAS_AVALUOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_EMPRESAS_AVALUOS_IDEMPRESAAVALUO_GENERATOR")
	@Column(name = "ID_EMPRESA_AVALUO")
	private Long idEmpresaAvaluo;

	@Column(name = "CARGA_ARCHIVO_PLANO")
	private String cargaArchivoPlano;

	@Column(name = "DESC_EMPRESA")
	private String descEmpresa;

	@Column(name = "ESTADO")
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "NUMERO_DOCUMENTO")
	private Long numeroDocumento;

	@Column(name = "REGISTRO_PRIVADO")
	private String registroPrivado;

	@Column(name = "REGISTRO_SIC")
	private String registroSic;

	@Column(name = "SUCURSAL_DAVIVIENDA")
	private Long sucursalDavivienda;

	@Column(name = "TIPO_DOCUMENTO")
	private String tipoDocumento;
	@Column(name = "ID_TIPO_IDENTIFICACION")
	private Long idTipoIdentificacion;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Column(name = "LOGO")
	private Long idLogo;

	@Column(name = "FIRMA")
	private Long idFirma;

	// uni-directional many-to-one association to Archivo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOGO", referencedColumnName = "ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo logo;

	// uni-directional many-to-one association to Archivo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FIRMA", referencedColumnName = "ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo firma;

	// bi-directional many-to-one association to PeritosEmpresa
	@OneToMany(mappedBy = "empresasAvaluo")
	private List<PeritosEmpresa> peritosEmpresas;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public EmpresasAvaluos() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	@Override
	public Long getNumeroIdentificacion() {
		return getNumeroDocumento();
	}

	@Override
	public String getNombre() {
		return getDescEmpresa();
	}

	@Override
	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}

	@Override
	public EmpresasAvaluos clone() {
		try {
			return (EmpresasAvaluos) super.clone();
		} catch (CloneNotSupportedException e) {
			Logger.getLogger(EmpresasAvaluos.class.getName()).log(Level.SEVERE,
					"La clonacion de la instancia no es soportada", e);
		}
		return null;
	}

	/*
	 * getteres y setters
	 */

	public Long getIdEmpresaAvaluo() {
		return this.idEmpresaAvaluo;
	}

	public void setIdEmpresaAvaluo(Long idEmpresaAvaluo) {
		this.idEmpresaAvaluo = idEmpresaAvaluo;
	}

	public String getCargaArchivoPlano() {
		return this.cargaArchivoPlano;
	}

	public void setCargaArchivoPlano(String cargaArchivoPlano) {
		this.cargaArchivoPlano = cargaArchivoPlano;
	}

	public String getDescEmpresa() {
		return this.descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Long getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getRegistroPrivado() {
		return this.registroPrivado;
	}

	public void setRegistroPrivado(String registroPrivado) {
		this.registroPrivado = registroPrivado;
	}

	public String getRegistroSic() {
		return this.registroSic;
	}

	public void setRegistroSic(String registroSic) {
		this.registroSic = registroSic;
	}

	public Long getSucursalDavivienda() {
		return this.sucursalDavivienda;
	}

	public void setSucursalDavivienda(Long sucursalDavivienda) {
		this.sucursalDavivienda = sucursalDavivienda;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public Archivo getLogo() {
		return this.logo;
	}

	public void setLogo(Archivo logo) {
		this.logo = logo;
	}

	public Archivo getFirma() {
		return this.firma;
	}

	public void setFirma(Archivo firma) {
		this.firma = firma;
	}

	public List<PeritosEmpresa> getPeritosEmpresas() {
		return this.peritosEmpresas;
	}

	public void setPeritosEmpresas(List<PeritosEmpresa> peritosEmpresas) {
		this.peritosEmpresas = peritosEmpresas;
	}

	public Long getIdLogo() {
		return idLogo;
	}

	public void setIdLogo(Long idLogo) {
		this.idLogo = idLogo;
	}

	public Long getIdFirma() {
		return idFirma;
	}

	public void setIdFirma(Long idFirma) {
		this.idFirma = idFirma;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_EMPRESAS_AVALUOS) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbEmpresasAvaluos() {
		return ATRIBUTOS_ENTIDAD_PGB_EMPRESAS_AVALUOS;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idEmpresaAvaluo);
		hash = 37 * hash + Objects.hashCode(this.tipoDocumento);
		hash = 37 * hash + Objects.hashCode(this.numeroDocumento);
		hash = 37 * hash + Objects.hashCode(this.descEmpresa);
		hash = 37 * hash + Objects.hashCode(this.estado);
		hash = 37 * hash + Objects.hashCode(this.logo);
		hash = 37 * hash + Objects.hashCode(this.sucursalDavivienda);
		hash = 37 * hash + Objects.hashCode(this.firma);
		hash = 37 * hash + Objects.hashCode(this.registroSic);
		hash = 37 * hash + Objects.hashCode(this.registroPrivado);
		hash = 37 * hash + Objects.hashCode(this.cargaArchivoPlano);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbEmpresasAvaluos que
	 * se pasa como parámetro comprobando que comparten los mismos valores en
	 * cada uno de sus atributos. Solo se tienen en cuenta los atributos
	 * simples, es decir, se omiten aquellos que definen una relación con otra
	 * tabla.
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
		final EmpresasAvaluos other = (EmpresasAvaluos) obj;

		if (!Objects.equals(this.idEmpresaAvaluo, other.idEmpresaAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
			return false;
		}

		if (!Objects.equals(this.numeroDocumento, other.numeroDocumento)) {
			return false;
		}

		if (!Objects.equals(this.descEmpresa, other.descEmpresa)) {
			return false;
		}

		if (!Objects.equals(this.estado, other.estado)) {
			return false;
		}

		if (!Objects.equals(this.logo, other.logo)) {
			return false;
		}

		if (!Objects.equals(this.sucursalDavivienda, other.sucursalDavivienda)) {
			return false;
		}

		if (!Objects.equals(this.firma, other.firma)) {
			return false;
		}

		if (!Objects.equals(this.registroSic, other.registroSic)) {
			return false;
		}

		if (!Objects.equals(this.registroPrivado, other.registroPrivado)) {
			return false;
		}

		if (!Objects.equals(this.cargaArchivoPlano, other.cargaArchivoPlano)) {
			return false;
		}

		if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}

	@Override
	public String getConsecutivo() {
		return null;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}