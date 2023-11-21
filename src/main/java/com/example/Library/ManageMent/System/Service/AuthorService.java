package com.example.Library.ManageMent.System.Service;

import com.example.Library.ManageMent.System.Entites.Author;
import com.example.Library.ManageMent.System.Entites.Book;
import com.example.Library.ManageMent.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public String addAuthor(Author author){

        authorRepository.save(author);

        return "Author has been added to the Db";
    }
    public List<String> getAllAuthorNames(){
        List<Author>authors=authorRepository.findAll();
        List<String>ans= new ArrayList<>();

        for(Author author:authors){
            ans.add(author.getAuthorName());
        }
        return ans;
    }
    public Author getAuthorById(Integer id) throws Exception {
        Optional<Author>optionalAuthor = authorRepository.findById(id);

        if(!optionalAuthor.isPresent()){
            //Throw an error
            throw new Exception("The id Entered is invalid");
        }
        Author author = optionalAuthor.get();
        return  author;
    }
    public List<String>getBookNames(Integer authorId){
        List<String>bookNames = new ArrayList<>();

        Author author = authorRepository.findById(authorId).get();

        List<Book>bookList = author.getBookList();

        for(Book book:bookList){
            bookNames.add(book.getBookName());
        }
        return bookNames;
    }
}
