package net.feryla.adventofcode;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author feryla
 */
public class Day3 {

    private static final String input = Helper.loadInput("day3").trim();

    public static void main(String[] args) {
        char[] chars = input.toCharArray();
        
        Map<String, Integer> houses = new HashMap<>();
        houses.put("0_0", 1);
        
        final Point santa = new Point(0, 0);
        for (int i = 0; i < chars.length; i++) {
            moveSantaAndAddPresentToHouseMap(chars[i], santa, houses);
        }
        
        long p1 = houses.values().stream().parallel().filter(i -> i > 0).count();
        System.out.println("Part 1: " + p1);

        houses.clear();
        houses.put("0_0", 2);
        santa.move(0, 0);
        final Point robotSanta = new Point(0, 0);
        
        for (int i = 0; i < chars.length; i = i+2) {
            moveSantaAndAddPresentToHouseMap(chars[i], santa, houses);
        }
        for (int i = 1; i < chars.length; i = i+2) {
            moveSantaAndAddPresentToHouseMap(chars[i], robotSanta, houses);
        }
        
        long p2 = houses.values().stream().parallel().filter(i -> i > 0).count();
        System.out.println("Part 2: " + p2);
    }

    private static void moveSantaAndAddPresentToHouseMap(int c, final Point santa, Map<String, Integer> houses) {
        if (c == '<') santa.translate(1, 0);
        if (c == '>') santa.translate(-1, 0);
        if (c == 'v') santa.translate(0, -1);
        if (c == '^') santa.translate(0, 1);
        
        String key = santa.x + "_" + santa.y;
        houses.put(key, houses.getOrDefault(key, 0) + 1);
    }
    


}
