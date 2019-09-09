package com.jay.demo.Routes;

import java.util.List;

import com.jay.demo.Module.StService;
import com.jay.demo.Module.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//Run project with 'Spring Boot App'
//Test the api working :: try with http://localhost:8080/students/103 in post man
//In chrome run http://localhost:8080/swagger-ui.html -> Expand operations -> Try it out

@RestController
public class StudentService {

    @Autowired
    private StService ss;

    @GetMapping("students")
    public List<Student> getStudents() throws Exception{
        return ss. getStudents();
    }

    @GetMapping("students/{id}")
    //@RequestMapping(method=RequestMethod.GET, value="students/{id}")
    public Student getSingleStudent(@PathVariable int id) throws Exception{
        return ss.getStudentById(id);
    }

    @PostMapping(path="/students")
    //@RequestMapping(value = "/students", method = RequestMethod.POST)
    public void addStudent(@RequestBody Student student){
        ss.addStudent(student);
    }

}