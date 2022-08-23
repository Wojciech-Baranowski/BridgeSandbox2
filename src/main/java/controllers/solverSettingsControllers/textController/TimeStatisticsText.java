package controllers.solverSettingsControllers.textController;

import engine.display.Drawable;
import engine.display.DrawableFactory;
import engine.display.text.Text;
import solver.multipleGamesSolver.TimeStatistics;

import static engine.scene.SceneBean.getScene;

public class TimeStatisticsText {

    private final Text title;
    private final Text total;
    private final Text maximum;
    private final Text average;
    private final Text median;
    private final Text standardDeviation;
    private final Text quantile90;
    private final Text quantile95;
    private final Text quantile99;

    TimeStatisticsText(DrawableFactory drawableFactory, Drawable background) {
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
    }

    public void update(TimeStatistics timeStatistics) {
        total.setText("sum: " + timeStatistics.getTotalValue());
        maximum.setText("max: " + timeStatistics.getMaximumValue());
        average.setText("avg: " + timeStatistics.getAverageValue());
        median.setText("med: " + timeStatistics.getMedianValue());
        standardDeviation.setText("dev: " + timeStatistics.getStandardDeviationValue());
        quantile90.setText("p90: " + timeStatistics.getQuantile90Value());
        quantile95.setText("p95: " + timeStatistics.getQuantile95Value());
        quantile99.setText("p99: " + timeStatistics.getQuantile99Value());
    }

}
