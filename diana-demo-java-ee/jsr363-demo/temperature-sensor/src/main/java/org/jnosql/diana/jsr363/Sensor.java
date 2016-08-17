package org.jnosql.diana.jsr363;


import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCollectionEntity;
import tec.uom.se.format.QuantityFormat;

import javax.measure.Quantity;
import javax.measure.quantity.Temperature;
import java.time.LocalDate;
import java.util.Objects;

public class Sensor {


    private final String sensorId;

    private final LocalDate time;

    private final Quantity<Temperature> temperature;

    Sensor(String sensorId, LocalDate time, Quantity<Temperature> temperature) {
        this.sensorId = sensorId;
        this.time = time;
        this.temperature = temperature;
    }

    public String getSensorId() {
        return sensorId;
    }

    public LocalDate getTime() {
        return time;
    }

    public Quantity<Temperature> getTemperature() {
        return temperature;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) o;
        return Objects.equals(sensorId, sensor.sensorId) &&
                Objects.equals(time, sensor.time) &&
                Objects.equals(temperature, sensor.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, time, temperature);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sensor{");
        sb.append("sensorId='").append(sensorId).append('\'');
        sb.append(", time=").append(time);
        sb.append(", temperature=").append(temperature);
        sb.append('}');
        return sb.toString();
    }

    public static Sensor of(DocumentCollectionEntity entity) {
        String sensorId = entity.find("sensorId").get().getValue().get(String.class);
        LocalDate time = entity.find("time").get().getValue().get(LocalDate.class);
        Document temperature = entity.find("temperature").get();
        QuantityFormat instance = QuantityFormat.getInstance();
        Quantity<Temperature> quantity = (Quantity<Temperature>) instance.parse(temperature.getValue().get(String.class));
        return Sensor.builder()
                .withSensorId(sensorId)
                .withTime(time)
                .withTemperature(quantity).build();

    }

    public static SensorBuilder builder() {
        return new SensorBuilder();
    }

    public static class SensorBuilder {

        private String sensorId;

        private LocalDate time;

        private Quantity<Temperature> temperature;

        private SensorBuilder() {
        }

        public SensorBuilder withSensorId(String sensorId) {
            this.sensorId = sensorId;
            return this;
        }

        public SensorBuilder withTime(LocalDate time) {
            this.time = time;
            return this;
        }

        public SensorBuilder withTemperature(Quantity<Temperature> temperature) {
            this.temperature = temperature;
            return this;
        }

        public Sensor build() {
            return new Sensor(sensorId, time, temperature);
        }
    }
}
