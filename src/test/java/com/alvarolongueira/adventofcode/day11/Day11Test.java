package com.alvarolongueira.adventofcode.day11;

import org.junit.Assert;
import org.junit.Test;

public class Day11Test {

    private static final String PATH = "com/alvarolongueira/adventofcode/day11/";

    @Test
    public void readFileWebExample() {
        FlashSpreadService service = new FlashSpreadService(PATH + "inputTestWebExample.txt");

        long result10 = service.calculate(10);
        long result100 = service.calculate(100);

        Assert.assertEquals(204, result10);
        Assert.assertEquals(1656, result100);
    }

    @Test
    public void readFileWebExamplePartTwo() {
        FlashSpreadService service = new FlashSpreadService(PATH + "inputTestWebExample.txt");

        long result = service.firstFlash();

        Assert.assertEquals(195, result);
    }

}

