package com.example.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String email;
    private String phoneno;
    private String subject;


    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("teachers")   //
    private Department department;
}
