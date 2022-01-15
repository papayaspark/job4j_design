package io;

import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import io.Search;

import static java.util.Objects.isNull;

public class Zip {
    public static void packFiles(List<File> sources, File target) {
        if (!target.exists()) {
            throw new IllegalArgumentException("File does not exist");
        }
        if (isNull(sources)) {
            throw new IllegalArgumentException("Source does not exist");
        }

        try (ZipOutputStream zips = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var file:
                 sources) {
            zips.putNextEntry(new ZipEntry(file.getPath()));
            try (BufferedInputStream outs = new BufferedInputStream(new FileInputStream(file))) {
                zips.write(outs.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName check = ArgsName.of(args);
        System.out.println(check.get("d"));
        System.out.println(check.get("o"));
        String[] end = args[1].split("\\.");
        System.out.println(end[1]);
        Predicate<Path> condition = p -> p.toFile().getName().endsWith(end[1]);
        Predicate<Path> noNcondition = condition.negate();


        List<Path> sources = new ArrayList<>();

        try {
            sources.addAll(Search.search(Paths.get(check.get("d")),
                    noNcondition));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<File> files = new ArrayList<>();
        for (var path: sources) {
            files.add(path.toFile());
        }
        File target = new File(check.get("o"));
//
        packFiles(files, target);
    }
}
