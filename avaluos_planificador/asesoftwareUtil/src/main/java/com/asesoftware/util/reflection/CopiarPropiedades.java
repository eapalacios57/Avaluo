package com.asesoftware.util.reflection;

import java.lang.reflect.InvocationTargetException;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;

import com.asesoftware.util.cons.Constantes;

public class CopiarPropiedades {

	private static Logger logger = Logger.getLogger(Constantes.LOGGER_PRINCIPAL);
	
	/**
     * Copia el valor que contienen los atributos del objeto fuente 
     * a los atributos del objeto destino cuyos nombres sean exactamente iguales.
     * Los atributos que no coinciden se omiten (Se dejan tal cual como estaban 
     * en el objeto destino).
     * 
     * @param destino objeto al que se le van a setear los valores de sus atributos
     * @param fuente objeto del que se copian los valores de los atributos
     */
    public static final void copiarPropiedades(Object destino, Object fuente) {
        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
        try {
            BeanUtils.copyProperties(destino, fuente);
        } catch (IllegalAccessException | InvocationTargetException ex) {
        	logger.error("Error copiando propiedades reflection",ex);
            
        }
        
    }

	
}
