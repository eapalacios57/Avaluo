package com.segurosbolivar.avaluos.modelo.service;

import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.HistoricoAvaluoConsultaDTO;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoAvaluo;
import java.io.Serializable;

@Local
public interface IHistoricoAvaluo extends Serializable {

	List<HistoricoAvaluo> consultarAvaluo(HistoricoAvaluoConsultaDTO consulta, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException;

	int cantidadRegistros(HistoricoAvaluoConsultaDTO consulta) throws NegocioException;

}
