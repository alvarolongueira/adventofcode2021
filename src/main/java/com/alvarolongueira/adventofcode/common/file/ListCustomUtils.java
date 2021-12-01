package com.alvarolongueira.adventofcode.common.file;

import java.util.List;
import java.util.stream.Collectors;

public class ListCustomUtils {

    public static List<Integer> convertToInt(List<String> list) {
        return list.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

}
