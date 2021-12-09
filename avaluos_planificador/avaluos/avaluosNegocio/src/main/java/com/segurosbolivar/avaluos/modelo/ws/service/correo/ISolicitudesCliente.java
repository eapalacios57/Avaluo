package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import javax.ejb.Local;
import java.io.Serializable;

@Local
public interface ISolicitudesCliente extends Serializable {

	SolicitudesCliente vetustezMenor50Anios(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente adobeSuperior3Niveles(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente aprobadoZonaNoAsegurable(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente aprobadoNoConstructor(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente aprobadoMenor8Pisos(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente aprobadoMamposteria8Niveles(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente aprobadoMatriculaRepetida(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente aprobadoPisosSuperior60Niveles(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente georreferenciacionFuncionando(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente georreferenciacionCaido(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente asegurabilidadPwaFuncionando(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente asegurabilidadCaido(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente tareaAsegurabilidadFinalizada(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente dualMenor8Pisos(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;

	SolicitudesCliente valorComparativo(String email, String numDoc, String tipDoc, String[] valCampos)
			throws ArrayIndexOutOfBoundsException;
}
