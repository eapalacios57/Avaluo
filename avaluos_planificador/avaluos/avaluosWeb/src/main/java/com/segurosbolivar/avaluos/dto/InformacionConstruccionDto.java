package com.segurosbolivar.avaluos.dto;

import java.io.Serializable;

import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;

/**
 * 
 * @author stilaguy
 *
 */
public class InformacionConstruccionDto implements Serializable {

	private static final long serialVersionUID = -6790344834794796065L;
	private InformacionConstruccion construccion;

	/**
	 * Constructor para el DTo de informacion barrio no se puede crear un dto
	 * sin el uso de un objeto de la entidad InformacionConstruccion.
	 * 
	 * @param construccion
	 *            valor para inicializar el dto
	 * @throws NegocioException
	 */
	public InformacionConstruccionDto(InformacionConstruccion construccion) throws NegocioException {
		if (construccion == null)
			throw new NegocioException(
					"No se puede construir un objeto de la case InformacionConstruccionDto con un entity InformacionConstruccion vacio");
		this.construccion = construccion;
	}

	public boolean isCodigoFinanciadoConstructor() {
		return convertirABooleano(construccion.getCodigoFinanciadoConstructor());
	}

	public void setCodigoFinanciadoConstructor(boolean codigoFinanciadoConstructor) {
		construccion.setCodigoFinanciadoConstructor(codigoFinanciadoConstructor ? 1L : 0L);
	}

	/**
	 * traduce un valor booleano a Long para simplicar el seteo de los valores
	 * en base de datos para los valores binarios del entity de informacion
	 * barrio.
	 * 
	 * @param valor
	 *            booleano a convertir.
	 * @return traducion del valor en long.
	 */
	private Long convertirALong(boolean valor) {
		return valor ? 1L : 0L;
	}

	/**
	 * traduce el valor de un long a booleano para simplicar el manejo de los
	 * select boolean checbox.
	 * 
	 * @param valor
	 * @return
	 */
	private boolean convertirABooleano(Long valor) {
		return valor != null && valor == 1L;
	}

	/*
	 * getters y setters para tranformar los valores booleanos a long.
	 */

	public InformacionConstruccion getConstruccion() {
		return construccion;
	}

	public void setConstruccion(InformacionConstruccion construccion) {
		this.construccion = construccion;
	}

	public boolean isPorteria() {
		return convertirABooleano(construccion.getPorteria());
	}

	public void setPorteria(boolean porteria) {
		construccion.setPorteria(convertirALong(porteria));
	}

	public boolean isPiscina() {
		return convertirABooleano(construccion.getPiscina());
	}

	public void setPiscina(boolean piscina) {
		construccion.setPiscina(convertirALong(piscina));
	}

	public boolean isGarajeVisitante() {
		return convertirABooleano(construccion.getGarajeVisitante());
	}

	public void setGarajeVisitante(boolean garajeVisitante) {
		construccion.setGarajeVisitante(convertirALong(garajeVisitante));
	}

	public boolean isBombaEyectora() {
		return convertirABooleano(construccion.getBombaEyectora());
	}

	public void setBombaEyectora(boolean bombaEyectora) {
		construccion.setBombaEyectora(convertirALong(bombaEyectora));
	}

	public boolean isZonasVerdes() {
		return convertirABooleano(construccion.getZonasVerdes());
	}

	public void setZonasVerdes(boolean zonasVerdes) {
		construccion.setZonasVerdes(convertirALong(zonasVerdes));
	}

	public boolean isCitofono() {
		return convertirABooleano(construccion.getCitofono());
	}

	public void setCitofono(boolean citofono) {
		construccion.setCitofono(convertirALong(citofono));
	}

	public boolean isTanqueAgua() {
		return convertirABooleano(construccion.getTanqueAgua());
	}

	public void setTanqueAgua(boolean tanqueAgua) {
		construccion.setTanqueAgua(convertirALong(tanqueAgua));
	}

	public boolean isJuegoNinos() {
		return convertirABooleano(construccion.getJuegoNinos());
	}

	public void setJuegoNinos(boolean juegoNinos) {
		construccion.setJuegoNinos(convertirALong(juegoNinos));
	}

	public boolean isAireAcondicionado() {
		return convertirABooleano(construccion.getAireAcondicionado());
	}

