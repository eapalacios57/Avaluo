
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
    "wkid",
    "latestWkid"
})
public class SpatialReference implements Serializable
{

    @JsonProperty("wkid")
    private Long wkid;
    @JsonProperty("latestWkid")
    private Long latestWkid;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -9157009880235175726L;

    @JsonProperty("wkid")
    public Long getWkid() {
        return wkid;
    }

    @JsonProperty("wkid")
    public void setWkid(Long wkid) {
        this.wkid = wkid;
    }

    @JsonProperty("latestWkid")
    public Long getLatestWkid() {
        return latestWkid;
    }

    @JsonProperty("latestWkid")
    public void setLatestWkid(Long latestWkid) {
        this.latestWkid = latestWkid;
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
        return new ToStringBuilder(this).append("wkid", wkid).append("latestWkid", latestWkid).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(wkid).append(additionalProperties).append(latestWkid).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SpatialReference) == false) {
            return false;
        }
        SpatialReference rhs = ((SpatialReference) other);
        return new EqualsBuilder().append(wkid, rhs.wkid).append(additionalProperties, rhs.additionalProperties).append(latestWkid, rhs.latestWkid).isEquals();
    }

}
