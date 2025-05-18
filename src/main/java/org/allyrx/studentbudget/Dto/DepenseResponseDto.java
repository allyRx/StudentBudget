package org.allyrx.studentbudget.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.allyrx.studentbudget.Entites.Category;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepenseResponseDto {
    private Long id;
    private String description;
    private Long montant;
    private LocalDateTime dateSpent;
    private Category category;
    private Long budgetId;
    private Long amountBudget;
    private String username;
}
