package com.segurosbolivar.avaluos.modelo.service.impl;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.cache.UtilCache;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.CalendarioDao;
import com.segurosbolivar.avaluos.modelo.data.CiudadDao;
import com.segurosbolivar.avaluos.modelo.data.DepartamentoDao;
import com.segurosbolivar.avaluos.modelo.data.DominioDao;
import com.segurosbolivar.avaluos.modelo.data.HorasCorteDao;
import com.segurosbolivar.avaluos.modelo.data.IndiceAcumuladoDao;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Calendario;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.entity.HorasCorteArchivo;
import com.segurosbolivar.avaluos.modelo.entity.IndiceAcumulado;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.jobs.Procedatos.JobProcedatos;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.ISeguridad;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;

/**
 * Implementaci�n del servicio de parametrizaci�n
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:45 a.m.s
 */
@Stateless
public class ParametrizacionImpl implements IParametrizacion {

	private static final long serialVersionUID = -3487881018072877320L;

	@EJB
	private ManejadorErroresNegocio mgrExc;

	@EJB
	private JobProcedatos procedatosSvc;

	@EJB
	private DominioDao dominioDao;
	/**
	 * Implementacion del manejador para la tabla de horas corte de asobancaria.
	 */
	@EJB
	private HorasCorteDao horasCorteDao;
	/**
	 * Implementacion del manejador para la tabla de departamentos
	 */
	@EJB
	private DepartamentoDao departamentoDao;
	/**
	 * Implementacion del manejador para la tabla de ciudades
	 */
	@EJB
	private CiudadDao ciudadDao;
	/**
	 * Implementacion del manejador para la tabla de calendario
	 */
	@EJB
	private CalendarioDao calendarioDao;
	/**
	 * Implementacion del manejador para la tabla de indices
	 */
	@EJB
	private IndiceAcumuladoDao indiceDao;
	
	
	@EJB
	private ParametrizacionDao parametrizacionDao;
	
	@EJB
	private ISeguridad seguridadSvc;

	@Override
	public int cargarAnioCalendario() {
		return 0;
	}
	
	

	@Override
	public List<Calendario> consultarFechasCalendario(Calendario calendario, int first, int pageSize, String campoOrden,
			SentidoOrientacion sentido) {
		return calendarioDao.consultar(calendario, first, pageSize, campoOrden, sentido);
	}

	@Override
	public List<Long> consultarAniosCalendario() throws NegocioException {
		return calendarioDao.consultarAnios();
	}

	@Override
	public List<String> consultarDominios(Dominios dominio, int inicio, int tamanioPagina, String campoOrden,
			SentidoOrientacion sentido) {
		return dominioDao.consultarDominios(dominio, inicio, tamanioPagina, campoOrden, sentido);
	}

	@Override
	public int consultarIndices() {
		return 0;
	}

	@Override
	public List<HorasCorteArchivo> consultarHoras(HorasCorteArchivo consultar, int inicio, int tamanioPagina,
			String campoOrden, SentidoOrientacion sentido) {
		return horasCorteDao.consultar(consultar, inicio, tamanioPagina, campoOrden, sentido);
	}

	@Override
	public List<IndiceAcumulado> consultarIndices(IndiceAcumulado consultar, int inicio, int tamanioPagina,
			String campoOrden, SentidoOrientacion sentido) {
		return indiceDao.consultar(consultar, inicio, tamanioPagina, campoOrden, sentido);
	}

	@Override
	public List<Dominios> consultarValoresDominio(String dominio, int inicio, int tamanioPagina, String campoOrden,
			SentidoOrientacion sentido) {
		return dominioDao.consultarValoresDominios(dominio, inicio, tamanioPagina, campoOrden, sentido);
	}

	@Override
	public List<Dominios> consultarValoresDominioFiltro(Dominios filtro, int inicio, int tamanioPagina, String campoOrden,
			SentidoOrientacion sentido) {
		return dominioDao.consultarValoresDominiosFiltro(filtro, inicio, tamanioPagina, campoOrden, sentido);
	}
	
