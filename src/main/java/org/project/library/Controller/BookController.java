package org.project.library.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.project.library.Dto.DeleteBookDTO;
import org.project.library.Dto.UpdateBookDTO;
import org.project.library.Exceptions.BadRequestException;
import org.project.library.Exceptions.DataNotFoundException;
import org.project.library.Model.Author;
import org.project.library.Model.Book;
import org.project.library.Repository.BookRepository;
import org.project.library.Service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/books" , produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookService bookService ;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<String> addNewBook(@RequestBody Book book)
    {
        Book b = bookService.addNewbook(book) ;
        if(b != null)
            return new ResponseEntity<>(String.format("Details for BookName : %s , added successfully" , book.getBookName()) , HttpStatus.CREATED) ;
        else
            return new ResponseEntity<>("Cannot add new Book due to some error" , HttpStatus.BAD_REQUEST) ;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> fetchBookById(@PathVariable("bookId") Integer bookId)
    {
        Book b = bookService.fetchBookById(bookId) ;

        if(b != null)
        {
            return new ResponseEntity<>(b, HttpStatus.FOUND) ;
        }
        else
        {
            log.error(String.format("Cannot fin details for book with BookID : %d",bookId)) ;
            return null ;
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> fetchAllBooks()
    {
        List<Book> books = bookService.fetchAllBooks() ;

        if(books != null)
        {
            return new ResponseEntity<>(books, HttpStatus.FOUND) ;
        }
        else
        {
            log.error("Not able to fetch details of all the books") ;
            return null ;
        }
    }

    @GetMapping("/allbooks/{authorName}")
    public ResponseEntity<List<Book>>fetchAllBooksByAuthor(@PathVariable("authorName") @NotBlank @NotNull String authorName)
    {
        if(authorName.equals("null") || authorName.isBlank())
        {
            log.error(String.format("Not able to fetch details of all the books by the author : %s , due to some error",authorName)) ;
            throw new BadRequestException(String.format("Not able to fetch details of all the books by the author : %s\n",authorName));
        }

        List<Book> books = bookService.fetchAllBooksByAuthor(authorName);

        if(books != null)
            return new ResponseEntity<>(books, HttpStatus.FOUND);
        else
        {
            log.error(String.format("No such author ExistS")) ;
            throw new BadRequestException(String.format("No such author ExistS : %s",authorName));
        }
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> fetchAllBooksByGenre(@PathVariable("genre") String genre)
    {
        List<Book> books = bookService.fetchAllBooksByGenre(genre) ;

        if(books != null)
        {
            return new ResponseEntity<>(books , HttpStatus.FOUND) ;
        }
        else
        {
            log.error(String.format("Not able to fetch details of all the books by the genre : %s",genre)) ;
            return null ;
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBook(@RequestBody DeleteBookDTO deleteBookDTO)
    {
        String deleted =  bookService.deleteBook(deleteBookDTO) ;

        if(deleted != null)
            return new ResponseEntity<>(deleted, HttpStatus.OK) ;
        else
        {
            log.error(String.format("Details for BookId : %d and BookName : %s cannot be deleted" , deleteBookDTO.getBookId(),deleteBookDTO.getBookName()));
            throw new DataNotFoundException("Cannot find the book , invalid book id") ;
        }
    }

    @PutMapping
    public ResponseEntity<String> updateBookPrice(@RequestBody @Valid UpdateBookDTO updateBookDTO)
    {
        try {
            Book b = bookService.updateBookPrice(updateBookDTO) ;
            if(b != null)
                return new ResponseEntity<>(String.format("Price updated for bookId : %d",updateBookDTO.getBookId()), HttpStatus.OK) ;
        }
        catch(RuntimeException e)
        {
            log.error("Invalid book Id provided , unable to update the price of the book") ;
            return new ResponseEntity<>("Not able to find the book with the given id" , HttpStatus.NOT_FOUND) ;
        }

        return null ;
    }
}
