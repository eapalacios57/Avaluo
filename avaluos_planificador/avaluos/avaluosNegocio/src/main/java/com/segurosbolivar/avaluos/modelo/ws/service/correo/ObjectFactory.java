package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public RespuestaSolicitudDTO createRespuestaSolicitudDTO() {
        return new RespuestaSolicitudDTO();
    }

    public AgrupadoresDTO createAgrupadoresDTO() {
        return new AgrupadoresDTO();
    }

    public ValoresDTO createValoresDTO() {
        return new ValoresDTO();
    }
    public RtaConsultarSolicitudDTO createRtaConsultarSolicitudDTO() {
        return new RtaConsultarSolicitudDTO();
    }

    public SolicitudDTO createSolicitudDTO() {
        return new SolicitudDTO();
    }

}