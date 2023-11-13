import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuManager {
    private final String folderPath;
    private final FileStatus fileStatus;

    public MenuManager(String folderPath, FileStatus fileStatus) {
        this.folderPath = folderPath;
        this.fileStatus = fileStatus;
        FolderChanges folderChanges = new FolderChanges(folderPath);
        folderChanges.startMonitoring(); // Start monitoring for file additions and deletions
    }

    public void processOptions() {
        Scanner scanner = new Scanner(System.in);
        TxtFiles textFileAnalyzer = new TxtFiles();
        JavaFiles javaFileAnalyzer = new JavaFiles();
        PyFiles pyFileAnalyzer = new PyFiles();

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

                if (filename.endsWith(".txt")) {
                    String result = textFileAnalyzer.analyzeTextFile(filePath);
                    System.out.println("-------------");
                    System.out.println(result);
                    System.out.println("-------------");
                } else if (filename.endsWith(".java")) {
                    javaFileAnalyzer.analyze(filePath);
                } else if (filename.endsWith(".py")) {
                    pyFileAnalyzer.printPythonFileInfo(filePath);
                }  else if (filename.endsWith(".jpg") || filename.endsWith(".png")) {
                    File imageFile = new File(filePath);
                    String imageSize = ImageSize.getImageSize(imageFile);
                    System.out.println("-------------");
                    System.out.println("Image Size: " + imageSize);
                    System.out.println("-------------");
            } else {
                    System.out.println("Unsupported file type.");
                }
            } else if (input.equalsIgnoreCase("status")) {
                fileStatus.checkForNewFilesAndMissingFiles();
                System.out.println("-------------");
                System.out.println("File Status:");
                fileStatus.showFileStatus();
                System.out.println("-------------");
            }
            else {
                switch (input) {
                    case "commit" -> {
                        LocalDateTime currentDateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedDateTime = currentDateTime.format(formatter);
                        System.out.println("-------------");
                        System.out.println("Created Snapshot at: " + formattedDateTime);
                        System.out.println("-------------");
                        fileStatus.resetAllFileStatus();
                    }
                    case "exit" -> {
                        System.out.println("Exiting the program");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice. Please choose a valid option.");
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java MenuManager <folderPath>");
            System.exit(1);
        }

        String folderPath = args[0];
        FileStatus fileStatus = new FileStatus(folderPath);
        MenuManager menuManager = new MenuManager(folderPath, fileStatus);
        menuManager.processOptions();
    }
}