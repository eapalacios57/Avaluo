package com.segurosbolivar.avaluos.modelo.jaxb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.log4j.Logger;

/**
 * Convierte fechas en formato largo y extendido como Tue Mar 19 00:00:00
 * GMT-05:00 2019 a Date y viceversa, esto para manejar los formatos que vienen
 * del servicio de MAP.
 * 
 * @author asanchez
 *
 */
public class LongDateStringToDateAdapter extends XmlAdapter<String, Date> {

	private final static String LONG_DATE_FORMAT = "E MMM dd HH:mm:ss zz yyyy";
	private final static SimpleDateFormat sdf = new SimpleDateFormat(LONG_DATE_FORMAT, Locale.ENGLISH);
	private static Logger logger = Logger.getLogger(LongDateStringToDateAdapter.class);
	@Override
	public Date unmarshal(String v) throws Exception {
		synchronized (sdf) {
			if(v.equals("null"))
				v = new Date().toString();
			return sdf.parse(v);
		}
	}

	@Override
	public String marshal(Date v) throws Exception {
		synchronized (sdf) {
			return sdf.format(v);
		}
	}

}
