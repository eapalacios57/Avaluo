package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

public class HandleMessage implements SOAPHandler<SOAPMessageContext> {

    @Override
    public void close(MessageContext arg0) {
        // Metodo vacio	por omision
    }

    @Override
    public boolean handleFault(SOAPMessageContext arg0) {

        String strMessage = "";

        try {
            strMessage = messageToString(arg0.getMessage());
        } catch (SOAPException e) {
            //logger.info("SOAPException: ", e);
            System.out.println(" EXCEPCION  SOAPException: "+e.getMessage());
        } catch (IOException e) {
            //logger.info("IOException: ", e);
            System.out.println(" EXCEPCION IOException: "+e.getMessage());
        }

        //logger.info("MENSAJE WEB SERVICE \n" + type + "\n" + format(strMessage));
        /*
        System.out.println("MENSAJE EXCEPCION WEB SERVICE \n" + arg0 + "\n" + format(strMessage));
        */
        System.out.println("MENSAJE EXCEPCION WEB SERVICE \n" + arg0 + "\n" + strMessage);
        return false;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext arg0) {
        //logger.info("Capturamos los mensajes");
        logMessage(arg0);
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        // Metodo vacio por omision
        return null;
    }
    //Metodos privados encargados de procesar el mensaje
    private void logMessage(SOAPMessageContext mc) {

        boolean isResponse = isResponse(mc);
        String type = isResponse ? "XML PETICION : " : "XML RESPUESTA: ";
        String strMessage = "";


        try {
            strMessage = messageToString(mc.getMessage());
        } catch (SOAPException e) {
            //logger.info("SOAPException: ", e);
            System.out.println("SOAPException: "+e.getMessage());
        } catch (IOException e) {
            //logger.info("IOException: ", e);
            System.out.println("IOException: "+e.getMessage());
        }

        //logger.info("MENSAJE WEB SERVICE \n" + type + "\n" + format(strMessage));
        /*
        String msg=format(strMessage);
        System.out.println("MENSAJE WEB SERVICE \n" + type + "\n" + msg);
        */
        System.out.println("MENSAJE WEB SERVICE \n" + type + "\n" + strMessage);

        /*
        if(!isResponse)
            try
            {

                JAXBContext jc = JAXBContext.newInstance(RecibeSolicitudResult.class);

                Unmarshaller unmarshaller = jc.createUnmarshaller();

                jc = JAXBContext.newInstance(RecibeSolicitudResponse.class);

                unmarshaller = jc.createUnmarshaller();

                RecibeSolicitudResponse customer1 = (RecibeSolicitudResponse)unmarshaller.unmarshal(mc.getMessage().getSOAPBody().getFirstChild());

                int i=1;

                SOAPMessage msg1 = mc.getMessage();
                SOAPBody soapBody = msg1.getSOAPBody();


                Marshaller marshaller = jc.createMarshaller();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                marshaller.marshal(customer1, out);
                String Xml=out.toString("utf-8");
                Xml=Xml.replace("&lt;", "<");
                Xml=Xml.replace("&gt;", ">");
                Xml=Xml.replace("&quot;", "\"");
                Xml=Xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
                Xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><recibeSolicitudResponse xmlns=\"http://microsoft.com/webservices/\">  <recibeSolicitudResult>    <diffGram>      <NewDataSet>        <Table1>          <Operacion>false</Operacion>          <IdSolicitud>0</IdSolicitud>        </Table1>      </NewDataSet>    </diffGram>  </recibeSolicitudResult></recibeSolicitudResponse>";
                Document document = parseXmlFile(Xml);
                System.out.println(format(Xml));


            }catch(Exception e)
            {
                e.printStackTrace();
            }
            */



    }
    private Boolean isResponse(SOAPMessageContext mc) {
        Boolean isReponse = (Boolean) mc
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        return isReponse;
    }
    private String messageToString(final SOAPMessage message)
            throws SOAPException, IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        message.writeTo(out);
        return out.toString(getMessageEncoding(message));
    }

    private String getMessageEncoding(SOAPMessage msg) throws SOAPException {
        String encoding = "utf-8";
        if (msg.getProperty(SOAPMessage.CHARACTER_SET_ENCODING) != null) {
            encoding = msg.getProperty(SOAPMessage.CHARACTER_SET_ENCODING)
                    .toString();
        }
        return encoding;
    }

    /*
    public String format(String unformattedXml) {
        try {
            if(unformattedXml!=null)
                if(!unformattedXml.equals(""))
                {
                    Document document = parseXmlFile(unformattedXml);

                    OutputFormat format = new OutputFormat(document);
                    format.setLineWidth(65);
                    format.setIndenting(true);
                    format.setIndent(2);
                    Writer out = new StringWriter();
                    XMLSerializer serializer = new XMLSerializer(out, format);
                    serializer.serialize(document);

                    return out.toString();
                }
            return unformattedXml;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));


            Document ret = db.parse(is);

            return ret;

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    */
}