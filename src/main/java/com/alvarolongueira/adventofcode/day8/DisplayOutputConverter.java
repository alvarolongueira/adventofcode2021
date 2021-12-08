package com.alvarolongueira.adventofcode.day8;

import java.util.HashMap;
import java.util.Map;

public class DisplayOutputConverter {

    public static Map<String, Integer> buildConversor(DisplayOutput displayOutput) {
        Map<String, Integer> conversor = new HashMap<>();

        return conversor;
    }


//      abcdefg 	8		_
//      a_c__f_		7		bdeg
//      _bcd_f_		4		aeg
//      __c__f_		1		abdeg
//
//      ab_defg		6		c
//      abcd_fg		9		e
//      abc_efg		0		d
//
//      ab_d_fg		5		ce
//      a_cde_g		2		bf
//      a_cd_fg		3		be
//
//      1	->	length 2
//      4	->	length 4
//      7	->	length 3
//      8	->	length 7
//      6	->	length 6 + no tiene c (que si estan en 1,4,7) (9,0 tienen c y f)
//      9	->	length 6 + si contiene el 4
//      0	->	length 6 + no contiene el 4
//      5	->	length 5 + esta contenido en el 6
//      2	->	length 5 + no contiene el 1
//      3	->	length 5 + si contiene el 1
}
