package net.feryla.adventofcode;

/**
 *
 * @author feryla
 */
public class Day1 {

    private static final String input = Helper.loadInput("day1").trim();

    public static void main(String[] args) {

        int p1 = input.chars().map(c -> c == '(' ? 1 : -1).sum();
        System.out.println("Part 1: " + p1);

        int p2 = firstBasement();
        System.out.println("Part 2: " + p2);
    }

    private static int firstBasement() {
        int floor = 0;
        for (int i = 0; i <input.length(); i++)   {
            if (input.charAt(i) == '(') floor++;
            else floor--;
            
            if (floor == -1) {
                return i + 1;
            }
        }
        return -1;
    }

}
