package com.asw.eventosbolivar.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/*
import com.asw.eventosbolivar.common.ws.eventos.AgrupadoresDTO;
import com.asw.eventosbolivar.common.ws.eventos.ArrayOfTns1AgrupadoresDTO;
import com.asw.eventosbolivar.common.ws.eventos.ArrayOfTns1ValoresDTO;
import com.asw.eventosbolivar.common.ws.eventos.SolicitudDTO;
import com.asw.eventosbolivar.common.ws.eventos.ValoresDTO;
*/
import com.segurosbolivar.mensajeria.utilsws.AgrupadoresDTO;
import com.segurosbolivar.mensajeria.serviciosws.ArrayOfTns1AgrupadoresDTO;
import com.segurosbolivar.mensajeria.serviciosws.ArrayOfTns1ValoresDTO;
import com.segurosbolivar.mensajeria.utilsws.SolicitudDTO;
import com.segurosbolivar.mensajeria.utilsws.ValoresDTO;

import com.asw.eventosbolivar.web.util.UtilPropiedadesWeb;
import com.asw.eventosbolivar.client.PlantillaEventos.AppEvento;
import com.asw.eventosbolivar.client.PlantillaEventos.CamposCorreo;
import com.asw.eventosbolivar.client.PlantillaEventos.CamposEnvio;
import com.asw.eventosbolivar.client.PlantillaEventos.EtiquetasCamposEnum;

public class SolicitudesCliente {

	private static SolicitudesCliente solicitudesServicesCliente;

	private String urlService;
	private SolicitudDTO solDTO;

	@SuppressWarnings("unchecked")
	private SolicitudesCliente(AppEvento appEvento, List<CamposEnvio> lCamposEnvio,
			List<CamposCorreo>... alCamposCorreo) {
		Properties propTrans = new UtilPropiedadesWeb().getPropiedades();
		urlService = propTrans.getProperty(UtilPropiedadesWeb.WSDL_EVENTOS);

		SolicitudDTO sol = new SolicitudDTO();
		sol.setAplicacion(appEvento.getCodAplicacion());
		sol.setEvento(appEvento.getCodEvento());
		sol.setGrupo(crearArrayAgrupadoresDTO(grupos(lCamposEnvio, alCamposCorreo)));
		solDTO = sol;
	}

	@SuppressWarnings("unchecked")
	public static SolicitudesCliente solicitudCorreoDesocupaciones(String email, String numDoc, String tipDoc,
		String[] valCampos) throws ArrayIndexOutOfBoundsException  {
		PlantillaEventos pe = new PlantillaEventos();
		AppEvento appe = pe.eveSiniestro();// ESTABLECE LA APLICACION Y EL EVENTO
		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.PRUEBA, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
		return solicitudesServicesCliente;
	}

	@SuppressWarnings("unchecked")
	public static SolicitudesCliente solicitudCorreoSiniestro(String email, String numDoc, String tipDoc,
		String[] valCampos) throws ArrayIndexOutOfBoundsException  {
		PlantillaEventos pe = new PlantillaEventos();
		AppEvento appe = pe.eveSiniestro();// ESTABLECE LA APLICACION Y EL EVENTO
		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.PRUEBA, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
		return solicitudesServicesCliente;
	}
        
	@SuppressWarnings("unchecked")
	public static SolicitudesCliente solicitudCorreoAutorizarOper(String email, String numDoc, String tipDoc,
		String[] valCampos) throws ArrayIndexOutOfBoundsException  {
		PlantillaEventos pe = new PlantillaEventos();
		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.PRUEBA, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
		return solicitudesServicesCliente;
	}
        
        @SuppressWarnings("unchecked")
	public static SolicitudesCliente solicitudCorreoRechazarOper(String email, String numDoc, String tipDoc,
		String[] valCampos) throws ArrayIndexOutOfBoundsException  {
		PlantillaEventos pe = new PlantillaEventos();
		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.PRUEBA, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
		return solicitudesServicesCliente;
	}
        
