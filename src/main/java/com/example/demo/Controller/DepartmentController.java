package com.example.demo.Controller;


import com.example.demo.Model.Department;
import com.example.demo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartment(){
        return new ResponseEntity<>(service.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id){
        return new ResponseEntity<>(service.getDepById(id),HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<?> addDepartment(@RequestBody Department department){
        service.addDepartment(department);
        return new ResponseEntity<>("ADDED",HttpStatus.OK);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        Department department = service.getDepById(id);

        if(department != null){
            service.delete(department);
            return new ResponseEntity<>("DELETED",HttpStatus.OK);
        }
        return new ResponseEntity<>("INVALID",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable int id, @RequestBody Department dep){
        Department department = service.getDepById(id);

        if(department != null){
            service.update(id,dep);
            return new ResponseEntity<>("UPDATED",HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT_FOUND",HttpStatus.NOT_FOUND);
    }
}
