package com.alvarolongueira.adventofcode.day13;

public class FoldPaperMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day13/input.txt";

    public static void main(String[] args) {
        FoldPaperService service = new FoldPaperService(filePath);

//        Set<CavePath> paths = service.calculate();
//        System.out.println("Paths availables: " + paths.size());

        System.out.println("----------------");

    }
}
