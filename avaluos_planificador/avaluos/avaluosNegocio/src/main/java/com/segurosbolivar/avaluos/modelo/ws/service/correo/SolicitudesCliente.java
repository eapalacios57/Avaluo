package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.PlantillaEventos.AppEvento;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.PlantillaEventos.CamposCorreo;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.PlantillaEventos.CamposEnvio;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.PlantillaEventos.EtiquetasCamposEnum;

@Stateless
public class SolicitudesCliente implements ISolicitudesCliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5708577430867672438L;
	private String urlService;
	private SolicitudDTO solDTO;


	public SolicitudDTO agregarValoresSolicitudesCliente(AppEvento appEvento, List<List<CamposEnvio>> lCamposEnvio,
			@SuppressWarnings("unchecked") List<CamposCorreo>... alCamposCorreo) {
		SolicitudDTO sol = new SolicitudDTO();
		sol.setAplicacion(appEvento.getCodAplicacion());
		sol.setEvento(appEvento.getCodEvento());
		sol.setGrupo(crearArrayAgrupadoresDTO(grupos(lCamposEnvio, alCamposCorreo)));
		return sol;
	}

	@Override
	public SolicitudesCliente vetustezMenor50Anios(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveVetustezMenor50Anios(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.VETUSTEZ_MENOR_50_ANIOS);
	}

	@Override
	public SolicitudesCliente dualMenor8Pisos(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveDualMenor8Pisos(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.DUAL_MENOR_8_PISOS);
	}

	@Override
	public SolicitudesCliente valorComparativo(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveValorComparativo(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.VALOR_COMPARATIVO);
	}

	@Override
	public SolicitudesCliente adobeSuperior3Niveles(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAdobeSuperior3Niveles(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.ADOBE_SUPERIOR_3_NIVELES);
	}

	@Override
	public SolicitudesCliente aprobadoZonaNoAsegurable(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAprobadoZonaNoAsegurable(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.APROBADO_ZONA_NO_ASEGURABLE);
	}

	@Override
	public SolicitudesCliente aprobadoNoConstructor(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAprobadoNoConstructor(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.APROBADO_NO_CONSTRUCTOR);
	}

	@Override
	public SolicitudesCliente aprobadoMenor8Pisos(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAprobadoMenor8Pisos(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.APROBADO_MENOR_8_PISOS);
	}

	@Override
	public SolicitudesCliente aprobadoMamposteria8Niveles(String email, String numDoc, String tipDoc,
			String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAprobadoMamposteria8Niveles(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.APROBADO_MAMPOSTERIA_8_NIVELES);
	}

	@Override
	public SolicitudesCliente aprobadoMatriculaRepetida(String email, String numDoc, String tipDoc,
			String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAprobadoMatriculaRepetida(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.APROBADO_MATRICULA_REPETIDA);
	}

	@Override
	public SolicitudesCliente aprobadoPisosSuperior60Niveles(String email, String numDoc, String tipDoc,
			String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAprobadoPisosSuperior60Niveles(), email, numDoc, tipDoc,
				valCampos, EtiquetasCamposEnum.APROBADO_PISOS_SUPERIOR_60_NIVELES);
	}

	@Override
	public SolicitudesCliente georreferenciacionFuncionando(String email, String numDoc, String tipDoc,
			String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveGeorreferenciacionFuncionando(), email, numDoc, tipDoc,
				valCampos, EtiquetasCamposEnum.GEORREFERENCIACION_FUNCIONANDO);
	}

	@Override
	public SolicitudesCliente georreferenciacionCaido(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveGeorreferenciacionCaido(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.GEORREFERENCIACION_CAIDO);
	}

	@Override
	public SolicitudesCliente asegurabilidadPwaFuncionando(String email, String numDoc, String tipDoc,
			String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAsegurabilidadPwaFuncionando(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.ASEGURABILIDAD_FUNCIONANDO);
	}

	@Override
	public SolicitudesCliente asegurabilidadCaido(String email, String numDoc, String tipDoc, String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveAsegurabilidadCaido(), email, numDoc, tipDoc, valCampos,
				EtiquetasCamposEnum.ASEGURABILIDAD_CAIDO);
	}

	@SuppressWarnings("unchecked")
	private SolicitudesCliente enviarCorreos(AppEvento evento, String email, String numDoc, String tipDoc,
			String[] valCampos, EtiquetasCamposEnum etiquetasCampo) {
		PlantillaEventos pe = new PlantillaEventos();
		List<List<CamposEnvio>> lce = pe.lCamposCabecera(Arrays.asList(email.split(",")), numDoc, tipDoc);
		List<CamposCorreo> lcc = pe.lCamposCorreo(etiquetasCampo, valCampos);
		String urlDelServicio = UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.WSDL_EVENTOS);
		SolicitudDTO dto = agregarValoresSolicitudesCliente(evento, lce, lcc);
		SolicitudesCliente result = new SolicitudesCliente();
		result.setSolDTO(dto);
		result.setUrlService(urlDelServicio);
		return result;
	}

	@Override
	public SolicitudesCliente tareaAsegurabilidadFinalizada(String email, String numDoc, String tipDoc,
			String[] valCampos) {
		return enviarCorreos(new PlantillaEventos().eveTareaAsegurabilidadFinalizada(), email, numDoc, tipDoc,
				valCampos, EtiquetasCamposEnum.TAREA_ASEGURABILIDAD_FINALIZADA);
	}

	private static ArrayOfTns1AgrupadoresDTO crearArrayAgrupadoresDTO(List<AgrupadoresDTO> aDTO) {
		ArrayOfTns1AgrupadoresDTO array = new ArrayOfTns1AgrupadoresDTO();
		for (AgrupadoresDTO a : aDTO) {
			array.getItem().add(a);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	private static List<AgrupadoresDTO> grupos(List<List<CamposEnvio>> lCamposEnvio,
			List<CamposCorreo>... alCamposCorreo) {
		int conAgr = 0;
		List<AgrupadoresDTO> lg = new ArrayList<AgrupadoresDTO>();
		for (List<CamposEnvio> camposEnvioDestinatario : lCamposEnvio) {
			AgrupadoresDTO aCabe = new AgrupadoresDTO();
			aCabe.setAgrupador("" + conAgr++);
			aCabe.setDatos(crearArrayValoresDTO(camposEnvio(camposEnvioDestinatario)));
			lg.add(aCabe);
		}
		for (List<CamposCorreo> lista : alCamposCorreo) {
			List<CamposCorreo> lcc = lista;
			AgrupadoresDTO aCorr = new AgrupadoresDTO();
			aCorr.setAgrupador(conAgr++ + "");
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
		List<ValoresDTO> lv = new ArrayList<>();
		for (CamposEnvio ce : lCamposEnvio) {
			ValoresDTO vDTO = new ValoresDTO();
			vDTO.setCodDato(ce.getCampo());
			vDTO.setValDato(ce.getValor());
			lv.add(vDTO);
		}
		return lv;
	}

	public void setUrlService(String urlService) {
		this.urlService = urlService;
	}

	public String getUrlService() {
		return urlService;
	}

	public void setSolDTO(SolicitudDTO solicitudDTO) {
		this.solDTO = solicitudDTO;
	}

	public SolicitudDTO getSolDTO() {
		return solDTO;
	}
}
