package com.segurosbolivar.avaluos.modelo.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;

import com.segurosbolivar.avaluos.modelo.data.DaReglasEstDirDao;
import com.segurosbolivar.avaluos.modelo.dto.NodoConversion;
import com.segurosbolivar.avaluos.modelo.entity.DaReglaEstDir;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;


import java.io.Serializable;

@Stateless
public class BUKEstandarizacionDirsX implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -855037095671926096L;
	private HashMap<String, NodoConversion> arbolConversion;
    private List<String> llavesArbolConversion;
    boolean time_out=false;
    private static String[] puntosCardinales = { "NORTE", "SUR", "BIS", "ESTE", "OESTE", "NORESTE", "SURESTE",
	    "SUROESTE", "NOROESTE", "ORIENTAL" };

    @EJB
    private DaReglasEstDirDao daReglasEstDirDao;

    /**
     *
     */
    @PostConstruct
    public void init() {
	cargarInfoConvDirX();
    }

    /**
     *
     * @param dirOriginal
     * @return
     */
    public String estandarizarDireccion(String dirOriginal, boolean validateComplemento) {
	String direccion = "";

	int ind = 0;
	String regExpEspacios = UtilConstantes.EXP_ESPACIOS;

	direccion = suprimirElementosEliminables(dirOriginal);

	List<String> tokens = fragmentarDireccion(direccion);

	tokens = remplazarExpresionPrincipal(tokens);

	tokens = remplazarExpresionSecundaria(tokens, validateComplemento);

	tokens = remplazarExpresionNoEncontrada(tokens, UtilConstantes.LETRA_I);

	tokens = remplazaViaPrincipal(tokens);
	
	/**
	 * bbeltran@asesoftware.com
	 * 01/06/2020
	 * se añade creacion de hilo para correr proceso remplazaAvenidas y evitar timeout 
	 * el flag itme_out indica si el proceso termino o murio por el tiempo de espera
	 */
	  
	  try { 
			Logger.getLogger(BUKEstandarizacionDirsX.class.getName()).info(" Inicio estandarizarDireccion");

		  time_out=true;
		  Proceso t1 = new Proceso(tokens); 
    	  Thread t = new Thread(t1); 
    	  t.start();
    	  for(int i=0;t.isAlive();i=i+1000) {
    		  Thread.sleep(1000);
    		  
    		  System.out.println(t.isAlive());
    		  if(i>=60000) {
    			  time_out=false;
    				Logger.getLogger(BUKEstandarizacionDirsX.class.getName()).info("fin  estandarizarDireccion por timeOut");

    			  break;
    		  }
    	  }
    	  
    	  t.stop();
    	  if(time_out) {
				Logger.getLogger(BUKEstandarizacionDirsX.class.getName()).info("fin estandarizarDireccion");

        	  tokens= t1.getTokens2();
    	  }
      } 
      catch (InterruptedException e) { 
          System.out.println("Caught:" + e); 
      } 
	 

	for (ind = 0; ind < tokens.size(); ind++) {
	    if ((tokens.get(ind)).trim().equals("")) {
		tokens.remove(ind);
	    } else {
		ind++;
	    }
	}

	if (tokens.size() == 1) {
	    tokens = null;
	}

	direccion = "";
	ind = 0;
	if (tokens != null) {
	    while (ind < tokens.size()) {
		direccion = direccion + tokens.get(ind);
		ind++;

		if (ind < tokens.size()) {
		    direccion = direccion + " ";
		}
	    }

	}

	direccion = direccion.replaceAll(regExpEspacios, " ");
	/** bbeltran@asesoftware.com
	 * 29/05/2020
	 * Se crea condicion para enviar mensaje de error si el flag tiem_out fue levantada en metodo "remplazaAvenidas" 
	 */
    if(!time_out) {
    	System.out.println("error por timeout remplazaAvenidas");
    	return "ERROR";
    }else {
    	return direccion != null ? direccion.trim() : "";

    }
    }
    
    public String estandarizarDireccionComplementaria(String dirOriginal) {
	String direccion = "";

	int ind = 0;
	String regExpEspacios = UtilConstantes.EXP_ESPACIOS;
	direccion = suprimirElementosEliminables(dirOriginal);
	
	List<String> tokens = fragmentarDireccion(direccion);
	
	remplazarExpresionNoEncontrada(tokens, UtilConstantes.LETRA_I);
	
	remplazarExpresionNoEncontrada(tokens, UtilConstantes.LETRA_N);
	
	for (ind = 0; ind < tokens.size(); ind++) {
	    if ((tokens.get(ind)).trim().equals("")) {
		tokens.remove(ind);
	    } else {
		ind++;
	    }
	}

	if (tokens.size() == 1) {
	    tokens = null;
	}

	direccion = "";
	ind = 0;
	if (tokens != null) {
	    while (ind < tokens.size()) {
		direccion = direccion + tokens.get(ind);
		ind++;

		if (ind < tokens.size()) {
		    direccion = direccion + " ";
		}
	    }

	}

	direccion = direccion.replaceAll(regExpEspacios, " ");

	return direccion != null ? direccion.trim() : "";
	
    }

    /**
     *
     * @param listTokens
     * @return
     */
    private List<String> remplazarExpresionPrincipal(List<String> listTokens) {
	NodoConversion nodoConversion = null;
	int i = 0;
	String primerToken = null;
	String segundoToken = null;
	String tercerToken = null;

	String tokenComp = null;
	boolean encontroDescrPrincipal = false;

	if (listTokens.size() == 1) {
	    listTokens = new ArrayList<>();
	    return listTokens;
	}

	while ((i < listTokens.size()) && (!encontroDescrPrincipal)) {
	    primerToken = null;
	    segundoToken = null;
	    tercerToken = null;

	    if (i + 2 < listTokens.size()) {
		primerToken = listTokens.get(i);
		segundoToken = listTokens.get(i + 1);
		tercerToken = listTokens.get(i + 2);
		tokenComp = primerToken + ' ' + segundoToken + ' ' + tercerToken;

		nodoConversion = arbolConversion.get(tokenComp);
		if ((nodoConversion != null)
			&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_I))) {
		    listTokens.set(i, nodoConversion.getConversion());
		    listTokens.remove(i + 2);
		    listTokens.remove(i + 1);

		    listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
		    encontroDescrPrincipal = true;
		} else {
		    tokenComp = primerToken + ' ' + segundoToken;

		    nodoConversion = arbolConversion.get(tokenComp);
		    if ((nodoConversion != null)
			    && (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_I))) {
			listTokens.set(i, nodoConversion.getConversion());
			listTokens.remove(i + 1);

			listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
			encontroDescrPrincipal = true;
		    } else {

			nodoConversion = arbolConversion.get(primerToken);

			if ((nodoConversion != null)
				&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_I))) {
			    if (!nodoConversion.getConversion().equalsIgnoreCase(primerToken)) {
				listTokens.set(i, nodoConversion.getConversion());
				listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
				encontroDescrPrincipal = true;
			    } else if (listTokens.size() == 2) {
				if (i == 0) {
				    if (!esNumerico((String) listTokens.get(i + 1))) {
					listTokens = new ArrayList<>();
				    } else {
					listTokens.set(i, nodoConversion.getConversion());
					listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
					encontroDescrPrincipal = true;
				    }

				} else if (esNumerico((String) listTokens.get(i - 1))) {
				    listTokens = new ArrayList<>();
				} else {
				    listTokens.set(i, nodoConversion.getConversion());
				    listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
				    encontroDescrPrincipal = true;
				}

			    } else if (listTokens.size() == 1) {
				listTokens = new ArrayList<>();
			    } else if (listTokens.size() >= 3) {
				listTokens.set(i, nodoConversion.getConversion());
				listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
				encontroDescrPrincipal = true;
			    }

			}

		    }

		}

	    } else if (i + 1 < listTokens.size()) {
		primerToken = listTokens.get(i);
		segundoToken = listTokens.get(i + 1);

		tokenComp = primerToken + ' ' + segundoToken;

		nodoConversion = arbolConversion.get(tokenComp);
		if ((nodoConversion != null)
			&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_I))) {
		    listTokens.set(i, nodoConversion.getConversion());
		    listTokens.remove(i + 1);

		    listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
		    encontroDescrPrincipal = true;
		} else {

		    nodoConversion = arbolConversion.get(primerToken);

		    if ((nodoConversion != null)
			    && (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_I))) {
			if (!nodoConversion.getConversion().equalsIgnoreCase(primerToken)) {
			    listTokens.set(i, nodoConversion.getConversion());
			    listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
			    encontroDescrPrincipal = true;
			} else if (listTokens.size() == 1) {
			    listTokens = new ArrayList<>();
			} else if (listTokens.size() == 2) {
			    listTokens.set(i, nodoConversion.getConversion());
			    listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
			    encontroDescrPrincipal = true;
			} else if (listTokens.size() >= 3) {
			    listTokens.set(i, nodoConversion.getConversion());
			    listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
			    encontroDescrPrincipal = true;
			}

		    }

		}

	    } else {
		primerToken = listTokens.get(i);

		String[] dosTokens = null;
		if (Pattern.matches(UtilConstantes.EXP_PATRON, primerToken)) {
		    dosTokens = obtenerParteAlfabeticaInicial(primerToken);
		    primerToken = dosTokens[0];
		    listTokens.set(i, primerToken);
		    listTokens.add(i + 1, dosTokens[1]);
		}

		nodoConversion = arbolConversion.get(primerToken);
		if ((nodoConversion != null)
			&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_I))) {
		    if (!nodoConversion.getConversion().equalsIgnoreCase(primerToken)) {
			listTokens.set(i, nodoConversion.getConversion());
			listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
			encontroDescrPrincipal = true;
		    } else if (listTokens.size() == 2) {
			if (i == 0) {
			    if (esNumerico((String) listTokens.get(i + 1))) {
				listTokens = new ArrayList<>();
			    } else {
				listTokens.set(i, nodoConversion.getConversion());
				listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
				encontroDescrPrincipal = true;
			    }

			} else if (esNumerico((String) listTokens.get(i - 1))) {
			    listTokens = new ArrayList<>();
			} else {
			    listTokens.set(i, nodoConversion.getConversion());
			    listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
			    encontroDescrPrincipal = true;
			}

		    } else if (listTokens.size() == 1) {
			listTokens = new ArrayList<>();
		    } else if (listTokens.size() >= 3) {
			listTokens.set(i, nodoConversion.getConversion());
			listTokens = pasarPrimerosElemsAlFinal(listTokens, i);
			encontroDescrPrincipal = true;
		    }

		}

	    }

	    i++;
	}

	if ((!encontroDescrPrincipal) && (listTokens != null)) {
	    listTokens = new ArrayList<>();
	}
	return listTokens;
    }

    public static boolean esNumerico(String unToken) {
	try {
	    Long.parseLong(unToken);
	} catch (NumberFormatException exc) {
	    return false;
	}
	return true;
    }

    private static String[] obtenerParteAlfabeticaInicial(String unToken) {
	char[] candidataPalPpal = new char[256];
	int indicador = 0;
	@SuppressWarnings("unused")
	boolean encontro = false;
	String[] arrRetorno;
	int indTokenNum = 0;
	String primerToken = null;
	String segundToken = null;

	while (indicador < unToken.length()) {
	    if (((unToken.charAt(indicador) < UtilConstantes.LETRA_MAYUS_A)
		    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MAYUS_Z))
		    && ((unToken.charAt(indicador) < UtilConstantes.LETRA_MINUS_A)
			    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MINUS_Z))
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MAYUS_Ñ)
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MINUS_Ñ)) {
		break;
	    }
	    candidataPalPpal[indicador] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	}

	primerToken = new String(candidataPalPpal, 0, indicador);

	indTokenNum = 0;
	while (indicador < unToken.length()) {
	    if ((unToken.charAt(indicador) < UtilConstantes.DIGIT_NUEVE)
		    || (unToken.charAt(indicador) > UtilConstantes.DIGIT_NUEVE))
		break;
	    candidataPalPpal[indTokenNum] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	    indTokenNum++;
	}
	segundToken = new String(candidataPalPpal, 0, indTokenNum);

	if (indicador == unToken.length()) {
	    arrRetorno = new String[2];
	    arrRetorno[0] = primerToken;
	    arrRetorno[1] = segundToken;
	    return arrRetorno;
	}

	indTokenNum = 0;
	while (indicador < unToken.length()) {
	    if (((unToken.charAt(indicador) < UtilConstantes.LETRA_MAYUS_A)
		    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MAYUS_Z))
		    && ((unToken.charAt(indicador) < UtilConstantes.LETRA_MINUS_A)
			    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MINUS_Z))
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MAYUS_Ñ)
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MINUS_Ñ)) {
		break;
	    }

	    candidataPalPpal[indTokenNum] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	    indTokenNum++;
	}
	String tercerToken = new String(candidataPalPpal, 0, indTokenNum);

	if (indicador == unToken.length()) {
	    arrRetorno = new String[3];
	    arrRetorno[0] = primerToken;
	    arrRetorno[1] = segundToken;
	    arrRetorno[2] = tercerToken;

	    return arrRetorno;
	}

	indTokenNum = 0;
	while (indicador < unToken.length()) {
	    if ((unToken.charAt(indicador) < UtilConstantes.DIGIT_CERO)
		    || (unToken.charAt(indicador) > UtilConstantes.DIGIT_NUEVE))
		break;
	    candidataPalPpal[indTokenNum] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	    indTokenNum++;
	}
	String cuartoToken = new String(candidataPalPpal, 0, indTokenNum);

	if (indicador == unToken.length()) {
	    arrRetorno = new String[4];
	    arrRetorno[0] = primerToken;
	    arrRetorno[1] = segundToken;
	    arrRetorno[2] = tercerToken;
	    arrRetorno[3] = cuartoToken;

	    return arrRetorno;
	}

	indTokenNum = 0;
	while (indicador < unToken.length()) {
	    if (((unToken.charAt(indicador) < UtilConstantes.LETRA_MAYUS_A)
		    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MAYUS_Z))
		    && ((unToken.charAt(indicador) < UtilConstantes.LETRA_MINUS_A)
			    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MINUS_Z))
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MAYUS_Ñ)
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MINUS_Ñ)) {
		break;
	    }

	    candidataPalPpal[indTokenNum] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	    indTokenNum++;
	}
	String quintoToken = new String(candidataPalPpal, 0, indTokenNum);

	if (indicador == unToken.length()) {
	    arrRetorno = new String[5];
	    arrRetorno[0] = primerToken;
	    arrRetorno[1] = segundToken;
	    arrRetorno[2] = tercerToken;
	    arrRetorno[3] = cuartoToken;
	    arrRetorno[4] = quintoToken;

	    return arrRetorno;
	}

	arrRetorno = new String[6];
	arrRetorno[0] = primerToken;
	arrRetorno[1] = segundToken;
	arrRetorno[2] = tercerToken;
	arrRetorno[3] = cuartoToken;
	arrRetorno[4] = quintoToken;
	arrRetorno[5] = unToken.substring(indicador);

	return arrRetorno;
    }

    private List<String> remplazaViaPrincipal(List<String> tokens) {
	boolean viaPrinEncontrada = false;
	boolean viaSegundaria = false;
	int posTokenOtraVia = 0;
	for (int i = 0; i < tokens.size() && !viaSegundaria; i++) {
	    String tokenActual = tokens.get(i);
	    NodoConversion nodoConversion = arbolConversion.get(tokenActual);
	    boolean esDobleLiteral = true;
	    if(i != 0) {
	    	esDobleLiteral = tokenActual.length() == 2;
		    esDobleLiteral &= tokens.get(i - 1).matches("[-+]?\\d*\\.?\\d+");
	    }
	    if ((nodoConversion != null)
		    && !esModificadorLetraAlfabeto(tokenActual) && (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_I)) && !esDobleLiteral) {
		if (viaPrinEncontrada) {
		    posTokenOtraVia = i;
		    viaSegundaria = true;
		}
		viaPrinEncontrada = true;
	    }
	}
	if (viaSegundaria) {
	    if (tokens.get(posTokenOtraVia - 1).matches("[-+]?\\d*\\.?\\d+")) {
		int i = posTokenOtraVia;
		while (i < tokens.size() && tokens.get(i) != "|") {
		    tokens.set(i, "");
		    i++;
		}
	    } else {
		tokens.set(posTokenOtraVia, "");
	    }
	}

	return tokens;
    }

    @SuppressWarnings("unused")
    private static String[] obtenerParteNumericaInicial(String unToken) {
	char[] candidataPalPpal = new char[256];
	int indicador = 0;
	boolean encontro = false;
	String[] arrRetorno = new String[2];
	String primerToken = null;
	String segundoToken = null;

	while (indicador < unToken.length()) {
	    if ((unToken.charAt(indicador) < UtilConstantes.DIGIT_CERO)
		    || (unToken.charAt(indicador) > UtilConstantes.DIGIT_NUEVE))
		break;
	    candidataPalPpal[indicador] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	}

	primerToken = new String(candidataPalPpal, 0, indicador);

	int indTokenNum = 0;
	while (indicador < unToken.length()) {
	    if (((unToken.charAt(indicador) < UtilConstantes.LETRA_MAYUS_A)
		    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MAYUS_Z))
		    && ((unToken.charAt(indicador) < UtilConstantes.LETRA_MINUS_A)
			    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MINUS_Z))
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MAYUS_Ñ)
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MINUS_Ñ)) {
		break;
	    }

	    candidataPalPpal[indTokenNum] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	    indTokenNum++;
	}

	candidataPalPpal[indTokenNum] = UtilConstantes.TRES_CEROS;
	segundoToken = new String(candidataPalPpal, 0, indTokenNum);

	if (indicador == unToken.length()) {
	    arrRetorno = new String[2];
	    arrRetorno[0] = primerToken;
	    arrRetorno[1] = segundoToken;

	    return arrRetorno;
	}

	int indx = 0;
	while (indicador < unToken.length()) {
	    if ((unToken.charAt(indicador) < UtilConstantes.DIGIT_CERO)
		    || (unToken.charAt(indicador) > UtilConstantes.DIGIT_NUEVE))
		break;
	    candidataPalPpal[indx] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	    indx++;
	}
	candidataPalPpal[indx] = UtilConstantes.TRES_CEROS;

	String tercerToken = new String(candidataPalPpal, 0, indx);

	if (indicador == unToken.length()) {
	    arrRetorno = new String[3];
	    arrRetorno[0] = primerToken;
	    arrRetorno[1] = segundoToken;
	    arrRetorno[2] = tercerToken;
	    return arrRetorno;
	}

	indTokenNum = 0;
	while (indicador < unToken.length()) {
	    if (((unToken.charAt(indicador) < UtilConstantes.LETRA_MAYUS_A)
		    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MAYUS_Z))
		    && ((unToken.charAt(indicador) < UtilConstantes.LETRA_MINUS_A)
			    || (unToken.charAt(indicador) > UtilConstantes.LETRA_MINUS_Z))
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MAYUS_Ñ)
		    && (unToken.charAt(indicador) != UtilConstantes.LETRA_MINUS_Ñ)) {
		break;
	    }

	    candidataPalPpal[indTokenNum] = unToken.charAt(indicador);
	    encontro = true;

	    indicador++;
	    indTokenNum++;
	}

	candidataPalPpal[indTokenNum] = UtilConstantes.TRES_CEROS;
	String cuartoToken = new String(candidataPalPpal, 0, indTokenNum);

	if (indicador == unToken.length()) {
	    arrRetorno = new String[4];
	    arrRetorno[0] = primerToken;
	    arrRetorno[1] = segundoToken;
	    arrRetorno[2] = tercerToken;
	    arrRetorno[3] = cuartoToken;
	    return arrRetorno;
	}

	arrRetorno = new String[5];
	arrRetorno[0] = primerToken;
	arrRetorno[1] = segundoToken;
	arrRetorno[2] = tercerToken;
	arrRetorno[3] = cuartoToken;
	arrRetorno[4] = unToken.substring(indicador);

	return arrRetorno;
    }

    public static String[] obtenerTokenSimbolosInicial(String unToken) {
	int indicador = 0;
	char[] candidataPalPpal = new char[100];
	String[] arrRetorno = null;

	while (indicador < unToken.length()) {
	    if (unToken.charAt(indicador) != UtilConstantes.DIGIT_NUMERAL)
		break;
	    candidataPalPpal[indicador] = unToken.charAt(indicador);

	    indicador++;
	}
	String[] arrTokens = null;

	String primerToken = new String(candidataPalPpal, 0, indicador);

	String faltante = unToken.substring(indicador);

	if ((faltante.charAt(0) >= UtilConstantes.DIGIT_CERO) && (faltante.charAt(0) <= UtilConstantes.DIGIT_NUEVE)) {
	    arrTokens = obtenerParteNumericaInicial(faltante);
	} else if (((faltante.charAt(0) >= UtilConstantes.LETRA_MINUS_A)
		&& (faltante.charAt(0) <= UtilConstantes.LETRA_MINUS_Z))
		|| ((faltante.charAt(0) >= UtilConstantes.LETRA_MAYUS_A)
			&& (faltante.charAt(0) <= UtilConstantes.LETRA_MAYUS_Z))
		|| (faltante.charAt(0) == UtilConstantes.LETRA_MAYUS_Ñ)
		|| (faltante.charAt(0) == UtilConstantes.LETRA_MINUS_Ñ)) {
	    arrTokens = obtenerParteAlfabeticaInicial(faltante);
	}
	arrRetorno = new String[1 + arrTokens.length];
	arrRetorno[0] = primerToken;

	for (int ind = 0; ind < arrTokens.length; ind++) {
	    arrRetorno[(ind + 1)] = arrTokens[ind];
	}

	return arrRetorno;
    }

    private static List<String> pasarPrimerosElemsAlFinal(List<String> lstTokens, int ind) {
	int k = 0;
	String token = null;

	while ((k < ind) && (k < lstTokens.size())) {
	    token = lstTokens.get(k);
	    lstTokens.add(token);
	    k++;
	}
	k = ind - 1;

	while (k >= 0) {
	    lstTokens.remove(k);
	    k--;
	}

	return lstTokens;
    }

    @SuppressWarnings("unused")
    private List<String> remplazarExpresionSecundaria(List<String> lstTokens, boolean validateComplemento) {
	NodoConversion nodoConversion = null;
	int i = 0;
	String primerToken = null;
	String segundoToken = null;
	String tercerToken = null;
	String cuartoToken = null;

	String tokenComp = null;
	boolean encontroDescrPrincipal = false;
	boolean esSegundaria = false;
	try {
	    try {
		while (i < lstTokens.size()) {
		    encontroDescrPrincipal = false;
		    primerToken = null;
		    segundoToken = null;
		    tercerToken = null;
		    cuartoToken = null;

		    if (i + 3 < lstTokens.size()) {
			primerToken = lstTokens.get(i);
			segundoToken = lstTokens.get(i + 1);
			tercerToken = lstTokens.get(i + 2);
			cuartoToken = lstTokens.get(i + 3);

			tokenComp = primerToken + ' ' + segundoToken + ' ' + tercerToken + ' ' + cuartoToken;

			nodoConversion = arbolConversion.get(tokenComp);

			if ((nodoConversion != null)
				&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_N))) {
			    lstTokens.set(i, nodoConversion.getConversion());
			    encontroDescrPrincipal = true;
			    lstTokens.remove(i + 3);
			    lstTokens.remove(i + 2);
			    lstTokens.remove(i + 1);
			} else {
			    tokenComp = primerToken + ' ' + segundoToken + ' ' + tercerToken;

			    nodoConversion = arbolConversion.get(tokenComp);
			    if ((nodoConversion != null)
				    && (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_N))) {
				lstTokens.set(i, nodoConversion.getConversion());
				lstTokens.remove(i + 2);
				lstTokens.remove(i + 1);

				encontroDescrPrincipal = true;
			    } else {
				tokenComp = primerToken + ' ' + segundoToken;

				nodoConversion = arbolConversion.get(tokenComp);
				if ((nodoConversion != null) && (nodoConversion.getModoSuprimir()
					.equalsIgnoreCase(UtilConstantes.LETRA_N))) {
				    lstTokens.set(i, nodoConversion.getConversion());
				    lstTokens.remove(i + 1);

				    encontroDescrPrincipal = true;
				} else {
				    tokenComp = primerToken + segundoToken;

				    nodoConversion = arbolConversion.get(tokenComp);
				    if ((nodoConversion != null) && (nodoConversion.getModoSuprimir()
					    .equalsIgnoreCase(UtilConstantes.LETRA_N))) {
					lstTokens.set(i, nodoConversion.getConversion());
					lstTokens.remove(i + 1);
					encontroDescrPrincipal = true;
				    } else {
					nodoConversion = arbolConversion.get(primerToken);

					if ((nodoConversion != null) && (nodoConversion.getModoSuprimir()
						.equalsIgnoreCase(UtilConstantes.LETRA_N))) {
					    encontroDescrPrincipal = true;

					    if ((nodoConversion.getConversion() != null)
						    && (nodoConversion.getConversion().trim().length() != 0)) {
						lstTokens.set(i, nodoConversion.getConversion());
						if (!esPuntoCardinal(nodoConversion.getConversion()) && !esSegundaria
							&& validateComplemento) {
						    lstTokens.add(i, "|");
						    esSegundaria = true;
						}
					    } else {
						lstTokens.remove(i);
					    }
					}
				    }

				}

			    }

			}

		    } else if (i + 2 < lstTokens.size()) {
			primerToken = lstTokens.get(i);
			segundoToken = lstTokens.get(i + 1);
			tercerToken = lstTokens.get(i + 2);
			tokenComp = primerToken + ' ' + segundoToken + ' ' + tercerToken;

			nodoConversion = arbolConversion.get(tokenComp);
			if ((nodoConversion != null)
				&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_N))) {
			    lstTokens.set(i, nodoConversion.getConversion());
			    lstTokens.remove(i + 2);
			    lstTokens.remove(i + 1);

			    encontroDescrPrincipal = true;
			} else {
			    tokenComp = primerToken + ' ' + segundoToken;

			    nodoConversion = arbolConversion.get(tokenComp);
			    if ((nodoConversion != null)
				    && (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_N))) {
				lstTokens.set(i, nodoConversion.getConversion());
				lstTokens.remove(i + 1);

				encontroDescrPrincipal = true;
			    } else {
				tokenComp = primerToken + segundoToken;

				nodoConversion = arbolConversion.get(tokenComp);
				if ((nodoConversion != null) && (nodoConversion.getModoSuprimir()
					.equalsIgnoreCase(UtilConstantes.LETRA_N))) {
				    lstTokens.set(i, nodoConversion.getConversion());
				    lstTokens.remove(i + 1);
				    encontroDescrPrincipal = true;
				} else {
				    nodoConversion = arbolConversion.get(primerToken);

				    if ((nodoConversion != null) && (nodoConversion.getModoSuprimir()
					    .equalsIgnoreCase(UtilConstantes.LETRA_N))) {
					if ((nodoConversion.getConversion() != null)
						&& (nodoConversion.getConversion().trim().length() != 0)) {
					    lstTokens.set(i, nodoConversion.getConversion());
					    if (!esPuntoCardinal(nodoConversion.getConversion()) && !esSegundaria
						    && validateComplemento) {
						lstTokens.add(i, "|");
						esSegundaria = true;
					    }
					} else {
					    lstTokens.remove(i);
					    continue;
					}

					encontroDescrPrincipal = true;
				    }
				}
			    }

			}

		    } else if (i + 1 < lstTokens.size()) {
			primerToken = lstTokens.get(i);
			segundoToken = lstTokens.get(i + 1);

			tokenComp = primerToken + ' ' + segundoToken;

			nodoConversion = arbolConversion.get(tokenComp);
			if ((nodoConversion != null)
				&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_N))) {
			    lstTokens.set(i, nodoConversion.getConversion());
			    lstTokens.remove(i + 1);

			    encontroDescrPrincipal = true;
			} else {
			    tokenComp = primerToken + segundoToken;

			    nodoConversion = arbolConversion.get(tokenComp);
			    if ((nodoConversion != null)
				    && (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_N))) {
				lstTokens.set(i, nodoConversion.getConversion());
				lstTokens.remove(i + 1);
				encontroDescrPrincipal = true;
			    } else {
				nodoConversion = arbolConversion.get(primerToken);

				if ((nodoConversion != null) && (nodoConversion.getModoSuprimir()
					.equalsIgnoreCase(UtilConstantes.LETRA_N))) {
				    if ((nodoConversion.getConversion() != null)
					    && (nodoConversion.getConversion().trim().length() != 0)) {
					lstTokens.set(i, nodoConversion.getConversion());
					if (!esPuntoCardinal(nodoConversion.getConversion()) && !esSegundaria
						&& validateComplemento) {
					    lstTokens.add(i, "|");
					    esSegundaria = true;
					}
				    } else {
					lstTokens.remove(i);
					continue;
				    }

				    encontroDescrPrincipal = true;
				}
			    }
			}

		    } else {
			primerToken = lstTokens.get(i);
			nodoConversion = arbolConversion.get(primerToken);
			if ((nodoConversion != null)
				&& (nodoConversion.getModoSuprimir().equalsIgnoreCase(UtilConstantes.LETRA_N))) {
			    if ((nodoConversion.getConversion() != null)
				    && (nodoConversion.getConversion().trim().length() != 0)) {
				lstTokens.set(i, nodoConversion.getConversion());
				if (!esPuntoCardinal(nodoConversion.getConversion()) && !esSegundaria
					&& validateComplemento) {
				    lstTokens.add(i, "|");
				    esSegundaria = true;
				}
			    } else {
				lstTokens.remove(i);
				continue;
			    }

			    encontroDescrPrincipal = true;
			} else {
			    i++;
			}
		    }
		    // OJOOOOOOOO BORRAR
		    i++;
		}
	    } catch (NullPointerException npe) {
		Logger.getLogger(BUKEstandarizacionDirsX.class.getName()).log(Level.SEVERE, "Existe informacion nula",
			npe);
	    }
	} catch (Exception exc) {
	    Logger.getLogger(BUKEstandarizacionDirsX.class.getName()).log(Level.SEVERE,
		    "Ha ocurrido en error durante la ejecucion del proceso", exc);
	}

	return lstTokens;
    }

    private static String toUpperCaseEspeciales(String original) {
	String resultado = "";
	int indice = 0;
	int indCarEsp = 0;

	while (indice < original.length()) {
	    indCarEsp = original.indexOf(UtilConstantes.PICO, indice);
	    if (indCarEsp == -1) {
		break;
	    }
	    resultado = resultado.concat(original.substring(indice, indCarEsp).toUpperCase())
		    .concat(UtilConstantes.PICO);
	    indice = indCarEsp + 1;
	}
	if (indCarEsp == -1) {
	    resultado = resultado.concat(original.substring(indice).toUpperCase());
	}

	return resultado;
    }
  
    public List<String> remplazaAvenidas(List<String> tokens) {
	int acumulador = 0;
	int maximoPalabras = 1;
	
	

	if(tokens == null || tokens.isEmpty()) {
	    return tokens;
	}
	if (tokens.get(0).trim().equals("AV") || tokens.get(0).equals("TV")) {
	    List<String> tokensSeg = tokens.subList(1, tokens.size());
	    
	    
	    for (int i = 0; (i < tokensSeg.size() && tokensSeg.get(i) != "|"); i++) {
		String tokenActual = tokensSeg.get(i).trim();
		
		if (!tokenActual.matches("[-+]?\\d*\\.?\\d+")) {
		    acumulador += tokenActual.length();
		    if (acumulador < 20 && maximoPalabras <= 3) {
			maximoPalabras++;
			continue;
		    } else {
			tokensSeg.remove(tokenActual);
			i--;
		    }
		}
	    }
	   
	    tokensSeg.add(0, tokens.get(0));
	    return tokensSeg;
	} else {
	    return tokens;
	}

    }

    private String suprimirElementosEliminables(String direccionOriginal) {
	String dirMayuscula = null;
	String descripcion = null;

	NodoConversion nc = null;

	if ((direccionOriginal == null) || (direccionOriginal.equals(""))) {
	    return direccionOriginal;
	}

	dirMayuscula = toUpperCaseEspeciales(direccionOriginal);

	Iterator<String> it = llavesArbolConversion.iterator();

	while (it.hasNext()) {
	    descripcion = it.next();
	    nc = arbolConversion.get(descripcion);

	    if (nc.getModoSuprimir().equals(UtilConstantes.LETRA_A)) {
		dirMayuscula = dirMayuscula.replace(descripcion, " ");
	    }
	}

	return dirMayuscula;
    }

    private static List<String> fragmentarDireccion(String direccion) {
	char[] tokenAct = new char[100];
	int indT = 0;
	char carAnterior = UtilConstantes.LETRA_MAYUS_B;

	int ind = 0;
	List<String> tokens = new ArrayList<>();
	String unToken = null;

	while (ind < direccion.length()) {
	    if ((carAnterior == UtilConstantes.LETRA_MAYUS_A) && (Character.isLetter(direccion.charAt(ind)))) {
		tokenAct[indT] = direccion.charAt(ind);
		indT++;
	    } else if ((carAnterior == UtilConstantes.LETRA_MAYUS_A) && (Character.isDigit(direccion.charAt(ind)))) {
		unToken = new String(tokenAct, 0, indT);
		tokens.add(unToken);
		indT = 0;
		tokenAct[indT] = direccion.charAt(ind);
		indT++;
		carAnterior = UtilConstantes.LETRA_MAYUS_N;
	    } else if ((carAnterior == UtilConstantes.LETRA_MAYUS_A)
		    && (Character.isSpaceChar(direccion.charAt(ind)))) {
		unToken = new String(tokenAct, 0, indT);
		tokens.add(unToken);
		indT = 0;
		carAnterior = UtilConstantes.LETRA_MAYUS_B;
	    } else if ((carAnterior == UtilConstantes.LETRA_MAYUS_A) && (esCaracterEspecial(direccion.charAt(ind)))) {
		unToken = new String(tokenAct, 0, indT);
		tokens.add(unToken);
		tokenAct[0] = direccion.charAt(ind);
		unToken = new String(tokenAct, 0, 1);
		tokens.add(unToken);
		carAnterior = UtilConstantes.LETRA_MAYUS_B;
		indT = 0;
	    } else if ((carAnterior == UtilConstantes.LETRA_MAYUS_B) && (Character.isLetter(direccion.charAt(ind)))) {
		tokenAct[0] = direccion.charAt(ind);
		indT = 1;
		carAnterior = UtilConstantes.LETRA_MAYUS_A;
	    } else if ((carAnterior == UtilConstantes.LETRA_MAYUS_B) && (Character.isDigit(direccion.charAt(ind)))) {
		tokenAct[0] = direccion.charAt(ind);
		indT = 1;
		carAnterior = UtilConstantes.LETRA_MAYUS_N;
	    } else if ((carAnterior == UtilConstantes.LETRA_MAYUS_B) && (esCaracterEspecial(direccion.charAt(ind)))) {
		tokenAct[0] = direccion.charAt(ind);
		unToken = new String(tokenAct, 0, 1);
		tokens.add(unToken);
		indT = 1;
	    } else if ((carAnterior != UtilConstantes.LETRA_MAYUS_B)
		    || (!Character.isSpaceChar(direccion.charAt(ind)))) {
		if ((carAnterior == UtilConstantes.LETRA_MAYUS_N) && (Character.isLetter(direccion.charAt(ind)))) {
		    unToken = new String(tokenAct, 0, indT);
		    tokens.add(unToken);
		    indT = 0;
		    tokenAct[indT] = direccion.charAt(ind);
		    indT++;
		    carAnterior = UtilConstantes.LETRA_MAYUS_A;
		} else if ((carAnterior == UtilConstantes.LETRA_MAYUS_N)
			&& (Character.isDigit(direccion.charAt(ind)))) {
		    tokenAct[indT] = direccion.charAt(ind);
		    indT++;
		} else if ((carAnterior == UtilConstantes.LETRA_MAYUS_N)
			&& (esCaracterEspecial(direccion.charAt(ind)))) {
		    unToken = new String(tokenAct, 0, indT);
		    tokens.add(unToken);
		    tokenAct[0] = direccion.charAt(ind);
		    unToken = new String(tokenAct, 0, 1);
		    tokens.add(unToken);
		    carAnterior = UtilConstantes.LETRA_MAYUS_B;
		    indT = 0;
		} else if ((carAnterior == UtilConstantes.LETRA_MAYUS_N)
			&& (Character.isSpaceChar(direccion.charAt(ind)))) {
		    unToken = new String(tokenAct, 0, indT);
		    tokens.add(unToken);
		    indT = 0;
		    carAnterior = UtilConstantes.LETRA_MAYUS_B;
		}
	    }
	    ind++;
	}

	if (indT > 0) {
	    unToken = new String(tokenAct, 0, indT);
	    tokens.add(unToken);
	}

	return tokens;
    }

    private static boolean esCaracterEspecial(char unCaracter) {
	return (unCaracter == UtilConstantes.DIGIT_NUMERAL) || (unCaracter == UtilConstantes.DIGIT_CORCHETE_DER)
		|| (unCaracter == UtilConstantes.DIGIT_CORCHETE_IZQ) || (unCaracter == UtilConstantes.DIGIT_TILDE)
		|| (unCaracter == UtilConstantes.DIGIT_GRADOS) || (unCaracter == UtilConstantes.DIGIT_INTERROGACION)
		|| (unCaracter == UtilConstantes.DIGIT_PUNTO_COMA) || (unCaracter == UtilConstantes.DIGIT_MAS)
		|| (unCaracter == UtilConstantes.DIGIT_PESO) || (unCaracter == UtilConstantes.DIGIT_PUNTO)
		|| (unCaracter == UtilConstantes.DIGIT_COMILLA) || (unCaracter == UtilConstantes.DIGIT_BACKSPACE)
		|| (unCaracter == UtilConstantes.LETRA_MINUS_Ñ) || (unCaracter == UtilConstantes.DIGIT_AMPERSAN)
		|| (unCaracter == UtilConstantes.DIGIT_ADMIRACION) || (unCaracter == UtilConstantes.DIGIT_MENOR_QUE)
		|| (unCaracter == UtilConstantes.DIGIT_MAYOR_QUE);
    }

    private static List<String> insertarOrdenadamente(List<String> llavesArbolConversion, String nvaDescripcion) {
	String descr = null;
	int ind = 0;
	while (ind < llavesArbolConversion.size()) {
	    descr = llavesArbolConversion.get(ind);

	    if (nvaDescripcion.length() > descr.length()) {
		llavesArbolConversion.add(ind, nvaDescripcion);
		break;
	    }
	    if ((nvaDescripcion.length() == descr.length()) && (nvaDescripcion.compareTo(descr) < 0)) {
		llavesArbolConversion.add(ind, nvaDescripcion);
		break;
	    }
	    ind++;
	}
	if (ind == llavesArbolConversion.size()) {
	    llavesArbolConversion.add(nvaDescripcion);
	}
	return llavesArbolConversion;
    }

    public void cargarInfoConvDirX() {
	NodoConversion nodoConversion = null;
	String descripcion = "";
	String conversion = "";
	String suprimir = "";

	try {
	    List<DaReglaEstDir> reglas = daReglasEstDirDao.obtenerReglasDirecciones();
	    arbolConversion = new HashMap<>();

	    for (DaReglaEstDir regla : reglas) {
		descripcion = regla.getDescripcion();
		conversion = regla.getConversion();
		suprimir = regla.getTipoConversion();
		nodoConversion = new NodoConversion(conversion, suprimir);
		arbolConversion.put(descripcion, nodoConversion);
	    }
	    Set<String> setDescripciones = arbolConversion.keySet();
	    Iterator<String> itDescripciones = setDescripciones.iterator();
	    String nvaDescripcion = null;

	    llavesArbolConversion = new ArrayList<>();
	    while (itDescripciones.hasNext()) {
		nvaDescripcion = itDescripciones.next();
		llavesArbolConversion = insertarOrdenadamente(llavesArbolConversion, nvaDescripcion);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private boolean esPuntoCardinal(String llave) {
	for (int i = 0; i < puntosCardinales.length; i++) {
	    if (puntosCardinales[i].equalsIgnoreCase(llave)) {
		return true;
	    }
	}
	return false;
    }

    private boolean esModificadorLetraAlfabeto(String llave) {
	return llave.matches("^[A-Z]");
    }

    private List<String> remplazarExpresionNoEncontrada(List<String> tokens, String tipoConversion) {
    	boolean esDobleLiteral = true;
    	String tokenAnterior = "";
    	for (int i = 0; i < tokens.size(); i++) {
    		esDobleLiteral = true;
	    String tokenActual = tokens.get(i);
	    NodoConversion nodoConversion = arbolConversion.get(tokenActual);
	    if(i != 0){
	    	//esDobleLiteral &= nodoConversion != null;
	    	esDobleLiteral &= tokenActual.length() == 2;
	    	esDobleLiteral &= !tokenAnterior.isEmpty() && StringUtils.isNumeric(tokenAnterior);
	    }
	    	
	    if ((nodoConversion != null) && (nodoConversion.getModoSuprimir().equalsIgnoreCase(tipoConversion))
		    && !nodoConversion.getConversion().equalsIgnoreCase(tokenActual) && !esDobleLiteral) {

		tokens.set(i, nodoConversion.getConversion());

	    }
	    tokenAnterior = tokens.get(i);
	}
	return tokens;
    }

}
