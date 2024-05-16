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
public class UpdateBookDTO {

    @NotNull(message = "Book id should not be null")
    private Integer bookId ;

    @NotNull(message = "Book price should not be null")
    private Float newPrice ;
}
