package com.servicios.aws;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.util.IOUtils;
import com.servicios.aws.interfaces.IS3Service;

public class ClienteS3Service implements Serializable, IS3Service {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6252068309103240262L;
	private AWSCredentials credenciales;
	private AmazonS3 amazonS3;

	private static final Logger log = Logger.getLogger(ClienteS3Service.class.getSimpleName());
	private List<byte[]> listaArchivos;

	public ClienteS3Service(String keyId, String secretkey, String habilitarAccelerateMode) {
		try {
			if (credenciales == null) {
				credenciales = new BasicAWSCredentials(keyId, secretkey);
			}
			AWSStaticCredentialsProvider staticCredencial = new AWSStaticCredentialsProvider(credenciales);
			if(habilitarAccelerateMode == null || !habilitarAccelerateMode.equals("S")) {
				amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(staticCredencial).withRegion("us-east-1")
						.build();//sa-east-1
				System.out.println("*************AVALUOS***********: Se crea cliente amazonS3 SIN modo accelerate");
			}else {
				amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(staticCredencial).withRegion("us-east-1").enableAccelerateMode()
						.build();//sa-east-1
				System.out.println("*************AVALUOS***********: Se crea cliente amazonS3 con modo accelerate");
			}
			log.info("Conexión S3. " + "ClienteS3Service(). " + "Exitoso. ");
		} catch (Exception e) {
			log.info("Conexión S3. " + "ClienteS3Service(). " + "Falló. " + e.getMessage());
		}
	}

	public void inicializaServicio() {

	}

	public void registrarArchivo(String awsBucket, String llave, InputStream archivo) {
		log.info("Registrar archivo S3. " + "registrarArchivo(). " + "Inicio " + llave + ".");
		ObjectMetadata metadata = new ObjectMetadata();
		long tamanio;
		try {
			tamanio = archivo.available();
			metadata.setContentLength(tamanio);
			PutObjectRequest solicitudDocumento = new PutObjectRequest(awsBucket, llave, archivo, metadata);
			amazonS3.putObject(solicitudDocumento);
			log.info("Registrar archivo S3. " + "registrarArchivo(). " + "Exitoso " + llave + ".");
		} catch (IOException e) {
			log.info("Registrar archivo S3. " + "registrarArchivo(). " + "Falló " + llave + ". " + e.getMessage());
			e.printStackTrace();
		}
	}

	public URL generarURLDescarga(String awsBucket, String objectKey, int minutes, String filename) {
		log.info("Generar URL descarga S3. " + "generarURLDescarga(). " + "Inicio. Objeto " + objectKey + ". Nombre " + filename);
		URL url = null;
		try {
			// Set the presigned URL to expire in minutes.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * minutes;
			expiration.setTime(expTimeMillis);
			log.info("Generar URL descarga S3. generarURLDescarga(). ExpirationTime. Objeto " + objectKey + ". Nombre " + filename);
			
			// Generate the presigned URL with specific filename.
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(awsBucket,
					objectKey).withMethod(HttpMethod.GET).withExpiration(expiration);
			log.info("Generar URL descarga S3. generarURLDescarga(). GeneratePresignedUrlRequest Objeto " + objectKey + ". Nombre " + filename);

			ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
			responseHeaders.setContentDisposition("attachment; filename =\"" + filename + "\"");
			generatePresignedUrlRequest.setResponseHeaders(responseHeaders);
			log.info("Generar URL descarga S3. generarURLDescarga(). End responseHeaders Objeto " + objectKey + ". Nombre " + filename);
			log.info("generatePresignedUrlRequest: " + generatePresignedUrlRequest.toString());
			url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
			log.info("Generar URL descarga S3. " + "generarURLDescarga(). " + "Exitoso " + objectKey + ".");
		} catch (AmazonServiceException  se) {
			log.info("Generar URL descarga S3. " + "generarURLDescarga(). " + "Falló porque Amazon S3 no pudo procesarlo: " + objectKey + ". "
					+ se.getMessage());
			se.printStackTrace();
		} catch (SdkClientException   sdke) {
			log.info("Generar URL descarga S3. " + "generarURLDescarga(). " + "Falló porque no se pudo conectar a Amazon S3 o el cliente no pudo analizar la respuesta: " + objectKey + ". "
					+ sdke.getMessage());
			sdke.printStackTrace();
		}catch (AmazonClientException ce) {
			log.info("Generar URL descarga S3. " + "generarURLDescarga(). " + "Falló por el Cliente" + objectKey + ". "
					+ ce.getMessage());
			ce.printStackTrace();
		}catch (Exception e) {
			log.info("Generar URL descarga S3. " + "generarURLDescarga(). " + "Falló " + objectKey + ". "
					+ e.getMessage());
			e.printStackTrace();
		
		}
		return url;
	}

