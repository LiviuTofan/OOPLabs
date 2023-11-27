import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PyFiles extends MyFile {
    private String currentFileName;
    private int lines;
    private int classes;
    private int methods;
    @Override
    public void analyze(String filePath) {
        File file = new File(filePath);
        currentFileName = file.getName();

        if (!file.exists() || !file.isFile() || !currentFileName.endsWith(".py")) {
            System.out.println("File not found or not a Python file.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            lines = 0;
            classes = 0;
            methods = 0;
            String line;
            boolean inMethod = false;

            // Regular expressions to match class and method definitions
            while ((line = reader.readLine()) != null) {
                lines++;
                line = line.trim(); // cut space in front of text
                if (line.startsWith("class ")) {
                    classes++;
                } else if (line.startsWith("def ")) {
                    methods++;
                    inMethod = true;
                } else if (inMethod && line.equals("):")) {
                    inMethod = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printInfo() {
        System.out.println("-------------");
        System.out.println("File: " + currentFileName);
        System.out.println("Line Count: " + lines);
        System.out.println("Class Count: " + classes);
        System.out.println("Method Count: " + methods);
        System.out.println("-------------");
    }
}
