package Question4;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTester {
    public static void main(String[] args) {
        LinkedList<Character> characters = new LinkedList<Character>();
        characters.add('a');
        characters.add('b');
        characters.add('c');
        characters.add('d');
        characters.add('e');
        characters.add('f');
        characters.add('g');
        characters.add('h');
        characters.add('i');
        characters.add('j');

        System.out.println("Original List: ");
        for (Character s : characters) {
            System.out.print(s + " ");
        }

        System.out.println();
        System.out.println();

        LinkedList<Character> reversedList = new LinkedList<>();
        ListIterator<Character> iterator = characters.listIterator(characters.size());
        while (iterator.hasPrevious()) {
            reversedList.add(iterator.previous());
        }
        System.out.println("Reversed List: ");
        for (Character s : reversedList) {
            System.out.print(s + " ");
        }
    }
}
