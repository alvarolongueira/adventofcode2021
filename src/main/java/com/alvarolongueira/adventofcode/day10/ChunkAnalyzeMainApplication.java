package com.alvarolongueira.adventofcode.day10;

public class ChunkAnalyzeMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day10/input.txt";

    public static void main(String[] args) {
        ChunkAnalyzeService service = new ChunkAnalyzeService(filePath);

        ChunkBracketResult result = service.calculate();
        System.out.println("Syntax error score: " + result.sumErrors());

        System.out.println("----------------");

        System.out.println("Syntax score from missed: " + result.scoreFromMissed());

    }
}
