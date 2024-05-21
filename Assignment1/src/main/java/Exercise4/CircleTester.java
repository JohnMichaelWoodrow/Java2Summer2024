package Exercise4;

public class CircleTester {
    public static void main(String[] args) {
        try {
            Circle circle1 = new Circle(10);
            System.out.println(circle1.getDescription());
            Circle circle2 = new Circle(-5);
        } catch (InvalidShapeParameterException e) {
            System.out.println(e.getMessage());
        }
        try {
            Circle circle3 = new Circle(15);
            circle3.setRadius(-7);
        } catch (InvalidShapeParameterException e) {
            System.out.println(e.getMessage());
        }
    }
}

