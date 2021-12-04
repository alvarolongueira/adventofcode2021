package com.alvarolongueira.adventofcode.day4;

import java.rmi.UnexpectedException;

public class BingoMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day4/input.txt";

    public static void main(String[] args) throws UnexpectedException {
        BingoService service = new BingoService(filePath);

        BoardBingo boardWinner = service.playBingoToWin(true);
        System.out.println("Board winner is " + boardWinner.toString() + " \nand multiply is " + boardWinner.calculate());

        System.out.println("----------------");

        BoardBingo boardLastWinner = service.playBingoToWin(false);
        System.out.println("Board last winner is " + boardLastWinner.toString() + " \nand multiply is " + boardLastWinner.calculate());

    }
}
