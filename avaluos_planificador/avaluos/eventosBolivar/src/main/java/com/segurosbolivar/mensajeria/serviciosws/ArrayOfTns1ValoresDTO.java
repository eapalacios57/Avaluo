
package com.segurosbolivar.mensajeria.serviciosws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.mensajeria.utilsws.ValoresDTO;


/**
 * <p>Java class for ArrayOf_tns1_ValoresDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOf_tns1_ValoresDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="item" type="{http://utilsws.mensajeria.segurosbolivar.com}ValoresDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOf_tns1_ValoresDTO", propOrder = {
    "item"
})
public class ArrayOfTns1ValoresDTO {

    protected List<ValoresDTO> item;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValoresDTO }
     * 
     * 
     */
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
