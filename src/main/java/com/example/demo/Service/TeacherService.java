package com.example.demo.Service;


import com.example.demo.Model.Teacher;
import com.example.demo.Repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {


    @Autowired
    TeacherRepo repo;

    public List<Teacher> getAll() {
        return repo.findAll();
    }


    public void addTeacher(Teacher teacher) {
        repo.save(teacher);
    }

    public String deleteTeacher(int id) {

        Teacher teacher = repo.findById(id).orElse(null);

        if(teacher == null){
            return "NOT_FOUND";
        }

        repo.delete(teacher);
        return "DELETED";
    }

    public String updateTeacher(int id, Teacher teacher){
        Optional<Teacher> existingTeacher = repo.findById(id);

        if(existingTeacher.isEmpty()){
            return "NOT_FOUND";
        }

        Teacher t = existingTeacher.get();
        t.setName(teacher.getName());
        t.setSubject(teacher.getSubject());

        repo.save(t);
        return "UPDATED";
    }
}
