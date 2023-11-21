package com.example.Library.ManageMent.System.Entites;

import com.example.Library.ManageMent.System.Enum.CardStatus;
import com.example.Library.ManageMent.System.Repository.StudentRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libraryCard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;// this is primary key of library casrd table

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private String nameOnCard;

    private Integer noOfBookIssued;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private Student student; // fk of libraryCard

   // this variable ia to be put in mapped by attribute in parent class;


    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();




}
