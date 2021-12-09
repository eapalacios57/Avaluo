package com.segurosbolivar.avaluos.planificador.servicios;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioConsultaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.MedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.BeneficiarioFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.IMedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudBeneficiario;

@Path("/Beneficiario")
@Stateless
public class BeneficiarioSvc {

	@EJB
	IBeneficiario beneficiario;	
	
	@EJB
	ISolicitudBeneficiario iSolicitudBeneficiario;
	
	@EJB
	IMedioComunicacion iMedioComunicacion;
	
	@GET
	@Path("/consultaTodos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<BeneficiarioDTO> consultaTodos() {

		BeneficiarioFullDTOMapper beneficiarioFullDTOMapper = Mappers
				.getMapper(BeneficiarioFullDTOMapper.class);

		List<BeneficiarioDTO> listado = new ArrayList<>();

		try {
			listado = beneficiarioFullDTOMapper.entity2dtoList(beneficiario.listaBeneficiario());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listado;
	}
	
	@GET
	@Path("/consultaPorSolicitud/{codigoSolicitud}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultaPorSolicitud(@PathParam("codigoSolicitud") String codigoSolicitud) {
		RespuestaRest respuesta;
		
		BeneficiarioFullDTOMapper beneficiarioFullDTOMapper = Mappers
				.getMapper(BeneficiarioFullDTOMapper.class);

		BeneficiarioConsultaDTO beneficiarioDto = new BeneficiarioConsultaDTO();
		BeneficiarioPK beneficiarioPK = new BeneficiarioPK();
		MedioComunicacionDTO medioComunicacionDto = new MedioComunicacionDTO();
		MedioComunicacion medioComunicacion = new MedioComunicacion();

		try {
			beneficiarioDto.setCodigoSolicitud(codigoSolicitud);
			SolicitudBeneficiario solicitudBeneficiario = iSolicitudBeneficiario.getSolicitudBeneficiario(codigoSolicitud);
			beneficiarioPK.setTipoDocumentoBeneficiario(solicitudBeneficiario.getId().getTipoDocumentoBeneficiario());
			beneficiarioPK.setNumeroDocumentoBeneficiario(solicitudBeneficiario.getId().getNumeroDocumentoBeneficiario());
			beneficiarioDto.setId(beneficiarioPK);
			//Beneficiario beneficiarioEnt = beneficiario.buscaBeneficiarioPorId(beneficiarioPK);
			Beneficiario beneficiarioEnt = solicitudBeneficiario.getBeneficiario();
			beneficiarioDto.setContacto(solicitudBeneficiario.getContacto());
			//medioComunicacionDto = iMedioComunicacion.consultaPorBeneficiario(beneficiarioPK);
			medioComunicacion = solicitudBeneficiario.getBeneficiario().getMedioComunicacions().get(0);
			beneficiarioDto.setPrimerNombre(beneficiarioEnt.getPrimerNombre());
			beneficiarioDto.setSegundoNombre(beneficiarioEnt.getSegundoNombre());
			beneficiarioDto.setPrimerApellido(beneficiarioEnt.getPrimerApellido());
			beneficiarioDto.setSegundoApellido(beneficiarioEnt.getSegundoApellido());
			beneficiarioDto.setMedioComunicacionValor(medioComunicacion.getValor());
			respuesta = new RespuestaRest("200","ok",beneficiarioDto);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500",e.getMessage(),null);
		}

		return respuesta;
	}
	
}