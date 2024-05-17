package org.project.library.Service;

import lombok.extern.slf4j.Slf4j;
import org.project.library.Dto.DeleteAuthorDTO;
import org.project.library.Dto.UpdateAuthorDTO;
import org.project.library.Exceptions.BadRequestException;
import org.project.library.Exceptions.DataNotFoundException;
import org.project.library.Model.Author;
import org.project.library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository ;

    public Author addNewAuthor(Author author) {
        return authorRepository.save(author) ;

        // save() -> for saving it uses persist
        //        -> for updating it uses merge
    }

    public Author fetchDetailsByAuthorName(String authorName)
    {
        return authorRepository.findByAuthorName(authorName) ;
    }

    public List<Author> fetchDetailsOfAllAuthors()
    {
        return authorRepository.findAll() ;
    }

    public Author fetchDetailsByAuthorId(Integer id)
    {
        return authorRepository.findById(id).get() ;
    }

    public Author updateAuthorAddress(UpdateAuthorDTO updateAuthorDTO)
    {
        Integer id = updateAuthorDTO.getAuthorId() ;
        Author author = authorRepository.findById(id).get() ;

        String newAddress = updateAuthorDTO.getNewAuthorAddress() ;
        author.setAuthorAddress(newAddress);
        return authorRepository.save(author);

    }

    public String deleteAuthor(DeleteAuthorDTO deleteAuthorDTO)
    {
        Integer id = deleteAuthorDTO.getAuthorId() ;
        authorRepository.deleteById(id);
        return "Author deleted" ;
    }
}
