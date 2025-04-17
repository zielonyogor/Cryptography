package utility;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileManager {
    public static void writeOutput(String filename, String output) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();

                if (!fileCreated) {
                    throw new IOException("Unable to create file at specified path");
                }
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(file, false))) {
                out.println(output);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error handling the file", e);
        }
    }

    public static ArrayList<String> readFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            File file = new File(filename);
            if (!file.exists()) {
                throw new IOException("File does not exist");
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error handling the file", e);
        }

        return lines;
    }
}
