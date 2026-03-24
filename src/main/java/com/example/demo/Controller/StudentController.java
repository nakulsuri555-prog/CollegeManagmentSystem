package com.example.demo.Controller;


import com.example.demo.Model.Department;
import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        return new ResponseEntity<>(service.getStudentById(id),HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<String> postStudent(@RequestPart Student student, @RequestPart MultipartFile imageFile){
        service.addStudent(student,imageFile);
        return new ResponseEntity<>("ADDED",HttpStatus.OK);
    }
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){

        Student student = service.getStudentById(id);

        if(student != null) {

            service.deleteStudent(student);
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT_FOUND",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/student/{id}/image")

    public ResponseEntity<?> getImage(@PathVariable int id){
        Student student = service.getStudentById(id);

        if(student == null){
            return new ResponseEntity<>("NOT_FOUND",HttpStatus.NOT_FOUND);
        }
        byte[] imageFile = student.getImage();

        return  ResponseEntity.ok().contentType(MediaType.valueOf(student.getImageType())).body(imageFile);
    }

    @GetMapping("/student/image/{depId}")
    public ResponseEntity<?> getStudentByDepId(@PathVariable int depId) {
        Department department = service.getDepartmentByStudentId(depId);
        if (department == null) {
            return new ResponseEntity<>("Student NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable int id,@RequestPart Student student,@RequestPart MultipartFile imageFile){
        Student s = service.getStudentById(id);

        if(s != null){
            service.updateStudent(student,imageFile);
            return new ResponseEntity<>("UPDATE",HttpStatus.OK);
        }
        return new ResponseEntity<>("FAILED",HttpStatus.NOT_FOUND);
    }
}
