package org.allyrx.studentbudget.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Validation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Instant createdAt;
    private Instant expiredAt;
    private Instant activateAt;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
