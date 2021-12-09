package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.AgrupadoresDTO;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.RespuestaSolicitudDTO;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudDTO;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudesServices;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudesServicesService;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.ValoresDTO;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;

public class SolicitudesClienteThread extends Thread {

	private SolicitudesCliente solSC;

	public SolicitudesCliente getSolSC() {
		return solSC;
	}

	public void setSolSC(SolicitudesCliente solSC) {
		this.solSC = solSC;
	}

	private SolicitudesServices solicitudesServicesCliente;
	private SolicitudesServicesService solicitudesServicesServicePort;

	public SolicitudesClienteThread() {
	}

	public SolicitudesClienteThread(SolicitudesCliente solSC) throws MalformedURLException {
		this.solSC = solSC;
		solicitudesServicesServicePort = new SolicitudesServicesService(new URL(this.solSC.getUrlService()));
		solicitudesServicesCliente = solicitudesServicesServicePort.getSolicitudesServices();
	}

	public void iniciarService() throws MalformedURLException {
		solicitudesServicesServicePort = new SolicitudesServicesService(new URL(this.solSC.getUrlService()));
		solicitudesServicesCliente = solicitudesServicesServicePort.getSolicitudesServices();

	}

	@Override
	public void run() {

		try {
			EnviarCorreo();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String EnviarCorreo() throws Exception {
		try {

			if (solicitudesServicesServicePort == null && solicitudesServicesCliente == null) {
				iniciarService();
			}

    		/*impresion tramas XML*/
			BindingProvider bp = (BindingProvider) solicitudesServicesCliente;
			//BindingProvider bp = (BindingProvider) solicitudesServicesServicePort;
			Binding binding = bp.getBinding();
			List handlerList = binding.getHandlerChain();
			if (handlerList == null)
				handlerList = new ArrayList();
			HandleMessage loggingHandler = new HandleMessage();
			handlerList.add(loggingHandler);
			binding.setHandlerChain(handlerList);
			/*fin impresion tramas XML*/

			SolicitudDTO sdto = this.solSC.getSolDTO();
			RespuestaSolicitudDTO resp = solicitudesServicesCliente.recibirSolicitudWS(sdto);
			List<AgrupadoresDTO> la = sdto.getGrupo().getItem();
			String impr = "";
			for (AgrupadoresDTO a : la) {
				impr = impr + "\n agrupador: " + a.getAgrupador() + "...";
				List<ValoresDTO> ld = a.getDatos().getItem();
				for (ValoresDTO d : ld) {
					impr = impr + "codDato: " + d.getCodDato() + " - valDato: " + d.getValDato() + "...";
				}
			}
			System.out.println(impr);
			System.out.println("Respuesta evento enviado... " + resp.getIdSolicitud());
			return resp.getCodRespuesta();

		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
