package Exercise5;

public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            //throw new ExceptionA("ExceptionA Says Hi");
            throw new ExceptionB("ExceptionB Says Hi");
            //throw new ExceptionC("ExceptionC Says Hi");
        } catch (ExceptionA e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
