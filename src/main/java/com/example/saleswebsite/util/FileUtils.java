package com.example.saleswebsite.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static Path saveFile(Path destDir, String filename, byte[] content) throws IOException {
        Files.createDirectories(destDir);
        Path dest = destDir.resolve(filename);
        Files.write(dest, content);
        log.info("Saved file {}", dest);
        return dest;
    }

    public static void deleteQuietly(Path path) {
        try {
            if (path != null && Files.exists(path)) {
                Files.delete(path);
                log.info("Deleted file {}", path);
            }
        } catch (IOException ex) {
            log.error("Error deleting file {}", path, ex);
        }
    }

    public static void rollbackUploadedFiles(List<Path> uploaded) {
        if (uploaded == null)
            return;
        for (Path p : uploaded) {
            deleteQuietly(p);
        }
    }
}
