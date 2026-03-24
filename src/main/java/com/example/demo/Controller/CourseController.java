package com.example.demo.Controller;


import com.example.demo.Model.Course;
import com.example.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CourseController {


    @Autowired
    CourseService service;

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        service.addCourse(course);
        return new ResponseEntity<>("ADDED",HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}/enroll/{studentId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId,@PathVariable int studentId) {
        String s = service.ans(courseId, studentId);

        return switch(s){
            case "course_not_found" ->  new ResponseEntity<>("INVALID COURSE",HttpStatus.NOT_FOUND);
            case "student_not_found" -> new ResponseEntity<>("INVALID STUDNET",HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(s,HttpStatus.OK);
        };
    }


    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable int id,
            @RequestBody Course course) {

        String result = service.updateCourse(id, course);

        return switch (result) {
            case "not_found" -> new ResponseEntity<>("COURSE NOT FOUND", HttpStatus.NOT_FOUND);
            default          -> new ResponseEntity<>(result,             HttpStatus.OK);
        };
    }
}
