package com.alvarolongueira.adventofcode.day1;

import java.util.ArrayList;
import java.util.List;

import com.alvarolongueira.adventofcode.common.file.FileReaderUtils;
import com.alvarolongueira.adventofcode.common.file.ListCustomUtils;

public class IncreasedCounterService {

    private final String file;

    public IncreasedCounterService(String file) {
        this.file = file;
    }

    public int calculate(int groupBy) {
        int result = 0;

        List<Integer> linesFromFile = this.read();

        if (!this.isValid(groupBy, linesFromFile)) {
            return result;
        }

        List<Integer> measures = this.groupMeasuresBy(groupBy, linesFromFile);

        int previous = measures.get(0);
        for (int current : measures) {
            result += this.compare(previous, current);
            previous = current;
        }

        return result;
    }

    private boolean isValid(int groupBy, List<Integer> linesFromFile) {
        int minElements = groupBy + 1;

        if (linesFromFile.isEmpty() || (linesFromFile.size() < minElements)) {
            return false;
        }
        return true;
    }

    private int compare(int first, int second) {
        return (first < second) ? 1 : 0;
    }

    private List<Integer> groupMeasuresBy(int groupBy, List<Integer> linesFromFile) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < linesFromFile.size(); i++) {
            if (i >= (groupBy - 1)) {
                list.add(this.accumulate(i, groupBy, linesFromFile));
            }
        }

        return list;
    }

    private int accumulate(int index, int groupBy, List<Integer> linesFromFile) {
        int accumulator = 0;
        for (int i = 0; i < groupBy; i++) {
            accumulator += linesFromFile.get(index - i);
        }
        return accumulator;
    }

    private List<Integer> read() {
        FileReaderUtils reader = new FileReaderUtils(this.file);
        return ListCustomUtils.convertToInt(reader.readAllNoLineBreaks());
    }

}
