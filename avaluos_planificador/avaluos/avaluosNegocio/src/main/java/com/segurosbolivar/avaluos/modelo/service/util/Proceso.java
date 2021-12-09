package com.segurosbolivar.avaluos.modelo.service.util;

import java.util.List;

public class Proceso implements Runnable {
	
/**
 * bbeltran@asesoftware.com
 * 01/06/2020
 * se realiza creacion de clase proceso para menejar en un hilo el proceso de remplazaAvenidas
 * se corre proceso sobre un hilo para evitar timeout
 */
private boolean exit; 
  
    private List<String> tokens; 
    private List<String> tokens2; 

    Thread t; 
    BUKEstandarizacionDirsX buke = new BUKEstandarizacionDirsX();

  
    Proceso(List<String> tokens) 
    { 
        this.tokens= tokens;
        exit = false; 
    } 
  
    // execution of thread starts from run() method 
    public void run() 
    { 
           this.tokens2 = buke.remplazaAvenidas(tokens); 
         
        System.out.println( " finaliza remplazaAvenidas"); 
    } 
  
 

	public List<String> getTokens2() {
		return tokens2;
	}

	public void setTokens2(List<String> tokens2) {
		this.tokens2 = tokens2;
	}

	
	
}



