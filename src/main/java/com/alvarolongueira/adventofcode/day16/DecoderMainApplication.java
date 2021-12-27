package com.alvarolongueira.adventofcode.day16;

public class DecoderMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day16/input.txt";

    public static void main(String[] args) {
        DecoderService service = new DecoderService(filePath);

        long decimal = service.calculateAndSumVersionsFromFile();
        System.out.println("Versions: " + decimal);

        System.out.println("----------------");

    }
}
