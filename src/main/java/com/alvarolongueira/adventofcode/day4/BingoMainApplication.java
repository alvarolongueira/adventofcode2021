package com.alvarolongueira.adventofcode.day4;

public class BingoMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day4/input.txt";

    public static void main(String[] args) {
        BingoService service = new BingoService(filePath);

        BoardBingo board = service.playBingo();
        System.out.println("Board winner is " + board.toString() + " and multiply is " + board.multiply());

    }
}
