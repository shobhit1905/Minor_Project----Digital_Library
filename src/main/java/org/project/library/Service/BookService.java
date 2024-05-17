package org.project.library.Service;

import org.project.library.Dto.DeleteBookDTO;
import org.project.library.Dto.UpdateBookDTO;
import org.project.library.Model.Author;
import org.project.library.Model.Book;
import org.project.library.Repository.AuthorRepository;
import org.project.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book addNewbook(Book book)
    {
        Author author = book.getAuthor() ;

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public List<Book> fetchAllBooks()
    {
        return bookRepository.findAll() ;
    }

    public List<Book> fetchAllBooksByAuthor(String authorName)
    {
        return bookRepository.findAllBooksByAuthor(authorName) ;
    }

    public List<Book> fetchAllBooksByGenre(String genre)
    {
        return bookRepository.findAllByGenre(genre) ;
    }


    public Book fetchBookById(Integer bookId)
    {
        return bookRepository.findById(bookId).get() ;
    }

    public String deleteBook(DeleteBookDTO object) {

        Integer id = object.getBookId() ;
        Book b = bookRepository.findBookById(id) ;
        if(b != null)
        {
            bookRepository.deleteById(id);
            return (String.format("Details for BookID : %d and BookName : %s , deleted successfully", id, object.getBookName()));
        }
        else
            return null ;
    }

    public Book updateBookPrice(UpdateBookDTO updateBookDTO)
    {
        Book book = bookRepository.findById(updateBookDTO.getBookId()).get() ;
        book.setBookPrice(updateBookDTO.getNewPrice());
        return bookRepository.save(book) ;
    }


}
