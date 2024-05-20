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
public class UpdateUserEmailDTO {

    @NotNull
    private Integer userId ;

    @NotBlank
    private String userName ;

    @NotNull
    private String newEmail ;
}
