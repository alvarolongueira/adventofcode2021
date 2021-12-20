package com.alvarolongueira.adventofcode.day14;

import java.util.Map;

public class PolymerMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day14/input.txt";

    public static void main(String[] args) {
        PolymerServiceOptionThree service = new PolymerServiceOptionThree(filePath);

        Map<String, Long> polymerMap = service.calculate(10);
        long result = service.calculateResult(polymerMap);
        System.out.println("Polymer result: " + result);

        System.out.println("----------------");

        Map<String, Long> polymerMapTwo = service.calculate(40);
        long resultTwo = service.calculateResult(polymerMapTwo);
        System.out.println("Polymer result: " + resultTwo);
//        4844889522565
    }
}
