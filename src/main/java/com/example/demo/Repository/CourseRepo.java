package com.example.demo.Repository;

import com.example.demo.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CourseRepo extends JpaRepository<Course,Integer> {
}
