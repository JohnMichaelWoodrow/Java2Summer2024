package src.main.java.question2;

import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;

public class wordsReversed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Sentence: ");
        String text = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        String[] textArray = text.split(" ");

        for (int i = textArray.length - 1; i >= 0; i--) {
            sb.append(textArray[i]).append(" ");
        }
        System.out.println(sb);
    }
}
