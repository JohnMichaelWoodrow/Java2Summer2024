package Exercise8;

public class WeightConverter {
    public static double poundsToKilos(double pounds) {
        assert (pounds > 0) : "Weight must be greater than 0";
        return pounds * 0.45359237;
    }

    public static double kilosToPounds(double kilos) {
        assert (kilos > 0) : "Weight must be greater than 0";
        return kilos * 2.20462262;
    }
}