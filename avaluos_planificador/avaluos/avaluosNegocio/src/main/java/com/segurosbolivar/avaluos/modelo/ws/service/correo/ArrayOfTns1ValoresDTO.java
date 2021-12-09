package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.ValoresDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns1_ValoresDTO", propOrder = {
    "item"
})
public class ArrayOfTns1ValoresDTO {

    protected List<ValoresDTO> item;

    public List<ValoresDTO> getItem() {
        if (item == null) {
            item = new ArrayList<ValoresDTO>();
        }
        return this.item;
    }
    public void setItem(List<ValoresDTO> item) {
        this.item=item;
    }

}