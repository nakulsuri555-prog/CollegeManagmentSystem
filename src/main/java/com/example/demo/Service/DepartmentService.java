package com.example.demo.Service;


import com.example.demo.Model.Department;
import com.example.demo.Repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo repo;
    public List<Department> getAllDepartments() {
        return repo.findAll();
    }

    public Department getDepById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void  addDepartment(Department department) {
        repo.save(department);
    }

    public void delete(Department department) {
        repo.delete(department);
    }

    public void update(int id, Department dep) {
        dep.setId(id);
        repo.save(dep);
    }
}
