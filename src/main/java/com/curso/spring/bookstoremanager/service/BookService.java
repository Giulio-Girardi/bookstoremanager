package com.curso.spring.bookstoremanager.service;

import com.curso.spring.bookstoremanager.dto.BookDTO;
import com.curso.spring.bookstoremanager.dto.MessageResponseDTO;
import com.curso.spring.bookstoremanager.entity.Book;
import com.curso.spring.bookstoremanager.mapper.BookMapper;
import com.curso.spring.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO){
        Book bookToSave = bookMapper.toModel(bookDTO);

        Book savedBook = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created by ID " + savedBook.getId())
                .build();
    }

    public BookDTO findById(long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        return bookMapper.toDTO(optionalBook.get());
    }
}
