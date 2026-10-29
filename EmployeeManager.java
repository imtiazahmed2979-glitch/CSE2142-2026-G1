
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check the number of arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            return;
        }

        // Load all employees
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readFile().split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
                System.out.println("Error reading data!");
            }

            System.out.println("Data Loaded.");

        // Select a random employee
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readFile().split(",");

                System.out.println(String.join(",", employees));

                Random random = new Random();

                System.out.println(
                    employees[random.nextInt(employees.length)]
                );

            } catch (Exception exception) {
                System.out.println("Error reading data!");
            }

            System.out.println("Data Loaded.");

        // Add a new employee
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                writeFile(", " + args[0].substring(1), true);

            } catch (Exception exception) {
                System.out.println("Error writing data!");
            }

            System.out.println("Data Loaded.");

        // Search for an employee
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String searchName = args[0].substring(1);

                if (searchEmployee(searchName)) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }

            } catch (Exception exception) {
                System.out.println("Error reading data!");
            }

            System.out.println("Data Loaded.");

        // Count words
        } else if (args[0].equals("c")) {
            System.out.println("Loading data ...");

            try {
                String content = readFile();

                int wordCount = content.trim().split("\\s+").length;

                System.out.println(
                    wordCount + " word(s) found " + content.length()
                );

            } catch (Exception exception) {
                System.out.println("Error reading data!");
            }

            System.out.println("Data Loaded.");

        // Update an employee
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readFile().split(",");
                String name = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) {
                        employees[i] = "Updated";
                    }
                }

                writeFile(String.join(",", employees), false);

            } catch (Exception exception) {
                System.out.println("Error updating data!");
            }

            System.out.println("Data Updated.");

        // Delete an employee
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");

            try {
                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(readFile().split(","))
                );

                employeeList.remove(args[0].substring(1));

                writeFile(String.join(",", employeeList), false);

            } catch (Exception exception) {
                System.out.println("Error deleting data!");
            }

            System.out.println("Data Deleted.");
        }
    }

    // Search for an employee
    public static boolean searchEmployee(String searchName)
            throws IOException {

        for (String employee : readFile().split(",")) {
            if (employee.equals(searchName)) {
                return true;
            }
        }

        return false;
    }

    // Read data from file
    public static String readFile() throws IOException {

        BufferedReader reader = new BufferedReader(
            new FileReader(Constants.FILE_NAME)
        );

        String content = reader.readLine();
        reader.close();

        return content;
    }

    // Write data to file
    public static void writeFile(String content, boolean append)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(Constants.FILE_NAME, append)
        );

        writer.write(content);
        writer.close();
    }
}
