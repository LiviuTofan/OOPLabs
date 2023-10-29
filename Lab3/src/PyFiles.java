import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PyFiles {
    // Analyze a single Python file
    public void analyzePythonFile(File file) {
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
            System.out.println("-------------");
            System.out.println("File: " + file.getName());
            System.out.println("Line Count: " + lines);
            System.out.println("Class Count: " + classes);
            System.out.println("Method Count: " + methods);
            System.out.println("-------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Print information for a specific Python file
    public void printPythonFileInfo(String fileName) {
        File file = new File(fileName);

        if (file.exists() && file.isFile() && file.getName().endsWith(".py")) {
            analyzePythonFile(file);
        } else {
            System.out.println("Invalid Python file path or the file doesn't exist.");
        }
    }
}
