package com.thecoder.models;

public class Student {

    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private String email;

    public Student(String firstname, String lastname, int age, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
    }

    public Student(Long id, String firstname, String lastname, int age, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
    }

    public Student(Long id, String firstname, String lastname, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;

    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
