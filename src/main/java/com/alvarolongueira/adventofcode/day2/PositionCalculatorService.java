package com.alvarolongueira.adventofcode.day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alvarolongueira.adventofcode.common.file.FileReaderUtils;
import com.alvarolongueira.adventofcode.day2.domain.Action;
import com.alvarolongueira.adventofcode.day2.domain.Movement;
import com.alvarolongueira.adventofcode.day2.domain.SubmarinePosition;

public class PositionCalculatorService {

    private final String file;

    public PositionCalculatorService(String file) {
        this.file = file;
    }

    public SubmarinePosition move(SubmarinePosition submarinePosition) {
        List<Action> actions = this.read();
        submarinePosition.move(actions);
        return submarinePosition;
    }

    private List<Action> read() {
        FileReaderUtils reader = new FileReaderUtils(this.file);
        return reader.readAllNoLineBreaks().stream()
                .map((line) -> this.getAction(line))
                .collect(Collectors.toList());
    }

    private Action getAction(String line) {
        List<String> elements = Arrays.asList(line.split(" "));
        Movement movement = Movement.get(elements.get(0));
        int times = Integer.valueOf(elements.get(1));
        return new Action(movement, times);
    }
}
