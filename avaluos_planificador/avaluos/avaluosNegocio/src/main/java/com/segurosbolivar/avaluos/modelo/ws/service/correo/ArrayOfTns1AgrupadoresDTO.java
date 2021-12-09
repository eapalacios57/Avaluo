package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.AgrupadoresDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns1_AgrupadoresDTO", propOrder = {
    "item"
})
public class ArrayOfTns1AgrupadoresDTO {

    protected List<AgrupadoresDTO> item;

    public List<AgrupadoresDTO> getItem() {
        if (item == null) {
            item = new ArrayList<AgrupadoresDTO>();
        }
        return this.item;
    }

    public void setItem(List<AgrupadoresDTO> item) {
    	this.item=item;
    }
    
}