package controllers.solverSettingsControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;

import static engine.scene.SceneBean.getScene;

public class TimeStatistics {

    private final Text title;
    private final Text total;
    private final Text maximum;
    private final Text average;
    private final Text median;
    private final Text standardDeviation;
    private final Text quantile90;
    private final Text quantile95;
    private final Text quantile99;
    private double totalValue;
    private double maximumValue;
    private double averageValue;
    private double medianValue;
    private double standardDeviationValue;
    private double quantile90Value;
    private double quantile95Value;
    private double quantile99Value;

    TimeStatistics(DrawableFactory drawableFactory, Drawable background) {
        title = drawableFactory.makeText(
                "Time:",
                20 + background.getX(),
                250 + background.getY(),
                "HBE32",
                "black");
        getScene().addObjectHigherThan(title, background);

        total = drawableFactory.makeText(
                "sum:",
                12 + title.getX(),
                40 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(total, background);

        maximum = drawableFactory.makeText(
                "max:",
                10 + title.getX(),
                72 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(maximum, background);

        average = drawableFactory.makeText(
                "avg:",
                18 + title.getX(),
                104 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(average, background);

        median = drawableFactory.makeText(
                "med:",
                12 + title.getX(),
                136 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(median, background);

        standardDeviation = drawableFactory.makeText(
                "dev:",
                18 + title.getX(),
                168 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(standardDeviation, background);

        quantile90 = drawableFactory.makeText(
                "p90:",
                17 + title.getX(),
                200 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(quantile90, background);

        quantile95 = drawableFactory.makeText(
                "p95:",
                17 + title.getX(),
                232 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(quantile95, background);

        quantile99 = drawableFactory.makeText(
                "p99:",
                17 + title.getX(),
                264 + title.getY(),
                "HBE24",
                "black");
        getScene().addObjectHigherThan(quantile99, background);

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
        total.setText("sum: " + tot);
        maximum.setText("max: " + max);
        average.setText("avg: " + avg);
        median.setText("med: " + med);
        standardDeviation.setText("dev: " + dev);
        quantile90.setText("p90: " + p90);
        quantile95.setText("p95: " + p95);
        quantile99.setText("p99: " + p99);
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
