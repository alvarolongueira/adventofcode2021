package com.alvarolongueira.adventofcode.day16;

public class DecoderMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day16/input.txt";

    public static void main(String[] args) {
        DecoderService service = new DecoderService(filePath);

        long result = service.calculateFromFile();
        System.out.println("Versions: " + service.getSumVersions());
        System.out.println("Calculate: " + result);

    }
}
