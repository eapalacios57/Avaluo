package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.HistoricoAvaluoConsultaDTO;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoAvaluo;
import com.segurosbolivar.avaluos.modelo.facade.IHistoricoAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.service.IHistoricoAvaluo;

@Stateless
public class HistoricoAvaluoFacadeImpl implements IHistoricoAvaluoFacade {

	private static final long serialVersionUID = 1672223872256435817L;

	@EJB
	private IHistoricoAvaluo historicoAvaluoSvc;

	@Override
	public List<HistoricoAvaluo> consultar(HistoricoAvaluoConsultaDTO consulta, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return historicoAvaluoSvc.consultarAvaluo(consulta, inicio, registroXPagina, campoOrden, sentido);
	}

	@Override
	public int cantidadRegistros(HistoricoAvaluoConsultaDTO consulta) throws NegocioException {
		return historicoAvaluoSvc.cantidadRegistros(consulta);
	}

}
