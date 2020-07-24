package com.spring.euler.helper;

import com.spring.euler.common.exception.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Slf4j
public class FilesHelper {
    public static File getResourceFile(String path) {
        String filePath = ClassLoader.getSystemResource(path).getFile();
        return getFile(filePath);
    }

    public static File getFile(String path){
        File file = new File(path);
        if (file.exists()) { return file; }
        else { throw new ApiError(HttpStatus.NOT_FOUND, "File not found"); }
    }

    public static List<String> readAllLines(String path) {
        File file = getFile(path);
        return readAllLines(file);
    }

    public static List<String> readAllLines(File file) {
        try { return Files.readAllLines(file.toPath()); }
        catch (IOException e) { throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to read from file"); }
    }
}
