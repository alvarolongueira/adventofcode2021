package com.alvarolongueira.adventofcode.day15;

public class CaveRiskMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day15/input.txt";

    public static void main(String[] args) {
        CaveRiskServiceOptionOne service = new CaveRiskServiceOptionOne(filePath);
        int risk = service.calculate();
        System.out.println("Lowest total risk: " + risk);

        System.out.println("----------------");

        CaveRiskServiceOptionOne serviceQuintuple = new CaveRiskServiceOptionOne(filePath, true);
        int riskQuintuple = serviceQuintuple.calculate();
        System.out.println("Lowest total risk quintuple: " + riskQuintuple);

    }
}
