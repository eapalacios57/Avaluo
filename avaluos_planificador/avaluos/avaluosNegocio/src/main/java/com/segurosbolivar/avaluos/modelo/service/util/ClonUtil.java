package com.segurosbolivar.avaluos.modelo.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.log4j.Logger;

public final class ClonUtil {

	private static final Logger log = Logger.getLogger(ClonUtil.class);

	private ClonUtil() {
	}

	public static Object getClon(Object original) {
		Object res = null;
		try {
			res = original.getClass().newInstance();
			clonar(res, original, original.getClass());
		} catch (Exception e) {
			log.error(e);
		}
		return res;
	}

	private static void clonar(Object copiar, Object original, Class<?> clase) throws Exception {
		boolean acce;
		for (Field field : clase.getDeclaredFields()) {
			if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
				acce = field.isAccessible();
				field.setAccessible(true);

				field.set(copiar, field.get(original));
				field.setAccessible(acce);
			}
		}
		if (!clase.getSuperclass().equals(Object.class)) {
			clonar(copiar, original, clase.getSuperclass());
		}
	}

	public static void clonar(Object origen) {
		try {
			clonar(origen.getClass().newInstance(), origen, origen.getClass());
		} catch (Exception e) {
			log.error(e);
		}
	}

}
