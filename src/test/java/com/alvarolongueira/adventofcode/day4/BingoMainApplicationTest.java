package com.alvarolongueira.adventofcode.day4;

import java.rmi.UnexpectedException;

import org.junit.Assert;
import org.junit.Test;

public class BingoMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day4/";

    @Test
    public void readFileWebExamplePartOne() throws UnexpectedException {
        BingoService service = new BingoService(PATH + "inputTestWebExample.txt");

        BoardBingo resultBoardWinner = service.playBingoToWin(true);

        Assert.assertEquals(24, resultBoardWinner.getPrize());
        Assert.assertEquals(4512, resultBoardWinner.calculate());

    }

    @Test
    public void readFileWebExamplePartTwo() throws UnexpectedException {
        BingoService service = new BingoService(PATH + "inputTestWebExample.txt");

        BoardBingo resultBoardWinner = service.playBingoToWin(true);

        Assert.assertEquals(24, resultBoardWinner.getPrize());
        Assert.assertEquals(4512, resultBoardWinner.calculate());

        BoardBingo resultBoardLastWinner = service.playBingoToWin(false);

        Assert.assertEquals(13, resultBoardLastWinner.getPrize());
        Assert.assertEquals(1924, resultBoardLastWinner.calculate());
    }
}