	public void borrarArchivos(String awsBucket, String llave) throws Exception {
		try {
			amazonS3.deleteObject(awsBucket, llave);
			log.info("Borrar archivo S3. " + "borrarArchivos(). " + "Exitoso " + llave + ".");
		} catch (Exception e) {
			log.info("Borrar archivo S3. " + "borrarArchivos(). " + "Falló " + llave + ". " + e.getMessage());
			e.printStackTrace();
		}
	}

	public byte[] obtenerArchivo(String awsBucket, String nombreArchivo) throws Exception {
		try {
			S3Object object = amazonS3.getObject(new GetObjectRequest(awsBucket, nombreArchivo));
			log.info("Obtener archivo S3. " + "obtenerArchivo(). " + "Exitoso " + nombreArchivo + ".");
			return IOUtils.toByteArray(object.getObjectContent());
		} catch (Exception e) {
			log.info("Obtener archivo S3. " + "obtenerArchivo(). " + "Falló " + nombreArchivo + ". " + e.getMessage());
			return null;
		}
	}

	/*public List<byte[]> listadoObjetosPorBucketandPrefix(String awsBucket, String prefijo) throws Exception {
		try {
			listaArchivos = null;
			
			ObjectListing objectListing = amazonS3
					.listObjects(new ListObjectsRequest().withBucketName(awsBucket).withPrefix(prefijo));
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				log.info(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");	
				S3Object object = amazonS3.getObject(new GetObjectRequest(awsBucket, objectSummary.getKey()));
				listaArchivos.add(IOUtils.toByteArray(object.getObjectContent()));
				
			}
			log.info("Obtener Listado objetos del bucket S3. " + "listadoObjetosPorBucket(). " + "Exitoso. ");
			return listaArchivos;
		} catch (Exception e) {
			log.info("Obtener Listado objetos del bucket S3. " + "listadoObjetosPorBucket(). " + "Falló " + awsBucket
					+ ". " + e.getMessage());
			return null;
		}
	 */
	public List<String> listadoObjetosPorBucketandPrefix(String awsBucket, String prefijo) throws Exception {
		try {
			log.info("Ingresa a listar los archivos prefijo:" + prefijo);	
			List<String> listaArchivos = new ArrayList() ;
			ObjectListing objectListing = amazonS3
					.listObjects(new ListObjectsRequest().withBucketName(awsBucket).withPrefix(prefijo));
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				log.info(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");	
				//S3Object object = amazonS3.getObject(new GetObjectRequest(awsBucket, objectSummary.getKey()));
				listaArchivos.add(objectSummary.getKey());
			}
			log.info("Obtener Listado objetos del bucket S3. " + "listadoObjetosPorBucket(). " + "Exitoso. ");
			return listaArchivos;
		} catch (Exception e) {
			log.info("Obtener Listado objetos del bucket S3. " + "listadoObjetosPorBucket(). " + "Falló " + awsBucket
					+ ". " + e.getMessage());
			return null;
		}
	}
		
	public void listadoObjetosPorBucket(String awsBucket) throws Exception {
		try {
			ObjectListing objectListing = amazonS3
					.listObjects(new ListObjectsRequest().withBucketName(awsBucket).withPrefix("My"));
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				log.info(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");
			}
			log.info("Obtener Listado objetos del bucket S3. " + "listadoObjetosPorBucket(). " + "Exitoso. ");
		} catch (Exception e) {
			log.info("Obtener Listado objetos del bucket S3. " + "listadoObjetosPorBucket(). " + "Falló " + awsBucket
					+ ". " + e.getMessage());
		}
	}

