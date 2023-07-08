package project_Infotrixs1;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EmployeeManagementSystemApp{
    private static final String DATA_FILE = "employees.txt";
    private static final Scanner scanner = new Scanner(System.in);
    private static EmployeeManagementSystem ems = new EmployeeManagementSystem(DATA_FILE);

    public static void main(String[] args) {
        displayMenu();
        int choice = getUserChoice();
        while (choice != 6) {
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    searchEmployee();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            displayMenu();
            choice = getUserChoice();
        }
        System.out.println("Thank you for using the Employee Management System!");
    }

    private static void displayMenu() {
        System.out.println("********** Employee Management System **********");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Search Employee");
        System.out.println("6. Exit");
        System.out.println("***********************************************");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, designation, salary);
        ems.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static void viewEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        Employee employee = ems.findEmployeeById(id);
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        Employee employee = ems.findEmployeeById(id);
        if (employee != null) {
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Employee Designation: ");
            String designation = scanner.nextLine();

            System.out.print("Enter Employee Salary: ");
            double salary = scanner.nextDouble();

            Employee updatedEmployee = new Employee(id, name, designation, salary);
            ems.updateEmployee(updatedEmployee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();

        ems.deleteEmployee(id);
    }

    private static void searchEmployee() {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        List<Employee> searchResults = ems.searchEmployeesByName(name);
        if (searchResults.isEmpty()) {
            System.out.println("No matching employees found.");
        } else {
           
            System.out.println("Matching employees:");
            for (Employee employee : searchResults) {
                System.out.println(employee);
            }
        }
    }
}