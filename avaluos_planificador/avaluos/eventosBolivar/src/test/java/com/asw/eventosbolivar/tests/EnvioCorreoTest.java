package com.asw.eventosbolivar.tests;

//import com.asesoftware.util.lang.UtilTexto;
import java.io.File;

//import com.asw.eventosbolivar.common.ws.eventos.SolicitudDTO;
import com.segurosbolivar.mensajeria.utilsws.SolicitudDTO;
import com.segurosbolivar.mensajeria.serviciosws.SolicitudesServices;
import com.asw.eventosbolivar.web.util.UtilPropiedadesWeb;
import com.asw.eventosbolivar.client.PlantillaEventos;
import com.asw.eventosbolivar.client.SolicitudesCliente;
import com.asw.eventosbolivar.client.SolicitudesClienteThread;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author dnino
 */
@RunWith(Arquillian.class)
public class EnvioCorreoTest {

    public static final String DEPLOY = "Prueba";

    //@EJB
    //ClienteMensajeria clienteMensajeria;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive arch = ShrinkWrap.create(JavaArchive.class, DEPLOY + ".jar")
                //.addPackage(ClienteMensajeria.class.getPackage())
                .addPackage(SolicitudesCliente.class.getPackage())
                .addPackage(SolicitudesClienteThread.class.getPackage())
                .addPackage(PlantillaEventos.class.getPackage())
                //.addPackage(AutorizarDesocupacionManagedBean.class.getPackage())
                .addPackage(UtilPropiedadesWeb.class.getPackage())
                .addPackage(SolicitudDTO.class.getPackage())
                .addPackage(SolicitudesServices.class.getPackage())
                //.addPackage(UtilConstantes.class.getPackage())
                //.addPackage(UtilTexto.class.getPackage())
                //.addPackage(HeadHandler.class.getPackage())
                .addAsResource(new FileAsset(new File("src/main/resources/application.properties")), "/application.properties");

        return arch;
    }

    @Before
    public void setUp() {
        //clinteMensajeria.init
        //SolicitudesCliente
        //SolicitudesClienteThread
        //PlantillaEventos
    }

    @Test
    public void EnviarCorreoTest() throws Exception {

        String email = "ldcontreras@asesoftware.com";
        String numDoc = "8908";
        String tipDoc = "CC";
        SolicitudesCliente sc = SolicitudesCliente.solicitudCorreoDesocupaciones(email, numDoc, tipDoc,
                new String[] { "Prueba Avaluos", "123456" });
        SolicitudesClienteThread sct = new SolicitudesClienteThread(sc);
        String respuesta = sct.EnviarCorreo();
        System.out.println(respuesta);
        assert (respuesta!=null);

    }

}
