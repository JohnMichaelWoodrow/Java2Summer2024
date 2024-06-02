package question1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class TelephoneNumberWordGenerator {
    public static void main(String[] args) {
        String phoneNumber = "7382273";
        generateWords(phoneNumber, "output.txt");
    }
    private static void generateWords(String phoneNumber, String fileName) {
        try (Formatter formatter = new Formatter(new FileWriter(fileName))) {
            generateWordsRecursive(phoneNumber, 0, new char[7], formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void generateWordsRecursive(String phoneNumber, int index, char[] currentWord, Formatter formatter) {
        if (index == phoneNumber.length()) {
            formatter.format("%s%n", new String(currentWord));
            return;
        }

        char digit = phoneNumber.charAt(index);
        char[] letters = TelephoneNumberWord.Digit.getLettersForDigit(digit);
        if (letters.length > 0) {
            for (char letter : letters) {
                currentWord[index] = letter;
                generateWordsRecursive(phoneNumber, index + 1, currentWord, formatter);
            }
        }
    }
}
