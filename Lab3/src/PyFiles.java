import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PyFiles {
    public void analyze(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".py")) {
                    int lines = 0;
                    int classes = 0;
                    int methods = 0;

                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        boolean inMethod = false;
                        while ((line = reader.readLine()) != null) {
                            lines++;
                            line = line.trim();
                            if (line.startsWith("class ")) {
                                classes++;
                            } else if (line.startsWith("def ")) {
                                methods++;
                                inMethod = true;
                            } else if (inMethod && line.equals("):")) {
                                inMethod = false;
                            }
                        }

                        System.out.println("File: " + file.getName());
                        System.out.println("Line Count: " + lines);
                        System.out.println("Class Count: " + classes);
                        System.out.println("Method Count: " + methods);
                        System.out.println();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path or the directory doesn't exist.");
        }
    }
}