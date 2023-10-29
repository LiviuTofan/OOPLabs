import java.io.IOException;
import java.nio.file.*;

public class DetectChanges {
    private WatchService watchService;
    private final FileStatus fileStatus;

    // Constructor that takes the folderPath and a FileStatus object
    public DetectChanges(String folderPath, FileStatus fileStatus) {
        this.fileStatus = fileStatus;

        try {
            // Create a WatchService and register it to monitor the folder for file modifications
            this.watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(folderPath);
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Start monitoring for file changes
    public void startMonitoring() {
        while (true) {
            try {
                WatchKey key = watchService.take();

                Thread.sleep(1000);

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Path changedFile = (Path) event.context();
                        String fileName = changedFile.toString();

                        if (!fileName.endsWith("~")) {
                            System.out.println();
                            System.out.println("-------------------------------");
                            System.out.println("File: " + fileName + " - changed");
                            System.out.println("-------------------------------");

                            // Update the file status as changed
                            fileStatus.fileChanged(fileName);
                        }
                    }
                }

                key.reset();
                System.out.print("Enter your choice: ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: DetectChanges <folder path>");
            return;
        }

        String folderPath = args[0];
        FileStatus fileStatus = new FileStatus(folderPath);
        DetectChanges detector = new DetectChanges(folderPath, fileStatus);
        detector.startMonitoring();
    }
}
