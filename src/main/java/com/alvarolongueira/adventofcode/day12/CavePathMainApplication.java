package com.alvarolongueira.adventofcode.day12;

import java.util.Set;

public class CavePathMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day12/input.txt";

    public static void main(String[] args) {
        CavePathService service = new CavePathService(filePath);

        Set<CavePath> paths = service.calculate();
        System.out.println("Paths availables: " + paths.size());

        System.out.println("----------------");

        Set<CavePath> pathsRepeating = service.calculate(true);
        System.out.println("Paths availables repeating: " + pathsRepeating.size());
    }
}
