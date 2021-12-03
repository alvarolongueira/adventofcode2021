package com.alvarolongueira.adventofcode.day3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LifeSupportRate {

    private String oxygenRate = "";
    private String coscrubberRate = "";
    private long oxygenRateDecimal = 0;
    private long coscrubberRateDecimal = 0;

    public long multiply() {
        return this.getOxygenRateDecimal() * this.getCoscrubberRateDecimal();
    }
}
