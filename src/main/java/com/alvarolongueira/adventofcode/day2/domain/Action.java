package com.alvarolongueira.adventofcode.day2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Action {

    private Movement movement;

    private int times;

}
