package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.TransactionRolledbackLocalException;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFolder;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ProductoRelacionadoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPiscicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ParametroValor;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionado;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionadoSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionadoSolicitudPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentajePK;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SolicitudPlanificadorFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPiscicola;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPorcicola;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICiudad;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICultivo;
import com.segurosbolivar.avaluos.planificador.modelo.services.IGestionArchivos;
import com.segurosbolivar.avaluos.planificador.modelo.services.IMedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.services.IParametroValor;
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
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.MunicipioDepartamentoModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.ParametrosValoresModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.ProductoRelacionadoModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.UnidadModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.CultivoModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.ImagenesSolicitudModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.PredioModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.RespuestaSincronizacionModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.UnidadProductivaModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.ValoresPorcentajeModel;
import com.segurosbolivar.avaluos.planificador.util.Constantes;
import com.segurosbolivar.avaluos.planificador.util.ProcesosSincronizacion;

@Stateless
@Local(ISincronizar.class)
//@TransactionManagement(TransactionManagementType.BEAN)
public class SincronizarImpl implements ISincronizar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ISolicitud iSolicitud;

	@EJB
	IMedioComunicacion iMedioComunicacion;

	@EJB
	IProductoRelacionado iProductoRelacionado;

	@EJB
	IProductoRelacionadoSolicitud iProductoRelacionadoSolicitud;

	@EJB
	IUnidadProductiva iUnidadProductiva;

	@EJB
	ICiudad iCiudad;

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

	@EJB
	IGestionArchivos iGestionArchivos;

	@EJB
	IUnidad iUnidad;

	@EJB
	IParametroValor iParametroValor;

	@Override
	public int sincronizarSolicitud(SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException , Exception  {
		// TODO Auto-generated method stub
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarSolicitud , inicio");
		
      try {
    	 
    	sincronizarInforamcionRollBack(sincronizarModel.getSolicitudModel().getCodSolicitud());
    	  
		Solicitud solicitud = iSolicitud.buscarPorId(sincronizarModel.getSolicitudModel().getCodSolicitud());			
				
		solicitud = actualizarSolicitud(solicitud, sincronizarModel);

		actualziarMedioComunicacion(sincronizarModel);

		crearProductoRelacionadoSolicitud(solicitud, sincronizarModel);

		crearUnidadProductiva(solicitud, sincronizarModel);		
		
		int estadoSincronizacion = actualziarSolicitudEstado(solicitud, sincronizarModel);
		
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarSolicitud , fin");

		return estadoSincronizacion;
		
      }catch(SQLException | ParseException | NegocioException e) {
    	  Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarSolicitud , Error ");
    	  e.printStackTrace();
    	  	throw new NegocioException ("Error al insertar datos sincronizados ");
    	  
      }
         //return Constantes.SINCRONIZAR_RESPUESTA_ERROR;
		// respuestaSincronizacionModel;
	}

	@Override
	public Solicitud actualizarSolicitud(Solicitud solicitud, SincronizarModel sincronizarModel) throws SQLException {
		// TODO Auto-generated method stub
		try {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  actualizarSolicitud , inicio , " + solicitud.getCodigoSolicitud() );
		solicitud.setComentariosAnexos(sincronizarModel.getSolicitudModel().getComentariosAnexos());
		SolicitudPlanificadorFullDTOMapper solicitudFullDTOMapper = Mappers
				.getMapper(SolicitudPlanificadorFullDTOMapper.class);
		SolicitudDTO solicitudDto = solicitudFullDTOMapper.entity2dto(solicitud);
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  actualizarSolicitud , fin , "  + solicitud.getCodigoSolicitud());		
		
		}catch(Exception   e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  actualizarSolicitud  , Error al actualizar la solicitud  , " + solicitud.getCodigoSolicitud());
			throw new SQLException("Sincronizar Solicitud ,  actualizarSolicitud  , Error al actualizar la solicitud" , e);
		}
		
		return iSolicitud.buscarPorId(sincronizarModel.getSolicitudModel().getCodSolicitud());
		
	}

	@Override
	public void actualziarMedioComunicacion(SincronizarModel sincronizarModel) throws SQLException {
	
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  actualizarMedioComunicacion , inicio");
		try {
		MedioComunicacionDTO comunicacionDTO = new MedioComunicacionDTO();
		comunicacionDTO
				.setTipoDocumentoBeneficiario(sincronizarModel.getSolicitudModel().getNumDocumentoBeneficiario());
		comunicacionDTO
				.setTipoDocumentoBeneficiario(sincronizarModel.getSolicitudModel().getTipoDocumentoBeneficiario());
		comunicacionDTO
				.setNumeroDocumentoBeneficiario(sincronizarModel.getSolicitudModel().getNumDocumentoBeneficiario());
		comunicacionDTO.setValor(sincronizarModel.getSolicitudModel().getTelefonoBeneficiario());
		comunicacionDTO.setTipoMedioComunicacion("2");
		comunicacionDTO.setPrincipal("N");
		comunicacionDTO.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		comunicacionDTO.setFechaCreacion(new Date());

		comunicacionDTO.setDescripcion("Teléfono obtenido durante la visita técnica");
		comunicacionDTO.setEstado("A");

		iMedioComunicacion.actualzarMedioComunicacionBeneficiario(comunicacionDTO);
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  actualizarMedioComunicaion , fin");
		
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  actualizarSolicitud  , Error al actualizar la solicitud  , " + sincronizarModel.getSolicitudModel().getCodSolicitud());
			e.printStackTrace();
			throw new SQLException("Sincronizar Solicitud ,  actualizarSolicitud  , Error al actualizar la solicitud" , e);
		}
		
	}

	@Override
	public void crearProductoRelacionadoSolicitud(Solicitud solicitud, SincronizarModel sincronizarModel)
			throws SQLException {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearProductoRelacionadoSolicitud , inicio");
		
		try {
		ProductoRelacionadoDTO productoRelacionadoDTO = new ProductoRelacionadoDTO();
		productoRelacionadoDTO.setCodigo(sincronizarModel.getSolicitudModel().getProductoRelacionado());

		ProductoRelacionado productoRelacionado = iProductoRelacionado.getProductosRelacionados(productoRelacionadoDTO);

		ProductoRelacionadoSolicitud productoRelacionadoSolicitud = new ProductoRelacionadoSolicitud();

		ProductoRelacionadoSolicitudPK pk = new ProductoRelacionadoSolicitudPK();
		pk.setCodigoSolicitud(solicitud.getCodigoSolicitud());
		pk.setProductoRelacionadoCodigo(productoRelacionado.getCodigo());

		productoRelacionadoSolicitud.setId(pk);
		productoRelacionadoSolicitud.setProductoRelacionado(productoRelacionado);
		productoRelacionadoSolicitud.setSolicitud(solicitud);
		productoRelacionadoSolicitud.setFechaCreacion(new Date());
		productoRelacionadoSolicitud.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		productoRelacionadoSolicitud.setPrincipal("S");

		productoRelacionadoSolicitud = iProductoRelacionadoSolicitud.crearProductoRelacionadoSolicitud(productoRelacionadoSolicitud);
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearProductoRelacionadoSolicitud , fin");
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearProductoRelacionadoSolicitudcrearProductoRelacionadoSolicitud  , Error al crear producto relacionado solicitud  , " + solicitud.getCodigoSolicitud());
			e.printStackTrace();
			throw new SQLException("Sincronizar Solicitud ,  crearProductoRelacionadoSolicitud  , Error al crear producto relacionado solicitud" , e);
		}     
	
		
	}

	@Override
	public void crearUnidadProductiva(Solicitud solicitud, SincronizarModel sincronizarModel)
			throws Exception, SQLException, TransactionRolledbackLocalException, NegocioException, ParseException {
		// TODO Auto-generated method stub
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearUnidadProductiva , inicio");
		try {
		
		for (UnidadProductivaModel unidadProductivaModel : sincronizarModel.getSolicitudModel()
				.getListUnidadProductivaModel()) {

			// crear unidad priductiva

			UnidadProductiva unidadProductiva = new UnidadProductiva();

			unidadProductiva.setSolicitud(solicitud);
			unidadProductiva.setTipoActividad(unidadProductivaModel.getTipoActividad().toString());
			unidadProductiva.setLugarInversion((unidadProductivaModel.isLugarInversion() ? "S" : "N"));
			unidadProductiva.setNombre(unidadProductivaModel.getNombreUnidadProductiva());
			unidadProductiva.setAreaProyecto(new BigDecimal(unidadProductivaModel.getAreaProyecto()));

			Unidad unidad = new Unidad();
			unidad.setIdUnidad(unidadProductivaModel.getUnidadAreaProyecto());

			unidadProductiva.setUnidad(unidad);
			unidadProductiva.setVereda(unidadProductivaModel.getNombreVereda());
			unidadProductiva.setLatitud(unidadProductivaModel.getLatitud());
			unidadProductiva.setLongitud(unidadProductivaModel.getLongitud());
			unidadProductiva.setAltitud(unidadProductivaModel.getAltitud());
			unidadProductiva.setCondicionViaAcceso(unidadProductivaModel.getCondicionesViaAcceso());
			unidadProductiva.setAspectosClimaticos(unidadProductivaModel.getAspectoClimatico());
			unidadProductiva.setPrecipitacion(unidadProductivaModel.getPrecipitacionAnual());
			unidadProductiva.setHumedadRelativa(unidadProductivaModel.getHumedadRelativa());
			unidadProductiva.setTopografia(unidadProductivaModel.getTopografiaPredominante());
			unidadProductiva.setMateriaOrganica(unidadProductivaModel.getContenidoMateriaOrganica());
			unidadProductiva.setNivelDrenaje(unidadProductivaModel.getNivelDrenaje());
			unidadProductiva.setAreaProtegida(unidadProductivaModel.getAreasProtegidasHA());
			unidadProductiva.setFuenteHidricaExistente(unidadProductivaModel.getFuentesHidricasExistentes());
			unidadProductiva
					.setInfraestAlmacenamientoAgua(unidadProductivaModel.getInfraestructuraAlmacenamientoAgua());
			unidadProductiva.setCoberturaSistemaRiego(unidadProductivaModel.getCoberturaSistemaRiego());
			unidadProductiva.setNombreDistritoRiego(unidadProductivaModel.getNombreDistritoRiego());
			unidadProductiva.setNombreDistritoRiego(unidadProductivaModel.getNombreDistritoRiego());
			unidadProductiva.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
			unidadProductiva.setAreaUtilizadaHA(new BigDecimal(unidadProductivaModel.getAreaUtilizadaHa()));
			unidadProductiva.setFechaCreacion(new Date());

			Ciudad ciudad = consultarCiudad(unidadProductivaModel);

			unidadProductiva.setIdCiudad(new BigDecimal(ciudad.getIdCiudad()));
			unidadProductiva.setIdDepartamento(new BigDecimal(ciudad.getDepartamento().getIdDepartamento()));

			unidadProductiva = iUnidadProductiva.crearUnidadProductiva(unidadProductiva);

			crearPredios(unidadProductiva, unidadProductivaModel, sincronizarModel);

			crearValoresPorcentajeModel(unidadProductiva, unidadProductivaModel, sincronizarModel);

			if (unidadProductivaModel.isTecnificacionAgricola()) {

				crearTecnificacionAgricola(unidadProductiva, unidadProductivaModel, sincronizarModel);
			}

			if (unidadProductivaModel.isCultivos()) {

				crearCultivos(unidadProductiva, unidadProductivaModel, sincronizarModel);

			}

			if (unidadProductivaModel.isActividadPecuaria()) {

				crearActividadPecuaria(unidadProductiva, unidadProductivaModel, sincronizarModel);

			}

			guardarImagenes(unidadProductiva, null, unidadProductivaModel.getListImagenesSolicitudModel(),
					sincronizarModel.getSolicitudModel().getNombrePlanificador(),
					sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());

		}
		
		
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearUnidadProductiva  , Error al crear la unidad productiva  , " + solicitud.getCodigoSolicitud());
			e.printStackTrace();
			throw new SQLException("Sincronizar Solicitud ,  crearUnidadProductiva  , Error al crear la unidad productiva" , e);
		}	
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearUnidadProductiva , fin");
	}

	@Override
	public Ciudad consultarCiudad(UnidadProductivaModel unidadProductivaModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException  {
		
		try {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  consultarCiudad , inicio");
		Ciudad ciudad = null;
		if (!unidadProductivaModel.getMunicipioDepartamento().equals("")
				&& unidadProductivaModel.getMunicipioDepartamento().split("-").length > 1) {

			ciudad = iCiudad.getCiudad(
					unidadProductivaModel.getMunicipioDepartamento().split("-")[0].trim().toUpperCase(),
					unidadProductivaModel.getMunicipioDepartamento().split("-")[1].trim().toUpperCase());
		} else {
			ciudad = iCiudad.getCiudad("", "");
		}
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  consultarCiudad , fin");
		return ciudad;
		}catch (Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  consultarCiudad  , Error al consultar la ciudad  , " + unidadProductivaModel.getCodSolicitud());
			e.printStackTrace();
			throw new SQLException("Sincronizar Solicitud ,  consultarCiudad  , Error al consultar la ciudad" , e);
			
			
		}
		
	}

	@Override
	public void crearPredios(UnidadProductiva unidadProductiva, UnidadProductivaModel unidadProductivaModel,
			SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearPredios , inicio");
		for (PredioModel predioModel : unidadProductivaModel.getListPredioModel()) {

			Predio predio = new Predio();

			predio.setVereda(unidadProductiva.getVereda());
			predio.setIdCiudad(unidadProductiva.getIdCiudad());
			predio.setIdDepartamento(unidadProductiva.getIdDepartamento());
			predio.setUnidadProductiva(unidadProductiva);
			predio.setAreaTotal(new BigDecimal(predioModel.getAreaPredio()));
			predio.setNombreMatriculaInmobiliaria(predioModel.getNombrePredio());
			predio.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
			predio.setFechaCreacion(new Date());

			Unidad unidad = new Unidad();
			unidad.setIdUnidad(predioModel.getIdUnidad());

			predio.setIdUnidadPredio(unidad);

			// TODO: Agregar campo de unidad

			iPredio.crearPredio(predio);
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearPredios , fin");
		}
        
	}

	@Override
	public void crearValoresPorcentajeModel(UnidadProductiva unidadProductiva,
			UnidadProductivaModel unidadProductivaModel, SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearValoresPorcentajeModel , inicio");
		for (ValoresPorcentajeModel valoresPorcentajeModel : unidadProductivaModel.getListValoresPorcentajeModel()) {
			ValorPorcentaje valorPorcentaje = new ValorPorcentaje();

			ValorPorcentajePK valorPorcentajePK = new ValorPorcentajePK();
			valorPorcentajePK.setConcepto(valoresPorcentajeModel.getConcepto());
			valorPorcentajePK.setIdUnidadProductiva(unidadProductiva.getIdUnidadProductiva());

			valorPorcentaje.setUnidadProductiva(unidadProductiva);
			valorPorcentaje.setId(valorPorcentajePK);
			valorPorcentaje.setPorcentaje(new BigDecimal(valoresPorcentajeModel.getPorcentage()));
			valorPorcentaje.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
			valorPorcentaje.setFechaCreacion(new Date());

			iValorPorcentaje.crearValorPorcentaje(valorPorcentaje);
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearValoresPorcentajeModel , fin");
		}

	}

	@Override
	public void crearTecnificacionAgricola(UnidadProductiva unidadProductiva,
			UnidadProductivaModel unidadProductivaModel, SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearTecnificacionAgricola , inicio");
		try {
		TecnificacionAgricola tecnificacionAgricola = new TecnificacionAgricola();

		tecnificacionAgricola.setUnidadProductiva(unidadProductiva);
		tecnificacionAgricola.setActividadMecanizadas(
				unidadProductivaModel.getTecnificacionAgricolaModel().getActividadMecanizadas());
		tecnificacionAgricola
				.setInfraestructura(unidadProductivaModel.getTecnificacionAgricolaModel().getInfraestructuras());
		tecnificacionAgricola.setMaquinariaDisponible(
				unidadProductivaModel.getTecnificacionAgricolaModel().getMaquinariaDisponible());
		tecnificacionAgricola.setTipoTransportePropio(
				unidadProductivaModel.getTecnificacionAgricolaModel().getTipoTransportePropio());
		tecnificacionAgricola.setTransporteAlquilado(
				unidadProductivaModel.getTecnificacionAgricolaModel().isTransporteAlquilado() ? "S" : "N");
		tecnificacionAgricola.setTransportePropio(
				unidadProductivaModel.getTecnificacionAgricolaModel().isTransportePropio() ? "S" : "N");
		tecnificacionAgricola.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		tecnificacionAgricola.setFechaCreacion(new Date());

		iTecnificacionAgricola.crearTecnificacionAgricola(tecnificacionAgricola);

		// registrar imagenes de la tecnificacion
		guardarImagenes(unidadProductiva, null,
				unidadProductivaModel.getTecnificacionAgricolaModel().getListImagenesSolicitudModel(),
				sincronizarModel.getSolicitudModel().getNombrePlanificador(),
				sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		}catch(Exception e) {
			 Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadGanadera , Error "+ unidadProductiva.getIdUnidadProductiva() );
			 e.printStackTrace();
	      		throw new Exception("Error al crear la tecnificacion agricola", e);
		}
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearTecnificacionAgricola , fin");
	}

	@Override
	public void guardarImagenes(UnidadProductiva unidadProductiva, Cultivo cultivo,
			List<ImagenesSolicitudModel> listImagenes, String nombreUsuario, String docUsuario)
			throws ParseException, TransactionRolledbackLocalException, NegocioException, SQLException,  Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  guardarImagenes , inicio");
	try {
		for (ImagenesSolicitudModel imagenesSolicitudModel : listImagenes) {

			byte[] data = DatatypeConverter.parseBase64Binary(imagenesSolicitudModel.getStrImagen());

			String strCultivo = "";
			if (cultivo != null) {
				strCultivo = "_CUL" + cultivo.getIdCultivo();
			}

			String nombreArchivo = "SOL" + imagenesSolicitudModel.getCodSolicitud() + "_UNI"
					+ imagenesSolicitudModel.getIdUnidadProductiva() + strCultivo
					+ imagenesSolicitudModel.getCodImagen() + ".jpg";

			List<String> ruta = new ArrayList<String>();
			ruta.add(UtilConstantes.RUTA_FOTOS);
			ruta.add(nombreArchivo);
			
			String path = iGestionArchivos.guardarArchivoS3(new String(UtilFolder.obtenerRutaDirectorio(ruta)), data);

			if (!path.equals("-1")) {

				DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				Date date = formatDate.parse(imagenesSolicitudModel.getFecha());

				Soporte soporte = new Soporte();

				soporte.setFecha(date);
				soporte.setHora(imagenesSolicitudModel.getHora());
				soporte.setLatitud(imagenesSolicitudModel.getLatitud());
				soporte.setLongitud(imagenesSolicitudModel.getLongitud());
				soporte.setCodImagen(imagenesSolicitudModel.getCodImagen());

				if (cultivo != null)
					soporte.setCultivo(cultivo);

				soporte.setPath(path);
				soporte.setTipoSoporte("1");
				soporte.setUnidadProductiva(unidadProductiva);
				soporte.setUsuarioCreacion(docUsuario);

				soporte.setFechaCreacion(new Date());

				iSoporte.crearSoporte(soporte);

			}
			
		}
		
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  guardarImagenes , fin " + unidadProductiva.getSolicitud().getCodigoSolicitud());
	}catch (  Exception  e) {		
		Logger.getLogger(SincronizarImpl.class.getName()).log(Level.ERROR,
					"No se puede guardar las imagenes de la planificación", e);
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  guardarImagenes , Error " + unidadProductiva.getSolicitud().getCodigoSolicitud());
		e.printStackTrace();
		throw new NegocioException("No se puede guardar las imagenes ");
			}	
		
}

	@Override
	public void crearCultivos(UnidadProductiva unidadProductiva, UnidadProductivaModel unidadProductivaModel,
			SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception {
		try {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearCultivos , inicio");
		for (CultivoModel cultivoModel : unidadProductivaModel.getListCultivoModel()) {

			Cultivo cultivo = new Cultivo();

			Unidad cultivoUnidad = iUnidad.getUnidad(cultivoModel.getUnidadPlantas());

			cultivo.setUnidad(cultivoUnidad);
			cultivo.setUnidadProductiva(unidadProductiva);
			cultivo.setCiclosProduccionAnio(new BigDecimal(cultivoModel.getCiclosProduccionAnio()));
			cultivo.setComentario(cultivoModel.getComentarios());
			cultivo.setDensidad(new BigDecimal(cultivoModel.getDensidad()));
			cultivo.setLugarVenta(cultivoModel.getLugarVenta());
			cultivo.setMesesCosecha(cultivoModel.getMesesCosecha());
			cultivo.setProducto(cultivoModel.getProducto());
			cultivo.setVariedad(cultivoModel.getVariedad());
			cultivo.setCultivoAlterna(cultivoModel.getCultivoAlterna());
			
			DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaSiembra = formatDate.parse(cultivoModel.getFechaSiembra());

			cultivo.setFechaSiembra(fechaSiembra);

			cultivo.setAreaProductiva(new BigDecimal(cultivoModel.getAreaProductiva()));
			cultivo.setAreaProductivaHa(new BigDecimal(cultivoModel.getAreaProductivaHa()));

			Unidad areaProductivaUnidad = new Unidad();
			areaProductivaUnidad.setIdUnidad(cultivoModel.getAreaProductivaUnidad());

			cultivo.setAreaProductivaUnidad(areaProductivaUnidad);

			cultivo.setRotaCultivo(cultivoModel.isRotaCultivo() ? "S" : "N");
			cultivo.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
			cultivo.setFechaCreacion(new Date());
			cultivo.setAsistenciaTecnica(cultivoModel.getAsistenciaTecnica());
			
			// TODO: Agregar campos de cultivos

			cultivo = iCultivo.crearCultivo(cultivo);

			guardarImagenes(unidadProductiva, cultivo, cultivoModel.getListImagenesSolicitudModel(),
					sincronizarModel.getSolicitudModel().getNombrePlanificador(),
					sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		}
	
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearCultivos , fin");
		
	  }catch(Exception e) {
		  Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearCultivos , Error "+ unidadProductiva.getIdUnidadProductiva() );
		  e.printStackTrace();
	  		throw new Exception("Error al crear los cultivos", e);
	  }
		
	}

	@Override
	public void crearActividadPecuaria(UnidadProductiva unidadProductiva, UnidadProductivaModel unidadProductivaModel,
			SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception {
		// TODO Auto-generated method stub
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadPecuaria , inicio");
		try {
		if (unidadProductivaModel.getActividadPorcicolaModel() != null) {

			crearActividadPorcicola(unidadProductiva, unidadProductivaModel, sincronizarModel);

		}

		if (unidadProductivaModel.getActividadGanaderaModel() != null) {

			crearActividadGanadera(unidadProductiva, unidadProductivaModel, sincronizarModel);
		}

		if (unidadProductivaModel.getActividadAvicolaModel() != null) {

			crearActividadAvicola(unidadProductiva, unidadProductivaModel, sincronizarModel);
		}

		if (unidadProductivaModel.getActividadPiscicolaModel() != null) {

			creaActividadPiscicola(unidadProductiva, unidadProductivaModel, sincronizarModel);
		}
		
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadPecuaria , fin");
	}catch(Exception e) {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadPecuaria , Error "+ unidadProductiva.getIdUnidadProductiva() );
		e.printStackTrace();
  		throw new Exception("Error al crear la actividad pecuaria", e);
	}
		
	}

	@Override
	public void crearActividadPorcicola(UnidadProductiva unidadProductiva, UnidadProductivaModel unidadProductivaModel,
			SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadPorcicola , inicio");
		try {
		
		ActividadPorcicola actividadPorcicola = new ActividadPorcicola();

		actividadPorcicola.setUnidadProductiva(unidadProductiva);
		actividadPorcicola.setCiclosProducccionAnio(
				new BigDecimal(unidadProductivaModel.getActividadPorcicolaModel().getCiclosProduccionAnio()));
		actividadPorcicola
				.setEquiposDisponibles(unidadProductivaModel.getActividadPorcicolaModel().getEquiposDisponibles());
		actividadPorcicola.setInfraestructura(unidadProductivaModel.getActividadPorcicolaModel().getInfraestructura());
		actividadPorcicola.setLineaGenetica(unidadProductivaModel.getActividadPorcicolaModel().getLineaGenetica());
		actividadPorcicola.setLugarVenta(unidadProductivaModel.getActividadPorcicolaModel().getLugarVenta());
		actividadPorcicola.setManejoResiduos(unidadProductivaModel.getActividadPorcicolaModel().getManejoResiduos());
		actividadPorcicola
				.setTipoTransportePropio(unidadProductivaModel.getActividadPorcicolaModel().getTipoTransportePropio());
		actividadPorcicola.setTransporteAlquilado(
				unidadProductivaModel.getActividadPorcicolaModel().isTransporteAlquilado() ? "S" : "N");
		actividadPorcicola.setTransportePropio(
				unidadProductivaModel.getActividadPorcicolaModel().isTransportePropio() ? "S" : "N");
		actividadPorcicola.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		actividadPorcicola.setFechaCreacion(new Date());
		actividadPorcicola.setAreaActividad(
				new BigDecimal(unidadProductivaModel.getActividadPorcicolaModel().getAreaActividadM()));
		
		actividadPorcicola.setComentarios(unidadProductivaModel.getActividadPorcicolaModel().getComentarios());
		actividadPorcicola.setAsistenciaTecnica(unidadProductivaModel.getActividadPorcicolaModel().getAsistenciaTecnica());

		// TODO: Agregar campos de actividad porcicola

		iActividadPorcicola.crearActividadPorcicola(actividadPorcicola);

		guardarImagenes(unidadProductiva, null,
				unidadProductivaModel.getActividadPorcicolaModel().getListImagenesSolicitudModel(),
				sincronizarModel.getSolicitudModel().getNombrePlanificador(),
				sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadPorcicola , fin");
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadPorcicola , Error "+ unidadProductiva.getIdUnidadProductiva() );
			e.printStackTrace();
      		throw new Exception("Error al crear la actividad porcicola", e);
		}
		
	}

	@Override
	public void crearActividadGanadera(UnidadProductiva unidadProductiva, UnidadProductivaModel unidadProductivaModel,
			SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadGanadera , inicio");
		
		try {
		ActividadGanadera actividadGanadera = new ActividadGanadera();

		actividadGanadera.setUnidadProductiva(unidadProductiva);

		actividadGanadera.setCantidadPotreros(
				new BigDecimal(unidadProductivaModel.getActividadGanaderaModel().getCantidadPotreros()));
		actividadGanadera.setCiclosProducccionAnio(
				new BigDecimal(unidadProductivaModel.getActividadGanaderaModel().getCiclosProduccionAnio()));
		actividadGanadera
				.setEquiposDisponibles(unidadProductivaModel.getActividadGanaderaModel().getEquiposDisponibles());
		actividadGanadera.setInfraestructura(unidadProductivaModel.getActividadGanaderaModel().getInfraestructura());
		actividadGanadera.setLugarVenta(unidadProductivaModel.getActividadGanaderaModel().getLugarVenta());
		actividadGanadera.setRazaCruce(unidadProductivaModel.getActividadGanaderaModel().getRazaCruce());
		actividadGanadera.setTipoPradera(unidadProductivaModel.getActividadGanaderaModel().getTipoPradera());
		actividadGanadera
				.setTipoTransportePropio(unidadProductivaModel.getActividadGanaderaModel().getTipoTransportePropio());
		actividadGanadera.setTransporteAlquilado(
				unidadProductivaModel.getActividadGanaderaModel().isTransporteAlquilado() ? "S" : "N");
		actividadGanadera.setTransportePropio(
				unidadProductivaModel.getActividadGanaderaModel().isTransportePropio() ? "S" : "N");
		actividadGanadera.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		actividadGanadera.setFechaCreacion(new Date());

		actividadGanadera.setAreaPraderaMejorada(
				new BigDecimal(unidadProductivaModel.getActividadGanaderaModel().getAreaPraderaMejorada()));

		actividadGanadera.setAreaPraderaMejoradaHa(
				new BigDecimal(unidadProductivaModel.getActividadGanaderaModel().getAreaPraderaMejoradaHa()));

		actividadGanadera
				.setAreaActividad(new BigDecimal(unidadProductivaModel.getActividadGanaderaModel().getAreaActividad()));

		actividadGanadera.setAreaActividadHa(
				new BigDecimal(unidadProductivaModel.getActividadGanaderaModel().getAreaActividadHa()));

		Unidad areaPraderaMejoradaUnidad = new Unidad();
		areaPraderaMejoradaUnidad
				.setIdUnidad(unidadProductivaModel.getActividadGanaderaModel().getAreaPraderaMejoradaUnidad());

		Unidad areaActividadUnidad = new Unidad();
		areaActividadUnidad.setIdUnidad(unidadProductivaModel.getActividadGanaderaModel().getAreaActividadUnidad());

		actividadGanadera.setAreaPraderaMejoradaUnidad(areaPraderaMejoradaUnidad);
		actividadGanadera.setAreaActividadUnidad(areaActividadUnidad);
		
		actividadGanadera.setComentarios(unidadProductivaModel.getActividadGanaderaModel().getComentarios());
		actividadGanadera.setAsistenciaTecnica(unidadProductivaModel.getActividadGanaderaModel().getAsistenciaTecnica());

		// TODO: Agregar campos de actividad ganadera

		iActividadGanadera.crearActividadGanadera(actividadGanadera);

		//

		guardarImagenes(unidadProductiva, null,
				unidadProductivaModel.getActividadGanaderaModel().getListImagenesSolicitudModel(),
				sincronizarModel.getSolicitudModel().getNombrePlanificador(),
				sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadGanadera , fin");
		}catch(Exception e) {
			 Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadGanadera , Error "+ unidadProductiva.getIdUnidadProductiva() );
			 e.printStackTrace();
	      		throw new Exception("Error al crear la actividad ganadera", e);
		}
		
	}

	@Override
	public void crearActividadAvicola(UnidadProductiva unidadProductiva, UnidadProductivaModel unidadProductivaModel,
			SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception {
			try {
		ActividadAvicola actividadAvicola = new ActividadAvicola();
		actividadAvicola.setLineaGenetica((unidadProductivaModel.getActividadAvicolaModel().getLineaGenetica()));
		actividadAvicola.setAreaGalponesEngorde(
				new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getAreaGalponesEngorde()));
		actividadAvicola.setAreaGalponesLevante(
				new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getAreaGalponesLevante()));
		actividadAvicola.setCiclosProducccionAnio(
				unidadProductivaModel.getActividadAvicolaModel().getCiclosProduccionAnio().toString());
		actividadAvicola
				.setDistancia(new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getDistanciaKM()));
		actividadAvicola.setEquipoDisponible(unidadProductivaModel.getActividadAvicolaModel().getEquipoDisponible());
		actividadAvicola.setInfraestructura(unidadProductivaModel.getActividadAvicolaModel().getInfraestructura());
		actividadAvicola.setIntegradoConQuien(unidadProductivaModel.getActividadAvicolaModel().getIntegradoConQuien());
		actividadAvicola.setNumeroAves(new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getNumAvesM2()));
		actividadAvicola.setNumeroGalponesEngorde(
				new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getNumGalponesEngorde()));
		actividadAvicola.setNumeroGalponesLevante(
				new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getNumGalponesLevante()));
		actividadAvicola.setProductorIntegrado(
				unidadProductivaModel.getActividadAvicolaModel().isProductorIntegrado() ? "S" : "N");
		actividadAvicola.setProveedor(unidadProductivaModel.getActividadAvicolaModel().getProveedor());
		actividadAvicola
				.setTipoTransportePropio(unidadProductivaModel.getActividadAvicolaModel().getTipoTransportePropio());
		actividadAvicola.setTransporteAlquilado(
				unidadProductivaModel.getActividadAvicolaModel().isTransporteAlquilado() ? "S" : "N");
		actividadAvicola
				.setTransportePropio(unidadProductivaModel.getActividadAvicolaModel().isTransportePropio() ? "S" : "N");
		actividadAvicola.setUnidadProductiva(unidadProductiva);
		actividadAvicola.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		actividadAvicola.setFechaCreacion(new Date());
		actividadAvicola.setLugarVenta(unidadProductivaModel.getActividadAvicolaModel().getLugarVenta());

		actividadAvicola.setAreaTotalGalpones(
				new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getAreaTotalGalpones()));
		
		actividadAvicola.setComentarios(unidadProductivaModel.getActividadAvicolaModel().getComentarios());
		
		actividadAvicola.setAreaGalponesPostura(new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getAreaGalponesPostura()));
		
		actividadAvicola.setNumGalponesPostura(new BigDecimal(unidadProductivaModel.getActividadAvicolaModel().getNumGalponesPostura()));
		
		actividadAvicola.setAsistenciaTecnica(unidadProductivaModel.getActividadAvicolaModel().getAsistenciaTecnica());

		// TODO: Agregar campos de actividad avicola

		iActividadAvicola.crearActividadAvicola(actividadAvicola);

		//
		guardarImagenes(unidadProductiva, null,
				unidadProductivaModel.getActividadAvicolaModel().getListImagenesSolicitudModel(),
				sincronizarModel.getSolicitudModel().getNombrePlanificador(),
				sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
			}catch(Exception e) {
				 Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  crearActividadAvicola , Error "+ unidadProductiva.getIdUnidadProductiva() );
				 e.printStackTrace();
		      		throw new Exception("Error al crear la actividad avicola", e);
			}

	}

	@Override
	public void creaActividadPiscicola(UnidadProductiva unidadProductiva, UnidadProductivaModel unidadProductivaModel,
			SincronizarModel sincronizarModel)
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception {
          try {
		ActividadPiscicola actividadPiscicola = new ActividadPiscicola();

		actividadPiscicola.setUnidadProductiva(unidadProductiva);
		actividadPiscicola.setAreaProductivaEspejoAgua(
				new BigDecimal(unidadProductivaModel.getActividadPiscicolaModel().getAreaProductivaEspejoAguaM2()));
		actividadPiscicola.setCiclosProducccionAnio(
				new BigDecimal(unidadProductivaModel.getActividadPiscicolaModel().getCiclosProduccionAnio()));
		actividadPiscicola
				.setEquipoDisponible(unidadProductivaModel.getActividadPiscicolaModel().getEquipoDisponible());
		actividadPiscicola
				.setEspecieCultivada(unidadProductivaModel.getActividadPiscicolaModel().getEspecieCultivada());
		actividadPiscicola.setInfraestructura(unidadProductivaModel.getActividadPiscicolaModel().getInfraestructura());
		actividadPiscicola.setNumeroAnimalesEstanque(
				new BigDecimal(unidadProductivaModel.getActividadPiscicolaModel().getNumAnimalesEstanques()));
		actividadPiscicola.setNumeroEstanques(
				new BigDecimal(unidadProductivaModel.getActividadPiscicolaModel().getNumEstanques()));
		actividadPiscicola
				.setProfundidad(new BigDecimal(unidadProductivaModel.getActividadPiscicolaModel().getProfundidadM2()));
		actividadPiscicola.setTipoProduccion(unidadProductivaModel.getActividadPiscicolaModel().getTipoProduccion());
		actividadPiscicola
				.setTipoTransportePropio(unidadProductivaModel.getActividadPiscicolaModel().getTipoTransportePropio());
		actividadPiscicola.setTransporteAlquilado(
				unidadProductivaModel.getActividadPiscicolaModel().isTransporteAlquilado() ? "S" : "N");
		actividadPiscicola.setTransportePropio(
				unidadProductivaModel.getActividadPiscicolaModel().isTransportePropio() ? "S" : "N");
		actividadPiscicola.setUsuarioCreacion(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		actividadPiscicola.setFechaCreacion(new Date());
		actividadPiscicola.setLugarVenta(unidadProductivaModel.getActividadPiscicolaModel().getLugarVenta());
		
		actividadPiscicola.setComentarios(unidadProductivaModel.getActividadPiscicolaModel().getComentarios());

		actividadPiscicola.setAsistenciaTecnica(unidadProductivaModel.getActividadPiscicolaModel().getAsistenciaTecnica());
		
		iActividadPiscicola.crearActividadPiscicola(actividadPiscicola);

		//

		guardarImagenes(unidadProductiva, null,
				unidadProductivaModel.getActividadPiscicolaModel().getListImagenesSolicitudModel(),
				sincronizarModel.getSolicitudModel().getNombrePlanificador(),
				sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
          }catch(Exception e) {
        	  Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  creaActividadPiscicola , Error "+ unidadProductiva.getIdUnidadProductiva() );
        	  e.printStackTrace();
      		throw new Exception("Error al crear la actividad piscicola", e);
          }

	}

	@Override
	public int actualziarSolicitudEstado(Solicitud solicitud, SincronizarModel sincronizarModel) {
	
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  actualziarSolicitudEstado , inicio");
		SolicitudMovimiento movimiento = new SolicitudMovimiento();

		movimiento.setEstadoMovimiento("SIN");
		movimiento.setFechaMovimiento(new Date());
		movimiento.setSolicitud(solicitud);
		movimiento.setUsuarioMovimiento(sincronizarModel.getSolicitudModel().getNumDocumentoPlanificador());
		try {

			SolicitudMovimiento exSM = iSolicitudMovimiento.consultarSolicitudMovimiento(solicitud.getCodigoSolicitud(),
					"SIN");
			if (exSM == null) {
				iSolicitudMovimiento.crearSolicitudMovimiento(movimiento);

				return Constantes.SINCRONIZAR_RESPUESTA_OK;
			} else {
				return Constantes.SINCRONIZAR_SINCRONIZADO;
			}
			
		} catch (NegocioException | javax.ejb.TransactionRolledbackLocalException | SQLException  ex) {
			ex.printStackTrace();
			//System.out.println("Error al consultar el registro de movimiento ");
			return Constantes.SINCRONIZAR_SINCRONIZADO;
		}
		
		
		
	}

	@Override
	public void sincronizarInforamcionRollBack(String codSoliciutd) throws NegocioException , SQLException {
		
		Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarInforamcionRollBack , inicio :" + codSoliciutd);
		
		try {

			Solicitud solicitud = iSolicitud.buscarPorId(codSoliciutd);	
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarInforamcionRollBack , buscarPorId :" + solicitud.getProductoRelacionadoSolicituds().size());

			if (solicitud.getProductoRelacionadoSolicituds() != null) {

				for (ProductoRelacionadoSolicitud productoRelacionadoSolicitud : solicitud
						.getProductoRelacionadoSolicituds()) {
					Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarInforamcionRollBack , eliminacion :" + productoRelacionadoSolicitud.toString());
					iProductoRelacionadoSolicitud.eliminarProductoRelacionadoSolicitud(productoRelacionadoSolicitud);

				}
			}

			if (solicitud.getSolicitudMovimientos() != null) {

				for (SolicitudMovimiento solicitudMovimientos : solicitud.getSolicitudMovimientos()) {

					if (solicitudMovimientos.getEstadoMovimiento().equals("SIN"))
						iSolicitudMovimiento.eliminarSolicitudMovimiento(solicitudMovimientos);

				}
			}

			for (UnidadProductiva unidadProductiva : solicitud.getUnidadProductivas()) {

				// eliminar los soportes
				if (unidadProductiva.getSoportes() != null) {

					for (Soporte soporte : unidadProductiva.getSoportes()) {
						iSoporte.eliminarSoporte(soporte);
					}
				}

				if (unidadProductiva.getActividadPiscicolas() != null) {
					for (ActividadPiscicola actividadPiscicolas : unidadProductiva.getActividadPiscicolas()) {
						iActividadPiscicola.eliminarActividadPiscicola(actividadPiscicolas);
					}
				}

				if (unidadProductiva.getActividadAvicolas() != null) {
					for (ActividadAvicola actividadAvicola : unidadProductiva.getActividadAvicolas()) {
						iActividadAvicola.EliminarActividadAvicola(actividadAvicola);
					}
				}

				if (unidadProductiva.getActividadGanaderas() != null) {
					for (ActividadGanadera actividadGanadera : unidadProductiva.getActividadGanaderas()) {
						iActividadGanadera.eliminarActividadGanadera(actividadGanadera);
					}
				}

				if (unidadProductiva.getActividadPorcicolas() != null) {

					for (ActividadPorcicola actividadPorcicola : unidadProductiva.getActividadPorcicolas()) {
						iActividadPorcicola.eliminarActividadPorcicola(actividadPorcicola);
					}
				}

				if (unidadProductiva.getCultivos() != null) {

					for (Cultivo cultivo : unidadProductiva.getCultivos()) {
						iCultivo.eliminarCultivo(cultivo);
					}
				}

				if (unidadProductiva.getTecnificacionAgricolas() != null) {
					for (TecnificacionAgricola tecnificacionAgricola : unidadProductiva.getTecnificacionAgricolas()) {
						iTecnificacionAgricola.eliminarTecnificacionAgricola(tecnificacionAgricola);
					}
				}

				if (unidadProductiva.getPredios() != null) {
					for (Predio predio : unidadProductiva.getPredios()) {
						iPredio.eliminarPredio(predio);
					}
				}

				if (unidadProductiva.getValorPorcentajes() != null) {
					for (ValorPorcentaje valorPorcentaje : unidadProductiva.getValorPorcentajes()) {
						iValorPorcentaje.eliminarValorPorcentaje(valorPorcentaje);
					}
				}

			}

			solicitud = iSolicitud.buscarPorId(codSoliciutd);

			if (solicitud.getUnidadProductivas() != null) {

				for (UnidadProductiva unidadProductiva : solicitud.getUnidadProductivas()) {
					iUnidadProductiva.eliminarUnidadProductiva(unidadProductiva);
				}
			}

			for (SolicitudMovimiento solicitudMovimiento : solicitud.getSolicitudMovimientos()) {

				if (solicitudMovimiento.getEstadoMovimiento().equals("SIN")) {
					iSolicitudMovimiento.eliminarSolicitudMovimiento(solicitudMovimiento);
				}

			}
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarInforamcionRollBack , fin");
		} catch (SQLException e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Sincronizar Solicitud ,  sincronizarInforamcionRollBack , error al realizar rollback " + codSoliciutd );
			e.printStackTrace();
			throw new SQLException("ERROR AL REALIZAR ROLLBACK DE SINCRONIZACION");
		}

	}

	@Override
	public List<UnidadModel> consultarUnida() throws SQLException {

		List<UnidadModel> listUnidadModel = new ArrayList<UnidadModel>();
		List<Unidad> listUnidad = iUnidad.getUnidades();

		for (Unidad unidad : listUnidad) {

			UnidadModel unidadModel = new UnidadModel();
			unidadModel.setId((int) unidad.getIdUnidad());
			unidadModel.setNombre(unidad.getNombre());
			unidadModel.setFactorHa(unidad.getFactorHa().doubleValue());

			listUnidadModel.add(unidadModel);
		}

		return listUnidadModel;
	}

	@Override
	public List<ParametrosValoresModel> consultarParametrosValores() throws SQLException {

		List<ParametrosValoresModel> listParametrosValoresModel = new ArrayList<>();

		List<ParametroValor> listParametroValor = iParametroValor.listaParametroValor();

		for (ParametroValor parametroValor : listParametroValor) {

			ParametrosValoresModel parametrosValoresModel = new ParametrosValoresModel();
			parametrosValoresModel.setIdParametro((int) parametroValor.getParametro().getIdParametro());
			parametrosValoresModel.setValorParametro(parametroValor.getDescripcion());

			listParametrosValoresModel.add(parametrosValoresModel);
		}

		return listParametrosValoresModel;
	}

	@Override
	public List<ProductoRelacionadoModel> consultarProductoRelacionado() throws SQLException {

		List<ProductoRelacionadoModel> listProductoRelacionadoModel = new ArrayList<>();

		List<ProductoRelacionado> listProductoRelacionado = iProductoRelacionado.getProductosRelacionados();

		for (ProductoRelacionado productoRelacionado : listProductoRelacionado) {
			ProductoRelacionadoModel productoRelacionadoMode = new ProductoRelacionadoModel();
			productoRelacionadoMode.setCodigo(productoRelacionado.getCodigo());
			productoRelacionadoMode.setNombre(productoRelacionado.getNombre());

			listProductoRelacionadoModel.add(productoRelacionadoMode);
		}

		return listProductoRelacionadoModel;
	}

	@Override
	public List<MunicipioDepartamentoModel> consultarMunicipioDepartamento() throws SQLException {
		List<MunicipioDepartamentoModel> listMunicipioDepartamentoModel = new ArrayList<>();

		List<Ciudad> listCiudad = iCiudad.getCiudades();

		for (Ciudad ciudad : listCiudad) {

			MunicipioDepartamentoModel municipioDepartamentoModel = new MunicipioDepartamentoModel();
			municipioDepartamentoModel
					.setMunicipioDepartamento(ciudad.getCiudad() + " - " + ciudad.getDepartamento().getDepartamento());

			try {
				municipioDepartamentoModel.setIdCiudad(Integer.parseInt(ciudad.getIdCiudad().toString()));
				municipioDepartamentoModel
						.setIdDepartamento(Integer.parseInt(ciudad.getDepartamento().getIdDepartamento().toString()));
			} catch (Exception e) {

			}

			listMunicipioDepartamentoModel.add(municipioDepartamentoModel);

		}

		return listMunicipioDepartamentoModel;
	}

}
