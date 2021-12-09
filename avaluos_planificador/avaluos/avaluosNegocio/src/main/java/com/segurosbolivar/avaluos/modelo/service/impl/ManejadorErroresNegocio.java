package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.ManejadorExcepcion;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.exception.RecuperadorNegocio;

@Stateless
@LocalBean
public class ManejadorErroresNegocio implements Serializable {

	private static final long serialVersionUID = -448080435133055972L;

	@EJB
	private RecuperadorNegocio recuperador;

	/**
	 * Permite lanzar una excepcion de negocio unicamente estableciendo el
	 * identificador de la excepcion y de forma opcional el tipo de error. Los
	 * mensajes de error tiene la estructura MSJ_ + identificador del recuperador +
	 * identificador del mensaje.
	 * 
	 * @param id
	 *            identificador del mensaje de la excepcion.
	 * @param tipo
	 *            nivel o clasificacion del error que permitiria determinar el
	 *            tratamiento para el mismo.
	 * @return excepcion con el mensaje especificado.
	 */
	public NegocioException lanzarExcepcion(long id, TipoErrorNegocio tipo) {
		return ManejadorExcepcion.getInstance(recuperador).lanzarExcepcion(id, tipo);
	}

	/**
	 * Permite lanzar una excepcion de negocio estableciendo todas sus propiedades,
	 * desde un detalle que complemente el error hasta parametros que permitan la
	 * personalizacion del mensaje de error. Los mensajes de error tiene la
	 * estructura MSJ_ + identificador del manejador + identificador del mensaje.
	 * 
	 * @param id
	 *            identificador del mensaje de la excepcion.
	 * @param tipo
	 *            nivel o clasificacion del error que permitiria determinar el
	 *            tratamiento para el mismo.
	 * @param detalle
	 *            descripcion extendida del error presentado.
	 * @param parametros
	 *            parametros para la personalizacion del mensaje de error si este lo
	 *            permite.
	 * @return
	 */
	public NegocioException lanzarExcepcion(long id, TipoErrorNegocio tipo, String detalle, String[] parametros) {
		return ManejadorExcepcion.getInstance(recuperador).lanzarExcepcion(id, tipo, detalle, parametros);
	}

	public NegocioException lanzarExcepcion(long id, TipoErrorNegocio tipo, String detalle, String[] parametros,
			NegocioException anidar) {
		NegocioException retorno = ManejadorExcepcion.getInstance(recuperador).lanzarExcepcion(id, tipo, detalle,
				parametros);
		if (anidar != null)
			retorno.agregarError(anidar);
		return retorno;
	}

}
