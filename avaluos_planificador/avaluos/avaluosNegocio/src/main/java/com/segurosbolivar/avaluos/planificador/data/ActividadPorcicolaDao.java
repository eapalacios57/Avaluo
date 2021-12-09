package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;

import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;

@Stateless
public class ActividadPorcicolaDao extends ManejadorCrud<ActividadPorcicola, BigDecimal> {

	private static final long serialVersionUID = 7747626177696187846L;

	public ActividadPorcicolaDao() {
		super(ActividadPorcicola.class);
	}

	
}
