package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    ArrayList<String> duplicates = new ArrayList<>();
    Set<FileProperty> setFile = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileProperty fProp = new FileProperty(attrs.size(), file.getFileName().toString());
        String way = file.toString();
        if (!setFile.add(fProp)) {
                duplicates.add(file.toString());
            }
        duplicates.forEach(System.out::println);
        return FileVisitResult.CONTINUE;
    }
}
