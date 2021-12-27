package com.alvarolongueira.adventofcode.day16;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day16Test {

    private static final String PATH = "com/alvarolongueira/adventofcode/day16/";

    @Test
    public void exampleOne() {
        DecoderService service = new DecoderService(PATH + "inputTestWebExample.txt");
        List<String> decimalResult = service.calculate("D2FE28");
        Assert.assertEquals(ImmutableList.of("2021"), decimalResult);
    }

    @Test
    public void exampleTwo() {
        DecoderService service = new DecoderService(PATH + "inputTestWebExample.txt");
        List<String> decimalResult = service.calculate("38006F45291200");
        Assert.assertEquals(ImmutableList.of("10", "20"), decimalResult);
    }

    @Test
    public void exampleThree() {
        DecoderService service = new DecoderService(PATH + "inputTestWebExample.txt");
        List<String> decimalResult = service.calculate("EE00D40C823060");
        Assert.assertEquals(ImmutableList.of("1", "2", "3"), decimalResult);
    }

    @Test
    public void exampleFour() {
        DecoderService service = new DecoderService(PATH + "inputTestWebExample.txt");
        long result = service.calculateAndSumVersions("8A004A801A8002F478");
        Assert.assertEquals(16, result);

        result = service.calculateAndSumVersions("620080001611562C8802118E34");
        Assert.assertEquals(12, result);

        result = service.calculateAndSumVersions("C0015000016115A2E0802F182340");
        Assert.assertEquals(23, result);

        result = service.calculateAndSumVersions("A0016C880162017C3686B18A3D4780");
        Assert.assertEquals(31, result);
    }


    @Test
    public void readFileWebExampleOptionOne() {
        DecoderService service = new DecoderService(PATH + "inputTestWebExample.txt");
        List<String> decimalResult = service.calculate("C200B40A82");
        Assert.assertEquals(0, decimalResult);
    }

    @Test
    public void readFileWebExampleOptionOnePartTwo() {
//        Service service = new Service(PATH + "inputTestWebExample.txt");
//        int result = service.calculate();
//        Assert.assertEquals(0, result);
    }

}
