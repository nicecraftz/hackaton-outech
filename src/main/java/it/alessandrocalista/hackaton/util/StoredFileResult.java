package it.alessandrocalista.hackaton.util;

import java.nio.file.Path;

public record StoredFileResult(
        Path storedPath,
        String computedHash
) {
}
