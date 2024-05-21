package Exercise4;

import java.util.Random;

public class ShapeGenerator {
    public static Shape generateShape() {
        Random random = new Random();
        while (true) {
            double radius = random.nextInt(100) - 50;
            double length = random.nextInt(100) - 50;
            double width = random.nextInt(100) - 50;
            int coinTossResult = random.nextInt(2);
            try {
                if (coinTossResult == 0) {
                    return new Circle(radius);
                } else {
                    return new Rectangle(length, width);
                }
            } catch (InvalidShapeParameterException e) {
                if (coinTossResult == 0) {
                    System.err.println("Caught exception: " + e.getMessage() + " (Circle radius: " + radius + ")");
                } else {
                    System.err.println("Caught exception: " + e.getMessage() + " (Rectangle length: " + length + ", width: " + width + ")");
                }
            }
        }
    }
}
