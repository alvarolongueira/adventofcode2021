package com.alvarolongueira.adventofcode.day13;

public class FoldPaperMainApplication {

    private final static String filePath = "com/alvarolongueira/adventofcode/day13/input.txt";

    public static void main(String[] args) {
        FoldPaperService service = new FoldPaperService(filePath);

        int dots = service.calculate(1);
        System.out.println("Visible dots: " + dots);

        System.out.println("----------------");

        service.calculate(99);
    }
}
