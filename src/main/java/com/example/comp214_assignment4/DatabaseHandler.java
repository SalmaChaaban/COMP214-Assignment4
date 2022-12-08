package com.example.comp214_assignment4;

import java.sql.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHandler {

    static final String DRIVER = "oracle.jdbc.OracleDriver";
    static final String DATABASE_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
    static final String USERNAME = "COMP214_F22_er_20";
    static final String PASSWORD = "password";
    static Connection connection;


    public static boolean attempToConnect() {
        try {
            System.out.println(">> Starting Program!");

            Class.forName(DRIVER);
            System.out.println(">> Driver Loaded Successfully!");

            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println(">> Database Connected Successfully!");

            return true;
        }
        catch (Exception e) {
            System.out.println("Exception Found!");
            e.printStackTrace();
            return false;
        }
    }

    public static LinkedList<Job> GetJobs() throws SQLException {

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT job_id, job_title FROM hr_jobs");
        LinkedList jobsList = new LinkedList<Job>();

        while (rs.next()) {
            Job job = new Job();
            job.setID(rs.getString("job_id"));
            job.setName(rs.getString("job_title"));
            jobsList.add(job);
        }

        return jobsList;
    }


    public static LinkedList<Employee> GetManagers() throws SQLException {

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT employee_id, first_name, last_name FROM hr_employees");
        LinkedList managersList = new LinkedList<Job>();

        while (rs.next()) {
            Employee employee = new Employee();
            employee.setEmpID(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            managersList.add(employee);
        }

        return managersList;
    }


    public static LinkedList<Department> GetDepartments() throws SQLException {

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT department_id, department_name FROM hr_departments");
        LinkedList departmentsList = new LinkedList<Job>();

        while (rs.next()) {
            Department department = new Department();
            department.setDepartmentID(rs.getInt("department_id"));
            department.setDepartmentName(rs.getString("department_name"));
            departmentsList.add(department);
        }

        return departmentsList;
    }


    public static LinkedList<Employee> GetEmployees() throws SQLException {

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM hr_employees ORDER BY employee_id");
        LinkedList employeesList = new LinkedList<Employee>();

        while (rs.next()) {
            Employee employee = new Employee();
            employee.setEmpID(rs.getInt("employee_id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setEmail(rs.getString("email"));
            employee.setPhoneNumber(rs.getString("phone_number"));
            employee.setHireDate(rs.getString("hire_date"));
            employee.setJobID(rs.getString("job_id"));
            employee.setSalary(rs.getDouble("salary"));
            employee.setCommissionPCT(rs.getDouble("commission_pct"));
            employee.setManagerID(rs.getInt("manager_id"));
            employee.setDepartmentID(rs.getInt("department_id"));
            employee.setStatus(rs.getString("status"));
            employeesList.add(employee);
        }

        return employeesList;
    }


    public static int setEmployeeID() throws SQLException {

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(employee_id) FROM hr_employees");
        int empID = 0;
        if(rs.next()) {

            empID = rs.getInt("MAX(employee_id)");
            empID++;
        }
        return empID;


    }


    public static void hireEmployee(String firstName, String lastName, String email, String phoneNumber, double salary, String jobID, int managerID, int departmentID) throws SQLException {

        PreparedStatement pst = connection.prepareStatement("INSERT INTO hr_employees (first_name, last_name, email, phone_number, job_id, salary, manager_id, department_id, employee_id, hire_date)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?)");

        pst.setString(1, firstName);
        pst.setString(2, lastName);
        pst.setString(3, email);
        pst.setString(4, phoneNumber);
        pst.setString(5, jobID);
        pst.setDouble(6, salary);
        pst.setInt(7, managerID);
        pst.setInt(8, departmentID);
        pst.setInt(9, setEmployeeID());
        long millis = System.currentTimeMillis();
        pst.setDate(10, new Date(millis));

        int val = pst.executeUpdate(); // val returns the row count
    }


    public static void updateEmployee(int empID, String newPhoneNumber, String newEmail, Double newSalary) throws SQLException {

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("UPDATE hr_employees SET phone_number = '" + newPhoneNumber + "', email = '" + newEmail + "', salary = '" + newSalary + "' WHERE employee_id = "+ empID );

    }


} // End Class
