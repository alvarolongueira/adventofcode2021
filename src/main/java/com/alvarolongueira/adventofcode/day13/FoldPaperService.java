package com.alvarolongueira.adventofcode.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;

public class FoldPaperService {

    private final String file;

    private List<FoldPaperPosition> positions = new ArrayList<>();
    private List<FoldInstruction> instructions = new ArrayList<>();

    public FoldPaperService(String file) {
        this.file = file;
        this.prepare();
    }

    public int calculate(int amount) {
        Map<FoldPaperPosition, Integer> map = new HashMap<>();
        int i = 0;

        this.positions.stream().forEach(value -> map.put(value, 1));
        this.paintMap(map);

        List<FoldPaperPosition> newPositions = this.positions;

        for (FoldInstruction instruction : this.instructions) {
            if (i >= amount) {
                break;
            }

            newPositions = this.foldByInstruction(instruction, newPositions);
            map.clear();
            newPositions.stream().forEach(value -> map.put(value, 1));
            this.paintMap(map);
            i++;
        }

        return map.values().stream().mapToInt(value -> value).sum();
    }

    private List<FoldPaperPosition> foldByInstruction(FoldInstruction instruction, List<FoldPaperPosition> positions) {
        List<FoldPaperPosition> list = new ArrayList<>();
        int index = instruction.getValue();

        for (FoldPaperPosition current : positions) {
            Optional<FoldPaperPosition> newPosition = this.buildNewPosition(index, instruction.isDirectionX(), current);
            if (newPosition.isPresent()) {
                list.add(newPosition.get());
            }
        }

        return list;
    }

    private Optional<FoldPaperPosition> buildNewPosition(int index, boolean isDirectionX, FoldPaperPosition current) {

        int currentValue = current.getY();
        if (isDirectionX) {
            currentValue = current.getX();
        }

        if (index > currentValue) {
            return Optional.of(current);
        }
        if (index == currentValue) {
            return Optional.empty();
        }

        int newValue = index - (currentValue - index);
        int x = current.getX();
        int y = current.getY();

        if (isDirectionX) {
            x = newValue;
        } else {
            y = newValue;
        }

        return Optional.of(FoldPaperPosition.of(x, y));
    }

    private void paintMap(Map<FoldPaperPosition, Integer> map) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        int maxX = map.keySet().stream().max((position1, position2) -> Integer.compare(position1.getX(), position2.getX())).get().getX();
        int maxY = map.keySet().stream().max((position1, position2) -> Integer.compare(position1.getY(), position2.getY())).get().getY();

        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                FoldPaperPosition position = FoldPaperPosition.of(x, y);
                String value = map.getOrDefault(position, 0) > 0 ? "#" : ".";
                System.out.print(value);
            }
            System.out.println();
        }
    }

    private void prepare() {
        List<FoldPaperPosition> listPositions = new ArrayList<>();
        List<FoldInstruction> listInstructions = new ArrayList<>();

        FileCustomUtils reader = new FileCustomUtils(this.file);

        List<String> lines = reader.readAllNoLineBreaks();
        for (String line : lines) {
            if (line.contains("fold")) {
                List<String> current = ListCustomUtils.split(line, "=");
                String code = current.get(0).contains("x") ? "x" : "y";
                int value = Integer.valueOf(current.get(1));
                FoldInstruction instruction = FoldInstruction.of(code, value);
                listInstructions.add(instruction);

            } else {
                List<Integer> current = ListCustomUtils.convertToIntSplitting(line, ",");
                FoldPaperPosition position = FoldPaperPosition.of(current.get(0), current.get(1));
                listPositions.add(position);
            }
        }

        this.positions = listPositions;
        this.instructions = listInstructions;
    }

}
