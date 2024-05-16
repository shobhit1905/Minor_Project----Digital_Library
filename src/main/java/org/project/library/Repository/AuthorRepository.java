package org.project.library.Repository;

import org.project.library.Model.Author ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    public Author findByAuthorName(String authorName) ;

}
