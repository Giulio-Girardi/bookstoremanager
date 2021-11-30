package com.curso.spring.bookstoremanager.service;

import com.curso.spring.bookstoremanager.dto.MessageResponseDTO;
import com.curso.spring.bookstoremanager.entity.Book;
import com.curso.spring.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(Book book){
        Book savedBook = bookRepository.save(book);
        return MessageResponseDTO.builder()
                .message("Livro criado pelo ID " + savedBook.getId())
                .build();
    }
}
