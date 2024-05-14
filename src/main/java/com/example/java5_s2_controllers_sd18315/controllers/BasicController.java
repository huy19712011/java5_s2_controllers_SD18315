package com.example.java5_s2_controllers_sd18315.controllers;

import com.example.java5_s2_controllers_sd18315.entities.Student;
import com.example.java5_s2_controllers_sd18315.services.StudentService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BasicController {

    final
    StudentService studentService;

    public BasicController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/students")
    public String showStudents(Model model) { // Model, ModelMap, ModelAndView
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);

        return "home";
    }


    //@GetMapping(value = "/hello")
    //@ResponseBody
    ////public String sayHello(@RequestParam(name = "username") @Nullable String name) {
    //public String sayHelloV2a(@RequestParam(name = "username", required = false) String name) {
    //    return "hello " + name + "!";
    //}

    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2b(
    //        @RequestParam(name = "username", required = false) String name,
    //        @RequestParam(name = "email", required = false) String email
    //) {
    //    return "hello " + name + " ! " + email;
    //}

    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2c(
    //        @RequestParam(name = "username") Optional<String> name,
    //        @RequestParam(name = "email", required = false) String email
    //) {
    //    return "hello " + name.orElseGet(() -> "Not Provided") + " ! " + email;
    //}

    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2c(
    //        @RequestParam(name = "username") Optional<String> name,
    //        @RequestParam(name = "email", defaultValue = "default-EMAIL") String email
    //) {
    //    return "hello " + name.orElseGet(() -> "Not Provided") + " ! " + email;
    //}

    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2c(
    //        @RequestParam Map<String, String> allParams
    //        ) {
    //    return "Parameters: " + allParams.entrySet();
    //}

    @GetMapping(value = "/hello", params = {"username", "email"})
    @ResponseBody
    public String sayHelloV2c(
            @RequestParam Map<String, String> allParams
            ) {
        return "Parameters: " + allParams.entrySet();
    }


}
