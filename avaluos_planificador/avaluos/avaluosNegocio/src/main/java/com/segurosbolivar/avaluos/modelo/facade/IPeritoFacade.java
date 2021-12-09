package com.segurosbolivar.avaluos.modelo.facade;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;

/**
 * Realizar la abstracci�n entre la capa de presentaci�n y los servicios de modo
 * que desde los controladores de la vista de peritos solo se realice la
 * inyecci�n de esta fachada y se eviten mutiples llamados.
 * 
 * Realiza la invocaci�n de las listas necesarias dentro de la vista, la gesti�n
 * de los peritos y sus empresas avaluadoras.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IPeritoFacade extends Serializable {

	int cantidadRegistros(EmpresasAvaluos empresasAvaluos);

	int cantidadRegistros(PeritosEmpresa peritosEmpresa);

	List<EmpresasAvaluos> consultar(EmpresasAvaluos consulta, int inicio, int registrosXPagina, String campoOrden,
			SentidoOrientacion sentido);

	List<PeritosEmpresa> consultar(PeritosEmpresa consulta, int inicio, int registrosXPagina, String campoOrden,
			SentidoOrientacion sentido);

	void guardarEmpresa(EmpresasAvaluos empresa, UsuarioDto usuario) throws NegocioException;

	void guardarPerito(PeritosEmpresa perito, UsuarioDto usuario) throws NegocioException;

	void desactivarPerito(List<PeritosEmpresa> peritos, UsuarioDto usuario) throws NegocioException;

	void eliminarEmpresa(EmpresasAvaluos empresa) throws NegocioException;

	String buscarNombrePerito(Long valor) throws NegocioException;

	List<Departamento> consultarDepartamentos() throws NegocioException;

	byte[] obtenerImagen(String idDocumento) throws NegocioException, IOException;

}