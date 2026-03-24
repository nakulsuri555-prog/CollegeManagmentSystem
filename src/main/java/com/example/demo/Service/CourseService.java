package com.example.demo.Service;

import com.example.demo.Model.Course;
import com.example.demo.Model.Student;
import com.example.demo.Repository.CourseRepo;
import com.example.demo.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepo repo;

    @Autowired
    StudentRepo studentRepo;

    public List<Course> getAll() {
        return repo.findAll();
    }

    public void addCourse(Course course) {
        repo.save(course);
    }

    @Transactional
    public String ans(int courseId, int studentId) {
        Course course = repo.findById(courseId).orElse(null);
        Student student = studentRepo.findById(studentId).orElse(null);

        if (course == null)  return "course_not_found";
        if (student == null) return "student_not_found";

        course.getStudents().remove(student);
        student.getCourses().remove(course);

        repo.save(course);
        return "REMOVED";
    }

    public String updateCourse(int id, Course course) {
        Course existing = repo.findById(id).orElse(null);

        if (existing == null) return "not_found";

        existing.setName(course.getName());
        existing.setCode(course.getCode());
        existing.setCredits(course.getCredits());

        repo.save(existing);
        return "UPDATED";
    }
}