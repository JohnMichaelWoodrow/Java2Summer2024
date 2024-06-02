package question2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PhishingScanner {
    private static final PhishingTerm[] PHISHINGTERMS = {
            new PhishingTerm("account", 3),
            new PhishingTerm("bank", 3),
            new PhishingTerm("verify", 3),
            new PhishingTerm("password", 3),
            new PhishingTerm("social security", 3),
            new PhishingTerm("credit card", 3),
            new PhishingTerm("claim", 3),
            new PhishingTerm("fraud alert", 3),
            new PhishingTerm("security alert", 3),
            new PhishingTerm("security breach", 3),
            new PhishingTerm("login", 2),
            new PhishingTerm("prince", 2),
            new PhishingTerm("suspended", 2),
            new PhishingTerm("unauthorized", 2),
            new PhishingTerm("account update", 2),
            new PhishingTerm("important", 2),
            new PhishingTerm("click here", 2),
            new PhishingTerm("special promotion", 2),
            new PhishingTerm("confirm", 1),
            new PhishingTerm("congratulations", 1),
            new PhishingTerm("limited time", 1),
            new PhishingTerm("offer", 1),
            new PhishingTerm("free", 1),
            new PhishingTerm("win", 1),
            new PhishingTerm("prize", 1),
            new PhishingTerm("money", 1),
            new PhishingTerm("deal", 1),
            new PhishingTerm("urgent", 1)
    };

    public static void main(String[] args) {
        String[] fileNames = {"PhishingEmailExample.txt", "PhishingEmailExample2.txt"};
        for (String fileName : fileNames) {
            Path filePath = Paths.get(fileName).toAbsolutePath();
            System.out.printf("Scanning file: %s%n", filePath);
            scanFile(filePath.toString());
        }
    }

    private static void scanFile(String fileName) {
        int totalPoints = 0;
        StringBuilder output = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (PhishingTerm term : PHISHINGTERMS) {
                    String phishingTerm = term.getTerm().toLowerCase();
                    int points = term.getPoints();

                    if (line.toLowerCase().contains(phishingTerm)) {
                        int occurrences = (line.length() - line.toLowerCase().replace(phishingTerm, "").length()) / phishingTerm.length();
                        totalPoints += occurrences * points;
                        String result = String.format("Found term: %s, Occurrences: %d, Points: %d%n", phishingTerm, occurrences, occurrences * points);
                        System.out.print(result);
                        output.append(result);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String totalPointsResult = String.format("Total Phishing Points in %s: %d%n", fileName, totalPoints);
        System.out.print(totalPointsResult);
        output.append(totalPointsResult);

        writeOutputToFile(fileName, output.toString());
    }

    private static void writeOutputToFile(String fileName, String content) {
        String outputFileName = fileName.replace(".txt", "output.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}