package src.main.java.question4;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Redactor {
    public static void main(String[] args) {
        String inputFileName = "src/main/java/question4/sampleInfo.txt";
        String outputFileName = "src/main/java/question4/sampleInfoRedacted.txt";
        redactFile(inputFileName, outputFileName);
    }

    private static void redactFile(String inputFileName, String outputFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(redactLine(line)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String redactLine(String line) {
        // Dates
        line = line.replaceAll("\\d{2}-\\d{2}-\\d{2}(\\d{2,4})?", "##-##-####");
        // Credit Card Numbers
        line = line.replaceAll("\\d{4}-\\d{4}-\\d{4}-\\d{4}", "####-####-####-####");
        // Currency
        line = line.replaceAll("\\$(\\d+(\\.\\d{2})?)", "\\$##.##");
        // Security Codes
        line = line.replaceAll("CODE\\w{17}", "CODE################");

        return line;
    }
}
