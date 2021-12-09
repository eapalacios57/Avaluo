package com.segurosbolivar.avaluos.modelo.data;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.CategoriaInmueble;
import com.segurosbolivar.avaluos.modelo.cons.SiNo;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad archivo plano. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_ARCHIVO_PLANO a través del uso
 * del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ArchivoPlanoDao extends ManejadorCrud<ArchivoPlano, Long> {

    private static final long serialVersionUID = 7008097438433749336L;

    public ArchivoPlanoDao() {
	super(ArchivoPlano.class);
    }

    @SuppressWarnings("unchecked")
    /**
     * Permite consultar la parametrizacion existente en la tabla de archivo plano
     * identificando los registros que son obligatorios para una tabla y una
     * categoria del inmueble
     * 
     * @param categoriaValor
     *            identificador de la categoria del inmueble ver enum
     *            CategoriaInmueble.
     * @param tabla
     *            de la base de datos poara la cual consultaremos la configuracion
     *            de obligatoriedad de sus campos.
     * @return listado de registros de archivo plano con los campos que estan
     *         configurados como obligatorios para una categoria en especifico.
     */
    public List<ArchivoPlano> buscarObligatoriosCategoria(Long categoriaValor, String tabla) {
	if (categoriaValor == null || UtilTexto.estaVacio(tabla))
	    return Collections.emptyList();
	CategoriaInmueble categoria = CategoriaInmueble.getCategoria(categoriaValor);
	if (categoria == null)
	    return Collections.emptyList();
	Query query = mp.createQuery("SELECT a FROM ArchivoPlano a WHERE a." + categoria.getCampo() + "!=:obligatorio AND a.tablaBd LIKE (:tabla) ORDER BY a.consecutivoPlano");
	query.setParameter("obligatorio", SiNo.NO.getValor());
	query.setParameter("tabla", tabla + "%");
	try {
	    return query.getResultList();
	} catch (Exception e) {
	    return Collections.emptyList();
	}
    }

}
