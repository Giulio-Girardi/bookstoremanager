package com.curso.spring.bookstoremanager.service;

import com.curso.spring.bookstoremanager.dto.BookDTO;
import com.curso.spring.bookstoremanager.entity.Book;
import com.curso.spring.bookstoremanager.exception.BookNotFoundException;
import com.curso.spring.bookstoremanager.repository.BookRepository;
import com.curso.spring.bookstoremanager.utils.BookUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFoundBook = BookUtils.createFakeBook();
        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        Assertions.assertEquals(expectedFoundBook.getName(), bookDTO.getName());
        Assertions.assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
        Assertions.assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
    }

    @Test
    void whenGivenUnexistingIdThenNotFindThrowAnException(){
        var invalidId = 10L;

        when(bookRepository.findById(invalidId))
                .thenReturn(Optional.ofNullable(any(Book.class)));

        assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));
    }

}
