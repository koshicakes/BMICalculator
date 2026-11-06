import java.util.InputMismatchException;
import java.util.Scanner;

public class BMICalculator {

    private static final double LBS_TO_KG = 0.45359237;
    private static final double INCHES_TO_M = 0.0254;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean again = true;

        System.out.println("=== BMI Calculator ===");

        while (again) {
            int choice = readChoice(scanner);
            double weightKg;
            double heightM;

            if (choice == 1) {
                double kg = readPositive(scanner, "Enter weight (kg): ");
                double cm = readPositive(scanner, "Enter height (cm): ");
                weightKg = kg;
                heightM = cm / 100.0;
            } else {
                double lbs = readPositive(scanner, "Enter weight (lbs): ");
                double inches = readPositive(scanner, "Enter height (inches): ");
                weightKg = lbs * LBS_TO_KG;
                heightM = inches * INCHES_TO_M;
            }

            double bmi = calculateBMI(weightKg, heightM);
            System.out.printf("%nYour BMI: %.1f%n", bmi);
            System.out.println("Category: " + getCategory(bmi));

            System.out.print("\nCalculate another? (y/n): ");
            again = scanner.next().trim().equalsIgnoreCase("y");
            System.out.println();
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    public static double calculateBMI(double weightKg, double heightM) {
        return weightKg / (heightM * heightM);
    }

    public static String getCategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25.0) return "Normal weight";
        if (bmi < 30.0) return "Overweight";
        return "Obese";
    }

    private static int readChoice(Scanner scanner) {
        while (true) {
            System.out.println("\nChoose units:");
            System.out.println("  1) Metric (kg, cm)");
            System.out.println("  2) Imperial (lbs, inches)");
            System.out.print("Selection: ");
            try {
                int choice = scanner.nextInt();
                if (choice == 1 || choice == 2) return choice;
            } catch (InputMismatchException e) {
                scanner.next(); // discard bad input
            }
            System.out.println("Please enter 1 or 2.");
        }
    }

    private static double readPositive(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                if (value > 0) return value;
            } catch (InputMismatchException e) {
                scanner.next(); // discard bad input
            }
            System.out.println("Please enter a positive number.");
        }
    }
}