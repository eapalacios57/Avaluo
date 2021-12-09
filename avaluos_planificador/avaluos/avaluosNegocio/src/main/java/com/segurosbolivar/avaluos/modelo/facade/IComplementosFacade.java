package com.segurosbolivar.avaluos.modelo.facade;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.ComplementosExcel;

@Local
public interface IComplementosFacade extends Serializable {

	List<ComplementosExcel> consultar(ComplementosExcel consulta, int registroInicio, int cantidadRegistros,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException;

	int cantidadRegistros(ComplementosExcel consulta) throws NegocioException;

	void guardar(ComplementosExcel complemento, InputStream archivo, UsuarioDto usuarioDto) throws NegocioException;

	void eliminar(ComplementosExcel complemento, UsuarioDto usuarioDto) throws NegocioException;

	public byte[] obtenerImagen(String idDocumento) throws NegocioException, IOException;

}
