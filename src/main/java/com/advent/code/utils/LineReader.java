package com.advent.code.utils;

import com.advent.code.exception.ServiceException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LineReader {
    private final String basePath;
    public LineReader(String basePath) {
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
