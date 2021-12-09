package com.segurosbolivar.avaluos.controlador.bean.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.asesoftware.util.cache.UtilCache;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupConfirmacionBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.service.IPerito;
import com.segurosbolivar.avaluos.modelo.service.ISeguridad;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulo;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulos;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Transaccion;

/**
 * Permite tener en memoria los valores de la sesion de usuario que esta
 * vigente, entre ellos los controles y permisos a los que este puede tener
 * acceso.
 * 
 * La información puede ser consultada por los controladores restantes del
 * sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
@ManagedBean(name = "sesion")
@SessionScoped
public class SesionBean extends PopupAbstractoBean implements Serializable{

    private static final long serialVersionUID = -8961316322093278919L;
    private UsuarioDto usuario;
	private MenuModel menu;
    private String usuarioLogin;
    private boolean verMenu;
    private Map<String, Map<String, Boolean>> permisos;
    private Map<String, Boolean> permisosEspecificos;
    private String fondo;
    private String nombreCiudad;
    private Map<String, String> imagenesFondo;

    @EJB
	private transient ISeguridad seguridadSvc;
    @EJB
	private transient IPerito peritoSvc;
	@EJB
	private ParametrizacionDao parametrizacionDao;
    
    private boolean estaEditandoAvaluo;
    private boolean puedeRealizarCargue;

    public static SesionBean getBean() {
	return UtilJsf.obtenerBackingBean("sesion");
    }

    @Override
    public String getPermiso() {
	return null;
    }

    @Override
    public String getPopupId() {
	return null;
    }

    @Override
    public void inicio() {
	permisosEspecificos = new HashMap<>();
	if(this.imagenesFondo == null || this.imagenesFondo.isEmpty())
		cargarImagenes();
    }

    public void cargar() {
	permisosEspecificos = new HashMap<>();
	usuarioLogin = (String) UtilJsf.obtenerParametroSesion("login");
	if (!UtilTexto.estaVacio(usuarioLogin))
	    comprobarSesion();
	this.fondo = this.imagenesFondo.get("BOGOTA");
	cambiaImagenFondo();
	
    }

    public void cargueMasivo() {
	estaEditandoAvaluo = false;
	UtilJsf.navegar(AvaluosCons.PAGINA_CARGUE);
    }

    public void informeAvaluo() {
	estaEditandoAvaluo = false;
	UtilJsf.navegar(AvaluosCons.PAGINA_INFORME_COMERCIAL);
    }
    
    private void cargarImagenes() {
		this.imagenesFondo = new HashMap<String, String>();
		this.imagenesFondo.put("BOGOTA", "fondoBogota.jpg");
		this.imagenesFondo.put("VALLE DEL CAUCA", "fondoCali.jpg");
		this.imagenesFondo.put("ANTIOQUIA", "fondoMedellin.jpg");
		this.imagenesFondo.put("QUINDIO", "fondoArmenia.jpg");
		this.imagenesFondo.put("ATLANTICO", "fondoBarranquilla.jpg");
		this.imagenesFondo.put("SANTANDER", "fondoBucaramanga.jpg");
		this.imagenesFondo.put("BOLIVAR", "fondoCartagena.jpg");
		this.imagenesFondo.put("TOLIMA", "fondoIbague.jpg");
		this.imagenesFondo.put("CALDAS", "fondoManizales.jpg");
		this.imagenesFondo.put("CORDOBA", "fondoMonteria.jpg");
		this.imagenesFondo.put("HUILA", "fondoNeiva.jpg");
		this.imagenesFondo.put("NARINO", "fondoPasto.jpg");
		this.imagenesFondo.put("RISARALDA", "fondoPereira.jpg");
		this.imagenesFondo.put("LA GUAJIRA", "fondoRioacha.jpg");
		this.imagenesFondo.put("SAN ANDRES Y PROVIDENCIA", "fondoSanAndres.jpg");
		this.imagenesFondo.put("MAGDALENA", "fondoSantaMarta.jpg");
		this.imagenesFondo.put("SUCRE", "fondoSincelejo.jpg");
		this.imagenesFondo.put("BOYACA", "fondoTunja.jpg");
		this.imagenesFondo.put("CESAR", "fondoValledupar.jpg");
		this.imagenesFondo.put("DEFECTO", "fondoBogota.jpg");
    }
    
    public void cambiaImagenFondo() {
    	try {
    		PeritosEmpresa perito = peritoSvc.consultarPeritoDocumento(Long.valueOf(getUsuario().getUsuario().getDocumento()));
    		if(perito != null) {
    			Long sucursalDavivienda = perito.getEmpresasAvaluo().getSucursalDavivienda();
    			String nombreDepartamento = ListaBean.getBean().descDominio("DEPARTAMENTOS", sucursalDavivienda);
        		String fondoCiudad = this.imagenesFondo.get(nombreDepartamento);
        		if (fondoCiudad != null && fondoCiudad.length() > 0) {
        			setFondo(fondoCiudad);
        		}
    		}
		} catch (Exception e) {
			setFondo(this.imagenesFondo.get("BOGOTA"));
		}
    }

    public void comprobarSesion() {
	try {
	    menu = new DefaultMenuModel();
	    verMenu = false;
//			//COMENTARIAR ESTA LINEA PARA HABILITAR LOGIN SIN FCA 
//			 Usuario usuarion=new Usuario(); 
//			 usuarion.setCodigo("1016027639");
//			 usuarion.setNombres("JASON RINCON");
//			 usuarion.setNombrePerfil("ADMINISTRADOR"); 
//			 usuario = new UsuarioDto(usuarion); 
	    // /*DESCOMENTARIAR ESTA LINEA PARA HABILITAR LOGIN SIN FCA
	    
	    usuario = seguridadSvc.consultarUsuario(usuarioLogin);
	   
	    cargaPermisosEspecificos();
	    // */
	    UtilJsf.crearSession(AvaluosCons.CREDENCIAL, usuario);
	    // /*DESCOMENTARIAR ESTA LINEA PARA HABILITAR LOGIN SIN FCA
	    generaMenu(usuario.getPerfil());
	    // */
	    verMenu = true;
	    estaEditandoAvaluo = false;
	    UtilJsf.navegar(AvaluosCons.PAGINA_INICIO);
	} 
	catch (NegocioException e) {
		if(e.getId() == 7) {
			log.info("Se intenta realizar reinicio de la pagina de inicio");
			UtilJsf.navegar(AvaluosCons.PAGINA_REINICIO);
		}else {
	    menu = new DefaultMenuModel();
	    cerrarSesion();
		}
	}
	catch (Exception e) {
	    procesarError(e);
	    menu = new DefaultMenuModel();
	    cerrarSesion();
	}
    }

    public String getUsuarioLogin() {
	return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
	this.usuarioLogin = usuarioLogin;
    }

    /**
     * Permite generar el arbol de menu permitido para un usuario autorizado
     * 
     * @param modulos
     */
    private void generaMenu(Modulos modulos) {
	if (permisos == null) {
	    permisos = new HashMap<>();
	}
	for (Modulo modulo : modulos.getModulo()) {
	    DefaultSubMenu submenu = new DefaultSubMenu(modulo.getLabel());
	    DefaultSubMenu submenuPadre = submenu;
	    procesarModulo(modulo, submenu, submenuPadre);
	    menu.addElement(submenuPadre);
	}
    }

    public void mostrarMenu() {
	verMenu = !verMenu;
    }

    public void cerrarSesion() {
	try {
		Parametrizacion parametroUrlCierreSesion = parametrizacionDao.getParametro(AvaluosCons.TIPO_PARAMETRO_WS_FCA, AvaluosCons.NOMBRE_PARAMETRO_URL_LOGOUT);
		
	    ocultar();
	    usuario = null;
	    limpiarCache();
	    String logout = parametroUrlCierreSesion.getValorparametro();
	    UtilJsf.navegarFueraAplicacion(logout);
	    UtilJsf.cerrarSession();
	} catch (Exception e) {
	    procesarError(e);
	}
    }
    
    
    private void limpiarCache() {
	    UtilCache.getInstance().agregarObjeto(UtilConstantes.CACHE_AVALUOS, UtilConstantes.CACHE_CIUDADES + UtilConstantes.SUFIJO_DOMINIOS, new ArrayList<>());
    }

    /**
     * Pocesa cada modulo al que tiene acceso un usuario recursivamente
     * 
     * @param modulo
     * @param menu
     * @param menuPadre
     */
    private void procesarModulo(Modulo modulo, DefaultSubMenu menu, DefaultSubMenu menuPadre) {

	if (modulo.getTransacciones() != null && !modulo.getTransacciones().isEmpty()) {
	    for (Transaccion transaccion : modulo.getTransacciones()) {
		cargaPermisos(transaccion);
		DefaultMenuItem menuItem = new DefaultMenuItem(transaccion.getLabel());
		generaAcciones(menuItem, transaccion);
		menuItem.setAjax(false);
		menuItem.setOncomplete("actualizarMenu();");
		menuItem.setUpdate("menuForm encabezadoForm contenidocentral");
		menu.addElement(menuItem);
	    }
	    if (modulo.getModulos() != null && !modulo.getModulos().isEmpty()) {
		for (Modulo modulohijo : modulo.getModulos()) {
		    DefaultSubMenu subMenu = new DefaultSubMenu(modulohijo.getLabel());
		    procesarModulo(modulohijo, subMenu, menu);
		}
	    }
	    if (!menuPadre.getLabel().equalsIgnoreCase(menu.getLabel())) {
		menuPadre.addElement(menu);
	    }
	}
    }

    private void generaAcciones(DefaultMenuItem item, Transaccion transaccion) {
	item.setId(transaccion.getIdTarea());
	if (!transaccion.getLabel().equalsIgnoreCase(AvaluosCons.LABEL_CONSULTA_USUARIOS)) {
	    item.setUrl(transaccion.getTransaccion());
	    item.setParam(transaccion.getIdTarea(), transaccion.getIdTarea());
	} else
		item.setCommand("#{impresor.ver}");
		item.setOnclick("actualizarMenu();");
		item.setAjax(false);
    }

    public void cambioMenu() {
	estaEditandoAvaluo = false;
	PopupConfirmacionBean.getBean().cancelar();
    }

    public boolean isEstaEditandoAvaluo() {
	return estaEditandoAvaluo;
    }

    public void setEstaEditandoAvaluo(boolean estaEditandoAvaluo) {
	this.estaEditandoAvaluo = estaEditandoAvaluo;
    }

    private void cargaPermisos(Transaccion transaccion) {
	Map<String, Boolean> permisosBasicos = new HashMap<>();
	String transaccionActual = transaccion.getTransaccion();
	if (!transaccionActual.contains(".faces")) {
	    return;
	}
	permisosBasicos.put("actualiza", transaccion.getActualiza().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
	permisosBasicos.put("adiciona", transaccion.getAdiciona().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
	permisosBasicos.put("consulta", transaccion.getConsulta().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
	permisosBasicos.put("ejecuta", transaccion.getEjecuta().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
	if(transaccion.getElimina() != null)
		permisosBasicos.put("elimina", transaccion.getElimina().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
	permisos.put(transaccion.getIdTarea(), permisosBasicos);
    }

    private void cargaPermisosEspecificos() throws NegocioException {
	if (getUsuario() == null || getUsuario().getUsuario() == null || UtilTexto.estaVacio(getUsuario().getUsuario().getCodigoPerfil()))
	    throw mgrExc.lanzarExcepcion(4, TipoErrorNegocio.FATAL);
	String codigoPerfil = getUsuario().getUsuario().getCodigoPerfil();
	switch (codigoPerfil) {
	case "PGBPERITO01":
	    permisosEspecificos.put(UtilConstantes.PERM_APROBADOR, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_APROBADOR_HIJO, Boolean.TRUE);
	    break;
	case "PGBPERITO02":
	    permisosEspecificos.put(UtilConstantes.PERM_APROBADOR_HIJO, Boolean.TRUE);
	    break;
	case "PGBCOORDINA01":
	    permisosEspecificos.put(UtilConstantes.PERM_APROBADOR_HIJO, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_COSTO_TRANSACCION, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_ALERTA_ASEGURABILIDAD, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_CONSULTOR, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_BLOQUEO_NUEVOS, Boolean.TRUE);
	    break;
	case "PGBADMINIST01":
	    permisosEspecificos.put(UtilConstantes.PERM_COSTO_TRANSACCION, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_ADMINISTRADOR, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_ALERTA_ASEGURABILIDAD, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_ADMINISTRA_COMPLEMENTOS, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_BLOQUEO_NUEVOS, Boolean.TRUE);
	    break;
	case "PGBCONSULTA01":
	    permisosEspecificos.put(UtilConstantes.PERM_ALERTA_ASEGURABILIDAD, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_CONSULTOR, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_BLOQUEO_NUEVOS, Boolean.TRUE);
	    break;
	case "PGBDESARROLLO":
	    permisosEspecificos.put(UtilConstantes.PERM_DESARROLLADOR, Boolean.TRUE);
	    break;
	case "PGBPROCEDATOS":
	    permisosEspecificos.put(UtilConstantes.PERM_PROCEDATOS, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_ALERTA_ASEGURABILIDAD, Boolean.TRUE);
	    permisosEspecificos.put(UtilConstantes.PERM_BLOQUEO_NUEVOS, Boolean.TRUE);
	    break;
	default:
	    permisosEspecificos.put(UtilConstantes.PERM_APROBADOR_HIJO, Boolean.FALSE);
	}

    }

    public boolean comprobarOpcion(String permiso) {
	if (permiso == null)
	    return true;
	if (usuario == null || usuario.getPerfil() == null || usuario.getPerfil().getTransaccion() == null)
	    return false;
	for (Transaccion transaccion : usuario.getPerfil().getTransaccion()) {
	    if (transaccion.getIdTarea().equals(permiso))
		return true;
	}
	return false;
    }

    public String getObtenerNombre() {
	return usuario != null && usuario.getUsuario() != null && usuario.getUsuario().getNombres() != null ? usuario.getUsuario().getNombres().toUpperCase() : "";
    }

    /*
     * getters y setters
     */

    @Override
    public UsuarioDto getUsuario() {
	return usuario;
    }

    public MenuModel getMenu() {
	return menu;
    }

    public void setMenu(MenuModel menu) {
	this.menu = menu;
    }

    public boolean isVerMenu() {
	return verMenu;
    }

    public void setVerMenu(boolean verMenu) {
	this.verMenu = verMenu;
    }

    public Map<String, Boolean> getPermisosEspecificos() {
	return permisosEspecificos;
    }

    public void setPermisosEspecificos(Map<String, Boolean> permisosEspecificos) {
	this.permisosEspecificos = permisosEspecificos;
    }

    public boolean isPuedeRealizarCargue() {
	return puedeRealizarCargue;
    }

	public String getFondo() {
		return fondo;
	}

	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	
}