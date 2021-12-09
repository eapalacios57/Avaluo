package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.math.BigDecimal;

public class ReferenciaEspacialDto {

	private BigDecimal wkid;
	private BigDecimal latestWkid;

	public BigDecimal getWkid() {
		return wkid;
	}

	public void setWkid(BigDecimal wkid) {
		this.wkid = wkid;
	}

	public BigDecimal getLatestWkid() {
		return latestWkid;
	}

	public void setLatestWkid(BigDecimal latestWkid) {
		this.latestWkid = latestWkid;
	}

}
