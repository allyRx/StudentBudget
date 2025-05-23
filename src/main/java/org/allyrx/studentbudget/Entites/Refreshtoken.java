package org.allyrx.studentbudget.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Entity
@Data  @NoArgsConstructor
@AllArgsConstructor
public class Refreshtoken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true , nullable = false)
    private String token;
    @Column(nullable = false)
    private Instant expires;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
