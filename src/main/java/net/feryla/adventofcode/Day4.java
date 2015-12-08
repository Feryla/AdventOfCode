package net.feryla.adventofcode;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author feryla
 */
public class Day4 {

    private static final String input = "ckczppom";

    public static void main(String[] args) {
        OptionalInt p1 = IntStream.iterate(0, i -> i + 1).parallel().filter(i -> DigestUtils.md5Hex(input + i).startsWith("00000")).findFirst();
        System.out.println("Part 1: " + p1);

        OptionalInt p2 = IntStream.iterate(0, i -> i + 1).parallel().filter(i -> DigestUtils.md5Hex(input + i).startsWith("000000")).findFirst();
        System.out.println("Part 2: " + p2);
    }
}
