package controllers.main.assets;

public class NumberRounder {

    public static Double round(Double number, int decimalPlaces) {
        Double roundingScale = Math.pow(10, decimalPlaces);
        return Math.round(number * roundingScale) / roundingScale;
    }
}
