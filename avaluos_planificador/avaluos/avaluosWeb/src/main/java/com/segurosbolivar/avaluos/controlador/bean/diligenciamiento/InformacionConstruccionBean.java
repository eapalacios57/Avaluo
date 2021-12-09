package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.DominioBean;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.dto.InformacionConstruccionDto;
import com.segurosbolivar.avaluos.modelo.cons.SeccionConstruccion;
import com.segurosbolivar.avaluos.modelo.cons.TipoInforme;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

/**
 * Controlador para la vista de la secci�n informaci�n de la construcci�n, que
 * hace parte del diligenciamiento del aval�o.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@ManagedBean(name = "construccionBean")
@SessionScoped
public class InformacionConstruccionBean extends GeneralAbstractoBean implements Serializable{

	public DominioBean getDominio() {
		return dominio;
	}

	public void setDominio(DominioBean dominio) {
		this.dominio = dominio;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private static final long serialVersionUID = -356885602102518992L;
	private Avaluo avaluo;
	private InformacionConstruccionDto construccion;
	private CondicionesSalubridad salubridad;
	private boolean ocultarAuditoria;
	private boolean noEditarPisos;
	private boolean verNombreConstructora;
	private boolean agregarCorreo;
	private boolean agregarTelefono;
	private List<SelectItem> materialEstructura;
	private List<SelectItem> detalleMaterial;
	private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";
	private int verCorreo;
	private int verTelefono;
	private String label;
	private DominioBean dominio;

	private int tab;
	@EJB
	public transient IAvaluoFacade avaluoFacade;
	private boolean habilitarProyectoConstructor;

	public static InformacionConstruccionBean getBean() {
		return UtilJsf.obtenerBackingBean("construccionBean");
	}

	@Override
	public void inicio() {
		// No es necesario inicializar ninguna clase.
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public void editar(Avaluo avaluo) {
		try {
			log.info("---inicia  vista sec. construccion");
			if (avaluo == null) {
				throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
			}
			verAdicional();
			this.avaluo = avaluo;
			InformacionConstruccion entidad = avaluoFacade.consultarConstruccion(avaluo.getIdAvaluo());
			CondicionesSalubridad entidadSalubridad = avaluoFacade.consultarSalubridad(avaluo.getIdAvaluo());
			if (entidad == null) {
				entidad = new InformacionConstruccion();
				entidad.setIdAvaluo(avaluo.getIdAvaluo());
				entidad.setAvaluo(avaluo);
				if (getUsuario() != null && getUsuario().getUsuario() != null) {
					entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
					entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
				}
				entidad.setFechaCreacion(new Date());
				entidad.setFechaTransaccion(new Date());
			}
			this.construccion = new InformacionConstruccionDto(entidad);
			if (entidadSalubridad == null) {
				entidadSalubridad = new CondicionesSalubridad();
				entidadSalubridad.setIdAvaluo(avaluo.getIdAvaluo());
				entidadSalubridad.setAvaluo(avaluo);
				if (getUsuario() != null && getUsuario().getUsuario() != null) {
					entidadSalubridad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
					entidadSalubridad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
				}
				entidadSalubridad.setFechaCreacion(new Date());
				entidadSalubridad.setFechaTransaccion(new Date());
			}
			this.salubridad = entidadSalubridad;
			ocultarAuditoria = true;
			iniciarCombos();
			
			if (!UtilTexto.estaVacio(this.construccion.getConstruccion().getCorreoAdministrador3())) {
				verCorreo = 2;
			    } else if (!UtilTexto.estaVacio(this.construccion.getConstruccion().getCorreoAdministrador2())) {
				verCorreo = 1;
			    } else 
				verCorreo = 0;
			
			if (!(this.construccion.getConstruccion().getTelefonoAdministrador3() == null)) {
				verTelefono = 2;
			    } else if (!(this.construccion.getConstruccion().getTelefonoAdministrador2() == null)) {
				verTelefono = 1;
			    } else 
				verTelefono = 0;
			    
			log.info("---finaliza  vista sec. construccion");
		} catch (Exception e) {
			procesarError(e);
		}
	}

	private void iniciarCombos() {
		log.info("---inicia  combos sec. construccion");
		if (avaluo.isConstructor()) {
//			lrm modificación para que no actualice automaticamente el estado Nuevo y Terminada Solicitud de Cambio
//			this.construccion.getConstruccion().setEstadoConstruccion(1L);
//			this.construccion.getConstruccion().setEstadoTerminadaNueva(1L);
			this.construccion.getConstruccion().setEstadoConservacion(1L);
		}
		Long estructura = construccion.getConstruccion().getEstructura() == null ? null
				: construccion.getConstruccion().getEstructura();
		if (estructura != null && (estructura.compareTo(3L) == 0 || estructura.compareTo(5L) == 0))
			estructura = 1L;
		materialEstructura = ListaBean.getBean().cargarDependiente("ESTRUCTURA2_BUA",
				estructura == null ? null : estructura.toString());
		Long materialEstructuraValor = construccion.getConstruccion().getMaterialConstruccionBUA() == null ? null
				: construccion.getConstruccion().getMaterialConstruccionBUA();
		detalleMaterial = ListaBean.getBean().cargarDependiente("C_ESTRUCTURA_SF_BUA",
				materialEstructuraValor == null ? null : materialEstructuraValor.toString());
		Long codigoConstructora = this.construccion.getConstruccion() != null
				&& this.construccion.getConstruccion().getCodigoNombreConstructora() != null
						? this.construccion.getConstruccion().getCodigoNombreConstructora()
						: 0L;
		verNombreConstructora = codigoConstructora != null && codigoConstructora.compareTo(7L) == 0;
		habilitarProyectoConstructor = this.construccion.getConstruccion() != null
				&& this.construccion.getConstruccion().getEstadoConstruccion() != null
				&& this.construccion.getConstruccion().getEstadoConstruccion().compareTo(1L) == 0;
		InformacionInmueble inmueble = InformacionInmuebleBean.getBean().getInmueble();
		if (inmueble != null && inmueble.getIdCategoria() != null && inmueble.getIdCategoria().compareTo(12L) == 0) {
			this.construccion.getConstruccion().setPisos(0L);
			this.construccion.getConstruccion().setTotalUnidades(0L);
			noEditarPisos = true;
		} else
			noEditarPisos = false;
		log.info("---finaliza  combos sec. construccion");
	}
	
	

	public void cambiarAscensores() {
		construccion.getConstruccion().setNumeroAscensores(construccion.isAscensor() ? 1L : null);
	}

	public void anteriorSuperior() {
		tab--;
	}

	public void siguienteSuperior() {
		tab++;
	}

	public void verAdicional() {
		tab = 0;
	}

	public void verDependencias() {
		tab = 1;
	}

	public void verSalubridad() {
		tab = 2;
	}

	public void verAcabados() {
		tab = 3;
	}

	public void verHorizontal() {
		tab = 4;
	}

	public void verDotacion() {
		tab = 5;
	}

	public void cambiarNumeroPisos(ValueChangeEvent evento) {
		if (evento == null)
			return;
		Long pisos = (Long) evento.getNewValue();
		Long numeroEdificios = construccion.getConstruccion().getNumeroEdificios();
		Long unidades = construccion.getConstruccion().getUnidadPorPiso();
		if (construccion.getConstruccion().getPropiedadHorizontal() != null
				&& construccion.getConstruccion().getPropiedadHorizontal().compareTo(2L) != 0)
			construccion.getConstruccion().setTotalUnidades((pisos == null ? 1 : pisos)
					* (numeroEdificios == null ? 1 : numeroEdificios) * (unidades == null ? 1 : unidades));
		else if (construccion.getConstruccion().getPropiedadHorizontal() == null)
			construccion.getConstruccion().setTotalUnidades(0L);
	}

	public void cambiarConservacionOptimo(ValueChangeEvent evento) {
		if (evento == null || evento.getNewValue() == null)
			return;
		String terminado = evento.getNewValue().toString();
		if (terminado.equals("1"))
			construccion.getConstruccion().setEstadoConservacion(1L);
	}

	public void cambiarNumeroEdificios(ValueChangeEvent evento) {
		if (evento == null)
			return;
		Long numeroEdificios = (Long) evento.getNewValue();
		Long pisos = construccion.getConstruccion().getPisos();
		Long unidades = construccion.getConstruccion().getUnidadPorPiso();
		if (construccion.getConstruccion().getPropiedadHorizontal() != null
				&& construccion.getConstruccion().getPropiedadHorizontal().compareTo(2L) != 0)
			construccion.getConstruccion().setTotalUnidades((numeroEdificios == null ? 1 : numeroEdificios)
					* (pisos == null ? 1 : pisos) * (unidades == null ? 1 : unidades));
		else
			construccion.getConstruccion().setTotalUnidades(1L);
	}

	public void cambiarUnidadesPiso(ValueChangeEvent evento) {
		if (evento == null)
			return;
		Long unidades = (Long) evento.getNewValue();
		Long pisos = construccion.getConstruccion().getPisos();
		Long numeroEdificios = construccion.getConstruccion().getNumeroEdificios();
		if (construccion.getConstruccion().getPropiedadHorizontal() != null
				&& construccion.getConstruccion().getPropiedadHorizontal().compareTo(2L) != 0) {
			construccion.getConstruccion().setTotalUnidades((unidades == null ? 1 : unidades)
					* (pisos == null ? 1 : pisos) * (numeroEdificios == null ? 1 : numeroEdificios));
		} else if (construccion.getConstruccion().getPropiedadHorizontal() == null && unidades != null && pisos != null
				&& numeroEdificios != null) {
			construccion.getConstruccion().setTotalUnidades((unidades == null ? 1 : unidades)
					* (pisos == null ? 1 : pisos) * (numeroEdificios == null ? 1 : numeroEdificios));
		} else if (construccion.getConstruccion().getPropiedadHorizontal() == null && unidades == null && pisos == null
				&& numeroEdificios == null) {
			construccion.getConstruccion().setTotalUnidades(1L);
		}
	}

	public void cambiarPropiedadHorizontal(ValueChangeEvent evento) {
		if (evento == null || evento.getNewValue() == null) {
			construccion.getConstruccion().setTotalUnidades(0L);
			return;
		}
		Long valor = (Long) evento.getNewValue();
		if (valor.compareTo(2L) == 0) {
			construccion.getConstruccion().setTotalUnidades(1L);
			return;
		}
		Long pisos = construccion.getConstruccion().getPisos() == null ? 1 : construccion.getConstruccion().getPisos();
		Long unidades = construccion.getConstruccion().getUnidadPorPiso() == null ? 1
				: construccion.getConstruccion().getUnidadPorPiso();
		Long edificios = construccion.getConstruccion().getNumeroEdificios() == null ? 1
				: construccion.getConstruccion().getNumeroEdificios();
		construccion.getConstruccion().setTotalUnidades(pisos * unidades * edificios);
	}

	public void cambiarConstructora(ValueChangeEvent evento) {
		if (evento == null || evento.getNewValue() == null)
			return;
		Long valor = (Long) evento.getNewValue();
		verNombreConstructora = valor.compareTo(7L) == 0;
	}

	public void cambiarEstructura(ValueChangeEvent evento) {
		if (evento == null || evento.getNewValue() == null) {
			materialEstructura = new ArrayList<>();
			detalleMaterial = new ArrayList<>();
			return;
		}
		Long valor = (Long) evento.getNewValue();
		if (valor.compareTo(3L) == 0 || valor.compareTo(5L) == 0)
			valor = 1L;
		materialEstructura = ListaBean.getBean().cargarDependiente("ESTRUCTURA2_BUA", valor.toString());
		construccion.getConstruccion().setMaterialConstruccionBUA(null);
		construccion.getConstruccion().setDetalleMaterialSFBUA(null);
		if (materialEstructura != null && materialEstructura.size() == 1) {
			detalleMaterial = ListaBean.getBean().cargarDependiente("C_ESTRUCTURA_SF_BUA",
					materialEstructura.get(0).getValue().toString());
			construccion.getConstruccion().setMaterialConstruccionBUA(
					UtilNumero.pasarEntero(materialEstructura.get(0).getValue().toString()));
			if (detalleMaterial != null && detalleMaterial.size() == 1)
				construccion.getConstruccion()
						.setDetalleMaterialSFBUA(UtilNumero.pasarEntero(detalleMaterial.get(0).getValue().toString()));
		} else
			detalleMaterial = new ArrayList<>();
	}

	public void cambiarMaterialEstructura(ValueChangeEvent evento) {
		if (evento == null || evento.getNewValue() == null) {
			detalleMaterial = new ArrayList<>();
			return;
		}
		Long valor = (Long) evento.getNewValue();
		detalleMaterial = ListaBean.getBean().cargarDependiente("C_ESTRUCTURA_SF_BUA", valor.toString());
		construccion.getConstruccion().setDetalleMaterialSFBUA(null);
		if (detalleMaterial != null && detalleMaterial.size() == 1)
			construccion.getConstruccion()
					.setDetalleMaterialSFBUA(UtilNumero.pasarEntero(detalleMaterial.get(0).getValue().toString()));
	}

	public void cambiarEstadoConstruccion(ValueChangeEvent evento) {
		construccion.getConstruccion().setEstadoTerminadaNueva(null);
		construccion.getConstruccion().setEstadoTerminadoUsado(null);
		construccion.getConstruccion().setSinTerminar(null);
		construccion.getConstruccion().setEnObra(null);
		construccion.getConstruccion().setEstadoRemodelacion(null);
		construccion.getConstruccion().setAvanceObra(null);
		if (evento == null || evento.getNewValue() == null)
			return;
		habilitarProyectoConstructor = ((Long) evento.getNewValue()).compareTo(1L) == 0;
		if (!habilitarProyectoConstructor)
			construccion.setCodigoFinanciadoConstructor(false);
		if (evento.getNewValue().toString().equals("2"))
			construccion.getConstruccion().setEstadoConservacion(0L);
	}

	public void anterior() {
		try {
			if (isEditable())
				guardar();
			retroceder();
		} catch (NegocioAlertaException e) {
			if (e.getId() == 57)
				retroceder();
			procesarError("avaluoForm:errorEspecifico", e);
		} catch (Exception e) {
			procesarError("avaluoForm:errorEspecifico", e);
		}
	}

	public void retroceder() {
		if (tab == 0)
			AvaluoBean.getBean().setTab(2);
		else
			tab--;
	}

	public void guardar() throws NegocioException {
		if (!validar())
			throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
		if (tab != 2) {
			log.info("---Se inicia  la accion de guardar sec. construccion");
			avaluoFacade.guardar(construccion.getConstruccion(), SeccionConstruccion.obtener(tab + 1), avaluo,
					getUsuario());
			log.info("---termina  la accion de guardar sec. construccion");
			avaluo.setInformacionConstruccion(construccion.getConstruccion());
			refrescarTipoProyecto();
			UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("inc_menGuardar"));
			LiquidacionBean.getBean()
					.cambiarPropiedadHorizontal(construccion.getConstruccion().getPropiedadHorizontal());
			LiquidacionBean.getBean().editar(avaluo);
		} else {
			log.info("---Se inicia  la accion de guardar las condiciones de salubridad");
			avaluoFacade.guardar(salubridad, getUsuario());
			log.info("---finaliza  la accion de guardar las condiciones de salubridad");
			avaluo.setCondicionSalubridad(salubridad);
			UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("inc_menGuardar"));
		}
	}
	
	public void llenar() {
		
		Long pisos = construccion.getConstruccion().getPisos() == null ? 0L : construccion.getConstruccion().getPisos();
		construccion.getConstruccion().setPisos(pisos);
		
		Long piso = construccion.getConstruccion().getPisoInmueble() == null ? 0L : construccion.getConstruccion().getPisoInmueble();
		construccion.getConstruccion().setPisoInmueble(piso);
		
		Long sotanos = construccion.getConstruccion().getSotanos() == null ? 0L : construccion.getConstruccion().getSotanos();
		construccion.getConstruccion().setSotanos(sotanos);
		
		Long fConstructor = construccion.getConstruccion().getCodigoFinanciadoConstructor() == null ? 0L : construccion.getConstruccion().getCodigoFinanciadoConstructor();
		construccion.getConstruccion().setCodigoFinanciadoConstructor(fConstructor);
		
	}

	private void refrescarTipoProyecto() {
		if (construccion.getConstruccion().getEstadoConstruccion() != null
				&& construccion.getConstruccion().getEstadoConstruccion().compareTo(1L) != 0) {
			avaluo.setTipoInforme(TipoInforme.CREDITO.getValor());
		} else {
			if (construccion.getConstruccion().getSinTerminar() != null
					&& construccion.getConstruccion().getSinTerminar().compareTo(1L) == 0
					&& construccion.getConstruccion().getAvanceObra() != null
					&& construccion.getConstruccion().getAvanceObra().compareTo(100L) == 0) {
				avaluo.setTipoInforme(TipoInforme.PROYECTO.getValor());
			} else {
				avaluo.setTipoInforme(TipoInforme.CREDITO.getValor());
			}
		}
	}

	public void siguiente() {
		try {
			if (isEditable())
				guardar();
			avanzar();
		} catch (NegocioAlertaException e) {
			if (e.getId() == 57)
				avanzar();
			procesarError("avaluoForm:errorEspecifico", e);
		} catch (Exception e) {
			procesarError("avaluoForm:errorEspecifico", e);
		}
	}

	public void avanzar() {
		if (tab == 5)
			AvaluoBean.getBean().setTab(4);
		else
			tab++;
	}

	public boolean validar() throws NegocioException {
		switch (tab) {
		case 0:
			if (isEstadoConstruccionNueva() && UtilTexto.estaVacio(construccion.getEstadoConstruccionNuevo())) {
				throw mgrExc.lanzarExcepcion(20, TipoErrorNegocio.ALERTA);
			}
			if (isEstadoConstruccionUsada() && UtilTexto.estaVacio(construccion.getEstadoConstruccionUsado())) {
				throw mgrExc.lanzarExcepcion(21, TipoErrorNegocio.ALERTA);
			}
			return validarAdicional();
		case 1:
			return validarDependencias();
		case 2:
			return validarSalubridad();
		case 3:
			return validarAcabados();
		case 4:
			return validarHorizontal();
		case 5:
			return validarComunal();
		default:
			break;
		}
		return true;
	}
	
	public void verCorreo() {
		
		if (verCorreo < 2) {
		    verCorreo++;
		}
		if (verCorreo == 2) {
		    this.construccion.getConstruccion().setCorreoAdministrador3(null);
		} else if (verCorreo == 1) {
		    this.construccion.getConstruccion().setCorreoAdministrador2(null);
		}  
	    }
	public void noVerCorreo() {
		if (verCorreo > 0)
			verCorreo--;
		if (verCorreo == 1) {
		    this.construccion.getConstruccion().setCorreoAdministrador3(null);
		    } else if (verCorreo == 0) {
			    this.construccion.getConstruccion().setCorreoAdministrador2(null);
		} 
	    }
	
public void verTelefono() {
		
		if (verTelefono < 2) {
		    verTelefono++;
		}
		if (verTelefono == 2) {
		    this.construccion.getConstruccion().setTelefonoAdministrador3(null);
		} else if (verTelefono == 1) {
		    this.construccion.getConstruccion().setTelefonoAdministrador2(null);
		}  
	    }
public void noVerTelefono() {
	if (verTelefono > 0)
		verTelefono--;
	if (verTelefono == 1) {
	    this.construccion.getConstruccion().setTelefonoAdministrador3(null);
	    } else if (verTelefono == 0) {
		    this.construccion.getConstruccion().setTelefonoAdministrador2(null);
	} 
    }
public void cambiarLabel() {	
	 if(this.construccion.getConstruccion().getTipoAdministrador() == null ) {
		 this.label="";
		 return;
	 }		 
		 
	if (this.construccion.getConstruccion().getTipoAdministrador().equals("1")) {
		label = obtenerEtiqueta("inc_nombre_administrador");
	}else if(this.construccion.getConstruccion().getTipoAdministrador().equals("2")) {
		this.label = obtenerEtiqueta("inc_nombre_empresa_administradora");
	}else label="Administrador";

}


	public boolean validarAdicional() {
		boolean validar = true;
		llenar();
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPisos(),
				"avaluoForm:edicionAvaluosTab:pisosAdicional", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getSotanos(),
				"avaluoForm:edicionAvaluosTab:sotanos", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getVetustez(),
				"avaluoForm:edicionAvaluosTab:vetustez", false, 4, 4, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPisoInmueble(),
				"avaluoForm:edicionAvaluosTab:pisoInmueble", false, 4, null);
		validar &= UtilValidadorJsf.comparar(construccion.getConstruccion().getPisoInmueble(),
				TipoComparacion.MENOR_IGUAL, construccion.getConstruccion().getPisos(),
				"avaluoForm:edicionAvaluosTab:pisoInmueble", null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getUbicacionFuentesHidricas(),
				"avaluoForm:edicionAvaluosTab:ubicacionFuentesHidricas", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getUbicacionNivelVia(),
				"avaluoForm:edicionAvaluosTab:ubicacionNivelVia", false, 3, null);
		// el codigo de la constructora es obligatorio si el avaluo se marco
		// como construccion
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCodigoNombreConstructora(),
				"avaluoForm:edicionAvaluosTab:codigoNombreConstructora", avaluo != null && avaluo.isConstructor(), 15,
				null);
		// El nombre de la constructura es obligatorio si el codigo de la
		// constructora es otros.
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getNombreConstructora(),
				"avaluoForm:edicionAvaluosTab:nombreConstructora", verNombreConstructora, 100, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoConstruccion(),
				"avaluoForm:edicionAvaluosTab:estadoConstruccion", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getEstadoConstruccionNuevo(),
				"avaluoForm:edicionAvaluosTab:estadoTerminadaNueva", isEstadoConstruccionNueva(), 6, null);
		validar &= UtilValidadorJsf.validar(construccion.getEstadoConstruccionUsado(),
				"avaluoForm:edicionAvaluosTab:estadoTerminadoUsado", isEstadoConstruccionUsada(), 6, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getAvanceObra(),
				"avaluoForm:edicionAvaluosTab:avanceObra", construccion.isEnObra(), 3, null);
		validar &= UtilValidadorJsf.comparar(construccion.getConstruccion().getAvanceObra(),
				TipoComparacion.MENOR_IGUAL, 100L, "avaluoForm:edicionAvaluosTab:avanceObra", null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoRemodelacion(),
				"avaluoForm:edicionAvaluosTab:estadoRemodelacion", false, 3, null);

		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoConservacion(),
				"avaluoForm:edicionAvaluosTab:estadoConservacion", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getParapetosCubierta(),
				"avaluoForm:edicionAvaluosTab:parapetosCubierta", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstructura(),
				"avaluoForm:edicionAvaluosTab:estructura", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstructuraReforzadaBUA(),
				"avaluoForm:edicionAvaluosTab:estructuraReforzadaBUA", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getMaterialConstruccionBUA(),
				"avaluoForm:edicionAvaluosTab:materialEstructura", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getDetalleMaterialSFBUA(),
				"avaluoForm:edicionAvaluosTab:detalleMaterialSFBUA", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getIrregularidadPlantaBUA(),
				"avaluoForm:edicionAvaluosTab:irregularidadPlantaBUA", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getIrregularidadAlturaBUA(),
				"avaluoForm:edicionAvaluosTab:irregularidadAlturaBUA", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getFachada(),
				"avaluoForm:edicionAvaluosTab:fachada", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTipoFachada(),
				"avaluoForm:edicionAvaluosTab:tipoFachada", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCubierta(),
				"avaluoForm:edicionAvaluosTab:cubierta", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getGolpeteo(),
				"avaluoForm:edicionAvaluosTab:golpeteo", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getDanoPrevioBUA(),
				"avaluoForm:edicionAvaluosTab:danoPrevioBUA", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getReparadosBUA(),
				"avaluoForm:edicionAvaluosTab:reparadosBUA", false, 3, null);
		return validar;
	}

	public boolean validarDependencias() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getSala(),
				"avaluoForm:edicionAvaluosTab:sala", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBanioSocial(),
				"avaluoForm:edicionAvaluosTab:banioSocial", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBanioPrivado(),
				"avaluoForm:edicionAvaluosTab:banioPrivado", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBanioServicio(),
				"avaluoForm:edicionAvaluosTab:banioServicio", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getJardin(),
				"avaluoForm:edicionAvaluosTab:jardin", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getComedor(),
				"avaluoForm:edicionAvaluosTab:comedor", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstarHabitacion(),
				"avaluoForm:edicionAvaluosTab:estarHabitacion", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCocina(),
				"avaluoForm:edicionAvaluosTab:cocina", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPatioInterior(),
				"avaluoForm:edicionAvaluosTab:patioInterior", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBalcon(),
				"avaluoForm:edicionAvaluosTab:balcon", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstudio(),
				"avaluoForm:edicionAvaluosTab:estudio", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getNumeroHabitaciones(),
				"avaluoForm:edicionAvaluosTab:numeroHabitaciones", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCuartoServicio(),
				"avaluoForm:edicionAvaluosTab:cuartoServicio", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTerraza(),
				"avaluoForm:edicionAvaluosTab:terraza", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getZonaVerdePrivada(),
				"avaluoForm:edicionAvaluosTab:zonaVerdePrivada", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTotalGarajes(),
				"avaluoForm:edicionAvaluosTab:totalGarajes", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getUsoExclusivo(),
				"avaluoForm:edicionAvaluosTab:usoExclusivo", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getSencillo(),
				"avaluoForm:edicionAvaluosTab:sencillo", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getDeposito(),
				"avaluoForm:edicionAvaluosTab:deposito", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getLocal(),
				"avaluoForm:edicionAvaluosTab:local", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCubierto(),
				"avaluoForm:edicionAvaluosTab:cubierto", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPrivado(),
				"avaluoForm:edicionAvaluosTab:privado", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getDoble(),
				"avaluoForm:edicionAvaluosTab:doble", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBodega(),
				"avaluoForm:edicionAvaluosTab:bodega", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getDescubierto(),
				"avaluoForm:edicionAvaluosTab:descubierto", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBahiaComunal(),
				"avaluoForm:edicionAvaluosTab:bahiaComunal", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getServidumbre(),
				"avaluoForm:edicionAvaluosTab:servidumbre", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getOficina(),
				"avaluoForm:edicionAvaluosTab:oficina", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getVentilacion(),
				"avaluoForm:edicionAvaluosTab:ventilacion", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getIluminacion(),
				"avaluoForm:edicionAvaluosTab:iluminacion", false, 3, null);
		return validar;
	}

	public boolean validarSalubridad() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(salubridad.getRequiereCondicionesSalubridad(),
				"avaluoForm:edicionAvaluosTab:requiereCondicionesSalubridad", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getCondicionSalubridad(),
				"avaluoForm:edicionAvaluosTab:condicionSalubridad", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getAmbientalArborizacion(),
				"avaluoForm:edicionAvaluosTab:ambientalArborizacion", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getAmbientalParques(),
				"avaluoForm:edicionAvaluosTab:ambientalParques", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getAmbientalZonaVerde(),
				"avaluoForm:edicionAvaluosTab:ambientalZonaVerde", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getImpactoNegativoPorAire(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoPorAire", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getImpactoNegativoBasura(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoBasura", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getImpactoNegativoInseguridad(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoInseguridad", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getImpactoNegativoRuido(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoRuido", false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getAguasServidas(), "avaluoForm:edicionAvaluosTab:aguasServidas",
				false, 3, null);
		validar &= UtilValidadorJsf.validar(salubridad.getImpactoNegativoOtros(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoOtros", false, 100, null);
		validar &= UtilValidadorJsf.validar(salubridad.getAmbientalOtros(),
				"avaluoForm:edicionAvaluosTab:ambientalOtros", false, 100, null);
		return validar;
	}

	public boolean validarAcabados() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoPisos(),
				"avaluoForm:edicionAvaluosTab:estadoPisos", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadPiso(),
				"avaluoForm:edicionAvaluosTab:calidadPiso", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoMuros(),
				"avaluoForm:edicionAvaluosTab:estadoMuros", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadMuro(),
				"avaluoForm:edicionAvaluosTab:calidadMuro", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstructuraTechos(),
				"avaluoForm:edicionAvaluosTab:estructuraTechos", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadTecho(),
				"avaluoForm:edicionAvaluosTab:calidadTecho", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoMadera(),
				"avaluoForm:edicionAvaluosTab:estadoMadera", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadMadera(),
				"avaluoForm:edicionAvaluosTab:calidadMadera", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoMetal(),
				"avaluoForm:edicionAvaluosTab:estadoMetal", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCalidMetal(),
				"avaluoForm:edicionAvaluosTab:calidMetal", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoBanios(),
				"avaluoForm:edicionAvaluosTab:estadoBanios", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadBanio(),
				"avaluoForm:edicionAvaluosTab:calidadBanio", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoCocina(),
				"avaluoForm:edicionAvaluosTab:estadoCocina", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadCocina(),
				"avaluoForm:edicionAvaluosTab:calidadCocina", false, 3, null);
		return validar;
	}

	public boolean validarHorizontal() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPropiedadHorizontal(),
				"avaluoForm:edicionAvaluosTab:propiedadHorizontal", true, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPisos(),
				"avaluoForm:edicionAvaluosTab:pisos", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getConjuntoAgrupacionCerrada(),
				"avaluoForm:edicionAvaluosTab:conjuntoAgrupacionCerrada", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getNumeroEdificios(),
				"avaluoForm:edicionAvaluosTab:numeroEdificios", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getUbicacionInmueble(),
				"avaluoForm:edicionAvaluosTab:ubicacionInmueble", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getUnidadPorPiso(),
				"avaluoForm:edicionAvaluosTab:unidadPorPiso", false, 2, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTotalUnidades(),
				"avaluoForm:edicionAvaluosTab:totalUnidades", false, 8, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getNombreAdministrador(),
				"avaluoForm:edicionAvaluosTab:nombreAdministrador", false, 50, null);
		validar &= UtilValidadorJsf.validarCorreo(construccion.getConstruccion().getCorreoAdministrador(),
				"avaluoForm:edicionAvaluosTab:correoSolicitantee", false, 60, null);
		validar &= UtilValidadorJsf.validarCorreo(construccion.getConstruccion().getCorreoAdministrador2(),
				"avaluoForm:edicionAvaluosTab:correoAdministrador2", false, 60, null);
		validar &= UtilValidadorJsf.validarCorreo(construccion.getConstruccion().getCorreoAdministrador3(),
				"avaluoForm:edicionAvaluosTab:correoAdministrador3", false, 60, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTelefonoAdministrador(),
				"avaluoForm:edicionAvaluosTab:telefonoAdministrador", false, 10, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTelefonoAdministrador2(),
				"avaluoForm:edicionAvaluosTab:telefonoAdministrador2", false, 10, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTelefonoAdministrador3(),
				"avaluoForm:edicionAvaluosTab:telefonoAdministrador3", false, 10, null);
		return validar;
	}

	public boolean validarComunal() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPorteria(),
				"avaluoForm:edicionAvaluosTab:porteria", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPiscina(),
				"avaluoForm:edicionAvaluosTab:piscina", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getGarajeVisitante(),
				"avaluoForm:edicionAvaluosTab:garajeVisitante", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBombaEyectora(),
				"avaluoForm:edicionAvaluosTab:bombaEyectora", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getZonasVerdes(),
				"avaluoForm:edicionAvaluosTab:zonasVerdesConstruccion", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCitofono(),
				"avaluoForm:edicionAvaluosTab:citofono", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getTanqueAgua(),
				"avaluoForm:edicionAvaluosTab:tanqueAgua", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getJuegoNinos(),
				"avaluoForm:edicionAvaluosTab:juegoNinos", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getAireAcondicionado(),
				"avaluoForm:edicionAvaluosTab:aireAcondicionado", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getGimnasio(),
				"avaluoForm:edicionAvaluosTab:gimnasio", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getBicicletero(),
				"avaluoForm:edicionAvaluosTab:bicicletero", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getClubHouse(),
				"avaluoForm:edicionAvaluosTab:clubHouse", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getCanchaMultiple(),
				"avaluoForm:edicionAvaluosTab:canchaMultiple", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getSquash(),
				"avaluoForm:edicionAvaluosTab:squash", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getGolfito(),
				"avaluoForm:edicionAvaluosTab:golfito", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getSalonComunal(),
				"avaluoForm:edicionAvaluosTab:salonComunal", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPlanta(),
				"avaluoForm:edicionAvaluosTab:planta", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getShutBasuras(),
				"avaluoForm:edicionAvaluosTab:shutBasuras", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getPresion(),
				"avaluoForm:edicionAvaluosTab:presion", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getAscensor(),
				"avaluoForm:edicionAvaluosTab:ascensor", false, 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getNumeroAscensores(),
				"avaluoForm:edicionAvaluosTab:numeroAscensores", construccion.isAscensor(), 3, null);
		validar &= UtilValidadorJsf.validar(construccion.getConstruccion().getOtrosDotacion(),
				"avaluoForm:edicionAvaluosTab:otrosDotacion", false, 250, null);
		return validar;
	}

	public void obligatoriedadSalubridad(List<ArchivoPlano> obligatorios) {
		if (obligatorios == null || obligatorios.isEmpty())
			return;
		UtilValidadorJsf.validar(salubridad.getRequiereCondicionesSalubridad(),
				"avaluoForm:edicionAvaluosTab:requiereCondicionesSalubridad",
				AvaluoBean.esObligatorio(obligatorios, 256L), 3, null);
		UtilValidadorJsf.validar(salubridad.getCondicionSalubridad(),
				"avaluoForm:edicionAvaluosTab:condicionSalubridad", AvaluoBean.esObligatorio(obligatorios, 46L), 3,
				null);
		UtilValidadorJsf.validar(salubridad.getAmbientalArborizacion(),
				"avaluoForm:edicionAvaluosTab:ambientalArborizacion", AvaluoBean.esObligatorio(obligatorios, 66L), 3,
				null);
		UtilValidadorJsf.validar(salubridad.getAmbientalParques(), "avaluoForm:edicionAvaluosTab:ambientalParques",
				AvaluoBean.esObligatorio(obligatorios, 67L), 3, null);
		UtilValidadorJsf.validar(salubridad.getAmbientalZonaVerde(), "avaluoForm:edicionAvaluosTab:ambientalZonaVerde",
				AvaluoBean.esObligatorio(obligatorios, 68L), 3, null);
		UtilValidadorJsf.validar(salubridad.getImpactoNegativoPorAire(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoPorAire", AvaluoBean.esObligatorio(obligatorios, 0L), 3,
				null);
		UtilValidadorJsf.validar(salubridad.getImpactoNegativoBasura(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoBasura", AvaluoBean.esObligatorio(obligatorios, 0L), 3,
				null);
		UtilValidadorJsf.validar(salubridad.getImpactoNegativoInseguridad(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoInseguridad", AvaluoBean.esObligatorio(obligatorios, 0L),
				3, null);
		UtilValidadorJsf.validar(salubridad.getImpactoNegativoRuido(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoRuido", AvaluoBean.esObligatorio(obligatorios, 0L), 3,
				null);
		UtilValidadorJsf.validar(salubridad.getAguasServidas(), "avaluoForm:edicionAvaluosTab:aguasServidas",
				AvaluoBean.esObligatorio(obligatorios, 0L), 3, null);
		UtilValidadorJsf.validar(salubridad.getImpactoNegativoOtros(),
				"avaluoForm:edicionAvaluosTab:impactoNegativoOtros", AvaluoBean.esObligatorio(obligatorios, 0L), 100,
				null);
		UtilValidadorJsf.validar(salubridad.getAmbientalOtros(), "avaluoForm:edicionAvaluosTab:ambientalOtros",
				AvaluoBean.esObligatorio(obligatorios, 0L), 100, null);
	}

	public void obligatoriedad(List<ArchivoPlano> obligatorios) {
		if (obligatorios == null || obligatorios.isEmpty())
			return;
		UtilValidadorJsf.validar(construccion.getConstruccion().getPisos(),
				"avaluoForm:edicionAvaluosTab:pisosAdicional", AvaluoBean.esObligatorio(obligatorios, 147L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getSotanos(), "avaluoForm:edicionAvaluosTab:sotanos",
				AvaluoBean.esObligatorio(obligatorios, 152L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getVetustez(), "avaluoForm:edicionAvaluosTab:vetustez",
				AvaluoBean.esObligatorio(obligatorios, 182L), 4, 4, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPisoInmueble(),
				"avaluoForm:edicionAvaluosTab:pisoInmueble", AvaluoBean.esObligatorio(obligatorios, 257L), 4, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getUbicacionFuentesHidricas(),
				"avaluoForm:edicionAvaluosTab:ubicacionFuentesHidricas", AvaluoBean.esObligatorio(obligatorios, 264L),
				3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getUbicacionNivelVia(),
				"avaluoForm:edicionAvaluosTab:ubicacionNivelVia", AvaluoBean.esObligatorio(obligatorios, 265L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCodigoNombreConstructora(),
				"avaluoForm:edicionAvaluosTab:codigoNombreConstructora", AvaluoBean.esObligatorio(obligatorios, 279L),
				15, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getNombreConstructora(),
				"avaluoForm:edicionAvaluosTab:nombreConstructora",
				verNombreConstructora && AvaluoBean.esObligatorio(obligatorios, 279L), 100, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoConstruccion(),
				"avaluoForm:edicionAvaluosTab:estadoConstruccion", AvaluoBean.esObligatorio(obligatorios, 185L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getEstadoConstruccionNuevo(),
				"avaluoForm:edicionAvaluosTab:estadoTerminadaNueva", AvaluoBean.esObligatorio(obligatorios, 83L), 6,
				null);
		UtilValidadorJsf.validar(construccion.getEstadoConstruccionUsado(),
				"avaluoForm:edicionAvaluosTab:estadoTerminadoUsado", AvaluoBean.esObligatorio(obligatorios, 84L), 6,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getAvanceObra(),
				"avaluoForm:edicionAvaluosTab:avanceObra", AvaluoBean.esObligatorio(obligatorios, 189L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoRemodelacion(),
				"avaluoForm:edicionAvaluosTab:estadoRemodelacion", AvaluoBean.esObligatorio(obligatorios, 82L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoConservacion(),
				"avaluoForm:edicionAvaluosTab:estadoConservacion", AvaluoBean.esObligatorio(obligatorios, 26L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getParapetosCubierta(),
				"avaluoForm:edicionAvaluosTab:parapetosCubierta", AvaluoBean.esObligatorio(obligatorios, 239L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstructura(),
				"avaluoForm:edicionAvaluosTab:estructura", AvaluoBean.esObligatorio(obligatorios, 32L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstructuraReforzadaBUA(),
				"avaluoForm:edicionAvaluosTab:estructuraReforzadaBUA", AvaluoBean.esObligatorio(obligatorios, 226L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getMaterialConstruccionBUA(),
				"avaluoForm:edicionAvaluosTab:materialEstructura", AvaluoBean.esObligatorio(obligatorios, 231L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getDetalleMaterialSFBUA(),
				"avaluoForm:edicionAvaluosTab:detalleMaterialSFBUA", AvaluoBean.esObligatorio(obligatorios, 233L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getIrregularidadPlantaBUA(),
				"avaluoForm:edicionAvaluosTab:irregularidadPlantaBUA", AvaluoBean.esObligatorio(obligatorios, 225L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getIrregularidadAlturaBUA(),
				"avaluoForm:edicionAvaluosTab:irregularidadAlturaBUA", AvaluoBean.esObligatorio(obligatorios, 224L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getFachada(), "avaluoForm:edicionAvaluosTab:fachada",
				AvaluoBean.esObligatorio(obligatorios, 35L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getTipoFachada(),
				"avaluoForm:edicionAvaluosTab:tipoFachada", AvaluoBean.esObligatorio(obligatorios, 48L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCubierta(), "avaluoForm:edicionAvaluosTab:cubierta",
				AvaluoBean.esObligatorio(obligatorios, 12L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getGolpeteo(), "avaluoForm:edicionAvaluosTab:golpeteo",
				AvaluoBean.esObligatorio(obligatorios, 243L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getDanoPrevioBUA(),
				"avaluoForm:edicionAvaluosTab:danoPrevioBUA", AvaluoBean.esObligatorio(obligatorios, 228L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getReparadosBUA(),
				"avaluoForm:edicionAvaluosTab:reparadosBUA", AvaluoBean.esObligatorio(obligatorios, 223L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getSala(), "avaluoForm:edicionAvaluosTab:sala",
				AvaluoBean.esObligatorio(obligatorios, 149L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBanioSocial(),
				"avaluoForm:edicionAvaluosTab:banioSocial", AvaluoBean.esObligatorio(obligatorios, 127L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBanioPrivado(),
				"avaluoForm:edicionAvaluosTab:banioPrivado", AvaluoBean.esObligatorio(obligatorios, 125L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBanioServicio(),
				"avaluoForm:edicionAvaluosTab:banioServicio", AvaluoBean.esObligatorio(obligatorios, 126L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getJardin(), "avaluoForm:edicionAvaluosTab:jardin",
				AvaluoBean.esObligatorio(obligatorios, 141L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getComedor(), "avaluoForm:edicionAvaluosTab:comedor",
				AvaluoBean.esObligatorio(obligatorios, 130L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstarHabitacion(),
				"avaluoForm:edicionAvaluosTab:estarHabitacion", AvaluoBean.esObligatorio(obligatorios, 137L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCocina(), "avaluoForm:edicionAvaluosTab:cocina",
				AvaluoBean.esObligatorio(obligatorios, 129L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPatioInterior(),
				"avaluoForm:edicionAvaluosTab:patioInterior", AvaluoBean.esObligatorio(obligatorios, 146L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBalcon(), "avaluoForm:edicionAvaluosTab:balcon",
				AvaluoBean.esObligatorio(obligatorios, 124L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstudio(), "avaluoForm:edicionAvaluosTab:estudio",
				AvaluoBean.esObligatorio(obligatorios, 138L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getNumeroHabitaciones(),
				"avaluoForm:edicionAvaluosTab:numeroHabitaciones", AvaluoBean.esObligatorio(obligatorios, 139L), 2,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCuartoServicio(),
				"avaluoForm:edicionAvaluosTab:cuartoServicio", AvaluoBean.esObligatorio(obligatorios, 132L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getTerraza(), "avaluoForm:edicionAvaluosTab:terraza",
				AvaluoBean.esObligatorio(obligatorios, 153L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getZonaVerdePrivada(),
				"avaluoForm:edicionAvaluosTab:zonaVerdePrivada", AvaluoBean.esObligatorio(obligatorios, 183L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getTotalGarajes(),
				"avaluoForm:edicionAvaluosTab:totalGarajes", AvaluoBean.esObligatorio(obligatorios, 156L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getUsoExclusivo(),
				"avaluoForm:edicionAvaluosTab:usoExclusivo", AvaluoBean.esObligatorio(obligatorios, 159L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getSencillo(), "avaluoForm:edicionAvaluosTab:sencillo",
				AvaluoBean.esObligatorio(obligatorios, 150L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getDeposito(), "avaluoForm:edicionAvaluosTab:deposito",
				AvaluoBean.esObligatorio(obligatorios, 134L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getLocal(), "avaluoForm:edicionAvaluosTab:local",
				AvaluoBean.esObligatorio(obligatorios, 142L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCubierto(), "avaluoForm:edicionAvaluosTab:cubierto",
				AvaluoBean.esObligatorio(obligatorios, 133L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPrivado(), "avaluoForm:edicionAvaluosTab:privado",
				AvaluoBean.esObligatorio(obligatorios, 148L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getDoble(), "avaluoForm:edicionAvaluosTab:doble",
				AvaluoBean.esObligatorio(obligatorios, 136L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBodega(), "avaluoForm:edicionAvaluosTab:bodega",
				AvaluoBean.esObligatorio(obligatorios, 128L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getDescubierto(),
				"avaluoForm:edicionAvaluosTab:descubierto", AvaluoBean.esObligatorio(obligatorios, 135L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBahiaComunal(),
				"avaluoForm:edicionAvaluosTab:bahiaComunal", AvaluoBean.esObligatorio(obligatorios, 123L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getServidumbre(),
				"avaluoForm:edicionAvaluosTab:servidumbre", AvaluoBean.esObligatorio(obligatorios, 151L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getOficina(), "avaluoForm:edicionAvaluosTab:oficina",
				AvaluoBean.esObligatorio(obligatorios, 145L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getVentilacion(),
				"avaluoForm:edicionAvaluosTab:ventilacion", AvaluoBean.esObligatorio(obligatorios, 55L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getIluminacion(),
				"avaluoForm:edicionAvaluosTab:iluminacion", AvaluoBean.esObligatorio(obligatorios, 41L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoPisos(),
				"avaluoForm:edicionAvaluosTab:estadoPisos", AvaluoBean.esObligatorio(obligatorios, 30L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadPiso(),
				"avaluoForm:edicionAvaluosTab:calidadPiso", AvaluoBean.esObligatorio(obligatorios, 7L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoMuros(),
				"avaluoForm:edicionAvaluosTab:estadoMuros", AvaluoBean.esObligatorio(obligatorios, 29L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadMuro(),
				"avaluoForm:edicionAvaluosTab:calidadMuro", AvaluoBean.esObligatorio(obligatorios, 6L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstructuraTechos(),
				"avaluoForm:edicionAvaluosTab:estructuraTechos", AvaluoBean.esObligatorio(obligatorios, 33L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadTecho(),
				"avaluoForm:edicionAvaluosTab:calidadTecho", AvaluoBean.esObligatorio(obligatorios, 8L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoMadera(),
				"avaluoForm:edicionAvaluosTab:estadoMadera", AvaluoBean.esObligatorio(obligatorios, 27L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadMadera(),
				"avaluoForm:edicionAvaluosTab:calidadMadera", AvaluoBean.esObligatorio(obligatorios, 3L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoMetal(),
				"avaluoForm:edicionAvaluosTab:estadoMetal", AvaluoBean.esObligatorio(obligatorios, 28L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCalidMetal(),
				"avaluoForm:edicionAvaluosTab:calidMetal", AvaluoBean.esObligatorio(obligatorios, 4L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoBanios(),
				"avaluoForm:edicionAvaluosTab:estadoBanios", AvaluoBean.esObligatorio(obligatorios, 24L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadBanio(),
				"avaluoForm:edicionAvaluosTab:calidadBanio", AvaluoBean.esObligatorio(obligatorios, 2L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEstadoCocina(),
				"avaluoForm:edicionAvaluosTab:estadoCocina", AvaluoBean.esObligatorio(obligatorios, 25L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCalidadCocina(),
				"avaluoForm:edicionAvaluosTab:calidadCocina", AvaluoBean.esObligatorio(obligatorios, 5L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPropiedadHorizontal(),
				"avaluoForm:edicionAvaluosTab:propiedadHorizontal", AvaluoBean.esObligatorio(obligatorios, 45L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getConjuntoAgrupacionCerrada(),
				"avaluoForm:edicionAvaluosTab:conjuntoAgrupacionCerrada", AvaluoBean.esObligatorio(obligatorios, 11L),
				3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getNumeroEdificios(),
				"avaluoForm:edicionAvaluosTab:numeroEdificios", AvaluoBean.esObligatorio(obligatorios, 144L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getUbicacionInmueble(),
				"avaluoForm:edicionAvaluosTab:ubicacionInmueble", AvaluoBean.esObligatorio(obligatorios, 52L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getUnidadPorPiso(),
				"avaluoForm:edicionAvaluosTab:unidadPorPiso", AvaluoBean.esObligatorio(obligatorios, 158L), 2, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getTotalUnidades(),
				"avaluoForm:edicionAvaluosTab:totalUnidades", AvaluoBean.esObligatorio(obligatorios, 157L), 8, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPorteria(), "avaluoForm:edicionAvaluosTab:porteria",
				AvaluoBean.esObligatorio(obligatorios, 99L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPiscina(), "avaluoForm:edicionAvaluosTab:piscina",
				AvaluoBean.esObligatorio(obligatorios, 96L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getGarajeVisitante(),
				"avaluoForm:edicionAvaluosTab:garajeVisitante", AvaluoBean.esObligatorio(obligatorios, 88L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBombaEyectora(),
				"avaluoForm:edicionAvaluosTab:bombaEyectora", AvaluoBean.esObligatorio(obligatorios, 73L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getZonasVerdes(),
				"avaluoForm:edicionAvaluosTab:zonasVerdesConstruccion", AvaluoBean.esObligatorio(obligatorios, 111L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCitofono(), "avaluoForm:edicionAvaluosTab:citofono",
				AvaluoBean.esObligatorio(obligatorios, 76L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getTanqueAgua(),
				"avaluoForm:edicionAvaluosTab:tanqueAgua", AvaluoBean.esObligatorio(obligatorios, 106L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getJuegoNinos(),
				"avaluoForm:edicionAvaluosTab:juegoNinos", AvaluoBean.esObligatorio(obligatorios, 92L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getAireAcondicionado(),
				"avaluoForm:edicionAvaluosTab:aireAcondicionado", AvaluoBean.esObligatorio(obligatorios, 58L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getGimnasio(), "avaluoForm:edicionAvaluosTab:gimnasio",
				AvaluoBean.esObligatorio(obligatorios, 87L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getBicicletero(),
				"avaluoForm:edicionAvaluosTab:bicicletero", AvaluoBean.esObligatorio(obligatorios, 72L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getClubHouse(),
				"avaluoForm:edicionAvaluosTab:clubHouse", AvaluoBean.esObligatorio(obligatorios, 77L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCanchaMultiple(),
				"avaluoForm:edicionAvaluosTab:canchaMultiple", AvaluoBean.esObligatorio(obligatorios, 74L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getSquash(), "avaluoForm:edicionAvaluosTab:squash",
				AvaluoBean.esObligatorio(obligatorios, 105L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getGolfito(), "avaluoForm:edicionAvaluosTab:golfito",
				AvaluoBean.esObligatorio(obligatorios, 89L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getSalonComunal(),
				"avaluoForm:edicionAvaluosTab:salonComunal", AvaluoBean.esObligatorio(obligatorios, 102L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPlanta(), "avaluoForm:edicionAvaluosTab:planta",
				AvaluoBean.esObligatorio(obligatorios, 97L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getShutBasuras(),
				"avaluoForm:edicionAvaluosTab:shutBasuras", AvaluoBean.esObligatorio(obligatorios, 103L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getPresion(), "avaluoForm:edicionAvaluosTab:presion",
				AvaluoBean.esObligatorio(obligatorios, 100L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getAscensor(), "avaluoForm:edicionAvaluosTab:ascensor",
				AvaluoBean.esObligatorio(obligatorios, 70L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getNumeroAscensores(),
				"avaluoForm:edicionAvaluosTab:numeroAscensores", AvaluoBean.esObligatorio(obligatorios, 143L), 3, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getOtrosDotacion(),
				"avaluoForm:edicionAvaluosTab:otrosDotacion", AvaluoBean.esObligatorio(obligatorios, 215L), 250, null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getEnObra(),
				"avaluoForm:edicionAvaluosTab:estadoTerminadaNueva", AvaluoBean.esObligatorio(obligatorios, 81L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getSinTerminar(),
				"avaluoForm:edicionAvaluosTab:estadoTerminadoUsado", AvaluoBean.esObligatorio(obligatorios, 104L), 3,
				null);
		UtilValidadorJsf.validar(construccion.getConstruccion().getCodigoFinanciadoConstructor(),
				"avaluoForm:edicionAvaluosTab:codigoFinanciadoConstructor",
				AvaluoBean.esObligatorio(obligatorios, 280L), 3, null);
	}

	public void ocultar() {
		ocultarAuditoria = !ocultarAuditoria;
	}

	public boolean isEstadoConstruccionNueva() {
		return construccion != null && construccion.getConstruccion().getEstadoConstruccion() != null
				&& construccion.getConstruccion().getEstadoConstruccion().compareTo(1L) == 0;
	}

	public boolean isEstadoConstruccionUsada() {
		return construccion != null && construccion.getConstruccion().getEstadoConstruccion() != null
				&& construccion.getConstruccion().getEstadoConstruccion().compareTo(2L) == 0;
	}

	/*
	 * getters y setters
	 */

	public boolean isEstadoAprobado() {
		return AvaluoBean.getBean().isEstadoAprobado();
	}

	public InformacionConstruccionDto getConstruccion() {
		return construccion;
	}

	public void setConstruccion(InformacionConstruccionDto construccion) {
		this.construccion = construccion;
	}

	public CondicionesSalubridad getSalubridad() {
		return salubridad;
	}

	public void setSalubridad(CondicionesSalubridad salubridad) {
		this.salubridad = salubridad;
	}

	public boolean isOcultarAuditoria() {
		return ocultarAuditoria;
	}

	public void setOcultarAuditoria(boolean ocultarAuditoria) {
		this.ocultarAuditoria = ocultarAuditoria;
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	public boolean isNoEditarPisos() {
		return noEditarPisos;
	}

	public void setNoEditarPisos(boolean noEditarPisos) {
		this.noEditarPisos = noEditarPisos;
	}

	public boolean isVerNombreConstructora() {
		return verNombreConstructora;
	}

	public List<SelectItem> getMaterialEstructura() {
		return materialEstructura;
	}

	public void setMaterialEstructura(List<SelectItem> materialEstructura) {
		this.materialEstructura = materialEstructura;
	}

	public List<SelectItem> getDetalleMaterial() {
		return detalleMaterial;
	}

	public void setDetalleMaterial(List<SelectItem> detalleMaterial) {
		this.detalleMaterial = detalleMaterial;
	}

	public boolean isHabilitarProyectoConstructor() {
		return habilitarProyectoConstructor;
	}
	
	@Override
    public boolean isEditable() {
    	return super.isEditable() && !AvaluoBean.getBean().isEstadoAprobado();
    }

	public boolean isAgregarCorreo() {
		return agregarCorreo;
	}

	public void setAgregarCorreo(boolean agregarCorreo) {
		this.agregarCorreo = agregarCorreo;
	}

	public boolean isAgregarTelefono() {
		return agregarTelefono;
	}

	public void setAgregarTelefono(boolean agregarTelefono) {
		this.agregarTelefono = agregarTelefono;
	}

	public int getVerCorreo() {
		return verCorreo;
	}

	public void setVerCorreo(int verCorreo) {
		this.verCorreo = verCorreo;
	}

	public int getVerTelefono() {
		return verTelefono;
	}

	public void setVerTelefono(int verTelefono) {
		this.verTelefono = verTelefono;
	}

}