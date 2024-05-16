package org.project.library.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBookDTO {

    @NotNull(message = "Book ID should not be null")
    private Integer bookId ;

    @NotBlank(message = "Book name should not blank")
    private String bookName ;
}
