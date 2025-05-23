package org.allyrx.studentbudget.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data @NoArgsConstructor @AllArgsConstructor
public class BudgetRequestDto {

    @NotBlank(message = "Le motif ne doit pas etre vide")
    private String motif;
    @NotBlank(message = "Le motif ne doit pas etre vide" )
    private String month;
    private String description;
    @NotNull
    private Long amount;
    @NotNull(message = "L'identifiant de l'utilisateur est requis")
    private String email;
    private LocalDateTime createdAt;
}
