package com.segurosbolivar.avaluos.motor.servicios;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.dto.ResponseUvrDto;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

@Path("/uvr")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UvrSvc {
	
	@EJB
	public transient IAvaluoFacade avaluoFacade;

	@GET
	@Path("/consultaUvr")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseUvrDto consultaAvaluo() {
		
		ResponseUvrDto responseUvrDto = new ResponseUvrDto();
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(5);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(false);

		try {
			responseUvrDto.setCodigoRespuesta("200");
			responseUvrDto.setValorUvrDia(df.format(avaluoFacade.consultaUvr()).toString());
		} catch (NegocioException e) {
			responseUvrDto.setCodigoRespuesta("204");	
			responseUvrDto.setMensajeRespuesta(e.getDetalle());
		}

		return responseUvrDto;
	
	}
}

