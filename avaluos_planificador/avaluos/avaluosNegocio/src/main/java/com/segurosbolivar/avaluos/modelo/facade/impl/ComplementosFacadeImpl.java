package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.ComplementosExcel;
import com.segurosbolivar.avaluos.modelo.facade.IComplementosFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IComplementos;

@Stateless
public class ComplementosFacadeImpl implements IComplementosFacade {

	private static final long serialVersionUID = -5338202607507251060L;
	@EJB
	private IComplementos complementosSvc;
	@EJB
	private IArchivo archivoSvc;

	@Override
	public List<ComplementosExcel> consultar(ComplementosExcel consulta, int registroInicio, int cantidadRegistros,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return complementosSvc.consultar(consulta, registroInicio, cantidadRegistros, campoOrden, sentido);
	}

	@Override
	public int cantidadRegistros(ComplementosExcel consulta) throws NegocioException {
		return complementosSvc.cantidadRegistros(consulta);
	}

	@Override
	public void guardar(ComplementosExcel complemento, InputStream archivo, UsuarioDto usuarioDto)
			throws NegocioException {
		complementosSvc.guardar(complemento, archivo, usuarioDto);
	}

	@Override
	public byte[] obtenerImagen(String idDocumento) throws NegocioException, IOException {
		return archivoSvc.obtenerDocumento(idDocumento);
	}

	@Override
	public void eliminar(ComplementosExcel complemento, UsuarioDto usuario) throws NegocioException {
		complementosSvc.eliminar(complemento, usuario);
	}

}
