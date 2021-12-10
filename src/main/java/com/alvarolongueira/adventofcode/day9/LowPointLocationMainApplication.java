package com.alvarolongueira.adventofcode.day9;

public class LowPointLocationMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day9/input.txt";

    public static void main(String[] args) {
        LowPointLocationService service = new LowPointLocationService(filePath);

        long riskLevel = service.calculate();
        System.out.println("Risk levels amount: " + riskLevel);

        System.out.println("----------------");

        long basinsSizes = service.calculateBasins();
        System.out.println("Basins amount: " + basinsSizes);

    }
}
