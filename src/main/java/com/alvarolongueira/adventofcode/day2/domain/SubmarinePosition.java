package com.alvarolongueira.adventofcode.day2.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SubmarinePosition {

    private Position position;

    public static SubmarinePosition of() {
        return new SubmarinePosition(Position.of(0, 0));
    }

    public static SubmarinePosition ofWithAim() {
        return new SubmarinePosition(Position.ofWithAim(0, 0));
    }

    public static SubmarinePosition of(Position position) {
        return new SubmarinePosition(position);
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
