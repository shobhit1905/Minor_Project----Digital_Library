package org.project.library.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Author {

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Author name should not be blank")
    private String authorName ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId ;

    @Column(nullable = true)
    @NotBlank(message = "Author address should not be blank")
    private String authorAddress ;

    // Relationship := one author can have multiple books

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "author")
    @JsonBackReference
    private List<Book> booksList = new ArrayList<>();
}
