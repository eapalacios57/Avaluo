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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_INFINMUEBLE database table.
 * 
 */
@Entity
@Table(name = "PGB_INFINMUEBLE")
@NamedQuery(name = "InformacionInmueble.idAvaluo", query = "SELECT p FROM InformacionInmueble p WHERE p.idAvaluo=:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class InformacionInmueble implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_INFINMUEBLE_PK = "idInfinmueble";
	public static final String ENTIDAD_PGB_INFINMUEBLE_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_TIPOVIVIENDA = "cTipovivienda";
	public static final String ENTIDAD_PGB_INFINMUEBLE_IDCATEGORIA = "idcategoria";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_UBICACION2 = "cUbicacion2";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_CHIP = "tChip";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_USOINMUEBLE = "cUsoinmueble";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_OTROUSOINM = "tOtrousoinm";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_CLASEINMUEBLE = "cClaseinmueble";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_OTROCLASE = "tOtroclase";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMBPPAL1 = "tMinmbppal1";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMBPPAL2 = "tMinmbppal2";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ1 = "tMinmobgj1";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ2 = "tMinmobgj2";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ3 = "tMinmobgj3";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ4 = "tMinmobgj4";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ5 = "tMinmobgj5";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMOBDP1 = "tMinmobdp1";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_MINMOBDP2 = "tMinmobdp2";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_NUMESCRITURA = "tNumescritura";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_NOTARIA = "tNotaria";
	public static final String ENTIDAD_PGB_INFINMUEBLE_F_FECHAESCRITURA = "fFechaescritura";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_DEPTOESCRITURA = "cDeptoescritura";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_CIUDADESCRITURA = "cCiudadescritura";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_UBICACION3 = "cUbicacion3";
	public static final String ENTIDAD_PGB_INFINMUEBLE_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_INFINMUEBLE_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_INFINMUEBLE_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_INFINMUEBLE_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_INFINMUEBLE_A_EDICONSTUSO = "aEdiconstuso";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_USOINMUEBLE_BUA = "cUsoinmuebleBua";
	public static final String ENTIDAD_PGB_INFINMUEBLE_C_USOINMUEBLE_SF = "cUsoinmuebleSf";
	public static final String ENTIDAD_PGB_INFINMUEBLE_T_CATASTRAL_SF = "tCatastralSf";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_INFINMUEBLE = { ENTIDAD_PGB_INFINMUEBLE_C_USOINMUEBLE_SF,
			ENTIDAD_PGB_INFINMUEBLE_A_EDICONSTUSO, ENTIDAD_PGB_INFINMUEBLE_T_NOTARIA,
			ENTIDAD_PGB_INFINMUEBLE_C_USOINMUEBLE_BUA, ENTIDAD_PGB_INFINMUEBLE_ID_AVALUO,
			ENTIDAD_PGB_INFINMUEBLE_USUARIO_CREACION, ENTIDAD_PGB_INFINMUEBLE_T_CHIP,
			ENTIDAD_PGB_INFINMUEBLE_C_TIPOVIVIENDA, ENTIDAD_PGB_INFINMUEBLE_IDCATEGORIA,
			ENTIDAD_PGB_INFINMUEBLE_T_CATASTRAL_SF, ENTIDAD_PGB_INFINMUEBLE_USUARIO_TRANSACCION,
			ENTIDAD_PGB_INFINMUEBLE_C_USOINMUEBLE, ENTIDAD_PGB_INFINMUEBLE_T_MINMBPPAL1,
			ENTIDAD_PGB_INFINMUEBLE_T_NUMESCRITURA, ENTIDAD_PGB_INFINMUEBLE_T_OTROCLASE,
			ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ5, ENTIDAD_PGB_INFINMUEBLE_PK, ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ4,
			ENTIDAD_PGB_INFINMUEBLE_C_UBICACION3, ENTIDAD_PGB_INFINMUEBLE_C_UBICACION2,
			ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ3, ENTIDAD_PGB_INFINMUEBLE_C_DEPTOESCRITURA,
			ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ2, ENTIDAD_PGB_INFINMUEBLE_T_MINMOBGJ1,
			ENTIDAD_PGB_INFINMUEBLE_FECHA_CREACION, ENTIDAD_PGB_INFINMUEBLE_F_FECHAESCRITURA,
			ENTIDAD_PGB_INFINMUEBLE_T_MINMOBDP2, ENTIDAD_PGB_INFINMUEBLE_T_MINMOBDP1,
			ENTIDAD_PGB_INFINMUEBLE_C_CIUDADESCRITURA, ENTIDAD_PGB_INFINMUEBLE_FECHA_TRANSACCION,
			ENTIDAD_PGB_INFINMUEBLE_T_MINMBPPAL2, ENTIDAD_PGB_INFINMUEBLE_T_OTROUSOINM,
			ENTIDAD_PGB_INFINMUEBLE_C_CLASEINMUEBLE };

	@Id
	@SequenceGenerator(name = "PGB_INFINMUEBLE_IDINFINMUEBLE_GENERATOR", sequenceName = "SEQ_PGB_INFINMUEBLE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_INFINMUEBLE_IDINFINMUEBLE_GENERATOR")
	@Column(name = "ID_INFINMUEBLE", unique = true, nullable = false, precision = 10)
	private Long idInfinmueble;

	@Column(name = "C_CIUDADESCRITURA", precision = 13)
	private Long ciudadEscritura;

	@Column(name = "C_CLASEINMUEBLE", precision = 3)
	private Long claseInmueble;

	@Column(name = "C_DEPTOESCRITURA", precision = 13)
	private Long departamentoEscritura;

	@Column(name = "C_TIPOVIVIENDA", precision = 3)
	private Long tipoVivienda;

	@Column(name = "C_UBICACION2", precision = 3)
	private Long ubicacion2;

	@Column(name = "C_UBICACION3", precision = 3)
	private Long ubicacion3;

	@Column(name = "C_USOINMUEBLE", nullable = false, precision = 3)
	private Long usoInmueble;

	@Column(name = "C_USOINMUEBLE_BUA")
	private Long usoInmuebleBUA;

	@Column(name = "C_USOINMUEBLE_SF")
	private Long usoInmuebleSF;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_FECHAESCRITURA")
	private Date fechaEscritura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "IDCATEGORIA", nullable = false, precision = 3)
	private Long idCategoria;

	//
	@Column(name = "T_CATASTRAL_SF")
	private String catastralSF;

	@Column(name = "T_CHIP", length = 20)
	private String chip;

	@Column(name = "T_MINMBPPAL1", length = 20)
	private String matriculaInmobiliariaPpal1;

	@Column(name = "T_MINMBPPAL2", length = 20)
	private String matriculaInmobiliariaPpal2;

	@Column(name = "T_MINMOBDP1", length = 20)
	private String matriculaInmobiliariaDeposito1;

	@Column(name = "T_MINMOBDP2", length = 20)
	private String matriculaInmobiliariaDeposito2;

	@Column(name = "T_MINMOBGJ1", length = 20)
	private String matriculaInmobiliariaGaraje1;

	@Column(name = "T_MINMOBGJ2", length = 20)
	private String matriculaInmobiliariaGaraje2;

	@Column(name = "T_MINMOBGJ3", length = 20)
	private String matriculaInmobiliariaGaraje3;

	@Column(name = "T_MINMOBGJ4", length = 20)
	private String matriculaInmobiliariaGaraje4;

	@Column(name = "T_MINMOBGJ5", length = 20)
	private String matriculaInmobiliariaGaraje5;

	@Column(name = "T_NOTARIA", length = 5)
	private String notaria;

	@Column(name = "T_NUMESCRITURA", length = 5)
	private String numeroEscritura;

	@Column(name = "T_OTROCLASE", length = 30)
	private String otroClase;

	@Column(name = "T_OTROUSOINM", length = 30)
	private String otroUsoInmueble;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	@Column(name = "A_EDICONSTUSO", precision = 3)
	private Long ediContUso;

	@PodamExclude
	@Column(name = "ID_AVALUO")
	private Long idAvaluo;

	// bi-directional many-to-one association to Avaluo
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", nullable = false, referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	private Avaluo avaluo;
	
	@Transient
	private Long numeroIdentificacion; 
	@Transient
	private String usoInmuebleDescripcion; //USOINMUEBLE
	@Transient
	private String usoInmuebleBuaDescripcion; //USOINMUEBLE_BUA
	@Transient
	private String ediContUsoDescripcion; //AFIRMAR
	@Transient
	private String ubicacion2Descripcion; //UBICACION2
	@Transient
	private String departamentoEscrituraNombre; 
	@Transient
	private String ciudadEscrituraNombre; 
	@Transient
	private String ubicacion3Descripcion; // UBICACION3 
	@Transient
	private String categoriaDescripcion; // CATEGORIA 
	@Transient
	private String claseDescripcion; // CLASEINMUEBLE 
	@Transient
	private String tipoViviendaDescripcion; // TIPOVIVIENDA 

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public InformacionInmueble() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end

	}

	@Override
	public InformacionInmueble clone() throws CloneNotSupportedException {
		return (InformacionInmueble) super.clone();
	}

	public Long getIdInfinmueble() {
		return this.idInfinmueble;
	}

	public void setIdInfinmueble(Long idInfinmueble) {
		this.idInfinmueble = idInfinmueble;
	}

	public Long getUbicacion2() {
		return this.ubicacion2;
	}

	public void setUbicacion2(Long ubicacion2) {
		this.ubicacion2 = ubicacion2;
	}

	public Long getUbicacion3() {
		return this.ubicacion3;
	}

	public void setUbicacion3(Long ubicacion3) {
		this.ubicacion3 = ubicacion3;
	}

	public Date getFechaEscritura() {
		return this.fechaEscritura;
	}

	public void setFechaEscritura(Date fechaEscritura) {
		this.fechaEscritura = fechaEscritura;
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

	public Long getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCatastralSF() {
		return catastralSF;
	}

	public void setCatastralSF(String catastralSF) {
		this.catastralSF = catastralSF;
	}

	public String getChip() {
		return this.chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public String getMatriculaInmobiliariaPpal1() {
		return this.matriculaInmobiliariaPpal1;
	}

	public void setMatriculaInmobiliariaPpal1(String matriculaInmobiliariaPpal1) {
		this.matriculaInmobiliariaPpal1 = matriculaInmobiliariaPpal1;
	}

	public String getMatriculaInmobiliariaPpal2() {
		return this.matriculaInmobiliariaPpal2;
	}

	public void setMatriculaInmobiliariaPpal2(String matriculaInmobiliariaPpal2) {
		this.matriculaInmobiliariaPpal2 = matriculaInmobiliariaPpal2;
	}

	public String getMatriculaInmobiliariaDeposito1() {
		return this.matriculaInmobiliariaDeposito1;
	}

	public void setMatriculaInmobiliariaDeposito1(String matriculaInmobiliariaDeposito1) {
		this.matriculaInmobiliariaDeposito1 = matriculaInmobiliariaDeposito1;
	}

	public String getMatriculaInmobiliariaDeposito2() {
		return this.matriculaInmobiliariaDeposito2;
	}

	public void setMatriculaInmobiliariaDeposito2(String matriculaInmobiliariaDeposito2) {
		this.matriculaInmobiliariaDeposito2 = matriculaInmobiliariaDeposito2;
	}

	public String getMatriculaInmobiliariaGaraje1() {
		return this.matriculaInmobiliariaGaraje1;
	}

	public void setMatriculaInmobiliariaGaraje1(String matriculaInmobiliariaGaraje1) {
		this.matriculaInmobiliariaGaraje1 = matriculaInmobiliariaGaraje1;
	}

	public String getMatriculaInmobiliariaGaraje2() {
		return this.matriculaInmobiliariaGaraje2;
	}

	public void setMatriculaInmobiliariaGaraje2(String matriculaInmobiliariaGaraje2) {
		this.matriculaInmobiliariaGaraje2 = matriculaInmobiliariaGaraje2;
	}

	public String getMatriculaInmobiliariaGaraje3() {
		return this.matriculaInmobiliariaGaraje3;
	}

	public void setMatriculaInmobiliariaGaraje3(String matriculaInmobiliariaGaraje3) {
		this.matriculaInmobiliariaGaraje3 = matriculaInmobiliariaGaraje3;
	}

	public String getMatriculaInmobiliariaGaraje4() {
		return this.matriculaInmobiliariaGaraje4;
	}

	public void setMatriculaInmobiliariaGaraje4(String matriculaInmobiliariaGaraje4) {
		this.matriculaInmobiliariaGaraje4 = matriculaInmobiliariaGaraje4;
	}

	public String getMatriculaInmobiliariaGaraje5() {
		return this.matriculaInmobiliariaGaraje5;
	}

	public void setMatriculaInmobiliariaGaraje5(String matriculaInmobiliariaGaraje5) {
		this.matriculaInmobiliariaGaraje5 = matriculaInmobiliariaGaraje5;
	}

	public String getNotaria() {
		return this.notaria;
	}

	public void setNotaria(String notaria) {
		this.notaria = notaria;
	}

	public String getNumeroEscritura() {
		return this.numeroEscritura;
	}

	public void setNumeroEscritura(String numeroEscritura) {
		this.numeroEscritura = numeroEscritura;
	}

	public String getOtroClase() {
		return this.otroClase;
	}

	public void setOtroClase(String otroClase) {
		this.otroClase = otroClase;
	}

	public String getOtroUsoInmueble() {
		return this.otroUsoInmueble;
	}

	public void setOtroUsoInmueble(String otroUsoInmueble) {
		this.otroUsoInmueble = otroUsoInmueble;
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

	public Avaluo getAvaluo() {
		return this.avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public Long getEdiContUso() {
		return ediContUso;
	}

	public void setEdiContUso(Long ediContUso) {
		this.ediContUso = ediContUso;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_INFINMUEBLE) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadInformacionInmueble() {
		return ATRIBUTOS_ENTIDAD_PGB_INFINMUEBLE;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idInfinmueble);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.tipoVivienda);
		hash = 37 * hash + Objects.hashCode(this.idCategoria);
		hash = 37 * hash + Objects.hashCode(this.ubicacion2);
		hash = 37 * hash + Objects.hashCode(this.chip);
		hash = 37 * hash + Objects.hashCode(this.usoInmueble);
		hash = 37 * hash + Objects.hashCode(this.otroUsoInmueble);
		hash = 37 * hash + Objects.hashCode(this.claseInmueble);
		hash = 37 * hash + Objects.hashCode(this.otroClase);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaPpal1);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaPpal2);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaGaraje1);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaGaraje2);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaGaraje3);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaGaraje4);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaGaraje5);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaDeposito1);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliariaDeposito2);
		hash = 37 * hash + Objects.hashCode(this.numeroEscritura);
		hash = 37 * hash + Objects.hashCode(this.notaria);
		hash = 37 * hash + Objects.hashCode(this.fechaEscritura);
		hash = 37 * hash + Objects.hashCode(this.departamentoEscritura);
		hash = 37 * hash + Objects.hashCode(this.ciudadEscritura);
		hash = 37 * hash + Objects.hashCode(this.ubicacion3);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.ediContUso);
		hash = 37 * hash + Objects.hashCode(this.usoInmuebleBUA);
		hash = 37 * hash + Objects.hashCode(this.usoInmuebleSF);
		hash = 37 * hash + Objects.hashCode(this.catastralSF);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad InformacionInmueble que
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
		final InformacionInmueble other = (InformacionInmueble) obj;

		if (!Objects.equals(this.idInfinmueble, other.idInfinmueble)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.tipoVivienda, other.tipoVivienda)) {
			return false;
		}

		if (!Objects.equals(this.idCategoria, other.idCategoria)) {
			return false;
		}

		if (!Objects.equals(this.ubicacion2, other.ubicacion2)) {
			return false;
		}

		if (!Objects.equals(this.chip, other.chip)) {
			return false;
		}

		if (!Objects.equals(this.usoInmueble, other.usoInmueble)) {
			return false;
		}

		if (!Objects.equals(this.otroUsoInmueble, other.otroUsoInmueble)) {
			return false;
		}

		if (!Objects.equals(this.claseInmueble, other.claseInmueble)) {
			return false;
		}

		if (!Objects.equals(this.otroClase, other.otroClase)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaPpal1, other.matriculaInmobiliariaPpal1)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaPpal2, other.matriculaInmobiliariaPpal2)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaGaraje1, other.matriculaInmobiliariaGaraje1)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaGaraje2, other.matriculaInmobiliariaGaraje2)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaGaraje3, other.matriculaInmobiliariaGaraje3)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaGaraje4, other.matriculaInmobiliariaGaraje4)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaGaraje5, other.matriculaInmobiliariaGaraje5)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaDeposito1, other.matriculaInmobiliariaDeposito1)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliariaDeposito2, other.matriculaInmobiliariaDeposito2)) {
			return false;
		}

		if (!Objects.equals(this.numeroEscritura, other.numeroEscritura)) {
			return false;
		}

		if (!Objects.equals(this.notaria, other.notaria)) {
			return false;
		}

		if (!Objects.equals(this.fechaEscritura, other.fechaEscritura)) {
			return false;
		}

		if (!Objects.equals(this.departamentoEscritura, other.departamentoEscritura)) {
			return false;
		}

		if (!Objects.equals(this.ciudadEscritura, other.ciudadEscritura)) {
			return false;
		}

		if (!Objects.equals(this.ubicacion3, other.ubicacion3)) {
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

		if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
			return false;
		}

		if (!Objects.equals(this.ediContUso, other.ediContUso)) {
			return false;
		}

		if (!Objects.equals(this.usoInmuebleBUA, other.usoInmuebleBUA)) {
			return false;
		}

		if (!Objects.equals(this.usoInmuebleSF, other.usoInmuebleSF)) {
			return false;
		}

		return Objects.equals(this.catastralSF, other.catastralSF);

	}

	public Long getCiudadEscritura() {
		return ciudadEscritura;
	}

	public void setCiudadEscritura(Long ciudadEscritura) {
		this.ciudadEscritura = ciudadEscritura;
	}

	public Long getClaseInmueble() {
		return claseInmueble;
	}

	public void setClaseInmueble(Long claseInmueble) {
		this.claseInmueble = claseInmueble;
	}

	public Long getDepartamentoEscritura() {
		return departamentoEscritura;
	}

	public void setDepartamentoEscritura(Long departamentoEscritura) {
		this.departamentoEscritura = departamentoEscritura;
	}

	public Long getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(Long tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public Long getUsoInmueble() {
		return usoInmueble;
	}

	public void setUsoInmueble(Long usoInmueble) {
		this.usoInmueble = usoInmueble;
	}

	public Long getUsoInmuebleBUA() {
		return usoInmuebleBUA;
	}

	public void setUsoInmuebleBUA(Long usoInmuebleBUA) {
		this.usoInmuebleBUA = usoInmuebleBUA;
	}

	public Long getUsoInmuebleSF() {
		return usoInmuebleSF;
	}

	public void setUsoInmuebleSF(Long usoInmuebleSF) {
		this.usoInmuebleSF = usoInmuebleSF;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public String getUsoInmuebleDescripcion() {
		return usoInmuebleDescripcion;
	}

	public void setUsoInmuebleDescripcion(String usoInmuebleDescripcion) {
		this.usoInmuebleDescripcion = usoInmuebleDescripcion;
	}

	public String getUsoInmuebleBuaDescripcion() {
		return usoInmuebleBuaDescripcion;
	}

	public void setUsoInmuebleBuaDescripcion(String usoInmuebleBuaDescripcion) {
		this.usoInmuebleBuaDescripcion = usoInmuebleBuaDescripcion;
	}

	public String getEdiContUsoDescripcion() {
		return ediContUsoDescripcion;
	}

	public void setEdiContUsoDescripcion(String ediContUsoDescripcion) {
		this.ediContUsoDescripcion = ediContUsoDescripcion;
	}

	public String getUbicacion2Descripcion() {
		return ubicacion2Descripcion;
	}

	public void setUbicacion2Descripcion(String ubicacion2Descripcion) {
		this.ubicacion2Descripcion = ubicacion2Descripcion;
	}

	public String getDepartamentoEscrituraNombre() {
		return departamentoEscrituraNombre;
	}

	public void setDepartamentoEscrituraNombre(String departamentoEscrituraNombre) {
		this.departamentoEscrituraNombre = departamentoEscrituraNombre;
	}

	public String getCiudadEscrituraNombre() {
		return ciudadEscrituraNombre;
	}

	public void setCiudadEscrituraNombre(String ciudadEscrituraNombre) {
		this.ciudadEscrituraNombre = ciudadEscrituraNombre;
	}

	public String getUbicacion3Descripcion() {
		return ubicacion3Descripcion;
	}

	public void setUbicacion3Descripcion(String ubicacion3Descripcion) {
		this.ubicacion3Descripcion = ubicacion3Descripcion;
	}

	public String getCategoriaDescripcion() {
		return categoriaDescripcion;
	}

	public void setCategoriaDescripcion(String categoriaDescripcion) {
		this.categoriaDescripcion = categoriaDescripcion;
	}

	public String getClaseDescripcion() {
		return claseDescripcion;
	}

	public void setClaseDescripcion(String claseDescripcion) {
		this.claseDescripcion = claseDescripcion;
	}

	public String getTipoViviendaDescripcion() {
		return tipoViviendaDescripcion;
	}

	public void setTipoViviendaDescripcion(String tipoViviendaDescripcion) {
		this.tipoViviendaDescripcion = tipoViviendaDescripcion;
	}

	public Long getNumeroIdentificacion() {
	    return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
	    this.numeroIdentificacion = numeroIdentificacion;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}