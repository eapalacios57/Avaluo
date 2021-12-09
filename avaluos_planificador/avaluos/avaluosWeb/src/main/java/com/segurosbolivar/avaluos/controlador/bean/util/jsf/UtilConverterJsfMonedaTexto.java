package com.segurosbolivar.avaluos.controlador.bean.util.jsf;

import java.text.DecimalFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("moneda")
public class UtilConverterJsfMonedaTexto implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valorNuevo) {
		String valor = valorNuevo;
		return valor;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valorNuevo) {
		String valor = DecimalFormat.getCurrencyInstance(Locale.US).format(valorNuevo);
		return valor;
	}

}
