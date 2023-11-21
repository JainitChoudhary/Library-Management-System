package com.example.Library.ManageMent.System.Controller;

import com.example.Library.ManageMent.System.Entites.LibraryCard;
import com.example.Library.ManageMent.System.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard(){


        LibraryCard newCard = cardService.generateCard();

        String response = "The new Card is generated  and having a cardNo "+ newCard.getCardNo();

        return  new ResponseEntity(response, HttpStatus.OK);

    }

    @PutMapping("/associateWithStudent")
    public  ResponseEntity associateWithStudent(@RequestParam("studentId") Integer studentId, @RequestParam("cardId") Integer cardNo){


        String result = cardService.associateStudentWIthCard(studentId,cardNo);

                return new ResponseEntity(result, HttpStatus.OK);
    }

}
