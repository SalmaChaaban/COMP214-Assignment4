package com.example.comp214_assignment4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable  {

    // Employee Hire Scene
    @FXML
    private ComboBox<Job> jobList;
    @FXML
    private ComboBox<Employee> managerList;
    @FXML
    private ComboBox<Department> departmentList;
    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField numField;
    @FXML
    private TextField hireDateField;
    @FXML
    private TextField salaryField;
    @FXML
    private Button hireButton;
    @FXML
    private Button cancelHireButton;


    // Employees Table in the Employee List Scene
    @FXML
    private TableView<Employee> employeeTable;
    // Employees Table Columns
    @FXML
    private TableColumn<Employee, Integer> employeeID;
    @FXML
    private TableColumn<Employee, String> firstName;
    @FXML
    private TableColumn<Employee, String> lastName;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, String> phoneNumber;
    @FXML
    private TableColumn<Employee, String> hireDate;
    @FXML
    private TableColumn<Employee, Integer> jobID;
    @FXML
    private TableColumn<Employee, Double> salary;
    @FXML
    private TableColumn<Employee, Double> commissionPCT;
    @FXML
    private TableColumn<Employee, Integer> managerID;
    @FXML
    private TableColumn<Employee, Integer> departmentID;
    @FXML
    private TableColumn<Employee, String> status;
    // 12 Columns
    // Employee Edit in Employee List Scene
    @FXML
    private TextField empIDForEditField;
    @FXML
    private TextField newPhoneNumField;
    @FXML
    private TextField newEmailField;
    @FXML
    private TextField newSalaryField;
    @FXML
    private Button updateEmpButton;



    private void fillJobList() throws SQLException {

        DatabaseHandler.attempToConnect();

        ObservableList<Job> test = FXCollections.observableList(DatabaseHandler.GetJobs());
        jobList.setItems(test);
        jobList.getSelectionModel().selectFirst();
        jobList.setConverter(new StringConverter<Job>() {
            @Override
            public String toString(Job job) {
                boolean isnull = job==null;
                if(isnull){
                    return "null";
                }
                return job.ID +"\t\t\t"+job.name;
            }

            @Override
            public Job fromString(String s) {
                return jobList.getItems().stream().filter( ap ->
                        ap.name.equals(s)).findFirst().orElse(null);
            }

        });
    }

    private void fillManagerList() throws SQLException {
        ObservableList<Employee> managersList = FXCollections.observableList(DatabaseHandler.GetManagers());
        managerList.setItems(managersList);
        managerList.getSelectionModel().selectFirst();

        managerList.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee employee) {
                return employee.empID + "\t\t\t" + employee.firstName + "\t\t\t" + employee.lastName;
            }

            @Override
            public Employee fromString(String s) {
                return managerList.getItems().stream().filter( ap ->
                        ap.firstName.equals(s)).findFirst().orElse(null);
            }
        });
    }

    private void fillDepartmentList() throws SQLException {
        ObservableList<Department> List = FXCollections.observableList(DatabaseHandler.GetDepartments());
        departmentList.setItems(List);
        departmentList.getSelectionModel().selectFirst();
        departmentList.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department department) {

                return department.departmentID +"\t\t\t"+department.departmentName;
            }

            @Override
            public Department fromString(String s) {
                return departmentList.getItems().stream().filter( ap ->
                        ap.departmentName.equals(s)).findFirst().orElse(null);
            }

        });
    }

    private void fillEmployeeTable() throws SQLException {

        ObservableList<Employee> List = FXCollections.observableList(DatabaseHandler.GetEmployees());

        employeeID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("empID"));
        firstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
        hireDate.setCellValueFactory(new PropertyValueFactory<Employee, String>("hireDate"));
        jobID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("jobID"));
        salary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        commissionPCT.setCellValueFactory(new PropertyValueFactory<Employee, Double>("commissionPCT"));
        managerID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));
        departmentID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("departmentID"));
        status.setCellValueFactory(new PropertyValueFactory<Employee, String>("status"));

        employeeTable.setItems(List);
        employeeTable.getSelectionModel().selectFirst();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            fillJobList();
            fillManagerList();
            fillDepartmentList();
            fillEmployeeTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void hireEmployee() throws SQLException {

        DatabaseHandler.hireEmployee(fNameField.getText(), lNameField.getText(), emailField.getText(), numField.getText(),
                Double.parseDouble(salaryField.getText()), jobList.getValue().ID, managerList.getValue().empID, departmentList.getValue().departmentID);

    }

    public void cancelHireEmployee() {
        fNameField.clear();
        lNameField.clear();
        emailField.clear();
        numField.clear();
        jobList.getSelectionModel().selectFirst();
        managerList.getSelectionModel().selectFirst();
        departmentList.getSelectionModel().selectFirst();

    }

    public void updateEmployee() throws SQLException {

        DatabaseHandler.updateEmployee(Integer.parseInt(empIDForEditField.getText()), newPhoneNumField.getText(), newEmailField.getText(), Double.parseDouble(newSalaryField.getText()));
    }



} // END CLASS
