package com.alvarolongueira.adventofcode.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;

public class ChunkAnalyzeService {

    private final String file;

    public ChunkAnalyzeService(String file) {
        this.file = file;
    }

    public ChunkBracketResult calculate() {
        List<String> lines = this.prepare();
        HashMap<Bracket, Integer> mapWithErrors = new HashMap<>();
        List<BracketList> linesMissed = new ArrayList<>();

        for (String line : lines) {
            Optional<Bracket> bracketError = this.lineWithError(line);

            if (bracketError.isPresent()) {
                Bracket bracket = bracketError.get();
                this.addOneToMap(bracket, mapWithErrors);
            } else {
                BracketList brackets = this.lineWithMissed(line);
                linesMissed.add(brackets);
            }
        }

        ChunkBracketResult result = ChunkBracketResult.builder()
                .bracketErrors(mapWithErrors)
                .bracketMissed(linesMissed)
                .build();
        return result;
    }

    private void addOneToMap(Bracket bracket, HashMap<Bracket, Integer> map) {
        int value = 1;

        if (map.containsKey(bracket)) {
            value += map.get(bracket);
        }
        map.put(bracket, value);
    }

    private Optional<Bracket> lineWithError(String line) {
        Optional<Bracket> bracket = Optional.empty();

        String newLine = this.removeBothBrackets(line);

        for (Character currentChar : newLine.toCharArray()) {
            for (Bracket currentBracket : Bracket.values()) {
                if (currentBracket.getCloseChar().equals(currentChar)) {
                    bracket = Optional.of(currentBracket);
                    break;
                }
            }
            if (bracket.isPresent()) {
                break;
            }
        }

        return bracket;
    }

    private BracketList lineWithMissed(String line) {
        List<Bracket> brackets = new ArrayList<>();

        String newLine = this.removeBothBrackets(line);

        for (Character currentChar : newLine.toCharArray()) {
            for (Bracket currentBracket : Bracket.values()) {
                if (currentBracket.getOpenChar().equals(currentChar)) {
                    brackets.add(currentBracket);
                    break;
                }
            }
        }

        Collections.reverse(brackets);
        return BracketList.of(brackets);
    }

    private String removeBothBrackets(String line) {
        int length = line.length();
        String newLine = line;

        do {
            length = newLine.length();

            for (Bracket current : Bracket.values()) {
                newLine = newLine.replaceAll(current.both(), "");
            }

        } while (length > newLine.length());

        return newLine;
    }

    private List<String> prepare() {
        FileCustomUtils reader = new FileCustomUtils(this.file);
        return reader.readAllNoLineBreaks();
    }

}
