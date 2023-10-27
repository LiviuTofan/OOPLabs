import java.io.*;
import java.util.Scanner;

public class TxtFiles {
    public static void analyzeTextFilesInDirectory(String folderPath) {
        if (folderPath != null) {
            File directory = new File(folderPath);

            if (directory.exists() && directory.isDirectory()) {
                File[] textFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

                if (textFiles != null) {
                    for (File file : textFiles) {
                        int lineCount = countLines(file);
                        int wordCount = countWords(file);
                        int charCount = countCharacters(file);

                        System.out.println("File: " + file.getName());
                        System.out.println("Line Count: " + lineCount);
                        System.out.println("Word Count: " + wordCount);
                        System.out.println("Character Count: " + charCount);
                        System.out.println();
                    }
                } else {
                    System.out.println("No .txt files found in the directory.");
                }
            } else {
                System.out.println("Invalid directory path or the directory doesn't exist.");
            }
        } else {
            System.out.println("Folder path is null.");
        }
    }

    public static int countLines(File file) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }

    public static int countWords(File file) {
        int wordCount = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                scanner.next();
                wordCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wordCount;
    }

    public static int countCharacters(File file) {
        int charCount = 0;
        try (FileReader reader = new FileReader(file)) {
            while (reader.read() != -1) {
                charCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charCount;
    }
}
