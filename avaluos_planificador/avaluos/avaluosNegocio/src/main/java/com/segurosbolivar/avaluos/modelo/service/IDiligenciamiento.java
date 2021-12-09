package com.segurosbolivar.avaluos.modelo.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SeccionConstruccion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;

/**
 * Permite realizar la operaci�n de diligenciamiento de un aval�o individual
 * implicando las funciones sobre las secciones de informaci�n del barrio,
 * informaci�n de la construcci�n, informaci�n del inmueble, condici�n de
 * salubridad, liquidaci�n del aval�o, observaciones, oferta y demanda.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@Local
public interface IDiligenciamiento extends Serializable {

	List<ListaAnexosPdf> consultarRegistroFotografico(Avaluo avaluo, boolean obtenerContenido) throws NegocioException;
	public List<ListaAnexosPdf> consultarRegistroFotograficoS3(Avaluo avaluo) throws NegocioException;
//    @Deprecated
//    void guardarArchivoFotografico(ListaAnexosPdf anexos, UsuarioDto usuario) throws Exception;

    void guardar(List<LiquidacionAvaluo> listado, LiquidacionAvaluoTotal total, UsuarioDto usuarioDto) throws NegocioException;

    void eliminarLiq(Long idAvaluo) throws NegocioException;

    void guardar(InformacionBarrio barrio, UsuarioDto usuario) throws NegocioException;

    void guardar(InformacionConstruccion construccion, SeccionConstruccion seccion, Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    NegocioAlertaException generarAlertaPrevioAprobacion(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    NegocioAlertaException generarAlertasAvaluo(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    NegocioAlertaException generarAlertasInformacionConstruccion(Avaluo avaluo, InformacionConstruccion construccion, UsuarioDto usuario) throws NegocioException;

    void guardar(InformacionInmueble inmueble, Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    void guardar(Observaciones observacion,  UsuarioDto usuario) throws NegocioException;

    /**
     * Permite almacenar un registro de oferta y demanda asociado a un avaluo. si el
     * registro no existe este se creara.
     * 
     * @param oferta
     *            registro de oferta y demanda a almacenar
     * @param usuario
     *            que realiza la accion de guardado y el cual se tendra en cuenta
     *            para la auditoria del sistema.
     * @throws NegocioException
     *             en caso de que existan problemas al almacenar la informacion
     *             respectiva.
     */
    /**
     * Permite almacenar un registro de oferta y demanda asociado a un avaluo. si el
     * registro no existe este se creara.
     * 
     * @param oferta
     *            registro de oferta y demanda a almacenar
     * @param usuario
     *            que realiza la accion de guardado y el cual se tendra en cuenta
     *            para la auditoria del sistema.
     * @throws NegocioException
     *             en caso de que existan problemas al almacenar la informacion
     *             respectiva.
     */
    void guardar(ComportamientoOfertaDemanda oferta, UsuarioDto usuario) throws NegocioException;

    void guardar(CondicionesSalubridad salubridad, UsuarioDto usuario) throws NegocioException;

    void crearAlerta(Avaluo avaluo, int codigoAlerta, UsuarioDto usuario) throws NegocioException;

    NegocioAlertaException generarAlertasInformacionInmueble(Avaluo avaluo, InformacionInmueble inmueble, UsuarioDto usuario) throws NegocioException;

    List<String> obtenerArchivofotograficos3(Avaluo avaluo);

    byte[] obtenerDocumento(String idDocumento) throws NegocioException, IOException;
    
    String obtenerDocumentoURL(String idDocumento, String nombreArchivo) throws NegocioException, IOException;

    InformacionBarrio consultarBarrio(Long idAvaluo) throws NegocioException;

    InformacionInmueble consultarInmueble(Long idAvaluo) throws NegocioException;

    InformacionConstruccion consultarConstruccion(Long avaluo) throws NegocioException;

    CondicionesSalubridad consultarSalubridad(Long idAvaluo) throws NegocioException;

    ComportamientoOfertaDemanda consultarOferta(Long idAvaluo) throws NegocioException;

    List<LiquidacionAvaluo> consultarLiquidacion(Long idAvaluo) throws NegocioException;

    LiquidacionAvaluoTotal consultarLiquidacionTotal(Long idAvaluo) throws NegocioException;

    Observaciones consultarObservacion(Long idAvaluo) throws NegocioException;

    AnexoFotografico consultarAnexo(Long idAvaluo) throws NegocioException;

    NegocioAlertaException consultarAlertas(Avaluo preAprobar) throws NegocioException;

    void enviarCorreos(Avaluo avaluoConsulta, UsuarioDto usuario) throws NegocioException;

    void enviarCorreosAvaluo(Avaluo avaluo, UsuarioDto usuario) ;

    String consultarDestinatariosNotificacion(String tipoDestinatario) throws NegocioException;

    void confirmarAlerta(Avaluo avaluo, int codigoAlerta, UsuarioDto usuario, boolean estaEnAlerta) throws NegocioException;

    void guardar(AnexoFotografico registroFotografico, List<ListaAnexosPdf> fotografias, Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

    boolean verificarInmuebleRepetido(InformacionInmueble inmueble);
    
    public void eliminarRegistrosFotograficos(AnexoFotografico registroFotografico, Avaluo avaluo) throws NegocioException;

	void eliminar(ComportamientoOfertaDemanda oferta) throws NegocioException;
}