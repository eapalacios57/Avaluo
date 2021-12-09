package com.segurosbolivar.avaluos.planificador.servicios;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.primefaces.component.log.Log;
import org.primefaces.util.Base64;

import com.asesoftware.util.exception.NegocioException;
import com.google.gson.Gson;
import com.segurosbolivar.avaluos.modelo.jobs.ProgramacionIntentoSincronizaciones;
import com.segurosbolivar.avaluos.modelo.prueba.ITomaTiempos;
//import com.segurosbolivar.avaluos.modelo.ws.service.impl.ClienteFileNetWebService;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;
import com.segurosbolivar.avaluos.planificador.modelo.service.impl.SincronizarImpl;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPiscicola;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPorcicola;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICiudad;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICultivo;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDocumento;
import com.segurosbolivar.avaluos.planificador.modelo.services.IGestionArchivos;
import com.segurosbolivar.avaluos.planificador.modelo.services.IMedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.services.IParametroValor;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPlanificador;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPredio;
import com.segurosbolivar.avaluos.planificador.modelo.services.IProductoRelacionado;
import com.segurosbolivar.avaluos.planificador.modelo.services.IProductoRelacionadoSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISincronizar;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudMovimiento;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISoporte;
import com.segurosbolivar.avaluos.planificador.modelo.services.ITecnificacionAgricola;
import com.segurosbolivar.avaluos.planificador.modelo.services.IUnidad;
import com.segurosbolivar.avaluos.planificador.modelo.services.IUnidadProductiva;
import com.segurosbolivar.avaluos.planificador.modelo.services.IValorPorcentaje;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.ParametrosModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.RespuestaSincronizacionModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.DocumentosModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.PlanificadorModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.SolicitudAsignadaModel;
import com.segurosbolivar.avaluos.planificador.util.Constantes;
import com.segurosbolivar.avaluos.planificador.util.ProcesosSincronizacion;

@Path("/api")
@Stateless
public class MovileAppSvc {

	@EJB
	ISincronizar iSincronizar;

	@EJB
	IUnidad iUnidad;

	@EJB
	IParametroValor iParametroValor;

	@EJB
	IProductoRelacionado iProductoRelacionado;

	@EJB
	ICiudad iCiudad;

	@EJB
	ISolicitud iSolicitud;

	@EJB
	IMedioComunicacion iMedioComunicacion;

	@EJB
	IProductoRelacionadoSolicitud iProductoRelacionadoSolicitud;

	@EJB
	IUnidadProductiva iUnidadProductiva;

	@EJB
	IPredio iPredio;

	@EJB
	IValorPorcentaje iValorPorcentaje;

	@EJB
	ITecnificacionAgricola iTecnificacionAgricola;

	@EJB
	ISoporte iSoporte;

	@EJB
	ICultivo iCultivo;

	@EJB
	IActividadPorcicola iActividadPorcicola;

	@EJB
	IActividadGanadera iActividadGanadera;

	@EJB
	IActividadAvicola iActividadAvicola;

	@EJB
	IActividadPiscicola iActividadPiscicola;

	@EJB
	ISolicitudMovimiento iSolicitudMovimiento;

//	@EJB
//	ClienteFileNetWebService clienteFileNetWebService;

	@EJB
	private ITomaTiempos Tiempo;

	@EJB
	IGestionArchivos iGestionArchivos;
	
	@EJB
	IDocumento iDocumento; 
	
	@EJB
	IPlanificador iPlanificador; 
	
	@EJB
	IEjbAsincrono  EjbAsincrono; 
	
	
	private static final Logger log = Logger.getLogger(MovileAppSvc.class);
	

