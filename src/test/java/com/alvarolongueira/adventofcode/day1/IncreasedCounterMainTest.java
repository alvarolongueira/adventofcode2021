package com.alvarolongueira.adventofcode.day1;

import org.junit.Assert;
import org.junit.Test;

import com.alvarolongueira.adventofcode.day1.service.IncreasedCounterService;

public class IncreasedCounterMainTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day1/";

    @Test
    public void readFileTestEmpty() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest1.txt");
        int result = service.calculate(1);
        Assert.assertEquals(0, result);
    }

    @Test
    public void readFileTestOneUpOneDown() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest2.txt");
        int result = service.calculate(1);
        Assert.assertEquals(1, result);
    }

    @Test
    public void readFileTestTwoUpsOneDown() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest3.txt");
        int result = service.calculate(1);
        Assert.assertEquals(2, result);
    }

    @Test
    public void readFileTestFourUpOneEqualBetween() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest4.txt");
        int result = service.calculate(1);
        Assert.assertEquals(4, result);
    }

    @Test
    public void readFileTestSixUpThreeDown() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest5.txt");
        int result = service.calculate(1);
        Assert.assertEquals(6, result);
    }

    @Test
    public void readFileTestExample() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTestWebExample.txt");
        int result = service.calculate(1);
        Assert.assertEquals(7, result);
    }

    @Test
    public void readFileTestByThreeNoEnough() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest2.txt");
        int result = service.calculate(3);
        Assert.assertEquals(0, result);
    }

    @Test
    public void readFileTestByThreeDoOneUp() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest3.txt");
        int result = service.calculate(3);
        Assert.assertEquals(1, result);
    }

    @Test
    public void readFileTestByThreeDoFourUpNoDown() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest4.txt");
        int result = service.calculate(3);
        Assert.assertEquals(4, result);
    }

    @Test
    public void readFileTestByThreeDoTwoUpFourDown() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTest5.txt");
        int result = service.calculate(3);
        Assert.assertEquals(2, result);

//        10 12 14    36
//        12 14 15    41
//        14 15 10    39
//        15 10 11    36
//        10 11 20    41
//        11 20 1     32
//        20 1 4      25
    }

    @Test
    public void readFileTestByThreeExample() {
        IncreasedCounterService service = new IncreasedCounterService(PATH + "inputTestWebExample.txt");
        int result = service.calculate(3);
        Assert.assertEquals(5, result);
    }
}
