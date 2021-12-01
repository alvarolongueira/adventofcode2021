package com.alvarolongueira.adventofcode.day1.service;

import java.util.List;

import com.alvarolongueira.adventofcode.common.file.FileReaderUtils;
import com.alvarolongueira.adventofcode.common.file.ListCustomUtils;

public class IncreasedCounterService {

    private final String file;
    private List<Integer> measures;

    public IncreasedCounterService(String file) {
        this.file = file;
    }

    public int calculate(int groupBy) {
        int result = 0;

        this.read();

        if (!this.isValid(groupBy)) {
            return result;
        }

        int previous = this.measures.get(0);
        for (int current : this.measures) {
            result += this.compare(previous, current);
            previous = current;
        }

        return result;
    }

    private boolean isValid(int groupBy) {
        int minElements = groupBy + 1;

        if (this.measures.isEmpty() || this.measures.size() < minElements) {
            return false;
        }
        return true;
    }

    private void read() {
        FileReaderUtils reader = new FileReaderUtils(this.file);
        this.measures = ListCustomUtils.convertToInt(reader.readAllNoLineBreaks());
    }

    private int compare(int first, int second) {
        return (first < second) ? 1 : 0;
    }

}
