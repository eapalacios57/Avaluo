package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
//import com.segurosbolivar.avaluos.modelo.dto.ConsultaFileNetDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;

public class DiligenciamientoImplTest {

    private IArchivo archivo;
    private AvaluoDao avaluoDao;

    private UsuarioDto usuario;

    private Logger log = Logger.getLogger(DiligenciamientoImplTest.class);

    Observaciones observaciones;
    private List<Avaluo> datos;

    @Before
    public void init() {
	try {
	    log.debug("Configurando Test " + DiligenciamientoImplTest.class.getName());
	    generarUsuario();
	    insertData();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @After
    public void finTest() throws Exception {
	log.debug("finalizando Test " + DiligenciamientoImplTest.class.getName());
	eliminarData();
    }

    @Test
    public void ingresarDocumentoFilenet() {
//	try {
//		String ruta = "C:\\anexos.pdf";
//	    //String ruta = "C:\\Users\\Asesoftware\\Documents\\Proyectos\\2017\\Migracion_Portal_Avaluos\\Planeacion\\3105201751853048.pdf";
//	    FileInputStream stream = new FileInputStream(ruta);
//	    String tituloDocumento = "Informe_3105201751853093";
//	    if(usuario.getUsuario()!=null && datos.get(0)!=null) {
//		ConsultaFileNetDto filtro = new ConsultaFileNetDto(tituloDocumento, usuario.getUsuario(), datos.get(0));
//		filtro.crearMetadata();    
//		Map<String, String> atributos = filtro.getParametros();
//		    archivo = new ArchivoImpl();
//		    int resultado = archivo.ingresarDocumento(stream, tituloDocumento + ".pdf", atributos);
//		    Assert.assertEquals(0, resultado);
//	    }
//	    
//	} catch (Exception ex) {
//	    ex.printStackTrace();
//	    log.debug(ex.getMessage());
//	}
    }

    private void insertData() {
	datos = new ArrayList<>();
	for (int i = 0; i < 10; i++) {
	    guardarAvaluo();
	}
    }

    private void eliminarData() {
	for (Avaluo avaluo : datos) {
	    try {
		avaluoDao.eliminar(avaluo);
	    } catch (Exception e) {
		log.debug(e.getMessage());
	    }
	}
    }

    @Test
    public void guardarAvaluo() {
	try {
	    Avaluo avaluo = generarAvaluo();
	    //avaluoFacade.guardar(avaluo, usuario);
	    datos.add(avaluo);
	    Assert.assertNotNull(avaluo);
	} catch (Exception e) {
	    log.debug(e.getMessage());
	}
    }
        
    private void generarUsuario() {
	Usuario user = new Usuario();
	user.setNombres("JASON RINCON");
	user.setCodigo("102234567");
	user.setDocumento("NIT");
	usuario = new UsuarioDto(user);
    }

    private Avaluo generarAvaluo() throws Exception {
	Avaluo entity = new Avaluo();
	entity.setBarrio("cajica");
	entity.setIdCiudad(1580L);
	entity.setIdDepartamento(1L);
	entity.setIdObjetoAvaluo(2L);
	entity.setTipoInforme(1L);
	entity.setLongitudSFBUA("-67.0000000");
	entity.setLatitudSFBUA("-3.0000000");
	entity.setNombreSolicitante("PEPITO PEREZ");
	entity.setIdTipoIdentificacion(22L);
	entity.setDireccionInmueble("finca las acacias");
	entity.setSector(2L);
	entity.setNumeroIdentificacion(ThreadLocalRandom.current().nextLong(111111L, 9999999999L));
	entity.setNombreBanco(UtilConstantes.NOMBRE_ENTIDAD_DAVIVIENDA);
	entity.setFechaAvaluo(new Date());
	String textoFecha = new SimpleDateFormat("ddMMYYY").format(new Date());
	entity.setConsecutivoBanco(new BigInteger(textoFecha + entity.getNumeroIdentificacion()));
	return entity;
    }


}
