package com.asw.eventosbolivar.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlantillaEventos implements Serializable {
	private static final long serialVersionUID = 5709570707476402349L;
	// CODIGO DE LA APLICACION EN EVENTOS
	private static final String COD_APP = "YQ8LA3JNDCFST4H";
	// CODIGO DEL EVENTO EN EVENTOS PARA SINIESTROS
	private static final String COD_SIN = "XSTSEVWQQZWWD9H";
	// CODIGO DEL EVENTO EN EVENTOS PARA DESOCUPACIONES
	private static final String COD_DES = "EE1I0UMHXZV7WLY";
	// Abobe vetuztez menor a 50 años
	private static final String COD_VETUSTEZ_MENOR_50_ANIOS = "EE1I0UMHXZV7WLY";
	// Abobe superior a 3 niveles.
	private static final String COD_ABOBE_SUPERIOR_3_NIVELES = "EE1I0UMHXZV7WLY";
	// localizado en zona No Asegurable de acuerdo a los mapas de riesgo de Seguros Bolívar.
	private static final String COD_APROBADO_ZONA_NO_ASEGURABLE = "EE1I0UMHXZV7WLY";
	// Aprobación de avalúo en un edificio NO CONSTRUCTOR.
	private static final String COD_APROBADO_NO_CONSTRUCTOR = "EE1I0UMHXZV7WLY";
	// Aprobación de avalúo con sistema estructural dual de concreto menor a 8 pisos.
	private static final String COD_APROBADO_MENOR_8_PISOS = "EE1I0UMHXZV7WLY";
	// Avalúo aprobado con mamposteria estructural superior a 8 niveles.
	private static final String COD_APROBADO_MAMPOSTERIA_8_NIVELES = "EE1I0UMHXZV7WLY";
	// Avalúo aprobado con matricula inmobiliaria repetida.
	private static final String COD_APROBADO_MATRICULA_REPETIDA = "EE1I0UMHXZV7WLY";
	// Avalúo aprobado con numero de pisos superior a 60 niveles.
	private static final String COD_APROBADO_PISOS_SUPERIOR_60_NIVELES = "EE1I0UMHXZV7WLY";
	// SERVICIO DE GEORREFERENCIACION DEL PWA FUNCIONANDO.
	private static final String COD_GEORREFERENCIACION_FUNCIONANDO = "EE1I0UMHXZV7WLY";
	// SERVICIO DE GEORREFERENCIACION DEL PWA CAIDO.
	private static final String COD_GEORREFERENCIACION_CAIDO = "EE1I0UMHXZV7WLY";
	// SERVICIO DE ASEGURABILIDAD DEL PWA FUNCIONANDO.
	private static final String COD_ASEGURABILIDAD_FUNCIONANDO = "EE1I0UMHXZV7WLY";
	// SERVICIO DE ASEGURABILIDAD DEL PWA CAIDO.
	private static final String COD_ASEGURABILIDAD_CAIDO = "EE1I0UMHXZV7WLY";
	// TAREA PROGRAMADA DE ACTUALIZACIÓN DE CONCEPTO DE ASEGURABILIDAD FINALIZADA.
	private static final String COD_TAREA_ASEGURABILIDAD_FINALIZADA = "EE1I0UMHXZV7WLY";
	
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

	// para los correos de siniestros
	public AppEvento eveSiniestro() {
		return new AppEvento(COD_APP, COD_SIN);
	}

	// para los correos de desocupacion
	public AppEvento eveDesocupacion() {
		return new AppEvento(COD_APP, COD_DES);
	}
	
	public AppEvento eveVetustezMenor50Anios() {
		return new AppEvento(COD_APP, COD_VETUSTEZ_MENOR_50_ANIOS);
	}
	
	public AppEvento eveAbobeSuperior3Niveles() {
		return new AppEvento(COD_APP, COD_ABOBE_SUPERIOR_3_NIVELES);
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
	public List<CamposCorreo> lCamposCorreo(EtiquetasCamposEnum enumCamposCorreo, String[] valorCamposCorreo) throws ArrayIndexOutOfBoundsException  {// PRUEBA POR EL MOMENTO
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
	public List<CamposEnvio> lCamposCabecera(String email, String numDoc, String tipDoc) {
		List<CamposEnvio> lCamposEnvio = new ArrayList<CamposEnvio>();
		lCamposEnvio.add(new CamposEnvio(CAMPO_PARA, email));
		lCamposEnvio.add(new CamposEnvio(CAMPO_NUME, numDoc));
		lCamposEnvio.add(new CamposEnvio(CAMPO_TIPO, tipDoc));
		return lCamposEnvio;
	}
	
	public enum EtiquetasCamposEnum{
		
		PRUEBA (new String[] {"ASUNTO"}),
		
		PRUEBA2 (new String[] {"ASUNTO", "NOMBRE"}),
        
		SINIESTROS (new String[] {"P_ARRENDATARIO", "P_SOLICITUD"}),

		VETUSTEZ_MENOR_50_ANIOS (new String[] {"P_ID_PERITO", "P_NOMBRE_PERITO", "P_CONSECUTIVO_AVALUO"}),
		
		ABOBE_SUPERIOR_3_NIVELES (new String[] {"P_ID_PERITO", "P_NOMBRE_PERITO", "P_CONSECUTIVO_AVALUO"}),
	
		APROBADO_ZONA_NO_ASEGURABLE (new String[] {"P_ID_PERITO", "P_NOMBRE_PERITO", "P_CONSECUTIVO_AVALUO"}),
	
		APROBADO_NO_CONSTRUCTOR (new String[] {"P_CONSECUTIVO_AVA", "P_DIRECCION", "P_CANTIDAD_AVALUOS", "P_CONSECUTIVO_AVALUOS"}),
	
		APROBADO_MENOR_8_PISOS (new String[] {"P_CONSECUTIVO_AVA", "P_DIRECCION", "P_CANTIDAD_AVALUOS", "P_CONSECUTIVO_AVALUOS"}),
	
		APROBADO_MAMPOSTERIA_8_NIVELES (new String[] {"P_CONSECUTIVO_AVA", "P_DIRECCION", "P_CANTIDAD_AVALUOS", "P_CONSECUTIVO_AVALUOS"}),
	
		APROBADO_MATRICULA_REPETIDA (new String[] {"P_ID_PERITO", "P_NOMBRE_PERITO", "P_CONSECUTIVO_AVA_1", "P_NUM_MATRICULA", "P_CONSECUTIVO_AVA_2", 
				"P_ID_CLIENTE", "P_NOMBRE_CLIENTE", "P_ANO"}),
	
		APROBADO_PISOS_SUPERIOR_60_NIVELES (new String[] {"P_CONSECUTIVO_AVA", "P_DIRECCION", "P_CANTIDAD_AVALUOS", "P_CONSECUTIVO_AVALUOS"}),
	
		GEORREFERENCIACION_FUNCIONANDO (new String[] {"P_HORA", "P_FECHA"}),
	
		GEORREFERENCIACION_CAIDO (new String[] {"P_HORA", "P_FECHA"}),
	
		ASEGURABILIDAD_FUNCIONANDO (new String[] {"P_HORA", "P_FECHA"}),
	
		ASEGURABILIDAD_CAIDO (new String[] {"P_HORA", "P_FECHA"}),
	
		TAREA_ASEGURABILIDAD_FINALIZADA (new String[] {"P_ARRENDATARIO", "P_SOLICITUD"});
		
		private String[] etiquetaCampos;
		
		private EtiquetasCamposEnum(String[] etiquetaCampos){
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
