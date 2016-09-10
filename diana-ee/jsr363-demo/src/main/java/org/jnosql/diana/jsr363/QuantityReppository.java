package org.jnosql.diana.jsr363;

import tec.uom.se.function.QuantityFunctions;

import javax.inject.Inject;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Temperature;
import javax.measure.spi.QuantityFactory;
import java.util.ArrayList;
import java.util.List;

import static tec.uom.se.function.QuantityFunctions.fiterByUnit;
import static tec.uom.se.unit.Units.CELSIUS;
import static tec.uom.se.unit.Units.KELVIN;

public class QuantityReppository {

    @Inject
    private List<Quantity> quantities;

    @Inject
    private QuantityFactory<Temperature> factory;


    public void add(Quantity<Temperature> quantity) {
        quantities.add(quantity);
    }

    Quantity<Temperature> sum(Unit<Temperature> unit) {
        List<Quantity<Temperature>> temperatures = getQuantities();
        return temperatures.stream().filter(fiterByUnit(CELSIUS, KELVIN)).reduce(QuantityFunctions.sum(unit)).orElse(factory.create(0, unit));
    }


    Quantity<Temperature> average(Unit<Temperature> unit) {
        List<Quantity<Temperature>> temperatures = getQuantities();
        return temperatures.stream().filter(fiterByUnit(CELSIUS, KELVIN)).collect(QuantityFunctions.summarizeQuantity(unit)).getAverage();
    }

    private List<Quantity<Temperature>> getQuantities() {
        List<Quantity<Temperature>> temperatures = new ArrayList<>();
        quantities.forEach(temperatures::add);
        return temperatures;
    }
}
