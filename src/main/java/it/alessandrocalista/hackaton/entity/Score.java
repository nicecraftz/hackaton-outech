package it.alessandrocalista.hackaton.entity;

import it.alessandrocalista.hackaton.Constant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_scores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private User user;

    @Basic(optional = false)
    private Long points = Constant.ZERO;

    @Column(nullable = false, name = "current_streak")
    private Long currentStreak = Constant.ZERO;
}
