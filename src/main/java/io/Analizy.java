package io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) throws FileNotFoundException {
        String s;
        String[] row = null;
        String[] nextRow = null;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(new FileOutputStream(target)))) {
            for (s = in.readLine(); s != null; s = in.readLine()) {
                if (s.length() == 0) {
                    continue;
                }

                if (row == null && (s.startsWith("400") || s.startsWith("500"))) {
                    row = s.split(" ");
                    out.printf("%s;", row[1]);
                } else if (row != null && (s.startsWith("200") || s.startsWith("300"))) {
                    nextRow = s.split(" ");
                    out.printf("%s;%n", nextRow[1]);
                    row = null;
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Analizy azy = new Analizy();
        azy.unavailable("source", "targets");
    }
}
