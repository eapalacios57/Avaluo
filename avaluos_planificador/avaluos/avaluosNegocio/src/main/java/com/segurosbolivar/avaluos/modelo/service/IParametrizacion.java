package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Calendario;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.entity.HorasCorteArchivo;
import com.segurosbolivar.avaluos.modelo.entity.IndiceAcumulado;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;

/**
 * Expone funcionalidades para la consulta y gesti�n de las tablas parametricas
 * del sistema: calendario, hora corte, dominio, departamento, ciudad.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IParametrizacion extends Serializable {

	int cargarAnioCalendario();

	void agregarElementoCache(String llave, Object objeto) throws NegocioException;

	Object obtenerElementoCache(String llave) throws NegocioException;

	List<?> obtenerListadoCache(String llave) throws NegocioException;

	List<Ciudad> consultarCiudades(Ciudad ciudad, int first, int pageSize, String campoOrden,
			SentidoOrientacion sentido);

	List<Departamento> consultarDepartamentos(Departamento consultar, int first, int pageSize, String campoOrden,
			SentidoOrientacion sentido);

	List<String> consultarDominios(Dominios dominio, int inicio, int tamanioPagina, String campoOrden,
			SentidoOrientacion sentido);
	
	List<Parametrizacion> consultarParametros(Parametrizacion consultar, int first, int pageSize, String campoOrden,SentidoOrientacion sentido);

	List<Calendario> consultarFechasCalendario(Calendario calendario, int first, int pageSize, String campoOrden,
			SentidoOrientacion sentido);

	List<Long> consultarAniosCalendario() throws NegocioException;

	/**
	 * Permite consulta las horas de corte del sistema, si se es necesario se aplica
	 * un filtro teniendo en cuenta los campos para la Hora.
	 * 
	 * @param consultar
	 *            contiene los parametros de filtro para la consulta de las horas de
	 *            corte.
	 * @param tamanioPagina
	 * @param inicio
	 * @return listado de horas de corte que aplican al filtro.
	 */
	List<HorasCorteArchivo> consultarHoras(HorasCorteArchivo consultar, int inicio, int tamanioPagina,
			String campoOrden, SentidoOrientacion sentido);

	List<IndiceAcumulado> consultarIndices(IndiceAcumulado consultar, int inicio, int tamanioPagina, String campoOrden,
			SentidoOrientacion sentido);

	int consultarIndices();

	void sincronizarCiudades(UsuarioDto usuario) throws NegocioException;

	/**
	 * Pemite consultar los valores dependientes de un dominio
	 * 
	 * @param dominio
	 *            al que seran consultados sus valores respectivos.
	 * @param inicio
	 *            registro desde que comienza la consulta
	 * @param tamanioPagina
	 *            cantidad de registros desde el registro desde empezamos la
	 *            consulta que retornaremos.
	 * @return Lisado de valores del dominio.
	 */
	List<Dominios> consultarValoresDominio(String dominio, int inicio, int tamanioPagina, String campoOrden,
			SentidoOrientacion sentido);
	
	/**
	 * Pemite consultar los valores dependientes de un dominio 
	 * Se adiciona criterio de selección por filtro
	 * 
	 * @param dominio
	 *            al que seran consultados sus valores respectivos.
	 * @param filtro
	 *            por el que se quiere consultar los valores.           
	 * @param inicio
	 *            registro desde que comienza la consulta
	 * @param tamanioPagina
	 *            cantidad de registros desde el registro desde empezamos la
	 *            consulta que retornaremos.
	 * @return Lisado de valores del dominio.
	 */
	List<Dominios> consultarValoresDominioFiltro(Dominios filtro, int inicio, int tamanioPagina, String campoOrden,
			SentidoOrientacion sentido);

	void guardarCiudad(Ciudad ciudad, UsuarioDto usuario) throws NegocioException;
	
	void guardarParametro(Parametrizacion parametro, UsuarioDto usuario)throws NegocioException;

	void guardarDepartamento(Departamento departamento, UsuarioDto usuario) throws NegocioException;

	void guardarFechasCalendario(List<Date> fechasNuevas, UsuarioDto usuario) throws NegocioException;

	/**
	 * Permite guardar una hora de corte para el proceso de ASOBANCARIA.
	 * 
	 * @param hora
	 *            hora de corte a registrar.
	 * @throws NegocioException
	 */
	void guardarHora(HorasCorteArchivo hora, UsuarioDto usuario) throws NegocioException;

	void iniciarJobProcedatos() throws NegocioException;

	void guardarIndice(IndiceAcumulado indice, UsuarioDto usuario) throws NegocioException;

	void guardarValorDominio(Dominios valor, UsuarioDto usuarioDto) throws NegocioException;

	/**
	 * Permite eliminar una hora de corte del sistema.
	 * 
	 * @param eliminar
	 *            hora de corte a eliminar.
	 */
	void eliminarHora(HorasCorteArchivo eliminar) throws NegocioException;

	void eliminarIndice(IndiceAcumulado eliminar) throws NegocioException;

	void eliminarCiudad(Ciudad eliminar) throws NegocioException;

	void eliminarDepartamento(Departamento eliminar) throws NegocioException;

	void eliminarFechaCalendario(Calendario eliminar) throws NegocioException;

	int eliminarIndice();

	void eliminarValorDominio(Dominios eliminar) throws NegocioException;

	/**
	 * Determina la cantidad de registros de la consulta de horas de corte.
	 * 
	 * @param consultar
	 *            permite filtrar la consulta de horas.
	 * @return cantidad de registros para la horas de corte segun el filtro.
	 */
	int cantidadRegistros(HorasCorteArchivo consultar);

	/**
	 * Determina la cantidad de registros de la consulta de indices acumulados.
	 * 
	 * @param consultar
	 *            permite filtrar la consulta de indices acumulados para determinar
	 *            la cantidad de registros de la misma.
	 * @return cantidad de registros para la consulta de indices acumulados segun el
	 *         filtro.
	 */
	int cantidadRegistros(IndiceAcumulado consultar);

	/**
	 * Determina la cantidad de registros de la consulta de dominios.
	 * 
	 * @param consultar
	 *            permite filtrar la consulta de los dominios para determinar la
	 *            cantidad de registros de la misma.
	 * @return cantidad de registros para los dominios segun el filtro aplicado.
	 */
	int cantidadRegistros(Dominios consultar);

	/**
	 * Determina la cantidad de registros de la consulta de departamentos.
	 * 
	 * @param consultar
	 *            permite filtrar la consulta de departamentos para determinar la
	 *            cantidad de registros de la misma..
	 * @return cantidad de registros para los departamentossegun el filtro aplciado.
	 */
	int cantidadRegistros(Departamento departamento);

	/**
	 * Determina la cantidad de registros de la consulta ciudades.
	 * 
	 * @param consultar
	 *            permite filtrar la consulta de ciudades para determinar la
	 *            cantidad de registros de la misma.
	 * @return cantidad de registros para las ciudades segun el filtro aplicado.
	 */
	int cantidadRegistros(Ciudad ciudadFiltro);

	/**
	 * Determina la cantidad de registros de la consulta de fechas del calendario.
	 * 
	 * @param consultar
	 *            permite filtrar la consulta de fechas del calendario para
	 *            determinar la cantidad de registros de la misma.
	 * @return cantidad de registros para las fechas del calendario segun el filtro
	 *         aplicado.
	 */
	int cantidadRegistros(Calendario calendario);

	/**
	 * Determina la cantidad de registros de la consulta de registros de un dominio
	 * determinado.
	 * 
	 * @param consultar
	 *            permite filtrar los registros de un dominio de terminado para
	 *            determinar la cantidad de los mismos.
	 * @return cantidad de registros para la los elementos de un dominio segun el
	 *         filtro aplicado.
	 */
	int cantidadRegistrosValores(String consultar);
	
	/**
	 * Determina la cantidad de registros de la consulta de registros de un dominio
	 * determinado.
	 * 
	 * @param consultar
	 *            permite filtrar los registros de un dominio de terminado para
	 *            determinar la cantidad de los mismos.
	 * @return cantidad de registros para la los elementos de un dominio segun el
	 *         filtro aplicado.
	 */
	int cantidadRegistrosValoresFiltro(String consultar, Dominios filtro);

	/**
	 * Permite consultar un elemento de un dominio por la llave del dominio y la
	 * abreviacion de uno de sus elementos.
	 * 
	 * @param llave
	 *            del dominio al que consultaremos los elementos.
	 * @param abreviacion
	 *            con la que se identifica el elmento del dominio.
	 * @return dominio que aplica para la llave y abreviacion.
	 * @throws NegocioException
	 */
	Dominios consultarDominioPorAbreviacion(String llave, String abreviacion) throws NegocioException;

	/**
	 * Permite consultar un elemento de un dominio por la llave del dominio y la el
	 * identificador de uno de sus elementos.
	 * 
	 * @param llave
	 *            del dominio al que consultaremos los elementos.
	 * @param lowValue
	 *            llave con la que se identifica el elmento del dominio.
	 * @return dominio que aplica para la llave y la identificacion del elemento.
	 * @throws NegocioException
	 */
	Dominios consultarDominio(String llave, String lowValue) throws NegocioException;

	/**
	 * Nos permite consultar todos los elemenntos de un dominio segun la llave del
	 * mismo.
	 * 
	 * @param llave
	 *            del dominio al que consultaremos los elementos.
	 * @return listado de elementos que pertenece al dominio.
	 * @throws NegocioException
	 */
	List<Dominios> consultarDominios(String llave) throws NegocioException;

	/**
	 * permite obtener para un dominio del sistema obtener el campo que representa
	 * la descripcion para el mismo teniendo en cuenta la llave del dominio.
	 * 
	 * @param llave
	 *            determinar a que lilstado vamos acceder.
	 * @param lowValue
	 *            permite determinar a que elemento del dominio le recuperaremos la
	 *            descripcion
	 * @return descricpion asociada al elmento de la llave del dominio que
	 *         especifcamos.
	 * @throws NegocioException
	 */
	String consultarDescripcionDominio(String llave, String lowValue) throws NegocioException;

	/***
	 * permite consutlar una ciudad por su id
	 * 
	 * @param id
	 *            de la ciudad a consultar
	 * @return ciudad asociada al id.
	 * @throws NegocioException
	 *             en caso de problemas con la consulta.
	 */
	Ciudad consultarCiudad(Long id) throws NegocioException;

	/**
	 * Permite consultar un departamento por su id
	 * 
	 * @param id
	 *            del departamento a consultar
	 * @return departamento que corresponde al id
	 * @throws NegocioException,
	 *             en caso de problemas con la consulta.
	 */
	Departamento consultarDepartamento(Long id) throws NegocioException;

	void cargarAnio(Integer anioCargar, UsuarioDto usuario) throws NegocioException;

	List<Ciudad> consultarCiudades(Ciudad ciudad, int first, int pageSize);
	
	String consultarDescripcionDominio(List<Dominios> dominios,String lowValue) throws NegocioException;

	int cantidadRegistros(Parametrizacion consultar); 
	
	void agregarElementoCache(String region, String llave, Object objeto) throws NegocioException;

	String getDominioByLowValue(String dominio, String valorDominio);
	
	Long getDominioByMeaning(String dominio,String valorDominio);
}