    @SuppressWarnings("unchecked")
	public SolicitudesCliente vetustezMenor50Anios(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.VETUSTEZ_MENOR_50_ANIOS, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
  	@SuppressWarnings("unchecked")
	public SolicitudesCliente abobeSuperior3Niveles(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.ABOBE_SUPERIOR_3_NIVELES, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente aprobadoZonaNoAsegurable(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.APROBADO_ZONA_NO_ASEGURABLE, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
   	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente aprobadoNoConstructor(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.APROBADO_NO_CONSTRUCTOR, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
  		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente aprobadoMenor8Pisos(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.APROBADO_MENOR_8_PISOS, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente aprobadoMamposteria8Niveles(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.APROBADO_MAMPOSTERIA_8_NIVELES, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente aprobadoMatriculaRepetida(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.APROBADO_MATRICULA_REPETIDA, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente aprobadoPisosSuperior60Niveles(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.APROBADO_PISOS_SUPERIOR_60_NIVELES, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente georreferenciacionFuncionando(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.GEORREFERENCIACION_FUNCIONANDO, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente georreferenciacionCaido(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.GEORREFERENCIACION_CAIDO, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente asegurabilidadPwaFuncionando(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.ASEGURABILIDAD_FUNCIONANDO, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente asegurabilidadCaido(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.ASEGURABILIDAD_CAIDO, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}
    	
   	@SuppressWarnings("unchecked")
	public SolicitudesCliente tareaAsegurabilidadFinalizada(String email, String numDoc, String tipDoc,
		String[] valCampos) {
   		PlantillaEventos pe = new PlantillaEventos();
   		AppEvento appe = pe.eveDesocupacion();// ESTABLECE LA APLICACION Y EL EVENTO
   		List<CamposEnvio> lce = pe.lCamposCabecera(email, numDoc, tipDoc);// ESTABLECE LOS CAMPOS DE ENVÍO
   		List<CamposCorreo> lcc = pe.lCamposCorreo(EtiquetasCamposEnum.TAREA_ASEGURABILIDAD_FINALIZADA, valCampos);// PRUEBA ESTABLECE LOS DEMAS CAMPOS DE CORREO
   		solicitudesServicesCliente = new SolicitudesCliente(appe, lce, lcc);
   		return solicitudesServicesCliente;
   	}

	private static ArrayOfTns1AgrupadoresDTO crearArrayAgrupadoresDTO(List<AgrupadoresDTO> aDTO) {
		ArrayOfTns1AgrupadoresDTO array = new ArrayOfTns1AgrupadoresDTO();
		for (AgrupadoresDTO a : aDTO) {
			array.getItem().add(a);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	private static List<AgrupadoresDTO> grupos(List<CamposEnvio> lCamposEnvio, List<CamposCorreo>... alCamposCorreo) {
		List<AgrupadoresDTO> lg = new ArrayList<AgrupadoresDTO>();

		int conAgr = 0;
		AgrupadoresDTO aCabe = new AgrupadoresDTO();
		aCabe.setAgrupador(conAgr + "");
		aCabe.setDatos(crearArrayValoresDTO(camposEnvio(lCamposEnvio)));
		lg.add(aCabe);

		for (List<CamposCorreo> lista : alCamposCorreo) {
			conAgr++;
			List<CamposCorreo> lcc = lista;
			AgrupadoresDTO aCorr = new AgrupadoresDTO();
			aCorr.setAgrupador(conAgr + "");
			aCorr.setDatos(crearArrayValoresDTO(camposCorreo(lcc)));
			lg.add(aCorr);
		}
		return lg;
	}

	private static ArrayOfTns1ValoresDTO crearArrayValoresDTO(List<ValoresDTO> vDTO) {
		ArrayOfTns1ValoresDTO array = new ArrayOfTns1ValoresDTO();
		for (ValoresDTO v : vDTO) {
			array.getItem().add(v);
		}
		return array;
	}

	// CAMPOS DEL CORREO
	private static List<ValoresDTO> camposCorreo(List<CamposCorreo> lCamposCorreo) {
		List<ValoresDTO> lv = new ArrayList<ValoresDTO>();
		for (CamposCorreo cc : lCamposCorreo) {
			ValoresDTO vDTO = new ValoresDTO();
			vDTO.setCodDato(cc.getCampo());
			vDTO.setValDato(cc.getValor());
			if (cc.getBindato() != null)
				vDTO.setBindato(cc.getBindato());
			lv.add(vDTO);
		}
		return lv;
	}

	// CAMPOS DEL ENVIO
	private static List<ValoresDTO> camposEnvio(List<CamposEnvio> lCamposEnvio) {
		List<ValoresDTO> lv = new ArrayList<ValoresDTO>();
		for (CamposEnvio ce : lCamposEnvio) {
			ValoresDTO vDTO = new ValoresDTO();
			vDTO.setCodDato(ce.getCampo());
			vDTO.setValDato(ce.getValor());
			lv.add(vDTO);
		}
		return lv;
	}

	public String getUrlService() {
		return urlService;
	}

	public SolicitudDTO getSolDTO() {
		return solDTO;
	}
}
