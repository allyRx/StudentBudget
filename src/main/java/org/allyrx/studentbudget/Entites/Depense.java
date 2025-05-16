package org.allyrx.studentbudget.Entites;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private  Long montant;
    private LocalDateTime dateSpent;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
}
