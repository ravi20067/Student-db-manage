package com.first.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.first.project.model.Student;
import com.first.project.repo.StudentRepo;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class StudentContyroller {

    @Autowired
    StudentRepo repo;

    @GetMapping("/student")
    public List<Student> getAllStudents(){
        return repo.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudentbyId(@PathVariable int id){
        Student student = repo.findById(id).get();
        return student;
    }

    @PostMapping("/student/create")
    public void createstudent(@RequestBody Student student){
        repo.save(student);
    }

    @GetMapping("/student/delete/{id}")
    public Student deletestudent(@PathVariable int id){
        Student student = repo.findById(id).get();
        repo.delete(student);
        return student;
    }
    @GetMapping("/student/del/{id}")
    public void delstudent(@PathVariable int id ){
        Student student = repo.findById(id).get();
        repo.delete(student);
    }
    @GetMapping("/id")
    public String getSessionid(HttpServletRequest request) {
        return "Session id = "+request.getSession().getId();
    }

    @GetMapping("/csrftoken")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
