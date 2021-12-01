package com.alvarolongueira.adventofcode.day1.part1;

import com.alvarolongueira.adventofcode.day1.part1.service.IncreasedCounterService;

public class IncreasedCounterMain {

    private final static String filePath = "adventofcode/day1/part1/input.txt";

    public static void main(String[] args) {
        IncreasedCounterService service = new IncreasedCounterService(filePath);
        int result = service.calculate();
        System.out.println("Number of times a depth measurement increases " + result);
    }

}
