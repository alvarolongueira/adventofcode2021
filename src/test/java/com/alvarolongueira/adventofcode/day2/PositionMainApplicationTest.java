package com.alvarolongueira.adventofcode.day2;

import org.junit.Assert;
import org.junit.Test;

public class PositionMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day2/";

    @Test
    public void readFileTestEmpty() {
        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest1.txt");
        int result = service.calculate();
        Assert.assertEquals(0, result);
    }

    @Test
    public void readFileZeroForward() {
        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest2.txt");
        int result = service.calculate();
        Assert.assertEquals(0, result);
    }

    @Test
    public void readFileZeroVertical() {
        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest3.txt");
        int result = service.calculate();
        Assert.assertEquals(0, result);
    }

    @Test
    public void readFilePreventUnderZero() {
        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest4.txt");
        int result = service.calculate();
        Assert.assertEquals(5, result);
    }

    @Test
    public void readFileMoveNormal() {
        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest4.txt");
        int result = service.calculate();
        Assert.assertEquals(25, result);
    }

    @Test
    public void readFileWebExamen() {
        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTestWebExample.txt");
        int result = service.calculate();
        Assert.assertEquals(150, result);
    }

}
