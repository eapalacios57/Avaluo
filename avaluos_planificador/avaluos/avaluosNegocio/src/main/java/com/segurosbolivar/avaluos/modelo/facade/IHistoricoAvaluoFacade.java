package com.segurosbolivar.avaluos.modelo.facade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.HistoricoAvaluoConsultaDTO;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoAvaluo;

@Local
public interface IHistoricoAvaluoFacade extends Serializable {

	List<HistoricoAvaluo> consultar(HistoricoAvaluoConsultaDTO consulta, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException;

	int cantidadRegistros(HistoricoAvaluoConsultaDTO consulta) throws NegocioException;

}