	@Override
	public List<Ciudad> consultarCiudades(Ciudad ciudad, int first, int pageSize, String campoOrden,
			SentidoOrientacion sentido) {
		return ciudadDao.consultar(ciudad, first, pageSize, campoOrden, sentido);
	}

	@Override
	public List<Ciudad> consultarCiudades(Ciudad ciudad, int first, int pageSize) {
		return ciudadDao.consultar(ciudad, first, pageSize);
	}

	@Override
	public List<Departamento> consultarDepartamentos(Departamento consultar, int first, int pageSize, String campoOrden,
			SentidoOrientacion sentido) {
		if (UtilTexto.estaVacio(campoOrden)) {
			campoOrden = "departamento";
			sentido = SentidoOrientacion.ASCENDENTE;
		}
		return departamentoDao.consultar(consultar, first, pageSize, campoOrden, sentido);
	}

	@Override
	public void guardarFechasCalendario(List<Date> fechasNuevas, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (fechasNuevas == null || fechasNuevas.isEmpty())
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		if (fechasNuevas.size() > 10)
			throw mgrExc.lanzarExcepcion(104, TipoErrorNegocio.INFO);
		for (Date fecha : fechasNuevas) {
			Calendario calendario = new Calendario();
			calendario.setFechaNoHabil(fecha);
			List<Calendario> repetidos = calendarioDao.consultar(calendario, 0, Integer.MAX_VALUE, null, null);
			if (repetidos != null && !repetidos.isEmpty())
				throw mgrExc.lanzarExcepcion(105, TipoErrorNegocio.ERROR, null,
						new String[] { UtilFecha.fechaAFormatoddMMyyyy(fecha) });
			calendario.setFechaCreacion(new Date());
			calendario.setFechaTransaccion(new Date());
			calendario.setAnio(UtilFecha.obtenerAnio(fecha).longValue());
			calendario.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			calendario.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
			calendarioDao.crear(calendario);
		}
	}

	@Override
	public void sincronizarCiudades(UsuarioDto usuario) throws NegocioException {
		try {
			if (usuario == null || usuario.getUsuario() == null)
				throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
			departamentoDao.sincronizacionDane(usuario.getUsuario().getCodigo());
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(91, TipoErrorNegocio.ALERTA, e.getMessage(), null);
		}
	}

