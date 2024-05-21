package Exercise8;

public class WeightConverterTester {
    public static void main(String[] args) {
        System.out.println(WeightConverter.poundsToKilos(10));
        System.out.println(WeightConverter.kilosToPounds(10));
        try {
            System.out.println(WeightConverter.poundsToKilos(-10));
        } catch (AssertionError e) {
            System.out.println("Assertion caught: " + e.getMessage());
        }
        try {
            System.out.println(WeightConverter.kilosToPounds(-10));
        } catch (AssertionError e) {
            System.out.println("Assertion caught: " + e.getMessage());
        }
    }
}
