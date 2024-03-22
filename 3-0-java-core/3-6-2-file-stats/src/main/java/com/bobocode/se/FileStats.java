package com.bobocode.se;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    private final Map<Character, Long> chars;

    FileStats(String fileName) {
        Path file;
        try {
            file = Path.of(FileStats.class.getClassLoader().getResource(fileName).toURI());
        } catch (Exception e) {
            throw new FileStatsException(e.getMessage());
        }
        try (Stream<String> lines = Files.lines(file)) {
            chars = lines
                    .flatMapToInt(String::chars)
                    .filter(c -> !Character.isSpaceChar(c))
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (Exception e) {
            throw new FileStatsException(e.getMessage());
        }
    }

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        return new FileStats(fileName);
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return chars.get(character).intValue();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        return Collections.max(chars.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return chars.containsKey(character);
    }
}
