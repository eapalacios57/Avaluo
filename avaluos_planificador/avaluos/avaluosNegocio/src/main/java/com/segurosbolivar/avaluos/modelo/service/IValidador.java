package com.segurosbolivar.avaluos.modelo.service;




import java.io.Serializable;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

@Local
public interface IValidador extends Serializable{
        
    boolean validar(Avaluo avaluo, NegocioException e,UsuarioDto usuario) throws NegocioException;
    
}
