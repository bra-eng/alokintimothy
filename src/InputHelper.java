import java.util.Scanner;

public final class InputHelper {
    private InputHelper() {}

    public static String readNonBlank(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Invalid input: value cannot be blank. Please try again.");
        }
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number > 0) return number;
                System.out.println("Invalid input: enter a number greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: enter a whole number.");
            }
        }
    }

    public static double readNonNegativeDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                double number = Double.parseDouble(value);
                if (!Double.isNaN(number) && !Double.isInfinite(number) && number >= 0) {
                    return number;
                }
                System.out.println("Invalid input: enter zero or a positive amount.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: enter a valid amount.");
            }
        }
    }

    public static boolean readYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().toLowerCase();
            if (value.equals("y") || value.equals("yes")) return true;
            if (value.equals("n") || value.equals("no")) return false;
            System.out.println("Invalid input: enter Y/Yes or N/No.");
        }
    }

    public static int readMenuChoice(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("Choose an option (" + min + "-" + max + "): ");
            String value = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(value);
                if (choice >= min && choice <= max) return choice;
                System.out.println("Invalid choice: select a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice: enter a whole number.");
            }
        }
    }
}
