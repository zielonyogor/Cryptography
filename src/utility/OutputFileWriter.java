package utility;

import java.io.*;

public class OutputFileWriter {
    public static void WriteOutput(String filename ,String output) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(file, false))) {
                out.println(output);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error handling the file", e);
        }
    }
}
