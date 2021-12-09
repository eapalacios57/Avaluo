package com.segurosbolivar.avaluos.modelo.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ComplementosExcel;

@Local
public interface IComplementos extends Serializable {

	List<ComplementosExcel> consultar(ComplementosExcel consulta, int registroInicio, int cantidadRegistros, String campoOrden,
			SentidoOrientacion sentido) throws NegocioException;

	int cantidadRegistros(ComplementosExcel consulta) throws NegocioException;

	void guardar(ComplementosExcel complementoDTO, InputStream archivo, UsuarioDto usuarioDto) throws NegocioException;

	void eliminar(ComplementosExcel complementoDTO, UsuarioDto usuarioDto) throws NegocioException;

	void enviarNotificacion(Avaluo avaluo, UsuarioDto usuario) throws NegocioException;

}
