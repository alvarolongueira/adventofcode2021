package com.alvarolongueira.adventofcode.day1.service;

import java.util.List;

import com.alvarolongueira.adventofcode.common.file.FileReaderUtils;
import com.alvarolongueira.adventofcode.common.file.ListCustomUtils;

public class IncreasedCounterService {

    private final String file;

    public IncreasedCounterService(String file) {
        this.file = file;
    }

    public int calculate() {
        int result = 0;

        FileReaderUtils reader = new FileReaderUtils(this.file);
        List<Integer> measures = ListCustomUtils.convertToInt(reader.readAllNoLineBreaks());

        if (measures.isEmpty() || measures.size() < 2) {
            return result;
        }

        int previous = measures.get(0);
        for (int current : measures) {
            result += this.compare(previous, current);
            previous = current;
        }

        return result;
    }

    private int compare(int first, int second) {
        return (first < second) ? 1 : 0;
    }

}
