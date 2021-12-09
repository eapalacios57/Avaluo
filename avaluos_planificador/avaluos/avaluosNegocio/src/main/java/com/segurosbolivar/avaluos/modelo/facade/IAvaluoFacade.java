package com.segurosbolivar.avaluos.modelo.facade;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SeccionConstruccion;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;

/**
 * Abstrae la capa de presentacion y los servicios para la gesti�n del aval�o.
 * 
 * Realiza la invocaci�n de las listas necesarias dentro de la vista, todas las
 * operaciones relacionadas con el aval�o y el diligencimiento individual del
 * mismo.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@Local
public interface IAvaluoFacade extends Serializable {

    /**
     * Permite realizar la consulta de la ventana de informe de avaluos, permite
     * establecer el orden definido por un campo determinado al igual que un limite
     * de registros a consultar para la paginacion.
     * 
     * @param consulta
     *            contiene todos los posibles filtro asociados a la consulta.
     * @param inicio
     *            registro en el que inicia la consulta
     * @param registroXPagina
     *            cantidad de registros a consultar a partir del registro inicial
     * @param campoOrden
     *            campo de la base de datos por la cual se determinará el orden de
     *            la consulta
     * @param sentido
     *            de orientacion de la consulta.
     * @return listado de avaluos asociados al filtro de la consulta.
     */
    List<Avaluo> consultar(ConsultaAvaluoDto consulta, int inicio, int registroXPagina, String campoOrden, SentidoOrientacion sentido) throws NegocioException;

    /**
     * Permite realizar la consulta de un avaluo a partir de una matricula inmobiliaria
     * 
     * @param consulta
     *            contiene todos los posibles filtro asociados a la consulta.
     * @param inicio
     *            registro en el que inicia la consulta
     * @param registroXPagina
     *            cantidad de registros a consultar a partir del registro inicial
     * @param campoOrden
     *            campo de la base de datos por la cual se determinará el orden de
     *            la consulta
     * @param sentido
     *            de orientacion de la consulta.
     * @return listado de avaluos asociados al filtro de la consulta.
     */
    List<Avaluo> consultarAvaluoPorMatricula(ConsultaAvaluoDto consulta, int inicio, int registroXPagina, String campoOrden, SentidoOrientacion sentido) throws NegocioException;

    
    /**
     * Permite realiza la consulta de un avaluo existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo a consultar.
     * @return avaluo que corresponde al identificador.
     * @throws NegocioException
     */
    Avaluo consultarAvaluo(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de la informacion del barrio para un avaluo
     * existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return informacion del barrio que corresponde al identificador del avaluo.
     * @throws NegocioException
     */
    InformacionBarrio consultarBarrio(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de la informacion del inmueble para un avaluo
     * existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return informacion del inmueble que corresponde al identificador del avaluo.
     * @throws NegocioException
     */
    InformacionInmueble consultarInmueble(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de la informacion de la construccion para un
     * avaluo existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return informacion de la construccion que corresponde al identificador del
     *         avaluo.
     * @throws NegocioException
     */
    InformacionConstruccion consultarConstruccion(Long avaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de las condiciones de salubridad para un avaluo
     * existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return condiciones de salubridad que corresponde al identificador del
     *         avaluo.
     * @throws NegocioException
     */
    CondicionesSalubridad consultarSalubridad(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de la ofreta y la demanda para un avaluo
     * existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return oferta y demanda que corresponde al identificador del avaluo.
     * @throws NegocioException
     */
    ComportamientoOfertaDemanda consultarOferta(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de los registros de liquidacion de la
     * construccion para un avaluo existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return listado de registros de liquidacion que corresponde al identificador
     *         del avaluo.
     * @throws NegocioException
     */
    List<LiquidacionAvaluo> consultarLiquidacion(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de la liquidacion total para un avaluo existente
     * a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return liquidacion total que corresponde al identificador del avaluo.
     * @throws NegocioException
     */
    LiquidacionAvaluoTotal consultarLiquidacionTotal(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de las observaciones para un avaluo existente a
     * traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return observaciones de la construccion que corresponde al identificador del
     *         avaluo.
     * @throws NegocioException
     */
    Observaciones consultarObservacion(Long idAvaluo) throws NegocioException;

    /**
     * Permite realiza la consulta de la informacion del anexo fotografico para un
     * avaluo existente a traves del id del mismo.
     * 
     * @param idAvaluo
     *            identificador del avlauo al que se consultara la infromacion
     *            correspondiente.
     * @return anexo fotografico que corresponde al identificador del avaluo.
     * @throws NegocioException
     */
    AnexoFotografico consultarAnexo(Long idAvaluo) throws NegocioException;

    /**
     * Permite aprobar varios avaluos a la vez
     * 
     * @param avaluos
     *            que se aprobaran
     * @param usuario
     *            que realia la operacion de aprobacion
     * @return los avaluos despues de generada su aprobacion.
     * @throws NegocioException
     * @throws Exception
     */
    List<Avaluo> aprobar(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException, Exception;

    /**
     * Permite realizar la aprobacion de un avaluo.
     * 
     * @param aprobar
     *            avaluo a aprobar.
     * @param usuario
     *            que realiza la accion
     * @return avaluo procesado.
     * @throws NegocioException
     * @throws Exception
     */
    Avaluo aprobar(Avaluo aprobar, UsuarioDto usuario) throws NegocioException, Exception;

    /**
     * Permite verificar si un listado de avaluos cumple con las validaciones previo
     * a la aprobacion, se valida para cada avaluo y si pasa se generan las alertas
     * de previo aprobacion con el fin de que el usuario las confirme
     * 
     * @param avaluos
     *            a verificar su aprobacion.
     * @param usuario
     *            que genera la accion.
     * @return en caso de que existan las alertas previas a la aprobacion que el
     *         usuario debe confirmar.
     * @throws NegocioException
     *             si el proceso de validacion no fue exitos.
     */
    NegocioAlertaException preAprobar(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException;

    /**
     * Permite verificar si avaluo cumple con las validaciones previo a la
     * aprobacion, se valida el avaluo y si pasa se generan las alertas de previo
     * aprobacion con el fin de que el usuario las confirme
     * 
     * @param avaluos
     *            a verificar su aprobacion.
     * @param usuario
     *            que genera la accion.
     * @return en caso de que existan las alertas previas a la aprobacion que el
     *         usuario debe confirmar.
     * @throws NegocioException
     *             si el proceso de validacion no fue exitos.
     */
    NegocioAlertaException preAprobar(Avaluo preAprobar, UsuarioDto usuario) throws NegocioException;

    /**
     * Permite generar para un avaluo las alertas previas a la aprobacion del mismo.
     * 
     * @param preAprobar
     *            avaluo al que se generan las alertas previas.
     * @param usuario
     *            que genera la accion.
     * @return excepcion con el listado de alertas asociadas al avaluo que se
     *         generaron segun las verificaicones realizadas por el sistema.
     * @throws NegocioException
     */
    NegocioAlertaException generarAlertaPrevioAprobacion(Avaluo preAprobar, UsuarioDto usuario) throws NegocioException;

    /**
     * Permite generar el proceso de replicacion de un avaluo(copia de un avaluo
     * donde solo cambia la fecha del avaluo y su consecutivo)
     * 
     * @param avaluoSeleccionado
     *            a replicar.
     * @param usuario
     *            que genera la accion.
     * @throws Exception
     */
    void replicar(List<Avaluo> avaluoSeleccionado, UsuarioDto usuario) throws Exception;

    /**
     * Con base a un avaluo se realizara una copia con ciertos valores modificados
     * por el usuario.
     * 
     * @param copia
     *            contiene los valores que el usuario modifico teniendo en cuenta el
     *            avaluo original
     * @param original
     *            avaluo del que se tomaran las propiedades o valores diferentes al
     *            que el usuario modifico.
     * @param usuario
     *            que genera la accion.
     * @throws Exception
     */
    void transcribir(Avaluo copia, Avaluo original, UsuarioDto usuario) throws Exception;

    /**
     * Con base en un avaluo aprobado, se generan multiples copias para las cuales
     * el usuario modifico ciertos valores.
     * 
     * @param copias
     *            contienen los valores que seran diferentes al avaluo que se toma
     *            como referencia.
     * @param original
     *            avaluo que se toma como base para las copias a generar.
     * @param codigoNombreConstructora
     *            que se aplicara a todos las copias del avlauo original.
     * @param nombreConstructora
     *            que se aplicara a todos las copias del avlauo original.
     * @param usuario
     *            que genera la accion
     * @throws Exception
     */
    void guardarMultipleConstructor(List<Avaluo> copias, Avaluo original, Long codigoNombreConstructora, String nombreConstructora, UsuarioDto usuario) throws NegocioException;

    /**
     *  Genera un archivo zip del avaluo 
     * @param avaluos
     * @return archivo que contiene 
     * @throws NegocioException
     */
    File crearCopiaSeguridad(List<Avaluo> avaluos) throws NegocioException;

//    String consultarDocumento(String idDocumento) throws NegocioException;

    List<String> consultarRegistroFotograficoS3(Avaluo avaluo) throws NegocioException;
    
    List<ListaAnexosPdf> consultarRegistroFotografico(Avaluo avaluo, boolean devolverContenido) throws NegocioException;
    
    byte[] obtenerDocumento(String idDocumento) throws NegocioException, IOException;
    
    String obtenerDocumentoURL(String idDocumento, String nombreArchivo) throws NegocioException, IOException;

    void eliminar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    void enviarParaAprobacion(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException;

    void guardar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    void guardar(List<LiquidacionAvaluo> listado, LiquidacionAvaluoTotal total, UsuarioDto usuarioDto) throws NegocioException;

    void guardar(InformacionBarrio barrio, UsuarioDto usuario) throws NegocioException;

    void guardar(InformacionConstruccion construccion, SeccionConstruccion seccion, Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    NegocioAlertaException generarAlertasInformacionConstruccion(InformacionConstruccion construccion, SeccionConstruccion seccion, Avaluo avaluo, UsuarioDto usuario)
	    throws NegocioException;

    void guardar(InformacionInmueble inmueble, Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    NegocioAlertaException generarAlertasInformacionInmueble(InformacionInmueble inmueble, Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    void guardar(Observaciones observacion, UsuarioDto usuario) throws NegocioException;

    /**
     * Permite almacenar un registro de oferta y demanda asociado a un avaluo. si el
     * registro no existe este se creara.
     * 
     * @param ofertademanda
     *            registro de oferta y demanda a almacenar
     * @param usuario
     *            que realiza la accion de guardado y el cual se tendra en cuenta
     *            para la auditoria del sistema.
     * @throws NegocioException
     *             en caso de que existan problemas al almacenar la informacion
     *             respectiva.
     */

    void guardar(ComportamientoOfertaDemanda ofertademanda, UsuarioDto usuario) throws NegocioException;

    void guardar(CondicionesSalubridad salubridad, UsuarioDto usuario) throws NegocioException;

    void reversar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    /**
     * Permite reversar la fecha de impresion de un avaluo de modo que vuelva a la
     * fecha anterior de impresion que tenia.
     * 
     * @param avaluo
     *            al que reversaremos la fecha de impresion
     * @param usuario
     *            que realiza la accion
     * @throws NegocioException
     *             en caso de error al intentar actualziar la fecha de imrpesion.
     */
    void impresionErronea(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    void validar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    String transformarDireccion(String direccion, boolean validateComplemento);

    String transformarDireccionComplementaria(String direccion);

    int cantidadRegistros(ConsultaAvaluoDto consulta) throws NegocioException;

    BigDecimal consultaUvr() throws NegocioException;

    void guardar(AnexoFotografico registroFotografico, List<ListaAnexosPdf> fotografias, Avaluo avaluo, UsuarioDto usuario) throws NegocioException;
    
    void eliminarRegistroFotografico(AnexoFotografico registroFotografico, Avaluo avaluo)throws NegocioException;
    
    File imprimir(List<Avaluo> avaluos, boolean registroFotografico, UsuarioDto usuario) throws NegocioException;
    
    File generarReporteAvaluos(List<Avaluo> avaluos, boolean verAsegurabilidad) throws NegocioException;

    Dominios consultarDominioPorAbreviacion(String llave, String abreviacion) throws NegocioException;

    Ciudad consultarCiudad(long idCiudad) throws NegocioException;

    Departamento consultarDepartamento(long idDepartamento) throws NegocioException;

    void enviarCorreos(Avaluo avaluoConsulta, UsuarioDto usuario) throws NegocioException;

    void enviarCorreos(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException;

    ArrayList<String> obtenerExtentCentroide(String departamentoDivipola, String ciudadDivipola, String direccion, String barrio) throws IOException, Exception;

    List<ArchivoPlano> consultarCamposObligatoriosCategoria(Long categoria, String tabla) throws NegocioException;

    NegocioAlertaException consultarAlertasCopia(Avaluo avaluoOrigional, List<Avaluo> copias, UsuarioDto usuario) throws NegocioException;

	String consultaUvrMotor() throws NegocioException;

	void eliminar(Avaluo avaluo) throws NegocioException;
	void eliminarLiq(Long idAvaluo) throws NegocioException;

	void eliminar(ComportamientoOfertaDemanda ofertademanda) throws NegocioException;
    
}