package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import java.awt.image.BufferedImage; 
import java.awt.Graphics2D; 
import java.awt.RenderingHints; 
import java.awt.Transparency; 
import java.io.ByteArrayOutputStream; 
import javax.imageio.ImageWriter; 
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam; 
import javax.imageio.IIOImage;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;

import com.asesoftware.util.archivo.UtilPdf;
import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BadPdfFormatException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.segurosbolivar.avaluos.modelo.cons.Procedencia;
import com.segurosbolivar.avaluos.modelo.data.AnexoFotograficoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoTmpDao;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.ArchivosTmp;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IEmpresaAvaluadora;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IReporte;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Implementaci�n del servicio de reportes
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@Stateless
public class ReporteImpl implements IReporte {

    private static final long serialVersionUID = 5821321306536035319L;

    @EJB
    private ManejadorErroresNegocio mgrExc;

    private static final Logger log = Logger.getLogger(ReporteImpl.class.getSimpleName());

    @EJB
    private IParametrizacion parametrizacionSvc;
    @EJB
    private IDiligenciamiento diligenciamientoSvc;
    @EJB
    private IEmpresaAvaluadora empresaSvc;
    @EJB
    private IArchivo archivoSvc;
    @EJB
    private AvaluoDao avaluoDao;
    @EJB
    private ArchivoDao archivoDao;
    @EJB
    private ArchivoTmpDao archivoTmpDao;
    @EJB
    private AnexoFotograficoDao anexoDao;

