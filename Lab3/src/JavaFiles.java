import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.*;
import java.io.File;

public class JavaFiles {
    // Analyze a Java file and display information about its classes and methods
    public void analyze(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName(); // Get the file name

        // Check if the file exists and is a Java file
        if (!file.exists() || !file.isFile() || !fileName.endsWith(".java")) {
            System.out.println("File not found or not a Java file.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            int lineCount = 0;
            int classCount = 0;
            int methodCount = 0;

            String line;
            boolean inCommentBlock = false;

            // Regular expressions to match class and method definitions
            Pattern classPattern = Pattern.compile("class\\s+([A-Za-z_][A-Za-z0-9_]*)");
            Pattern methodPattern = Pattern.compile("([A-Za-z_][A-Za-z0-9_]*)\\s*\\([^)]*\\)\\s*\\{");

            while ((line = reader.readLine()) != null) {
                lineCount++;

                if (inCommentBlock) {
                    if (line.contains("*/")) {
                        inCommentBlock = false;
                    }
                } else {
                    if (line.contains("/*")) {
                        inCommentBlock = true;
                    } else {
                        Matcher classMatcher = classPattern.matcher(line);
                        while (classMatcher.find()) {
                            classCount++;
                        }

                        Matcher methodMatcher = methodPattern.matcher(line);
                        while (methodMatcher.find()) {
                            methodCount++;
                        }
                    }
                }
            }

            // Display the analysis results
            System.out.println("-------------");
            System.out.println("File: " + fileName);
            System.out.println("Lines: " + lineCount);
            System.out.println("Classes: " + classCount);
            System.out.println("Methods: " + methodCount);
            System.out.println("-------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
