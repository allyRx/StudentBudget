package org.allyrx.studentbudget.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DepenseRequestDto {

    private Long id;
    private String description;
    private Long montant;
    private LocalDateTime dateSpent;
    private String nameCategory;
    private String email;
    private String motif;
}
