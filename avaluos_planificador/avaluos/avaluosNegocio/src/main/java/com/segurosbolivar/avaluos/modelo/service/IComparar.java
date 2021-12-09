package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

/**
 * Establece y expone los servicios para la gestión integral de las operaciones
 * asociadas a la comparacion y verificacion de avalúos. desde su creación, consulta y eliminación, asi como
 * tambien su aprobación, la generación de las multiples copias, la reversión a
 * su estado inicial, la validación, etc.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@Local
public interface IComparar extends Serializable {

    void confirmarAsegurabilidad(Avaluo avaluo, UsuarioDto usuario);

    void comparaAvaluo(Avaluo avaluo, UsuarioDto usuario);

}
