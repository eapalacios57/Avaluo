/**
 * permite validar si un numero es entero
 * 
 * @param evt:
 *            captura del evento al presionar el teclado este nos permira
 *            obtener la tecla presionada
 * @returns verdadero en caso de que la tecla presionada sea permitida
 */
function numeroEntero(evt) {
	var keyPressed = (evt.which)?evt.which: evt.keyCode;
	return keyPressed <= 31 || (keyPressed >=48 && keyPressed <= 57) ;
}

/**
 * 
 * @param evt
 * @param valor
 * @returns {Boolean}
 */
function enteroNegativo(evt,valor) {
	var keyPressed = (evt.which)?evt.which: evt.keyCode;
	if (valor.length==0 && (keyPressed == 45 || keyPressed == 109 || keyPressed == 189 )) {
		return valor.indexOf('-')==-1;
	}
	return keyPressed <= 31 || (keyPressed >=48 && keyPressed <= 57) ;

}

/**
 * 
 * @param evt
 * @param valor
 * @returns {Boolean}
 */
function decimalNegativo(evt,valor) {
	var keyPressed = (evt.which)?evt.which: evt.keyCode;
	if (valor.length==0 && (keyPressed == 45 || keyPressed == 109 || keyPressed == 189 )) {
		return valor.indexOf('-')==-1;
	}
	if(keyPressed == 46 || keyPressed == 110 || keyPressed == 190) {
		if(valor.indexOf('-')!=-1 && valor.length>1) {
				return valor.indexOf('.')==-1;
		}else if (valor.indexOf('-')==-1 && valor.length>0 ) {
			return valor.indexOf('.')==-1;
		} 
	}
	return keyPressed <= 31 || (keyPressed >=48 && keyPressed <= 57) ;

}

/**
 * 
 * @param evt
 * @param valor
 * @returns {Boolean}
 */
function numeroDecimal(evt,valor) {
	var keyPressed = (evt.which)?evt.which: evt.keyCode;
	if (valor.length>0 && (keyPressed == 46 || keyPressed == 110 || keyPressed == 190)) {
		return valor.indexOf('.')==-1;
	}
	return keyPressed <= 31 || (keyPressed >=48 && keyPressed <= 57) ;
}



