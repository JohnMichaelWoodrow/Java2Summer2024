package question3;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class FitnessProgram {
    private static final String FILENAME = "Settings.xml";

    public static void main(String[] args) {
        Settings settings = loadSettings();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View Settings");
        System.out.println("2. Update Settings");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            if (settings == null) {
                System.out.println("No settings found. Update settings first.");
                settings = updateSettings(scanner);
                saveSettings(settings);
                System.out.println();
                displaySettings(settings);
            } else {
                displaySettings(settings);
            }
        } else if (choice == 2) {
            settings = updateSettings(scanner);
            saveSettings(settings);
            displaySettings(settings);
        }
        scanner.close();
    }

    private static Settings loadSettings() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILENAME))) {
                return JAXB.unmarshal(reader, Settings.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void saveSettings(Settings settings) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILENAME), StandardOpenOption.CREATE)) {
            JAXB.marshal(settings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displaySettings(Settings settings) {
        System.out.printf("Name: %s%n", settings.getName());
        System.out.printf("Height: %.2f cm%n", settings.getHeight());
        System.out.printf("Weight: %.2f kg%n", settings.getWeight());
        System.out.printf("Birthday: %s%n", settings.getBirthday());
        System.out.printf("Functional Threshold Power: %.2f %n", settings.getFunctionalThresholdPower());
    }

    private static Settings updateSettings(Scanner scanner) {
        Settings settings = new Settings();
        System.out.print("Enter Name: ");
        settings.setName(scanner.nextLine());
        System.out.print("Enter Height (in cm): ");
        settings.setHeight(scanner.nextDouble());
        System.out.print("Enter Weight (in kg): ");
        settings.setWeight(scanner.nextDouble());
        scanner.nextLine();
        System.out.print("Enter Birthday (YYYY-MM-DD): ");
        settings.setBirthday(scanner.nextLine());
        System.out.print("Enter Functional Threshold Power: ");
        settings.setFunctionalThresholdPower(scanner.nextDouble());
        return settings;
    }
}

