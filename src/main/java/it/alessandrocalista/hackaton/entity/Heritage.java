package it.alessandrocalista.hackaton.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "heritages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Heritage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "heritage_id")
    private List<HeritageImage> images = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String description;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "inscribed_date")
    private String inscribedDate;

    public Heritage(Long id) {
        this.id = id;
    }

    public boolean hasImages() {
        int count = 0;
        for (HeritageImage heritageImage : images) {
            if (heritageImage.isUploaded()) count++;
        }
        return count > 0;
    }
}
