package com.example.Library.ManageMent.System.Service;

import com.example.Library.ManageMent.System.Entites.Student;
import com.example.Library.ManageMent.System.Repository.StudentRepository;
import com.example.Library.ManageMent.System.ResponseObject.BasicDetailsStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String addStudent(Student student){

        studentRepository.save(student);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String body = "Hi "+student.getName()+" !" +
                "You have successfully registered. You can start issuing the books now.";

        mailMessage.setFrom("sdemode408@gmail.com");
        mailMessage.setTo(student.getEmailId());
        mailMessage.setSubject("Welcome To  Rewa Library !!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);
        return "Student has been saved to the DB";

    }

    public BasicDetailsStudentResponse getStudentDetails(Integer id){

        Student student = studentRepository.findBy(id).get();

        BasicDetailsStudentResponse result = new BasicDetailsStudentResponse();

        result.setAge(student.getAge());
        result.setName(student.getName());
        result.setMobNo(student.getMobNo());

        return result;

    }


}