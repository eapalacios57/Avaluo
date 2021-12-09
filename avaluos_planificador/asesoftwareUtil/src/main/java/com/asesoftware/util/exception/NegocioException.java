package com.asesoftware.util.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.lang.UtilTexto;

/**
 * Excepcion para el manejo de errores dentro del sistema permite definir la
 * prioridad de los errores y anidarlos uno con otro.
 * 
 * @author stilaguy
 *
 */
@ApplicationException(rollback = true)
public class NegocioException extends Exception {

    private static final long serialVersionUID = -1877514126146793809L;

    /**
     * Contiene los errores anidados asociados a una excepcion general.
     */
    protected List<NegocioException> errores;
    /**
     * define el tipo de excepcion y el nivel de prioridad del mismo determina el
     * tratamiento que debe darsele a la excepcion.
     */
    protected TipoErrorNegocio tipo;
    /**
     * Identifiicador unico del mensaje de error, de esta forma se puede recuperar
     * el mensaje de la excepcion de un archivo de propiedades.
     */
    protected Long id;
    /**
     * Si el mensaje contiene una traza o necesita una descripcion mas compleja la
     * misma puede ser almacenada en esta propiedad.
     */
    protected String detalle;

    protected String titulo;

    /**
     * Permite crear una excepcion de negocio con el mnsaje y definiendo por defecto
     * el tipo como Error.
     * 
     * @param mensaje
     */
    public NegocioException(String mensaje) {
	this(TipoErrorNegocio.ERROR, mensaje);
    }

    /**
     * Permite crear una excepcion de negocio solo con el tipo y el mensaje.
     * 
     * @param tipo
     * @param mensaje
     */
    public NegocioException(TipoErrorNegocio tipo, String mensaje) {
	this(tipo, mensaje, null);
    }

    /**
     * Permite crear una excepcion de negocio con el tipo, el mensaje y el detalle.
     * 
     * @param tipo
     * @param mensaje
     * @param detalle
     */
    public NegocioException(TipoErrorNegocio tipo, String mensaje, String detalle) {
	this(0, tipo, null, mensaje, detalle);
    }

    /**
     * Constructor para una excepcion de negocio a traves del identificador. Este
     * constructor solo es usado por el manejador de excepciones. Para recuperar
     * previamente el mensaje asociado al identificador de la excepcion.
     * 
     * @param id
     *            identificador de la excepcion.
     * @param tipo
     *            tipo de excepcion si no se define por defecto se usara INFO.
     * @param tiitulo
     *            identificador del mensaje o titulo.
     * @param mensaje
     *            mensaje a modo de titulo del error presentado.
     * @param detalle
     *            Si es necesario se pueda dar una informacion mas detallada del
     *            origen del mensaje.
     */
    protected NegocioException(long id, TipoErrorNegocio tipo, String titulo, String mensaje, String detalle) {
	super(mensaje);
	this.tipo = tipo == null ? TipoErrorNegocio.INFO : tipo;
	this.id = id;
	this.titulo = titulo;
	this.detalle = detalle;
	errores = new ArrayList<>();
    }

    /**
     * Permite anidar una excepcion de negocio a la excepcion maestra.
     * 
     * @param error:
     *            error a adicionar.
     */
    public void agregarError(NegocioException error) {
	errores.add(error);
    }

    public void removerError(Long idRemover) {
	if (errores == null)
	    return;
	NegocioException quitar = null;
	for (NegocioException negocioException : errores) {
	    if (negocioException.getId().compareTo(idRemover) == 0) {
		quitar = negocioException;
		break;
	    }
	}
	if (quitar != null)
	    errores.remove(quitar);
    }

    public String obtenerTexto() {
	StringBuilder texto = new StringBuilder();
	if (getErrores() == null || getErrores().isEmpty()) {
	    return getMessage();
	}
	procesarAnidados(texto, this, 0);
	return texto.toString();
    }

    public String procesarAnidados(StringBuilder texto, NegocioException e, int identacion) {
	if (texto == null)
	    texto = new StringBuilder();
	if (e.getErrores() == null || e.getErrores().isEmpty()) {
	    return "";
	}
	for (int i = 0; i < (identacion - 1); i++) {
	    texto.append("\t");
	}
	texto.append(e.getMessage());
	texto.append("\r\n");
	for (NegocioException anidar : e.getErrores()) {
	    for (int i = 0; i < identacion; i++) {
		texto.append("\t");
	    }
	    texto.append("\t");
	    if (anidar.getErrores() == null || anidar.getErrores().isEmpty()) {
		texto.append(anidar.getMessage() + (UtilTexto.estaVacio(anidar.getDetalle()) ? "" : ":" + anidar.getDetalle()));
		texto.append("\r\n");
	    } else {
	    	procesarAnidados(texto, anidar, identacion + 1);
	    }
	}
	return texto.toString();
    }

    /*
     * Getters y setters
     */

    public List<NegocioException> getErrores() {
	return errores;
    }

    public TipoErrorNegocio getTipo() {
	return tipo;
    }

    public String getDetalle() {
	return detalle;
    }

    public Long getId() {
	return id;
    }

    public String getTitulo() {
	return titulo;
    }

    public void setDetalle(String detalle) {
	this.detalle = detalle;
    }

}