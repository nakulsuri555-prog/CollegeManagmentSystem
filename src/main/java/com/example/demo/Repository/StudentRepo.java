package com.example.demo.Repository;


import com.example.demo.Model.Department;
import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

    @Query("SELECT s.department FROM Student s WHERE s.id = :id")
    Department findDepartmentByStudentId(@Param("id") int id);
}
