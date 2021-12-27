package com.alvarolongueira.adventofcode.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListCustomUtils {

    public static List<Integer> convertToInt(List<String> list) {
        return list.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List<Integer> convertToIntSplitting(String line, String split) {
        return split(line, split).stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List<String> sortInternalAlphabetic(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String word : list) {
            char charArray[] = word.toCharArray();
            Arrays.sort(charArray);
            newList.add(String.copyValueOf(charArray));
        }
        return newList;
    }

    public static List<String> split(String line, String split) {
        List<String> newList = new ArrayList<>();
        newList.addAll(Arrays.asList(line.split(split)));
        return newList;
    }

    public static List<String> splitSpaces(String line) {
        List<String> list = Arrays.asList(line.split(" "));
        return list.stream().filter(value -> !value.isEmpty() && !value.equals(" ") && !value.equals("")).collect(Collectors.toList());
    }


}