	public void registrarMultiplesArchivos(String awsBucket, List<File> archivos, String rutaDirectorio)
			throws Exception {
		try {
			TransferManager transferencia = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
			MultipleFileUpload multiples = transferencia.uploadFileList(awsBucket, rutaDirectorio, new File("."),
					archivos);
			multiples.waitForCompletion();
			log.info("Registrar Múltiples Archivos S3. " + "registrarMultiplesArchivos(). " + "Exitoso "
					+ rutaDirectorio + ".");
		} catch (Exception e) {
			log.info("Registrar Múltiples Archivos S3. " + "registrarMultiplesArchivos(). " + "Falló " + rutaDirectorio
					+ ". " + e.getMessage());
		}
	}

	public void registrarDirectorio(String awsBucket, String rutaDirectorio, String nombre) throws Exception {
		log.info("Registrar Directorio S3. " + "registrarDirectorio(). " + "Inicio " + rutaDirectorio + ".");
		try {
			TransferManager transferencia = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
			MultipleFileUpload multiples = transferencia.uploadDirectory(awsBucket, nombre, new File(rutaDirectorio),
					true);
			multiples.waitForCompletion();
			log.info("Registrar Directorio S3. " + "registrarDirectorio(). " + "Exitoso " + rutaDirectorio + ".");
		} catch (Exception e) {
			log.info("Registrar Directorio S3. " + "registrarDirectorio(). " + "Falló " + rutaDirectorio + ". "
					+ e.getMessage());
		}
	}

	public void descargarDirectorio(String awsBucket, String rutaArchivoAWS, File rutaDirectorioDdestino)
			throws Exception {
		log.info("Descargar Directorio S3. " + "descargarDirectorio(). " + "Inicio " + rutaArchivoAWS + ".");
		try {
			TransferManager transferencia = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
			MultipleFileDownload download = transferencia.downloadDirectory(awsBucket, rutaArchivoAWS,
					rutaDirectorioDdestino);
			download.waitForCompletion();
			log.info("Descargar Directorio S3. " + "descargarDirectorio(). " + "Exitoso " + rutaArchivoAWS + ".");
		} catch (Exception e) {
			log.info("Descargar Directorio S3. " + "descargarDirectorio(). " + "Falló " + rutaArchivoAWS + ". "
					+ e.getMessage());
		}
	}

	public void eliminarDirectorio(String awsBucket, String llaveDirectorio) throws Exception {
		log.info("Eliminar Directorio S3. " + "eliminarDirectorio(). " + "Inicio " + llaveDirectorio + ".");
		try {
			ObjectListing lista = amazonS3.listObjects(awsBucket, llaveDirectorio);
			if (lista.getObjectSummaries().isEmpty()) {
				log.info("Eliminar Directorio S3. " + "eliminarDirectorio(). " + "Directorio vacío " + llaveDirectorio
						+ ".");
				return;
			}
			List<String> listaLlaves = new ArrayList<String>();
			for (S3ObjectSummary objectSummary : lista.getObjectSummaries()) {
				listaLlaves.add(objectSummary.getKey());
			}
			DeleteObjectsRequest eliminacion = new DeleteObjectsRequest(awsBucket);
			String[] llaves = new String[listaLlaves.size()];
			eliminacion.withKeys(listaLlaves.toArray(llaves));
			amazonS3.deleteObjects(eliminacion);
			log.info("Eliminar Directorio S3. " + "eliminarDirectorio(). " + "Exitoso " + llaveDirectorio + ".");
		} catch (Exception e) {
			log.info("Eliminar Directorio S3. " + "eliminarDirectorio(). " + "Falló " + llaveDirectorio + ". "
					+ e.getMessage());
		}
	}

