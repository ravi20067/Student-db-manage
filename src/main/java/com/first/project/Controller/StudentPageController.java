package com.first.project.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.first.project.model.Student;
import com.first.project.repo.StudentRepo;

@Controller
public class StudentPageController {

    @Autowired
    StudentRepo repo;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", repo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String newPost(Model model) {
        model.addAttribute("student", new Student());
        return "new";
    }

    @PostMapping("/new")
    public String savePost(@ModelAttribute Student student) {
        repo.save(student);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {
        model.addAttribute("student", repo.findById(id).get());
        return "edit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        repo.save(student);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchStudents(@RequestParam("keyword") String keyword, Model model) {
        List<Student> allStudents = repo.findAll(); 

        List<Student> listStudents;
        if (keyword == null || keyword.trim().isEmpty()) {
            listStudents = allStudents; 
        } else {
            String kw = keyword.toLowerCase();
            listStudents = allStudents.stream()
                    .filter(s ->
                        (String.valueOf(s.getId()).contains(kw)) || 
                        (s.getFullname() != null && s.getFullname().toLowerCase().contains(kw)) ||
                        (s.getClassname() != null && s.getClassname().toLowerCase().contains(kw))
                    )
                    .collect(Collectors.toList());
        }

        model.addAttribute("listStudents", listStudents);
        return "index"; // thymeleaf page
    }
}
