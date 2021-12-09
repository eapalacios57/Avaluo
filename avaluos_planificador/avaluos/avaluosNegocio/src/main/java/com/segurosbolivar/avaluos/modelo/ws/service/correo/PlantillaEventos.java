package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlantillaEventos implements Serializable {
	private static final long serialVersionUID = 5709570707476402349L;
	// CODIGO DE LA APLICACION EN EVENTOS
	private static final String COD_APP = "YQ8LA3JNDCFST4H";

	// Abobe vetuztez menor a 50 años
	private static final String COD_VETUSTEZ_MENOR_50_ANIOS = "NRP6U1538DKYSJC";
	private static final String COD_DUAL_MENOR_8_PISOS = "O6W60LSPC4H4ODD";
	private static final String COD_VALOR_COMPARATIVO = "GJ8K0IGK37T5NOI";
	// Abobe superior a 3 niveles.
	private static final String COD_ADOBE_SUPERIOR_3_NIVELES = "JTJT6B2098NWP3J";
	// localizado en zona No Asegurable de acuerdo a los mapas de riesgo de Seguros
	// Bolívar.
	private static final String COD_APROBADO_ZONA_NO_ASEGURABLE = "FK4VTRKVUHPEHLT";
	// Aprobación de avalúo en un edificio NO CONSTRUCTOR.
	private static final String COD_APROBADO_NO_CONSTRUCTOR = "LH9G1NI8E70A1VF";
	// Aprobación de avalúo con sistema estructural dual de concreto menor a 8
	// pisos.
	private static final String COD_APROBADO_MENOR_8_PISOS = "Y19TCBT2M7EGNBW";
	// Avalúo aprobado con mamposteria estructural superior a 8 niveles.
	private static final String COD_APROBADO_MAMPOSTERIA_8_NIVELES = "GHSN1U1C5MWIQRG";
	// Avalúo aprobado con matricula inmobiliaria repetida.
	private static final String COD_APROBADO_MATRICULA_REPETIDA = "SIFPD5VIJDZQ66P";
	// Avalúo aprobado con numero de pisos superior a 60 niveles.
	private static final String COD_APROBADO_PISOS_SUPERIOR_60_NIVELES = "Y19TCBT2M7EGNBW";
	// SERVICIO DE GEORREFERENCIACION DEL PWA FUNCIONANDO.
	private static final String COD_GEORREFERENCIACION_FUNCIONANDO = "ZR7HUYS7Y5DO3FB";
	// SERVICIO DE GEORREFERENCIACION DEL PWA CAIDO.
	private static final String COD_GEORREFERENCIACION_CAIDO = "I8CJAVS5VYW9EPR";
	// SERVICIO DE ASEGURABILIDAD DEL PWA FUNCIONANDO.
	private static final String COD_ASEGURABILIDAD_FUNCIONANDO = "ECND8DPCFX1WANQ";
	// SERVICIO DE ASEGURABILIDAD DEL PWA CAIDO.
	private static final String COD_ASEGURABILIDAD_CAIDO = "LM75ENHITPO9UVF";
	// TAREA PROGRAMADA DE ACTUALIZACIÓN DE CONCEPTO DE ASEGURABILIDAD FINALIZADA.
	private static final String COD_TAREA_ASEGURABILIDAD_FINALIZADA = "HSR68Y5M1358WRY";

	// ERROR DE TAMANIO DE ARRAY DE CAMPOS
	private static final String ERR_CAM = "Los Array de los campos de envio tienen diferente tamanio";

	// ------------------------------------------------------------------------------------------------------------
	// INICIO CONSTANTES PARA CAMPOS DE ENVIO
	// ------------------------------------------------------------------------------------------------------------
	private static final String CAMPO_PARA = "PARA";
	private static final String CAMPO_NUME = "IDENTIFICACION";
	private static final String CAMPO_TIPO = "TIPODOC";
	// ------------------------------------------------------------------------------------------------------------
	// FIN CONSTANTES PARA CAMPOS DE ENVIO
	// ------------------------------------------------------------------------------------------------------------

	public AppEvento eveVetustezMenor50Anios() {
		return new AppEvento(COD_APP, COD_VETUSTEZ_MENOR_50_ANIOS);
	}

	public AppEvento eveDualMenor8Pisos() {
		return new AppEvento(COD_APP, COD_DUAL_MENOR_8_PISOS);
	}

	public AppEvento eveValorComparativo() {
		return new AppEvento(COD_APP, COD_VALOR_COMPARATIVO);
	}

	public AppEvento eveAdobeSuperior3Niveles() {
		return new AppEvento(COD_APP, COD_ADOBE_SUPERIOR_3_NIVELES);
	}

	public AppEvento eveAprobadoZonaNoAsegurable() {
		return new AppEvento(COD_APP, COD_APROBADO_ZONA_NO_ASEGURABLE);
	}

	public AppEvento eveAprobadoNoConstructor() {
		return new AppEvento(COD_APP, COD_APROBADO_NO_CONSTRUCTOR);
	}

	public AppEvento eveAprobadoMenor8Pisos() {
		return new AppEvento(COD_APP, COD_APROBADO_MENOR_8_PISOS);
	}

	public AppEvento eveAprobadoMamposteria8Niveles() {
		return new AppEvento(COD_APP, COD_APROBADO_MAMPOSTERIA_8_NIVELES);
	}

	public AppEvento eveAprobadoMatriculaRepetida() {
		return new AppEvento(COD_APP, COD_APROBADO_MATRICULA_REPETIDA);
	}

	public AppEvento eveAprobadoPisosSuperior60Niveles() {
		return new AppEvento(COD_APP, COD_APROBADO_PISOS_SUPERIOR_60_NIVELES);
	}

	public AppEvento eveGeorreferenciacionFuncionando() {
		return new AppEvento(COD_APP, COD_GEORREFERENCIACION_FUNCIONANDO);
	}

	public AppEvento eveGeorreferenciacionCaido() {
		return new AppEvento(COD_APP, COD_GEORREFERENCIACION_CAIDO);
	}

	public AppEvento eveAsegurabilidadPwaFuncionando() {
		return new AppEvento(COD_APP, COD_ASEGURABILIDAD_FUNCIONANDO);
	}

	public AppEvento eveAsegurabilidadCaido() {
		return new AppEvento(COD_APP, COD_ASEGURABILIDAD_CAIDO);
	}

	public AppEvento eveTareaAsegurabilidadFinalizada() {
		return new AppEvento(COD_APP, COD_TAREA_ASEGURABILIDAD_FINALIZADA);
	}

	// PARAMETROS DEL ENVIO
	public List<CamposCorreo> lCamposCorreo(EtiquetasCamposEnum enumCamposCorreo, String[] valorCamposCorreo)
			throws ArrayIndexOutOfBoundsException {// PRUEBA POR EL MOMENTO
		List<CamposCorreo> lCamposEnvio = new ArrayList<CamposCorreo>();
		String[] nombreCamposCorreo = enumCamposCorreo.getEtiquetaCampos();
		if (nombreCamposCorreo.length != valorCamposCorreo.length)
			throw new ArrayIndexOutOfBoundsException(ERR_CAM);
		for (int i = 0; i < nombreCamposCorreo.length; i++) {
			lCamposEnvio.add(new CamposCorreo(nombreCamposCorreo[i], valorCamposCorreo[i]));
		}
		return lCamposEnvio;
	}

	// PARAMETROS DEL CORREO
	public List<List<CamposEnvio>> lCamposCabecera(List<String> destinatarios, String numDoc, String tipDoc) {
		List<List<CamposEnvio>> retorno = new ArrayList<>();
		for (String destinatario : destinatarios) {
			List<CamposEnvio> lCamposEnvio = new ArrayList<>();
			lCamposEnvio.add(new CamposEnvio(CAMPO_PARA, destinatario));
			lCamposEnvio.add(new CamposEnvio(CAMPO_NUME, numDoc));
			lCamposEnvio.add(new CamposEnvio(CAMPO_TIPO, tipDoc));
			retorno.add(lCamposEnvio);
		}
		return retorno;
	}

	public enum EtiquetasCamposEnum {

		PRUEBA(new String[] { "ASUNTO" }),

		PRUEBA2(new String[] { "ASUNTO", "NOMBRE" }),

		SINIESTROS(new String[] { "P_ARRENDATARIO", "P_SOLICITUD" }),

		VETUSTEZ_MENOR_50_ANIOS(new String[] { "ASUNTO", "P_CONSECUTIVO_AVALUO", "P_ID_PERITO", "P_NOMBRE_PERITO" }),

		DUAL_MENOR_8_PISOS(new String[] { "ASUNTO", "P_CONSECUTIVO_AVALUO", "P_ID_PERITO", "P_NOMBRE_PERITO" }),

		VALOR_COMPARATIVO(new String[] { "ASUNTO", "P_CANTIDAD_AVALUOS", "P_CANTIDAD_METROS", "P_CONSECUTIVO_AVALUO",
				"P_ID_PERITO", "P_NOMBRE_PERITO", "P_RESUMEN", "P_VALOR_METRO_CUADRADO", "P_VALOR_REGISTRADO" }),

		ADOBE_SUPERIOR_3_NIVELES(new String[] { "ASUNTO", "P_CONSECUTIVO_AVALUO", "P_ID_PERITO", "P_NOMBRE_PERITO" }),

		APROBADO_ZONA_NO_ASEGURABLE(
				new String[] { "ASUNTO", "P_CONSECUTIVO_AVALUO", "P_ID_PERITO", "P_NOMBRE_PERITO" }),

		APROBADO_NO_CONSTRUCTOR(new String[] { "ASUNTO", "P_CANTIDAD_AVALUOS", "P_CONSECUTIVO_AVA",
				"P_CONSECUTIVO_AVALUOS", "P_DIRECCION" }),

		APROBADO_MENOR_8_PISOS(new String[] { "ASUNTO", "P_CONSECUTIVO_AVA", "P_DIRECCION", "P_CANTIDAD_AVALUOS",
				"P_CONSECUTIVO_AVALUOS" }),

		APROBADO_MAMPOSTERIA_8_NIVELES(
				new String[] { "ASUNTO", "P_CONSECUTIVO_AVALUO", "P_ID_PERITO", "P_NOMBRE_PERITO" }),

		APROBADO_MATRICULA_REPETIDA(new String[] { "ASUNTO", "P_ANO", "P_CONSECUTIVO_AVA_1", "P_CONSECUTIVO_AVA_2",
				"P_ID_CLIENTE", "P_ID_PERITO", "P_NOMBRE_CLIENTE", "P_NOMBRE_PERITO", "P_NUM_MATRICULA" }),

		APROBADO_PISOS_SUPERIOR_60_NIVELES(
				new String[] { "ASUNTO", "P_CONSECUTIVO_AVALUO", "P_ID_PERITO", "P_NOMBRE_PERITO" }),

		GEORREFERENCIACION_FUNCIONANDO(new String[] { "ASUNTO", "P_FECHA", "P_HORA" }),

		GEORREFERENCIACION_CAIDO(new String[] { "ASUNTO", "P_FECHA", "P_HORA" }),

		ASEGURABILIDAD_FUNCIONANDO(new String[] { "ASUNTO", "P_FECHA", "P_HORA" }),

		ASEGURABILIDAD_CAIDO(new String[] { "ASUNTO", "P_FECHA", "P_HORA" }),

		TAREA_ASEGURABILIDAD_FINALIZADA(new String[] { "ASUNTO", "P_CANTAVALUOS_ACTUALIZ", "P_CANTAVALUOS_PEND" });

		private String[] etiquetaCampos;

		private EtiquetasCamposEnum(String[] etiquetaCampos) {
			this.etiquetaCampos = etiquetaCampos;
		}

		public String[] getEtiquetaCampos() {
			return etiquetaCampos;
		}

	}

	public class AppEvento {
		private String codAplicacion;
		private String codEvento;

		private AppEvento(String codAplicacion, String codEvento) {
			this.codAplicacion = codAplicacion;
			this.codEvento = codEvento;
		}

		public String getCodAplicacion() {
			return codAplicacion;
		}

		public String getCodEvento() {
			return codEvento;
		}
	}

	public class CamposEnvio {
		private String campo;
		private String valor;

		public CamposEnvio(String campo, String valor) {
			this.campo = campo;
			this.valor = valor;
		}

		public String getCampo() {
			return campo;
		}

		public String getValor() {
			return valor;
		}
	}

	public class CamposCorreo {
		private String campo;
		private String valor;
		private byte[] bindato;

		public CamposCorreo(String campo, String valor) {
			this.campo = campo;
			this.valor = valor;
		}

		public CamposCorreo(String campo, String valor, byte[] bindato) {
			this.campo = campo;
			this.valor = valor;
			this.bindato = bindato;
		}

		public String getCampo() {
			return campo;
		}

		public String getValor() {
			return valor;
		}

		public byte[] getBindato() {
			return bindato;
		}
	}

}
