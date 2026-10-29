
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Method to read data from file
    public static String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(
            new FileReader("employees.txt")
        );

        String line = reader.readLine();
        reader.close();

        return line;
    }

    // Method to write data into file
    public static void writeFile(String content, boolean append)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(
            new FileWriter("employees.txt", append)
        );

        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {

        // Check arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String line = readFile();
                String[] employees = line.split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

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
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                String name = args[0].substring(1);
                writeFile(", " + name, true);

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String line = readFile();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);

                for (int index = 0;
                     index < employees.length && !found;
                     index++) {

                    if (employees[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

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
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                String line = readFile();
                String[] employees = line.split(",");
                String name = args[0].substring(1);

                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(name)) {
                        employees[index] = "Updated";
                    }
                }

                writeFile(String.join(",", employees), false);

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

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
            }

            System.out.println("Data Deleted.");
        }
    }
}
