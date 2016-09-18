package org.jnosql.diana.jsr363;


import org.jnosql.diana.api.document.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class SensorRepository {

    private static final String TEMPERATURE = "temperature";
    private static final String SENSORS = "sensors";
    private static final Document SENSOR_ID = Document.of("_id", "devices");

    @Inject
    private DocumentCollectionManager documentCollectionManager;

    public void save(Sensor sensor) {
        DocumentEntity entity = DocumentEntity.of(TEMPERATURE);
        entity.add(Document.of("sensorId", sensor.getSensorId()));
        entity.add(Document.of("temperature", sensor.getTemperature().toString()));
        entity.add(Document.of("time", sensor.getTime()));
        documentCollectionManager.save(entity);
    }


    public List<String> sensors() {
        DocumentQuery query = DocumentQuery.of(SENSORS);
        query.addCondition(DocumentCondition.eq(SENSOR_ID));
        List<DocumentEntity> documentCollectionEntities = documentCollectionManager.find(query);
        if (documentCollectionEntities.isEmpty()) {
            return emptyList();
        }
        Document devices = documentCollectionEntities.get(0).find("devices").get();
        return devices.getValue().cast();
    }

    public void saveSensors(List<String> sensors) {
        DocumentEntity entity = DocumentEntity.of(SENSORS);
        entity.add(SENSOR_ID);
        entity.add(Document.of("devices", sensors.stream().distinct().collect(toList())));
        if (sensors.size() == 1) {
            documentCollectionManager.save(entity);
        } else {
            documentCollectionManager.update(entity);
        }
    }

    public List<Sensor> getSensor(String sensorId) {
        DocumentQuery query = DocumentQuery.of(TEMPERATURE);
        query.addCondition(DocumentCondition.eq(Document.of("sensorId", sensorId)));
        List<DocumentEntity> documentCollectionEntities = documentCollectionManager.find(query);
        return documentCollectionEntities.stream().map(Sensor::of).collect(Collectors.toList());
    }

}
