
package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
    "displayFieldName",
    "geometryType",
    "spatialReference",
    "fields",
    "features",
    "exceededTransferLimit"
})
public class Value implements Serializable
{

    @JsonProperty("displayFieldName")
    private String displayFieldName;
    @JsonProperty("geometryType")
    private String geometryType;
    @JsonProperty("spatialReference")
    @Valid
    private SpatialReference spatialReference;
    @JsonProperty("fields")
    @Valid
    private List<Field> fields = null;
    @JsonProperty("features")
    @Valid
    private List<Feature> features = null;
    @JsonProperty("exceededTransferLimit")
    private Boolean exceededTransferLimit;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4659659305581577450L;

    @JsonProperty("displayFieldName")
    public String getDisplayFieldName() {
        return displayFieldName;
    }

    @JsonProperty("displayFieldName")
    public void setDisplayFieldName(String displayFieldName) {
        this.displayFieldName = displayFieldName;
    }

    @JsonProperty("geometryType")
    public String getGeometryType() {
        return geometryType;
    }

    @JsonProperty("geometryType")
    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    @JsonProperty("spatialReference")
    public SpatialReference getSpatialReference() {
        return spatialReference;
    }

    @JsonProperty("spatialReference")
    public void setSpatialReference(SpatialReference spatialReference) {
        this.spatialReference = spatialReference;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonProperty("features")
    public List<Feature> getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @JsonProperty("exceededTransferLimit")
    public Boolean getExceededTransferLimit() {
        return exceededTransferLimit;
    }

    @JsonProperty("exceededTransferLimit")
    public void setExceededTransferLimit(Boolean exceededTransferLimit) {
        this.exceededTransferLimit = exceededTransferLimit;
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
        return new ToStringBuilder(this).append("displayFieldName", displayFieldName).append("geometryType", geometryType).append("spatialReference", spatialReference).append("fields", fields).append("features", features).append("exceededTransferLimit", exceededTransferLimit).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(displayFieldName).append(additionalProperties).append(features).append(exceededTransferLimit).append(spatialReference).append(fields).append(geometryType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Value) == false) {
            return false;
        }
        Value rhs = ((Value) other);
        return new EqualsBuilder().append(displayFieldName, rhs.displayFieldName).append(additionalProperties, rhs.additionalProperties).append(features, rhs.features).append(exceededTransferLimit, rhs.exceededTransferLimit).append(spatialReference, rhs.spatialReference).append(fields, rhs.fields).append(geometryType, rhs.geometryType).isEquals();
    }

}
