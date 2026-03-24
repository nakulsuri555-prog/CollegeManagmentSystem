package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Student> students;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")    // it will not include departments in the output
    private List<Teacher> teachers;
}