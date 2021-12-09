//package com.segurosbolivar.avaluos.modelo.dto;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//import com.asesoftware.util.archivo.UtilPropiedades;
//import com.asesoftware.util.lang.UtilFecha;
//import com.asesoftware.util.lang.UtilTexto;
//import com.segurosbolivar.avaluos.modelo.cons.TipoClasificacionHD;
//import com.segurosbolivar.avaluos.modelo.cons.TipoClasificacionSeguridad;
//import com.segurosbolivar.avaluos.modelo.cons.TipoElaborador;
//import com.segurosbolivar.avaluos.modelo.cons.TipoIdentificacion;
//import com.segurosbolivar.avaluos.modelo.cons.TipoInformeGD;
//import com.segurosbolivar.avaluos.modelo.cons.TipoProducto;
//import com.segurosbolivar.avaluos.modelo.cons.TipoRamo;
//import com.segurosbolivar.avaluos.modelo.entity.IPersona;
//import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.Propiedad;
//import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
//
//public class ConsultaFileNetDto {
//
//	private static ResourceBundle propiedades = UtilConstantes.RSC_ERRORES;
//
//	public static final String TITULO_DOCUMENTO = "DocumentTitle";
//	public static final String RETORNO = "retorno";
//	private static final String NOMBRE_USUARIO = "NombreUsuario";
//	private static final String IDENTIFICADOR_USUARIO = "identificadorUsuario";
//	private static final String NOMBRE_DEL_DOCUMENTO = "NombreDelDocumento";
//	private static final String TIPO_DE_MOVIMIENTO = "TipoMovimiento";
//	public static final String ID_DOCUMENTO = "id";
//	public static final String ID_DOCUMENTO_INGRESAR = "idDocumento";
//
//	private static final String FECHA_EXPEDICION_DOCUMENTO = "FechaexpedicionDocumento";
//	private static final String VIGENCIA_DOCUMENTO = "VigenciaDocumento";
//	private static final String RAMO_PRINCIPAL = "Ramo";
//	private static final String RAMO_SECUNDARIO = "Ramo";
//	private static final String PRODUCTO_PRINCIPAL = "Producto";
//	private static final String PRODUCTO_SECUNDARIO = "Producto";
//	private static final String CLASIFICACION_SEGURIDAD = "ClasificacionSeguridadInformacion";
//	private static final String CLASIFICACION_HABEAS_DATA = "ClasificacionHabeasData";
//	private static final String NUMERO_DE_POLIZA = "NumerodePoliza";
//	private static final String NUMERO_DE_SINIESTRO = "NumeroSiniestro";
//	private static final String INFORME_ELABORADO_POR = "InformeElaboradoPor";
//	private static final String TIPO_INFORME = "TipoInforme";
//	private static final String NUMERO_ID_CLIENTE = "NumeroIdentificacionCliente";
//	private static final String NOMBRE_TITULAR_DOCUMENTO = "NombreTitularDocumento";
//	private static final String TIPO_DOC_ID_CIENTE = "TipoDocumentoIdCliente";
//	private static final String TIPO_DOC_ELABORA = "TipoDocumentoQuienElaboraInforme";
//	private static final String ID_DOC_ELABORA = "IdentificacionQuienElaboraInforme";
//	private static final String NOMBRE_ELABORA = "NombreQuienElaboraInforme";
//	private static final String ID_COMPANIA = "idCompania";
//	private static final String TIPO_DOC_TITULAR = "TipodocumentoTitular";
//	private static final String NUMERO_DOC_TITULAR = "NumeroDocumentoTitular";
//	private static final String TAXONOMIA = "taxonomia";
//	private static final String[] ATRIBUTOS_CONSULTA = { TAXONOMIA, NOMBRE_DEL_DOCUMENTO, TIPO_INFORME,
//			/*
//			 * Cambiar para ambiente de desarrollo NUMERO_DOC_TITULAR
//			 * 
//			 * @arincon
//			 */
//			NUMERO_ID_CLIENTE, CLASIFICACION_SEGURIDAD, ID_COMPANIA, PRODUCTO_SECUNDARIO, TIPO_DE_MOVIMIENTO,
//			CLASIFICACION_HABEAS_DATA, RAMO_SECUNDARIO
//			/*
//			 * Se agrega el campo del titulo del documento para el filtro de consulta
//			 * 
//			 * @arincon
//			 */, TITULO_DOCUMENTO
//			 };
//
//	private static final String[] ATRIBUTOS_CONSULTA_ID = { //NUMERO_DE_POLIZA, 
//			TAXONOMIA, NOMBRE_DEL_DOCUMENTO,
//			TIPO_INFORME, NUMERO_ID_CLIENTE, CLASIFICACION_SEGURIDAD, ID_COMPANIA, PRODUCTO_SECUNDARIO,
//			TIPO_DE_MOVIMIENTO, CLASIFICACION_HABEAS_DATA, RAMO_SECUNDARIO, TITULO_DOCUMENTO, RETORNO };
//
//	private String tituloDocumento;
//	private String identificador;
//	private String nombreUsuario;
//	private String identificadorUsuario;
//	private String nombreDocumento;
//	private String tipoMovimiento;
//	private String tipoDocumentoIdCliente;
//	private String numeroIdentificacionCliente;
//	private String nombreTitularDocumento;
//	private String fechaExpedicionDocumento;
//	private String vigenciaDocumento;
//	private List<String> ramo;
//	private List<String> producto;
//	private String clasificacionSeguridadInformatica;
//	private String clasificacionHabeasData;
//	private String numeroPoliza;
//	private String numeroSiniestro;
//	private String informeElaboradoPor;
//	private String tipoInforme;
//	private String tipoDocumentoQuienElaboraInforme;
//	private String identificacionQuienElaboraInforme;
//	private String nombreQuienElaboraInforme;
//	private String identificacionCompania;
//	private Usuario usuario;
//	private IPersona avaluo;
//	private Map<String, String> parametros;
//
//	public ConsultaFileNetDto() {
//	}
//
//	public ConsultaFileNetDto(String tituloDocumento, String consecutivoAvaluo, Usuario usuario, IPersona avaluo) {
//		this.tituloDocumento = tituloDocumento;
//		this.numeroPoliza = consecutivoAvaluo;
//		this.usuario = usuario;
//		this.avaluo = avaluo;
//		parametros = new HashMap<>();
//	}
//	
//	public ConsultaFileNetDto(String tituloDocumento, String identificador) {
//		this.tituloDocumento = tituloDocumento;
//		this.identificador = identificador;
//		parametros = new HashMap<>();
//	}
//
//	public ConsultaFileNetDto(String tituloDocumento, Usuario usuario, IPersona avaluo) {
//		this.tituloDocumento = tituloDocumento;
//		this.usuario = usuario;
//		this.avaluo = avaluo;
//		parametros = new HashMap<>();
//	}
//
//	public void crearMetadata() {
//		Calendar fechaExpedicionDoc = Calendar.getInstance();
//		String fechaExpedicion = UtilFecha.dateToString("dd-MM-yyyy HH:mm", fechaExpedicionDoc.getTime());
//		fechaExpedicionDoc.add(Calendar.YEAR, 10);
//		String vigencia = UtilFecha.dateToString("dd-MM-yyyy HH:mm", fechaExpedicionDoc.getTime());
//		parametros.put(ConsultaFileNetDto.TAXONOMIA, UtilConstantes.ID_TAXONOMIA);
//		parametros.put(ConsultaFileNetDto.TITULO_DOCUMENTO, this.tituloDocumento);
//		parametros.put(ConsultaFileNetDto.NOMBRE_USUARIO, this.usuario.getNombres());
//		parametros.put(ConsultaFileNetDto.IDENTIFICADOR_USUARIO, this.usuario.getCodigo());
//		parametros.put(ConsultaFileNetDto.NOMBRE_DEL_DOCUMENTO,
//				UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_DEL_DOCUMENTO));
//		parametros.put(ConsultaFileNetDto.TIPO_DE_MOVIMIENTO,
//				UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.TIPO_MOVIMIENTO));
//		parametros.put(ConsultaFileNetDto.FECHA_EXPEDICION_DOCUMENTO, fechaExpedicion);
//		parametros.put(ConsultaFileNetDto.VIGENCIA_DOCUMENTO, vigencia);
//		parametros.put(ConsultaFileNetDto.RAMO_PRINCIPAL, TipoRamo.HOGAR.getValor());
//		parametros.put(ConsultaFileNetDto.RAMO_SECUNDARIO, TipoRamo.INCENDIO.getValor());
//		parametros.put(ConsultaFileNetDto.PRODUCTO_PRINCIPAL, TipoProducto.HOGAR.getValor());
//		parametros.put(ConsultaFileNetDto.PRODUCTO_SECUNDARIO, TipoProducto.HIPOTECARIO_DAVIVIENDA.getValor());
//		parametros.put(ConsultaFileNetDto.CLASIFICACION_SEGURIDAD, TipoClasificacionSeguridad.CSI004.getValor());
//		parametros.put(ConsultaFileNetDto.CLASIFICACION_HABEAS_DATA, TipoClasificacionHD.HD003.getValor());
//		parametros.put(ConsultaFileNetDto.NUMERO_DE_POLIZA, UtilTexto.estaVacio(numeroPoliza)?"0":numeroPoliza);
//		parametros.put(ConsultaFileNetDto.NUMERO_DE_SINIESTRO, "0");
//		parametros.put(ConsultaFileNetDto.INFORME_ELABORADO_POR, TipoElaborador.PROVEEDOR.getValor());
//		parametros.put(ConsultaFileNetDto.TIPO_INFORME, TipoInformeGD.TECNICO.getValor());
//		parametros.put(ConsultaFileNetDto.NUMERO_ID_CLIENTE, this.avaluo.getNumeroIdentificacion().toString());
//		parametros.put(ConsultaFileNetDto.NOMBRE_TITULAR_DOCUMENTO, this.avaluo.getNombre());
//		parametros.put(ConsultaFileNetDto.NUMERO_DOC_TITULAR, this.avaluo.getNumeroIdentificacion().toString());
//		parametros.put(ConsultaFileNetDto.TIPO_DOC_TITULAR,
//				convertidorTiposDocumentos(this.avaluo.getIdTipoIdentificacion().intValue()));
//		parametros.put(ConsultaFileNetDto.TIPO_DOC_ID_CIENTE,
//				convertidorTiposDocumentos(this.avaluo.getIdTipoIdentificacion().intValue()));
//		parametros.put(ConsultaFileNetDto.TIPO_DOC_ELABORA, convertidorTiposDocumentos(21));
//																						
//		parametros.put(ConsultaFileNetDto.ID_DOC_ELABORA, this.usuario.getCodigo());
//		parametros.put(ConsultaFileNetDto.NOMBRE_ELABORA, this.usuario.getNombres());
//		parametros.put(ConsultaFileNetDto.ID_COMPANIA, UtilConstantes.ID_COMPANIA);
//		parametros.put(ConsultaFileNetDto.RETORNO, UtilConstantes.IDS);
//	}
//
//	
//	public void crearMetadataPlanificador() {
//		Calendar fechaExpedicionDoc = Calendar.getInstance();
//		String fechaExpedicion = UtilFecha.dateToString("dd-MM-yyyy HH:mm", fechaExpedicionDoc.getTime());
//		fechaExpedicionDoc.add(Calendar.YEAR, 10);
//		String vigencia = UtilFecha.dateToString("dd-MM-yyyy HH:mm", fechaExpedicionDoc.getTime());
//		parametros.put(ConsultaFileNetDto.TAXONOMIA, UtilConstantes.ID_TAXONOMIA);
//		parametros.put(ConsultaFileNetDto.TITULO_DOCUMENTO, this.tituloDocumento);
//		parametros.put(ConsultaFileNetDto.NOMBRE_USUARIO, this.usuario.getNombres());
//		parametros.put(ConsultaFileNetDto.IDENTIFICADOR_USUARIO, this.usuario.getCodigo());
//		parametros.put(ConsultaFileNetDto.NOMBRE_DEL_DOCUMENTO,
//				UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_DEL_DOCUMENTO));
//		parametros.put(ConsultaFileNetDto.TIPO_DE_MOVIMIENTO,
//				UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.TIPO_MOVIMIENTO));
//		parametros.put(ConsultaFileNetDto.FECHA_EXPEDICION_DOCUMENTO, fechaExpedicion);
//		parametros.put(ConsultaFileNetDto.VIGENCIA_DOCUMENTO, vigencia);
//		parametros.put(ConsultaFileNetDto.RAMO_PRINCIPAL, TipoRamo.HOGAR.getValor());
//		parametros.put(ConsultaFileNetDto.RAMO_SECUNDARIO, TipoRamo.INCENDIO.getValor());
//		parametros.put(ConsultaFileNetDto.PRODUCTO_PRINCIPAL, TipoProducto.HOGAR.getValor());
//		parametros.put(ConsultaFileNetDto.PRODUCTO_SECUNDARIO, TipoProducto.HIPOTECARIO_DAVIVIENDA.getValor());
//		parametros.put(ConsultaFileNetDto.CLASIFICACION_SEGURIDAD, TipoClasificacionSeguridad.CSI004.getValor());
//		parametros.put(ConsultaFileNetDto.CLASIFICACION_HABEAS_DATA, TipoClasificacionHD.HD003.getValor());
//		parametros.put(ConsultaFileNetDto.NUMERO_DE_POLIZA, UtilTexto.estaVacio(numeroPoliza)?"0":numeroPoliza);
//		parametros.put(ConsultaFileNetDto.NUMERO_DE_SINIESTRO, "0");
//		parametros.put(ConsultaFileNetDto.INFORME_ELABORADO_POR, TipoElaborador.PROVEEDOR.getValor());
//		parametros.put(ConsultaFileNetDto.TIPO_INFORME, TipoInformeGD.TECNICO.getValor());
//		parametros.put(ConsultaFileNetDto.TIPO_DOC_ELABORA, convertidorTiposDocumentos(21));																						// defecto
//		parametros.put(ConsultaFileNetDto.ID_DOC_ELABORA, this.usuario.getCodigo());
//		parametros.put(ConsultaFileNetDto.NOMBRE_ELABORA, this.usuario.getNombres());
//		parametros.put(ConsultaFileNetDto.ID_COMPANIA, UtilConstantes.ID_COMPANIA);
//		parametros.put(ConsultaFileNetDto.RETORNO, UtilConstantes.IDS);
//		parametros.put(ConsultaFileNetDto.TIPO_DOC_ID_CIENTE,convertidorTiposDocumentos(21));
//		
//		parametros.put(ConsultaFileNetDto.NOMBRE_TITULAR_DOCUMENTO, this.usuario.getNombres());	
//		parametros.put(ConsultaFileNetDto.NUMERO_DOC_TITULAR, this.usuario.getCodigo());
//		parametros.put(ConsultaFileNetDto.NUMERO_ID_CLIENTE, this.usuario.getCodigo());
//		parametros.put(ConsultaFileNetDto.TIPO_DOC_TITULAR,convertidorTiposDocumentos(21));
//		
//
//	}
//
//	
//	public List<Propiedad> filtroConsultaWithOr() {
//		List<Propiedad> propiedadesConsulta = new ArrayList<>();
//		for (Map.Entry<String, String> entry : parametros.entrySet()) {
//			if (contieneAtributo(entry.getKey()) && entry.getKey().equals("DocumentTitle")) {
//				Propiedad propiedad = new Propiedad();
//				propiedad.setClave(entry.getKey());
//				propiedad.setValor(entry.getValue());
//				propiedad.setOperador("OR");
//				propiedadesConsulta.add(propiedad);
//			}
//		}
//
//		return propiedadesConsulta;
//	}
//
//	// eliminamos el atributo documento title para dejar solo el archivo asociado al
//	// avaluo
//	public List<Propiedad> filtroConsultaWithoutFileName() {
//		List<Propiedad> propiedadesConsulta = new ArrayList<>();
//		for (Map.Entry<String, String> entry : parametros.entrySet()) {
//			if (contieneAtributo(entry.getKey()) && !entry.getKey().equals("DocumentTitle")) {
//				Propiedad propiedad = new Propiedad();
//				propiedad.setClave(entry.getKey());
//				propiedad.setValor(entry.getValue());
//				propiedad.setOperador("AND");
//				propiedadesConsulta.add(propiedad);
//			}
//		}
//
//		return propiedadesConsulta;
//	}
//
//	public List<Propiedad> filtroConsulta() {
//		List<Propiedad> propiedadesConsulta = new ArrayList<>();
//		for (Map.Entry<String, String> entry : parametros.entrySet()) {
//			if (!contieneAtributo(entry.getKey()))
//				continue;
//			Propiedad propiedad = new Propiedad();
//			propiedad.setClave(entry.getKey());
//			propiedad.setValor(entry.getValue());
//			propiedad.setOperador("AND");
//			propiedadesConsulta.add(propiedad);
//		}
//		return propiedadesConsulta;
//	}
//
//	public List<Propiedad> filtroConsultaIds() {
//		List<Propiedad> propiedadesConsulta = new ArrayList<>();
//		for (Map.Entry<String, String> entry : parametros.entrySet()) {
//			if (!contieneAtributoId(entry.getKey()))
//				continue;
//			Propiedad propiedad = new Propiedad();
//			propiedad.setClave(entry.getKey());
//			propiedad.setValor(entry.getValue());
//			propiedad.setOperador("AND");
//			propiedadesConsulta.add(propiedad);
//		}
//		return propiedadesConsulta;
//	}
//
//	private boolean contieneAtributo(String llave) {
//		for (final String atr : ATRIBUTOS_CONSULTA) {
//			if (atr.equals(llave))
//				return true;
//		}
//		return false;
//	}
//
//	private boolean contieneAtributoId(String llave) {
//		for (final String atr : ATRIBUTOS_CONSULTA_ID) {
//			if (atr.equals(llave))
//				return true;
//		}
//		return false;
//	}
//
//	private String convertidorTiposDocumentos(int tipoDocumento) {
//		switch (tipoDocumento) {
//		case 21:
//			return TipoIdentificacion.CEDULA_CIUDADANIA.getValor();
//		case 22:
//			return TipoIdentificacion.NIT.getValor();
//		default:
//			return TipoIdentificacion.NIT.getValor();
//		}
//
//	}
//
//	public Map<String, String> getParametros() {
//		return parametros;
//	}
//
//	public void setParametros(Map<String, String> parametros) {
//		this.parametros = parametros;
//	}
//
//	public String getTituloDocumento() {
//		return tituloDocumento;
//	}
//
//	public void setTituloDocumento(String tituloDocumento) {
//		this.tituloDocumento = tituloDocumento;
//	}
//
//	public String getNombreUsuario() {
//		return nombreUsuario;
//	}
//
//	public void setNombreUsuario(String nombreUsuario) {
//		this.nombreUsuario = nombreUsuario;
//	}
//
//	public String getIdentificadorUsuario() {
//		return identificadorUsuario;
//	}
//
//	public void setIdentificadorUsuario(String identificadorUsuario) {
//		this.identificadorUsuario = identificadorUsuario;
//	}
//
//	public String getNombreDocumento() {
//		return nombreDocumento;
//	}
//
//	public void setNombreDocumento(String nombreDocumento) {
//		this.nombreDocumento = nombreDocumento;
//	}
//
//	public String getTipoMovimiento() {
//		return tipoMovimiento;
//	}
//
//	public void setTipoMovimiento(String tipoMovimiento) {
//		this.tipoMovimiento = tipoMovimiento;
//	}
//
//	public String getTipoDocumentoIdCliente() {
//		return tipoDocumentoIdCliente;
//	}
//
//	public void setTipoDocumentoIdCliente(String tipoDocumentoIdCliente) {
//		this.tipoDocumentoIdCliente = tipoDocumentoIdCliente;
//	}
//
//	public String getNumeroIdentificacionCliente() {
//		return numeroIdentificacionCliente;
//	}
//
//	public void setNumeroIdentificacionCliente(String numeroIdentificacionCliente) {
//		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
//	}
//
//	public String getNombreTitularDocumento() {
//		return nombreTitularDocumento;
//	}
//
//	public void setNombreTitularDocumento(String nombreTitularDocumento) {
//		this.nombreTitularDocumento = nombreTitularDocumento;
//	}
//
//	public String getFechaExpedicionDocumento() {
//		return fechaExpedicionDocumento;
//	}
//
//	public void setFechaExpedicionDocumento(String fechaExpedicionDocumento) {
//		this.fechaExpedicionDocumento = fechaExpedicionDocumento;
//	}
//
//	public String getVigenciaDocumento() {
//		return vigenciaDocumento;
//	}
//
//	public void setVigenciaDocumento(String vigenciaDocumento) {
//		this.vigenciaDocumento = vigenciaDocumento;
//	}
//
//	public List<String> getRamo() {
//		return ramo;
//	}
//
//	public void setRamo(List<String> ramo) {
//		this.ramo = ramo;
//	}
//
//	public List<String> getProducto() {
//		return producto;
//	}
//
//	public void setProducto(List<String> producto) {
//		this.producto = producto;
//	}
//
//	public String getClasificacionSeguridadInformatica() {
//		return clasificacionSeguridadInformatica;
//	}
//
//	public void setClasificacionSeguridadInformatica(String clasificacionSeguridadInformatica) {
//		this.clasificacionSeguridadInformatica = clasificacionSeguridadInformatica;
//	}
//
//	public String getClasificacionHabeasData() {
//		return clasificacionHabeasData;
//	}
//
//	public void setClasificacionHabeasData(String clasificacionHabeasData) {
//		this.clasificacionHabeasData = clasificacionHabeasData;
//	}
//
//	public String getNumeroPoliza() {
//		return numeroPoliza;
//	}
//
//	public void setNumeroPoliza(String numeroPoliza) {
//		this.numeroPoliza = numeroPoliza;
//	}
//
//	public String getNumeroSiniestro() {
//		return numeroSiniestro;
//	}
//
//	public void setNumeroSiniestro(String numeroSiniestro) {
//		this.numeroSiniestro = numeroSiniestro;
//	}
//
//	public String getInformeElaboradoPor() {
//		return informeElaboradoPor;
//	}
//
//	public void setInformeElaboradoPor(String informeElaboradoPor) {
//		this.informeElaboradoPor = informeElaboradoPor;
//	}
//
//	public String getTipoInforme() {
//		return tipoInforme;
//	}
//
//	public void setTipoInforme(String tipoInforme) {
//		this.tipoInforme = tipoInforme;
//	}
//
//	public String getTipoDocumentoQuienElaboraInforme() {
//		return tipoDocumentoQuienElaboraInforme;
//	}
//
//	public void setTipoDocumentoQuienElaboraInforme(String tipoDocumentoQuienElaboraInforme) {
//		this.tipoDocumentoQuienElaboraInforme = tipoDocumentoQuienElaboraInforme;
//	}
//
//	public String getIdentificacionQuienElaboraInforme() {
//		return identificacionQuienElaboraInforme;
//	}
//
//	public void setIdentificacionQuienElaboraInforme(String identificacionQuienElaboraInforme) {
//		this.identificacionQuienElaboraInforme = identificacionQuienElaboraInforme;
//	}
//
//	public String getNombreQuienElaboraInforme() {
//		return nombreQuienElaboraInforme;
//	}
//
//	public void setNombreQuienElaboraInforme(String nombreQuienElaboraInforme) {
//		this.nombreQuienElaboraInforme = nombreQuienElaboraInforme;
//	}
//
//	public String getIdentificacionCompania() {
//		return identificacionCompania;
//	}
//
//	public void setIdentificacionCompania(String identificacionCompania) {
//		this.identificacionCompania = identificacionCompania;
//	}
//
//	public String getIdentificador() {
//		return identificador;
//	}
//
//	public void setIdentificador(String identificador) {
//		this.identificador = identificador;
//	}
//
//}
