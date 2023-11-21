package com.example.Library.ManageMent.System.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student {


    @Id
    private Integer studentId; // this will behave as the Primary Key


    private String name;

    private int age;

    @Column(name = "contactNo",unique = true,nullable = false)

    private String mobNo;

    private String emailId;

    private String bloodGroup;


    @JsonIgnore
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;

}
