package com.alvarolongueira.adventofcode.day6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lanternfish {

    private int timer = 8;

    public int olderAndHaveChild() {
        this.timer--;
//        if (this.timer == 0) {
//        }
        if (this.timer < 0) {
            this.timer = 6;
            return 1;
        }
        return 0;
    }


    @Override
    public String toString() {
        return String.valueOf(this.timer);
    }
}