    public static final String RUTA_REPORTES_INFORME = UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.RUTA_JASPER)
	    + UtilConstantes.RUTA_INFORME_AVALUOS;
    private static final String DOMINIO_AFIRMAR = "AFIRMAR";
    private static final String DOMINIO_AFIRMAR_2 = "AFIRMAR2";
    private static final String DOMINIO_INFO_CONSTR = "INFOCONSTR";
    private static final String DOMINIO_CALIDAD = "CALIDAD";
    private static final String DOMINIO_MBR = "MBR";

    @Override
    public File generarReporte(String rutaReporte, String rutaExportacion, List<?> data, String nombreOrigenDatos) throws NegocioException {
	Map<String, Object> parametros = new HashMap<>();
	parametros.put(nombreOrigenDatos, new JRBeanCollectionDataSource(data));
	try {
	    InputStream reporteIngreso = this.getClass().getClassLoader().getResourceAsStream(rutaReporte);
	    JasperPrint reporteCompilado = UtilPdf.generarReportJasper(reporteIngreso, parametros, new JREmptyDataSource());
	    return UtilPdf.generarReportJasperPdf(rutaExportacion, reporteCompilado);
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(25, TipoErrorNegocio.FATAL, e.getMessage(), null);
	}
    }

    @Override
    public File generarReporteXLS(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data, String nombreOrigenDatos) throws NegocioException {
	Map<String, Object> parametros = new HashMap<>();
	parametros.put(nombreOrigenDatos, new JRBeanCollectionDataSource(data));
	try {
	    InputStream reporteIngreso = this.getClass().getClassLoader().getResourceAsStream(rutaReporte);
	    JasperPrint reporteCompilado = UtilPdf.generarReportJasper(reporteIngreso, parametros, new JREmptyDataSource());
	    return UtilPdf.generarReportJasperPdf(rutaExportacion, reporteCompilado);
	    // return UtilPdf.generarReportJasperExcel(nombreReporte, rutaExportacion,
	    // rutaReporte, reporteCompilado);
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(25, TipoErrorNegocio.FATAL, e.getMessage(), null);
	}
    }
    
    @Override
    public File generarReporteExcel(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data, String nombreOrigenDatos) throws NegocioException {
	Map<String, Object> parametros = new HashMap<>();
	parametros.put(nombreOrigenDatos, new JRBeanCollectionDataSource(data));
	try {
	    InputStream reporteIngreso = this.getClass().getClassLoader().getResourceAsStream(rutaReporte);
	    JasperPrint reporteCompilado = UtilPdf.generarReportJasper(reporteIngreso, parametros, new JREmptyDataSource());
	    //return UtilPdf.generarReportJasperPdf(rutaExportacion, reporteCompilado);
	     return UtilPdf.generarReportJasperExcel(rutaExportacion,reporteCompilado);
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(25, TipoErrorNegocio.FATAL, e.getMessage(), null);
	}
    }

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public File imprimir(List<Avaluo> avaluos, boolean registroFotografico, UsuarioDto usuario)
			throws NegocioException {
		if (avaluos == null || avaluos.isEmpty())
			throw mgrExc.lanzarExcepcion(12L, TipoErrorNegocio.ERROR);

		File retorno = new File(
				UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.RUTA_REPOSITORIO)
						+ UtilConstantes.NOMBRE_INFORME_AVALUOS.replace(".jasper", "")
						+ UtilFecha.fechaActualFormatoAAAAMMDDHH24MISS() + ".pdf");
		PdfCopy copiar = null;
		String consecutivo = null;
		try {
			Document documento = new Document();
			copiar = new PdfCopy(documento, new FileOutputStream(retorno));
			PdfReader informeFotografico = null;
			documento.open();
			//int numeroAnexos=0;
			log.info("numero de avaluos: " + avaluos.size());
			for (Avaluo avaluo : avaluos) {
				log.info("identificador  del avaluo: " + avaluo.getIdAvaluo());
				avaluo = avaluoDao.buscar(avaluo.getIdAvaluo());
				if(avaluo != null && (avaluo.getLiquidacionAvaluos() == null || avaluo.getLiquidacionAvaluos().isEmpty() )) {
					avaluo.setLiquidacionAvaluos(diligenciamientoSvc.consultarLiquidacion(avaluo.getIdAvaluo()));
				}
				log.info("consecutivo del avaluo: " + avaluo.getCodigoBanco());
				consecutivo = avaluo.getConsecutivoBanco()+"";
				PdfReader informe = null;
				//mejora validar si el avaluo ya tiene id del pdf del avaluo, solo consultarlo al s3 y aqui no generarlo, si no tiene id o no existe en s3, allí si hacer lo siguiente 
				informe = new PdfReader(generarReporteAvaluo(avaluo));//genera y queda en inform el pdf del avaluo pero sin fotos (osea solo 1er pagina)
				PdfImportedPage pagina = copiar.getImportedPage(informe, 1);//toma la 1ra pagina del pdf del avaluo (paso anterior)
				copiar.addPage(pagina);//al pdf final le adiciona la pagina extraida anteriormente (la 1ra pagina del pdf del avaluo)
				
				boolean esCargueMasivo=avaluo.getCodigoProcedencia()==Procedencia.CARGUE_MASIVO.getValor();
				
				if(!avaluo.isProvisional() && registroFotografico) {
					//si es de indmotor 
					AnexoFotografico anxTemporal = anexoDao.consultarAnexoImprimir(avaluo.getIdAvaluo());//creo consulta si tiene anexos de fotografias el avaluo
					if (avaluo.getListaAnexosPdf() != null && !avaluo.getListaAnexosPdf().isEmpty() || ( avaluo.getIndMotor() != null && avaluo.getIndMotor().equals("S"))) {
						//valida que tenga anexos, que sea individual
						byte[] contenidoFotografico = generarReporteRegistroFotografico(avaluo);//genera el pdf con solo las imagenes (registro fotografico)
						if(contenidoFotografico != null)
							informeFotografico = new PdfReader(contenidoFotografico);//guarda en informeFotografico el pdf con las imagenes
					}
					
					if(esCargueMasivo) {
						//valida si es cargue masivo
						copiar = obtenerInformeCargueMasivo(avaluo, copiar);
					}else if(anxTemporal!=null && !anxTemporal.getIdImgFachada().equals(anxTemporal.getIdDocAnexos())) {
						//valida si es una avaluo antiguo
						copiar = obtenerInforme(avaluo, anxTemporal, copiar, informeFotografico);
					}else {
						//adiciona a lo que tenia copiar (1ra pagina del avaluo), una a una las paginas del pdf del registro fotografico
						//para asi ya tener el pdf completo
						copiar = construirInforme(avaluos, avaluo, registroFotografico, copiar, informeFotografico); 
					}
				}
				avaluo.setImpreso(true);
				avaluo.setFechaImpresionAnterior(avaluo.getFechaImpresion());
				avaluo.setFechaImpresion(new Date());
				avaluoDao.actualizar(avaluo);
			}
			copiar.close();
		} catch (NegocioException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(83L, TipoErrorNegocio.ERROR, "El avalúo "+consecutivo+ " no pudo ser impreso. Por favor verifique el registro fotográfico.", null);
		}
		return retorno;
	}

    private PdfCopy construirInforme(List<Avaluo> avaluos, Avaluo avaluo, boolean registroFotografico, PdfCopy copiar, PdfReader informeFotografico) {
		if (informeFotografico != null) {
			for (int i = 1; i <= informeFotografico.getNumberOfPages(); i++) {
				PdfImportedPage pag = copiar.getImportedPage(informeFotografico, i);
				try {
					copiar.addPage(pag);
				} catch (BadPdfFormatException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return copiar;
	}

    private PdfCopy obtenerInforme(Avaluo avaluo, AnexoFotografico anxTemporal, PdfCopy copiar, PdfReader informeFotografico) {
    	PdfReader informe = null;
		if (anxTemporal == null) {
			log.info("==== ERROR EN EL ANEXO FOTOGRAFICO=====");
		}else {
			Archivo archivoFotografico = archivoDao.buscar(anxTemporal.getIdDocAnexos());
			byte[] contenido = archivoFotografico.getContenidoArchivo();
			try {
				informe = new PdfReader(contenido);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (informe != null && informeFotografico == null) {
				for (int i = 1; i <= informe.getNumberOfPages(); i++) {
					PdfImportedPage pagInfo = copiar.getImportedPage(informe, i);
					try {
						copiar.addPage(pagInfo);
					} catch (BadPdfFormatException | IOException e) {
						e.printStackTrace();
					}
				}
			}
			if (informeFotografico != null) {
				for (int i = 1; i <= informeFotografico.getNumberOfPages(); i++) {
					PdfImportedPage pag = copiar.getImportedPage(informeFotografico, i);
					try {
						copiar.addPage(pag);
					} catch (BadPdfFormatException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return copiar;
    }
    
   
    
    private PdfCopy obtenerInformeCargueMasivo( Avaluo avaluo, PdfCopy copiar) {
    	PdfReader informe = null;
    		String nombreArchivo=avaluo.getConsecutivoBanco()+"_fotos.pdf";
    		
			List<ArchivosTmp> archivoFotografico = archivoTmpDao.buscarArchivosPorNombre(nombreArchivo);
			byte[] contenido = archivoFotografico.get(0).getContenido();
			try {
				informe = new PdfReader(contenido);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (informe != null) {
				for (int i = 1; i <= informe.getNumberOfPages(); i++) {
					PdfImportedPage pagInfo = copiar.getImportedPage(informe, i);
					try {
						copiar.addPage(pagInfo);
					} catch (BadPdfFormatException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		return copiar;
    }
      
    public PdfReader generarInformeAvaluo(Avaluo avaluo, boolean registroFotografico) throws NegocioException {
	if (avaluo == null)
	    throw mgrExc.lanzarExcepcion(12L, TipoErrorNegocio.FATAL);
	PdfCopy copy = null;
	PdfReader informeFotografico = null;
	PdfReader informeAvaluo = null;
	try {
	    byte[] reporteAvaluos = generarReporteAvaluo(avaluo);
	    if (!registroFotografico || avaluo.getListaAnexosPdf() == null || avaluo.getListaAnexosPdf().isEmpty())
		return new PdfReader(reporteAvaluos);
	    informeAvaluo = new PdfReader(reporteAvaluos);
	    
	    return informeAvaluo;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(84, TipoErrorNegocio.ERROR, e.getMessage(), null);
	} finally {
	    if (copy != null)
		copy.close();
	    if (informeAvaluo != null)
		informeAvaluo.close();
	    if (informeFotografico != null)
		informeFotografico.close();
	}
    }

    private byte[] generarReporteAvaluo(Avaluo avaluo) throws NegocioException {
		try {
		    if (avaluo == null)
			throw mgrExc.lanzarExcepcion(12L, TipoErrorNegocio.ERROR);
		    Map<String, Object> parametros = new HashMap<>();
		    parametros.put("SUBREPORT_DIR", RUTA_REPORTES_INFORME);
		    prepararAvaluo(avaluo);
		    return UtilPdf.generarReportJasperPdf(this.getClass().getClassLoader().getResourceAsStream(RUTA_REPORTES_INFORME + UtilConstantes.NOMBRE_INFORME_AVALUOS), parametros,
			    new JRBeanCollectionDataSource(Collections.singleton(avaluo)));
		} catch (NegocioException e) {
		    throw e;
		} catch (Exception e) {
		    throw mgrExc.lanzarExcepcion(85, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	    }
	    
	   
	    private byte[] generarReporteRegistroFotografico(Avaluo avaluo) throws NegocioException {
	    	log.info("----- inicial el metodo generarReporteRegistroFotografico -------");
		try {
		    if (avaluo == null)
		    	throw mgrExc.lanzarExcepcion(12L, TipoErrorNegocio.ERROR);
		    Map<String, Object> parametros = new HashMap<>();
		    List<ListaAnexosPdf> l = null;
		    try {
		    	l =  diligenciamientoSvc.consultarRegistroFotografico(avaluo, true);//retorna las imagenes anexas (del registro fotografico con el byte[] en setAnexo)
		    	if(l == null || l.isEmpty())
		    		return null;
		 	    for(ListaAnexosPdf anexo : l){
		 	    	if(anexo.getAnexo()==null) {
		 	    		log.info("El anexo no tiene informacion: "+anexo.getIdAvaluo());
		 	    		return null;
		 	    	}
		 	    }
		    }catch (Exception e) {
				return null;
			}
		    long tiempoInicial = System.currentTimeMillis();
		    //comprimir imagenes
		     for (int i = 0; i < l.size(); i++) {
		    	 try{
		    		 InputStream is = new ByteArrayInputStream(l.get(i).getAnexo());
		    		 BufferedImage image = ImageIO.read(is);
		    		 l.get(i).setAnexo(comprimirImagen(image, image.getHeight(),image.getWidth() ));
		    		 //comprimirImagen(is);
		    	 } catch (Exception e) {
		    		 log.info("----- Error al comprimir la imagen-------");
		    	 }
	    	     
		   	 }
		     long tiempoFinal = System.currentTimeMillis();
		     long tiempoTotal = tiempoFinal - tiempoInicial;
		     log.info("============Tiempo total comprimiendo imagenes : "+ tiempoTotal+"ms");
		    	
		    parametros.put("ANEXOS", l);//pone en parametros ANEXOS las fotografias y esto se lo envia al jasper para que genere el pdf de las fotografias
		    log.info("----- fi+"
		    		+ "naliza el metodo generarReporteRegistroFotografico -------");
		    return UtilPdf.generarReportJasperPdf(this.getClass().getClassLoader().getResourceAsStream(RUTA_REPORTES_INFORME + UtilConstantes.NOMBRE_INFORME_REGISTRO_FOTOGRAFICO),
				    parametros, new JRBeanCollectionDataSource(Collections.singleton(avaluo)));
		} catch (NegocioException e) {
		    throw e;
		} catch (Exception e) {
		    throw mgrExc.lanzarExcepcion(86, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
    }
	public byte[] comprimirImagen(BufferedImage image, int imgHeight, int imgWidth) {
		 int targetWidth = imgWidth/2; 
		 int targetHeight = imgHeight/2; 
		 try{	
			 image = scale(image, targetWidth, targetHeight); 
	   	     Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
	   	     ImageWriter writer = (ImageWriter) writers.next();
	   	    
	   	     ByteArrayOutputStream os = new ByteArrayOutputStream(37628);
	   	     ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	   	     writer.setOutput(ios);
	
	   	     ImageWriteParam param = writer.getDefaultWriteParam();
	
	   	     param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	   	     param.setCompressionQuality(0.2f);  // Change the quality value you prefer
	   	     writer.write(null, new IIOImage(image, null, null), param);
	   	  
	   	     ByteArrayInputStream bai = new ByteArrayInputStream(os.toByteArray());
	   	     RenderedImage out = ImageIO.read(bai);
	   	     
	   	     byte[] imageBytes = os.toByteArray();
	   	   
	   	     os.close();
	   	     ios.close();
	   	     writer.dispose();
	   	     return imageBytes;
		 } catch (Exception e) {
			 log.info("----- Error al comprimir la imagen-------");
			 return null;
		 }
	}
    public static BufferedImage scale(BufferedImage img, int targetWidth, int targetHeight) { 
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = img; 
		BufferedImage scratchImage = null;
		Graphics2D g2 = null; 
		int w = img.getWidth(); 
		int h = img.getHeight(); 
		int prevW = w; 
		int prevH = h; 
		do { 
			if (w > targetWidth) { 
				w /= 2; w = (w < targetWidth) ? targetWidth : w; 
			} 
			if (h > targetHeight) { 
				h /= 2; h = (h < targetHeight) ? targetHeight : h; 
			} 
			if (scratchImage == null) { 
				scratchImage = new BufferedImage(w, h, type); 
				g2 = scratchImage.createGraphics(); 
			} 
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
			g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null); 
			prevW = w; 
			prevH = h; 
			ret = scratchImage;
		} while (w != targetWidth || h != targetHeight); 
		
		if (g2 != null) { 
		g2.dispose(); 
		} 
		if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) { 
			scratchImage = new BufferedImage(targetWidth, targetHeight, type); 
			g2 = scratchImage.createGraphics();
			g2.drawImage(ret, 0, 0, null); 
			g2.dispose(); ret = scratchImage; 
		} 
		return ret;
  } 
    private void prepararAvaluo(Avaluo avaluo) throws NegocioException, IOException {
	    avaluo.setDireccionInmueble(UtilTexto.estaVacio(avaluo.getDireccionInmueble()) ? "": avaluo.getDireccionInmueble());
	    avaluo.setDireccionComplementaria(UtilTexto.estaVacio(avaluo.getDireccionComplementaria()) ? "":avaluo.getDireccionComplementaria());
		avaluo.setSectorDescripcion(parametrizacionSvc.consultarDescripcionDominio("SECTOR", UtilNumero.pasarTexto(avaluo.getSector())));
		avaluo.setObjetoDescripcion(parametrizacionSvc.consultarDescripcionDominio("OBJETOAVALUO", UtilNumero.pasarTexto(avaluo.getIdObjetoAvaluo())));
		avaluo.setTipoDocumento(parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION", UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())));
		avaluo.setMetodologiaDescripcion(parametrizacionSvc.consultarDescripcionDominio("METODOLOGIA", UtilNumero.pasarTexto(avaluo.getIdMetodologia())));
		if (!UtilTexto.estaVacio(avaluo.getUsuario())) {
		    EmpresasAvaluos empresa = empresaSvc.consultarXDocumento(Long.valueOf(avaluo.getUsuario()));
		    if (empresa != null) {
			avaluo.setDescEmpresa(empresa.getDescEmpresa());
			log.info(avaluo.getDescEmpresa());
			avaluo.setRegistroPrivado(empresa.getRegistroPrivado());
			log.info(avaluo.getRegistroPrivado());
			avaluo.setRegistroSic(empresa.getRegistroSic());
			log.info(avaluo.getRegistroSic());
			if (empresa.getLogo() != null)
			    try {
			    	log.info(empresa.getLogo().getIdArchivo().toString());
				avaluo.setImagenLogoEmpresa(
					new ByteArrayInputStream(!UtilTexto.estaVacio(empresa.getLogo().getIdDocumento()) ? archivoSvc.obtenerDocumentoCompreso(empresa.getLogo().getIdDocumento())
						: empresa.getLogo().getContenidoArchivo()));
			    } catch (Exception e) {
				throw e;
			    }
		    }
		}
		prepararImpresion(avaluo.getInformacionBarrio());
		prepararImpresion(avaluo.getInformacionInmueble());
		prepararImpresion(avaluo.getInformacionConstruccion());
		prepararImpresion(avaluo.getCondicionSalubridad());
		prepararImpresion(avaluo.getLiquidacionAvaluos());
		prepararImpresion(avaluo.getLiquidacionTotal());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void prepararImpresion(List<LiquidacionAvaluo> liquidacionAvaluos) throws NegocioException {
		if (liquidacionAvaluos == null || liquidacionAvaluos.isEmpty())
		    return;
		for (LiquidacionAvaluo liquidacionAvaluo : liquidacionAvaluos) {
		    liquidacionAvaluo.setDescripcionLiquidacionDescripcion(
			    parametrizacionSvc.consultarDescripcionDominio("DESCLIQUIDACION", UtilNumero.pasarTexto(liquidacionAvaluo.getDescripcionLiquidacion())));
		}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void prepararImpresion(LiquidacionAvaluoTotal liquidacionTotal) throws NegocioException {
		if (liquidacionTotal == null)
		    return;
		liquidacionTotal.setCalificacionDescripcion(parametrizacionSvc.consultarDescripcionDominio("CALGARANTIA", UtilNumero.pasarTexto(liquidacionTotal.getCalificacion())));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void prepararImpresion(CondicionesSalubridad condicionSalubridad) throws NegocioException {
		if (condicionSalubridad == null)
		    return;
	
		condicionSalubridad.setRequiereCondicionesSalubridadDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(condicionSalubridad.getRequiereCondicionesSalubridad())));
		condicionSalubridad.setImpactoNegativoRuidoDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getImpactoNegativoRuido())));
		condicionSalubridad.setImpactoNegativoPorAireDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getImpactoNegativoPorAire())));
		condicionSalubridad.setImpactoNegativoInseguridadDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getImpactoNegativoInseguridad())));
		condicionSalubridad.setImpactoNegativoBasuraDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getImpactoNegativoBasura())));
		condicionSalubridad.setAmbientalZonaVerdeDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getAmbientalZonaVerde())));
		condicionSalubridad.setAmbientalParquesDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getAmbientalParques())));
		condicionSalubridad.setAmbientalArborizacionDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getAmbientalArborizacion())));
		condicionSalubridad
			.setAguasServidasDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR_2, UtilNumero.pasarTexto(condicionSalubridad.getAguasServidas())));
		condicionSalubridad.setCondicionesSalubridadDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_MBR, UtilNumero.pasarTexto(condicionSalubridad.getCondicionSalubridad())));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void prepararImpresion(InformacionConstruccion informacionConstruccion) throws NegocioException {
		if (informacionConstruccion == null)
		    return;
		informacionConstruccion.setEstructuraTechosDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_INFO_CONSTR, UtilNumero.pasarTexto(informacionConstruccion.getEstructuraTechos())));
		informacionConstruccion.setCodigoConstructoraDescripcion(
			parametrizacionSvc.consultarDescripcionDominio("NOMBRECONSTRUCTORA", UtilNumero.pasarTexto(informacionConstruccion.getCodigoNombreConstructora())));
		informacionConstruccion.setDetalleMaterialSFBUADescripcion(
			parametrizacionSvc.consultarDescripcionDominio("C_ESTRUCTURA_SF_BUA", UtilNumero.pasarTexto(informacionConstruccion.getDetalleMaterialSFBUA())));
		informacionConstruccion.setAlturaDescripcion(parametrizacionSvc.consultarDescripcionDominio("ALTURA", UtilNumero.pasarTexto(informacionConstruccion.getAltura())));
		informacionConstruccion
			.setUbicacionNivelViaDescripcion(parametrizacionSvc.consultarDescripcionDominio("NIVEL", UtilNumero.pasarTexto(informacionConstruccion.getUbicacionNivelVia())));
		informacionConstruccion
			.setUbicacionTanqueDescripcion(parametrizacionSvc.consultarDescripcionDominio("TANQUES", UtilNumero.pasarTexto(informacionConstruccion.getUbicacionTanque())));
		informacionConstruccion.setParapetosCubiertaDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(informacionConstruccion.getParapetosCubierta())));
		informacionConstruccion.setUbicacionInmuebleDescripcion(
			parametrizacionSvc.consultarDescripcionDominio("UBICACION", UtilNumero.pasarTexto(informacionConstruccion.getUbicacionInmueble())));
		informacionConstruccion.setConjuntoAgrupacionCerradaDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(informacionConstruccion.getConjuntoAgrupacionCerrada())));
		informacionConstruccion.setPropiedadHorizontalDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(informacionConstruccion.getPropiedadHorizontal())));
		informacionConstruccion
			.setCalidadCocinaDescripcion(parametrizacionSvc.consultarDescripcionDominio("COCINA", UtilNumero.pasarTexto(informacionConstruccion.getCalidadCocina())));
		informacionConstruccion
			.setCalidadBanioDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_CALIDAD, UtilNumero.pasarTexto(informacionConstruccion.getCalidadBanio())));
		informacionConstruccion
			.setCalidadTechosDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_CALIDAD, UtilNumero.pasarTexto(informacionConstruccion.getCalidadTecho())));
		informacionConstruccion
			.setCalidadMuroDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_CALIDAD, UtilNumero.pasarTexto(informacionConstruccion.getCalidadMuro())));
		informacionConstruccion
			.setCalidadMetalDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_CALIDAD, UtilNumero.pasarTexto(informacionConstruccion.getCalidMetal())));
		informacionConstruccion
			.setCalidadPisoDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_CALIDAD, UtilNumero.pasarTexto(informacionConstruccion.getCalidadPiso())));
		informacionConstruccion
			.setCalidadMaderaDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_CALIDAD, UtilNumero.pasarTexto(informacionConstruccion.getCalidadMadera())));
		informacionConstruccion
			.setEstadoCocinaDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_INFO_CONSTR, UtilNumero.pasarTexto(informacionConstruccion.getEstadoCocina())));
		informacionConstruccion
			.setEstadoBaniosDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_INFO_CONSTR, UtilNumero.pasarTexto(informacionConstruccion.getEstadoBanios())));
		informacionConstruccion
			.setEstadoMetalDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_INFO_CONSTR, UtilNumero.pasarTexto(informacionConstruccion.getEstadoMetal())));
		informacionConstruccion
			.setEstadoMaderaDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_INFO_CONSTR, UtilNumero.pasarTexto(informacionConstruccion.getEstadoMadera())));
		informacionConstruccion
			.setEstadoMurosDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_INFO_CONSTR, UtilNumero.pasarTexto(informacionConstruccion.getEstadoMuros())));
		informacionConstruccion
			.setEstadoPisosDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_INFO_CONSTR, UtilNumero.pasarTexto(informacionConstruccion.getEstadoPisos())));
		informacionConstruccion
			.setIluminacionDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_MBR, UtilNumero.pasarTexto(informacionConstruccion.getIluminacion())));
		informacionConstruccion
			.setVentilacionDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_MBR, UtilNumero.pasarTexto(informacionConstruccion.getVentilacion())));
		informacionConstruccion
			.setAsentamientosDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(informacionConstruccion.getAsentamientos())));
		informacionConstruccion
			.setDanoPrevioDescripcion(parametrizacionSvc.consultarDescripcionDominio("DANOSPREVIOS_BUA", UtilNumero.pasarTexto(informacionConstruccion.getDanoPrevioBUA())));
		informacionConstruccion.setLucesDescripcion(parametrizacionSvc.consultarDescripcionDominio("LUCES", UtilNumero.pasarTexto(informacionConstruccion.getLuces())));
		informacionConstruccion.setGolpeteoDescripcion(parametrizacionSvc.consultarDescripcionDominio("GOLPETEO", UtilNumero.pasarTexto(informacionConstruccion.getGolpeteo())));
		informacionConstruccion
			.setSobrePesoDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(informacionConstruccion.getSobrePeso())));
		informacionConstruccion.setUbicacionFuentesHidricasDescripcion(
			parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(informacionConstruccion.getUbicacionFuentesHidricas())));
		informacionConstruccion.setSimetriaPlantaDescripcion(
			parametrizacionSvc.consultarDescripcionDominio("IRREGULARIDAD_BUA", UtilNumero.pasarTexto(informacionConstruccion.getIrregularidadPlantaBUA())));
		informacionConstruccion.setSimetriaAlturaDescripcion(
			parametrizacionSvc.consultarDescripcionDominio("IRREGULARIDAD_BUA", UtilNumero.pasarTexto(informacionConstruccion.getIrregularidadAlturaBUA())));
		informacionConstruccion.setMaterialEstructuraDescripcion(
			parametrizacionSvc.consultarDescripcionDominio("ESTRUCTURA2_BUA", UtilNumero.pasarTexto(informacionConstruccion.getMaterialConstruccionBUA())));
		informacionConstruccion.setCubiertaDescripcion(parametrizacionSvc.consultarDescripcionDominio("CUBIERTA", UtilNumero.pasarTexto(informacionConstruccion.getCubierta())));
		informacionConstruccion
			.setTipoFachadaDescripcion(parametrizacionSvc.consultarDescripcionDominio("TIPOFACHADA", UtilNumero.pasarTexto(informacionConstruccion.getTipoFachada())));
		informacionConstruccion
			.setEstructuraDescripcion(parametrizacionSvc.consultarDescripcionDominio("ESTRUCTURA", UtilNumero.pasarTexto(informacionConstruccion.getEstructura())));
		informacionConstruccion.setFachadaDescripcion(parametrizacionSvc.consultarDescripcionDominio("FACHADA", UtilNumero.pasarTexto(informacionConstruccion.getFachada())));
		informacionConstruccion.setEstadoConservacionDescripcion(
			parametrizacionSvc.consultarDescripcionDominio("ESTADOCONSERVACION", UtilNumero.pasarTexto(informacionConstruccion.getEstadoConservacion())));
		informacionConstruccion
			.setPisosBodegaDescripcion(parametrizacionSvc.consultarDescripcionDominio("PISOSBODEGA", UtilNumero.pasarTexto(informacionConstruccion.getPisosBodega())));
		informacionConstruccion.setEstadoConstruccionDescripcion(
			parametrizacionSvc.consultarDescripcionDominio("ESTADOCONSTRUCCION", UtilNumero.pasarTexto(informacionConstruccion.getEstadoConstruccion())));
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void prepararImpresion(InformacionInmueble informacionInmueble) throws NegocioException {
		if (informacionInmueble == null)
		    return;
		Ciudad ciudad = parametrizacionSvc.consultarCiudad(informacionInmueble.getCiudadEscritura());
		if (ciudad != null)
		    informacionInmueble.setCiudadEscrituraNombre(ciudad.getCiudad());
		Departamento departamento = parametrizacionSvc.consultarDepartamento(informacionInmueble.getDepartamentoEscritura());
		if (departamento != null)
		    informacionInmueble.setDepartamentoEscrituraNombre(departamento.getDepartamento());
		informacionInmueble.setUsoInmuebleDescripcion(parametrizacionSvc.consultarDescripcionDominio("USOINMUEBLE", UtilNumero.pasarTexto(informacionInmueble.getUsoInmueble())));
		informacionInmueble
			.setUsoInmuebleBuaDescripcion(parametrizacionSvc.consultarDescripcionDominio("USOINMUEBLE_BUA", UtilNumero.pasarTexto(informacionInmueble.getUsoInmuebleBUA())));
		informacionInmueble.setEdiContUsoDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_AFIRMAR, UtilNumero.pasarTexto(informacionInmueble.getEdiContUso())));
		informacionInmueble.setUbicacion2Descripcion(parametrizacionSvc.consultarDescripcionDominio("UBICACION2", UtilNumero.pasarTexto(informacionInmueble.getUbicacion2())));
		informacionInmueble.setUbicacion3Descripcion(parametrizacionSvc.consultarDescripcionDominio("UBICACION3", UtilNumero.pasarTexto(informacionInmueble.getUbicacion3())));
		informacionInmueble.setCategoriaDescripcion(parametrizacionSvc.consultarDescripcionDominio("CATEGORIA", UtilNumero.pasarTexto(informacionInmueble.getIdCategoria())));
		informacionInmueble.setClaseDescripcion(parametrizacionSvc.consultarDescripcionDominio("CLASEINMUEBLE", UtilNumero.pasarTexto(informacionInmueble.getClaseInmueble())));
		informacionInmueble
			.setTipoViviendaDescripcion(parametrizacionSvc.consultarDescripcionDominio("TIPOVIVIENDA", UtilNumero.pasarTexto(informacionInmueble.getTipoVivienda())));

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void prepararImpresion(InformacionBarrio informacionBarrio) throws NegocioException {
		if (informacionBarrio == null)
		    return;
		informacionBarrio.setTransporteDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_MBR, UtilNumero.pasarTexto(informacionBarrio.getTransporte())));
		informacionBarrio
			.setEstadosViaAccesoDescripcion(parametrizacionSvc.consultarDescripcionDominio(DOMINIO_MBR, UtilNumero.pasarTexto(informacionBarrio.getEstadosViaAcceso())));
		informacionBarrio.setTopografiaDescripcion(parametrizacionSvc.consultarDescripcionDominio("TOPOGRAFIA", UtilNumero.pasarTexto(informacionBarrio.getTopografia())));
		informacionBarrio.setEstratoDescripcion(parametrizacionSvc.consultarDescripcionDominio("ESTRATO", UtilNumero.pasarTexto(informacionBarrio.getEstrato())));
		informacionBarrio.setLegalidadDescripcion(parametrizacionSvc.consultarDescripcionDominio("LEGALBARRIO", UtilNumero.pasarTexto(informacionBarrio.getLegalidad())));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public byte[] generarReporteFotografico(Avaluo avaluo) throws NegocioException {
    	return generarReporteRegistroFotografico(avaluo);
    }

	@Override
	public byte[] generarReporte(InputStream jasperStream, List<?> data, String nombreOrigenDatos) throws NegocioException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put(nombreOrigenDatos, new JRBeanCollectionDataSource(data));
		try {
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(jasperStream);
			JasperPrint reporteCompilado = JasperFillManager.fillReport(jasperReport, parametros,
					new JREmptyDataSource());
			return UtilPdf.generarReportJasperPdf(reporteCompilado);
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(25, TipoErrorNegocio.FATAL, e.getMessage(), null);
		}
	}

	@Override
	public byte[] generarReporteXLS(InputStream jasperStream, List<?> data, String nombreOrigenDatos)
			throws NegocioException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put(nombreOrigenDatos, new JRBeanCollectionDataSource(data));
		try {
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(jasperStream);
			JasperPrint reporteCompilado = JasperFillManager.fillReport(jasperReport, parametros,
					new JREmptyDataSource());
			return UtilPdf.generarReportJasperExcel(reporteCompilado);
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(25, TipoErrorNegocio.FATAL, e.getMessage(), null);
		}
	}

	@Override
	public byte[] generarReporteExcel(InputStream jasperStream, List<?> data, String nombreOrigenDatos)
			throws NegocioException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put(nombreOrigenDatos, new JRBeanCollectionDataSource(data));
		parametros.put(JRXlsAbstractExporter.PROPERTY_AUTO_FIT_COLUMN, "true");
		parametros.put(JRXlsAbstractExporter.PROPERTY_AUTO_FIT_ROW, "true");
		try {
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(jasperStream);
			JasperPrint reporteCompilado = JasperFillManager.fillReport(jasperReport, parametros,
					new JREmptyDataSource());
			return UtilPdf.generarReportJasperExcel(reporteCompilado);
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(25, TipoErrorNegocio.FATAL, e.getMessage(), null);
		}
	}
    
    
}