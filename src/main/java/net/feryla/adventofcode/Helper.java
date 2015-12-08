package net.feryla.adventofcode;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author feryla
 */
public class Helper {

    public static String loadInput(String in) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = Helper.class.getClassLoader();
        File file = new File(classLoader.getResource(in).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
