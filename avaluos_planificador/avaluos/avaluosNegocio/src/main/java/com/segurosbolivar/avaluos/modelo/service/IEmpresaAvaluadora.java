package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;

/**
 * Permite la gesti�n de las empresas avaluadoras asociadas al banco davivienda.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@Local
public interface IEmpresaAvaluadora extends Serializable {

	int cantidadRegistros(EmpresasAvaluos empresasAvaluos);

	List<EmpresasAvaluos> consultar(EmpresasAvaluos empresa, int inicio, int registrosXPagina, String campoOrden,
			SentidoOrientacion sentido);

	EmpresasAvaluos consultarXDocumento(Long numeroDocumento) throws NegocioException;

	void guardar(EmpresasAvaluos empresa, UsuarioDto usuario) throws NegocioException;

	void eliminar(EmpresasAvaluos empresa) throws NegocioException;
}