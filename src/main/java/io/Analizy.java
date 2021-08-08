package io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) throws FileNotFoundException {
        String s;
        String[] row = null;
        String[] nextRow = null;
        try(BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(new FileOutputStream(target)))) {
            for (s = in.readLine(); s != null; s = in.readLine()) {
                if (s.length() == 0) {
                    continue;
                }

                if (row == null && (s.startsWith("400") || s.startsWith("500"))) {
                    row = s.split(" ");
                    out.print(row[1]);
                    out.print(";");
                } else if (row != null && (s.startsWith("200") || s.startsWith("300"))) {
                    nextRow = s.split(" ");
                    out.print(nextRow[1]);
                    out.println();
                    row = null;
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException{
//        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
//            out.println("15:01:30;15:02:32");
//            out.println("15:10:30;23:12:32");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Analizy azy = new Analizy();
        azy.unavailable("source", "targets");
    }
}
