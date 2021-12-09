
package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "FID",
    "FID_Asegur",
    "CALIFICACI",
    "ASEGURABIL",
    "ADMBDG_Ase",
    "SHAPE_Leng",
    "SHAPE_Area",
    "FID_p"
})
public class Attributes implements Serializable
{

    @JsonProperty("FID")
    private Long fID;
    @JsonProperty("FID_Asegur")
    private Long fIDAsegur;
    @JsonProperty("CALIFICACI")
    private Long cALIFICACI;
    @JsonProperty("ASEGURABIL")
    private String aSEGURABIL;
    @JsonProperty("ADMBDG_Ase")
    private Long aDMBDGAse;
    @JsonProperty("SHAPE_Leng")
    private Double sHAPELeng;
    @JsonProperty("SHAPE_Area")
    private Double sHAPEArea;
    @JsonProperty("FID_p")
    private Long fIDP;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2463791243530716623L;

    @JsonProperty("FID")
    public Long getFID() {
        return fID;
    }

    @JsonProperty("FID")
    public void setFID(Long fID) {
        this.fID = fID;
    }

    @JsonProperty("FID_Asegur")
    public Long getFIDAsegur() {
        return fIDAsegur;
    }

    @JsonProperty("FID_Asegur")
    public void setFIDAsegur(Long fIDAsegur) {
        this.fIDAsegur = fIDAsegur;
    }

    @JsonProperty("CALIFICACI")
    public Long getCALIFICACI() {
        return cALIFICACI;
    }

    @JsonProperty("CALIFICACI")
    public void setCALIFICACI(Long cALIFICACI) {
        this.cALIFICACI = cALIFICACI;
    }

    @JsonProperty("ASEGURABIL")
    public String getASEGURABIL() {
        return aSEGURABIL;
    }

    @JsonProperty("ASEGURABIL")
    public void setASEGURABIL(String aSEGURABIL) {
        this.aSEGURABIL = aSEGURABIL;
    }

    @JsonProperty("ADMBDG_Ase")
    public Long getADMBDGAse() {
        return aDMBDGAse;
    }

    @JsonProperty("ADMBDG_Ase")
    public void setADMBDGAse(Long aDMBDGAse) {
        this.aDMBDGAse = aDMBDGAse;
    }

    @JsonProperty("SHAPE_Leng")
    public Double getSHAPELeng() {
        return sHAPELeng;
    }

    @JsonProperty("SHAPE_Leng")
    public void setSHAPELeng(Double sHAPELeng) {
        this.sHAPELeng = sHAPELeng;
    }

    @JsonProperty("SHAPE_Area")
    public Double getSHAPEArea() {
        return sHAPEArea;
    }

    @JsonProperty("SHAPE_Area")
    public void setSHAPEArea(Double sHAPEArea) {
        this.sHAPEArea = sHAPEArea;
    }

    @JsonProperty("FID_p")
    public Long getFIDP() {
        return fIDP;
    }

    @JsonProperty("FID_p")
    public void setFIDP(Long fIDP) {
        this.fIDP = fIDP;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fID", fID).append("fIDAsegur", fIDAsegur).append("cALIFICACI", cALIFICACI).append("aSEGURABIL", aSEGURABIL).append("aDMBDGAse", aDMBDGAse).append("sHAPELeng", sHAPELeng).append("sHAPEArea", sHAPEArea).append("fIDP", fIDP).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(fIDAsegur).append(cALIFICACI).append(aDMBDGAse).append(aSEGURABIL).append(additionalProperties).append(fIDP).append(fID).append(sHAPEArea).append(sHAPELeng).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Attributes) == false) {
            return false;
        }
        Attributes rhs = ((Attributes) other);
        return new EqualsBuilder().append(fIDAsegur, rhs.fIDAsegur).append(cALIFICACI, rhs.cALIFICACI).append(aDMBDGAse, rhs.aDMBDGAse).append(aSEGURABIL, rhs.aSEGURABIL).append(additionalProperties, rhs.additionalProperties).append(fIDP, rhs.fIDP).append(fID, rhs.fID).append(sHAPEArea, rhs.sHAPEArea).append(sHAPELeng, rhs.sHAPELeng).isEquals();
    }

}
