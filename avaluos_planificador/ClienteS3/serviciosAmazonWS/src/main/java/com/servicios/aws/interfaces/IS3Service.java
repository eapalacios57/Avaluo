package com.servicios.aws.interfaces;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

public interface IS3Service {

    void registrarArchivo(String awsBucket ,String llave, InputStream archivo);
    
    public URL generarURLDescarga(String awsBucket, String objectKey, int minutes, String filename);
	
    void borrarArchivos(String awsBucket, String llave)throws Exception ;
	
	byte[] obtenerArchivo(String awsBucket, String nombreArchivo) throws Exception;
	
	void listadoObjetosPorBucket(String awsBucket)throws Exception;
	
	void registrarMultiplesArchivos(String awsBucket, List<File> archivos, String rutaDirectorio) throws Exception;
	
	void registrarDirectorio(String awsBucket, String rutaDirectorio, String nombre) throws Exception;
	
	void descargarDirectorio(String awsBucket, String rutaArchivoAWS, File rutaDirectorioDdestino) throws Exception;

	void eliminarDirectorio(String awsBucket, String llaveDirectorio)throws Exception;
	
	void eliminarListaArchivos(String awsBucket, String llaveDirectorio, List<String> nombresArchivos)throws Exception;
	
	void cargarArchivoFragmentado(String awsBucket ,String llave,File file) ;
	
	void cargarArchivoPorUrl(String awsBucket ,String llave, InputStream archivo) ;
	
	public List<String> listadoObjetosPorBucketandPrefix(String awsBucket, String prefijo) throws Exception;

}
