package org.allyrx.studentbudget.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean enabled;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
}
