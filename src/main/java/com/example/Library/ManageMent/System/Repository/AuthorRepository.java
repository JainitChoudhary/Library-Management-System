package com.example.Library.ManageMent.System.Repository;

import com.example.Library.ManageMent.System.Entites.Author;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




import java.util.List;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Integer> {



    List<Author> findAuthorsByAgeGreaterThanEqualAndRatingEquals(Integer age,double rating);












}
