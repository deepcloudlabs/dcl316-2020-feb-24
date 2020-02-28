package com.example.hr.entity;

import com.example.hr.validation.Iban;
import com.example.hr.validation.TcKimlikNo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Document(collection = "employees")
public class Employee {
    @TcKimlikNo
    @Id
    private String identity;
    @Size(min=3)
    private String fullname;
    @Min(2700)
    private double salary;
    @Iban
    private String iban;
    private String photo;
    @Max(2004)
    private int birthYear;
    @AssertTrue
    private boolean fulltime;
    private Department department;

    public Employee() {
    }

    //region getters/setters

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
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
    //endregion


    @Override
    public String toString() {
        return "Employee{" +
                "identity='" + identity + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                ", iban='" + iban + '\'' +
                ", birthYear=" + birthYear +
                ", fulltime=" + fulltime +
                ", department=" + department +
                '}';
    }
}
