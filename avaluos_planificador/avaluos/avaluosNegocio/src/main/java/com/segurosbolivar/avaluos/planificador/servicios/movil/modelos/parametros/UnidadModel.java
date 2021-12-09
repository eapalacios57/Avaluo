package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros;



public class UnidadModel {

   
    private Integer id;

    
    private String nombre;

    private double factorHa;


    public UnidadModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFactorHa() {
        return factorHa;
    }

    public void setFactorHa(double factorHa) {
        this.factorHa = factorHa;
    }
}
