package com.advent.code.utils;

import com.advent.code.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);
    public static final String BASE_PATH = "target/classes/";

    public static List<String> lineReader(String fileName) {
        List<String> listOfLines = new ArrayList<>();
        BufferedReader bufReader;
        try {
            bufReader = new BufferedReader(new java.io.FileReader(BASE_PATH + fileName));
        } catch (FileNotFoundException e) {
            throw new ServiceException("File not found", e);
        }
        String line;
        try {
            line = bufReader.readLine();
            while (line != null) {
                listOfLines.add(line);
                line = bufReader.readLine();
                LOGGER.debug("Line: {}", line);
            }
        } catch (IOException e) {
            throw new ServiceException("Error reading line", e);
        }
        return listOfLines;
    }
}
