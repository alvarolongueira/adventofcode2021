package com.alvarolongueira.adventofcode.day2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alvarolongueira.adventofcode.day2.domain.Position;
import com.alvarolongueira.adventofcode.day2.domain.SubmarinePosition;

public class PositionMainApplicationTest {

    private static final String PATH = "com/alvarolongueira/adventofcode/day2/";

    private SubmarinePosition submarinePosition;
    private SubmarinePosition submarinePositionWithAim;

    @Before
    public void setUp() throws Exception {
        this.submarinePosition = SubmarinePosition.of();
        this.submarinePositionWithAim = SubmarinePosition.ofWithAim();
    }

    private boolean samePosition(SubmarinePosition a, SubmarinePosition b) {
        boolean isTheSamePosition = true;
        if (a.getPosition().getX() != a.getPosition().getX()) {
            isTheSamePosition = false;
        }
        if (a.getPosition().getY() != b.getPosition().getY()) {
            isTheSamePosition = false;
        }

        if (!isTheSamePosition) {
            System.err.println("Expected : " + a);
            System.err.println("Current : " + b);
        }
        return isTheSamePosition;
    }

    @Test
    public void readFileTestEmpty() {
        SubmarinePosition expected = SubmarinePosition.of(Position.of(0, 0));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest1.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.samePosition(expected, result));
        Assert.assertEquals(0, result.multiply());
    }

    @Test
    public void readFileZeroForward() {
        SubmarinePosition expected = SubmarinePosition.of(Position.of(0, 8));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest2.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.samePosition(expected, result));
        Assert.assertEquals(0, result.multiply());
    }

    @Test
    public void readFileZeroVertical() {
        SubmarinePosition expected = SubmarinePosition.of(Position.of(8, 0));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest3.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.samePosition(expected, result));
        Assert.assertEquals(0, result.multiply());
    }

    @Test
    public void readFilePreventUnderZero() {
        SubmarinePosition expected = SubmarinePosition.of(Position.of(1, 8));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest4.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.samePosition(expected, result));
        Assert.assertEquals(8, result.multiply());
    }

    @Test
    public void readFileMoveNormal() {
        SubmarinePosition expected = SubmarinePosition.of(Position.of(5, 5));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTest5.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.samePosition(expected, result));
        Assert.assertEquals(25, result.multiply());
    }

    @Test
    public void readFileWebExample() {
        SubmarinePosition expected = SubmarinePosition.of(Position.of(15, 10));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTestWebExample.txt");
        SubmarinePosition result = service.move(this.submarinePosition);

        Assert.assertTrue(this.samePosition(expected, result));
        Assert.assertEquals(150, result.multiply());
    }

    @Test
    public void readFileWebExampleWithAim() {
        SubmarinePosition expected = SubmarinePosition.ofWithAim(Position.of(15, 60));

        PositionCalculatorService service = new PositionCalculatorService(PATH + "inputTestWebExample.txt");
        SubmarinePosition result = service.move(this.submarinePositionWithAim);

        Assert.assertTrue(this.samePosition(expected, result));
        Assert.assertEquals(900, result.multiply());
    }
}
