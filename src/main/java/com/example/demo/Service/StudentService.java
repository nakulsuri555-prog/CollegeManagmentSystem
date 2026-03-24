package com.example.demo.Service;


import com.example.demo.Model.Department;
import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepo;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;


    public List <Student> getAll() {
        return repo.findAll();
    }

    public Student getStudentById(int id) {
        return  repo.findById(id).orElse(null);

    }

    public void addStudent(Student student, MultipartFile imageFile){
        student.setImageName(imageFile.getOriginalFilename());
        student.setImageType(imageFile.getContentType());
        try {
            student.setImage(imageFile.getBytes());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        repo.save(student);
    }

    public void deleteStudent(Student student) {

        repo.delete(student);
    }

    public void updateStudent(Student student, MultipartFile imageFile) {

        student.setImageName(imageFile.getName());
        student.setImageFile(imageFile.getContentType());
        try {
            student.setImage(imageFile.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        repo.save(student);
    }

    public Department getDepartmentByStudentId(int id) {
        return repo.findDepartmentByStudentId(id);
    }
}
