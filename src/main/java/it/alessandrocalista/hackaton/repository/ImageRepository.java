package it.alessandrocalista.hackaton.repository;

import it.alessandrocalista.hackaton.entity.HeritageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<HeritageImage, Long> {

    @Query("SELECT COUNT(i) > 0 FROM HeritageImage i WHERE i.hash = :hash")
    boolean existsByHash(@Param("hash") String hash);
}
