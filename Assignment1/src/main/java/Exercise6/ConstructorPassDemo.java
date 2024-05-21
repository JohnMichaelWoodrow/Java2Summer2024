package Exercise6;

public class ConstructorPassDemo {
    public static void main(String[] args) {
        try {
            ConstructorPass ConstructorPass = new ConstructorPass();
        } catch (Exception e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}