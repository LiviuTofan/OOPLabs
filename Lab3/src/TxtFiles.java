import java.io.*;
import java.util.Scanner;

public class TxtFiles {
    // Analyze a text file and return information about it
    public String analyzeTextFile(String filePath) {
        File file = new File(filePath);

        // Check if the file exists and is a text file
        if (file.exists() && file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
            int lineCount = countLines(file);
            int wordCount = countWords(file);
            int charCount = countCharacters(file);

            // Construct a result string with file information

            return "File: " + file.getName() + "\n" +
                    "Line Count: " + lineCount + "\n" +
                    "Word Count: " + wordCount + "\n" +
                    "Character Count: " + charCount;
        } else {
            return "Invalid text file path or the file doesn't exist.";
        }
    }

    // Count the number of lines in a text file
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

    // Count the number of words in a text file
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

    // Count the number of characters in a text file
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
