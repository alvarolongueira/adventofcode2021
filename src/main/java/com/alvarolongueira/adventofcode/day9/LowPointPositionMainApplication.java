package com.alvarolongueira.adventofcode.day9;

public class LowPointPositionMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day9/input.txt";

    public static void main(String[] args) {
        LowPointPositionService service = new LowPointPositionService(filePath);

        long riskLevel = service.calculate();
        System.out.println("Risk levels amount: " + riskLevel);

        System.out.println("----------------");

        long basinsSizes = service.calculateBasins();
        System.out.println("Basins amount: " + basinsSizes);

    }
}
