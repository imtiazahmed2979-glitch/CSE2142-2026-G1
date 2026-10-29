
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
                String line = readFile();
                String[] employees = line.split(",");

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
                String line = readFile();
                System.out.println(line);

                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);

                System.out.println(employees[randomIndex]);

            } catch (Exception exception) {
                System.out.println("Error reading data!");
            }

            System.out.println("Data Loaded.");

        // Add a new employee
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                String name = args[0].substring(1);
                writeFile(", " + name, true);

            } catch (Exception exception) {
                System.out.println("Error writing data!");
            }

            System.out.println("Data Loaded.");

        // Search for an employee
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String line = readFile();
                String[] employees = line.split(",");
                String searchName = args[0].substring(1);
                boolean found = false;

                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

            } catch (Exception exception) {
                System.out.println("Error reading data!");
            }

            System.out.println("Data Loaded.");

        // Count words
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");

            try {
                String line = readFile();
                char[] characters = line.toCharArray();

                boolean inWord = false;
                int count = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(
                    count + " word(s) found " + characters.length
                );

            } catch (Exception exception) {
                System.out.println("Error reading data!");
            }

            System.out.println("Data Loaded.");

        // Update an employee
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                String line = readFile();
                String[] employees = line.split(",");
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
                String line = readFile();
                String[] employees = line.split(",");
                String name = args[0].substring(1);

                List<String> employeeList =
                    new ArrayList<>(Arrays.asList(employees));

                employeeList.remove(name);

                writeFile(String.join(",", employeeList), false);

            } catch (Exception exception) {
                System.out.println("Error deleting data!");
            }

            System.out.println("Data Deleted.");
        }
    }

    // Read data from file
    public static String readFile() throws IOException {

        BufferedReader reader = new BufferedReader(
            new FileReader(Constants.FILE_NAME)
        );

        String line = reader.readLine();
        reader.close();

        return line;
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
