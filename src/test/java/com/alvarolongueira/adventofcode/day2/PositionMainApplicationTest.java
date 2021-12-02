package com.alvarolongueira.adventofcode.day2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alvarolongueira.adventofcode.day2.domain.Position;
import com.alvarolongueira.adventofcode.day2.domain.SubmarinePosition;

public class PositionMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day2/";

    private SubmarinePosition submarinePosition;

    @Before
    public void setUp() throws Exception {
        this.submarinePosition = new SubmarinePosition();
    }

    private boolean same(SubmarinePosition a, SubmarinePosition b) {
        if (a.getPosition().getX() != a.getPosition().getX()) {
            return false;
        }
        if (a.getPosition().getY() != b.getPosition().getY()) {
            return false;
        }
        return true;
    }

    @Test
    public void readFileTestEmpty() {
        SubmarinePosition expected = new SubmarinePosition(Position.of(0, 0));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest1.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.same(expected, result));
        Assert.assertEquals(0, result.multiply());
    }

    @Test
    public void readFileZeroForward() {
        SubmarinePosition expected = new SubmarinePosition(Position.of(0, 8));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest2.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.same(expected, result));
        Assert.assertEquals(0, result.multiply());
    }

    @Test
    public void readFileZeroVertical() {
        SubmarinePosition expected = new SubmarinePosition(Position.of(8, 0));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest3.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.same(expected, result));
        Assert.assertEquals(0, result.multiply());
    }

    @Test
    public void readFilePreventUnderZero() {
        SubmarinePosition expected = new SubmarinePosition(Position.of(1, 8));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest4.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.same(expected, result));
        Assert.assertEquals(8, result.multiply());
    }

    @Test
    public void readFileMoveNormal() {
        SubmarinePosition expected = new SubmarinePosition(Position.of(5, 5));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest5.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.same(expected, result));
        Assert.assertEquals(25, result.multiply());
    }

    @Test
    public void readFileWebExamen() {
        SubmarinePosition expected = new SubmarinePosition(Position.of(15, 10));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTestWebExample.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.same(expected, result));
        Assert.assertEquals(150, result.multiply());
    }

}
