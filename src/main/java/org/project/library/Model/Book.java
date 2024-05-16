package org.project.library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId ;

    @Column(nullable = false , length = 200)
    private String bookName ;

    @Column(nullable = false)
    private Integer publicationYear ;

    @Column(nullable = false)
    private Float bookPrice ;

    @Column(nullable = false)
    private String bookGenre ;

    @Column(nullable = false , length = 10)
    private String bookEdition ;

    @CreationTimestamp
    private LocalDateTime insertionTime ;

    @UpdateTimestamp
    private LocalDateTime updationTime ;

    // Relationship with author := many books can be written by a single author

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author ;

}
