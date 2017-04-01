package org.jnosql.diana.jsr363;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.time.LocalTime;

import javax.inject.Inject;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Temperature;
import javax.measure.spi.QuantityFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import tec.uom.se.unit.Units;

@Path("api/v1/temperature")
@Produces(APPLICATION_JSON + ";charset=UTF-8")
@Consumes(APPLICATION_JSON + ";charset=UTF-8")
public class TemperatureResource {


    @Inject
    private QuantityFactory<Temperature> factory;

    @Inject
    private QuantityRepository quantityReppository;


    @GET
    public QuantityRepresentation example() {
        return QuantityRepresentation.of(factory.create(10, Units.KELVIN));
    }

    @POST
    public void add(QuantityRepresentation representation) {
        LocalTime now = LocalTime.now();
        LocalTime.of(now.getHour(), now.getMinute());
        Quantity<Temperature> temperatureQuantity = representation.toQuantity();
        quantityReppository.add(temperatureQuantity);
    }

    @Path("sum/{unit}")
    @GET
    public QuantityRepresentation sum(@PathParam("unit") String unit) {
        Quantity<Temperature> sum = quantityReppository.sum(getUnit(unit));
        return QuantityRepresentation.of(sum);
    }

    @Path("average/{unit}")
    @GET
    public QuantityRepresentation average(@PathParam("unit") String unit) {
        Quantity<Temperature> sum = quantityReppository.average(getUnit(unit));
        return QuantityRepresentation.of(sum);
    }

    private Unit<Temperature> getUnit(String unit) {
        if ("CELSIUS".equalsIgnoreCase(unit)) {
            return Units.CELSIUS;
        }
        return Units.KELVIN;
    }

}