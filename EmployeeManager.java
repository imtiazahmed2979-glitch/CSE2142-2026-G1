
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Validate the number of command-line arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            return;
        }

        // Validate the given command
        if (!isValidCommand(args[0])) {
            System.out.println("Invalid argument!");
            return;
        }

        // Load and display all employees
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeData();

                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }

            } catch (Exception exception) {
                System.out.println("Error reading employee data!");
            }

            System.out.println("Data Loaded.");

        // Select and display a random employee
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeData();

                System.out.println(String.join(",", employeeNames));

                Random randomGenerator = new Random();

                System.out.println(
                    employeeNames[
                        randomGenerator.nextInt(employeeNames.length)
                    ]
                );

            } catch (Exception exception) {
                System.out.println("Error reading employee data!");
            }

            System.out.println("Data Loaded.");

        // Add a new employee to the file
        } else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");

            try {
                String newEmployeeName = args[0].substring(1);

                writeEmployeeData(", " + newEmployeeName, true);

            } catch (Exception exception) {
                System.out.println("Error adding employee!");
            }

            System.out.println("Data Loaded.");

        // Search for an employee by name
        } else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");

            try {
                String searchEmployeeName = args[0].substring(1);

                if (isEmployeePresent(searchEmployeeName)) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }

            } catch (Exception exception) {
                System.out.println("Error searching employee!");
            }

            System.out.println("Data Loaded.");

        // Count the words in the employee data
        } else if (args[0].equals("c")) {
            System.out.println("Loading data ...");

            try {
                String employeeData = readFileContent();

                int totalWordCount =
                    employeeData.trim().split("\\s+").length;

                System.out.println(
                    totalWordCount + " word(s) found "
                    + employeeData.length()
                );

            } catch (Exception exception) {
                System.out.println("Error counting words!");
            }

            System.out.println("Data Loaded.");

        // Update an employee's name
        } else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");

            try {
                String[] employeeNames = readEmployeeData();
                String employeeNameToUpdate = args[0].substring(1);

                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(employeeNameToUpdate)) {
                        employeeNames[index] = "Updated";
                    }
                }

                writeEmployeeData(
                    String.join(",", employeeNames), false
                );

            } catch (Exception exception) {
                System.out.println("Error updating employee!");
            }

            System.out.println("Data Updated.");

        // Delete an employee by name
        } else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");

            try {
                List<String> employeeList = new ArrayList<>(
                    Arrays.asList(readEmployeeData())
                );

                String employeeNameToDelete = args[0].substring(1);

                employeeList.remove(employeeNameToDelete);

                writeEmployeeData(
                    String.join(",", employeeList), false
                );

            } catch (Exception exception) {
                System.out.println("Error deleting employee!");
            }

            System.out.println("Data Deleted.");
        }
    }

    // Check whether the command-line argument is valid
    public static boolean isValidCommand(String command) {

        if (command.equals("l")
                || command.equals("s")
                || command.equals("c")) {
            return true;
        }

        if (command.startsWith("+")
                || command.startsWith("?")
                || command.startsWith("u")
                || command.startsWith("d")) {

            return command.length() > 1;
        }

        return false;
    }

    // Search for an employee and return the search result
    public static boolean isEmployeePresent(String employeeName)
            throws IOException {

        for (String existingEmployee : readEmployeeData()) {
            if (existingEmployee.equals(employeeName)) {
                return true;
            }
        }

        return false;
    }

    // Read employee names from the data file
    public static String[] readEmployeeData() throws IOException {
        return readFileContent().split(",");
    }

    // Read the complete first line of the data file
    public static String readFileContent() throws IOException {

        BufferedReader fileReader = new BufferedReader(
            new FileReader(Constants.FILE_NAME)
        );

        String fileContent = fileReader.readLine();
        fileReader.close();

        return fileContent;
    }

    // Write employee data to the file
    public static void writeEmployeeData(
            String employeeData, boolean appendData)
            throws IOException {

        BufferedWriter fileWriter = new BufferedWriter(
            new FileWriter(Constants.FILE_NAME, appendData)
        );

        fileWriter.write(employeeData);
        fileWriter.close();
    }
}
