import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.*;
import java.io.File;

public class JavaFiles extends MyFile {
    private String currentFileName;
    private int lineCount;
    private int classCount;
    private int methodCount;
    @Override
    public void analyze(String filePath) {
        File file = new File(filePath);
        currentFileName = file.getName();

        if (!file.exists() || !file.isFile() || !currentFileName.endsWith(".java")) {
            System.out.println("File not found or not a Java file.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            lineCount = 0;
            classCount = 0;
            methodCount = 0;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printInfo() {
        System.out.println("-------------");
        System.out.println("File: " + currentFileName);
        System.out.println("Lines: " + lineCount);
        System.out.println("Classes: " + classCount);
        System.out.println("Methods: " + methodCount);
        System.out.println("-------------");
    }
}
