package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud;

public class PlanificadorModel {

    private String tipoDocIdentificacion;
    
    private String numDocIdentificacion;
   
	private String dispositivo;
	
	private String tokenDispositivo;

    public PlanificadorModel() {
    }

    public String getTipoDocIdentificacion() {
        return tipoDocIdentificacion;
    }

    public void setTipoDocIdentificacion(String tipoDocIdentificacion) {
        this.tipoDocIdentificacion = tipoDocIdentificacion;
    }

    public String getNumDocIdentificacion() {
        return numDocIdentificacion;
    }

    public void setNumDocIdentificacion(String numDocIdentificacion) {
        this.numDocIdentificacion = numDocIdentificacion;
    }

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getTokenDispositivo() {
		return tokenDispositivo;
	}

	public void setTokenDispositivo(String tokenDispositivo) {
		this.tokenDispositivo = tokenDispositivo;
	}
    
    
    
}
