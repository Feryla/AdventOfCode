package net.feryla.adventofcode;

import java.util.Arrays;

/**
 *
 * @author feryla
 */
public class Day2 {

    private static final String input = Helper.loadInput("day2").trim();

    public static void main(String[] args) {

        int p1 = Arrays.asList(input.split("\n")).stream().parallel().mapToInt(str -> {
            int[] dims = Arrays.asList(str.split("x")).stream().mapToInt(dim -> Integer.parseInt(dim)).sorted().toArray();
            return dims[0] * dims[1] * 2 + dims[1] * dims[2] * 2 + dims[0] * dims[2] * 2 + dims[0] * dims[1];
        }).sum();
        
        System.out.println("Part 1: " + p1);

        int p2 = Arrays.asList(input.split("\n")).stream().parallel().mapToInt(str -> {
            int[] dims = Arrays.asList(str.split("x")).stream().mapToInt(dim -> Integer.parseInt(dim)).sorted().toArray();
            return dims[0]*2 + dims[1]*2  + dims[0]*dims[1] * dims[2];
        }).sum();
        System.out.println("Part 2: " + p2);
    }

}
