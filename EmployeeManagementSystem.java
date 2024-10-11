package EmployeeSystem;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Employe
{
    int id;
    String name;
    int age;
    String department;
    double salary;

    Employe(int id, String name, int age, String department, double salary)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment()
    {
        return department;
    }

    public int getAge()
    {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString()
    {
        return "Employee ID: " + id + "\nName: " + name + "\nAge: " + age +
                "\nDepartment: " + department + "\nSalary: $" + String.format("%.2f", salary) + "\n";
    }
}

public class EmployeeManagementSystem
{
    private static final List<Employe> employeeList = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do
        {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice)
            {
                case 1: addEmployee(scanner); break;
                case 2: displayEmployees(); break;
                case 3: updateEmployee(scanner); break;
                case 4: deleteEmployee(scanner); break;
                case 5: searchEmployeeByName(scanner); break;
                case 6: sortEmployeesByName(); break;
                case 7: sortEmployeesBySalary(); break;
                case 8: exportEmployeeData(); break;
                case 9: importEmployeeData(); break;
                case 10: promoteEmployee(scanner); break;
                case 11: filterEmployeesByDepartment(scanner); break;
                case 12: increaseSalaryByPercentage(scanner); break;
                case 13: viewHighestSalary(); break;
                case 14: calculateAverageSalary(); break;
                case 15: findEmployeeById(scanner); break;
                case 16: printEmployeeStatistics(); break;
                case 17: printDepartmentWiseEmployeeCount(); break;
                case 18: printAgeWiseEmployeeCount(); break;
                case 19: printEmployeeNameInReverseOrder(); break;
                case 20: printEmployeeNameInUpperCase(); break;
                case 21: printEmployeeNameInLowerCase(); break;
                case 22: printEmployeeNameWithFirstLetterUpperCase(); break;
                case 23: printEmployeeNameWithFirstLetterLowerCase(); break;
                case 24: System.out.println("Exiting the program."); break;
                default: System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 24);

        scanner.close();
    }

    private static void printMenu()
    {
        System.out.println("\n===== Employee Management System =====");
        System.out.println("1. Add Employee");
        System.out.println("2. Display All Employees");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Search Employee by Name");
        System.out.println("6. Sort Employees by Name");
        System.out.println("7. Sort Employees by Salary");
        System.out.println("8. Export Employee Data");
        System.out.println("9. Import Employee Data");
        System.out.println("10. Promote Employee");
        System.out.println("11. Filter Employees by Department");
        System.out.println("12. Increase Salary by Percentage");
        System.out.println("13. View Employee with Highest Salary");
        System.out.println("14. Calculate Average Salary");
        System.out.println("15. Find Employee by ID");
        System.out.println("16. Print Employee Statistics");
        System.out.println("17. Print Department Wise Employee Count");
        System.out.println("18. Print Age Wise Employee Count");
        System.out.println("19. Print Employee Name in Reverse Order");
        System.out.println("20. Print Employee Name in Upper Case");
        System.out.println("21. Print Employee Name in Lower Case");
        System.out.println("22. Print Employee Name with First Letter Upper Case");
        System.out.println("23. Print Employee Name with First Letter Lower Case");
        System.out.println("24. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployee(Scanner scanner)
    {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        Employe newEmployee = new Employe(nextId++, name, age, department, salary);
        employeeList.add(newEmployee );
        System.out.println("Employee added successfully!");
    }

    private static void displayEmployees()
    {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employeeList.forEach(System.out::println);
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employe employee = findEmployeeById(id);
        if (employee != null) {
            System.out.println("1. Update Name\n2. Update Age\n3. Update Department\n4. Update Salary");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice)
            {
                case 1 -> {
                    System.out.print("Enter new name: ");
                    employee.name = scanner.nextLine();
                }
                case 2 -> {
                    System.out.print("Enter new age: ");
                    employee.age = scanner.nextInt();
                }
                case 3 -> {
                    System.out.print("Enter new department: ");
                    employee.department = scanner.nextLine();
                }
                case 4 -> {
                    System.out.print("Enter new salary: ");
                    employee.salary = scanner.nextDouble();
                }
                default -> System.out.println("Invalid choice!");
            }
            System.out.println("Employee updated successfully!");
        } else
        {
            System.out.println("Employee not found!");
        }
    }

    private static void deleteEmployee(Scanner scanner)
    {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();

        Employe employee = findEmployeeById(id);
        if (employee != null) {
            employeeList.remove(employee);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void searchEmployeeByName(Scanner scanner)
    {
        System.out.print("Enter Employee Name to search: ");
        String name = scanner.nextLine();

        employeeList.stream()
                .filter(e -> e.name.equalsIgnoreCase(name))
                .forEach(System.out::println);
    }

    private static void sortEmployeesByName()
    {
        employeeList.sort(Comparator.comparing(e -> e.name.toLowerCase()));
        System.out.println("Employees sorted by name.");
        displayEmployees();
    }

    private static void sortEmployeesBySalary()
    {
        employeeList.sort(Comparator.comparingDouble(e -> e.salary));
        System.out.println("Employees sorted by salary.");
        displayEmployees();
    }

    private static void promoteEmployee(Scanner scanner)
    {
        System.out.print("Enter Employee ID to promote: ");
        int id = scanner.nextInt();

        Employe employee = findEmployeeById(id);
        if (employee != null) {
            System.out.println("Promoting " + employee.name + "...");
            // Promotion logic can be added here
            System.out.println(employee.name + " has been promoted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void exportEmployeeData()
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("employees.txt"))) {
            for (Employe employee : employeeList) {
                writer.println(employee.id + " " + employee.name + " " + employee.age + " " + employee.department + " " + employee.salary);
            }
            System.out.println("Employee data exported successfully!");
        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }

    private static void importEmployeeData()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            employeeList.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String department = data[3];
                double salary = Double.parseDouble(data[4]);

                employeeList.add(new Employe(id, name, age, department, salary));
            }
            System.out.println("Employee data imported successfully!");
        } catch (IOException e) {
            System.out.println("Error importing data: " + e.getMessage());
        }
    }

    private static void filterEmployeesByDepartment(Scanner scanner)
    {
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        employeeList.stream()
                .filter(e -> e.department.equalsIgnoreCase(department))
                .forEach(System.out::println);
    }

    private static void increaseSalaryByPercentage(Scanner scanner)
    {
        System.out.print("Enter percentage to increase salary: ");
        double percentage = scanner.nextDouble();

        for (Employe employee : employeeList)
        {
            employee.salary += employee.salary * (percentage / 100);
        }
        System.out.println("Salaries increased by " + percentage + "%");
    }

    private static void viewHighestSalary()
    {
        Employe highestSalaryEmployee = Collections.max(employeeList, Comparator.comparingDouble(e -> e.salary));
        System.out.println("Employee with the highest salary:\n" + highestSalaryEmployee);
    }

    private static String calculateAverageSalary()
    {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return null;
        }

        double totalSalary = employeeList.stream().mapToDouble(e -> e.salary).sum();
        double averageSalary = totalSalary / employeeList.size();
        System.out.println("Average Salary: $" + String.format("%.2f", averageSalary));
        return null;
    }

    private static void findEmployeeById(Scanner scanner)
    {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();

        Employe employee = findEmployeeById(id);
        if (employee != null) {
            System.out.println("Employee Found:\n" + employee);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    private static Employe findEmployeeById(int id)
    {
        return employeeList.stream().filter(e -> e.id == id).findFirst().orElse(null);
    }

    private static void printEmployeeStatistics()
    {
        System.out.println("Employee Statistics :");
        System.out.println("Total Employees: " + employeeList.size());
        System.out.println("Average Salary: $" + String.format("%.2f", calculateAverageSalary()));
        System.out.println("Highest Salary: $" + String.format("%.2f", findHighestSalary()));
    }

    private static double findHighestSalary()
    {
        return employeeList.stream().mapToDouble(e -> e.salary).max().getAsDouble();
    }

    private static void printDepartmentWiseEmployeeCount()
    {
        Map<String, Long> departmentWiseCount = employeeList.stream()
                .collect(Collectors.groupingBy(Employe::getDepartment, Collectors.counting()));

        System.out.println("Department Wise Employee Count:");
        departmentWiseCount.forEach((department, count) -> System.out.println(department + ": " + count));
    }

    private static void printAgeWiseEmployeeCount()
    {
        Map<Integer, Long> ageWiseCount = employeeList.stream()
                .collect(Collectors.groupingBy(Employe::getAge, Collectors.counting()));

        System.out.println("Age Wise Employee Count:");
        ageWiseCount.forEach((age, count) -> System.out.println(age + ": " + count));
    }

    private static void printEmployeeNameInReverseOrder()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(name -> new StringBuilder(name).reverse().toString())
                .forEach(System.out::println);
    }

    private static void printEmployeeNameInUpperCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static void printEmployeeNameInLowerCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }

    private static void printEmployeeNameWithFirstLetterUpperCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .forEach(System.out::println);
    }

    private static void printEmployeeNameWithFirstLetterLowerCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(name -> name.substring(0, 1).toLowerCase() + name.substring(1))
                .forEach(System.out::println);
    }

    private static void printEmployeeNameWithFirstLetterUpperCaseAndRestLowerCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase())
                .forEach(System.out::println);
    }

    private static void printEmployeeNameWithFirstLetterLowerCaseAndRestUpperCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(name -> name.substring(0, 1).toLowerCase() + name.substring(1).toUpperCase())
                .forEach(System.out::println);
    }

    private static void printEmployeeNameWithFirstLetterUpperCaseAndRestInTitleCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase())
                .map(name -> name.substring(0, 1) + name.substring(1).replace(" ", " ").replace(" ", " "))
                .forEach(System.out::println);
    }

    private static void printEmployeeNameWithFirstLetterLowerCaseAndRestInTitleCase()
    {
        employeeList.stream()
                .map(Employe::getName)
                .map(name -> name.substring(0, 1).toLowerCase() + name.substring(1).toUpperCase())
                .map(name -> name.substring(0, 1) + name.substring(1).replace(" ", " ").replace(" ", " "))
                .forEach(System.out::println);
    }
}