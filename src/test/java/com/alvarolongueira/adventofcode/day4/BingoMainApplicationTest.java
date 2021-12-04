package com.alvarolongueira.adventofcode.day4;

import java.rmi.UnexpectedException;

import org.junit.Assert;
import org.junit.Test;

public class BingoMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day4/";

    @Test
    public void readFileWebExample() throws UnexpectedException {
        BingoService service = new BingoService(PATH + "inputTestWebExample.txt");
        BoardBingo resultBoard = service.playBingo();

        Assert.assertEquals(24, resultBoard.getPrize());
        Assert.assertEquals(4512, resultBoard.calculate());
    }

}
