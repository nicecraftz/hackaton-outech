package it.alessandrocalista.hackaton.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "heritage_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeritageImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "heritage_id")
    private Heritage heritage;

    @Basic
    private String date;

    @Column(nullable = false, name = "date_accessed")
    private String dateAccessed;

    @Basic(optional = false)
    private String author;

    @Basic(optional = false)
    private String copyright;

    @Basic
    private String source;

    @Basic(optional = false)
    private String link;

    @Basic(optional = false)
    private String license;

    @Basic(optional = false)
    private String hash;

    @Column(name = "file_path")
    private String filePath;

    public boolean isUploaded() {
        return filePath != null;
    }
}
