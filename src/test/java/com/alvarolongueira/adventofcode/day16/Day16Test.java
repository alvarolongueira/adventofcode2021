package com.alvarolongueira.adventofcode.day16;

import org.junit.Assert;
import org.junit.Test;

public class Day16Test {

    private static final String PATH = "com/alvarolongueira/adventofcode/day16/";

    @Test
    public void readFileWebExamplePartOne() {
        DecoderService service = new DecoderService(PATH + "inputTestWebExample.txt");
        service.calculate("8A004A801A8002F478");
        Assert.assertEquals(16, service.getSumVersions());

        service.calculate("620080001611562C8802118E34");
        Assert.assertEquals(12, service.getSumVersions());

        service.calculate("C0015000016115A2E0802F182340");
        Assert.assertEquals(23, service.getSumVersions());

        service.calculate("A0016C880162017C3686B18A3D4780");
        Assert.assertEquals(31, service.getSumVersions());
    }

    @Test
    public void readFileWebExamplePartTwo() {
        DecoderService service = new DecoderService(PATH + "inputTestWebExample.txt");

        long result = service.calculate("C200B40A82");
        Assert.assertEquals(3, result);

        result = service.calculate("04005AC33890");
        Assert.assertEquals(54, result);

        result = service.calculate("880086C3E88112");
        Assert.assertEquals(7, result);

        result = service.calculate("CE00C43D881120");
        Assert.assertEquals(9, result);

        result = service.calculate("D8005AC2A8F0");
        Assert.assertEquals(1, result);

        result = service.calculate("F600BC2D8F");
        Assert.assertEquals(0, result);

        result = service.calculate("9C005AC2F8F0");
        Assert.assertEquals(0, result);

        result = service.calculate("9C0141080250320F1802104A08");
        Assert.assertEquals(1, result);

    }

}
