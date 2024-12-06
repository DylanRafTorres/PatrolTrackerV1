import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time;

public class PatrolTimeLog {
    private static ArrayList<PatrolLog> patrolLogs = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Patrol Time Log System ---");
            System.out.println("1. Start Patrol");
            System.out.println("2. End Patrol");
            System.out.println("3. View Patrol Logs");
            System.out.println("4. Export Logs to File");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    startPatrol();
                    break;
                case 2:
                     endPatrol();
                     break;
                case 3:
                     viewLogs();
                     break;
                case 4:
                     exportLogsToFile();
                     break;
                case 5:
                     System.out.println("Exiting program. Stay safe!");
                     break;

                default:
                     System.out.println("Invalid choice. Please try again.");
                     break;
            }
        } while (choice != 5);
    }

    private static void startPatrol() {
        System.out.print("Enter officer's name: ");
        String officerName = scanner.nextLine();
        LocalDateTime startTime = LocalDateTime.now();
        PatrolLog newLog = new PatrolLog(officerName, startTime);
        patrolLogs.add(newLog);
        System.out.println("Patrol started for Officer " + officerName + " at " + startTime);
    }

    private static void endPatrol() {
        if (patrolLogs.isEmpty()) { //In put validation 
            System.out.println("No patrols currently active.");
            return;
        }
        System.out.println("Active patrols:");
        for (int i = 0; i < patrolLogs.size(); i++) {
            if (patrolLogs.get(i).toString().contains("Still on patrol")) {
                System.out.println((i + 1) + ". " + patrolLogs.get(i));
            }
        }
        System.out.print("Select patrol to end (number): ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (index >= 0 && index < patrolLogs.size() && patrolLogs.get(index).toString().contains("Still on patrol")) {
            LocalDateTime endTime = LocalDateTime.now();
            patrolLogs.get(index).setEndTime(endTime);
            System.out.println("Patrol ended for " + patrolLogs.get(index));
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void viewLogs() {
        if (patrolLogs.isEmpty()) {
            System.out.println("No logs available.");
        } else {
            System.out.println("\n--- Patrol Logs ---");
            for (PatrolLog log : patrolLogs) {
                System.out.println(log);
            }
        }
    }

    private static void exportLogsToFile() {
        if (patrolLogs.isEmpty()) {
            System.out.println("No logs available to export.");
            return;
        }
        System.out.print("Enter file name to export logs (e.g., patrol_logs.txt): ");
        String fileName = scanner.nextLine();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (PatrolLog log : patrolLogs) {
                writer.write(log.toString() + "\n");
            }
            System.out.println("Logs exported successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting logs: " + e.getMessage());
        }
    }
}
    

