package com.advent.code;

import com.advent.code.utils.FileParser;
import com.advent.code.utils.StringOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        puzzle1();
        puzzle2();
    }

    public static void puzzle1() {
        int result = 0;
        List<String> lines = FileParser.lineReader("advent_file_1.txt");
        for (String line : lines) {
           int calibrationValue = StringOperator.getCalibrationValue(line);
           result += calibrationValue;
        }
        LOGGER.info("Result puzzle 1: {}", result);
    }

    public static void puzzle2() {
        int result = 0;
        List<String> lines = FileParser.lineReader("advent_file_1.txt");
        for (String line : lines) {
            int calibrationValue = StringOperator.getCalibrationValue2(line);
            result += calibrationValue;
        }
        LOGGER.info("Result puzzle 2: {}", result);
    }
}
