package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad informacion inmueble. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_INFINMUEBLE a través del uso
 * del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class InformacionInmuebleDao extends ManejadorCrud<InformacionInmueble, Long> {

    private static final long serialVersionUID = 1738485234355375028L;
    public static final String SELECT = "SELECT c FROM InformacionInmueble c ";

    public InformacionInmuebleDao() {
	super(InformacionInmueble.class);
    }

    /**
     * Permite consultar la informacion inmueble asociado a un avaluo en especifico.
     * 
     * @param avaluo
     *            id para el cual consultaremos el registro de informacion inmueble
     * @return si tiene asociado el registro devolvera la informacion inmueble para
     *         el avaluo. de lo contrario nulo.
     */
    @SuppressWarnings("rawtypes")
    public InformacionInmueble consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(InformacionInmueble.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (InformacionInmueble) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

    /**
     * Realiza una consulta de los registros de inmuebles por las matriculas
     * inmobiliarias asociadas, con el fin de determinar si existen avaluos con
     * matriculas repetidas.
     * 
     * @param consultar
     *            registro que contiene las matriculas inmobiliarias a verificar
     * @return listado de registros inmobiliarios que tienen asociadas las mismas
     *         matriculas.
     */
    @SuppressWarnings("unchecked")
    public List<InformacionInmueble> consultarMatriculas(InformacionInmueble consultar) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhereConsultaMatriculasRepetidas(consultar)));
	return consulta.getResultList();
    }

    /**
     * genera la cadena WHERE para la consulta de matriculas inmobiliarias para su
     * comparacion.
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhereConsultaMatriculasRepetidas(InformacionInmueble consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaPpal1", consultar.getMatriculaInmobiliariaPpal1(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaPpal2", consultar.getMatriculaInmobiliariaPpal2(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaDeposito1", consultar.getMatriculaInmobiliariaDeposito1(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaDeposito2", consultar.getMatriculaInmobiliariaDeposito2(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaGaraje1", consultar.getMatriculaInmobiliariaGaraje1(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaGaraje2", consultar.getMatriculaInmobiliariaGaraje2(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaGaraje3", consultar.getMatriculaInmobiliariaGaraje3(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaGaraje4", consultar.getMatriculaInmobiliariaGaraje4(), null, tieneCondiciones, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.matriculaInmobiliariaGaraje5", consultar.getMatriculaInmobiliariaGaraje5(), null, tieneCondiciones, false);
	if(tieneCondiciones && consultar.getNumeroIdentificacion()!=null)
	    return  UtilConstantes.SQL_WHERE + " c.avaluo.numeroIdentificacion != '"+consultar.getNumeroIdentificacion()+"' AND ("+ sb.toString()+")";
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

}
