package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.BindingProvider;

//import org.apache.log4j.Logger;

public class CertificadoUtil {
	
//    private static final Logger log = Logger.getLogger(HeaderHandleWsseSecurity.class);
	
	/*private static ResourceBundle bundle;
	static {
		try {
			bundle = UtilPropiedades.leerProperties(UtilConstantes.RUTA_PROPERTIES);
		} catch (NegocioException e) {
			log.error("No se ha podido leer el archivo de propiedades", e);
		}
	}*/
	
	
	public static void adjuntarCertificado(Object webServicePort, String rutaAlmacenCertificados, String passwordAlmacenCertificados,String passwordLlavero) throws Exception {
//		final java.lang.String JAXWS_HOSTNAME_VERIFIER ="com.sun.xml.internal.ws.transport.https.client.hostname.verifier";
		final java.lang.String JAXWS_SSL_SOCKET_FACTORY = "com.sun.xml.internal.ws.transport.https.client.SSLSocketFactory";
		
	    if (webServicePort instanceof BindingProvider) {
	      BindingProvider bp = (BindingProvider) webServicePort;
	      Map requestContext = bp.getRequestContext();
	      requestContext.put(JAXWS_SSL_SOCKET_FACTORY,getFactory(rutaAlmacenCertificados,passwordAlmacenCertificados,passwordLlavero));
//	      requestContext.put(JAXWS_HOSTNAME_VERIFIER,new NaiveHostnameVerifier());
	    } else {
	      throw new IllegalArgumentException(
	          "Web service port "
	              + webServicePort.getClass().getName()
	              + " does not implement "
	              + BindingProvider.class.getName());
	    }
	}
	
	private static SSLSocketFactory getFactory(String rutaAlmacenCertificados, String passwordAlmacenCertificados, String passwordLlavero) throws Exception{
	    String KEY_MANAGER_ALGORITHM = "SunX509";
//	    String KEY_STORE_FORMAT = "PKCS12";
	    String TRUST_STORE_FORMAT = "JKS";
	    String TRUST_MANAGER_ALGORITHM="PKIX";
	    String SSL_CONTEXT_ALGORITHM = "TLSv1.2";//TLSv1.2
	    
        SSLSocketFactory socketFactory = null;

        	KeyManagerFactory keyManagerFactory;
            keyManagerFactory = KeyManagerFactory.getInstance(KEY_MANAGER_ALGORITHM);
            KeyStore keyStore;

            keyStore = KeyStore.getInstance(TRUST_STORE_FORMAT);//KEY_STORE_FORMAT);
            InputStream keyInput = new FileInputStream(new File(rutaAlmacenCertificados));
            //InputStream keyInput = ClienteNotificacionesAseguradorasService.class.getClassLoader().getResourceAsStream("test_ambPruebas_2019_exportedCert.pfx");//new FileInputStream(new File("C:\\Temp\\test_ambPruebas_2019_exportedCert.pfx"));//getClass().getClassLoader().getResourceAsStream("test_ambPruebas_2019_exportedCert.pfx");
System.out.println("************Se va cargar el almacen de certificados "+rutaAlmacenCertificados);
            keyStore.load(keyInput, passwordAlmacenCertificados.toCharArray());
            keyInput.close();
System.out.println("************Se va inicialiar el keyManagerFactory ");            
            keyManagerFactory.init(keyStore, passwordLlavero.toCharArray());// o passwordAlmacenCertificados
System.out.println("************Fin keyManagerFactory ");

//version anterior: con el .pfx en carpeta del .ear
//keyStore = KeyStore.getInstance(TRUST_STORE_FORMAT);//KEY_STORE_FORMAT);
//InputStream keyInput = CertificadoUtil.class.getClassLoader().getResourceAsStream("test_ambPruebas_2019_exportedCert.pfx");//new FileInputStream(new File("C:\\Temp\\test_ambPruebas_2019_exportedCert.pfx"));//getClass().getClassLoader().getResourceAsStream("test_ambPruebas_2019_exportedCert.pfx");
//keyStore.load(keyInput, passwordLlavero.toCharArray());
//keyInput.close();
//System.out.println("************Se va inicialiar el keyManagerFactory "+rutaAlmacenCertificados);            
//keyManagerFactory.init(keyStore, passwordLlavero.toCharArray());// o passwordAlmacenCertificados
//System.out.println("************Fin keyManagerFactory ");



            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TRUST_MANAGER_ALGORITHM);
            KeyStore trustStore;
            trustStore = KeyStore.getInstance(TRUST_STORE_FORMAT);
            //InputStream trustStoreInput = SrvScnGestorFileNetImple.class.getClassLoader().getResourceAsStream("cacerts");
            InputStream trustStoreInput = new FileInputStream(new File(rutaAlmacenCertificados));
            trustStore.load(trustStoreInput, passwordAlmacenCertificados.toCharArray());
            trustStoreInput.close();
            trustManagerFactory.init(trustStore);

            SSLContext context = SSLContext.getInstance(SSL_CONTEXT_ALGORITHM);//"SSL" SSL_CONTEXT_ALGORITHM 
//            SSLContext context = SSLContext.getDefault();
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            socketFactory=context.getSocketFactory();
            
        return socketFactory;
    }
	
	private static class NaiveHostnameVerifier implements HostnameVerifier {
	  @Override
	  public boolean verify(String hostName,
	      SSLSession session) {
	    return true;
	  }
	}
}
