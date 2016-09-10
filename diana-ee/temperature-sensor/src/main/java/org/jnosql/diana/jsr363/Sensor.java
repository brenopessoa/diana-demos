package org.jnosql.diana.jsr363;


import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCollectionEntity;
import tec.uom.se.format.QuantityFormat;

import javax.measure.Quantity;
import javax.measure.quantity.Temperature;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Sensor {


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM HH:mm");

    private final String sensorId;

    private final LocalDateTime time;

    private final Quantity<Temperature> temperature;

    Sensor(String sensorId, LocalDateTime time, Quantity<Temperature> temperature) {
        this.sensorId = sensorId;
        this.time = time;
        this.temperature = temperature;
    }

    public String getSensorId() {
        return sensorId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getFormatedDate() {
        return DATE_TIME_FORMATTER.format(time);
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
        LocalDateTime time = entity.find("time").get().getValue().get(LocalDateTime.class);
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

        private LocalDateTime time;

        private Quantity<Temperature> temperature;

        private SensorBuilder() {
        }

        public SensorBuilder withSensorId(String sensorId) {
            this.sensorId = sensorId;
            return this;
        }

        public SensorBuilder withTime(LocalDateTime time) {
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
