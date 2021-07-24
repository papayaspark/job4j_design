package io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] values = text.toString().split(System.lineSeparator());
            for (var num: values) {
                if (Integer.parseInt(num) % 2 == 0) {
                    System.out.println(num + " is even");
                } else {
                    System.out.println(num + " is not even");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
