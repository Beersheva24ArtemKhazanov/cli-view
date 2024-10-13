package telran.view;

import java.time.LocalDate;
import java.util.*;

record Employee(long id, String name, String department, int salary, LocalDate birthDate) {
}

public class Main {
    static InputOutput io = new StandardInputOutput();
    /*********************** */
    // For HW #35 constants
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = { "QA", "Audit", "Development", "Management" };
    // name should be at least 3 English letters; first - capital, others - lower
    // case
    final static long MIN_ID = 100000;
    final static long MAX_ID = 999999;
    final static int MIN_AGE = 18;
    final static int MAX_AGE = 70;

    /*********************************** */
    public static void main(String[] args) {
        // readEmployeeAsObject();23
        readEmployeeBySeparateFields();
    }

    static void readEmployeeAsObject() {
        Employee empl = io.readObject("Enter employee data in the format:" +
                " <id>#<name>#<department>#<salary>#<yyyy-MM-DD> ",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    return new Employee(Long.parseLong(tokens[0]), tokens[1], tokens[2],
                            Integer.parseInt(tokens[3]), LocalDate.parse(tokens[4]));

                });
        io.writeLine("You are entered the following Employee data");
        io.writeLine(empl);
    }

    static void readEmployeeBySeparateFields() {
        long id = io.readNumberRange(String.format("Please Enter ID between %d and %d", MIN_ID, MAX_ID),
         String.format("Wrong format for Employee ID. Must be between %d and %d",MIN_ID, MAX_ID), MIN_ID, MAX_ID).intValue();
        io.writeLine("You are entered the following Employee ID");
        io.writeLine(id);

        String name = io.readStringPredicate("Please Enter your name in format : \n Starts with a capital letter \n Minimum 3 letters", 
        "Wrong format for Employee name. Name should contain: \n Starts with a capital letter \n Minimum 3 letters", n -> n.matches("[A-Z][a-z]{2,}"));
        io.writeLine("You are entered the following Employee name");
        io.writeLine(name);

        String department = io.readStringOptions(String.format("Please enter a department from those suggested %s.", Arrays.toString(DEPARTMENTS)), 
        "Department does not exist", new HashSet<>(Arrays.asList(DEPARTMENTS)));
        io.writeLine("You are entered the following Employee department");
        io.writeLine(department);

        int salary = io.readNumberRange(String.format("Please enter salary between %d and %d", MIN_SALARY, MAX_SALARY), 
        String.format("Wrong format for Employee salary. Must be between %d and %d",MIN_SALARY, MAX_SALARY), MIN_SALARY, MAX_SALARY).intValue();
        io.writeLine("You are entered the following Employee salary");
        io.writeLine(salary);

        LocalDate birthDate = io.readIsoDateRange(String.format("Please enter your birth date in format: \n yyyy-mm-dd \n Your age must be between %d and %d", MIN_AGE, MAX_AGE),
        String.format("Wrong format for Employee birth date. Your age must be between %d and %d", MIN_AGE, MAX_AGE), LocalDate.now().minusYears(MAX_AGE), LocalDate.now().minusYears(MIN_AGE));
        io.writeLine("You are entered the following Employee birth date");
        io.writeLine(birthDate);

        io.writeLine(new Employee(id, name, department, salary, birthDate));
    }
}