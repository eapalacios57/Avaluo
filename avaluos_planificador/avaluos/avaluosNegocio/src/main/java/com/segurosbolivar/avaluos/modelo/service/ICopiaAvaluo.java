package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

/**
 * Establece y expone los servicios para la realizacion de copias de un avaluo
 * tales como las operaciones de replica, transcripcion y multiple constructor.
 *
 * @author stilaguy
 * @version 1.0
 * @created 16-may-2018 10:30:42 a.m.
 */
@Local
public interface ICopiaAvaluo extends Serializable {

	void replicar(List<Avaluo> avaluoSeleccionado, UsuarioDto usuario)throws NegocioException;;

	void transcribir(Avaluo copia, Avaluo original, UsuarioDto usuario) throws NegocioException;

	void multipleConstructor(List<Avaluo> copias, Avaluo original, Long codigoNombreConstructora,
			String nombreConstructora, UsuarioDto usuario)throws Exception;

	void validarCopia(List<Avaluo> copias, boolean replicacion) throws NegocioException;
	
	void validarCopiaMultiple(List<Avaluo> copias) throws NegocioException;

	NegocioAlertaException consultarAlertasCopia(Avaluo avaluoOriginal, List<Avaluo> copias) throws NegocioException;

}