	@Override
	public void guardarValorDominio(Dominios valor, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (valor == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (valor.getIdCgRefCodes() == null || dominioDao.buscar(valor.getIdCgRefCodes()) == null) {
			valor.setRvCreateBy(usuario.getUsuario().getCodigo());
			dominioDao.crear(valor);
		} else
			dominioDao.actualizar(valor);
	}

	@Override
	public void guardarHora(HorasCorteArchivo hora, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (hora == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		hora.setFechaTransaccion(new Date());
		hora.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (hora.getIdHorasCorteArchivo() == null || horasCorteDao.buscar(hora.getIdHorasCorteArchivo()) == null) {
			hora.setFechaCreacion(new Date());
			hora.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			horasCorteDao.crear(hora);
		} else
			horasCorteDao.actualizar(hora);
	}

	@Override
	public void iniciarJobProcedatos() throws NegocioException {
		procedatosSvc.inicializarJob();
	}

	@Override
	public void guardarIndice(IndiceAcumulado indice, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (indice == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		if (indice.getIdCiudad() == null || indice.getIdCiudad() != 2695L)
			indice.setEstrato(80L);
		indice.setFechaTransaccion(new Date());
		indice.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		validarRepeticion(indice);
		if (indice.getIdIndiceAcumulado() == null || indiceDao.buscar(indice.getIdIndiceAcumulado()) == null) {
			indice.setFechaCreacion(new Date());
			indice.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			indiceDao.crear(indice);
		} else
			indiceDao.actualizar(indice);
	}

	/**
	 * Permite validar si se repite un indice acumulado con otro, por departamento,
	 * ciudad, año y estrato en caso de bogota.
	 * 
	 * @param indice
	 *            a verificar si esta repetido.
	 * @throws NegocioException
	 *             en caso de que se repita.
	 */
	private void validarRepeticion(IndiceAcumulado indice) throws NegocioException {
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		List<IndiceAcumulado> repetidos = indiceDao.consultar(indice, 0, Integer.MAX_VALUE, null, null);
		if (repetidos == null || repetidos.isEmpty())
			return;
		if (indice.getIdIndiceAcumulado() == null)
			throw mgrExc.lanzarExcepcion(88, TipoErrorNegocio.ERROR);
		for (IndiceAcumulado repetido : repetidos) {
			if (repetido.getIdIndiceAcumulado().compareTo(indice.getIdIndiceAcumulado()) != 0)
				throw mgrExc.lanzarExcepcion(88, TipoErrorNegocio.ERROR);
		}
	}

	@Override
	public void guardarCiudad(Ciudad ciudad, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (ciudad == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		if (ciudad.getIdDepartamento() == null)
			throw mgrExc.lanzarExcepcion(87, TipoErrorNegocio.ERROR);
		ciudad.setFechaTransaccion(new Date());
		ciudad.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (ciudad.getIdCiudad() == null || ciudadDao.buscar(ciudad.getIdCiudad()) == null) {
			ciudad.setFechaCreacion(new Date());
			ciudad.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			ciudadDao.crear(ciudad);
		} else
			ciudadDao.actualizar(ciudad);
	}

	@Override
	public void guardarDepartamento(Departamento departamento, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (departamento == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		departamento.setFechaTransaccion(new Date());
		departamento.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (departamento.getIdDepartamento() == null
				|| departamentoDao.buscar(departamento.getIdDepartamento()) == null) {
			departamento.setFechaCreacion(new Date());
			departamento.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			departamentoDao.crear(departamento);
		} else
			departamentoDao.actualizar(departamento);
	}

	@Override
	public void eliminarFechaCalendario(Calendario eliminar) throws NegocioException {
		if (eliminar == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		calendarioDao.eliminar(eliminar);
	}

	@Override
	public void eliminarHora(HorasCorteArchivo eliminar) throws NegocioException {
		if (eliminar == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		horasCorteDao.eliminar(eliminar);
	}

	@Override
	public void eliminarIndice(IndiceAcumulado eliminar) throws NegocioException {
		if (eliminar == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		indiceDao.eliminar(eliminar);
	}

	@Override
	public void eliminarDepartamento(Departamento eliminar) throws NegocioException {
		if (eliminar == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		if (eliminar.getCiudadesDepto() != null && !eliminar.getCiudadesDepto().isEmpty())
			throw mgrExc.lanzarExcepcion(89, TipoErrorNegocio.ERROR);
		departamentoDao.eliminar(eliminar);
	}

	@Override
	public void eliminarCiudad(Ciudad eliminar) throws NegocioException {
		if (eliminar == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		ciudadDao.eliminar(eliminar);
	}

	@Override
	public void eliminarValorDominio(Dominios eliminar) throws NegocioException {
		if (eliminar == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		dominioDao.eliminar(eliminar);
	}

	@Override
	public int eliminarIndice() {
		return 0;
	}

	@Override
	public int cantidadRegistros(Departamento departamento) {
		return departamentoDao.cantidadRegistros(departamento);
	}

	@Override
	public int cantidadRegistros(Ciudad ciudadFiltro) {
		return ciudadDao.cantidadRegistros(ciudadFiltro);
	}

	@Override
	public int cantidadRegistros(Calendario calendario) {
		return calendarioDao.cantidadRegistros(calendario);
	}

	@Override
	public int cantidadRegistros(HorasCorteArchivo consultar) {
		return horasCorteDao.cantidadRegistros(consultar);
	}

	@Override
	public int cantidadRegistros(IndiceAcumulado consultar) {
		return indiceDao.cantidadRegistros(consultar);
	}

	@Override
	public int cantidadRegistros(Dominios consultar) {
		return dominioDao.cantidadRegistros(consultar);
	}

	@Override
	public int cantidadRegistrosValores(String consultar) {
		return dominioDao.cantidadRegistrosValores(consultar);
	}
	
	@Override
	public int cantidadRegistrosValoresFiltro(String consultar, Dominios filtro) {
		return dominioDao.cantidadRegistrosValoresFiltro(consultar, filtro);
	}

	@Override
	public void agregarElementoCache(String llave, Object objeto) throws NegocioException {
		try {
			UtilCache.getInstance().agregarObjeto(UtilConstantes.CACHE_AVALUOS, llave, objeto);
		} catch (Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, "Error al agregar un elemento en memoria. ",
					e.getMessage());
		}
	}
	
	@Override
	public void agregarElementoCache(String region, String llave, Object objeto) throws NegocioException {
		try {
			UtilCache.getInstance().agregarObjeto(region, llave, objeto);
		} catch (Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, "Error al agregar un elemento en memoria. ",
					e.getMessage());
		}
	}
	
	

	@Override
	@SuppressWarnings("unchecked")
	public Dominios consultarDominioPorAbreviacion(String llave, String abreviacion) throws NegocioException {
		if (UtilTexto.estaVacio(abreviacion) || UtilTexto.estaVacio(llave))
			return null;
		List<Dominios> dominios = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS,
				llave + UtilConstantes.SUFIJO_DOMINIOS);
		if (dominios == null || dominios.isEmpty())
			return null;
		for (Dominios comparar : dominios) {
			if (comparar.getRvAbbreviation() != null && comparar.getRvAbbreviation().equalsIgnoreCase(abreviacion))
				return comparar;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dominios> consultarDominios(String llave) throws NegocioException {
		if (UtilTexto.estaVacio(llave))
			return Collections.emptyList();
		return UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS,
				llave + UtilConstantes.SUFIJO_DOMINIOS);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Dominios consultarDominio(String llave, String lowValue) throws NegocioException {
		if (UtilTexto.estaVacio(lowValue) || UtilTexto.estaVacio(llave))
			return null;
		List<Dominios> dominios = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS,
				llave + UtilConstantes.SUFIJO_DOMINIOS);
		if (dominios == null || dominios.isEmpty())
			return null;
		for (Dominios comparar : dominios) {
			if (comparar.getRvLowValue() != null && comparar.getRvLowValue().equalsIgnoreCase(lowValue))
				return comparar;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String consultarDescripcionDominio(String llave, String lowValue) throws NegocioException {
		if (UtilTexto.estaVacio(lowValue) || UtilTexto.estaVacio(llave))
			return null;
		//System.out.println("llave + sufijo "+llave + UtilConstantes.SUFIJO_DOMINIOS);
		List<Dominios> dominios = (List<Dominios>) obtenerListadoCache(llave + UtilConstantes.SUFIJO_DOMINIOS);
		//System.out.println("Retorna el dominio correctamente");
		if (dominios == null || dominios.isEmpty())
			return null;
		for (Dominios comparar : dominios) {
			if (comparar.getRvLowValue() != null && comparar.getRvLowValue().equalsIgnoreCase(lowValue))
				return comparar.getRvMeaning();
		}
		return null;
	}
	
	@Override
	public String consultarDescripcionDominio(List<Dominios> dominios,String lowValue) throws NegocioException {
	    if (UtilTexto.estaVacio(lowValue)  || dominios==null || dominios.isEmpty())
		return null;
	    for (Dominios comparar : dominios) {
		if (comparar.getRvLowValue() != null && comparar.getRvLowValue().equalsIgnoreCase(lowValue))
		    return comparar.getRvMeaning();
	    }
	    return null;
	}

	@Override
	public Object obtenerElementoCache(String llave) throws NegocioException {
		try {
			return UtilCache.getInstance().obtenerObjeto(UtilConstantes.CACHE_AVALUOS, llave);
		} catch (Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, "Error al obtener un objeto de memoria. ",
					e.getMessage());
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<?> obtenerListadoCache(String llave) throws NegocioException {
		try {
			if (UtilTexto.estaVacio(llave))
				return Collections.emptyList();
			// Intentamos obtener el listado de memoria
			List respuesta = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS, llave);
			if (respuesta != null && !respuesta.isEmpty())
				return respuesta;
			// si el listado no se encuentra en memoria y es al paracer un
			// listado especial como ciudades o departamentos lo intentamos
			// cargar en su forma base, esto debido a que es posible que se
			// guarde un listado de select items, pero este debe ser creado por
			// la capa de presentacion directamente.
			if (llave.startsWith(UtilConstantes.CACHE_DEPARTAMENTOS))
				return obtenerDepartamentosCache();
			if (llave.startsWith(UtilConstantes.CACHE_CIUDADES))
				return obtenerCiudadesCache();
			if(llave.startsWith(UtilConstantes.CACHE_USUARIOS))
				return obtenerUsuariosCache();
			// si el dominio no es un dominio especial, intentamos buscar el
			// listado base, para devolverlo y que en la capa de presentacion
			// sea transformado.
			List<Dominios> dominios = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS,
					llave + (llave.endsWith(UtilConstantes.SUFIJO_DOMINIOS) ? "" : UtilConstantes.SUFIJO_DOMINIOS));
			if (dominios != null && !dominios.isEmpty())
				return dominios;
			// si no estan en cache lo consultamos directamente de base de datos
			dominios = consultarValoresDominio(UtilTexto.removerSufijo(llave, UtilConstantes.SUFIJO_DOMINIOS), 0,
					Integer.MAX_VALUE, "rvMeaning", SentidoOrientacion.ASCENDENTE);
			if (dominios == null || dominios.isEmpty())
				return Collections.emptyList();
			// y agregamos la lista base a cache y la retornamos para que sea
			// tranformada.
			agregarElementoCache(llave + UtilConstantes.SUFIJO_DOMINIOS, dominios);
			return dominios;
		} catch (Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, "Error al obtener un listado de memoria. " +llave,
					e.getMessage());
		}
	}

	/**
	 * Este metodo devuelve el listado completo de departamentos, si no estan
	 * cargadas en la cache, las consulta de base de datos y los intenta agregar.
	 * 
	 * @return listado de departamentos.
	 * @throws NegocioException
	 *             encaso de problemas con la consulta.
	 */
	@SuppressWarnings("unchecked")
	private List<Departamento> obtenerDepartamentosCache() throws NegocioException {
		List<Departamento> departamentos = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS,
				UtilConstantes.CACHE_DEPARTAMENTOS + UtilConstantes.SUFIJO_DOMINIOS);
		if (departamentos != null && !departamentos.isEmpty())
			return departamentos;
		departamentos = consultarDepartamentos(new Departamento(), 0, Integer.MAX_VALUE, null, null);
		if (departamentos == null || departamentos.isEmpty()) {
			return Collections.emptyList();
		}
		agregarElementoCache(UtilConstantes.CACHE_DEPARTAMENTOS + UtilConstantes.SUFIJO_DOMINIOS, departamentos);
		return departamentos;
	}
	
	private List<Usuario> obtenerUsuariosCache()throws NegocioException, RemoteException{
		UtilCache.getInstance().agregarRegion(UtilConstantes.CACHE_REGION_USUARIOS, String.class, List.class,1000,86400);
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_REGION_USUARIOS, UtilConstantes.CACHE_USUARIOS + UtilConstantes.SUFIJO_SERVICIOS_WEB);
		if(usuarios != null && !usuarios.isEmpty())
			return usuarios;
		usuarios = seguridadSvc.consultarUsuariosDetallado();
		agregarElementoCache(UtilConstantes.CACHE_REGION_USUARIOS,UtilConstantes.CACHE_USUARIOS + UtilConstantes.SUFIJO_SERVICIOS_WEB, usuarios);
		return usuarios;
	}

	/**
	 * Este metodo devuelve el listado completo de ciudades, si no estan cargadas en
	 * la cache, las consulta de base de datos y las intenta agregar.
	 * 
	 * @return listado de ciudades.
	 * @throws NegocioException
	 *             encaso de problemas con la consulta.
	 */
	@SuppressWarnings("unchecked")
	private List<Ciudad> obtenerCiudadesCache() throws NegocioException {
		List<Ciudad> ciudades = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS,
				UtilConstantes.CACHE_CIUDADES + UtilConstantes.SUFIJO_DOMINIOS);
		if (ciudades != null && !ciudades.isEmpty())
			return ciudades;
		ciudades = consultarCiudades(new Ciudad(), 0, Integer.MAX_VALUE);
		if (ciudades == null || ciudades.isEmpty()) {
			return Collections.emptyList();
		}
		agregarElementoCache(UtilConstantes.CACHE_CIUDADES + UtilConstantes.SUFIJO_DOMINIOS, ciudades);
		return ciudades;
	}

	@Override
	public Ciudad consultarCiudad(Long id) throws NegocioException {
		if (id == null || id.compareTo(0L) == 0)
			return null;
		List<Ciudad> ciudades = obtenerCiudadesCache();
		if (ciudades == null || ciudades.isEmpty())
			return null;
		for (Ciudad ciudad : ciudades) {
			if (ciudad.getIdCiudad().compareTo(id) == 0)
				return ciudad;
		}
		return null;
	}

	@Override
	public Departamento consultarDepartamento(Long id) throws NegocioException {
		if (id == null || id.compareTo(0L) == 0)
			return null;
		List<Departamento> departamentos = obtenerDepartamentosCache();
		if (departamentos == null || departamentos.isEmpty())
			return null;
		for (Departamento ciudad : departamentos) {
			if (ciudad.getIdDepartamento().compareTo(id) == 0)
				return ciudad;
		}
		return null;
	}

	@Override
	public void cargarAnio(Integer anioCargar, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null || UtilTexto.estaVacio(usuario.getUsuario().getCodigo())) {
			throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
		}
		if (anioCargar == null) {
			throw mgrExc.lanzarExcepcion(12L, TipoErrorNegocio.ERROR);
		}
		calendarioDao.cargarAnio(anioCargar, usuario.getUsuario().getCodigo());
	}

	@Override
	public List<Parametrizacion> consultarParametros(Parametrizacion consultar, int first, int pageSize,
			String campoOrden, SentidoOrientacion sentido) {
		return parametrizacionDao.consultar(consultar, first, pageSize, campoOrden, sentido);
	}

	@Override
	public int cantidadRegistros(Parametrizacion consultar) {
		return parametrizacionDao.cantidadRegistros(consultar);
	}

	@Override
	public void guardarParametro(Parametrizacion parametro, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (parametro == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		parametro.setFechamodificacion(new Date());
		parametro.setUsuariomodificacion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (parametro.getIdparametrizacion() == null || parametrizacionDao.buscar(parametro.getIdparametrizacion()) == null) {
			parametro.setFechacreacion(new Date());
			parametro.setUsuariocreacion(usuario.getUsuario().getCodigo());
			parametrizacionDao.crear(parametro);
		} else
			parametrizacionDao.actualizar(parametro);
		
	}
	@Override
	public String getDominioByLowValue(String dominio,String valorDominio) {
		String rvMeaning;
		if(valorDominio == null)
			return null;
		Dominios dominios = dominioDao.getDominioByLowValue(dominio,valorDominio);
		rvMeaning = dominios != null ? dominios.getRvMeaning() : null;
		return rvMeaning;
	}
	
	public Long getDominioByMeaning(String dominio,String valorDominio) {
		Long rvLowValue;
		if(valorDominio == null)
			return null;
		Dominios dominios = dominioDao.getDominioByMeaning(dominio,valorDominio);
		rvLowValue = dominios != null ? Long.getLong(dominios.getRvLowValue()) : null;
		return rvLowValue;
	}

}