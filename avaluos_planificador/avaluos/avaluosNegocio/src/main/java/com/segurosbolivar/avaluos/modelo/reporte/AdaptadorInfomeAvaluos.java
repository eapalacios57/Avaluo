package com.segurosbolivar.avaluos.modelo.reporte;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.util.IOUtils;

import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class AdaptadorInfomeAvaluos {

	protected static List<Avaluo> datos;

	public JRDataSource createDatasource() {
		return new JRBeanCollectionDataSource(createBeanCollection());
	}

	public static void main(String... args) {
		createBeanCollection();
	}

	@SuppressWarnings("rawtypes")
	public static Collection createBeanCollection() {
		datos = new ArrayList<>();
		Avaluo reg = generarAvaluo();
		datos.add(reg);
		reg.setInformacionBarrio(generarBarrio());
		reg.setInformacionConstruccion(generarConstruccion());
		reg.setInformacionInmueble(generarInmueble());
		reg.setLiquidacionAvaluos(new ArrayList<LiquidacionAvaluo>());
		reg.getLiquidacionAvaluos().add(generarLiquidacion());
		reg.getLiquidacionAvaluos().add(generarLiquidacion());
		reg.getLiquidacionAvaluos().add(generarLiquidacion());
		reg.setListaAnexosPdf(new ArrayList<ListaAnexosPdf>());
		try {
			reg.getListaAnexosPdf().add(generaFoto());
			reg.getListaAnexosPdf().add(generaFoto());
			reg.getListaAnexosPdf().add(generaFoto());
		} catch (Exception e) {
			reg.getListaAnexosPdf().clear();
		}
		reg.setCompOfertaDemanda(generarOferta());
		reg.setObservacion(generarObservaciones());
		reg.setLiquidacionTotal(generarLiquidacionTotal());
		reg.setCondicionSalubridad(generarSalubridad());
		datos.add(reg);
		datos.add(reg);
		return datos;
	}

	private static LiquidacionAvaluo generarLiquidacion() {
		LiquidacionAvaluo entity = new LiquidacionAvaluo();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setDescripcionLiquidacionDescripcion("que pasa");
		entity.setAreaLiquidacion(new BigDecimal("12.5"));
		entity.setValor(new BigDecimal("12.5"));
		entity.setValorTotal(new BigDecimal("12.5"));
		entity.setConsecutivo(2L);
		entity.setDescripcionLiquidacion(1L);
		entity.setDescripcionDependencia("tex7o otros usostexto otrosFIN");
		return entity;
	}

	private static LiquidacionAvaluoTotal generarLiquidacionTotal() {
		LiquidacionAvaluoTotal entity = new LiquidacionAvaluoTotal();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setAvaluoUvr(new BigDecimal("469.02"));
		entity.setCalificacion(1L);
		entity.setCalificacionDescripcion("FAVORABLE");
		entity.setNumCostoTransaccion(new BigDecimal("458.02"));
		entity.setValorUvrDia(new BigDecimal("459.02"));
		entity.setValorAsegurable(new BigDecimal("451.02"));
		entity.setTotalAvaluo(new BigDecimal("454.02"));
		return entity;
	}

	public static Avaluo generarAvaluo() {
		Avaluo entity = new Avaluo();
		entity.setBarrio("texto otros usost7xto otrosFIN");
		entity.setIdCiudad(1580L);
		entity.setIdDepartamento(1L);
		entity.setIdObjetoAvaluo(2L);
		entity.setTipoInforme(1L);
		entity.setLongitudSFBUA("-67.0000000");
		entity.setLatitudSFBUA("-3.0000000");
		entity.setJustificacion("3sdjkahsdkjhaskjdhas kdjkash djkashd kjash kjdh asjkdhasjk"
				+ "  4jsdh akj sh dkjfchwui9as dfpaisdh ahsg djash djkh askjdhas djhaskljdh"
				+ "a5kljdhas kljdhkajsdh kasjlhdjkashdas lkjdha slkjdhaskljdh asjkdh ajklsdh"
				+ " 6skjdhas kljdh ask jldh akj sasdjkahsdkjhaskjdhas kdjkash djkashd kjash"
				+ " 7jdh asjkdhasjk  kjsdh akj sh dkjfchwui9as dfpaisdh ahsg djash djkh "
				+ "a8kjdhas djhaskljdhaskljdhas kljdhkajsdh kasjlhdjkashdas lkjdha slkjdhas"
				+ "kl8dh asjkdh ajklsdh askjdhas kljdh ask jldh akj s jashdjkashdjkash dkjashd kjhaaFIN");
		entity.setIdTipoIdentificacion(21L);
		entity.setDireccionInmueble(
				"1tros usostexto otrostexto otros usostexto otrostexto otros usostexto texto otros usostexto otrosFIN");
		entity.setDireccionComplementaria(
				"2tros usostexto otrostexto otros usostexto otrostexto otros usostexto texto otros usostexto otrosFIN");
		entity.setSector(2L);
		entity.setNumeroIdentificacion(ThreadLocalRandom.current().nextLong(111111L, 9999999999L));
		entity.setNombreBanco("texto otros 7sostexto otrosFIN");
		entity.setNombreConjuntoEdificio("texto otros usostedto otrosFIN");
		entity.setCorreoSolicitante("texto otros usostexto otrosasstexto otros usostexto otrosFIN");
		entity.setNombreSolicitante("texto otros usostexto otrosasstexto otros usostexto otrosFIN");
		entity.setFechaAvaluo(new Date());
		String textoFecha = new SimpleDateFormat("ddMMyyyy").format(new Date());
		entity.setConsecutivoBanco(new BigInteger(textoFecha + entity.getNumeroIdentificacion()));
		return entity;
	}

	public static ComportamientoOfertaDemanda generarOferta() {
		ComportamientoOfertaDemanda entity = new ComportamientoOfertaDemanda();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setActualidadEdificadora("4sdjkahsdkjhaskj4has kdjkash djkashd kjash kjdh asjkdhasjk"
				+ "  kjsdh akj sh dkjfchwui9as dfpaisdh ahs4 djash djkh askjdhas djhaskljdh"
				+ "askljdhas kljdhkajsdh kasjlhdjkashdas lkj0ha slkjdhaskljdh asjkdh ajklsdh"
				+ " askjdhas kljdh ask jldh akj sasdjkahsdkj0askjdhas kdjkash djkashd kjash"
				+ " kjdh asjkdhasjk  kjsdh akj sh dkjfchwui99s dfpaisdh ahsg djash djkh "
				+ "askjdhas djhaskljdhaskljdhas kljdhkajsdh 8asjlhdjkashdas lkjdha slkjdhas"
				+ "kljdh asjkdh ajklsdh askjdhas kljdh ask jld6 akj s jashdjkashdjkash dkjashd kjhaaFIN");
		entity.setComportamiento("asdjkahsdkjhaskjdhas kdjkash djkashd kjash kjdh asjkdhasjk"
				+ "  kjsdh akj sh dkjfchwui9as dfpaisdh ahsg djash djkh askjdhas djhaskljdh"
				+ "askljdhas kljdhkajsdh kasjlhdjkashdas lkjdha slkjdhaskljdh asjkdh ajklsdh"
				+ " askjdhas kljdh ask jldh akj sasdjkahsdkjhaskjdhas kdjkash djkashd kjash"
				+ " kjdh asjkdhasjk  kjsdh akj sh dkjfchwui9as dfpaisdh ahsg djash djkh "
				+ "askjdhas djhaskljdhaskljdhas kljdhkajsdh kasjlhdjkashdas lkjdha slkjdhas"
				+ "kljdh asjkdh ajklsdh askjdhas kljdh ask jldh akj s jashdjkashdjkash dkjashd kjhaaFIN");
		entity.setTipoComercializacion(1L);
		return entity;
	}

	public static Observaciones generarObservaciones() {
		Observaciones entity = new Observaciones();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setDireccionAnexos("actualidad edificadora");
		entity.setOtrasDirecciones("comportamiento");
		entity.setObservacionAvaluo(
				"aqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo"
						+ " poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo"
						+ " poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner"
						+ " dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil"
						+ " caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui"
						+ " debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos"
						+ " mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui"
						+ " debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil"
						+ " caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo"
						+ " poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil "
						+ "caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui deb"
						+ "o poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil"
						+ " caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui d"
						+ "ebo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil"
						+ " caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui deb"
						+ "o poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caracteresaqui debo poner dos mil caract"
						+ "eresaqui debo poner dos mil caracterdos mil caracteresaqui debo poner dos mil caracterdos mil caracteresaqui debo poner dos mil caaFIN");
		return entity;
	}

	public static InformacionInmueble generarInmueble() {
		InformacionInmueble entity = new InformacionInmueble();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setCatastralSF("texto otros 1sostFIN");
		entity.setChip("texto otros 2sostFIN");
		entity.setClaseInmueble(1L);
		entity.setCiudadEscritura(1L);
		entity.setDepartamentoEscritura(1L);
		entity.setEdiContUso(1L);
		entity.setIdCategoria(1L);
		entity.setMatriculaInmobiliariaDeposito1("texto 3tros usostFIN");
		entity.setMatriculaInmobiliariaDeposito2("texto 4tros usostFIN");
		entity.setMatriculaInmobiliariaGaraje1("texto 5tros usostFIN");
		entity.setMatriculaInmobiliariaGaraje2("texto 6tros usostFIN");
		entity.setMatriculaInmobiliariaGaraje3("texto 7tros usostFIN");
		entity.setMatriculaInmobiliariaGaraje4("texto 8tros usostFIN");
		entity.setMatriculaInmobiliariaGaraje5("texto 9tros usostFIN");
		entity.setMatriculaInmobiliariaPpal1("texto 10ros usostFIN");
		entity.setMatriculaInmobiliariaPpal2("texto 11ros usostFIN");
		entity.setNotaria("12345");
		entity.setNumeroEscritura("12345");
		entity.setOtroClase("texto otros usostextoeozrosFIN");
		entity.setOtroUsoInmueble("texto otros usostexto otqosFIN");
		entity.setTipoVivienda(1L);
		entity.setUbicacion2(1L);
		entity.setUbicacion3(1L);
		entity.setUsuarioCreacion("texto otros usos");
		entity.setUsuarioTransaccion("texto otros usos");
		entity.setUsoInmueble(10L);
		entity.setUsoInmuebleBUA(1L);
		entity.setUsoInmuebleSF(1L);
		return entity;
	}

	public static InformacionBarrio generarBarrio() {
		InformacionBarrio entity = new InformacionBarrio();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setAcueductoPredio(1L);
		entity.setAcueductoSector(1L);
		entity.setAlcantarilladoPredio(1L);
		entity.setAlcantarilladoSector(1L);
		entity.setElectricidadPredio(1L);
		entity.setElectricidadSector(1L);
		entity.setGasPredio(1L);
		entity.setGasSector(1L);
		entity.setTelefonoPredio(1L);
		entity.setTelefonoSector(1L);
		entity.setVivienda(1L);
		entity.setIndustria(1L);
		entity.setComercio(1L);
		entity.setOtrosUsos(1L);
		entity.settextoOtrosUsos("texto otros usostexto oyrosFIN");
		entity.setEdificacionesIguales(10L);
		entity.setPavimentada(1L);
		entity.setAndenes(1L);
		entity.setSardeneles(1L);
		entity.setParques(1L);
		entity.setParadero(1L);
		entity.setAlumbrado(1L);
		entity.setZonasVerdes(1L);
		entity.setArborizacion(1L);
		entity.setAlamedas(1L);
		entity.setCicloRutas(1L);
		entity.setEstrato(3L);
		entity.setLegalidad(1L);
		entity.setTopografia(1L);
		entity.setTransporte(1L);
		entity.setPerspectivas("asdjkahsdkjhaskjdhas kdjkash djkashd kjash kjdh asjkdhasjk"
				+ "  kjsdh akj sh dkjfchwui9as dfpaisdh ahsg djash djkh askjdhas djhaskljdh"
				+ "askljdhas kljdhkajsdh kasjlhdjkashdas lkjdha slkjdhaskljdh asjkdh ajklsdh"
				+ " askjdhas kljdh ask jldh akj sasdjkahsdkjhaskjdhas kdjkash djkashd kjash"
				+ " kjdh asjkdhasjk  kjsdh akj sh dkjfchwui9as dfpaisdh ahsg djash djkh "
				+ "askjdhas djhaskljdhaskljdhas kljdhkajsdh kasjlhdjkashdas lkjdha slkjdhas"
				+ "kljdh asjkdh ajklsdh askjdhas kljdh ask jldh akj s jashdjkashdjkash dkjashd kjhaaFIN");
		return entity;
	}

	public static InformacionConstruccion generarConstruccion() {
		InformacionConstruccion entity = new InformacionConstruccion();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setPorteria(1L);
		entity.setPiscina(1L);
		entity.setGarajeVisitante(1L);
		entity.setBombaEyectora(1L);
		entity.setZonasVerdes(1L);
		entity.setCitofono(1L);
		entity.setTanqueAgua(1L);
		entity.setJuegoNinos(1L);
		entity.setAireAcondicionado(1L);
		entity.setGimnasio(1L);
		entity.setBicicletero(1L);
		entity.setClubHouse(1L);
		entity.setCanchaMultiple(1L);
		entity.setSquash(1L);
		entity.setGolfito(1L);
		entity.setSalonComunal(1L);
		entity.setSala(1L);
		entity.setBanioSocial(1L);
		entity.setBanioPrivado(1L);
		entity.setBanioServicio(1L);
		entity.setJardin(1L);
		entity.setComedor(1L);
		entity.setEstarHabitacion(1L);
		entity.setCocina(1L);
		entity.setPatioInterior(1L);
		entity.setBalcon(1L);
		entity.setEstudio(1L);
		entity.setNumeroHabitaciones(1L);
		entity.setCuartoServicio(1L);
		entity.setTerraza(1L);
		entity.setZonaVerdePrivada(1L);
		entity.setTotalGarajes(1L);
		entity.setUsoExclusivo(1L);
		entity.setSencillo(1L);
		entity.setDeposito(1L);
		entity.setLocal(1L);
		entity.setCubierto(1L);
		entity.setPrivado(1L);
		entity.setDoble(1L);
		entity.setBodega(1L);
		entity.setDescubierto(1L);
		entity.setBahiaComunal(1L);
		entity.setServidumbre(1L);
		entity.setOficina(1L);
		entity.setPlanta(1L);
		entity.setEstadoPisos(1L);
		entity.setCalidadPiso(1L);
		entity.setEstadoMuros(1L);
		entity.setCalidadMuro(1L);
		entity.setEstructuraTechos(1L);
		entity.setCalidadTecho(1L);
		entity.setEstadoMadera(1L);
		entity.setCalidadMadera(1L);
		entity.setEstadoBanios(1L);
		entity.setCalidadBanio(1L);
		entity.setEstadoCocina(1L);
		entity.setCalidadCocina(1L);
		entity.setShutBasuras(1L);
		entity.setPresion(1L);
		entity.setAscensor(1L);
		entity.setNumeroAscensores(3L);
		entity.setPisos(10L);
		entity.setNumeroEdificios(10L);
		entity.setUnidadPorPiso(10L);
		entity.setTotalUnidades(1000L);
		entity.setPropiedadHorizontal(1L);
		entity.setConjuntoAgrupacionCerrada(1L);
		entity.setUbicacionInmueble(1L);
		entity.setTextoFachada("texto otros usostex4o otrosFIN");
		entity.setTextoCubierta("texto otros usostext5 otrosFIN");
		entity.setTextoEstructura("texto otros usoste7to otrosFIN");
		entity.setNombreConstructora(
				"atros usost5xto otrostexto otros usostexto otrostexto otros usostexto texto otros usostexto otrosFIN");
		entity.setOtroMaterial("texto otros usostexto otrosFIN");
		entity.setOtraUbicacionTanque("texto otros usostexto otrosFIN");
		entity.setOtrosDotacion("asd asdadss sadadasdkjash kjdh asjkdhasjk  kjsdh akj"
				+ " sh dkjfchwui9as dfpaisdh ahsg djash djkh askjdhas djhaskljdhaskljdhas"
				+ " kljdhkajsdh kasjlhdjkashdas lkjdha slkjdhaskljdh asjkdh ajklsdh askjdhas"
				+ " kljdh ask jldh akj s jashdjkashdjkash dkjashd kjhaaFIN");
		return entity;
	}

	public static CondicionesSalubridad generarSalubridad() {
		CondicionesSalubridad entity = new CondicionesSalubridad();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setRequiereCondicionesSalubridad(1L);
		entity.setCondicionSalubridad(1L);
		entity.setAmbientalArborizacion(1L);
		entity.setAmbientalParques(1L);
		entity.setAmbientalZonaVerde(1L);
		entity.setImpactoNegativoPorAire(1L);
		entity.setImpactoNegativoBasura(1L);
		entity.setImpactoNegativoRuido(1L);
		entity.setAguasServidas(1L);
		entity.setAmbientalOtros(
				"atros usostexto otrostexto otros usostexto otrostexto otros usostexto texto otros usostexto otrosFIN");
		entity.setImpactoNegativoOtros(
				"atros usostexto otrostexto otros usostexto otrostexto otros usostexto texto otros usostexto otrosFIN");
		return entity;
	}

	public static ListaAnexosPdf generaFoto() throws IOException {
		ListaAnexosPdf anexo = new ListaAnexosPdf();
		FileInputStream entrada = new FileInputStream("C:\\tmp\\italy-1587287_640.jpg");
		anexo.setAnexo(IOUtils.toByteArray(entrada));
		anexo.setArchivo(new Archivo());
		anexo.getArchivo().setTamanioArchivo(1048L);
		anexo.getArchivo().setNombreArchivo("italy-1587287_640.jpg");
		anexo.setConsecutivoAnexo(1L);
		anexo.setEditarNombre(false);
		anexo.setFechaCreacion(new Date());
		anexo.setFechaTransaccion(new Date());
		anexo.setUsuarioCreacion(datos.get(0).getUsuarioCreacion());
		anexo.setUsuarioTransaccion(datos.get(0).getUsuarioTransaccion());
		anexo.setIdAvaluo(datos.get(0).getIdAvaluo());
		anexo.setNombreFotografia("italy-1587287_640.jpg");
		anexo.setPortada(true);
		return anexo;
	}

}
