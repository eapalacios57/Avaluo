package com.segurosbolivar.avaluos.modelo.prueba;

import javax.ejb.Local;
import java.io.Serializable;

@Local
public interface ITomaTiempos extends Serializable{
	void setTiempoInicial(long TiempoInicial);
	long getTomaTiempos();
}
