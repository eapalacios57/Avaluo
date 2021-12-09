package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que se encarga de las operaciones a base de datos de la tabla de
 * a1000500@PVENTAS_PTRON a través del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class TronadorDao extends ManejadorCrud<PeritosEmpresa, BigDecimal> {

    private static final long serialVersionUID = -18939637472116844L;
    private static final String SQL_CONSULTA_UVR = "SELECT max(tc1) from(SELECT  tc1 FROM a1000500@PVENTAS_PTRON"
	//    + " WHERE cod_mon = 7 AND fecha_tipo_cambio = TO_DATE(SYSDATE) UNION SELECT -1 tc1 FROM dual)"; //HABILITAR PARA LOCAL Y TEST
    + " WHERE cod_mon = 7 AND fecha_tipo_cambio = TO_DATE('21-NOV-99') UNION SELECT -1 tc1 FROM dual)"; //HABILITAR PARA PRODUCCION


    public TronadorDao() {
	super(PeritosEmpresa.class);
    }

    /**
     * Permite consultar el valor de la UVR del dia
     * 
     * @return valor de la UVR si el dia no esta parametrizado devolvera el
     *         valor.-1.
     * @throws NegocioException
     *             en caso de que tenga problemas con el dblink o la base de datos.
     */
    public BigDecimal consultaUvr() throws NegocioException {
	try {
	    List<Object[]> lista = mp.doNativeQuery(SQL_CONSULTA_UVR);
	    if (lista == null || lista.isEmpty())
		return BigDecimal.ZERO;
	    Object objarr = lista.get(0);
	    if (objarr instanceof BigDecimal)
		return (BigDecimal) objarr;
	} catch (Exception e) {
	    throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
	}
	return null;
    }
    
    /**
	 * Permite consultar el valor de la UVR del dia para el servicio motor
	 * 
	 * @return valor de la UVR si el dia no esta parametrizado devolvera el null
	 * @throws NegocioException
	 */
	public String consultaUvrMotor() throws NegocioException {

		List<Object[]> lista;
		try {
			lista = mp.doNativeQuery("select PKG_GENERAL_AVALUOS.sf_uvr_pwa_motor() from dual ");
			if (lista == null || lista.isEmpty())
				return null;
		} catch (Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, null," Error consultando el valor de la UVR para la fecha " 
						+ new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		}
		return String.valueOf(lista.get(0));
	}
    

}
