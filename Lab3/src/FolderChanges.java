import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FolderChanges {
    private final String folderPath;
    private final List<Path> addedFiles = new ArrayList<>();
    private final List<Path> deletedFiles = new ArrayList<>();

    public FolderChanges(String folderPath) {
        this.folderPath = folderPath;
    }

    public void startMonitoring() {
        Thread thread = new Thread(() -> {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path path = Paths.get(folderPath);
                path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

                while (true) {
                    WatchKey key;
                    try {
                        key = watchService.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        Path filePath = (Path) event.context();

                        // Check if the file name matches a pattern for temporary files
                        if (!filePath.toString().endsWith("~")) {
                            if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                                addedFiles.add(filePath);
                                System.out.println();
                                System.out.println("-------------");
                                System.out.println("File added: " + filePath);
                                System.out.println("-------------");
                                System.out.print("Enter your choice: ");
                            } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                                deletedFiles.add(filePath);
                                System.out.println();
                                System.out.println("-------------");
                                System.out.println("File deleted: " + filePath);
                                System.out.println("-------------");
                                System.out.print("Enter your choice: ");
                            }
                        }
                    }

                    key.reset();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }
}

