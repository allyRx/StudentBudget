package org.allyrx.studentbudget.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String motif;
    private String month;
    private String description;
    private Long amount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "budget" ,cascade = CascadeType.ALL)
    private List<Depense> depense;
}
