package com.thecoder.controllers;

import com.thecoder.models.Student;
import com.thecoder.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
class StudentController {

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/student")
    public String studentList(Model model) throws SQLException {
        model.addAttribute("students", studentRepository.getAllStudents());
        return "students";
    }

    // add new student site
    @GetMapping("/students/new")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("addStudents",student);
        return "addStudent";
    }
    // students gets posted on student site and u get redirected
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("addStudents")Student student) {
        studentRepository.addStudent(student);
        return "redirect:/student";
    }

    // update site
    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        model.addAttribute("updateStud", studentRepository.getStudentById(id));
        return "updateStudent";
    }
    // updated students get posted on the redirected site.
    @PostMapping("/students/{id}")
    public String editStudent(@PathVariable Long id, @ModelAttribute("updateStud")Student student) {

        Student students = studentRepository.getStudentById(id); // getting the student from the database
        students.setId(id);
        students.setFirstname(student.getFirstname());
        students.setLastname(student.getLastname());
        students.setAge(student.getAge());
        students.setEmail(student.getEmail());
        studentRepository.updateStudent(students);
        return "redirect:/student";
    }

    @GetMapping("/student/{id}")
    public String deleteStudent(@PathVariable Long id) throws SQLException {
        studentRepository.deleteStudent(id);
        return "redirect:/student";
    }

}
