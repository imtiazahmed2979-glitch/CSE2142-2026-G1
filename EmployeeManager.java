
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String line = reader.readLine();
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
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String line = reader.readLine();
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
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", true)
                );

                String name = args[0].substring(1);
                writer.write(", " + name);
                writer.close();

            } catch (Exception exception) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);

                for (int index = 0; index < employees.length && !found; index++) {
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
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String line = reader.readLine();
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
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String line = reader.readLine();
                String[] employees = line.split(",");
                String name = args[0].substring(1);

                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(name)) {
                        employees[index] = "Updated";
                    }
                }

                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt")
                );

                writer.write(String.join(",", employees));
                writer.close();

            } catch (Exception exception) {
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );

                String line = reader.readLine();
                String[] employees = line.split(",");
                String name = args[0].substring(1);

                List<String> employeeList =
                    new ArrayList<>(Arrays.asList(employees));

                employeeList.remove(name);

                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt")
                );

                writer.write(String.join(",", employeeList));
                writer.close();

            } catch (Exception exception) {
            }

            System.out.println("Data Deleted.");
        }
    }
}
