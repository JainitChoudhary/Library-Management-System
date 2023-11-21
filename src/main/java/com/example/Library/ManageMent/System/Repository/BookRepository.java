package com.example.Library.ManageMent.System.Repository;

import com.example.Library.ManageMent.System.Entites.Book;
import com.example.Library.ManageMent.System.Enum.Genre;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {

         List<Book> findBooksByGenre(Genre genre);

         Book findBookByBookName(String bookName);



}
