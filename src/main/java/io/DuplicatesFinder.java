package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor dVisit = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), dVisit);
        dVisit.duplicates.forEach(System.out::println);
    }
}
