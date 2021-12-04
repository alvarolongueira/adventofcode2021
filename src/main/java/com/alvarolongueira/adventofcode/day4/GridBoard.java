package com.alvarolongueira.adventofcode.day4;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter

@ToString
public class GridBoard {

    private int x;
    private int y;

    public static GridBoard of(int x, int y) {
        return new GridBoard(x, y);
    }

    @Override
    public boolean equals(Object o) {
        GridBoard other = (GridBoard) o;
        return (this.getX() == other.getX()) && (this.getY() == other.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
