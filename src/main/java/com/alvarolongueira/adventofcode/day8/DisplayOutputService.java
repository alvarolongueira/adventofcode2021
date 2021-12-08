package com.alvarolongueira.adventofcode.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class DisplayOutputService {

    private final String file;
    private List<DisplayOutput> displayOutputs = new ArrayList<>();

    public DisplayOutputService(String file) {
        this.file = file;
        this.prepare();
    }

    public long calculate() {
        long result = 0L;

        Set<Integer> lengths = ImmutableSet.of(2, 3, 4, 7);

        for (String output : this.displayOutputs.stream().flatMap(current -> current.getOutput().stream()).collect(Collectors.toList())) {
            if (lengths.contains(output.length())) {
                result++;
            }
        }
        return result;
    }

    public List<DisplayOutputResult> calculateComplex() {
        ImmutableList.Builder<DisplayOutputResult> list = ImmutableList.builder();

        for (DisplayOutput displayOutput : this.displayOutputs) {
            Map<String, Integer> converter = DisplayOutputConverter.of(displayOutput).getConverter();

            DisplayOutputResult result = DisplayOutputResult.builder()
                    .displayOutput(displayOutput)
                    .converter(converter)
                    .build();
            list.add(result);
        }
        return list.build();
    }

    private void prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);

        for (String line : reader.readAllNoLineBreaks()) {
            List<String> lineSplit = ListCustomUtils.split(line, "\\|");
            List<String> controlDigits = this.splitAndSort(lineSplit.get(0));
            List<String> output = this.splitAndSort(lineSplit.get(1));
            this.displayOutputs.add(DisplayOutput.of(controlDigits, output));
        }
    }

    private List<String> splitAndSort(String line) {
        List<String> lines = ListCustomUtils.splitSpaces(line);
        List<String> sortLines = ListCustomUtils.sortInternalAlphabetic(lines);
        return sortLines;
    }
}
