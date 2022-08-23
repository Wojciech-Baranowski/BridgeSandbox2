package solver.multipleGamesSolver;

import lombok.Getter;

@Getter
public class VisitedNodesStatistics {

    private long totalValue;
    private long maximumValue;
    private long averageValue;
    private long medianValue;
    private long standardDeviationValue;
    private long quantile90Value;
    private long quantile95Value;
    private long quantile99Value;

    public VisitedNodesStatistics() {
        totalValue = 0;
        maximumValue = 0;
        averageValue = 0;
        medianValue = 0;
        standardDeviationValue = 0;
        quantile90Value = 0;
        quantile95Value = 0;
        quantile99Value = 0;
    }

    public void update(long tot, long max, long avg, long med, long dev, long p90, long p95, long p99) {
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
