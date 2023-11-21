package com.example.Library.ManageMent.System.Service;


import com.example.Library.ManageMent.System.Entites.Book;
import com.example.Library.ManageMent.System.Entites.Book;
import com.example.Library.ManageMent.System.Entites.LibraryCard;
import com.example.Library.ManageMent.System.Entites.Transaction;
import com.example.Library.ManageMent.System.Enum.CardStatus;
import com.example.Library.ManageMent.System.Enum.TransactionStatus;
import com.example.Library.ManageMent.System.Exception.*;
import com.example.Library.ManageMent.System.Exception.InvalidCardStatus;
import com.example.Library.ManageMent.System.Repository.BookRepository;
import com.example.Library.ManageMent.System.Repository.CardRepository;
import com.example.Library.ManageMent.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Integer MAX_LIMIT_OF_BOOKS = 3;

    private static final Integer FINE_PER_DAY = 5;


    public String issueBook(Integer bookId,Integer cardId) throws Exception{

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //Validations

        //Valid BookId
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(!bookOptional.isPresent()) {
            throw new BookNotFound("BookId entered is invalid");
        }
        Book book = bookOptional.get();
        //Availability of book

        if(!book.isAvailable()){
            throw new BookNotAvailable("Book is Unavailable");
        }

        //Valid CardId
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFound("CardId entered is Invalid");
        }


        LibraryCard libraryCard = optionalLibraryCard.get();

        //Valid Card Status
        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatus("Card Status is Not Active");
        }

        // Maximum no of book Issues : MAX_LIMIT = 3
        if(libraryCard.getNoOfBookIssued()==MAX_LIMIT_OF_BOOKS){
            throw new MaxBooksAlreadyIssued(MAX_LIMIT_OF_BOOKS+" is maximum books that can be issued");
        }


        //Creating the Transaction Entity :
        transaction.setTransactionStatus(TransactionStatus.ISSUED);


        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued()+1);

        book.setAvailable(false); //Book is no longer available

        //Setting FK
        transaction.setBook(book);
        transaction.setCard(libraryCard);


        //Saving relevant Entities : Bidirectional Mapping
        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);


        //Instead of saving the parent : just save the child
        transactionRepository.save(transaction);

        return "The book with bookId "+bookId+" has been issued " +
                "to card with "+cardId;
    }

    public String returnBook(Integer bookId,Integer cardId){

        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        //I need to find out that issue Transaction

        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        Date issueDate = transaction.getCreatedOn();

        //Predefined method that you can use to calculate days
        long milliSeconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);

        int fineAmount = 0;

        if(days>15){
            fineAmount = Math.toIntExact((days - 15) * FINE_PER_DAY);
        }

        Transaction newTransaction = new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //Setting the FK's
        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBookIssued(card.getNoOfBookIssued()-1);

        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "book has been returned";

    }
}