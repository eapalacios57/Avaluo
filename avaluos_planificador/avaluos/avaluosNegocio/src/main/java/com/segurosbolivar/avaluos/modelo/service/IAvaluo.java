package com.segurosbolivar.avaluos.modelo.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.Procedencia;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.AvaluoFechaEstadoDTO;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

/**
 * Establece y expone los servicios para la gestión integral de las operaciones
 * asociadas al avalúo. desde su creación, consulta y eliminación, asi como
 * tambien su aprobación, la generación de las multiples copias, la reversión a
 * su estado inicial, la validación, etc.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@Local
public interface IAvaluo extends Serializable {

	List<Avaluo> aprobar(List<Avaluo> seleccionado, UsuarioDto usuario) throws NegocioException, Exception;

	Avaluo aprobar(Avaluo aprobar, UsuarioDto usuario) throws NegocioException, Exception;

	void eliminar(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException;

	void enviarParaAprobacion(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException;

	void validarRepeticionConsecutivoBanco(Avaluo avaluo) throws NegocioException;

	void guardar(Avaluo avaluo, UsuarioDto usuario, Procedencia procedencia) throws NegocioException;

	void reversar(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException;

	List<Avaluo> consultarAvaluo(ConsultaAvaluoDto consulta, int inicio, int registroXPagina, String campoOrden,
			SentidoOrientacion sentido) throws NegocioException;
	
	List<Avaluo> consultarAvaluoPorMatriculaInmobiliaria(ConsultaAvaluoDto consulta, int inicio, int registroXPagina, String campoOrden,
			SentidoOrientacion sentido) throws NegocioException;

	int cantidadRegistros(ConsultaAvaluoDto consulta) throws NegocioException;

	Avaluo consultarAvaluo(Long idAvaluo) throws NegocioException;

	void impresionErronea(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException;

	void ejecutarJobAvaluo(AvaluoFechaEstadoDTO consulta) throws NegocioException, Exception;

	ArrayList<String> obtenerExtentCentroide(String departamentoDivipola, String ciudadDivipola, String direccion,
			String barrio) throws IOException, Exception;

	void ejecutarJobAsegurabilidad() throws Exception;

	List<ArchivoPlano> consultarCamposObligatoriosCategoria(Long categoria, String tabla) throws NegocioException;

	void eliminar(Avaluo avaluo) throws NegocioException;

}
