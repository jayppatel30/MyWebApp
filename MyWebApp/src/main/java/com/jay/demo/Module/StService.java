package com.jay.demo.Module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StService {

    private List<Student> students = Arrays.asList();

    public void addStudent(Student s){
        System.out.println(s);
        System.out.println(s.getFirstname() );
        students.add(s);
    }

    public List<Student> getStudents(){
        return students;
    }

    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s.getStudentId() == id) {
                return s;
            }
        }
        return null;
    }

    public void printStudents(){
        for(Student s: students){
            System.out.println(s.getFirstname());
        }
    }
}
