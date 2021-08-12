package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        List<File> listFile = new ArrayList<>();
        ArrayList<File> duplicates = new ArrayList<>();
        Set<File> setFile = new HashSet<>();
        File files = file.toFile();
        if (!files.exists()) {
            throw new IllegalArgumentException(String.format("Not existed %s", files.getAbsoluteFile()));
        }
        listFile = Arrays.asList(files.listFiles());
        for (File xFile : listFile) {
            if (xFile.isDirectory()) {
                visitFile(xFile.toPath(), attrs);
            } else if (!setFile.add(xFile)) {
                duplicates.add(xFile);
            }
        }
        duplicates.forEach(System.out::println);
        return FileVisitResult.CONTINUE;
    }
}
