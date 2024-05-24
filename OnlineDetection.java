import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineDetection {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Boolean> employeeStatus = new HashMap<>();

        // Initialize employee status
        employeeStatus.put("Employee1", true);
        employeeStatus.put("Employee2", true);
        employeeStatus.put("Employee3", true);

        // Check employee status
        System.out.println("Employee Status:");
        for (Map.Entry<String, Boolean> entry : employeeStatus.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() ? "Working" : "Not Working"));
        }

        // Update employee status
        System.out.print("Enter employee name to update status: ");
        String employeeName = scanner.nextLine();
        if (employeeStatus.containsKey(employeeName)) {
            System.out.print("Is " + employeeName + " working? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("yes")) {
                employeeStatus.put(employeeName, true);
            } else if (response.equals("no")) {
                employeeStatus.put(employeeName, false);
            } else {
                System.out.println("Invalid response.");
            }
        } else {
            System.out.println("Employee not found.");
        }

        // Updated employee status
        System.out.println("Updated Employee Status:");
        for (Map.Entry<String, Boolean> entry : employeeStatus.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() ? "Working" : "Not Working"));
        }

        scanner.close();
    }
}
