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
        ImageSize imageSizeAnalyzer = new ImageSize();

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
                    textFileAnalyzer.analyze(filePath);
                    textFileAnalyzer.printInfo();
                } else if (filename.endsWith(".java")) {
                    javaFileAnalyzer.analyze(filePath);
                    javaFileAnalyzer.printInfo();
                } else if (filename.endsWith(".py")) {
                    pyFileAnalyzer.analyze(filePath);
                    pyFileAnalyzer.printInfo();
                } else if (filename.endsWith(".jpg") || filename.endsWith(".png")) {
                    imageSizeAnalyzer.setTargetFileName(filename);
                    imageSizeAnalyzer.printInfo();
                } else {
                    System.out.println("Unsupported file type.");
                }
        } else if (input.equalsIgnoreCase("status")) {
                fileStatus.checkForNewFilesAndMissingFiles();
                System.out.println("-------------");
                System.out.println("File Status:");
                fileStatus.showFileStatus();
                System.out.println("-------------");
            } else {
                switch (input) {
                    case "commit" -> {
                        LocalDateTime currentDateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedDateTime = currentDateTime.format(formatter);
                        System.out.println("-------------");
                        System.out.println("Created Snapshot at: " + formattedDateTime);
                        System.out.println("-------------");
                        fileStatus.resetAllFileStatus();
                        fileStatus.commit(); // Set the committed flag to true
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
            System.exit(1);
        }

        String folderPath = args[0];
        FileStatus fileStatus = new FileStatus(folderPath);
        MenuManager menuManager = new MenuManager(folderPath, fileStatus);
        menuManager.processOptions();
    }
}
