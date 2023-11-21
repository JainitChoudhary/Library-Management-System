package com.example.Library.ManageMent.System.Service;

import com.example.Library.ManageMent.System.Entites.LibraryCard;
import com.example.Library.ManageMent.System.Entites.Student;
import com.example.Library.ManageMent.System.Enum.CardStatus;
import com.example.Library.ManageMent.System.Repository.CardRepository;
import com.example.Library.ManageMent.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;
    public LibraryCard generateCard(){

        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.NEW);

        card= cardRepository.save(card);

        return card;
    }
    public String associateStudentWIthCard(Integer studentId,Integer cardNo){

        Optional<Student>optionalStudent= studentRepository.findById(studentId);

        Student student = optionalStudent.get();

        Optional<LibraryCard>optionalLibraryCard = cardRepository.findById(cardNo);

        LibraryCard libraryCard = optionalLibraryCard.get();

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNameOnCard(student.getName());
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);
        studentRepository.save(student);

        return  "Card with "+cardNo+" has been associate to student with "+ studentId;









    }
}
