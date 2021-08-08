package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String[] check;
        String s;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (s = read.readLine(); s != null; s = read.readLine()) {
                if (s.length() == 0 || s.startsWith("#")) {
                    continue;
            }
                check = s.split("=");
                if (check.length != 2) {
                    throw new IllegalArgumentException("Error in key-value");
                }
                values.put(check[0], check[1]);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
