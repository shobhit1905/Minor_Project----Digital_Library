package org.project.library.Controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.project.library.Dto.DeleteAuthorDTO;
import org.project.library.Dto.UpdateAuthorDTO;
import org.project.library.Exceptions.BadRequestException;
import org.project.library.Model.Author;
import org.project.library.Repository.AuthorRepository;
import org.project.library.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@Slf4j
@RequestMapping(value = "/v1/api/authors" , produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    @Autowired
    private AuthorService authorService;

//    @Autowired
//    private AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<String> addNewAuthor(@RequestBody @Valid Author author)
    {
        Author author1 = authorService.addNewAuthor(author) ;
        if(author1 != null)
        {
            return new ResponseEntity<>(String.format("Details for Author : %s inserted successfully" , author1.getAuthorName()), HttpStatus.CREATED) ;
        }
        else
        {
            log.error("Error while adding new author");
            return new ResponseEntity<>("Error while adding new author" , HttpStatus.BAD_REQUEST) ;
        }


    }

    @GetMapping("/{authorName}")
    public Author fetchAuthorByName(@PathVariable("authorName") String authorName)
    {
        if(authorName.equals("null") || authorName.isBlank())
            throw new BadRequestException("Author name should not be blank or null") ;

        return authorService.fetchDetailsByAuthorName(authorName) ;


    }

//    @GetMapping("/usingParam")
//    public Author fetchAuthorByNameUSingParam(@RequestParam("authorName") String authorName)
//    {
//        if(authorName.equals("null") || authorName.isBlank())
//            throw new BadRequestException("Author name should not be blank or null") ;
//
//        return authorService.fetchDetailsByAuthorName(authorName) ;
//    }

    @GetMapping
    public ResponseEntity<List<Author>> fetchDetailsOfAllAuthors()
    {
        if(authorService.fetchDetailsOfAllAuthors() == null)
        return new ResponseEntity<>(authorService.fetchDetailsOfAllAuthors() , HttpStatus.OK) ;

        else {
            log.error("Error while fetching details of all Authors");
            return null ;
        }
    }

    @PutMapping
    public ResponseEntity<String> updateAuthorAddress(@RequestBody @Valid UpdateAuthorDTO updateAuthorDTO)
    {
        Author author = authorService.updateAuthorAddress(updateAuthorDTO);

        if(author != null)
        {
            return new ResponseEntity<>(String.format("Address for author id : %d , successfully updated" , updateAuthorDTO.getAuthorId()) , HttpStatus.OK) ;
        }
        else
        {
            log.error("Error while updating author address");
            return new ResponseEntity<>("Error while updating author address" , HttpStatus.BAD_REQUEST) ;
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAuthor(@RequestBody @Valid DeleteAuthorDTO deleteAuthorDTO)
    {
        String ans = authorService.deleteAuthor(deleteAuthorDTO) ;
        if(ans != null)
            return new ResponseEntity<>(ans, HttpStatus.OK) ;
        else
            return new ResponseEntity<>("Error while deleting author" , HttpStatus.BAD_REQUEST) ;
    }


}
