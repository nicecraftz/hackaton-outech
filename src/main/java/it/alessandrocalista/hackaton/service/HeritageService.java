package it.alessandrocalista.hackaton.service;

import it.alessandrocalista.hackaton.dto.HeritageDto;
import it.alessandrocalista.hackaton.entity.Category;
import it.alessandrocalista.hackaton.entity.Heritage;
import it.alessandrocalista.hackaton.entity.Location;
import it.alessandrocalista.hackaton.payload.HeritagePayload;
import it.alessandrocalista.hackaton.repository.CategoryRepository;
import it.alessandrocalista.hackaton.repository.HeritageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HeritageService {

    private final HeritageRepository heritageRepository;
    private final CategoryRepository categoryRepository;

    public HeritageService(HeritageRepository heritageRepository, CategoryRepository categoryRepository) {
        this.heritageRepository = heritageRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<HeritageDto> getAll() {
        return heritageRepository.findAll().stream().map(HeritageDto::from).toList();
    }

    public void importHeritages(List<HeritagePayload.ImportPart> entities) {
        List<Heritage> heritages = new ArrayList<>();
        log.debug("Importing Heritages");

        for (HeritagePayload.ImportPart entity : entities) {
            Location location = new Location();
            location.setState(entity.state());
            location.setLatitude(entity.latitude());
            location.setLongitude(entity.longitude());
            location.setRegion(entity.region().getFirst());

            Heritage heritage = new Heritage();
            heritage.setName(entity.name());
            heritage.setDescription(entity.description());
            heritage.setLocation(location);
            heritage.setInscribedDate(entity.inscribedDate());

            String categoryName = entity.category().toLowerCase();
            Category category = categoryRepository.findByName(categoryName)
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(categoryName);
                        return categoryRepository.save(newCategory);
                    });

            log.info("Importing Heritage {}", entity);
            heritage.setCategory(category);
            heritages.add(heritage);
        }

        log.debug("Importing Heritages {}", heritages);
        heritageRepository.saveAll(heritages);
    }

    public HeritageDto get(Long heritageId) {
        return heritageRepository.findById(heritageId).map(HeritageDto::from).orElse(null);
    }
}