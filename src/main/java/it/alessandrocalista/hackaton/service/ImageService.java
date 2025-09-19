package it.alessandrocalista.hackaton.service;

import it.alessandrocalista.hackaton.entity.Heritage;
import it.alessandrocalista.hackaton.entity.HeritageImage;
import it.alessandrocalista.hackaton.payload.HeritageImagePayload;
import it.alessandrocalista.hackaton.repository.ImageRepository;
import it.alessandrocalista.hackaton.util.StoredFileResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImageMetadata(Long heritageId, StoredFileResult result, HeritageImagePayload.ImportPart metadata) {
        HeritageImage image = new HeritageImage();
        image.setHeritage(new Heritage(heritageId));

        image.setAuthor(metadata.author());
        image.setDate(metadata.date());
        image.setCopyright(metadata.copyright());
        image.setLicense(metadata.license());
        image.setSource(metadata.source());
        image.setDateAccessed(metadata.dateAccessed());
        image.setLink(metadata.link());
        image.setFilePath(result.storedPath().toFile().getName());
        image.setHash(result.computedHash());
        imageRepository.save(image);
    }
}
