package com.example.StudentSystem.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer regno;
    private String name;
    private String dept;
    private Integer year;

    public Registration(long id, Integer regno, String name, String dept, Integer year) {
        this.id = id;
        this.regno = regno;
        this.name = name;
        this.dept = dept;
        this.year = year;
    }

    public Registration() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRegno() {
        return regno;
    }

    public void setRegno(Integer regno) {
        this.regno = regno;
    }

}