import java.io.File;
import java.util.Scanner;

public class MenuManager {
    private final String folderPath;
    private final FileStatus fileStatus; // Object to track file status changes
    private final PyFiles pyFileAnalyzer; // Object to analyze Python files

    // Constructor to initialize the folder path, file status object, and Python file analyzer
    public MenuManager(String folderPath, FileStatus fileStatus) {
        this.folderPath = folderPath;
        this.fileStatus = fileStatus; // Initialize the FileStatus object
        this.pyFileAnalyzer = new PyFiles(); // Initialize the PyFiles object
    }

    // Process user options in the menu
    public void processOptions() {
        Scanner scanner = new Scanner(System.in);
        TxtFiles textFileAnalyzer = new TxtFiles();
        JavaFiles javaFileAnalyzer = new JavaFiles();

        while (true) {
            System.out.println("Menu:");
            System.out.println("commit");
            System.out.println("info <filename>");
            System.out.println("status");
            System.out.println("exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().toLowerCase();

            if (input.startsWith("info ")) {
                String filename = input.substring(5);
                String filePath = this.folderPath + File.separator + filename;

                // Analyze and display information based on the file type
                if (filename.endsWith(".txt")) {
                    String result = textFileAnalyzer.analyzeTextFile(filePath);
                    System.out.println("-------------");
                    System.out.println(result);
                    System.out.println("-------------");
                } else if (filename.endsWith(".java")) {
                    javaFileAnalyzer.analyze(filePath);
                } else if (filename.endsWith(".py")) {
                    pyFileAnalyzer.printPythonFileInfo(filePath);
                } else if (filename.endsWith(".jpg") || filename.endsWith(".png")) {
                    // Analyze and display image size for .jpg and .png files
                    File imageFile = new File(filePath);
                    String imageSize = ImageSize.getImageSize(imageFile);
                    System.out.println("-------------");
                    System.out.println("Image Size: " + imageSize);
                    System.out.println("-------------");
                } else {
                    System.out.println("Unsupported file type.");
                }
            } else if (input.equalsIgnoreCase("status")) {
                // Display the status of tracked files
                System.out.println("-------------");
                System.out.println("File Status:");
                fileStatus.showFileStatus();
                System.out.println("-------------");
            } else {
                switch (input) {
                    case "commit" -> System.out.println("Commit option selected");

                    // Add your commit logic using this.folderPath
                    case "exit" -> {
                        System.out.println("Exiting the program");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice. Please choose a valid option.");
                }
            }
        }
    }
}
