package com.curso.spring.bookstoremanager.repository;

import com.curso.spring.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
