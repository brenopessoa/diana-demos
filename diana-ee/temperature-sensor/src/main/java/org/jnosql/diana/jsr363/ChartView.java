package org.jnosql.diana.jsr363;

import org.primefaces.model.chart.LineChartModel;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;

import static tec.uom.se.unit.Units.CELSIUS;
import static tec.uom.se.unit.Units.KELVIN;

@Model
public class ChartView implements Serializable {

	private static final long serialVersionUID = 2024781042109517878L;
	
	@Inject
    private LineChartModelBuilder builder;


    public LineChartModel getAreaModelCelsius() {
        return builder.createAreaModel(CELSIUS);
    }

    public LineChartModel getAreaModelKelvin() {
        return builder.createAreaModel(KELVIN);
    }
}