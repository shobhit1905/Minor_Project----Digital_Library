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
public class DeleteAuthorDTO {

    @NotNull(message = "Author id should not be null")
    private Integer authorId ;

    @NotBlank(message = "Author name should not blank")
    private String authorName ;
}
