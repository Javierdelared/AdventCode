package com.advent.code.utils;

import com.advent.code.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Component
public class LineReader {
    public String basePath = "src/main/resources/";
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public List<String> readLines(String fileName) {
        try (Stream<String> lines = Files.lines(Paths.get(basePath, fileName))) {
            return lines.toList();
        } catch (IOException e) {
            throw new ServiceException("File not found", e);
        }
    }
}
