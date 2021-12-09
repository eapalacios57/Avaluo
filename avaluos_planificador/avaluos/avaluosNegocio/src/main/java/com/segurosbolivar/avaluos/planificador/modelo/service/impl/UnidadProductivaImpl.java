package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.PredioDao;
import com.segurosbolivar.avaluos.planificador.data.UnidadProductivaDao;
import com.segurosbolivar.avaluos.planificador.data.ValorPorcentajeDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.UnidadProductivaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.UnidadProductivaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IUnidadProductiva;

@Stateless
@Local(IUnidadProductiva.class)
public class UnidadProductivaImpl implements IUnidadProductiva {

	private static final long serialVersionUID = -2088678805488718651L;

	@EJB
	private UnidadProductivaDao unidadProductivaDao;

	@EJB
	private ValorPorcentajeDao valorPorcentajeDao;

	@EJB
	private PredioDao predioDao;

	@Override
	public UnidadProductiva getUnidadProductiva(Long idUnidadProductiva, String codigoSolicitud) throws SQLException {
		return unidadProductivaDao.getUnidadProductiva(idUnidadProductiva, codigoSolicitud);
	}

	@Override
	public UnidadProductiva crearUnidadProductiva(UnidadProductiva unidadProductiva)
			throws SQLException, javax.ejb.TransactionRolledbackLocalException {
		return unidadProductivaDao.crearUnidadProductiva(unidadProductiva);
	}

	@Override
	public List<UnidadProductiva> listarUnidadesProductivas(String codigoSolicitud) throws SQLException {
		return unidadProductivaDao.listarUnidadesProductivas(codigoSolicitud);
	}

	@Override
	public void eliminarUnidadProductiva(UnidadProductiva unidadProductiva)
			throws SQLException, TransactionRolledbackLocalException, NegocioException {
		unidadProductivaDao.eliminar(unidadProductiva);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarUnidadProductiva(UnidadProductivaDTO unidadProductivaDTO) throws NegocioException {
		List<ValorPorcentaje> porcentajes = unidadProductivaDTO.getValorPorcentajes();
		UnidadProductivaFullDTOMapper mapper = Mappers.getMapper(UnidadProductivaFullDTOMapper.class);
		UnidadProductiva unidad = mapper.dto2entitySinRelaciones(unidadProductivaDTO);
		unidadProductivaDao.actualizar(unidad);

		UnidadProductiva upValorProc = new UnidadProductiva();
		upValorProc.setIdUnidadProductiva(unidadProductivaDTO.getIdUnidadProductiva());
		/* Actualizar valor porcentajes */
		valorPorcentajeDao.eliminarValoresPorcentajePorUnidad(unidadProductivaDTO.getIdUnidadProductiva());
		for (ValorPorcentaje valorPorc : porcentajes) {
			valorPorc.setUnidadProductiva(upValorProc);
			valorPorcentajeDao.crear(valorPorc);
		}

		/* Actualizar predios */
		List<Predio> prediosBd = predioDao.buscarPrediosPorUnidadProd(unidadProductivaDTO.getIdUnidadProductiva());
		for (Predio predioBd : prediosBd) {
			for (Predio predio : unidadProductivaDTO.getPredios()) {
				if (predioBd.getIdPredio().equals(predio.getIdPredio())) {
					predioBd.setNombreMatriculaInmobiliaria(predio.getNombreMatriculaInmobiliaria());
					predioBd.setAreaTotal(predio.getAreaTotal());
					predioBd.setVereda(unidadProductivaDTO.getVereda());
					predioBd.setUsuarioTransaccion(unidadProductivaDTO.getUsuarioTransaccion());
					predioBd.setFechaTransaccion(unidadProductivaDTO.getFechaTransaccion());
					predioBd.getIdUnidadPredio().setCultivos(null);
					predioBd.getIdUnidadPredio().setUnidadProductivas(null);
					predioDao.actualizar(predioBd);
				}
			}
		}
	}

}
