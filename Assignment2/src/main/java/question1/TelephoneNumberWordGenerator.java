package question1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class TelephoneNumberWordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String phoneNumber = "";
        while (true) {
            System.out.print("Enter a phone number (2-9 only without spaces or special characters): ");
            phoneNumber = scanner.nextLine();
            try {
                validatePhoneNumber(phoneNumber);
                break;
            } catch (InvalidPhoneNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        generateWords(phoneNumber, "output.txt");
    }

    private static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (!phoneNumber.matches("[2-9]+")) {
            throw new InvalidPhoneNumberException("Invalid input. Enter a phone number (2-9 only without spaces or special characters): ");
        }
    }

    private static void generateWords(String phoneNumber, String fileName) {
        try (Formatter formatter = new Formatter(new FileWriter(fileName))) {
            generateWordsRecursive(phoneNumber, 0, new char[phoneNumber.length()], formatter);
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
        char[] letters = TelephoneNumberWord.getLettersForDigit(digit);
        if (letters.length > 0) {
            for (char letter : letters) {
                currentWord[index] = letter;
                generateWordsRecursive(phoneNumber, index + 1, currentWord, formatter);
            }
        }
    }

    static class InvalidPhoneNumberException extends Exception {
        public InvalidPhoneNumberException(String message) {
            super(message);
        }
    }
}

