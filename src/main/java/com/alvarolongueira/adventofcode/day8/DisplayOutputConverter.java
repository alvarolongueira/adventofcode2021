package com.alvarolongueira.adventofcode.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;

public class DisplayOutputConverter {

    private final DisplayOutput displayOutput;
    private BiMap<String, Integer> map = HashBiMap.create();
    private List<String> fiveLengthList = new ArrayList<>();
    private List<String> sixLengthList = new ArrayList<>();

    public static DisplayOutputConverter of(DisplayOutput displayOutput) {
        return new DisplayOutputConverter(displayOutput);
    }

    private DisplayOutputConverter(DisplayOutput displayOutput) {
        this.displayOutput = displayOutput;
    }

    public Map<String, Integer> getConverter() {
        this.filterByLength();
        this.filterSixLength();
        this.filterFiveLength();
        return this.map;
    }

    private void filterByLength() {
        for (String current : this.displayOutput.getControlDigits()) {
            switch (current.length()) {
                case 2:
                    this.map.put(current, 1);
                    break;
                case 3:
                    this.map.put(current, 7);
                    break;
                case 4:
                    this.map.put(current, 4);
                    break;
                case 7:
                    this.map.put(current, 8);
                    break;
                case 5:
                    this.fiveLengthList.add(current);
                    break;
                case 6:
                    this.sixLengthList.add(current);
                    break;
                default:
                    throw new IllegalArgumentException("Length not defined: " + current.length());
            }

        }
    }

    private void filterSixLength() {
        Set<Character> charsFour = this.getChars(4);
        Set<Character> charsSeven = this.getChars(7);
        Set<Character> charsFourAndSeven = ImmutableSet.<Character>builder()
                .addAll(charsFour)
                .addAll(charsSeven)
                .build();

        for (String current : ImmutableSet.copyOf(this.sixLengthList)) {
            Set<Character> currentList = this.getChars(current);
            boolean isNine = charsFourAndSeven.stream().allMatch(value -> currentList.contains(value));
            boolean isZero = charsSeven.stream().allMatch(value -> currentList.contains(value));

            if (isNine) {
                this.map.put(current, 9);
            } else if (isZero) {
                this.map.put(current, 0);
            } else {
                this.map.put(current, 6);
            }
        }
    }

    private void filterFiveLength() {
        Set<Character> charsOne = this.getChars(1);
        Set<Character> charsSix = this.getChars(6);

        for (String current : ImmutableSet.copyOf(this.fiveLengthList)) {
            Set<Character> currentList = this.getChars(current);
            boolean isThree = charsOne.stream().allMatch(value -> currentList.contains(value));
            boolean isFive = currentList.stream().allMatch(value -> charsSix.contains(value));
            if (isThree) {
                this.map.put(current, 3);
            } else if (isFive) {
                this.map.put(current, 5);
            } else {
                this.map.put(current, 2);
            }
        }
    }

    private Set<Character> getChars(int number) {
        return this.getChars(this.map.inverse().get(number));
    }

    private Set<Character> getChars(String chain) {
        return chain.chars().mapToObj(value -> Character.valueOf((char) value)).collect(ImmutableSet.toImmutableSet());
    }

//      abcdefg 	8		_
//      a_c__f_		7		bdeg
//      _bcd_f_		4		aeg
//      __c__f_		1		abdeg
//      ab_defg		6		c
//      abcd_fg		9		e
//      abc_efg		0		d
//      ab_d_fg		5		ce
//      a_cde_g		2		bf
//      a_cd_fg		3		be
//
//      1	->	length 2
//      4	->	length 4
//      7	->	length 3
//      8	->	length 7
//      9	->	length 6 + contains 4,7
//      0	->	length 6 + contains 7
//      6	->	length 6 + no contains 7
//      3	->	length 5 + contains 1
//      5	->	length 5 + is into 6
//      2	->	length 5 + the last
//
}
