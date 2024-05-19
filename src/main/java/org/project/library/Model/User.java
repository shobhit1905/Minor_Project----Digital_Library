package org.project.library.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId ;

    private String userName ;

    private String userMobile ;

    private String userEmail ;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Books_Issued",
            joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "book_Id", referencedColumnName = "bookId")
    )
    List<Book> issuedBooks = new ArrayList<>() ;
}
