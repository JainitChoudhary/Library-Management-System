package com.example.Library.ManageMent.System.Repository;

import com.example.Library.ManageMent.System.Entites.Book;
import com.example.Library.ManageMent.System.Entites.LibraryCard;
import com.example.Library.ManageMent.System.Entites.Transaction;
import com.example.Library.ManageMent.System.Enum.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction,Integer> {

       Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transaction);
}
