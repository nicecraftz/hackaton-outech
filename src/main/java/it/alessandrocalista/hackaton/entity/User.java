package it.alessandrocalista.hackaton.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String username;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Score score;

    public void setScore(Score score) {
        if (score != null) score.setUser(this);
        this.score = score;
    }

    @PrePersist
    public void prePersist() {
        this.username = username.toLowerCase();
    }
}
