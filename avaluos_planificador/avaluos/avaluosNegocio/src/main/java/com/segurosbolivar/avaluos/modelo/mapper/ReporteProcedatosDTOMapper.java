package com.segurosbolivar.avaluos.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.ReporteProcedatosDTO;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

@Mapper (uses = { HistoricoAvaluoFullDTOMapper.class, LiquidacionAvaluoFullDTOMapper.class, ListaAnexosPdfFullDTOMapper.class, AlertaAvaluosFullDTOMapper.class } )
public interface ReporteProcedatosDTOMapper {
	
	ReporteProcedatosDTOMapper MAPPER = Mappers.getMapper(ReporteProcedatosDTOMapper.class);
	
	@Mappings ( {
		@Mapping( target = "codigoDaneCiudad", source = "codigoDaneCiudad", defaultValue = "0" ),
		@Mapping( target = "andenes", source = "informacionBarrio.andenes", defaultValue = "0" ),
		@Mapping( target = "calidadBanio", source = "informacionConstruccion.calidadBanio", defaultValue = "0" ),
		@Mapping( target = "calidadMadera", source = "informacionConstruccion.calidadMadera", defaultValue = "0" ),
		@Mapping( target = "calidMetal", source = "informacionConstruccion.calidMetal", defaultValue = "0" ),
		@Mapping( target = "calidadCocina", source = "informacionConstruccion.calidadCocina", defaultValue = "0" ),
		@Mapping( target = "calidadMuro", source = "informacionConstruccion.calidadMuro", defaultValue = "0" ),
		@Mapping( target = "calidadPiso", source = "informacionConstruccion.calidadPiso", defaultValue = "0" ),
		@Mapping( target = "calidadTecho", source = "informacionConstruccion.calidadTecho", defaultValue = "0" ),
		@Mapping( target = "ciudadEscritura", source = "informacionInmueble.ciudadEscritura", defaultValue = "0" ),
		@Mapping( target = "claseInmueble", source = "informacionInmueble.claseInmueble", defaultValue = "0" ),
		@Mapping( target = "conjuntoAgrupacionCerrada", source = "informacionConstruccion.conjuntoAgrupacionCerrada", defaultValue = "0" ),
		@Mapping( target = "cubierta", source = "informacionConstruccion.cubierta", defaultValue = "0" ),
		@Mapping( target = "departamentoEscritura", source = "informacionInmueble.departamentoEscritura", defaultValue = "0" ),
		@Mapping( target = "estadoBanios", source = "informacionConstruccion.estadoBanios", defaultValue = "0" ),
		@Mapping( target = "estadoCocina", source = "informacionConstruccion.estadoCocina", defaultValue = "0" ),
		@Mapping( target = "estadoConservacion", source = "informacionConstruccion.estadoConservacion", defaultValue = "0" ),
		@Mapping( target = "estadoMadera", source = "informacionConstruccion.estadoMadera", defaultValue = "0" ),
		@Mapping( target = "estadoMetal", source = "informacionConstruccion.estadoMetal", defaultValue = "0" ),
		@Mapping( target = "estadoMuros", source = "informacionConstruccion.estadoMuros", defaultValue = "0" ),
		@Mapping( target = "estadoPisos", source = "informacionConstruccion.estadoPisos", defaultValue = "0" ),
		@Mapping( target = "estrato", source = "informacionBarrio.estrato", defaultValue = "0" ),
		@Mapping( target = "estructura", source = "informacionConstruccion.estructura", defaultValue = "0" ),
		@Mapping( target = "estructuraTechos", source = "informacionConstruccion.estructuraTechos", defaultValue = "0" ),
		@Mapping( target = "estadosViaAcceso", source = "informacionBarrio.estadosViaAcceso", defaultValue = "0" ),
		@Mapping( target = "fachada", source = "informacionConstruccion.fachada", defaultValue = "0" ),
		@Mapping( target = "iluminacion", source = "informacionConstruccion.iluminacion", defaultValue = "0" ),
		@Mapping( target = "legalidad", source = "informacionBarrio.legalidad", defaultValue = "0" ),
		@Mapping( target = "pavimentada", source = "informacionBarrio.pavimentada", defaultValue = "0" ),
		@Mapping( target = "pisosBodega", source = "informacionConstruccion.pisosBodega", defaultValue = "0" ),
		@Mapping( target = "propiedadHorizontal", source = "informacionConstruccion.propiedadHorizontal", defaultValue = "0" ),
		@Mapping( target = "condicionSalubridad", source = "condicionSalubridad.condicionSalubridad", defaultValue = "0" ),
		@Mapping( target = "sardeneles", source = "informacionBarrio.sardeneles", defaultValue = "0" ),
		@Mapping( target = "tipoFachada", source = "informacionConstruccion.tipoFachada", defaultValue = "0" ),
		@Mapping( target = "tipoVivienda", source = "informacionInmueble.tipoVivienda", defaultValue = "0" ),
		@Mapping( target = "topografia", source = "informacionBarrio.topografia", defaultValue = "0" ),
		@Mapping( target = "transporte", source = "informacionBarrio.transporte", defaultValue = "0" ),
		@Mapping( target = "ubicacionInmueble", source = "informacionConstruccion.ubicacionInmueble", defaultValue = "0" ),
		@Mapping( target = "ubicacion2", source = "informacionInmueble.ubicacion2", defaultValue = "0" ),
		@Mapping( target = "usoInmueble", source = "informacionInmueble.usoInmueble", defaultValue = "0" ),
		@Mapping( target = "ventilacion", source = "informacionConstruccion.ventilacion", defaultValue = "0" ),
		@Mapping( target = "fechaEscritura", source = "informacionInmueble.fechaEscritura" ),
		@Mapping( target = "aireAcondicionado", source = "informacionConstruccion.aireAcondicionado", defaultValue = "0" ),
		@Mapping( target = "acueductoPredio", source = "informacionBarrio.acueductoPredio", defaultValue = "0" ),
		@Mapping( target = "acueductoSector", source = "informacionBarrio.acueductoSector", defaultValue = "0" ),
		@Mapping( target = "aguasServidas", source = "condicionSalubridad.aguasServidas", defaultValue = "0" ),
		@Mapping( target = "alamedas", source = "informacionBarrio.alamedas", defaultValue = "0" ),
		@Mapping( target = "alcantarilladoPredio", source = "informacionBarrio.alcantarilladoPredio", defaultValue = "0" ),
		@Mapping( target = "alcantarilladoSector", source = "informacionBarrio.alcantarilladoSector", defaultValue = "0" ),
		@Mapping( target = "alumbrado", source = "informacionBarrio.alumbrado", defaultValue = "0" ),
		@Mapping( target = "ambientalArborizacion", source = "condicionSalubridad.ambientalArborizacion", defaultValue = "0" ),
		@Mapping( target = "ambientalParques", source = "condicionSalubridad.ambientalParques", defaultValue = "0" ),
		@Mapping( target = "ambientalZonaVerde", source = "condicionSalubridad.ambientalZonaVerde", defaultValue = "0" ),
		@Mapping( target = "arborizacion", source = "informacionBarrio.arborizacion", defaultValue = "0" ),
		@Mapping( target = "ascensor", source = "informacionConstruccion.ascensor", defaultValue = "0" ),
		@Mapping( target = "impactoNegativoBasura", source = "condicionSalubridad.impactoNegativoBasura", defaultValue = "0" ),
		@Mapping( target = "bicicletero", source = "informacionConstruccion.bicicletero", defaultValue = "0" ),
		@Mapping( target = "bombaEyectora", source = "informacionConstruccion.bombaEyectora", defaultValue = "0" ),
		@Mapping( target = "canchaMultiple", source = "informacionConstruccion.canchaMultiple", defaultValue = "0" ),
		@Mapping( target = "cicloRutas", source = "informacionBarrio.cicloRutas", defaultValue = "0" ),
		@Mapping( target = "citofono", source = "informacionConstruccion.citofono", defaultValue = "0" ),
		@Mapping( target = "clubHouse", source = "informacionConstruccion.clubHouse", defaultValue = "0" ),
		@Mapping( target = "comercio", source = "informacionBarrio.comercio", defaultValue = "0" ),
		@Mapping( target = "electricidadPredio", source = "informacionBarrio.electricidadPredio", defaultValue = "0" ),
		@Mapping( target = "electricidadSector", source = "informacionBarrio.electricidadSector", defaultValue = "0" ),
		@Mapping( target = "enObra", source = "informacionConstruccion.enObra", defaultValue = "0" ),
		@Mapping( target = "estadoRemodelacion", source = "informacionConstruccion.estadoRemodelacion", defaultValue = "0" ),
		@Mapping( target = "estadoTerminadaNueva", source = "informacionConstruccion.estadoTerminadaNueva", defaultValue = "0" ),
		@Mapping( target = "estadoTerminadoUsado", source = "informacionConstruccion.estadoTerminadoUsado", defaultValue = "0" ),
		@Mapping( target = "gasPredio", source = "informacionBarrio.gasPredio", defaultValue = "0" ),
		@Mapping( target = "gasSector", source = "informacionBarrio.gasSector", defaultValue = "0" ),
		@Mapping( target = "gimnasio", source = "informacionConstruccion.gimnasio", defaultValue = "0" ),
		@Mapping( target = "garajeVisitante", source = "informacionConstruccion.garajeVisitante", defaultValue = "0" ),
		@Mapping( target = "golfito", source = "informacionConstruccion.golfito", defaultValue = "0" ),
		@Mapping( target = "industria", source = "informacionBarrio.industria", defaultValue = "0" ),
		@Mapping( target = "impactoNegativoInseguridad", source = "condicionSalubridad.impactoNegativoInseguridad", defaultValue = "0" ),
		@Mapping( target = "juegoNinos", source = "informacionConstruccion.juegoNinos", defaultValue = "0" ),
		@Mapping( target = "otrosUsos", source = "informacionBarrio.otrosUsos", defaultValue = "0" ),
		@Mapping( target = "paradero", source = "informacionBarrio.paradero", defaultValue = "0" ),
		@Mapping( target = "parques", source = "informacionBarrio.parques", defaultValue = "0" ),
		@Mapping( target = "piscina", source = "informacionConstruccion.piscina", defaultValue = "0" ),
		@Mapping( target = "planta", source = "informacionConstruccion.planta", defaultValue = "0" ),
		@Mapping( target = "impactoNegativoPorAire", source = "condicionSalubridad.impactoNegativoPorAire", defaultValue = "0" ),		//		PGB_CONDSALUBRIDAD	K_PORAIRE
		@Mapping( target = "porteria", source = "informacionConstruccion.porteria", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_PORTERIA
		@Mapping( target = "presion", source = "informacionConstruccion.presion", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_PRESION
		@Mapping( target = "impactoNegativoRuido", source = "condicionSalubridad.impactoNegativoRuido", defaultValue = "0" ),		//		PGB_CONDSALUBRIDAD	K_RUIDO
		@Mapping( target = "salonComunal", source = "informacionConstruccion.salonComunal", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_SALONCOMN
		@Mapping( target = "shutBasuras", source = "informacionConstruccion.shutBasuras", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_SHUT
		@Mapping( target = "sinTerminar", source = "informacionConstruccion.sinTerminar", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_SINTERMINAR
		@Mapping( target = "squash", source = "informacionConstruccion.squash", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_SQUASH
		@Mapping( target = "tanqueAgua", source = "informacionConstruccion.tanqueAgua", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_TANQUEAGUA
		@Mapping( target = "telefonoPredio", source = "informacionBarrio.telefonoPredio", defaultValue = "0" ),		//		PGB_INFBARRIO	K_TELPREDIO
		@Mapping( target = "telefonoSector", source = "informacionBarrio.telefonoSector", defaultValue = "0" ),		//		PGB_INFBARRIO	K_TELSECTOR
		@Mapping( target = "vivienda", source = "informacionBarrio.vivienda", defaultValue = "0" ),		//		PGB_INFBARRIO	K_VIVIENDA
		@Mapping( target = "zonasVerdes", source = "informacionBarrio.zonasVerdes", defaultValue = "0" ),		//		PGB_INFBARRIO	K_ZONASVERDES
		@Mapping( target = "zonasVerdesInfoConstruccion", source = "informacionConstruccion.zonasVerdes", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	K_ZVERDES
		@Mapping( target = "avaluoUvr", source = "liquidacionTotal.avaluoUvr", defaultValue = "0" ),		//		PGB_LIQAVALUO_TOTAL	N_AVALUOUVR
		@Mapping( target = "bahiaComunal", source = "informacionConstruccion.bahiaComunal", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_BAHIACOMUNAL
		@Mapping( target = "balcon", source = "informacionConstruccion.balcon", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_BALCON
		@Mapping( target = "banioPrivado", source = "informacionConstruccion.banioPrivado", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_BANIOPRIVADO
		@Mapping( target = "banioServicio", source = "informacionConstruccion.banioServicio", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_BANIOSERVICIO
		@Mapping( target = "banioSocial", source = "informacionConstruccion.banioSocial", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_BANIOSOCIAL
		@Mapping( target = "bodega", source = "informacionConstruccion.bodega", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_BODEGA
		@Mapping( target = "cocina", source = "informacionConstruccion.cocina", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_COCINA
		@Mapping( target = "comedor", source = "informacionConstruccion.comedor", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_COMEDOR
		@Mapping( target = "cuartoServicio", source = "informacionConstruccion.cuartoServicio", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_CUARTOSERV
		@Mapping( target = "cubierto", source = "informacionConstruccion.cubierto", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_CUBIERTO
		@Mapping( target = "deposito", source = "informacionConstruccion.deposito", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_DEPOSITO
		@Mapping( target = "descubierto", source = "informacionConstruccion.descubierto", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_DESCUBIERTO
		@Mapping( target = "doble", source = "informacionConstruccion.doble", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_DOBLE
		@Mapping( target = "estarHabitacion", source = "informacionConstruccion.estarHabitacion", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_ESTARHAB
		@Mapping( target = "estudio", source = "informacionConstruccion.estudio", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_ESTUDIO
		@Mapping( target = "numeroHabitaciones", source = "informacionConstruccion.numeroHabitaciones", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_HABITACIONES
		@Mapping( target = "jardin", source = "informacionConstruccion.jardin", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_JARDIN
		@Mapping( target = "local", source = "informacionConstruccion.local", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_LOCAL
		@Mapping( target = "numeroAscensores", source = "informacionConstruccion.numeroAscensores", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_NUMASCENSORES
		@Mapping( target = "numeroEdificios", source = "informacionConstruccion.numeroEdificios", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_NUMEDIF
		@Mapping( target = "oficina", source = "informacionConstruccion.oficina", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_OFICINA
		@Mapping( target = "patioInterior", source = "informacionConstruccion.patioInterior", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_PATIOINT
		@Mapping( target = "pisos", source = "informacionConstruccion.pisos", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_PISOS
		@Mapping( target = "privado", source = "informacionConstruccion.privado", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_PRIVADO
		@Mapping( target = "sala", source = "informacionConstruccion.sala", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_SALA
		@Mapping( target = "sencillo", source = "informacionConstruccion.sencillo", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_SENCILLO
		@Mapping( target = "servidumbre", source = "informacionConstruccion.servidumbre", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_SERVIDUMBRE
		@Mapping( target = "sotanos", source = "informacionConstruccion.sotanos", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_SOTANOS
		@Mapping( target = "terraza", source = "informacionConstruccion.terraza", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_TERRAZA
		@Mapping( target = "tipoComercializacion", source = "compOfertaDemanda.tipoComercializacion", defaultValue = "0" ),		//		PGB_COMP_OFERTA_DEMANDA	N_TIPOCOMERCIALIZA
		@Mapping( target = "totalAvaluo", source = "liquidacionTotal.totalAvaluo", defaultValue = "0" ),		//		PGB_LIQAVALUO_TOTAL	N_TOTALAVALUO
		@Mapping( target = "totalGarajes", source = "informacionConstruccion.totalGarajes", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_TOTALGARAJES
		@Mapping( target = "totalUnidades", source = "informacionConstruccion.totalUnidades", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_TOTALUND
		@Mapping( target = "unidadPorPiso", source = "informacionConstruccion.unidadPorPiso", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_UNDPISO
		@Mapping( target = "usoExclusivo", source = "informacionConstruccion.usoExclusivo", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_USOEXCLUSIVO
		@Mapping( target = "valorAsegurable", source = "liquidacionTotal.valorAsegurable", defaultValue = "0" ),		//		PGB_LIQAVALUO_TOTAL	N_VALORASEGURABLE
		@Mapping( target = "valorUvrDia", source = "liquidacionTotal.valorUvrDia", defaultValue = "0" ),		//		PGB_LIQAVALUO_TOTAL	N_VALUVRDIA
		@Mapping( target = "vetustez", source = "informacionConstruccion.vetustez", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_VETUSTEZ
		@Mapping( target = "zonaVerdePrivada", source = "informacionConstruccion.zonaVerdePrivada", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_ZVERDEPRIV
		@Mapping( target = "calificacion", source = "liquidacionTotal.calificacion", defaultValue = "0" ),		//		PGB_LIQAVALUO_TOTAL	R_CALIFICACION
		@Mapping( target = "estadoConstruccion", source = "informacionConstruccion.estadoConstruccion", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	R_ESTCONS
		@Mapping( target = "actualidadEdificadora", source = "compOfertaDemanda.actualidadEdificadora", defaultValue = "0" ),		//		PGB_COMP_OFERTA_DEMANDA	T_ACTEDIFICADORA
		@Mapping( target = "impactoNegativoOtros", source = "condicionSalubridad.impactoNegativoOtros", defaultValue = "0" ),		//		PGB_CONDSALUBRIDAD	T_AMBNEGOTRO
		@Mapping( target = "ambientalOtros", source = "condicionSalubridad.ambientalOtros", defaultValue = "0" ),		//		PGB_CONDSALUBRIDAD	T_AMBOTRO
		@Mapping( target = "avanceObra", source = "informacionConstruccion.avanceObra", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	T_AVANCEOBRA
		@Mapping( target = "chip", source = "informacionInmueble.chip", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_CHIP
		@Mapping( target = "comportamiento", source = "compOfertaDemanda.comportamiento", defaultValue = "0" ),		//		PGB_COMP_OFERTA_DEMANDA	T_COMPORTAOD
		@Mapping( target = "textoCubierta", source = "informacionConstruccion.cubierta", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	T_CUBIERTA
		@Mapping( target = "direccionAnexos", source = "observacion.direccionAnexos", defaultValue = "0" ),		//		PGB_OBSERVACIONES	T_DIRANEXOS
		@Mapping( target = "textoEstructura", source = "informacionConstruccion.estructura", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	T_ESTRUCTURA
		@Mapping( target = "textoFachada", source = "informacionConstruccion.fachada", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	T_FACHADA
		@Mapping( target = "matriculaInmobiliariaPpal1", source = "informacionInmueble.matriculaInmobiliariaPpal1", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMBPPAL1
		@Mapping( target = "matriculaInmobiliariaPpal2", source = "informacionInmueble.matriculaInmobiliariaPpal2", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMBPPAL2
		@Mapping( target = "matriculaInmobiliariaDeposito1", source = "informacionInmueble.matriculaInmobiliariaDeposito1", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMOBDP1
		@Mapping( target = "matriculaInmobiliariaDeposito2", source = "informacionInmueble.matriculaInmobiliariaDeposito2", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMOBDP2
		@Mapping( target = "matriculaInmobiliariaGaraje1", source = "informacionInmueble.matriculaInmobiliariaGaraje1", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMOBGJ1
		@Mapping( target = "matriculaInmobiliariaGaraje2", source = "informacionInmueble.matriculaInmobiliariaGaraje2", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMOBGJ2
		@Mapping( target = "matriculaInmobiliariaGaraje3", source = "informacionInmueble.matriculaInmobiliariaGaraje3", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMOBGJ3
		@Mapping( target = "matriculaInmobiliariaGaraje4", source = "informacionInmueble.matriculaInmobiliariaGaraje4", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMOBGJ4
		@Mapping( target = "matriculaInmobiliariaGaraje5", source = "informacionInmueble.matriculaInmobiliariaGaraje5", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_MINMOBGJ5
		@Mapping( target = "notaria", source = "informacionInmueble.notaria", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_NOTARIA
		@Mapping( target = "numeroEscritura", source = "informacionInmueble.numeroEscritura", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_NUMESCRITURA
		@Mapping( target = "otrasDirecciones", source = "observacion.otrasDirecciones", defaultValue = "0" ),		//		PGB_OBSERVACIONES	T_OTRASDIR
		@Mapping( target = "otroClase", source = "informacionInmueble.otroClase", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_OTROCLASE
		@Mapping( target = "otrosDotacion", source = "informacionConstruccion.otrosDotacion", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	T_OTROSDOTACION
		@Mapping( target = "otrosUsosBarrio", source = "informacionBarrio.otrosUsos", defaultValue = "0" ),		//		PGB_INFBARRIO	T_OTROSUSOS
		@Mapping( target = "otroUsoInmueble", source = "informacionInmueble.otroUsoInmueble", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_OTROUSOINM
		@Mapping( target = "perspectivas", source = "informacionBarrio.perspectivas", defaultValue = "0" ),		//		PGB_INFBARRIO	T_PERSPECTIVAS
		@Mapping( target = "altura", source = "informacionConstruccion.altura", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_ALTURA
		@Mapping( target = "ubicacion3", source = "informacionInmueble.ubicacion3", defaultValue = "0" ),		//		PGB_INFINMUEBLE	C_UBICACION3
		@Mapping( target = "reparadosBUA", source = "informacionConstruccion.reparadosBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_REPARADOS
		@Mapping( target = "irregularidadAlturaBUA", source = "informacionConstruccion.irregularidadAlturaBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_IRRALTURA
		@Mapping( target = "irregularidadPlantaBUA", source = "informacionConstruccion.irregularidadPlantaBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_IRRPLANTA
		@Mapping( target = "estructuraReforzadaBUA", source = "informacionConstruccion.estructuraReforzadaBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_ESTRUCTURAREFORZADA
		@Mapping( target = "danoPrevioBUA", source = "informacionConstruccion.danoPrevioBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_DANOPREVIO
		@Mapping( target = "anoConstruccionBUA", source = "informacionConstruccion.anoConstruccionBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	N_ANOCONSTRUCCION
		@Mapping( target = "materialConstruccionBUA", source = "informacionConstruccion.materialConstruccionBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_MATERIAL
		@Mapping( target = "detalleMaterialSFBUA", source = "informacionConstruccion.detalleMaterialSFBUA", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_DETALLEMATERIAL
		@Mapping( target = "idCategoria", source = "informacionInmueble.idCategoria", defaultValue = "0" ),		//		PGB_INFINMUEBLE	IDCATEGORIA
		@Mapping( target = "simetriaPlanta", source = "informacionConstruccion.simetriaPlanta", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_EDISIMPLANTA
		@Mapping( target = "materialEstructura", source = "informacionConstruccion.materialEstructura", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_EDIMAT
		@Mapping( target = "otroMaterial", source = "informacionConstruccion.otroMaterial", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_OTRO_MATERIAL
		@Mapping( target = "parapetosCubierta", source = "informacionConstruccion.parapetosCubierta", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_EDIPARAPET
		@Mapping( target = "ubicacionTanque", source = "informacionConstruccion.ubicacionTanque", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_DCELEVTANQ
		@Mapping( target = "otraUbicacionTanque", source = "informacionConstruccion.otraUbicacionTanque", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_OTRA_UBIC_TANQUE
		@Mapping( target = "sobrePeso", source = "informacionConstruccion.sobrePeso", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_SOBREPESO
		@Mapping( target = "golpeteo", source = "informacionConstruccion.golpeteo", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_GOLPETEO
		@Mapping( target = "asentamientos", source = "informacionConstruccion.asentamientos", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_ASENTAMIENTOS
		@Mapping( target = "danoPrevio", source = "informacionConstruccion.danoPrevio", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_DANOPREVIO
		@Mapping( target = "requiereCondicionesSalubridad", source = "condicionSalubridad.requiereCondicionesSalubridad", defaultValue = "0" ),		//		PGB_CONDSALUBRIDAD	A_REQUIERE_COND_SALUB
		@Mapping( target = "pisoInmueble", source = "informacionConstruccion.pisoInmueble", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_EDIPISO
		@Mapping( target = "simetriaAltura", source = "informacionConstruccion.simetriaAltura", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_EDISIMALT
		@Mapping( target = "ubicacionFuentesHidricas", source = "informacionConstruccion.ubicacionFuentesHidricas", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_UBICFUENTESH
		@Mapping( target = "ubicacionNivelVia", source = "informacionConstruccion.ubicacionNivelVia", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	 A_UBICNIVELZ 
		@Mapping( target = "edificacionesIguales", source = "informacionBarrio.edificacionesIguales", defaultValue = "0" ),		//		PGB_INFBARRIO	A_EDIIGUALES
		@Mapping( target = "luces", source = "informacionConstruccion.luces", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	 A_LUCES 
		@Mapping( target = "ediContUso", source = "informacionInmueble.ediContUso", defaultValue = "0" ),		//		PGB_INFINMUEBLE	A_EDICONSTUSO
		@Mapping( target = "rangoConstruccionSF", source = "informacionConstruccion.rangoConstruccionSF", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_RCONS_SF
		@Mapping( target = "catastralSF", source = "informacionInmueble.catastralSF", defaultValue = "0" ),		//		PGB_INFINMUEBLE	T_CATASTRAL_SF
		@Mapping( target = "danoReparadoSF", source = "informacionConstruccion.danoReparadoSF", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_DANOREPARADO_SF
		@Mapping( target = "simetriaAlturaSF", source = "informacionConstruccion.simetriaAlturaSF", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	SIMETRIA_ALT_SF
		@Mapping( target = "simetriaPlantaSF", source = "informacionConstruccion.simetriaPlantaSF", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	SIMETRIA_PLANT_SF
		@Mapping( target = "danoPrevioSF", source = "informacionConstruccion.danoPrevioSF", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	A_DANOPREVIO_SF
		@Mapping( target = "estructuraReforzadaSF", source = "informacionConstruccion.estructuraReforzadaSF", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_ESTREFORZADA_SF
		@Mapping( target = "materialConstruccionSF", source = "informacionConstruccion.materialConstruccionSF", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	C_MATERIAL_SF
		@Mapping( target = "usoInmuebleBUA", source = "informacionInmueble.usoInmuebleBUA", defaultValue = "0" ),		//		PGB_INFINMUEBLE	C_USOINMUEBLE_BUA
		@Mapping( target = "usoInmuebleSF", source = "informacionInmueble.usoInmuebleSF", defaultValue = "0" ),		//		PGB_INFINMUEBLE	C_USOINMUEBLE_SF
		@Mapping( target = "nombreConstructora", source = "informacionConstruccion.nombreConstructora", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	COD_NOMBRE_CONSTRUCTORA
		@Mapping( target = "codigoFinanciadoConstructor", source = "informacionConstruccion.codigoFinanciadoConstructor", defaultValue = "0" ),		//		PGB_INFCONSTRUCCION	COD_FINANCIADO_CONSTRUCTOR
	} )	
	ReporteProcedatosDTO entity2dto(Avaluo entity);
	
	List<ReporteProcedatosDTO> entity2dtoList(List<Avaluo> entities);
	
}