	public void setAireAcondicionado(boolean aireAcondicionado) {
		construccion.setAireAcondicionado(convertirALong(aireAcondicionado));
	}

	public boolean isGimnasio() {
		return convertirABooleano(construccion.getGimnasio());
	}

	public void setGimnasio(boolean gimnasio) {
		construccion.setGimnasio(convertirALong(gimnasio));
	}

	public boolean isBicicletero() {
		return convertirABooleano(construccion.getBicicletero());
	}

	public void setBicicletero(boolean bicicletero) {
		construccion.setBicicletero(convertirALong(bicicletero));
	}

	public boolean isClubHouse() {
		return convertirABooleano(construccion.getClubHouse());
	}

	public void setClubHouse(boolean clubHouse) {
		construccion.setClubHouse(convertirALong(clubHouse));
	}

	public boolean isCanchaMultiple() {
		return convertirABooleano(construccion.getCanchaMultiple());
	}

	public void setCanchaMultiple(boolean canchaMultiple) {
		construccion.setCanchaMultiple(convertirALong(canchaMultiple));
	}

	public boolean isSquash() {
		return convertirABooleano(construccion.getSquash());
	}

	public void setSquash(boolean squash) {
		construccion.setSquash(convertirALong(squash));
	}

	public boolean isGolfito() {
		return convertirABooleano(construccion.getGolfito());
	}

	public void setGolfito(boolean golfito) {
		construccion.setGolfito(convertirALong(golfito));
	}

	public boolean isSalonComunal() {
		return convertirABooleano(construccion.getSalonComunal());
	}

	public void setSalonComunal(boolean salonComunal) {
		construccion.setSalonComunal(convertirALong(salonComunal));
	}

	public boolean isPlanta() {
		return convertirABooleano(construccion.getPlanta());
	}

	public void setPlanta(boolean planta) {
		construccion.setPlanta(convertirALong(planta));
	}

	public boolean isShutBasuras() {
		return convertirABooleano(construccion.getShutBasuras());
	}

	public void setShutBasuras(boolean shutBasuras) {
		construccion.setShutBasuras(convertirALong(shutBasuras));
	}

	public boolean isPresion() {
		return convertirABooleano(construccion.getPresion());
	}

	public void setPresion(boolean presion) {
		construccion.setPresion(convertirALong(presion));
	}

	public boolean isAscensor() {
		return convertirABooleano(construccion.getAscensor());
	}

	public void setAscensor(boolean ascensor) {
		construccion.setAscensor(convertirALong(ascensor));
	}

	public boolean isEstadoRemodelacion() {
		return convertirABooleano(construccion.getEstadoRemodelacion());
	}

	public void setEstadoRemodelacion(boolean estadoRemodelacion) {
		construccion.setEstadoRemodelacion(convertirALong(estadoRemodelacion));
	}

	public boolean isEnObra() {
		return convertirABooleano(construccion.getEnObra());
	}

	public boolean isTerminadoUsado() {
		return convertirABooleano(construccion.getEstadoTerminadoUsado());
	}

	public String getEstadoConstruccionUsado() {
		if (convertirABooleano(construccion.getEstadoTerminadoUsado()))
			return "1";
		if (convertirABooleano(construccion.getSinTerminar()))
			return "2";
		return "";
	}

	public void setEstadoConstruccionUsado(String estadoConstruccionUsado) {
		if (UtilTexto.estaVacio(estadoConstruccionUsado))
			return;
		setEstadoRemodelacion(false);
		construccion.setEstadoTerminadoUsado(convertirALong("1".equals(estadoConstruccionUsado)));
		construccion.setSinTerminar(convertirALong("2".equals(estadoConstruccionUsado)));
	}

	public String getEstadoConstruccionNuevo() {
		if (convertirABooleano(construccion.getEstadoTerminadaNueva()))
			return "1";
		if (convertirABooleano(construccion.getEnObra()))
			return "2";
		return "";
	}

	public void setEstadoConstruccionNuevo(String estadoConstruccionNuevo) {
		if (UtilTexto.estaVacio(estadoConstruccionNuevo))
			return;
		construccion.setAvanceObra(null);
		construccion.setEstadoTerminadaNueva(convertirALong("1".equals(estadoConstruccionNuevo)));
		construccion.setEnObra(convertirALong("2".equals(estadoConstruccionNuevo)));
	}

}