package com.alvarolongueira.adventofcode.day2.domain;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SubmarinePosition {

    private Position position;

    public SubmarinePosition() {
        this.position = Position.of(0, 0);
    }

    public SubmarinePosition(Position position) {
        this.position = position;
    }

    public void move(List<Action> actions) {
        actions.stream().forEach(action -> this.move(action));
    }

    private void move(Action action) {
        for (int i = 0; i < action.getTimes(); i++) {
            this.position.apply(action.getMovement());
        }
    }

    public int multiply() {
        return this.position.getX() * this.position.getY();
    }

}
