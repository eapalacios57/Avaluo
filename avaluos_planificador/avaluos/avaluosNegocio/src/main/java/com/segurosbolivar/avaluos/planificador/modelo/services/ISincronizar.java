package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.TransactionRolledbackLocalException;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.MunicipioDepartamentoModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.ParametrosValoresModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.ProductoRelacionadoModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.UnidadModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.ImagenesSolicitudModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.UnidadProductivaModel;
import java.io.Serializable;

@Local
public interface ISincronizar extends Serializable{ 

	public int sincronizarSolicitud( SincronizarModel sincronizarModel ) throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	Solicitud actualizarSolicitud(Solicitud solicitud,SincronizarModel sincronizarModel) throws SQLException, Exception;
	
	void actualziarMedioComunicacion(SincronizarModel sincronizarModel) throws SQLException;
	
	void crearProductoRelacionadoSolicitud(Solicitud solicitud,SincronizarModel sincronizarModel) throws SQLException; 
	
	void crearUnidadProductiva(Solicitud solicitud,SincronizarModel sincronizarModel) throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception; 
	
	Ciudad consultarCiudad(UnidadProductivaModel unidadProductivaModel) throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException; 
	
	void crearPredios(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException; 
	
	void crearValoresPorcentajeModel(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException;
	
	void crearTecnificacionAgricola(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	void crearCultivos(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	void guardarImagenes(UnidadProductiva unidadProductiva, Cultivo cultivo,
			List<ImagenesSolicitudModel> listImagenes, String nombreUsuario, String docUsuario) throws ParseException, TransactionRolledbackLocalException, NegocioException, Exception;
	
	void crearActividadPecuaria(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	void crearActividadPorcicola(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	void crearActividadGanadera(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	void crearActividadAvicola(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	void creaActividadPiscicola(UnidadProductiva unidadProductiva,UnidadProductivaModel unidadProductivaModel,SincronizarModel sincronizarModel)throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException, Exception;
	
	int actualziarSolicitudEstado(Solicitud solicitud, SincronizarModel sincronizarModel) throws SQLException, TransactionRolledbackLocalException, NegocioException;
	
	void sincronizarInforamcionRollBack(String codSoliciutd) throws Exception;
	
	public List<UnidadModel> consultarUnida() throws SQLException;
	
	public List<ParametrosValoresModel> consultarParametrosValores() throws SQLException;
	
	public List<ProductoRelacionadoModel> consultarProductoRelacionado() throws SQLException;
	
	public List<MunicipioDepartamentoModel> consultarMunicipioDepartamento() throws SQLException;
	
}
