package solver.multipleGamesSolver;

import lombok.Getter;

@Getter
public class TimeStatistics {

    private double totalValue;
    private double maximumValue;
    private double averageValue;
    private double medianValue;
    private double standardDeviationValue;
    private double quantile90Value;
    private double quantile95Value;
    private double quantile99Value;

    public TimeStatistics() {
        totalValue = 0;
        maximumValue = 0;
        averageValue = 0;
        medianValue = 0;
        standardDeviationValue = 0;
        quantile90Value = 0;
        quantile95Value = 0;
        quantile99Value = 0;
    }

    public void update(double tot, double max, double avg, double med, double dev, double p90, double p95, double p99) {
        totalValue = tot;
        maximumValue = max;
        averageValue = avg;
        medianValue = med;
        standardDeviationValue = dev;
        quantile90Value = p90;
        quantile95Value = p95;
        quantile99Value = p99;
    }

    public String getCSVData() {
        return new StringBuilder()
                .append(totalValue)
                .append(";")
                .append(maximumValue)
                .append(";")
                .append(averageValue)
                .append(";")
                .append(medianValue)
                .append(";")
                .append(standardDeviationValue)
                .append(";")
                .append(quantile90Value)
                .append(";")
                .append(quantile95Value)
                .append(";")
                .append(quantile99Value)
                .toString();
    }


}
