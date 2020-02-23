package com.jay.demo.Module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StService {
    @Autowired
    Student s = new Student();
    private List<Student> students = new ArrayList<Student>();

    public void addStudent(Student s){
        System.out.println("added student");
        students.add(s);
        System.out.println(students);
    }

    public void deleteStudent(String id){
        for (Student s:students) {
            if(s.getStudentId().equals(id)){
                students.remove(s);
            }
        }
    }

    public List<Student> getStudents(){
        System.out.println("Inside getStudents");
        return students;
    }

    public Student getStudentById(String id) {
        for (Student s : students) {
            String check = s.getStudentId();
            if (check.equals(id)) {
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
