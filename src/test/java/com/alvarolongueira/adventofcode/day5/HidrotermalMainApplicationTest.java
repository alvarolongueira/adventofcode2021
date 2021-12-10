package com.alvarolongueira.adventofcode.day5;

import org.junit.Assert;
import org.junit.Test;

public class HidrotermalMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day5/";

    @Test
    public void readFileWebExample() {
        HidrotermalService service = new HidrotermalService(PATH + "inputTestWebExample.txt");

        long result = service.calculatePointsMoreThanTwo(false);
        Assert.assertEquals(5, result);

    }

    @Test
    public void readFileWebExamplePartTwo() {
        HidrotermalService service = new HidrotermalService(PATH + "inputTestWebExample.txt");

        long result = service.calculatePointsMoreThanTwo(false);
        Assert.assertEquals(5, result);

        long resultWithDiagonal = service.calculatePointsMoreThanTwo(true);
        Assert.assertEquals(12, resultWithDiagonal);
    }
}
