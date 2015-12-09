package net.feryla.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author feryla
 */
public class Day9 {

    private static final String input = Helper.loadInput("day9").trim();

    private static final Map<String, Node> nodes = new HashMap<>();

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(input.split("\n")).stream().map(str -> {
            String[] s = str.split(" ");
            Edge e = new Edge(s[0], s[2], Integer.valueOf(s[4]));
            return e;
        }).collect(Collectors.toList());

        edges.stream().filter(e -> e.v1.equals("Faerun")).forEachOrdered(e -> {
            if (nodes.values().isEmpty()) {
                Node n = new Node();
                n.id = e.v1;
                nodes.put(n.id, n);
            }
            Node n = new Node();
            n.id = e.v2;
            nodes.put(n.id, n);
        });

        edges.stream().forEach(e -> {
            nodes.get(e.v1).edges.add(e);
            nodes.get(e.v2).edges.add(e);
        });

        int min = 1000;
        for (String key : nodes.keySet()) {
            List<String> visited = new ArrayList<>();
            visited.add(key);
            int d = findShortestForRemainingUnvisited(nodes.get(key), visited);
            if (min > d) {
                min = d;
            }
        }
        System.out.println("Part 1: " + min);
        
        int max = 0;
        for (String key : nodes.keySet()) {
            List<String> visited = new ArrayList<>();
            visited.add(key);
            int d = findLongestForRemainingUnvisited(nodes.get(key), visited);
            if (max < d) {
                max = d;
            }
        }
        System.out.println("Part 2: " + max);
    }

    private static int findLongestForRemainingUnvisited(Node n, List<String> visited) {
        int max = 0;
        for (Edge e : n.edges) {
            List<String> v2 = new ArrayList<>(visited);
            if (e.v1.equals(n.id) && !visited.contains(e.v2)) {
                v2.add(e.v2);
                int d = findLongestForRemainingUnvisited(nodes.get(e.v2), v2);
                if (d + e.distance > max) {
                    max = d + e.distance;
                }
            } else if (!visited.contains(e.v1)) {
                v2.add(e.v1);
                int d = findLongestForRemainingUnvisited(nodes.get(e.v1), v2);
                if (d + e.distance > max) {
                    max = d + e.distance;
                }
            }
        }
        return max;
    }

    private static int findShortestForRemainingUnvisited(Node n, List<String> visited) {
        int min = 0;
        for (Edge e : n.edges) {
            List<String> v2 = new ArrayList<>(visited);
            if (e.v1.equals(n.id) && !visited.contains(e.v2)) {
                v2.add(e.v2);
                int d = findShortestForRemainingUnvisited(nodes.get(e.v2), v2);
                if (min == 0 || d + e.distance < min) {
                    min = d + e.distance;
                }
            } else if (!visited.contains(e.v1)) {
                v2.add(e.v1);
                int d = findShortestForRemainingUnvisited(nodes.get(e.v1), v2);
                if (min == 0 || d + e.distance < min) {
                    min = d + e.distance;
                }
            }
        }
        return min;
    }

}

class Node {
    String id;
    List<Edge> edges = new ArrayList<>();
}

class Edge {

    String v1;
    String v2;
    int distance;

    public Edge(String v1, String v2, int distance) {
        this.v1 = v1;
        this.v2 = v2;
        this.distance = distance;
    }
}
