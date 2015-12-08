package net.feryla.adventofcode;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author feryla
 */
public class Day6 {

    private static final String input = Helper.loadInput("day6").trim().replaceAll("turn ", "turn");
    private static final Map<String, Integer> lights = new HashMap<>();

    public static void main(String[] args) {
        Arrays.asList(input.split("\n")).stream().forEachOrdered(str -> {
            String[] command = str.split(" ");
            Point start = getPos(command[1]);
            Point end = getPos(command[3]);
            updateLights(command, start, end);
        });
        
        long p1 = lights.values().stream().parallel().filter(i -> i > 0).count();
        System.out.println("Part 1: " + p1);
        
        lights.clear();
        Arrays.asList(input.split("\n")).stream().forEachOrdered(str -> {
            String[] command = str.split(" ");
            Point start = getPos(command[1]);
            Point end = getPos(command[3]);
            updateLights2(command, start, end);
        });

        long p2 = lights.values().stream().reduce(0, (a,b) -> a + b).longValue();
        System.out.println("Part 2: " + p2);
    }

    private static void updateLights(String[] command, Point start, Point end) {
        for (int x = start.x; x <= end.x; x++) {
            for (int y = start.y; y <= end.y; y++) {
                String key = x + "_" + y;
                int current = lights.getOrDefault(key, 0);
                switch (command[0]) {
                    case "toggle":
                        lights.put(key, current > 0 ? 0: 1);
                        break;
                    case "turnon":
                        lights.put(key, 1);
                        break;
                    case "turnoff":
                        lights.put(key, 0);
                        break;
                }
            }
        }
    }
    
    private static void updateLights2(String[] command, Point start, Point end) {
        for (int x = start.x; x <= end.x; x++) {
            for (int y = start.y; y <= end.y; y++) {
                String key = x + "_" + y;
                int current = lights.getOrDefault(key, 0);
                switch (command[0]) {
                    case "toggle":
                        lights.put(key, current + 2);
                        break;
                    case "turnon":
                        lights.put(key, current + 1);
                        break;
                    case "turnoff":
                        lights.put(key, current > 0 ? current -1: 0);
                        break;
                }
            }
        }
    }

    private static Point getPos(String posStr) {
        String[] split = posStr.split(",");
        return new Point(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    }
}
