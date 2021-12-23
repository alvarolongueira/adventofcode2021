package com.alvarolongueira.adventofcode.day15;

import org.junit.Assert;
import org.junit.Test;

public class CaveRiskMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day15/";

    // OPTION ONE

    @Test
    public void readFileWebExampleOptionOne() {
        CaveRiskServiceOptionOne service = new CaveRiskServiceOptionOne(PATH + "inputTestWebExample.txt");
        int result = service.calculate();
        Assert.assertEquals(40, result);
    }

    @Test
    public void readFileWebExampleOptionOnePartTwo() {
        CaveRiskServiceOptionOne service = new CaveRiskServiceOptionOne(PATH + "inputTestWebExample.txt", true);
        int result = service.calculate();
        Assert.assertEquals(315, result);
    }

    @Test
    public void readFileWebExampleOptionOneFinal() {
        CaveRiskServiceOptionOne service = new CaveRiskServiceOptionOne(PATH + "input.txt");
        int result = service.calculate();
        Assert.assertEquals(602, result);
    }

    @Test
    public void readFileWebExampleOptionOneFinalPartTwo() {
        CaveRiskServiceOptionOne service = new CaveRiskServiceOptionOne(PATH + "input.txt", true);
        int result = service.calculate();
        Assert.assertEquals(0, result);
    }

    // OPTION TWO

//    @Test
//    public void readFileWebExampleOptionTwo() {
//        CaveRiskServiceOptionTwo service = new CaveRiskServiceOptionTwo(PATH + "inputTestWebExample.txt");
//        int result = service.calculate();
//        Assert.assertEquals(40, result);
//    }
//
//    @Test
//    public void readFileWebExampleOptionTwoPartTwo() {
//        CaveRiskServiceOptionTwo service = new CaveRiskServiceOptionTwo(PATH + "inputTestWebExample.txt", true);
//        int result = service.calculate();
//        Assert.assertEquals(315, result);
//    }
//
//    @Test
//    public void readFileWebExampleOptionTwoFinal() {
//        CaveRiskServiceOptionTwo service = new CaveRiskServiceOptionTwo(PATH + "input.txt");
//        int result = service.calculate();
//        Assert.assertEquals(602, result);
//    }
//
@Test
public void readFileWebExampleOptionTwoFinalPartTwo() {
    CaveRiskServiceOptionTwo service = new CaveRiskServiceOptionTwo(PATH + "input.txt", true);
    int result = service.calculate();
    Assert.assertEquals(0, result);
}

    // OPTION THREE
//
//    @Test
//    public void readFileWebExampleOptionThree() {
//        CaveRiskServiceOptionThree service = new CaveRiskServiceOptionThree(PATH + "inputTestWebExample.txt");
//        int result = service.calculate();
//        Assert.assertEquals(40, result);
//    }
//
//    @Test
//    public void readFileWebExampleOptionThreePartTwo() {
//        CaveRiskServiceOptionThree service = new CaveRiskServiceOptionThree(PATH + "inputTestWebExample.txt", true);
//        int result = service.calculate();
//        Assert.assertEquals(315, result);
//    }
//
//    @Test
//    public void readFileWebExampleOptionThreeFinal() {
//        CaveRiskServiceOptionThree service = new CaveRiskServiceOptionThree(PATH + "input.txt");
//        int result = service.calculate();
//        Assert.assertEquals(602, result);
//    }
//
//    @Test
//    public void readFileWebExampleOptionThreeFinalPartTwo() {
//        CaveRiskServiceOptionThree service = new CaveRiskServiceOptionThree(PATH + "input.txt", true);
//        int result = service.calculate();
//        Assert.assertEquals(0, result);
//    }

}
