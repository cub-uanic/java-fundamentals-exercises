package com.bobocode.se;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        try {
            return String.join("\n", Files.readAllLines(
                    Path.of(FileReaders.class.getClassLoader().getResource(fileName).toURI())
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
