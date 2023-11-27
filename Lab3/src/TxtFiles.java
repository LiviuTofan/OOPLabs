import java.io.*;
import java.util.Scanner;

public class TxtFiles extends MyFile {
    private String currentFileName;
    private int lineCount;
    private int wordCount;
    private int charCount;
    @Override
    public void analyze(String filePath) {
        File file = new File(filePath);

        if (file.exists() && file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
            lineCount = countLines(file);
            wordCount = countWords(file);
            charCount = countCharacters(file);

            currentFileName = file.getName();
        }
    }

    @Override
    public void printInfo() {
        File[] files = readFolder();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                    if (file.getName().equalsIgnoreCase(currentFileName)) {
                        analyze(file.getAbsolutePath());

                        System.out.println("-------------");
                        System.out.println("File: " + currentFileName);
                        System.out.println("Line Count: " + lineCount);
                        System.out.println("Word Count: " + wordCount);
                        System.out.println("Character Count: " + charCount);
                        System.out.println("-------------");
                        break;
                    }
                }
            }
        }
    }

    public int countLines(File file) {
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

    public int countWords(File file) {
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

    public int countCharacters(File file) {
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
