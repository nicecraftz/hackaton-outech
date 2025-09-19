package it.alessandrocalista.hackaton.repository;

import it.alessandrocalista.hackaton.entity.Heritage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeritageRepository extends JpaRepository<Heritage, Long> {

}
