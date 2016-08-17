package org.jnosql.diana.jsr363;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SensorRepresentation implements Serializable {

    private String sensorId;

    private String time;

    private QuantityRepresentation quantity;

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public QuantityRepresentation getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityRepresentation quantity) {
        this.quantity = quantity;
    }


    @JsonIgnore
    public Sensor toSensor() {
        return Sensor.builder()
                .withSensorId(sensorId)
                .withTemperature(quantity.toQuantity())
                .withTime(LocalDate.parse(time)).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensorRepresentation)) {
            return false;
        }
        SensorRepresentation that = (SensorRepresentation) o;
        return Objects.equals(sensorId, that.sensorId) &&
                Objects.equals(time, that.time) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, time, quantity);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SensorRepresentation{");
        sb.append("sensorId='").append(sensorId).append('\'');
        sb.append(", time=").append(time);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

    @JsonIgnore
    public static SensorRepresentation of(Sensor sensor) {
        SensorRepresentation representation = new SensorRepresentation();
        representation.setQuantity(QuantityRepresentation.of(sensor.getTemperature()));
        representation.setSensorId(sensor.getSensorId());
        representation.setTime(sensor.getTime().toString());
        return representation;
    }
}
