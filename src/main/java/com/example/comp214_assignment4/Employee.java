package com.example.comp214_assignment4;

public class Employee {

    public int empID;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String hireDate;
    public String jobID;
    public Double salary;
    public Double commissionPCT;
    public int managerID;
    public int departmentID;
    public String status;

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int ID) {
        this.empID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String job_iD) {
        this.jobID = job_iD;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommissionPCT() {
        return commissionPCT;
    }

    public void setCommissionPCT(Double commissionPCT) {
        this.commissionPCT = commissionPCT;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
