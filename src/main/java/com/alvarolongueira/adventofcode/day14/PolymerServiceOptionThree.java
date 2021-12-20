package com.alvarolongueira.adventofcode.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alvarolongueira.adventofcode.common.FileCustomUtils;
import com.alvarolongueira.adventofcode.common.ListCustomUtils;

public class PolymerServiceOptionThree {

    private final String file;
    private Map<PairPolymer, String> conversor = new HashMap<>();
    private Map<PairPolymer, Long> template = new HashMap<>();
    private String last = "";

    public PolymerServiceOptionThree(String file) {
        this.file = file;
    }

    public Map<String, Long> calculate(int amount) {
        this.prepare();

        Map<PairPolymer, Long> newMap = new HashMap<>(this.template);

        for (int i = 0; i < amount; i++) {
            Map<PairPolymer, Long> tempMap = new HashMap<>();

            for (Map.Entry<PairPolymer, Long> entry : newMap.entrySet()) {
                String newLetter = this.conversor.get(entry.getKey());
                long value = entry.getValue();
                PairPolymer left = PairPolymer.of(entry.getKey().left(), newLetter);
                PairPolymer right = PairPolymer.of(newLetter, entry.getKey().right());
                this.addOneToMap(tempMap, left, value);
                this.addOneToMap(tempMap, right, value);
            }
            newMap = new HashMap<>(tempMap);
        }

        Map<String, Long> mapByLetters = new HashMap<>();
        mapByLetters.put(this.last, 1L);

        for (Map.Entry<PairPolymer, Long> entry : newMap.entrySet()) {

            String letter = entry.getKey().left();
            long leftValue = mapByLetters.getOrDefault(letter, 0L);
            leftValue += entry.getValue();
            mapByLetters.put(letter, leftValue);
        }

        return mapByLetters;
    }

    private void addOneToMap(Map<PairPolymer, Long> map, PairPolymer current, long times) {
        long value = map.getOrDefault(current, 0L);
        value += times;
        map.put(current, value);
    }

    public long calculateResult(Map<String, Long> polymerMap) {
        long max = polymerMap.values().stream().max(Long::compareTo).get();
        long min = polymerMap.values().stream().min(Long::compareTo).get();
        return max - min;
    }

    private void prepare() {
        this.conversor.clear();
        this.template.clear();

        FileCustomUtils reader = new FileCustomUtils(this.file);
        List<String> firstLine = ListCustomUtils.split(reader.readLine().get(), "");

        for (int i = 0; i < firstLine.size() - 1; i++) {
            String current = firstLine.get(i);
            String next = firstLine.get(i + 1);

            PairPolymer currentPair = PairPolymer.of(current, next);
            long value = this.template.getOrDefault(currentPair, 0L);
            value++;

            this.template.put(currentPair, value);

            this.last = next;
        }

        List<String> lines = reader.readAllNoLineBreaks();
        for (String line : lines) {
            List<String> letters = ListCustomUtils.split(line.replaceAll(" -> ", ""), "");
            this.conversor.put(PairPolymer.of(letters.get(0), letters.get(1)), letters.get(2));
        }

    }

}
