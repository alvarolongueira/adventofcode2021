package com.alvarolongueira.adventofcode.day1;

import com.alvarolongueira.adventofcode.day1.service.IncreasedCounterService;

public class IncreasedCounterMain {

    private final static String filePath = "com/alvarolongueira/adventofcode/day1/input.txt";

    public static void main(String[] args) {
        IncreasedCounterService service = new IncreasedCounterService(filePath);
        int result = service.calculate();
        System.out.println("Number of times a depth measurement increases " + result);
    }

}
