package com.segurosbolivar.avaluos.modelo.service;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.segurosbolivar.avaluos.modelo.cons.SeccionConstruccion;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.service.impl.DiligenciamientoImpl;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

public class AvaluoFacadeTest {

	@EJB
	IAvaluoFacade avaluoFacade;
	@EJB
	IAvaluo avaluoSvc;
	@EJB
	AvaluoDao avaluoDao;

	UsuarioDto usuario;

	private Logger log = Logger.getLogger(AvaluoFacadeTest.class);

	public static List<Avaluo> datos;
	private List<ComportamientoOfertaDemanda> ofertas;
	private List<InformacionConstruccion> construcciones;
	private List<CondicionesSalubridad> salubridad;
	private List<InformacionBarrio> barrios;
	private List<ListaAnexosPdf> anexospdf;
	private List<InformacionInmueble> inmuebles;

	static {
		datos = new ArrayList<>();
	}

	// private final PodamFactory fabrica = new PodamFactoryImpl();

	@Before
	public void configTest() throws Exception {
		log.debug("Configurando Test " + AvaluoFacadeTest.class.getName());
		insertData();
		ofertas = new ArrayList<>();
		construcciones = new ArrayList<>();
		barrios = new ArrayList<>();
		salubridad = new ArrayList<>();
	}

	@After
	public void finTest() throws Exception {
		log.debug("finalizando Test " + AvaluoFacadeTest.class.getName());
		eliminarData();
	}

