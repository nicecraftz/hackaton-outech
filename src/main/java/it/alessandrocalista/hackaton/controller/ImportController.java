package it.alessandrocalista.hackaton.controller;

import it.alessandrocalista.hackaton.payload.HeritageImagePayload;
import it.alessandrocalista.hackaton.payload.HeritagePayload;
import it.alessandrocalista.hackaton.service.HeritageService;
import it.alessandrocalista.hackaton.service.ImageService;
import it.alessandrocalista.hackaton.service.StorageService;
import it.alessandrocalista.hackaton.util.Result;
import it.alessandrocalista.hackaton.util.StoredFileResult;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/import")
public class ImportController {
    private final HeritageService heritageService;
    private final StorageService storageService;
    private final ImageService imageService;

    public ImportController(HeritageService heritageService, StorageService storageService, ImageService imageService) {
        this.heritageService = heritageService;
        this.storageService = storageService;
        this.imageService = imageService;
    }

    @PostMapping("/heritage")
    public void importHeritage(@RequestBody List<HeritagePayload.@Valid ImportPart> payload) {
        heritageService.importHeritages(payload);
    }

    @PostMapping("/images/publish/{id}")
    public void importImageMultipart(
            @PathVariable("id") Long heritageId,
            @RequestParam("file") MultipartFile file,
            @ModelAttribute @Valid HeritageImagePayload.ImportPart metadata
    ) {
        Result<StoredFileResult> storeResult = storageService.saveImage(file);
        if (storeResult instanceof Result.Ok<StoredFileResult>(StoredFileResult value)) {
            imageService.saveImageMetadata(heritageId, value, metadata);
        }
    }
}
