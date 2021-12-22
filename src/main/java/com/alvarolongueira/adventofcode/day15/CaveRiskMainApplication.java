package com.alvarolongueira.adventofcode.day15;

public class CaveRiskMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day15/input.txt";

    public static void main(String[] args) {
        CaveRiskService service = new CaveRiskService(filePath);
        int risk = service.calculate();
        System.out.println("Lowest total risk: " + risk);

        System.out.println("----------------");

        CaveRiskService serviceQuintuple = new CaveRiskService(filePath, true);
        int riskQuintuple = serviceQuintuple.calculate();
        System.out.println("Lowest total risk quintuple: " + riskQuintuple);

    }
}
