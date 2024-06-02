package question3;

import java.io.*;
import java.util.Scanner;

public class FitnessProgram {
    private static final String FILE_NAME = "Settings.xml";

    public static void main(String[] args) {
        Settings settings = loadSettings();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. View Settings\n2. Update Settings");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            if (settings == null) {
                System.out.println("No settings found. Please save your settings first.");
                settings = updateSettings(scanner);
                saveSettings(settings);
                System.out.println();
                displaySettings(settings); //Display settings after saving
            } else {
                displaySettings(settings);
            }
        } else if (choice == 2) {
            settings = updateSettings(scanner);
            saveSettings(settings);
            displaySettings(settings); //Display settings after saving
        }

        scanner.close();
    }

    private static Settings loadSettings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Settings) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private static void saveSettings(Settings settings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displaySettings(Settings settings) {
        System.out.printf("Name: %s%n", settings.getName());
        System.out.printf("Height: %.2f cm%n", settings.getHeight());
        System.out.printf("Weight: %.2f kg%n", settings.getWeight());
        System.out.printf("Birthday: %s%n", settings.getBirthday());
        System.out.printf("Functional Threshold Power: %d%n", settings.getFunctionalThresholdPower());
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
        settings.setFunctionalThresholdPower(scanner.nextInt());

        return settings;
    }
}
