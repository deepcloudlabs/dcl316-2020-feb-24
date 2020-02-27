package com.example.hr.dto;

import com.example.hr.entity.Department;
import com.example.hr.validation.Iban;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

public class EmployeeUpdateRequest {
    @Min(2700)
    private double salary;
    @Iban
    private String iban;
    private byte[] photo;
    @AssertTrue
    private boolean fulltime;
    private Department department;

    public EmployeeUpdateRequest() {
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
