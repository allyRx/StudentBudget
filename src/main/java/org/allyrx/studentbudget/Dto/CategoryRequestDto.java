package org.allyrx.studentbudget.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryRequestDto {

    private Long id;
    @NotBlank(message = "Le nom du category ne pas etre vide")
    private String name;
}
