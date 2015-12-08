package net.feryla.adventofcode;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author feryla
 */
public class Day5 {

    private static final String input = Helper.loadInput("day5").trim();

    public static void main(String[] args) {
        long p1 = Arrays.asList(input.split("\n")).stream().parallel()
                .filter(str -> !Pattern.compile("(ab|cd|pq|xy)").matcher(str).find())
                .filter(str -> Pattern.compile("(\\w)\\1+").matcher(str).find())
                .filter(str -> str.replaceAll("[^aeiou]", "").length() > 2)
                .count();
        System.out.println("Part 1: " + p1);

        long p2 = Arrays.asList(input.split("\n")).stream().parallel()
                .filter(str -> Pattern.compile("(\\w).\\1+").matcher(str).find())
                .filter(str -> Pattern.compile("(.{2}).*\\1").matcher(str).find())
                .count();
        System.out.println("Part 2: " + p2);
    }
}