	private void eliminarData() {
		for (Avaluo avaluo : datos) {
			try {
				avaluoDao.eliminar(avaluo);
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}
	}

	private void insertData() {
		for (int i = 0; i < 10; i++) {
			guardarAvaluo();
		}
	}

	@Test
	public void guardarAvaluo() {
		try {
			Avaluo avaluo = generarAvaluo();
			// avaluoFacade.guardar(avaluo, usuario);
			datos.add(avaluo);
			Assert.assertNotNull(avaluo);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se guardo el avaluo");
		}
	}

	@Test
	public void actualizarAvaluo() {
		try {
			Avaluo avaluo = datos.get(0);
			avaluoFacade.guardar(avaluo, usuario);
			Assert.assertNotNull(avaluo);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizo el avaluo");
		}
	}

	@Test
	public void consultarAvaluo() {
		try {
			List<Avaluo> avaluos = avaluoFacade.consultar(new ConsultaAvaluoDto(), 0, 10, null, null);
			Assert.assertTrue(avaluos.size() > 0);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se pudo consultar el avaluo");
		}
	}

	public static Avaluo generarAvaluo() {
		Avaluo entity = new Avaluo();
		// entity = fabrica.manufacturePojo(Avaluo.class);
		entity.setBarrio("cajica");
		entity.setIdCiudad(1580L);
		entity.setIdDepartamento(1L);
		entity.setIdObjetoAvaluo(2L);
		entity.setTipoInforme(1L);
		entity.setLongitudSFBUA("-67.0000000");
		entity.setLatitudSFBUA("-3.0000000");
		entity.setNombreSolicitante("avaluo test case 1");
		entity.setIdTipoIdentificacion(21L);
		entity.setDireccionInmueble("finca las acacias");
		entity.setSector(2L);
		entity.setNumeroIdentificacion(ThreadLocalRandom.current().nextLong(111111L, 9999999999L));
		entity.setNombreBanco(UtilConstantes.NOMBRE_ENTIDAD_DAVIVIENDA);
		entity.setFechaAvaluo(new Date());
		String textoFecha = new SimpleDateFormat("ddMMYYY").format(new Date());
		entity.setConsecutivoBanco(new BigInteger(textoFecha + entity.getNumeroIdentificacion()));
		return entity;
	}

	@Test
	public void guardarOferta() {
		try {
			ComportamientoOfertaDemanda guardar = generarOferta();
			avaluoFacade.guardar(guardar, usuario);
			ofertas.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se creo la oferta");
		}
	}

	@Test
	public void actualizarOferta() {
		try {
			avaluoFacade.guardar(ofertas.get(0), usuario);
			Assert.assertNotNull(ofertas.get(0));
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizo la oferta");
		}
	}

	public static ComportamientoOfertaDemanda generarOferta() {
		ComportamientoOfertaDemanda entity = new ComportamientoOfertaDemanda();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setActualidadEdificadora("actualidad edificadora");
		entity.setComportamiento("comportamiento");
		entity.setTipoComercializacion(1L);
		return entity;

	}

	@Test
	public void guardarConstruccionDotacion() {
		try {
			InformacionConstruccion guardar = generarDotacion();
			avaluoFacade.guardar(guardar, SeccionConstruccion.DOTACION_COMUNAL, datos.get(0), usuario);
			construcciones.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se creo la dotacion");
		}
	}

	@Test
	public void actualizarConstruccionDotacion() {
		try {
			avaluoFacade.guardar(construcciones.get(0), SeccionConstruccion.DOTACION_COMUNAL, datos.get(0), usuario);
			Assert.assertNotNull(construcciones.get(0));
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizo la dotacion");
		}
	}

	public static InformacionConstruccion generarDotacion() {
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
		entity.setPlanta(1L);
		entity.setShutBasuras(1L);
		entity.setPresion(1L);
		entity.setAscensor(1L);
		entity.setNumeroAscensores(3L);
		entity.setOtrosDotacion("OtrosDotacion");
		return entity;

	}

	@Test
	public void guardarConstruccionHorizontal() {
		try {
			InformacionConstruccion guardar = generarHorizontal();
			avaluoFacade.guardar(guardar, SeccionConstruccion.PROPIEDAD_HORIZONTAL, datos.get(0), usuario);
			construcciones.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se creo la propiedad horizontal");
		}
	}

	@Test
	public void actualizarConstruccionHorizontal() {
		try {
			avaluoFacade.guardar(construcciones.get(1), SeccionConstruccion.PROPIEDAD_HORIZONTAL, datos.get(0),
					usuario);
			Assert.assertNotNull(construcciones.get(1));
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizo la propiedad horizontal");
		}
	}

	@Test
	public void errorTotalUnidadesHorizontal() {
		try {
			InformacionConstruccion guardar = construcciones.get(1);
			guardar.setPisos(50L);
			guardar.setTotalUnidades(4L);
			avaluoFacade.guardar(construcciones.get(1), SeccionConstruccion.PROPIEDAD_HORIZONTAL, datos.get(0),
					usuario);
			Assert.fail("Se esperaba error");
		} catch (Exception e) {

		}
	}

	public static InformacionConstruccion generarHorizontal() {
		InformacionConstruccion entity = new InformacionConstruccion();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setPisos(10L);
		entity.setNumeroEdificios(10L);
		entity.setUnidadPorPiso(10L);
		entity.setTotalUnidades(1000L);
		entity.setPropiedadHorizontal(1L);
		entity.setConjuntoAgrupacionCerrada(1L);
		entity.setUbicacionInmueble(1L);
		return entity;
	}

	@Test
	public void guardarConstruccionAcabados() {
		try {
			InformacionConstruccion guardar = generarAcabados();
			avaluoFacade.guardar(guardar, SeccionConstruccion.ACABADOS, datos.get(0), usuario);
			construcciones.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se crearon los acabados");
		}
	}

	@Test
	public void actualizarConstruccionAcabados() {
		try {
			avaluoFacade.guardar(construcciones.get(2), SeccionConstruccion.ACABADOS, datos.get(0), usuario);
			Assert.assertNotNull(construcciones.get(2));
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizaron los acabados");
		}
	}

	public static InformacionConstruccion generarAcabados() {
		InformacionConstruccion entity = new InformacionConstruccion();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
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
		return entity;
	}

	@Test
	public void guardarConstruccionDependencias() {
		try {
			InformacionConstruccion guardar = generarDependencias();
			avaluoFacade.guardar(guardar, SeccionConstruccion.DEPENDECIAS, datos.get(0), usuario);
			construcciones.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se crearon las dependencias");
		}
	}

	@Test
	public void actualizarConstruccionDependencias() {
		try {
			avaluoFacade.guardar(construcciones.get(3), SeccionConstruccion.DEPENDECIAS, datos.get(0), usuario);
			Assert.assertNotNull(construcciones.get(3));
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizaron las dependencias");
		}
	}

	public static InformacionConstruccion generarDependencias() {
		InformacionConstruccion entity = new InformacionConstruccion();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
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
		return entity;
	}

	@Test
	public void guardarConstruccionSalubridad() {
		try {
			CondicionesSalubridad guardar = generarSalubridad();
			avaluoFacade.guardar(guardar, usuario);
			salubridad.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se creo la salubridad");
		}
	}

	@Test
	public void actualizarConstruccionSalubridad() {
		try {
			avaluoFacade.guardar(salubridad.get(0), usuario);
			Assert.assertNotNull(salubridad.get(0));
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizo los salubridad");
		}
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
		entity.setAmbientalOtros("ambiental Otros");
		entity.setImpactoNegativoOtros("impacto Negativo Otros");
		return entity;
	}

	@Test
	public void guardarBarrio() {
		try {
			InformacionBarrio guardar = generarBarrio();
			avaluoFacade.guardar(guardar, usuario);
			barrios.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se creo el barrio");
		}
	}

	@Test
	public void guardarInmueble() {
		try {
			InformacionInmueble guardar = generarInmueble();
			avaluoFacade.guardar(guardar, datos.get(0), usuario);
			inmuebles.add(guardar);
			Assert.assertNotNull(guardar);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se creo el barrio");
		}
	}

	public static InformacionInmueble generarInmueble() {
		InformacionInmueble entity = new InformacionInmueble();
		entity.setIdAvaluo(datos.get(0).getIdAvaluo());
		entity.setCatastralSF("catastra");
		entity.setChip("chip");
		entity.setClaseInmueble(1L);
		entity.setCiudadEscritura(1L);
		entity.setDepartamentoEscritura(1L);
		entity.setEdiContUso(1L);
		entity.setIdCategoria(1L);
		entity.setMatriculaInmobiliariaDeposito1("matricula");
		entity.setMatriculaInmobiliariaDeposito2("matricula");
		entity.setMatriculaInmobiliariaGaraje1("matricula");
		entity.setMatriculaInmobiliariaGaraje2("matricula");
		entity.setMatriculaInmobiliariaGaraje3("matricula");
		entity.setMatriculaInmobiliariaGaraje4("matricula");
		entity.setMatriculaInmobiliariaGaraje5("matricula");
		entity.setMatriculaInmobiliariaPpal1("matricula");
		entity.setMatriculaInmobiliariaPpal2("matricula");
		entity.setNotaria("notaria");
		entity.setNumeroEscritura("asdfasd");
		entity.setOtroClase("otroclas");
		entity.setOtroUsoInmueble("otro");
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

	@Test
	public void actualizarBarrio() {
		try {
			avaluoFacade.guardar(barrios.get(0), usuario);
			Assert.assertNotNull(barrios.get(0));
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se actualizo el barrio");
		}
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
		entity.settextoOtrosUsos("texto otros usos");
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
		entity.setPerspectivas("perpestivas");
		return entity;
	}

	@Test
	public void guardarRegistroFotografico() {
		try {
			ListaAnexosPdf fotos = generaFoto();
			AnexoFotografico anexoFoto = new AnexoFotografico();
			List<ListaAnexosPdf> listaAnexos = new ArrayList<>();
			listaAnexos.add(fotos);
			DiligenciamientoImpl diligencia = new DiligenciamientoImpl();
			diligencia.guardar(anexoFoto, listaAnexos, datos.get(0), usuario);
			anexospdf.add(fotos);
			Assert.assertNotNull(fotos);
		} catch (Exception e) {
			log.debug(e.getMessage());
			Assert.fail("No se guardo el registro fotografico");
		}
	}

	public static ListaAnexosPdf generaFoto() throws Exception {
		try {
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
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
