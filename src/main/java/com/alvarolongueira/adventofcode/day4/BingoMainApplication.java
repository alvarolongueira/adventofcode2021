package com.alvarolongueira.adventofcode.day4;

import java.rmi.UnexpectedException;

public class BingoMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day4/input.txt";

    public static void main(String[] args) throws UnexpectedException {
        BingoService service = new BingoService(filePath);

        BoardBingo board = service.playBingo();
        System.out.println("Board winner is " + board.toString() + " \nand multiply is " + board.calculate());

    }
}
