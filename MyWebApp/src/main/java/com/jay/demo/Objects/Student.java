package com.jay.demo.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Student {


    private String firstname;
    private String lastname;
    private String studentId;
    private String email;

    /*** Getters ***/
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getEmail() { return email; }

    /*** Setters ***/
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setEmail(String email) { this.email = email; }




}
