package com.jay.demo.Routes;

import java.util.List;

import com.jay.demo.Module.EssayKeywordsService;
import com.jay.demo.Module.SimilarityCheck;
import com.jay.demo.Objects.EssayKeywords;
import com.jay.demo.Module.StService;
import com.jay.demo.Objects.Student;
import com.jay.demo.Objects.TwoTexts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//Run project with 'Spring Boot App'
//Test the api working :: try with http://localhost:8080/students/103 in post man
//In chrome run http://localhost:8080/swagger-ui.html -> Expand operations -> Try it out
@Slf4j
@RestController
public class StudentRoute {

    @Autowired
    private StService ss;
    @Autowired
    private EssayKeywordsService ek;
    @Autowired
    private SimilarityCheck sim;

    @GetMapping("students")
    @RequestMapping(method=RequestMethod.GET, value = "students")
    public List<Student> getStudents() throws Exception{
        return ss.getStudents();
    }

    @GetMapping("students/{id}")
    @RequestMapping(method=RequestMethod.GET, value="students/{id}")
    public Student getSingleStudent(@PathVariable String id) throws Exception{
        return ss.getStudentById(id);
    }

    @GetMapping("delete/{id}")
    @RequestMapping(method=RequestMethod.GET, value="delete/{id}")
    public void deleteStudent(@PathVariable String id) throws Exception{
        ss.deleteStudent(id);
    }

    @PostMapping(path="/add/student")
    @RequestMapping(value = "/add/student", method = RequestMethod.POST)
    public void addStudent(@RequestBody Student student){
        ss.addStudent(student);
    }

    @PostMapping(path="/edit/student")
    @RequestMapping(value = "/edit/student", method = RequestMethod.POST)
    public void editStudent(@RequestBody Student student){
        ss.editStudent(student);
    }

    @PostMapping(path="/check/keywords")
    @RequestMapping(value = "/check/keywords", method = RequestMethod.POST)
    public int totalKeywords(@RequestBody EssayKeywords textData) throws Exception{
        textData.getKeywords();
        return ek.totalKeywords(textData);
    }

    @PostMapping(path="/check/similarity")
    @RequestMapping(value = "/check/similarity", method = RequestMethod.POST)
    public double getTest(@RequestBody TwoTexts texts){
        return sim.similar(texts.getText1(),texts.getText2());
    }

}