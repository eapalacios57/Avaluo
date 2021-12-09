package com.segurosbolivar.avaluos.planificador.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.apache.log4j.Logger;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.google.gson.Gson;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.planificador.modelo.service.impl.IntentoSincronizacion;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;


public class ProcesosSincronizacion implements Serializable {
	public ProcesosSincronizacion() {		
	}
	private static Logger log = Logger.getLogger(IntentoSincronizacion.class.getName());

	//String ruta = "C:\\avaluosOptimizacionSincronizacion\\archivos\\";
	String ruta = UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.RUTA_REPOSITORIO);
	private static final long serialVersionUID = 1L;

	public void guardarSolicitud(SincronizarModel sincronizarModel) {
		FileWriter writer = null;
		String idSolicitud=sincronizarModel.getSolicitudModel().getCodSolicitud();
		Gson gson = new Gson();
		
		String sincronizarM = gson.toJson(sincronizarModel);
		try {
			// write converted json data to a file named "file.json"
			writer = new FileWriter(ruta +idSolicitud  + ".json");
			writer.write(sincronizarM);
			writer.close();
			log.info("ProcesosSincronizacion , guardarSolicitud(), Guardado archivo JSON de la solicitud exitoso");
		} catch (IOException e) {
			e.printStackTrace();
			log.error("ProcesosSincronizacion , guardarSolicitud(), Error al guardar archivo JSON de la solicitud");
		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public SincronizarModel ConsultarSolicitud(String idSolicitud) {
		
		String solicitud = "";
		FileReader fr = null;
		Gson gson = new Gson();
		SincronizarModel sincronizarModel= new SincronizarModel();
		try {
			fr = new FileReader(ruta + idSolicitud + ".json");
			sincronizarModel = (SincronizarModel) gson.fromJson(fr, SincronizarModel.class);
			log.info("ProcesosSincronizacion , ConsultarSolicitud(), Consulta de archivo JSON de la solicitud exitoso");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("ProcesosSincronizacion , ConsultarSolicitud(), Error al consultar archivo JSON de la solicitud");
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return sincronizarModel;
	}
	
	public void eliminarSolicitud (String idSolicitud) {

		File fichero = new File(ruta + idSolicitud + ".json");
		
		if (fichero.delete()) {
			log.info("ProcesosSincronizacion , eliminarSolicitud(),Achivo json eliminado solicitud  " +idSolicitud);
		}else {
			log.info("ProcesosSincronizacion , eliminarSolicitud(),No existe json  de la solicitud  " +idSolicitud);
		}
	}
	
	public List<String> consultarSolicitudes() {
        List<String> listSolicitudes = new ArrayList<String>();
        File f = new File(ruta);
        log.info("ProcesosSincronizacion , consultarSolicitudes(), Cantidad de solicitudes sin sincronizar "+ f.listFiles().length);
        try {
        if(f.exists()) {
        		for (File file : f.listFiles()) {
        			if (file.getName().contains(".json")) {
        				log.info("ProcesosSincronizacion , consultarSolicitudes(), solicitud sin sincronizar "+ file.getName());
        				listSolicitudes.add(file.getName().replace(".json", ""));
        			}
        		}
        }else {
        	log.info("ProcesosSincronizacion , consultarSolicitudes(), No existe el fichero");
        }
     
        }catch(Exception e) {
        	log.info("ProcesosSincronizacion , consultarSolicitudes(), Error al consultar los json");
        	e.printStackTrace(); 
        	return null;
        }
    return listSolicitudes;
}

	
}
