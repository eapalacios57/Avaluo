package com.segurosbolivar.avaluos.modelo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.HistoricoAvaluoDao;
import com.segurosbolivar.avaluos.modelo.dto.HistoricoAvaluoConsultaDTO;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IHistoricoAvaluo;

@Stateless
public class HistoricoAvaluoImpl implements IHistoricoAvaluo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private HistoricoAvaluoDao historicoAvaluosDao;
	@EJB
	private AvaluoDao avaluoDao;

	@Override
	public List<HistoricoAvaluo> consultarAvaluo(HistoricoAvaluoConsultaDTO consulta, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return  historicoAvaluosDao.getHistoricoAvaluoList(consulta, inicio, registroXPagina,
				campoOrden, sentido);
	}

	@Override
	public int cantidadRegistros(HistoricoAvaluoConsultaDTO consulta) throws NegocioException {
		return  historicoAvaluosDao.cantidadRegistros(consulta);
	}

}
