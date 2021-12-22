package com.alvarolongueira.adventofcode.day15;

public class CaveRiskMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day15/input.txt";

    public static void main(String[] args) {
//        CaveRiskServiceOptionTwo service = new CaveRiskServiceOptionTwo(filePath);
//        int risk = service.calculate();
//        System.out.println("Lowest total risk: " + risk);

        System.out.println("----------------");

        CaveRiskServiceOptionTwo serviceQuintuple = new CaveRiskServiceOptionTwo(filePath, true);
        int riskQuintuple = serviceQuintuple.calculate();
        System.out.println("Lowest total risk quintuple: " + riskQuintuple);

    }
}
