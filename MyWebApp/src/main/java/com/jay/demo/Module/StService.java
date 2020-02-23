package com.jay.demo.Module;

import com.jay.demo.Objects.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                System.out.println("Student with id- "+id+" is Deleted");
                break;
            }
        }
    }

    public List<Student> getStudents(){
        System.out.println("getStudents Invoked");
        return students;
    }

    public void editStudent(Student student){
        for (Student s : students) {
            String check = s.getStudentId();
            if (check.equals(student.getStudentId())) {
                s.setStudentId(student.getStudentId());
                s.setFirstname(student.getFirstname());
                s.setLastname(student.getLastname());
                s.setEmail(student.getEmail());
            }
        }
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
