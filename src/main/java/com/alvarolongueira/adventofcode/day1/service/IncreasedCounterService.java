package com.alvarolongueira.adventofcode.day1.service;

import java.util.ArrayList;
import java.util.List;

import com.alvarolongueira.adventofcode.common.file.FileReaderUtils;
import com.alvarolongueira.adventofcode.common.file.ListCustomUtils;

public class IncreasedCounterService {

    private final String file;
    private List<Integer> linesFromFile;

    public IncreasedCounterService(String file) {
        this.file = file;
    }

    public int calculate(int groupBy) {
        int result = 0;

        this.read();

        if (!this.isValid(groupBy)) {
            return result;
        }

        List<Integer> measures = this.groupMeasuresBy(groupBy);

        int previous = measures.get(0);
        for (int current : measures) {
            result += this.compare(previous, current);
            previous = current;
        }

        return result;
    }

    private boolean isValid(int groupBy) {
        int minElements = groupBy + 1;

        if (this.linesFromFile.isEmpty() || (this.linesFromFile.size() < minElements)) {
            return false;
        }
        return true;
    }

    private int compare(int first, int second) {
        return (first < second) ? 1 : 0;
    }

    private List<Integer> groupMeasuresBy(int groupBy) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < this.linesFromFile.size(); i++) {
            if (i >= (groupBy - 1)) {
                list.add(this.accumulate(i, groupBy));
            }
        }

        return list;
    }

    private int accumulate(int index, int groupBy) {
        int accumulator = 0;
        for (int i = 0; i < groupBy; i++) {
            accumulator += this.linesFromFile.get(index - i);
        }
        return accumulator;
    }

    private void read() {
        FileReaderUtils reader = new FileReaderUtils(this.file);
        this.linesFromFile = ListCustomUtils.convertToInt(reader.readAllNoLineBreaks());
    }

}
