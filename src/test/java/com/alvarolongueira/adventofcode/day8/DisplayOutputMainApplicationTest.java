package com.alvarolongueira.adventofcode.day8;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DisplayOutputMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day8/";

    @Test
    public void readFileWebExample() {
        DisplayOutputService service = new DisplayOutputService(PATH + "inputTestWebExample.txt");

        long result = service.calculate();
        Assert.assertEquals(26, result);

    }

    @Test
    public void readFileWebExamplePartTwo() {
        DisplayOutputService service = new DisplayOutputService(PATH + "inputTestWebExample.txt");

        List<DisplayOutputResult> result = service.calculateComplex();

        Assert.assertEquals(8394, result.get(0).value());
        Assert.assertEquals(9781, result.get(1).value());
        Assert.assertEquals(1197, result.get(2).value());
        Assert.assertEquals(9361, result.get(3).value());
        Assert.assertEquals(4873, result.get(4).value());
        Assert.assertEquals(8418, result.get(5).value());
        Assert.assertEquals(4548, result.get(6).value());
        Assert.assertEquals(1625, result.get(7).value());
        Assert.assertEquals(8717, result.get(8).value());
        Assert.assertEquals(4315, result.get(9).value());

        Assert.assertEquals(61229, DisplayOutputResult.sumValues(result));
    }

}
