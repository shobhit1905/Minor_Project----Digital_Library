package org.project.library.Repository;

import org.project.library.Model.Author;
import org.project.library.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b JOIN Author a ON b.author.authorId = a.authorId WHERE a.authorName = ?1")
    List<Book> findAllBooksByAuthor(String authorName);

    @Query("SELECT b FROM Book b WHERE b.bookGenre = ?1")
    List<Book> findAllByGenre(String genre);

}