	public void eliminarListaArchivos(String awsBucket, String llaveDirectorio, List<String> sufijos) throws Exception {
		log.info("Eliminar Lista archivos S3. " + "eliminarListaArchivos(). " + "Inicio " + llaveDirectorio + ".");
		try {
			ObjectListing lista = amazonS3.listObjects(awsBucket, llaveDirectorio);
			if (lista.getObjectSummaries().isEmpty()) {
				log.info("Eliminar Lista archivos S3. " + "eliminarListaArchivos(). " + "Lista vacía " + llaveDirectorio
						+ ".");
				return;
			}
			List<String> listaLlaves = new ArrayList<String>();
			for (S3ObjectSummary objectSummary : lista.getObjectSummaries()) {
				String key = objectSummary.getKey();
				for (String nombre : sufijos) {
					if (key.endsWith(nombre))
						listaLlaves.add(key);
				}
			}
			if (listaLlaves.isEmpty()) {
				log.info("Eliminar Lista archivos S3. " + "eliminarListaArchivos(). " + "Lista vacía " + llaveDirectorio
						+ ".");
				return;
			}
			DeleteObjectsRequest eliminacion = new DeleteObjectsRequest(awsBucket);
			String[] llaves = new String[listaLlaves.size()];
			eliminacion.withKeys(listaLlaves.toArray(llaves));
			amazonS3.deleteObjects(eliminacion);
			log.info("Eliminar Lista archivos S3. " + "eliminarListaArchivos(). " + "Exitoso " + llaveDirectorio + ".");
		} catch (Exception e) {
			log.info("Eliminar Lista archivos S3. " + "eliminarListaArchivos(). " + "Falló " + llaveDirectorio + ". "
					+ e.getMessage());
		}
	}

	public void cargarArchivoFragmentado(String awsBucket, String llave, File file) {
		try {
			long contentLength = file.length();
			long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.
			// Create a list of ETag objects. You retrieve ETags for each object part
			// uploaded,
			// then, after each individual part has been uploaded, pass the list of ETags to
			// the request to complete the upload.
			List<PartETag> partETags = new ArrayList<PartETag>();

			// Initiate the multipart upload.
			InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(awsBucket, llave);
			InitiateMultipartUploadResult initResponse = amazonS3.initiateMultipartUpload(initRequest);
			System.out.println("Ejecution Init " + new Date());

			// Upload the file parts.
			long filePosition = 0;
			for (int i = 1; filePosition < contentLength; i++) {
				// Because the last part could be less than 5 MB, adjust the part size as
				// needed.
				partSize = Math.min(partSize, (contentLength - filePosition));

				// Create the request to upload a part.
				UploadPartRequest uploadRequest = new UploadPartRequest().withBucketName(awsBucket).withKey(llave)
						.withUploadId(initResponse.getUploadId()).withPartNumber(i).withFileOffset(filePosition)
						.withFile(file).withPartSize(partSize);

				// Upload the part and add the response's ETag to our list.
				UploadPartResult uploadResult = amazonS3.uploadPart(uploadRequest);
				partETags.add(uploadResult.getPartETag());

				filePosition += partSize;
			}

			// Complete the multipart upload.
			CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(awsBucket, llave,
					initResponse.getUploadId(), partETags);
			amazonS3.completeMultipartUpload(compRequest);
			System.out.println("Ejecution complete: " + new Date());
			log.info("Cargar archivo fragmentado S3. " + "cargarArchivoFragmentado(). " + "Exitoso " + llave + " - "
					+ file + ".");
		} catch (Exception e) {
			log.info("Cargar archivo fragmentado S3. " + "cargarArchivoFragmentado(). " + "Falló " + llave + " - "
					+ file + ". " + e.getMessage());
		}
	}

	public void cargarArchivoPorUrl(String awsBucket, String llave, InputStream archivo) {
		try {
			// Set the pre-signed URL to expire after one hour.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * 60;
			expiration.setTime(expTimeMillis);
			System.out.println("HTTP response Inicia: " + new Date());

			// Generacion de pre-signed URL.
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(awsBucket, llave)
					.withMethod(HttpMethod.PUT).withExpiration(expiration);
			URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
			// creacion de la conexion y uso para cargar el archivo usando pre-signed URL.
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			OutputStream out = connection.getOutputStream();
			byte[] buf = new byte[1024];
			int i;

			while ((i = archivo.read(buf)) != -1) {
				out.write(buf, 0, i);
			}
			out.close();

			connection.getResponseCode();
			System.out.println("HTTP response code: " + new Date() + " # " + connection.getResponseCode());
			log.info("Cargar archivo por URL S3. " + "cargarArchivoPorUrl(). " + "Exitoso " + llave + ".");
		} catch (Exception e) {
			log.info("Cargar archivo por URL S3. " + "cargarArchivoPorUrl(). " + "Falló " + llave + ". "
					+ e.getMessage());
		}
	}
}
