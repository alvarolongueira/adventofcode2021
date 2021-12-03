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
public class EnergyRate {

    private String gammaRate = "";
    private String epsilonRate = "";
    private long gammaRateDecimal = 0;
    private long epsilonRateDecimal = 0;

    public long multiply() {
        return this.getGammaRateDecimal() * this.getEpsilonRateDecimal();
    }
}
