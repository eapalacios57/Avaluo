package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;
import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.PlanificadorModel;


/**
 * Se encarga de las operaciones referentes a los archivos del sistema. Desde la
 * creaci�n de la copia de seguridad con los aval�os, a la consulta de los
 * complementos y manuales del sistema.
 * 
 * Tambi�n es usado por los servicios de cargue masivo y procedato, para los
 * archivos comprimidos que deben procesarse en cada uno.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@Local
public interface IPlanificador extends Serializable {

	public List<Planificador> listaPlanificador() throws SQLException;
	public void crearPlanificador(PlanificadorDTO planificador);
	public Planificador buscaPlanificador(PlanificadorPK planificadorPk);	
	public Planificador buscaPorNumDoc(String numeroDocumentoPlanificador) throws SQLException, NegocioException ; 
	public Planificador actualziarTokenDispositivo(PlanificadorModel model) throws SQLException, NegocioException ; 
	public List<Planificador>listaPlanificadorPorRegion(Long idDepartamento) throws SQLException;

}