package org.allyrx.studentbudget.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class BudgetResponseDto {
    private Long id;
    private String motif;
    private String month;
    private String description;
    private Long amount;
    private Long userId;
    private String username;
    private String email;
}
