package org.project.library.Repository;

import org.project.library.Model.Book;
import org.project.library.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findByName(String userName);

    @Query(value = "SELECT b.book_id,b.book_name,b.publication_year,b.book_genre,b.book_price FROM Book b INNER JOIN Books_Issued bi ON b.book_id = bi.book_id WHERE bi.user_id = ?" , nativeQuery = true)
    List<Book> findAllBooksIssuedToUser(Integer id);
}
