package io;

import java.io.FileOutputStream;

public class File {
    public static void main(String[] args) {
        int line, col, rsl;
                try (FileOutputStream out = new FileOutputStream("result.txt")) {
                    for (line = 1; line < 11; line++) {
                        System.out.println();
                        for (col = 1; col < 11; col++) {
                            rsl = line * col;
                            System.out.print(rsl + " ");
                            out.write(String.valueOf(rsl + " ").getBytes());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
        }
    }
}
