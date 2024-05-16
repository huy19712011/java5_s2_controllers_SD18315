package com.example.java5_s2_controllers_sd18315.controllers;

import com.example.java5_s2_controllers_sd18315.entities.Student;
import com.example.java5_s2_controllers_sd18315.services.StudentService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/basic")
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


    //2. @RequestParam
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

    //3. @PathVariable
    // http://localhost:8080/hello/1/email
    @GetMapping(value = "/hello/{id}/{email}")
    @ResponseBody
    public String sayHelloV3a(
            @PathVariable("id") int id,
            @PathVariable("email") String email
    ) {
        return "Hello student with id = " + id + " email = " + email;
    }

    //4. @RequestMapping on Class level

    //5. @RequestMapping with headers
    @RequestMapping(value = "/method", headers = {"key1=value1", "id=1"})
    @ResponseBody
    public String method() {
        return "method";
    }

    //6. @RequestMapping with produces, consumes
    @RequestMapping(value = "/method6", produces = {"application/json", "application/xml"}, consumes = "text/html")
    @ResponseBody
    public String method6() {
        return "method6";
    }

    //7. @CookieValue
    @RequestMapping(value = "/readCookie")
    @ResponseBody
    public String readCookieValue(@CookieValue(value = "username", defaultValue = "defaultName") String username) {
        return "Cookie value: " + username;
    }

    @GetMapping(value = "/change-username")
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "ABC");
        response.addCookie(cookie);

        return "Usename is changed...";
    }

    @GetMapping(value = "/all-cookies")
    @ResponseBody
    public String readAllCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            return Arrays.stream(cookies)
                    .map(c -> c.getName() + " = " + c.getValue())
                    .collect(Collectors.joining(", "));
        }

        return "No cookies";
    }


}

