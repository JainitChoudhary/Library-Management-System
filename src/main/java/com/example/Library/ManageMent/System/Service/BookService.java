package com.example.Library.ManageMent.System.Service;

import ch.qos.logback.core.model.INamedModel;
import com.example.Library.ManageMent.System.Entites.Author;
import com.example.Library.ManageMent.System.Entites.Book;
import com.example.Library.ManageMent.System.Enum.Genre;
import com.example.Library.ManageMent.System.Exception.AuthorNotFoundException;
import com.example.Library.ManageMent.System.Repository.AuthorRepository;
import com.example.Library.ManageMent.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    public String addBook(Book book ,Integer authorId) throws Exception{

        Optional<Author>optionalAuthor = authorRepository.findById(authorId);

        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Author Id Entered is invalid");
        }
        Author author = optionalAuthor.get();

        book.setAuthor(author);

        author.getBookList().add(book);


        authorRepository.save(author);

        return "Book has been added to the  Db";


    }

    public List<String> getBooksByGenre(Genre genre){

        List<Book>bookList = bookRepository.findBooksByGenre(genre);
        List<String>bookNames =new ArrayList<>();

        for(Book book: bookList){
            bookNames.add((book.getBookName()));
        }
        return bookNames;
    }


}


















