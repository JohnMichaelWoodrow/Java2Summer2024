package Exercise4;

public class RectangleTester {
    public static void main(String[] args) {
        try {
            Rectangle rectangle1 = new Rectangle(10, 5);
            System.out.println(rectangle1.getDescription());
            Rectangle rectangle2 = new Rectangle(-10, 5);
        } catch (InvalidShapeParameterException e) {
            System.out.println(e.getMessage());
        }
        try {
            Rectangle rectangle3 = new Rectangle(15, 7);
            rectangle3.setLength(-7);
        } catch (InvalidShapeParameterException e) {
            System.out.println(e.getMessage());
        }
    }
}
