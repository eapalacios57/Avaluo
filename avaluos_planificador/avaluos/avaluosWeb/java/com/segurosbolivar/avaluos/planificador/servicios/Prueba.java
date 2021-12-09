 
package com.segurosbolivar.avaluos.planificador.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("Prueba")
@Consumes("application/json")
@Produces("application/json")
public class Prueba {
	/**
     * Default constructor. 
     */
    public Prueba() {
        // TODO Auto-generated constructor stub
    }


    /**
     * Retrieves representation of an instance of Prueba
     * @return an instance of String
     */
	@GET
	@Produces("text/plain")
	public RespuestaRest sourceMethodGET() { 
		// TODO Auto-generated method stub
		
		RespuestaRest sasa = new RespuestaRest("200","aaaa","");
		
		return sasa;
		
	}

	/**
     * PUT method for updating or creating an instance of Prueba
     * @content content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
	@PUT
	@Consumes("text/plain")
	public void resourceMethodPUT(String content) { 
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}