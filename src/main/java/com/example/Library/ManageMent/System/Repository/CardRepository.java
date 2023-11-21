package com.example.Library.ManageMent.System.Repository;

import com.example.Library.ManageMent.System.Entites.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository  extends JpaRepository<LibraryCard,Integer> {

}
