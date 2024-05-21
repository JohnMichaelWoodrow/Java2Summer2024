package Exercise4;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[5];

        int num = 0;
        for (int i = 0; i < shapes.length; i++) {
            Shape shape = ShapeGenerator.generateShape();
            if (shape != null) {
                shapes[num] = shape;
                num++;
                System.out.println(shape.getDescription());
            }
        }
    }
}