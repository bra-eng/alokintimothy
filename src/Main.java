import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String STUDENT_NAME = "ALOIKIN TIMOTHY";
    private static final String REGISTRATION = "VU-BIT-2511-1049-EVE";
    private static final String ID_PREFIX = "AT049-";

    private final JobManager manager = new JobManager();
    private final Scanner scanner = new Scanner(System.in);
    private int nextNumber = 1;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("==============================================");
        System.out.println("TAILORING SHOP CHARGING SYSTEM");
        System.out.println("Student: " + STUDENT_NAME);
        System.out.println("Registration: " + REGISTRATION);
        System.out.println("Discount: 9% for loyal customers");
        System.out.println("Report: customer name, alphabetically");
        System.out.println("==============================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = InputHelper.readMenuChoice(scanner, 1, 7);
            try {
                switch (choice) {
                    case 1 -> addRepairJob();
                    case 2 -> addNewGarmentJob();
                    case 3 -> listReport();
                    case 4 -> findJob();
                    case 5 -> removeJob();
                    case 6 -> showSummary();
                    case 7 -> running = false;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InvalidJobException | JobNotFoundException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
            System.out.println();
        }
        System.out.println("Thank you. Program closed.");
        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n--------------- MAIN MENU ----------------");
        System.out.println("1. Add Repair Job");
        System.out.println("2. Add New Garment Job");
        System.out.println("3. List Sorted Report");
        System.out.println("4. Find Job");
        System.out.println("5. Remove Job");
        System.out.println("6. Show Summary");
        System.out.println("7. Exit");
    }

    private String generateId() {
        return String.format("%s%03d", ID_PREFIX, nextNumber++);
    }

    private void addRepairJob() {
        String name = InputHelper.readNonBlank(scanner, "Customer name: ");
        int items = InputHelper.readPositiveInt(scanner, "Number of repair items: ");
        boolean loyal = InputHelper.readYesNo(scanner, "Is the customer loyal? (Y/N): ");

        RepairJob job = new RepairJob(generateId(), name, loyal, items);
        manager.addJob(job);
        System.out.println("Repair job added successfully: " + job.getId());
        System.out.printf("Charge: UGX %,.2f%n", job.calculateCharge());
    }

    private void addNewGarmentJob() {
        String name = InputHelper.readNonBlank(scanner, "Customer name: ");
        double fabric = InputHelper.readNonNegativeDouble(scanner, "Fabric cost (UGX): ");
        boolean loyal = InputHelper.readYesNo(scanner, "Is the customer loyal? (Y/N): ");

        NewGarmentJob job = new NewGarmentJob(generateId(), name, loyal, fabric);
        manager.addJob(job);
        System.out.println("New garment job added successfully: " + job.getId());
        System.out.printf("Charge: UGX %,.2f%n", job.calculateCharge());
    }

    private void listReport() {
        if (manager.isEmpty()) {
            System.out.println("No jobs have been recorded.");
            return;
        }

        System.out.println("\n================ SORTED REPORT ================");
        List<Job> jobs = manager.getSortedJobs();
        for (Chargeable record : jobs) {
            Job job = (Job) record;
            System.out.printf(
                "%s | %-15s | %-16s | loyal=%-5s | charge=UGX %,.2f%n",
                job.getId(), job.getCustomerName(), job.getJobType(),
                job.isLoyal(), record.calculateCharge()
            );
        }
        System.out.println("================================================");
    }

    private void findJob() {
        String id = InputHelper.readNonBlank(scanner, "Enter job ID: ");
        Job job = manager.findJob(id);
        System.out.println("Job found:");
        System.out.println(job);
        System.out.printf("Discount given: UGX %,.2f%n", job.getDiscountAmount());
    }

    private void removeJob() {
        String id = InputHelper.readNonBlank(scanner, "Enter job ID to remove: ");
        Job job = manager.findJob(id);
        System.out.println("Selected: " + job);
        boolean confirm = InputHelper.readYesNo(scanner, "Confirm removal? (Y/N): ");
        if (confirm) {
            manager.removeJob(id);
            System.out.println("Job removed successfully.");
        } else {
            System.out.println("Removal cancelled.");
        }
    }

    private void showSummary() {
        System.out.println("\n================ SUMMARY ================");
        System.out.println("Number of jobs: " + manager.size());
        System.out.printf("Total charges: UGX %,.2f%n", manager.getTotalCharges());
        System.out.printf("Total discount given: UGX %,.2f%n", manager.getTotalDiscount());
        System.out.println("=========================================");
    }
}
