package com.example.Library.ManageMent.System.Controller;

import com.example.Library.ManageMent.System.Entites.Author;
import com.example.Library.ManageMent.System.Entites.Book;
import com.example.Library.ManageMent.System.Entites.Student;
import com.example.Library.ManageMent.System.ResponseObject.BasicDetailsStudentResponse;
import com.example.Library.ManageMent.System.Service.StudentService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
       return studentService.addStudent((student));



    }
    @GetMapping("/getBasicDetails")
    public BasicDetailsStudentResponse getBasicDetails(@RequestParam("id")Integer id){

        BasicDetailsStudentResponse result = studentService.getStudentDetails(id);
        return result;

    }


}
