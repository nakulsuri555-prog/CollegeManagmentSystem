package com.example.demo.Controller;


import com.example.demo.Model.Teacher;
import com.example.demo.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController

@RequestMapping("/api")
public class TeacherController {

    @Autowired
    TeacherService service;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher){
        service.addTeacher(teacher);
        return new ResponseEntity<>("ADDED",HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int id){
        String s = service.deleteTeacher(id);
        return switch(s){
            case "NOT_FOUND" -> new ResponseEntity<>(s,HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(s,HttpStatus.OK);
        };
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable int id,@RequestBody Teacher teacher){
        String s = service.updateTeacher(id, teacher);

        return switch(s){
            case "NOT_FOUND" -> new ResponseEntity<>(s, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(s, HttpStatus.OK);
        };
    }
}
