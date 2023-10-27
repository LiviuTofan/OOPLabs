import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.*;

public class JavaFiles {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: JavaFiles <directory>");
            return;
        }

        String folderPath = args[0];
        JavaFiles javaAnalyzer = new JavaFiles();
        javaAnalyzer.analyze(folderPath);
    }

    public void analyze(String folderPath) {
        Path directory = Paths.get(folderPath);
        if (!Files.isDirectory(directory)) {
            System.out.println("The specified path is not a directory.");
            return;
        }

        try {
            Files.walk(directory)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(this::analyzeJavaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analyzeJavaFile(Path file) {
        String fileName = file.getFileName().toString(); // Get the file name without the path

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            int lineCount = 0;
            int classCount = 0;
            int methodCount = 0;

            String line;
            boolean inCommentBlock = false;

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

            System.out.println("File: " + fileName); // Print only the file name, not the full path
            System.out.println("Lines: " + lineCount);
            System.out.println("Classes: " + classCount);
            System.out.println("Methods: " + methodCount);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
