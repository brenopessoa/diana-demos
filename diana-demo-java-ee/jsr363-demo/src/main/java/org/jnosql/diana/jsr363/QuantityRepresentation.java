package org.jnosql.diana.jsr363;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import tec.uom.se.ComparableQuantity;
import tec.uom.se.format.QuantityFormat;

import javax.measure.Quantity;
import javax.measure.quantity.Temperature;
import java.util.Objects;

public class QuantityRepresentation {

    private final String unit;

    private final Number value;

    @JsonCreator
    public QuantityRepresentation(@JsonProperty("unit") String unit, @JsonProperty("value") Number value) {
        this.unit = unit;
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public Number getValue() {
        return value;
    }

    @JsonIgnore
    public Quantity<Temperature> toQuantity() {
        return (Quantity<Temperature>) QuantityFormat.getInstance().parse(value + " " + unit);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuantityRepresentation{");
        sb.append("unit='").append(unit).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuantityRepresentation)) {
            return false;
        }
        QuantityRepresentation that = (QuantityRepresentation) o;
        return Objects.equals(unit, that.unit) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit, value);
    }

    public static QuantityRepresentationBuilder builder() {
        return new QuantityRepresentationBuilder();
    }

    public static QuantityRepresentation of(Quantity quantity) {
        return new QuantityRepresentation(quantity.getUnit().toString(), quantity.getValue());
    }

    public static class QuantityRepresentationBuilder {

        private String unit;

        private Number value;

        private QuantityRepresentationBuilder() {
        }

        public QuantityRepresentationBuilder withUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public QuantityRepresentationBuilder withValue(Number value) {
            this.value = value;
            return this;
        }

        public QuantityRepresentation build() {
            return new QuantityRepresentation(unit, value);
        }
    }
}