	@POST
	@Path("/sincronizarInformacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.MULTIPART_FORM_DATA)
	public RespuestaSincronizacionModel sincronizarInformacion(@HeaderParam("Authorization") String token,
			SincronizarModel sincronizarModel) throws Exception {
		log.info("Inicio Proceso de Sincronizacion");
		RespuestaSincronizacionModel respuestaSincronizacionModel = new RespuestaSincronizacionModel();
		ProcesosSincronizacion procesos = new ProcesosSincronizacion();		
		try {	
			//Llamado a guardar el Json
			procesos.guardarSolicitud(sincronizarModel);			
			//Se envía estatus de sincronizado ok 
			log.info("----------------------------antes:  "+ respuestaSincronizacionModel.getEstatus());
			EjbAsincrono.RespuestaAsincrona(sincronizarModel);
			respuestaSincronizacionModel.setMensaje("Sincronizacion OK");
			respuestaSincronizacionModel.setEstatus(Constantes.SINCRONIZAR_RESPUESTA_OK);				
			log.info("----------------------------despues:  "+ respuestaSincronizacionModel.getEstatus());

		} catch (Exception e) {					
			 e.printStackTrace();

		}

		return respuestaSincronizacionModel;
	}

	
	@GET
	@Path("/sincronizarParametros")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ParametrosModel sincronizarParametros(@HeaderParam("Authorization") String token) {

		ParametrosModel parametrosModel = new ParametrosModel();

		try {

			// agregar unidad
			parametrosModel.setListUnidadModel(iSincronizar.consultarUnida());

			// agregar parametros
			parametrosModel.setListParametrosValoresModel(iSincronizar.consultarParametrosValores());

			// Agregar producto relacionado
			parametrosModel.setListProductoRelacionadoModel(iSincronizar.consultarProductoRelacionado());

			// Agregar ciudad departamento
			parametrosModel.setListMunicipioDepartamentoModel(iSincronizar.consultarMunicipioDepartamento());
			

		} catch (SQLException e) {
			parametrosModel = new ParametrosModel();
			e.printStackTrace();
		}

		return parametrosModel;
	}

	@POST
	@Path("/consultarSolicitudes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<SolicitudAsignadaModel> consultarSolicitudes(@HeaderParam("Authorization") String token,
			PlanificadorModel planificadorModel) {

		List<SolicitudAsignadaModel> listSolicitudModel = new ArrayList<SolicitudAsignadaModel>();

		try {

			listSolicitudModel = iSolicitud.consultarSolicitud(planificadorModel);
						
		} catch (SQLException | NegocioException e) {
			// TODO Auto-generated catch block

			listSolicitudModel = new ArrayList<SolicitudAsignadaModel>();
			e.printStackTrace();

		}

		return listSolicitudModel;
	}
	
	@GET
	@Path("/obtenerDocumento/{idDocumento}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DocumentosModel obtenerDocumento(@HeaderParam("Authorization") String token,@PathParam("idDocumento") Integer idDocumento ) {
		
		Documento doc =	iDocumento.buscaDocumento(new BigDecimal(idDocumento).longValue());
		
		DocumentosModel respuesta = new DocumentosModel();
		byte[] data;
		
		try {
			
			//BORRAR - TEST
//			Log.info("Descarga documento por URL, obtenerDocumento, inicio ");
//			iGestionArchivos.consultarArchivoS3URL(doc.getPath());
//			Log.info("Descarga documento por URL, obtenerDocumento, fin ");			
//			data = iGestionArchivos.consultarArchivoS3(null);// FIN BORRAR - TEST
			
			
			data = iGestionArchivos.consultarArchivoS3(doc.getPath());
			String dataStr = Base64.encodeToString(data,false);
			respuesta.setPathFileNet(dataStr);
			
		} catch (NegocioException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return respuesta;
	}
	
	@POST
	@Path("/actualziarTokenDispositivo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PlanificadorModel actualziarTokenDispositivo(@HeaderParam("Authorization") String token,
			PlanificadorModel planificadorModel) {
		
		try {
			
			iPlanificador.actualziarTokenDispositivo(planificadorModel);	
			
		} catch (SQLException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return planificadorModel;
	}
	

}