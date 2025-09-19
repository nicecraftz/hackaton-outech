package it.alessandrocalista.hackaton.service;

import it.alessandrocalista.hackaton.repository.ImageRepository;
import it.alessandrocalista.hackaton.util.FileHashUtil;
import it.alessandrocalista.hackaton.util.Result;
import it.alessandrocalista.hackaton.util.StoredFileResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static it.alessandrocalista.hackaton.Constant.STORE_DIR;

@Slf4j
@Service
public class StorageService {
    private static final Long MINIMUM_STORAGE_REQUIREMENT = 5 * 1024 * 1024 * 1024L;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private final ImageRepository imageRepository;

    public StorageService(ImageRepository imageRepository) {
        if (!STORE_DIR.exists()) STORE_DIR.mkdirs();
        this.imageRepository = imageRepository;
    }

    public boolean hasEnoughStorage() {
        return STORE_DIR.getFreeSpace() >= MINIMUM_STORAGE_REQUIREMENT;
    }

    public Result<StoredFileResult> saveImage(MultipartFile file) {


        try {
            String hash = FileHashUtil.calculateSHA256(file);
            boolean alreadyExists = imageRepository.existsByHash(hash);
            if(alreadyExists) {
                return new Result.Error<>("File already exists");
            }

            if (file.isEmpty()) {
                return new Result.Error<>("File is empty");
            }

            if (file.getSize() > MAX_FILE_SIZE) {
                return new Result.Error<>("File size exceeds maximum allowed size (10MB)");
            }

            if (!hasEnoughStorage()) {
                return new Result.Error<>("Insufficient storage space");
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                return new Result.Error<>("Invalid filename");
            }

            String fileExtension = getFileExtension(originalFilename);
            if (!fileExtension.equalsIgnoreCase(".jpg")) {
                return new Result.Error<>("Invalid file extension, only images are supported");
            }

            String uniqueFilename = UUID.randomUUID() + fileExtension;

            Path targetPath = Paths.get(STORE_DIR.getAbsolutePath(), uniqueFilename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            log.info("Stored new image correctly!");
            return new Result.Ok<>(new  StoredFileResult(targetPath, hash));
        } catch (IOException e) {
            log.error("Failed to save image: {}", e.getMessage(), e);
            return new Result.Error<>("Failed to save file: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error while saving image: {}", e.getMessage(), e);
            return new Result.Error<>("Unexpected error occurred");
        }
    }

    private boolean isValidImageType(String contentType) {
        return contentType != null && (
                contentType.equals("image/jpeg") ||
                        contentType.equals("image/jpg") ||
                        contentType.equals("image/png") ||
                        contentType.equals("image/gif") ||
                        contentType.equals("image/webp")
        );
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex);
    }

    public String getRelativePath(Path fullPath) {
        return STORE_DIR.toPath().relativize(fullPath).toString();
    }
}