package project_Infotrixs1;
import java.io.*;
import java.util.*;


public class EmployeeManagementSystem {
    private List<Employee> employees;
    private String dataFile;

    public EmployeeManagementSystem(String dataFile) {
        this.employees = new ArrayList<>();
        this.dataFile = dataFile;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveEmployees();
    }

    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> searchEmployeesByName(String name) {
        List<Employee> searchResults = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                searchResults.add(employee);
            }
        }
        return searchResults;
    }

    public void updateEmployee(Employee updatedEmployee) {
        Employee employee = findEmployeeById(updatedEmployee.getId());
        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setDesignation(updatedEmployee.getDesignation());
            employee.setSalary(updatedEmployee.getSalary());
            saveEmployees();
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void deleteEmployee(int id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employees.remove(employee);
            saveEmployees();
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void loadEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String designation = parts[2];
                double salary = Double.parseDouble(parts[3]);
                Employee employee = new Employee(id, name, designation, salary);
                employees.add(employee);
            }
        } catch (IOException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }

    private void saveEmployees() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," +
                        employee.getName() + "," +
                        employee.getDesignation() + "," +
                        employee.getSalary());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}