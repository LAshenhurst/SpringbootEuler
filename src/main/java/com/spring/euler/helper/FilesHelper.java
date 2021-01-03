package com.spring.euler.helper;

import com.spring.euler.common.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public final class FilesHelper {
    public static File getResourceFile(String path) {
        String filePath = ClassLoader.getSystemResource(path).getFile();
        return getFile(filePath);
    }

    public static File getFile(String path){
        File file = new File(path);
        if (file.exists()) { return file; }
        else {
            String errorMessage = "File not found at: " + path;
            throw new ApiException(HttpStatus.NOT_FOUND, errorMessage);
        }
    }

    public static List<String> readAllLines(String path) {
        File file = getFile(path);
        return readAllLines(file);
    }

    public static List<String> readAllLines(File file) {
        try { return Files.readAllLines(file.toPath()); }
        catch (IOException e) { throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to read from file"); }
    }
}