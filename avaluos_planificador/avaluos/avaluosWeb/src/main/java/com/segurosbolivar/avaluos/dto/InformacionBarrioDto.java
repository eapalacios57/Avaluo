package com.segurosbolivar.avaluos.dto;

import java.io.Serializable;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;

/**
 * 
 * @author stilaguy
 *
 */
public class InformacionBarrioDto implements Serializable {

	private static final long serialVersionUID = -6790344834794796065L;
	private InformacionBarrio barrio;

	/**
	 * Constructor para el DTo de informacion barrio no se puede crear un dto
	 * sin el uso de un objeto de la entidad informacionBarrio.
	 * 
	 * @param barrio
	 *            valor para inicializar el dto
	 * @throws NegocioException 
	 */
	public InformacionBarrioDto(InformacionBarrio barrio) throws NegocioException {
		if (barrio == null)
			throw new NegocioException(
					"No se puede construir un objeto de la case InformacionBarrioDto con un entity InformacionBarrio vacio");
		this.barrio = barrio;
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

	public boolean isAndenes() {
		return convertirABooleano(barrio.getAndenes());
	}

	public void setAndenes(boolean andenes) {
		barrio.setAndenes(convertirALong(andenes));
	}

	public boolean isPavimentada() {
		return convertirABooleano(barrio.getPavimentada());
	}

	public void setPavimentada(boolean pavimentada) {
		barrio.setPavimentada(convertirALong(pavimentada));
	}

	public boolean isSardeneles() {
		return convertirABooleano(barrio.getSardeneles());
	}

	public void setSardeneles(boolean sardeneles) {
		barrio.setSardeneles(convertirALong(sardeneles));
	}

	public boolean isAcueductoPredio() {
		return convertirABooleano(barrio.getAcueductoPredio());
	}

	public void setAcueductoPredio(boolean acueductoPredio) {
		barrio.setAcueductoPredio(convertirALong(acueductoPredio));
	}

	public boolean isAcueductoSector() {
		return convertirABooleano(barrio.getAcueductoSector());
	}

	public void setAcueductoSector(boolean acueductoSector) {
		barrio.setAcueductoSector(convertirALong(acueductoSector));
	}

	public boolean isAlamedas() {
		return convertirABooleano(barrio.getAlamedas());
	}

	public void setAlamedas(boolean alamedas) {
		barrio.setAlamedas(convertirALong(alamedas));
	}

	public boolean isAlcantarilladoPredio() {
		return convertirABooleano(barrio.getAlcantarilladoPredio());
	}

	public void setAlcantarilladoPredio(boolean alcantarilladoPredio) {
		barrio.setAlcantarilladoPredio(convertirALong(alcantarilladoPredio));
	}

	public boolean isAlcantarilladoSector() {
		return convertirABooleano(barrio.getAlcantarilladoSector());
	}

	public void setAlcantarilladoSector(boolean alcantarilladoSector) {
		barrio.setAlcantarilladoSector(convertirALong(alcantarilladoSector));
	}

	public boolean isAlumbrado() {
		return convertirABooleano(barrio.getAlumbrado());
	}

	public void setAlumbrado(boolean alumbrado) {
		barrio.setAlumbrado(convertirALong(alumbrado));
	}

	public boolean isArborizacion() {
		return convertirABooleano(barrio.getArborizacion());
	}

	public void setArborizacion(boolean arborizacion) {
		barrio.setArborizacion(convertirALong(arborizacion));
	}

	public boolean isCicloRutas() {
		return convertirABooleano(barrio.getCicloRutas());
	}

	public void setCicloRutas(boolean cicloRutas) {
		barrio.setCicloRutas(convertirALong(cicloRutas));
	}

	public boolean isComercio() {
		return convertirABooleano(barrio.getComercio());
	}

	public void setComercio(boolean comercio) {
		barrio.setComercio(convertirALong(comercio));
	}

	public boolean isElectricidadPredio() {
		return convertirABooleano(barrio.getElectricidadPredio());
	}

	public void setElectricidadPredio(boolean electricidadPredio) {
		barrio.setElectricidadPredio(convertirALong(electricidadPredio));
	}

	public boolean isElectricidadSector() {
		return convertirABooleano(barrio.getElectricidadSector());
	}

	public void setElectricidadSector(boolean electricidadSector) {
		barrio.setElectricidadSector(convertirALong(electricidadSector));
	}

	public boolean isGasPredio() {
		return convertirABooleano(barrio.getGasPredio());
	}

	public void setGasPredio(boolean gasPredio) {
		barrio.setGasPredio(convertirALong(gasPredio));
	}

	public boolean isGasSector() {
		return convertirABooleano(barrio.getGasSector());
	}

	public void setGasSector(boolean gasSector) {
		barrio.setGasSector(convertirALong(gasSector));
	}

	public boolean isIndustria() {
		return convertirABooleano(barrio.getIndustria());
	}

	public void setIndustria(boolean industria) {
		barrio.setIndustria(convertirALong(industria));
	}

	public boolean isOtrosUsos() {
		return convertirABooleano(barrio.getOtrosUsos());
	}

	public void setOtrosUsos(boolean otrosUsos) {
		barrio.setOtrosUsos(convertirALong(otrosUsos));
	}

	public boolean isParadero() {
		return convertirABooleano(barrio.getParadero());
	}

	public void setParadero(boolean paradero) {
		barrio.setParadero(convertirALong(paradero));
	}

	public boolean isParques() {
		return convertirABooleano(barrio.getParques());
	}

	public void setParques(boolean parques) {
		barrio.setParques(convertirALong(parques));
	}

	public boolean isTelefonoPredio() {
		return convertirABooleano(barrio.getTelefonoPredio());
	}

	public void setTelefonoPredio(boolean telefonoPredio) {
		barrio.setTelefonoPredio(convertirALong(telefonoPredio));
	}

	public boolean isTelefonoSector() {
		return convertirABooleano(barrio.getTelefonoSector());
	}

	public void setTelefonoSector(boolean telefonoSector) {
		barrio.setTelefonoSector(convertirALong(telefonoSector));
	}

	public boolean isVivienda() {
		return convertirABooleano(barrio.getVivienda());
	}

	public void setVivienda(boolean vivienda) {
		barrio.setVivienda(convertirALong(vivienda));
	}

	public boolean isZonasVerdes() {
		return convertirABooleano(barrio.getZonasVerdes());
	}

	public void setZonasVerdes(boolean zonasVerdes) {
		barrio.setZonasVerdes(convertirALong(zonasVerdes));
	}

	/*
	 * implementacion de los metodos to String, hascode y equals haciendo la
	 * invocacion del entity de informacion barrio como prioridad antes de la
	 * invocacion del objeto que lo enmascara.
	 */

	@Override
	public String toString() {
		if (barrio == null)
			return toString();
		return barrio.toString();
	}

	@Override
	public int hashCode() {
		if (barrio == null)
			return hashCode();
		return barrio.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof InformacionBarrio)
			return obj.equals(this.barrio);
		else if (obj instanceof InformacionBarrioDto)
			return ((InformacionBarrioDto) obj).getBarrio().equals(this.barrio);
		return false;
	}

	/*
	 * getters y setters
	 */

	public InformacionBarrio getBarrio() {
		return barrio;
	}

	public void setBarrio(InformacionBarrio barrio) {
		this.barrio = barrio;
	}

}